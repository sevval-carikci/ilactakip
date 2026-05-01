package com.ilactakip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/actuator/**").permitAll()

                        // Public
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()

                        // USER medicines
                        .requestMatchers(HttpMethod.GET, "/api/user/medicines/**")
                        .hasAnyRole("USER","MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/user/medicines/**")
                        .hasRole("USER")

                        // ADMIN medicines
                        .requestMatchers("/api/admin/medicines/**")
                        .hasRole("ADMIN")

                        // Users
                        .requestMatchers("/api/users/**")
                        .hasRole("ADMIN")

                        // Barcode
                        .requestMatchers("/api/barcodes/**")
                        .hasAnyRole("ADMIN","MANAGER","USER")

                        // Category & Role
                        .requestMatchers("/api/categories/**").hasRole("ADMIN")
                        .requestMatchers("/api/roles/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
