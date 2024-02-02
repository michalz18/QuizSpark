import React, { useState } from 'react';
import DashboardAppBar from '../components/DashboardAppBar';
import Greeting from '../components/Greeting';
import QuizForm from '../components/QuizForm';
import { useAuth } from '../context/AuthContext';

const HomePage = () => {
    const { currentUser } = useAuth();
    const [showQuizForm, setShowQuizForm] = useState(false);

    const handleCreateQuizClick = () => {
        setShowQuizForm(true);
    };

    return (
        <div>
            <DashboardAppBar onCreateQuizClick={handleCreateQuizClick} />
            {showQuizForm ? (
                <QuizForm />
            ) : (
                <Greeting email={currentUser} />
            )}
        </div>
    );
};

export default HomePage;
