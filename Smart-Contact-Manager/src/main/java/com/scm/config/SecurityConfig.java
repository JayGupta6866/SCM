package com.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    // create user and login using java code with in-memory service
    // @Bean
    // public UserDetailsService userDetailsService() {

    //     // default User
    //     UserDetails user1 = User.withDefaultPasswordEncoder().
    //             username("admin123").password("admin").roles("ADMIN", "USER").build();
    //     UserDetails user2 = User.withDefaultPasswordEncoder()
    //             .username("user123").password("user").roles("USER").build();

    //     InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2);
    //     return inMemoryUserDetailsManager;
    // }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(null);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
