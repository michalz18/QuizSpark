package com.example.quizspark.quizzes.service;

import com.example.quizspark.quizzes.model.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizService {
    Quiz saveQuiz(Quiz quiz);
    Quiz getQuizById(UUID id);
    List<Quiz> getAllQuizzes();
}
