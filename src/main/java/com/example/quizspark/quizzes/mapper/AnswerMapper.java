package com.example.quizspark.quizzes.mapper;

import com.example.quizspark.quizzes.dto.AnswerDTO;
import com.example.quizspark.quizzes.model.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    AnswerDTO answerToAnswerDTO(Answer answer);
    Answer answerDTOToAnswer(AnswerDTO answerDTO);
}
