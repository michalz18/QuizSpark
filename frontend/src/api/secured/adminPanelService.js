import axios from '../axiosInstance';

export const getAllUsersData = async () => {
    const response = await axios.get(`/admin/users`);
    return response.data;
};



