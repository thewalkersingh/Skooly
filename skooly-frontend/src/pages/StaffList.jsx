import React, {useEffect, useState} from 'react';
import axiosInstance from '../api/axiosInstance';
import {Link} from 'react-router-dom';

const StaffList = () => {
  const [staff, setStaff] = useState([]);
  
  useEffect(() => {
    fetchStaff();
  }, []);
  
  const fetchStaff = async () => {
    try {
      const response = await axiosInstance.get('/staff');
      setStaff(response.data.content); // paged
    } catch (error) {
      console.error('Failed to fetch staff', error);
    }
  };
  
  return (
    <div>
      <h2>Staff Members</h2>
      <Link to="/staff/add">
        <button>Add Staff</button>
      </Link>
      <ul>
        {staff.map(member => (
          <li key={member.id}>
            {member.firstName} {member.lastName} - {member.role}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default StaffList;
