package com.example.stock.config;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

import com.example.stock.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .map(user -> User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER")
                        .build())
                .orElseThrow();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
            .requestMatchers(HttpMethod.GET,  "/api/products").permitAll()
                .requestMatchers(HttpMethod.GET,  "/api/magazine/products").permitAll()
                .requestMatchers(HttpMethod.POST,  "/api/magazine/products").permitAll()
                .requestMatchers(HttpMethod.PUT,  "/api/magazine/products/chiqim").permitAll()
                .requestMatchers(HttpMethod.GET,  "/api/magazine/hisobot").permitAll()
                .requestMatchers(HttpMethod.DELETE,  "/api/magazine/hisobot").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/products").permitAll()
            .requestMatchers(HttpMethod.GET,  "/api/documents").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/documents").permitAll()
            .requestMatchers(HttpMethod.GET,  "/api/stocks").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/stocks").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/documents/{id}/items").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/reports").permitAll()
            .requestMatchers(HttpMethod.GET,  "/api/debts").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/debts").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/debts/*/payments").permitAll()
            .requestMatchers(HttpMethod.PUT,    "/api/stocks/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/stocks/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults());

    return http.build();
}
}