package com.example.quizspark.admin.controller;

import com.example.quizspark.admin.dto.UserDTO;
import com.example.quizspark.security.authentication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling administrative operations in the QuizSpark application.
 * This controller provides endpoints for admin functionalities, such as managing user accounts.
 */
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;

    /**
     * Retrieves all user accounts from the system.
     *
     * @return A {@link ResponseEntity} containing a list of {@link UserDTO} objects representing all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
