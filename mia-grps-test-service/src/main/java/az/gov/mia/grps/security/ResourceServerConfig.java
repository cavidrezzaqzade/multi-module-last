package az.gov.mia.grps.security;

/**
 * @author Cavid Aslanov
 * @time 18/02/2021 - 10:39 AM
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import java.util.Map;

/**
 * @author Rasim R. İmanov
 */
@Configuration
@ConditionalOnProperty(value = "security.oauth2.resource.jwk.key-set-uri")
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private final String jwkSetUri;

    @Autowired
    public ResourceServerConfig(
            @Value("${security.oauth2.resource.jwk.key-set-uri}") String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        System.out.println("CONFIG JWK URI " + jwkSetUri);

        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/adminhr/user/addNewUser").permitAll()
                .antMatchers("/adminhr/works/check/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    public String tokenExtractor(HttpServletRequest request) {
//        System.out.println("request:"+request);
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println("header:"+header);
////        if (header != null)
////            return token.replace("Bearer ", "");
////        Cookie cookie = WebUtils.getCookie(request, "auth.access_token");
////        if (cookie != null)
////            return cookie.getValue();
//        return null;
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/api-docs", "/configuration/**", "/swagger*", "/webjars/**");
        web.ignoring().antMatchers("/v3/api-docs/**");
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-ui-custom.html"); // Custom template for swagger
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/api/test/**");
        //super.configure(web);

    }

    @Bean
    public TokenStore tokenStore() {
        return new JwkTokenStore(this.jwkSetUri, accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(new AccessTokenConverterImpl());
        return converter;
    }

    public static class AccessTokenConverterImpl extends DefaultAccessTokenConverter {

        @Override
        public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
            OAuth2Authentication authentication
                    = super.extractAuthentication(claims);
            authentication.setDetails(claims);
            return authentication;
        }

    }


}