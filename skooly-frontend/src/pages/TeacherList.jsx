import React, {useEffect, useState} from 'react';
import axiosInstance from '../api/axiosInstance';
import {Link} from 'react-router-dom';

const TeacherList = () => {
  const [teachers, setTeachers] = useState([]);
  
  useEffect(() => {
    fetchTeachers();
  }, []);
  
  const fetchTeachers = async () => {
    try {
      const response = await axiosInstance.get('/teachers');
      setTeachers(response.data.content); // paged
    } catch (error) {
      console.error('Failed to fetch teachers', error);
    }
  };
  
  return (
    <div>
      <h2>Teachers</h2>
      <Link to="/teachers/add">
        <button>Add Teacher</button>
      </Link>
      <ul>
        {teachers.map(teacher => (
          <li key={teacher.id}>
            {teacher.firstName} {teacher.lastName}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TeacherList;
