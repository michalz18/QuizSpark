package com.example.quizspark.quizzes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultDTO {
    private int totalQuestions;
    private int correctAnswers;
    private double score;
}
