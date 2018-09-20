package org.mary.pro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // All requests send to the Web Server request must be authenticated
        http.authorizeRequests().anyRequest().authenticated();

        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);


    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = "123";
        String encrytedPassword = this.passwordEncoder().encode(password);

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //

                mngConfig = auth.inMemoryAuthentication();

        UserDetails u1 = User.withUsername("tom").password(encrytedPassword).roles("USER").build();
        UserDetails u2 = User.withUsername("jerry").password(encrytedPassword).roles("USER").build();

        mngConfig.withUser(u1);
        mngConfig.withUser(u2);
//        System.out.println("Encoded password of 123=" + encrytedPassword);
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password")
//                .authorities("ROLE_USER");
//    }

//    @Override
//    public void configure(WebSecurity webSecurity) throws Exception {
//        webSecurity
//                .ignoring()
//                .antMatchers("/resources/**");}

//        @Override
//        protected void configure(HttpSecurity http) throws Exception {

//                    .authorizeRequests()
//                    .antMatchers("/signup","/about").permitAll() // #4
//                    .antMatchers("/admin/**").hasRole("ADMIN") // #6
//                    .anyRequest().authenticated() // 7
//                    .and()
//                    .formLogin()  // #8
////                    .loginUrl("/login") // #9
//                    .permitAll(); // #5

//        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//		.and().formLogin();

    }
}


//        http.authorizeRequests()
//                .antMatchers("/securityNone").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(authenticationEntryPoint);
//
//        http.addFilterAfter(new CustomFilter(),
//                BasicAuthenticationFilter.class);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
