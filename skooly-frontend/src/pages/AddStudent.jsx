import React, { useState } from 'react';
import axiosInstance from '../api/axiosInstance';
import { useNavigate } from 'react-router-dom';

const AddStudent = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    studentCode: '',
    firstName: '',
    lastName: '',
    gender: '',
    dateOfBirth: '',
    dateOfAdmission: '',
    email: '',
    mobileNumber: '',
    parentName: '',
    parentNumber: '',
  });
  
  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axiosInstance.post('/students', formData);
      alert('Student added successfully');
      navigate('/students');
    } catch (error) {
      alert('Failed to add student');
    }
  };
  
  return (
    <div>
      <h2>Add Student</h2>
      <form onSubmit={handleSubmit}>
        {/* Render all input fields dynamically */}
        {Object.keys(formData).map((key) => (
          <input
            key={key}
            type="text"
            name={key}
            placeholder={key}
            value={formData[key]}
            onChange={handleChange}
          />
        ))}
        <button type="submit">Add Student</button>
      </form>
    </div>
  );
};

export default AddStudent;
