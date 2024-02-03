package com.example.quizspark.admin.dto;

import com.example.quizspark.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}

