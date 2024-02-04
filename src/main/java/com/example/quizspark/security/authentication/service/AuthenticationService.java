package com.example.quizspark.security.authentication.service;

import com.example.quizspark.admin.dto.UserDTO;
import com.example.quizspark.admin.mapper.UserMapper;
import com.example.quizspark.security.authentication.requests.AuthenticationRequest;
import com.example.quizspark.security.authentication.requests.RegisterRequest;
import com.example.quizspark.security.authentication.responses.AuthenticationResponse;
import com.example.quizspark.security.user.User;
import com.example.quizspark.security.user.UserRepository;
import com.example.quizspark.security.configuration.JwtService;
import com.example.quizspark.security.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service providing authentication functionalities such as user registration,
 * authentication, and retrieval of all user details.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user with the provided registration details. The first registered user gets an ADMIN role,
     * and subsequent users get a USER role.
     *
     * @param request The registration request containing user details.
     * @return An {@link AuthenticationResponse} containing the JWT token for the registered user.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        Role assignedRole = userRepository.count() == 0 ? Role.ADMIN : Role.USER;

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(assignedRole)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticates a user based on email and password.
     *
     * @param request The authentication request containing login credentials.
     * @return An {@link AuthenticationResponse} containing the user's email, role, and JWT token.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .email(request.getEmail())
                .role(String.valueOf(user.getRole()))
                .token(jwtToken)
                .build();
    }

    /**
     * Retrieves a list of all users in the system.
     *
     * @return A list of {@link UserDTO} representing all users.
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .toList();
    }
}
