package com.example.quizspark.quizzes.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Result {
    private final int totalQuestions;
    private final int correctAnswers;
    private final double score;
}

