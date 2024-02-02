import axios from '../axiosInstance';
export const createQuiz = async (quizData) => {
    const response = await axios.post('/quizzes', quizData);
    return response.data;
};

export const getQuizById = async (id) => {
    const response = await axios.get(`${process.env.REACT_APP_API_QUIZ_URL}/${id}`);
    return response.data;
};

export const getAllQuizzes = async () => {
    const response = await axios.get(`${process.env.REACT_APP_API_QUIZ_UR}`);
    return response.data;
};


