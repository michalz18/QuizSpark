import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignIn from './components/SignIn';
import SignUp from "./components/SignUp";
import { AuthProvider } from './context/AuthContext';
import HomePage from "./pages/HomePage";

const AppRouter = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<SignIn />} />
                    <Route path="/signup" element={<SignUp />} />
                    <Route path="/dashboard" element={<HomePage />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
};

export default AppRouter;
