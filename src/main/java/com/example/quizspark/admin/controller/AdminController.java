package com.example.quizspark.admin.controller;

import com.example.quizspark.admin.dto.UserDTO;
import com.example.quizspark.security.authentication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private AuthenticationService authenticationService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}

