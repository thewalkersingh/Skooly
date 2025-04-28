import React, {useState} from 'react';
import axiosInstance from '../api/axiosInstance';
import {useNavigate} from 'react-router-dom';

const AddTeacher = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    teacherCode: '',
    firstName: '',
    lastName: '',
    gender: '',
    dateOfBirth: '',
    dateOfHire: '',
    email: '',
    mobileNumber: '',
    salary: '',
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
      await axiosInstance.post('/teachers', formData);
      alert('Teacher added successfully');
      navigate('/teachers');
    } catch (error) {
      alert('Failed to add teacher');
    }
  };
  
  return (
    <div>
      <h2>Add Teacher</h2>
      <form onSubmit={handleSubmit}>
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
        <button type="submit">Add Teacher</button>
      </form>
    </div>
  );
};

export default AddTeacher;
