import React, { useState } from 'react';
import DashboardAppBar from '../components/DashboardAppBar';
import Greeting from '../components/Greeting';
import QuizForm from '../components/QuizForm';
import QuizTiles from '../components/QuizTiles';
import UserManagementDashboard from "../components/UserManagementDashboard";
import { useAuth } from '../context/AuthContext';

const HomePage = () => {
    const { currentUser } = useAuth();
    const [activeComponent, setActiveComponent] = useState('greeting');
    const renderComponent = () => {
        switch (activeComponent) {
            case 'quizForm':
                return <QuizForm />;
            case 'quizTiles':
                return <QuizTiles />;
            case 'userManagementDashboard':
                return <UserManagementDashboard />;
            case 'greeting':
            default:
                return <Greeting email={currentUser.email} />;
        }
    };

    return (
        <div>
            <DashboardAppBar
                onCreateQuizClick={() => setActiveComponent('quizForm')}
                onSolveQuizClick={() => setActiveComponent('quizTiles')}
                onUserManagementClick={() => setActiveComponent('userManagementDashboard')}
            />
            {renderComponent()}
        </div>
    );
};

export default HomePage;
