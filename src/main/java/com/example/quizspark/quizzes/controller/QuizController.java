package com.example.quizspark.quizzes.controller;

import com.example.quizspark.quizzes.dto.AnswerDTO;
import com.example.quizspark.quizzes.dto.QuizDTO;
import com.example.quizspark.quizzes.dto.ResultDTO;
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

/**
 * Controller for managing quizzes within the QuizSpark application.
 * Supports operations such as creating a new quiz, retrieving quizzes, and solving them.
 */
@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;

    /**
     * Creates a new quiz based on the provided quiz data.
     *
     * @param quizDTO Data Transfer Object containing quiz information.
     * @return ResponseEntity containing the created QuizDTO.
     */
    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@Validated @RequestBody QuizDTO quizDTO) {
        logger.info("Creating new quiz: {}", quizDTO);
        Quiz quiz = QuizMapper.INSTANCE.quizDTOToQuiz(quizDTO);
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        QuizDTO savedQuizDTO = QuizMapper.INSTANCE.quizToQuizDTO(savedQuiz);

        return ResponseEntity.ok(savedQuizDTO);
    }

    /**
     * Retrieves a quiz by its unique identifier.
     *
     * @param id The UUID of the quiz to retrieve.
     * @return ResponseEntity containing the QuizDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable UUID id) {
        logger.info("Getting quiz by id: {}", id);
        Quiz quiz = quizService.getQuizById(id);
        QuizDTO quizDTO = QuizMapper.INSTANCE.quizToQuizDTO(quiz);

        return ResponseEntity.ok(quizDTO);
    }

    /**
     * Retrieves all quizzes available in the system.
     *
     * @return ResponseEntity with a list of all QuizDTOs.
     */
    @GetMapping
    public ResponseEntity<List<QuizDTO>> getQuizzes() {
        logger.info("Getting all quizzes");
        List<Quiz> quizzes = quizService.getAllQuizzes();
        List<QuizDTO> quizDTOs = quizzes.stream()
                .map(QuizMapper.INSTANCE::quizToQuizDTO)
                .toList();

        return ResponseEntity.ok(quizDTOs);
    }

    /**
     * Submits answers for a quiz and calculates the result.
     *
     * @param id The UUID of the quiz being solved.
     * @param answers A list of AnswerDTOs representing the user's answers.
     * @return ResponseEntity containing the ResultDTO with the quiz results.
     */
    @PostMapping("/{id}/solve")
    public ResponseEntity<ResultDTO> solveQuiz(@PathVariable UUID id, @RequestBody List<AnswerDTO> answers) {
        logger.info("Solving quiz with id: {}", id);
        ResultDTO result = quizService.solveQuiz(id, answers);
        return ResponseEntity.ok(result);
    }
}
