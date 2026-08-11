package com.civicbridge.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                /*
                 * REST API ke liye CSRF disable
                 */
                .csrf(csrf -> csrf.disable())

                /*
                 * JWT based authentication hai,
                 * isliye session STATELESS rahega.
                 */
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                /*
                 * Public aur protected endpoints
                 */
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        /*
                         * Admin APIs
                         */
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        /*
                         * Officer APIs
                         */
                        .requestMatchers("/api/officer/**")
                        .hasRole("OFFICER")

                        /*
                         * Citizen APIs
                         */
                        .requestMatchers("/api/citizen/**")
                        .hasRole("CITIZEN")

                        /*
                         * Complaint APIs
                         *
                         * Login ke baad accessible.
                         */
                        .requestMatchers("/api/complaints/**")
                        .authenticated()

                        /*
                         * Baaki APIs
                         */
                        .anyRequest()
                        .authenticated()
                )

                /*
                 * Unauthorized response
                 */
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(
                                authenticationEntryPoint
                        )
                )

                /*
                 * Authentication Provider
                 */
                .authenticationProvider(
                        authenticationProvider()
                )

                /*
                 * JWT Filter ko UsernamePasswordAuthenticationFilter
                 * se pehle execute karna hai.
                 */
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(
                customUserDetailsService
        );

        provider.setPasswordEncoder(
                passwordEncoder
        );

        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration
                .getAuthenticationManager();
    }
}