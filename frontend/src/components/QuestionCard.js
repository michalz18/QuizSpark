import React from 'react';
import { Box, Card, CardContent, TextField, IconButton, Button, Checkbox, FormControlLabel, CardActions } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

const QuestionCard = ({ question, index, onChange, onDelete, onAnswerChange, onAddAnswer, onDeleteAnswer }) => (
    <Card variant="outlined" sx={{ mb: 2 }}>
        <CardContent>
            <TextField
                fullWidth
                label={`Question ${index + 1}`}
                value={question.questionContent}
                onChange={e => onChange(e, index)}
                margin="normal"
            />
            {question.answers.map((answer, aIndex) => (
                <Box key={aIndex} display="flex" alignItems="center" mt={1}>
                    <TextField
                        fullWidth
                        label={`Answer ${aIndex + 1}`}
                        value={answer.answerContent}
                        onChange={e => onAnswerChange(e, index, aIndex)}
                        margin="normal"
                    />
                    <FormControlLabel
                        control={
                            <Checkbox
                                checked={answer.isCorrect}
                                onChange={e => onAnswerChange(e, index, aIndex, 'isCorrect')}
                                color="primary"
                            />
                        }
                        label="Correct"
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
        <CardActions>
            <IconButton color="secondary" onClick={() => onDelete(index)}>
                <DeleteIcon />
            </IconButton>
        </CardActions>
    </Card>
);

export default QuestionCard;
