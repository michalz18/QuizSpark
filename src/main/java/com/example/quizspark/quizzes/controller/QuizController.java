package com.example.quizspark.quizzes.controller;

import com.example.quizspark.quizzes.model.Quiz;
import com.example.quizspark.quizzes.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/save")
    public ResponseEntity<Quiz> saveQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.saveQuiz(quiz));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable UUID id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }
}
