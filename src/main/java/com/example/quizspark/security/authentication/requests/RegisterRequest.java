package com.example.quizspark.security.authentication.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulates the data required for registering a new user in the system.
 * Includes personal information, credentials, and the desired role for the new account.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /**
     * The first name of the user attempting to register.
     */
    private String firstname;

    /**
     * The last name of the user attempting to register.
     */
    private String lastname;

    /**
     * The email address of the user, which will be used as the username for the account.
     */
    private String email;

    /**
     * The password chosen by the user for the new account.
     */
    private String password;
}
