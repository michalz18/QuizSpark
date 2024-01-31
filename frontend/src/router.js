import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignIn from './components/SignIn';

const AppRouter = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<SignIn />} />
            </Routes>
        </Router>
    );
};

export default AppRouter;
