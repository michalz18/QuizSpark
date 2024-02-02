package com.example.quizspark.quizzes.controller;

import com.example.quizspark.quizzes.model.Quiz;
import com.example.quizspark.quizzes.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/save")
    public ResponseEntity<Quiz> saveQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.saveQuiz(quiz));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable UUID id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }
}
