package com.example.quizspark.quizzes.service;

import com.example.quizspark.quizzes.dto.AnswerDTO;
import com.example.quizspark.quizzes.dto.ResultDTO;
import com.example.quizspark.quizzes.model.Answer;
import com.example.quizspark.quizzes.model.Question;
import com.example.quizspark.quizzes.model.Quiz;
import com.example.quizspark.quizzes.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link QuizService} for managing quiz operations.
 * Handles CRUD operations for quizzes, solving quizzes, and calculating results.
 */
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    /**
     * Saves a new quiz or updates an existing quiz, ensuring relationships between quiz, questions, and answers are correctly persisted.
     *
     * @param quiz The quiz entity to be saved or updated.
     * @return The saved or updated quiz entity.
     */
    @Override
    @Transactional
    public Quiz saveQuiz(Quiz quiz) {
        // Ensure correct mapping of bidirectional relationships
        quiz.getQuestions().forEach(question -> {
            question.setQuiz(quiz);
            question.getAnswers().forEach(answer -> answer.setQuestion(question));
        });

        return quizRepository.save(quiz);
    }

    /**
     * Retrieves a quiz by its unique identifier.
     *
     * @param id The UUID of the quiz to retrieve.
     * @return The found quiz entity.
     * @throws ResponseStatusException If the quiz is not found.
     */
    @Override
    public Quiz getQuizById(UUID id) {
        return quizRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found for id: " + id));
    }

    /**
     * Fetches all quizzes available in the repository.
     *
     * @return A list of all quiz entities.
     */
    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    /**
     * Evaluates the provided answers against the correct answers for a quiz and calculates the result.
     *
     * @param quizId The UUID of the quiz to solve.
     * @param providedAnswers A list of answers provided by the user.
     * @return A {@link ResultDTO} representing the outcome of the quiz attempt.
     */
    @Override
    public ResultDTO solveQuiz(UUID quizId, List<AnswerDTO> providedAnswers) {
        Quiz quiz = getQuizById(quizId); // Utilizes the method within this class to handle possible exceptions.
        return calculateQuizResult(quiz, providedAnswers);
    }

    /**
     * Checks if the provided answer ID matches the correct answer for the question.
     *
     * @param question The question to check the answer against.
     * @param answerId The UUID of the provided answer.
     * @return true if the answer is correct; false otherwise.
     */
    private boolean isAnswerCorrect(Question question, UUID answerId) {
        return question.getAnswers().stream()
                .filter(a -> a.getId().equals(answerId))
                .findFirst()
                .map(Answer::getIsCorrect)
                .orElse(false);
    }

    /**
     * Calculates the quiz results based on the provided answers.
     *
     * @param quiz The quiz to calculate results for.
     * @param providedAnswers The answers provided by the user.
     * @return A {@link ResultDTO} with the total questions, correct answers count, and score.
     */
    private ResultDTO calculateQuizResult(Quiz quiz, List<AnswerDTO> providedAnswers) {
        int correctAnswersCount = 0;
        for (AnswerDTO providedAnswer : providedAnswers) {
            Question question = findQuestionForAnswer(quiz, providedAnswer.getId());
            if (question != null && isAnswerCorrect(question, providedAnswer.getId())) {
                correctAnswersCount++;
            }
        }
        double score = ((double) correctAnswersCount / quiz.getQuestions().size()) * 100;

        return ResultDTO.builder()
                .totalQuestions(quiz.getQuestions().size())
                .correctAnswers(correctAnswersCount)
                .score(score)
                .build();
    }

    /**
     * Finds the question that corresponds to the provided answer ID.
     *
     * @param quiz The quiz containing the question.
     * @param answerId The UUID of the answer to find the corresponding question for.
     * @return The question if found; null otherwise.
     */
    private Question findQuestionForAnswer(Quiz quiz, UUID answerId) {
        for (Question question : quiz.getQuestions()) {
            boolean answerExists = question.getAnswers().stream()
                    .anyMatch(a -> a.getId().equals(answerId));
            if (answerExists) {
                return question;
            }
        }
        return null;
    }
}
