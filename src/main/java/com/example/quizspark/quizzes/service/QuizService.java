package com.example.quizspark.quizzes.service;

import com.example.quizspark.quizzes.model.Quiz;
import com.example.quizspark.quizzes.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class QuizService implements QuizServiceImpl {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuiz(UUID id) {
        return quizRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found for id: " + id));
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }
}
