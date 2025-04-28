import React, {useState} from 'react';
import axiosInstance from '../api/axiosInstance';
import {useNavigate} from 'react-router-dom';

const AddStaff = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    staffCode: '',
    firstName: '',
    lastName: '',
    gender: '',
    dateOfBirth: '',
    dateOfHire: '',
    email: '',
    mobileNumber: '',
    salary: '',
    role: '',
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
      await axiosInstance.post('/staff', formData);
      alert('Staff member added successfully');
      navigate('/staff');
    } catch (error) {
      alert('Failed to add staff' + error);
    }
  };
  
  return (
    <div>
      <h2>Add Staff</h2>
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
        <button type="submit">Add Staff</button>
      </form>
    </div>
  );
};

export default AddStaff;
