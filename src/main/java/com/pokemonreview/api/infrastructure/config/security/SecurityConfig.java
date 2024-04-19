package com.pokemonreview.api.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    @Order(1)
    public DefaultSecurityFilterChain freeForAllFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/", "/error", "/login", "/api/login", "/logout", "/favicon.ico").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        
        http.headers().frameOptions().disable();
        
        return http.build();
    }
    
    @Bean
    @Order(2)
    SecurityFilterChain forAuthenticatedUsersFilterChain(HttpSecurity http) throws Exception {
        
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/for-users").hasAnyRole("USER", "ADMIN");
        });
        
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        final var encoder =  new BCryptPasswordEncoder();
        return encoder;
    }

}
