package com.example.quizspark.quizzes.mapper;

import com.example.quizspark.quizzes.dto.QuizDTO;
import com.example.quizspark.quizzes.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    QuizDTO quizToQuizDTO(Quiz quiz);

    Quiz quizDTOToQuiz(QuizDTO quizDTO);
}
