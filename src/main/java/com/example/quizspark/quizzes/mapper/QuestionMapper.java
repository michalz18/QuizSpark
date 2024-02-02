package com.example.quizspark.quizzes.mapper;

import com.example.quizspark.quizzes.dto.QuestionDTO;
import com.example.quizspark.quizzes.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AnswerMapper.class)
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mappings({
            @Mapping(target = "answers", source = "answers")
    })
    QuestionDTO questionToQuestionDTO(Question question);
    Question questionDTOToQuestion(QuestionDTO questionDTO);
}
