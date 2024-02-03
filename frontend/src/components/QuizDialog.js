import React, {useEffect, useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    Button,
    RadioGroup,
    FormControlLabel,
    Radio,
    CircularProgress,
    LinearProgress,
    Typography,
    Box,
    Container, Alert
} from '@mui/material';
import useSWR from 'swr';
import { getQuizById, solveQuiz } from "../api/secured/quizService";
import Confetti from 'react-confetti';
import { useWindowSize }from 'react-use';

const QuizDialog = ({ quizId, open, handleClose }) => {
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [selectedAnswers, setSelectedAnswers] = useState([]);
    const [quizCompleted, setQuizCompleted] = useState(false);
    const [loading, setLoading] = useState(false);
    const [quizResult, setQuizResult] = useState(null);
    const [showConfetti, setShowConfetti] = useState(false);
    const { width, height } = useWindowSize();
    const { data: quizData, error } = useSWR(quizId, getQuizById);
    const currentQuestion = quizData?.questions[currentQuestionIndex];

    useEffect(() => {
        if (quizCompleted) {
            setShowConfetti(true);
            const timer = setTimeout(() => setShowConfetti(false), 5000);
            return () => clearTimeout(timer);
        }
    }, [quizCompleted]);

    if (!quizData && !error) {
        return (
            <Container maxWidth="lg" sx={{ mt: 4, display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <CircularProgress />
            </Container>
        );
    }

    if (error) {
        return (
            <Container maxWidth="lg" sx={{ mt: 4 }}>
                <Alert severity="error">An error occurred while loading the quizzes. Please try refreshing the page.</Alert>
            </Container>
        );
    }

    const handleAnswerSelect = (answerId, isCorrect) => {
        const newAnswer = { id: answerId, isCorrect };
        setSelectedAnswers(prev => [...prev, newAnswer]);
    };

    const goToNextQuestion = () => {
        if (currentQuestionIndex < quizData.questions.length - 1) {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
        } else {
            submitQuiz();
        }
    };

    const submitQuiz = async () => {
        setLoading(true);
        try {
            const result = await solveQuiz(quizId, selectedAnswers);
            setQuizResult(result);
            setQuizCompleted(true);
            setShowConfetti(true);
            setTimeout(() => setShowConfetti(false), 5000);
        } catch (error) {
            console.error('Failed to submit quiz:', error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
            {showConfetti && <Confetti width={width} height={height} />}
            <Dialog open={open} onClose={handleClose} fullWidth maxWidth="sm">
                <DialogTitle sx={{ textAlign: 'center' }}>{quizData.title}</DialogTitle>
                <DialogContent>
                    {loading ? (
                        <CircularProgress />
                    ) : quizCompleted ? (
                        <Box textAlign="center">
                            <Typography variant="h6" gutterBottom>Quiz Completed!</Typography>
                            {quizResult ? (
                                <>
                                    <Typography>Total Questions: {quizResult.totalQuestions}</Typography>
                                    <Typography>Correct Answers: {quizResult.correctAnswers}</Typography>
                                    <Typography>Score: {quizResult.score.toFixed(2)}</Typography>
                                </>
                            ) : <Typography>There was an issue retrieving the results.</Typography>}
                            <Button variant="contained" color="primary" onClick={handleClose} sx={{ mt: 2 }}>Close Quiz</Button>
                        </Box>
                    ) : (
                        <Box>
                            <LinearProgress variant="determinate" value={(currentQuestionIndex + 1) / quizData.questions.length * 100} sx={{ mb: 2 }} />
                            <Typography variant="h6" sx={{ mb: 2 }}>{currentQuestion.questionContent}</Typography>
                            <RadioGroup onChange={(e) => handleAnswerSelect(e.target.value, true)}>
                                {currentQuestion.answers.map((answer) => (
                                    <FormControlLabel key={answer.id} value={answer.id} control={<Radio />} label={answer.answerContent} />
                                ))}
                            </RadioGroup>
                            <Box display="flex" justifyContent="flex-end">
                                <Button variant="contained" color="secondary" onClick={goToNextQuestion}>Next Question</Button>
                            </Box>
                        </Box>
                    )}
                </DialogContent>
            </Dialog>
        </>
    );
}

export default QuizDialog;
