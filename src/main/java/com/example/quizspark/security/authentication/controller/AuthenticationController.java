package com.example.quizspark.security.authentication.controller;

import com.example.quizspark.security.authentication.service.AuthenticationService;
import com.example.quizspark.security.authentication.requests.AuthenticationRequest;
import com.example.quizspark.security.authentication.requests.RegisterRequest;
import com.example.quizspark.security.authentication.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling authentication and registration requests.
 * Provides endpoints for user registration and authentication within the QuizSpark application.
 */
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Adjust the origins as per your front-end application's URL.
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Handles the registration of new users.
     *
     * @param request The registration request containing the user's details.
     * @return A {@link ResponseEntity} containing the {@link AuthenticationResponse} with the JWT token.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Handles authentication requests from users attempting to log in.
     *
     * @param request The authentication request containing the user's login credentials.
     * @return A {@link ResponseEntity} containing the {@link AuthenticationResponse} with the user's details and JWT token.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
