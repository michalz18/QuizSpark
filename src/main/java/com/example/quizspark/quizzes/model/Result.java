package com.example.quizspark.quizzes.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int totalQuestions;
    private int correctAnswers;
    private double score;
}

