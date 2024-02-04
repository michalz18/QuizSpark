package com.example.quizspark.quizzes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object for answer details within a quiz question.
 */
@Getter
@Setter
public class AnswerDTO {
    /**
     * Unique identifier for the answer.
     */
    private UUID id;

    /**
     * Content of the answer.
     */
    private String answerContent;

    /**
     * Indicates whether this answer is correct.
     */
    private Boolean isCorrect;
}
