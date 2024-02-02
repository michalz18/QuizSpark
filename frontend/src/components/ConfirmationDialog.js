import React from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@mui/material';

const ConfirmationDialog = ({ open, onCancel, onConfirm, title, content }) => {
    return (
        <Dialog open={open} onClose={onCancel} aria-labelledby="alert-dialog-title">
            <DialogTitle id="alert-dialog-title">{title}</DialogTitle>
            <DialogContent>{content}</DialogContent>
            <DialogActions>
                <Button onClick={onCancel} color="primary">
                    Cancel
                </Button>
                <Button onClick={onConfirm} color="primary" autoFocus>
                    Submit
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ConfirmationDialog;