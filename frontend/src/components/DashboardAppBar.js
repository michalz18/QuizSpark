import React, { useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import LightbulbIcon from '@mui/icons-material/Lightbulb';
import QuizIcon from '@mui/icons-material/Quiz';
import CreateIcon from '@mui/icons-material/Create';
import { useAuth } from '../context/AuthContext';

const DashboardAppBar = ({ onCreateQuizClick, onSolveQuizClick, onUserManagementClick }) => {
    const [anchorEl, setAnchorEl] = useState(null);
    const { currentUser } = useAuth();

    const isAdmin = currentUser && currentUser.role === 'ADMIN';

    return (
        <AppBar position="static">
            <Toolbar>
                <Box sx={{ display: 'flex', alignItems: 'center', flexGrow: 1 }}>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="logo"
                    >
                        <LightbulbIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ ml: 2 }}>
                        QuizSpark
                    </Typography>
                </Box>
                {isAdmin ? (
                    <Box sx={{ flexGrow: 2, display: 'flex', justifyContent: 'center' }}>
                        <Button
                            color="inherit"
                            onClick={onUserManagementClick}
                        >
                            User Management
                        </Button>
                    </Box>
                ) : (
                    <Box sx={{ flexGrow: 2, display: 'flex', justifyContent: 'center' }}>
                        <Button color="inherit" startIcon={<QuizIcon />} onClick={onSolveQuizClick}>
                            Solve quiz
                        </Button>
                        <Button
                            color="inherit"
                            startIcon={<CreateIcon />}
                            onClick={onCreateQuizClick}
                        >
                            Create own quiz
                        </Button>
                    </Box>
                )}
                <Box sx={{ flexGrow: 1, display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>
                    <IconButton
                        color="inherit"
                        aria-label="display more actions"
                        edge="end"
                        onClick={(event) => setAnchorEl(event.currentTarget)}
                    >
                        <MoreVertIcon />
                    </IconButton>
                    <Menu
                        anchorEl={anchorEl}
                        open={Boolean(anchorEl)}
                        onClose={() => setAnchorEl(null)}
                    >
                        <MenuItem onClick={() => setAnchorEl(null)} component="a" href="/change-password">
                            Change credentials
                        </MenuItem>
                        <MenuItem onClick={() => setAnchorEl(null)} component="a" href="/logout">
                            Logout
                        </MenuItem>
                    </Menu>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default DashboardAppBar;
