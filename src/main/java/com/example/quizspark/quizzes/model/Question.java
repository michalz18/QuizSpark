package com.example.quizspark.quizzes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a question within a quiz, containing the question's content, its associated quiz, and possible answers.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Content must not be blank")
    private String questionContent;

    /**
     * The quiz this question is part of.
     * Back reference to avoid circular serialization issues.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @JsonBackReference
    private Quiz quiz;

    /**
     * A list of answers for this question.
     * Managed reference to maintain consistency and avoid circular issues.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();
}
