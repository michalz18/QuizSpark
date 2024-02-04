package com.example.quizspark.quizzes.service;

import com.example.quizspark.quizzes.dto.AnswerDTO;
import com.example.quizspark.quizzes.dto.ResultDTO;
import com.example.quizspark.quizzes.model.Quiz;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing quizzes in the QuizSpark application.
 * Defines operations for creating, retrieving, and solving quizzes.
 */
public interface QuizService {

    /**
     * Saves a new quiz or updates an existing quiz in the repository.
     *
     * @param quiz The quiz to save or update.
     * @return The saved or updated quiz.
     */
    Quiz saveQuiz(Quiz quiz);

    /**
     * Retrieves a quiz by its unique identifier.
     *
     * @param id The unique identifier of the quiz.
     * @return The requested quiz if found.
     */
    Quiz getQuizById(UUID id);

    /**
     * Retrieves all quizzes available in the repository.
     *
     * @return A list of all quizzes.
     */
    List<Quiz> getAllQuizzes();

    /**
     * Evaluates the provided answers for a specific quiz and calculates the result.
     *
     * @param quizId The unique identifier of the quiz to solve.
     * @param providedAnswers A list of answers provided by the user.
     * @return A {@link ResultDTO} object containing the results of the quiz attempt.
     */
    ResultDTO solveQuiz(UUID quizId, List<AnswerDTO> providedAnswers);
}
