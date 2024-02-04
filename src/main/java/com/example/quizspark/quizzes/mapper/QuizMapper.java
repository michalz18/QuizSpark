package com.example.quizspark.quizzes.mapper;

import com.example.quizspark.quizzes.dto.QuizDTO;
import com.example.quizspark.quizzes.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct mapper interface for converting between {@link Quiz} entities and {@link QuizDTO} objects.
 * Enables automatic generation of mapping code to reduce boilerplate code associated with object conversions.
 */
@Mapper
public interface QuizMapper {
    /**
     * Singleton instance of {@link QuizMapper}, accessible through MapStruct's {@link Mappers} factory.
     */
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    /**
     * Maps a {@link Quiz} entity to a {@link QuizDTO}.
     *
     * @param quiz The {@link Quiz} entity to be mapped.
     * @return The corresponding {@link QuizDTO}.
     */
    QuizDTO quizToQuizDTO(Quiz quiz);

    /**
     * Maps a {@link QuizDTO} back to a {@link Quiz} entity.
     *
     * @param quizDTO The {@link QuizDTO} to be converted.
     * @return The resulting {@link Quiz} entity.
     */
    Quiz quizDTOToQuiz(QuizDTO quizDTO);
}
