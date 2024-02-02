import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignIn from './components/SignIn';
import SignUp from "./components/SignUp";
import DashboardAppBar from "./components/DashboardAppBar";
import { AuthProvider } from './context/AuthContext';

const AppRouter = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<SignIn />} />
                    <Route path="/signup" element={<SignUp />} />
                    <Route path="/dashboard" element={<DashboardAppBar />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
};

export default AppRouter;
