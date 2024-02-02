package com.example.quizspark.quizzes.repository;

import com.example.quizspark.quizzes.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
}
