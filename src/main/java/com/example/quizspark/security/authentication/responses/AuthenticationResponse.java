package com.example.quizspark.security.authentication.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response sent back to the client upon successful authentication.
 * This response includes the user's email, assigned role, and a JWT token for session management.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    /**
     * The email of the authenticated user.
     */
    private String email;

    /**
     * The role of the authenticated user, indicating their level of access and permissions.
     */
    private String role;

    /**
     * The JWT token generated for the authenticated session, used for authorizing subsequent requests.
     */
    private String token;
}
