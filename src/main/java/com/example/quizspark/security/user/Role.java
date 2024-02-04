package com.example.quizspark.security.user;

/**
 * Defines the roles available in the QuizSpark application.
 * This enumeration is used to specify the authority level of a {@link User} within the system.
 *
 * <p>The roles are used to determine access rights across the application, with different roles
 * allowing for different levels of access and functionality. Currently, two roles are defined:</p>
 *
 * <ul>
 *     <li>{@code USER} - Represents a standard user with basic access rights.</li>
 *     <li>{@code ADMIN} - Represents an administrative user with elevated access rights,
 *     capable of performing administrative tasks such as managing user accounts.</li>
 * </ul>
 */
public enum Role {
    /**
     * Standard user role, granting basic access and functionalities within the application.
     */
    USER,

    /**
     * Administrative role, granting full access to all features and administrative functionalities.
     */
    ADMIN
}
