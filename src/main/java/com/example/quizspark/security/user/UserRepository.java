package com.example.quizspark.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

/**
 * The {@code UserRepository} interface extends the {@link JpaRepository} to provide CRUD operations for {@link User} entities.
 * It includes custom methods for user-specific queries, such as finding a user by their email.
 *
 * <p>This repository interface is part of the security package, primarily focused on user management within the QuizSpark application.
 * It leverages Spring Data JPA to abstract the complexity of direct database access and query construction.</p>
 *
 * @see JpaRepository
 * @see User
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieves an {@link Optional} instance of a {@link User} based on the provided email.
     * This method is used to find a user entity where the email field matches the given email.
     *
     * <p>If a user with the specified email is found, an {@link Optional} containing the user is returned.
     * Otherwise, an empty {@link Optional} is returned, indicating that no user with the provided email exists in the database.</p>
     *
     * <p>This method supports the authentication process by enabling the lookup of users by their email,
     * which is assumed to be unique across all users in the system.</p>
     *
     * @param email the email address to search for in the database.
     * @return an {@link Optional} containing the found {@link User} if available, or an empty {@link Optional} if not.
     */
    Optional<User> findByEmail(String email);
}
