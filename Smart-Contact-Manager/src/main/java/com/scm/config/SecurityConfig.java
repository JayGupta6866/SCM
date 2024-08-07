package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.impl.CustomUserDetailService;

@Configuration
public class SecurityConfig {
    // create user and login using java code with in-memory service
    // @Bean
    // public UserDetailsService userDetailsService() {

    // // default User
    // UserDetails user1 = User.withDefaultPasswordEncoder().
    // username("admin123").password("admin").roles("ADMIN", "USER").build();
    // UserDetails user2 = User.withDefaultPasswordEncoder()
    // .username("user123").password("user").roles("USER").build();

    // InMemoryUserDetailsManager inMemoryUserDetailsManager = new
    // InMemoryUserDetailsManager(user1, user2);
    // return inMemoryUserDetailsManager;
    // }

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private OAuthAuthenticationSucessHandler handler;

    // CONFIGURATION OF AUTHENCIATION PROVIDER
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // object of user detail service
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // object of password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // configure security of urls to allow and disallow access to authorized users
        http.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home","/signup").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        // .csrf((csrf) -> csrf.disable());

        // default form loginpage
        http.formLogin(formLogin -> {
            // redirect to this url
            formLogin.loginPage("/login");
            // validate details on this page
            formLogin.loginProcessingUrl("/authenticate");
            // after successful login, redirect to this page
            formLogin.successForwardUrl("/user/dashboard");
            // after failed login, redirect to this page
            // formLogin.failureForwardUrl("/login?error=true");
            // this is the username parameter to look for when authenticating user
            formLogin.usernameParameter("email");
            // this is the password parameter to look for when authenticating user
            formLogin.passwordParameter("password");
        });

        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(logout -> {
            logout.logoutUrl("/do-logout");
            logout.logoutSuccessUrl("/login?logout=true");
        });

        // oauth Configuration

        http.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
