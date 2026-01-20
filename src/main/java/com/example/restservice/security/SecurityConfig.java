package com.example.restservice.security;

import com.example.restservice.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final AuthService authService;
    
    @Value("${cors.allowed-origin:}")
    private String allowedOrigin;
    
    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }
    
    @Bean
    public RateLimitingFilter rateLimitingFilter() {
        return new RateLimitingFilter();
    }
    
    @Bean
    public NacatechAuthenticationFilter nacatechAuthenticationFilter(
            com.example.restservice.security.SessionTokenService sessionTokenService) {
        return new NacatechAuthenticationFilter(authService, sessionTokenService);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, 
                                                   RateLimitingFilter rateLimitingFilter,
                                                   NacatechAuthenticationFilter nacatechAuthenticationFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Allow OPTIONS requests for CORS preflight
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                // Public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/greeting").permitAll()
                // All other endpoints require authentication (session token or nacatech token)
                .anyRequest().authenticated()
            )
            .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(nacatechAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .headers(headers -> headers
                .contentTypeOptions(contentTypeOptions -> {}) // X-Content-Type-Options: nosniff
                .frameOptions(frameOptions -> frameOptions.deny()) // X-Frame-Options: DENY
                .xssProtection(xssProtection -> xssProtection.headerValue(org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)) // X-XSS-Protection: 1; mode=block
                .referrerPolicy(referrerPolicy -> referrerPolicy.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
                .httpStrictTransportSecurity(hsts -> hsts
                    .maxAgeInSeconds(31536000) // 1 year
                )
            );
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Set allowed origin from configuration, or allow all for development if not set
        if (allowedOrigin != null && !allowedOrigin.isEmpty()) {
            configuration.setAllowedOrigins(List.of(allowedOrigin));
            configuration.setAllowCredentials(true); // Enable credentials when using specific origin
        } else {
            // Development fallback - allow all origins (should be set in production)
            configuration.setAllowedOrigins(List.of("*"));
            configuration.setAllowCredentials(false);
        }
        
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "X-User-Token", "X-Session-Token"));
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
