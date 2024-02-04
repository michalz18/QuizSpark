package com.example.quizspark.quizzes.mapper;

import com.example.quizspark.quizzes.dto.AnswerDTO;
import com.example.quizspark.quizzes.model.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct mapper interface for converting between {@link Answer} entities and {@link AnswerDTO} objects.
 * Utilizes MapStruct for code generation to minimize the boilerplate code for object mappings.
 */
@Mapper
public interface AnswerMapper {
    /**
     * The singleton instance of {@link AnswerMapper} for use in the application.
     * Obtained via MapStruct's {@link Mappers} factory class.
     */
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    /**
     * Converts an {@link Answer} entity to an {@link AnswerDTO}.
     *
     * @param answer The {@link Answer} entity to convert.
     * @return The corresponding {@link AnswerDTO}.
     */
    AnswerDTO answerToAnswerDTO(Answer answer);

    /**
     * Converts an {@link AnswerDTO} to an {@link Answer} entity.
     *
     * @param answerDTO The {@link AnswerDTO} to convert.
     * @return The corresponding {@link Answer} entity.
     */
    Answer answerDTOToAnswer(AnswerDTO answerDTO);
}
