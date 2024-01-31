import axios from 'axios';

export const registerUser = async (userData) => {
    try {
        const { data } = await axios.post('http://localhost:8080/api/authentication/register', userData);
        console.log('Registration process was successful', data);
        return data;
    } catch (error) {
        console.error('Error during registration process', error);
        throw error;
    }
};