import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Dashboard = () => {
    const navigate = useNavigate();
    const { currentUser, logout } = useAuth();

    return (
        <div>
            <h1>Dashboard</h1>
            <p>Welcome, {currentUser?.email}</p>
            <button onClick={() => { logout(); navigate('/'); }}>Logout</button>
        </div>
    );
};

export default Dashboard;
