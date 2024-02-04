package com.example.quizspark.admin.dto;

import com.example.quizspark.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing user information in the QuizSpark application.
 * This DTO is used for transferring user data between the server and client, particularly in admin operations.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /**
     * Unique identifier for the user.
     */
    private UUID id;

    /**
     * First name of the user.
     */
    private String firstname;

    /**
     * Last name of the user.
     */
    private String lastname;

    /**
     * Email address of the user. This can also serve as the username for authentication purposes.
     */
    private String email;

    /**
     * Role of the user, indicating their level of access and permissions within the application.
     */
    private Role role;
}
