package com.example.quizspark.quizzes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

/**
 * Represents an answer to a quiz question, indicating whether the answer is correct.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The question this answer is associated with.
     * Back reference to support the relationship without causing serialization issues.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;

    @NotBlank(message = "Content must not be blank")
    private String answerContent;

    @NotNull
    private Boolean isCorrect;
}
