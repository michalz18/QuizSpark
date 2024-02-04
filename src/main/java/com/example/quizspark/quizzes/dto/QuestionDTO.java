package com.example.quizspark.quizzes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for question details within a quiz.
 */
@Getter
@Setter
public class QuestionDTO {
    /**
     * Unique identifier for the question.
     */
    private UUID id;

    /**
     * Content of the question.
     */
    private String questionContent;

    /**
     * List of possible answers for this question.
     */
    private List<AnswerDTO> answers;
}
