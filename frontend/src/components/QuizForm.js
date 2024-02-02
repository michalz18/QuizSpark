import React, { useState } from 'react';
import { Box, TextField, Button, Pagination } from '@mui/material';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import QuestionCard from './QuestionCard';

const QuizForm = () => {
    const [quiz, setQuiz] = useState({
        title: '',
        questions: [{ questionContent: '', answers: [] }]
    });
    const [currentPage, setCurrentPage] = useState(1);

    const handlePageChange = (event, value) => {
        setCurrentPage(value);
    };

    const handleQuestionChange = (event, index) => {
        const newQuestions = [...quiz.questions];
        newQuestions[index].questionContent = event.target.value;
        setQuiz({ ...quiz, questions: newQuestions });
    };

    const handleAnswerChange = (event, qIndex, aIndex) => {
        const newQuestions = [...quiz.questions];
        const newAnswers = newQuestions[qIndex].answers.map((answer, index) => {
            return {
                ...answer,
                isCorrect: index === aIndex ? event.target.checked : false
            };
        });

        newQuestions[qIndex].answers = newAnswers;
        setQuiz({ ...quiz, questions: newQuestions });
    };

    const handleAddQuestion = () => {
        setQuiz({
            ...quiz,
            questions: [...quiz.questions, { questionContent: '', answers: [] }]
        });
        setCurrentPage(quiz.questions.length + 1);
    };

    const handleDeleteQuestion = (index) => {
        if (quiz.questions.length > 1) {
            const newQuestions = [...quiz.questions];
            newQuestions.splice(index, 1);
            setQuiz({ ...quiz, questions: newQuestions });
            if (currentPage > newQuestions.length) {
                setCurrentPage(newQuestions.length);
            }
        }
    };

    const handleAddAnswer = (qIndex) => {
        const newQuestions = [...quiz.questions];
        newQuestions[qIndex].answers.push({ answerContent: '', isCorrect: false });
        setQuiz({ ...quiz, questions: newQuestions });
    };

    const handleDeleteAnswer = (qIndex, aIndex) => {
        const newQuestions = [...quiz.questions];
        newQuestions[qIndex].answers.splice(aIndex, 1);
        setQuiz({ ...quiz, questions: newQuestions });
    };

    return (
        <Box sx={{ my: 4 }}>
            <TextField
                fullWidth
                label="Quiz Title"
                value={quiz.title}
                onChange={(e) => setQuiz({ ...quiz, title: e.target.value })}
                margin="normal"
            />
            <QuestionCard
                question={quiz.questions[currentPage - 1]}
                index={currentPage - 1}
                onChange={handleQuestionChange}
                onDelete={handleDeleteQuestion}
                onAnswerChange={handleAnswerChange}
                onAddAnswer={handleAddAnswer}
                onDeleteAnswer={handleDeleteAnswer}
                canDelete={quiz.questions.length > 1}
            />
            <Button startIcon={<AddCircleOutlineIcon />} onClick={handleAddQuestion}>
                Add Question
            </Button>
            {quiz.questions.length > 1 && (
                <Pagination
                    count={quiz.questions.length}
                    page={currentPage}
                    onChange={handlePageChange}
                    color="primary"
                    hidePrevButton
                    hideNextButton
                    sx={{ mt: 2, justifyContent: 'center', display: 'flex' }}
                />
            )}
        </Box>
    );
};

export default QuizForm;
