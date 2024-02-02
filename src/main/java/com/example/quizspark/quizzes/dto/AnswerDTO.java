package com.example.quizspark.quizzes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerDTO {
    private UUID id;
    private String answerContent;
    private Boolean isCorrect;
}
