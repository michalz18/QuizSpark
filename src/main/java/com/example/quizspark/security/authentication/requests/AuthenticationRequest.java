package com.example.quizspark.security.authentication.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Encapsulates the request data for authentication, containing the user's email and password.
 * This class is used to transport the necessary credentials for logging in to the system.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * The email of the user attempting to authenticate. This serves as the username in the authentication process.
     */
    private String email;

    /**
     * The password of the user. It is used in conjunction with the email to authenticate the user.
     */
    private String password;
}
