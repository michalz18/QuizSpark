// Assuming the file path: /src/components/QuizTiles.js

import React, { useState } from 'react';
import { Grid, Card, CardContent, Typography } from '@mui/material';

const QuizTiles = () => {
    const [quizzes] = useState([
        { id: '1', title: 'Introduction to React' },
        { id: '2', title: 'Advanced React Patterns' },
        { id: '3', title: 'State Management' },
        { id: '4', title: 'React Performance Optimization' }
    ]);

    return (
        <Grid container spacing={2}>
            {quizzes.map((quiz) => (
                <Grid item xs={12} sm={6} md={4} key={quiz.id}>
                    <Card>
                        <CardContent>
                            <Typography variant="h5" component="div">
                                {quiz.title}
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
            ))}
        </Grid>
    );
};

export default QuizTiles;
