import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/staff';

const getAll = async () => {
  const response = await axios.get(BASE_URL);
  return response.data;
};

const getById = async (id) => {
  const response = await axios.get(`${BASE_URL}/${id}`);
  return response.data;
};

const addStaff = async (staff) => {
  const response = await axios.post(`${BASE_URL}/`, staff);
  return response.data;
};

const updateStaff = async (id, staff) => {
  const response = await axios.put(`${BASE_URL}/${id}`, staff);
  return response.data;
};

const deleteStaff = async (id) => {
  const response = await axios.delete(`${BASE_URL}/${id}`);
  return response.data;
};

const staffService = {getAll, getById, addStaff, updateStaff, deleteStaff};

export default staffService;
