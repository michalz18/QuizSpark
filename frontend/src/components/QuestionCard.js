import React from 'react';
import { Box, Card, CardContent, TextField, IconButton, Button, Checkbox, Typography, Grid, CardActions } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

const QuestionCard = ({ question, index, onChange, onDelete, onAnswerChange, onAddAnswer, onDeleteAnswer, canDelete }) => (
    <Card variant="outlined" sx={{ mb: 2 }}>
        <CardContent>
            <Grid container spacing={2} alignItems="center">
                <Grid item xs={12} md={8}>
                    <TextField
                        fullWidth
                        label={`Question ${index + 1}`}
                        value={question.questionContent}
                        onChange={e => onChange(e, index)}
                        margin="normal"
                    />
                </Grid>
            </Grid>
            {question.answers.map((answer, aIndex) => (
                <Box key={aIndex} display="flex" alignItems="center" mt={1}>
                    <TextField
                        fullWidth
                        label={`Answer ${aIndex + 1}`}
                        value={answer.answerContent}
                        onChange={e => onAnswerChange(e, index, aIndex)}
                        margin="normal"
                        sx={{ flexGrow: 1 }}
                    />
                    <Checkbox
                        checked={answer.isCorrect}
                        onChange={e => onAnswerChange(e, index, aIndex, 'isCorrect')}
                        color="primary"
                        sx={{ mx: 2 }}
                    />
                    <IconButton color="primary" onClick={() => onDeleteAnswer(index, aIndex)}>
                        <DeleteIcon />
                    </IconButton>
                </Box>
            ))}
            <Button startIcon={<AddCircleOutlineIcon />} onClick={() => onAddAnswer(index)}>
                Add Answer
            </Button>
        </CardContent>
        {canDelete && (
            <CardActions sx={{ justifyContent: 'center' }}> {/* Wyśrodkowanie przycisku w CardActions */}
                <Button
                    startIcon={<DeleteIcon />}
                    onClick={() => onDelete(index)}
                    color="error"
                    sx={{ maxWidth: 'fit-content' }} // Ustawienie maksymalnej szerokości przycisku
                >
                    Remove Question
                </Button>
            </CardActions>
        )}
    </Card>
);

export default QuestionCard;
