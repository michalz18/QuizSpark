package com.example.quizspark.quizzes.controller;

import com.example.quizspark.quizzes.dto.QuizDTO;
import com.example.quizspark.quizzes.mapper.QuizMapper;
import com.example.quizspark.quizzes.model.Quiz;
import com.example.quizspark.quizzes.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;
    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@Validated @RequestBody QuizDTO quizDTO) {
        logger.info("Creating new quiz: {}", quizDTO);
        Quiz quiz = QuizMapper.INSTANCE.quizDTOToQuiz(quizDTO);
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        QuizDTO savedQuizDTO = QuizMapper.INSTANCE.quizToQuizDTO(savedQuiz);

        return ResponseEntity.ok(savedQuizDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable UUID id) {
        logger.info("Getting quiz by id: {}", id);
        Quiz quiz = quizService.getQuizById(id);
        QuizDTO quizDTO = QuizMapper.INSTANCE.quizToQuizDTO(quiz);

        return ResponseEntity.ok(quizDTO);
    }
    @GetMapping
    public ResponseEntity<List<QuizDTO>> getQuizzes() {
        logger.info("Getting all quizzes");
        List<Quiz> quizzes = quizService.getAllQuizzes();
        List<QuizDTO> quizDTOs = quizzes.stream()
                .map(QuizMapper.INSTANCE::quizToQuizDTO)
                .toList();

        return ResponseEntity.ok(quizDTOs);
    }
}
