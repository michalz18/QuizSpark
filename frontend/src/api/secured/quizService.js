import axios from '../axiosInstance';
export const createQuiz = async (quizData) => {
    const response = await axios.post('/quizzes', quizData);
    return response.data;
};

export const getQuizById = async (id) => {
    const response = await axios.get(`/quizzes/${id}`);
    return response.data;
};

export const getAllQuizzes = async () => {
    const response = await axios.get(`/quizzes`);
    return response.data;
};
export const solveQuiz = async (id, answers) => {
    const response = await axios.post(`/quizzes/${id}/solve`, answers);
    return response.data;
};



