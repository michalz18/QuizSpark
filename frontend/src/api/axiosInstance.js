import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: process.env.REACT_APP_API_KEY,
});

axiosInstance.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

axiosInstance.interceptors.response.use(response => {
    return response;
}, error => {
    if (error.response.status === 401) {
    window.location = '/';
    }
    return Promise.reject(error);
});

export default axiosInstance;
