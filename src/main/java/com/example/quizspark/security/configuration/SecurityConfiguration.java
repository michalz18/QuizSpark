package com.example.quizspark.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * Configures security settings for the QuizSpark application.
 * This configuration sets up CORS, CSRF protection, session management, and route-specific security rules.
 *
 * It integrates a custom JWT authentication filter to secure the application's API endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Constructs a {@code SecurityConfiguration} instance with the specified JWT authentication filter.
     *
     * @param jwtAuthFilter the JWT authentication filter to be added to the security filter chain.
     */
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Configures the {@link HttpSecurity} object to set up security rules for the application.
     *
     * <p>It disables CSRF protection for simplicity and configures CORS to allow all origins.
     * The method also configures endpoint-specific access controls, ensuring that authentication
     * endpoints are publicly accessible while admin endpoints require the ADMIN authority.
     * All other requests require authentication.</p>
     *
     * <p>Session management is configured to be stateless to support JWT authentication,
     * and the custom JWT authentication filter is registered in the filter chain.</p>
     *
     * @param http the {@link HttpSecurity} to configure.
     * @return the configured {@link SecurityFilterChain}.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("*"));
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedHeaders(List.of("*"));
                    return configuration;
                }))
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers(new AntPathRequestMatcher("/api/authentication/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(sessionMgmt -> sessionMgmt
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
