import React from 'react';
import { useAuth } from '../context/AuthContext';

const Dashboard = () => {
    const { currentUser, logout } = useAuth();

    return (
        <div>
            <h1>Dashboard</h1>
            <p>Welcome, {currentUser?.email}</p>
            <button onClick={logout}>Logout</button>
        </div>
    );
};

export default Dashboard;
