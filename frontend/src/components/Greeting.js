// src/components/Greeting.js
import React from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

const Greeting = ({ email }) => {
    return (
        <Box sx={{ display: 'flex', textAlign: 'center', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
            <Typography variant="h4">
                Hello, {email}! <br /> Hope you will enjoy my application :)
            </Typography>
        </Box>
    );
};

export default Greeting;
