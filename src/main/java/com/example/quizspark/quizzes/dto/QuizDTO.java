package com.example.quizspark.quizzes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for quiz details.
 */
@Getter
@Setter
public class QuizDTO {
    /**
     * Unique identifier for the quiz.
     */
    private UUID id;

    /**
     * Title of the quiz.
     */
    private String title;

    /**
     * List of questions associated with this quiz.
     */
    private List<QuestionDTO> questions;
}
