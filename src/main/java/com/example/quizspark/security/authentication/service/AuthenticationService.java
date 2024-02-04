package com.example.quizspark.security.authentication.service;

import com.example.quizspark.admin.dto.UserDTO;
import com.example.quizspark.admin.mapper.UserMapper;
import com.example.quizspark.assets.UserNotFoundException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .email(request.getEmail())
                .role(String.valueOf(user.getRole()))
                .token(jwtToken)
                .build();
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .toList();
    }
}
