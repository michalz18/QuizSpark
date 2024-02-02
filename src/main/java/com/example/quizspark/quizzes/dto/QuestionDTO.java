package com.example.quizspark.quizzes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class QuestionDTO {
    private UUID id;
    private String questionContent;
    private List<AnswerDTO> answers;
}
