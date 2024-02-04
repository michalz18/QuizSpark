package com.example.quizspark.security.configuration;

import com.example.quizspark.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Central configuration class for application-wide security settings.
 * Defines beans related to user authentication and password encoding.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    /**
     * Configures the {@link UserDetailsService} bean used to load user-specific data.
     * It leverages the {@link UserRepository} to fetch user details based on the username (email).
     *
     * @return A custom {@link UserDetailsService} implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Defines the {@link AuthenticationProvider} bean that integrates with Spring Security's
     * authentication mechanism. Utilizes a {@link DaoAuthenticationProvider} configured with
     * the custom {@link UserDetailsService} and a password encoder.
     *
     * @return An {@link AuthenticationProvider} for authenticating user credentials.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Configures the {@link PasswordEncoder} bean to use {@link BCryptPasswordEncoder} for hashing passwords.
     * This ensures that passwords are securely stored and verified.
     *
     * @return A {@link BCryptPasswordEncoder} instance for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the {@link AuthenticationManager} bean, obtained from the {@link AuthenticationConfiguration}.
     * This bean is essential for processing authentication requests within the application.
     *
     * @param config The {@link AuthenticationConfiguration} to retrieve the {@link AuthenticationManager}.
     * @return The global {@link AuthenticationManager} for the application.
     * @throws Exception If an error occurs during the configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
