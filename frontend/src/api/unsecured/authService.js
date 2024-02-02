import axiosInstance from '../axiosInstance';

export const registerUser = async (userData) => {
    try {
        const { data } = await axiosInstance.post('/authentication/register', userData);
        console.log('Registration process was successful', data);
        return data;
    } catch (error) {
        console.error('Error during registration process', error);
        throw error;
    }
};

export const authenticateUser = async (userData) => {
    try {
        const { data } = await axiosInstance.post('/authentication/authenticate', userData);
        console.log('Authentication successful', data);
        return data;
    } catch (error) {
        console.error('Error during authentication', error);
        throw error;
    }
};
