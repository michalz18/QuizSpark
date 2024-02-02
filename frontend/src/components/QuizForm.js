import React, { useState } from 'react';
import {Box, TextField, Button, Pagination, Grid} from '@mui/material';
import QuestionCard from './QuestionCard';
import SaveIcon from '@mui/icons-material/Save';
import AddBoxIcon from '@mui/icons-material/AddBox';
import { createQuiz } from '../api/secured/quizService';
import ConfirmationDialog from './ConfirmationDialog';

const QuizForm = () => {
    const [quiz, setQuiz] = useState({
        title: '',
        questions: [{ questionContent: '', answers: [] }]
    });
    const [currentPage, setCurrentPage] = useState(1);
    const [confirmOpen, setConfirmOpen] = useState(false);

    const submitQuiz = async () => {
        if (!validateQuiz()) return;
        try {
            await createQuiz(quiz);
            setQuiz({ title: '', questions: [{ questionContent: '', answers: [] }] });
            setCurrentPage(1);
            setConfirmOpen(false);
            alert('Quiz saved successfully!');
        } catch (error) {
            console.error('Error during saving the quiz', error);
            alert('Error saving quiz. Please try again.');
        }
    };

    const validateQuiz = () => {
        if (!quiz.title) {
            alert('Please enter a quiz title.');
            return false;
        }
        for (let i = 0; i < quiz.questions.length; i++) {
            if (!quiz.questions[i].questionContent) {
                alert(`Please enter a content for question ${i + 1}.`);
                return false;
            }
            if (quiz.questions[i].answers.length < 2) {
                alert(`Please add at least 2 answers for question ${i + 1}.`);
                return false;
            }
            let correctAnswers = 0;
            for (let j = 0; j < quiz.questions[i].answers.length; j++) {
                if (!quiz.questions[i].answers[j].answerContent) {
                    alert(`Please enter an answer content for question ${i + 1}, answer ${j + 1}.`);
                    return false;
                }
                if (quiz.questions[i].answers[j].isCorrect) {
                    correctAnswers++;
                }
            }
            if (correctAnswers !== 1) {
                alert(`Please ensure that only one answer is marked as correct for question ${i + 1}.`);
                return false;
            }
        }
        return true;
    };
    const handlePageChange = (event, value) => {
        setCurrentPage(value);
    };

    const handleQuestionChange = (event, index) => {
        const newQuestions = [...quiz.questions];
        newQuestions[index].questionContent = event.target.value;
        setQuiz({ ...quiz, questions: newQuestions });
    };

    const handleAnswerChange = (event, qIndex, aIndex, field = 'answerContent', newValue = '') => {
        const newQuestions = [...quiz.questions];
        const newAnswers = [...newQuestions[qIndex].answers];
        newAnswers[aIndex] = { ...newAnswers[aIndex], [field]: field === 'isCorrect' ? event.target.checked : event.target.value };
        newQuestions[qIndex].answers = newAnswers;

        setQuiz({ ...quiz, questions: newQuestions });
    };
    const handleAddQuestion = () => {
        if (!validateQuiz()) return;
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
        if (quiz.questions[qIndex].answers.length >= 4) {
            alert('You can have a maximum of 4 answers per question.');
            return;
        }
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
            <Grid container justifyContent="center">
                <Grid item xs={12} sm={8} md={6}>
                    <TextField
                        fullWidth
                        label="Quiz Title"
                        value={quiz.title}
                        onChange={(e) => setQuiz({ ...quiz, title: e.target.value })}
                        margin="normal"
                        sx={{
                            mb: 4,
                        }}
                    />
                </Grid>
            </Grid>
            <Box sx={{ my: 4, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Grid container justifyContent="center" alignItems="center">
                    <Grid item xs={12} sm={8} md={6}>
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
                    </Grid>
                </Grid>
            </Box>
            {quiz.questions.length > 1 && (
                <Pagination
                    count={quiz.questions.length}
                    page={currentPage}
                    onChange={handlePageChange}
                    color="primary"
                    hidePrevButton
                    hideNextButton
                    sx={{ mt: 4, justifyContent: 'center', display: 'flex' }}
                />
            )}
            <Box sx={{ display: 'flex', justifyContent: 'center', gap: 2, mt: 2 }}>
                <Button
                    startIcon={<AddBoxIcon />}
                    onClick={handleAddQuestion}
                    variant="contained"
                    color="secondary"
                >
                    Add Question
                </Button>
                <Button
                    startIcon={<SaveIcon />}
                    onClick={() => setConfirmOpen(true)}
                    variant="contained"
                    color="primary"
                >
                    Submit Quiz
                </Button>
            </Box>
            <ConfirmationDialog
                open={confirmOpen}
                onCancel={() => setConfirmOpen(false)}
                onConfirm={submitQuiz}
                title="Are you sure you want to submit the quiz?"
                content="Please confirm before submitting the quiz."
            />
        </Box>
    );
};

export default QuizForm;
