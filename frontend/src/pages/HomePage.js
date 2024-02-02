import React from 'react';
import DashboardAppBar from '../components/DashboardAppBar';
import Greeting from '../components/Greeting';
import { useAuth } from '../context/AuthContext';

const HomePage = () => {
    const { currentUser } = useAuth();

    return (
        <div>
            <DashboardAppBar />
            <Greeting email={currentUser} />
        </div>
    );
};

export default HomePage;
