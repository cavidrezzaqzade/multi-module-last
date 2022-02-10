//package az.gov.mia.grp.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//
///**
// * @author Cavid Aslanov
// * @time 12/02/2021 - 6:57 PM
// **/
//
//@Configuration
//@EnableWebSecurity
////@EnableGlobalMethodSecurity(
////        // securedEnabled = true,
////        // jsr250Enabled = true,
////        prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
//
////
////    @Autowired
////    UserDetailsService userDetailsService;
//
////
////    @Override
////    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////    }
////
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//
////    @Bean
////    public PasswordEncoder encoder() {
////        return new BCryptPasswordEncoder(11);
////
////    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        System.out.println("configure:"+"i am configure");
//        //web.ignoring().antMatchers("/api-docs", "/configuration/**", "/swagger*", "/webjars/**");
//        web.ignoring().antMatchers("/v2/api-docs/**");
//        web.ignoring().antMatchers("/swagger.json");
//        web.ignoring().antMatchers("/swagger-ui.html");
//        web.ignoring().antMatchers("/swagger-resources/**");
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/api/test/**");
//        web.ignoring().antMatchers("/swagger/**");
//        //super.configure(web);
//
//    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        System.out.println("configure:"+"i am http");
//        http
//                .cors().and().csrf().disable()
//                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
////                .antMatchers("/api/auth/signin").permitAll()
////                .antMatchers("/api/adminhr/user/*").permitAll()
//
//                .anyRequest().permitAll()
//
//        ;
//
//               // http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//
//
//}
