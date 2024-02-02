package com.example.quizspark.quizzes.service;

import com.example.quizspark.quizzes.dto.AnswerDTO;
import com.example.quizspark.quizzes.dto.ResultDTO;
import com.example.quizspark.quizzes.model.Answer;
import com.example.quizspark.quizzes.model.Question;
import com.example.quizspark.quizzes.model.Quiz;
import com.example.quizspark.quizzes.model.Result;
import com.example.quizspark.quizzes.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    @Transactional
    public Quiz saveQuiz(Quiz quiz) {
        quiz.getQuestions().forEach(question -> {
            question.setQuiz(quiz);
            question.getAnswers().forEach(answer -> answer.setQuestion(question));
        });

        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(UUID id) {
        return quizRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found for id: " + id));
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public ResultDTO solveQuiz(UUID quizId, List<AnswerDTO> providedAnswers) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        int correctAnswersCount = 0;

        for (AnswerDTO providedAnswer : providedAnswers) {
            Question question = quiz.getQuestions().stream()
                    .filter(q -> q.getAnswers().stream()
                            .anyMatch(a -> a.getId().equals(providedAnswer.getId())))
                    .findFirst()
                    .orElse(null);

            if (question != null) {
                boolean isCorrect = question.getAnswers().stream()
                        .filter(a -> a.getId().equals(providedAnswer.getId()))
                        .findFirst()
                        .map(Answer::getIsCorrect)
                        .orElse(false);

                if (isCorrect) {
                    correctAnswersCount++;
                }
            }
        }

        double score = ((double) correctAnswersCount / quiz.getQuestions().size()) * 100;

        return ResultDTO.builder()
               .totalQuestions(quiz.getQuestions().size())
               .correctAnswers(correctAnswersCount)
               .score(score)
               .build();
    }
}
