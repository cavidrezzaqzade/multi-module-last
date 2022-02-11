package az.gov.mia.grps.auth.config;

import az.gov.mia.grps.auth.service.UserDetailsServiceImpl;
import az.gov.mia.grps.auth.utility.SShaPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

//    //@Autowired
//    private SShaPasswordEncoder sShaPasswordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //    @Bean
//    public AuthTokenFilter authTokenFilter(){
//        return new AuthTokenFilter();
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        System.out.println("66666");
//        registry.addMapping("/**");
//               // .allowedOrigins("http://localhost", "http://localhost:3002", "http://localhost:80", "http://134.209.246.138:80", "http://134.209.246.138")                .allowedMethods("*");
//    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(sShaPasswordEncoder());
        // @formatter:off

//                .and()
//                .withUser("tom").password(passwordEncoder.encode("111")).roles("ADMIN").and()
//                .withUser("user1").password(passwordEncoder.encode("pass")).roles("USER").and()
//                .withUser("admin").password(passwordEncoder.encode("nimda")).roles("ADMIN");
    }
    // @formatter:on


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        System.out.println("http "+http.toString());
        // @formatter:off

              http.cors().and().csrf().disable().httpBasic().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                      .and()

                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .antMatchers("/oauth").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/oauth/token/revokeById/**").permitAll()
                .antMatchers("/tokens/**").permitAll()
                .antMatchers("/.well-known/jwks.json").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .disable();

//        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
//        http.cors().disable();
    }

    //@Bean
    //@Primary
    public SShaPasswordEncoder sShaPasswordEncoder() {
        return new SShaPasswordEncoder();
    }

}
