package com.example.quizspark.quizzes.service;

import com.example.quizspark.quizzes.model.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizServiceImpl {

    Quiz saveQuiz(Quiz quiz);
    Quiz getQuiz(UUID id);
    List<Quiz> getQuizzes();
}
