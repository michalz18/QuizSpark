import React, { useState } from 'react';
import useSWR from 'swr';
import { Container, Grid, Card, CardContent, Typography, CardActionArea, CircularProgress, Alert, Button } from '@mui/material';
import QuizDialog from './QuizDialog';
import { getAllQuizzes } from "../api/secured/quizService";

const QuizTiles = () => {
    const [selectedQuiz, setSelectedQuiz] = useState(null);
    const [quizDialogOpen, setQuizDialogOpen] = useState(false);

    const { data: quizzes, error } = useSWR('quizzes', getAllQuizzes);

    if (!quizzes && !error) {
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

    if (quizzes && quizzes.length === 0) {
        return (
            <Container maxWidth="lg" sx={{ mt: 4, textAlign: 'center' }}>
                <Typography sx={{ mb: 2 }}>No quizzes found. Would you like to create the first one?</Typography>
            </Container>
        );
    }

    return (
        <Container maxWidth="lg" sx={{ mt: 4 }}>
            <Grid container spacing={2} justifyContent="center">
                {quizzes.map((quiz) => (
                    <Grid item xs={12} sm={6} md={4} lg={3} key={quiz.id}>
                        <Card>
                            <CardActionArea onClick={(quiz) => {
                                setSelectedQuiz(quiz);
                                setQuizDialogOpen(true);
                            }}>
                                <CardContent>
                                    <Typography variant="h6" component="div" sx={{ textAlign: 'center' }}>
                                        {quiz.title}
                                    </Typography>
                                </CardContent>
                            </CardActionArea>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            {selectedQuiz && (
                <QuizDialog
                    quiz={selectedQuiz}
                    open={quizDialogOpen}
                    handleClose={() => {
                        setQuizDialogOpen(false);
                        setSelectedQuiz(null);
                    }}
                />
            )}
        </Container>
    );
};

export default QuizTiles;
