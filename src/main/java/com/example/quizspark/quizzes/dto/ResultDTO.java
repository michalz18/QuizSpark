package com.example.quizspark.quizzes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object representing the result of a quiz attempt.
 */
@Getter
@Setter
@Builder
public class ResultDTO {
    /**
     * The total number of questions in the quiz.
     */
    private int totalQuestions;

    /**
     * The number of correctly answered questions.
     */
    private int correctAnswers;

    /**
     * The calculated score as a percentage of correct answers.
     */
    private double score;
}
