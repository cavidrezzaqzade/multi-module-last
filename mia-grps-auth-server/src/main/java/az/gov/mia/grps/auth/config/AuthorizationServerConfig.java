package az.gov.mia.grps.auth.config;

import java.util.Arrays;

import az.gov.mia.grps.auth.utility.MyWebResponseExceptionTranslator;
import az.gov.mia.grps.auth.utility.SShaPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.jwt.clientId:grps}")
    private String clientId;

    @Value("${security.oauth2.jwt.client-secret:secret}")
    private String clientSecret;

    @Value("${security.oauth2.jwt.private-key:jwtsign123}")
    private String jwtPrivateKey;

    @Value("${security.oauth2.jwt.public-key:jwtsign123}")
    private String jwtPublicKey;

    @Value("${security.oauth2.accessTokenValidititySeconds:43200}") // 12 hours
    private int accessTokenValiditySeconds;

    @Value("${security.oauth2.refreshTokenValiditySeconds:2592000}") // 30 days
    private int refreshTokenValiditySeconds;

    @Value("${security.oauth2.authorizedGrantTypes:password,authorization_code,refresh_token}")
    private String[] authorizedGrantTypes;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Value("${security.oauth2.scopes:read,write}")
    private String[] authorizationScopes;
//    @Autowired
//    private SShaPasswordEncoder sShaPasswordEncoder;



    private static final String KEY_STORE_FILE = "bael-jwt.jks";
    private static final String KEY_STORE_PASSWORD = "bael-pass";
    private static final String KEY_ALIAS = "bael-oauth-jwt";
    private static final String JWK_KID = "bael-key-id";


//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients)
            throws Exception { // @formatter:off
                    clients.inMemory()
                .withClient(clientId)
                .secret("{bcrypt}"+passwordEncoder().encode(clientSecret))
                .authorizedGrantTypes(authorizedGrantTypes)
                .scopes(authorizationScopes)
                .accessTokenValiditySeconds(accessTokenValiditySeconds) // 1 hour
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds) // 30 days
                .autoApprove(true);
    }
    
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {

        System.out.println("CORSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        Map<String, CorsConfiguration> corsConfigMap = new HashMap<>();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //TODO: Make configurable
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        corsConfigMap.put("/oauth/token", config);
        endpoints.getFrameworkEndpointHandlerMapping()
                .setCorsConfigurations(corsConfigMap);

        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
        .exceptionTranslator(new MyWebResponseExceptionTranslator());
    }
    
    

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {

        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }


    @Bean
    public TokenStore tokenStore() {
       // return new InMemoryTokenStore();
         return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        Map<String, String> customHeaders = Collections.singletonMap("kid", JWK_KID);
//        converter.setSigningKey(jwtPrivateKey);
//        converter.setVerifierKey(jwtPublicKey);
       // converter.setKeyPair();
        return new JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair());
        //return converter;
    }

    @Bean
    public KeyPair keyPair() {
        ClassPathResource ksFile = new ClassPathResource(KEY_STORE_FILE);
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
        return ksFactory.getKeyPair(KEY_ALIAS);
    }

    @Bean
    public JWKSet jwkSet() {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(JWK_KID);
        return new JWKSet(builder.build());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
