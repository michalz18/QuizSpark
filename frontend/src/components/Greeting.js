import React from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

const Greeting = ({ firstName, lastName }) => {
    return (
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Typography variant="h4">
                Hello, {firstName} {lastName}! Hope you will enjoy my application :)
            </Typography>
        </Box>
    );
};

export default Greeting;
