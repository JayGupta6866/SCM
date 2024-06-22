package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.impl.CustomUserDetailService;

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

    @Autowired
    private CustomUserDetailService userDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //object of user detail service
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //object of password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
