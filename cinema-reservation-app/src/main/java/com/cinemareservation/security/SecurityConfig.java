package com.cinemareservation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(AuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated() // All endpoints require authentication
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler) // Custom login page
                        .defaultSuccessUrl("/index", true)  // Redirect after successful login
                        .permitAll()  // Allow everyone to see the login page
                        .usernameParameter("username")  // Specify the name of the login field
                        .passwordParameter("password")  // Specify the name of the password field
                )
                .logout((logout) -> logout.permitAll());  // Allow everyone to perform logout
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
