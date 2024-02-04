package com.example.quizspark.quizzes.repository;

import com.example.quizspark.quizzes.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for {@link Quiz} entities. Extends {@link JpaRepository} to provide
 * standard CRUD operations on the Quiz entity within the QuizSpark application.
 *
 * Utilizes Spring Data JPA's repository abstraction to simplify data access to the underlying database.
 */
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    // Additional query methods can be defined here if needed, utilizing Spring Data JPA's method query derivation.
}
