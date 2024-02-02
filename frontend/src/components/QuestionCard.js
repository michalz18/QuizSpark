import React from 'react';
import {
    Card,
    CardContent,
    TextField,
    IconButton,
    Button,
    Checkbox,
    Grid,
    CardActions,
    Tooltip,
    Divider
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';


const QuestionCard = ({ question, index, onChange, onDelete, onAnswerChange, onAddAnswer, onDeleteAnswer, canDelete }) => (
    <Card variant="outlined" sx={{ mb: 4, boxShadow: 3}}>
        <CardContent>
            <Grid container spacing={2} alignItems="center">
                <Grid item xs={12} md={8}>
                    <TextField
                        fullWidth
                        label={`Question ${index + 1}`}
                        value={question.questionContent}
                        onChange={e => onChange(e, index)}
                        margin="normal"
                        required
                    />
                </Grid>
            </Grid>
            <Divider sx={{ my: 2 }} />
            {question.answers.map((answer, aIndex) => (
                <Grid container spacing={2} alignItems="center" key={aIndex}>
                    <Grid item xs={12} sm={6} md={8}>
                        <TextField
                            fullWidth
                            label={`Answer ${aIndex + 1}`}
                            value={answer.answerContent}
                            onChange={e => onAnswerChange(e, index, aIndex)}
                            margin="normal"
                        />
                    </Grid>
                    <Grid item xs={2}>
                        <Tooltip title="Select as correct answer" placement="top">
                            <Checkbox
                                checked={answer.isCorrect}
                                onChange={e => onAnswerChange(e, index, aIndex, 'isCorrect')}
                                color="primary"
                            />
                        </Tooltip>
                    </Grid>
                    <Grid item xs={2}>
                        <Tooltip title="Remove an answer" placement="top">
                        <IconButton color="primary" onClick={() => onDeleteAnswer(index, aIndex)}>
                            <DeleteIcon />
                        </IconButton>
                        </Tooltip>
                    </Grid>
                </Grid>
            ))}
            <Button startIcon={<AddCircleOutlineIcon />} onClick={() => onAddAnswer(index)}>
                Add Answer
            </Button>
        </CardContent>
        {canDelete && (
            <CardActions sx={{ justifyContent: 'center' }}>
                <Button
                    startIcon={<DeleteIcon />}
                    onClick={() => onDelete(index)}
                    color="error"
                    sx={{ maxWidth: 'fit-content' }}
                >
                    Remove Question
                </Button>
            </CardActions>
        )}
    </Card>
);

export default QuestionCard;
