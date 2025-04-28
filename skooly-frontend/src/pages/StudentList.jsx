import React, {useEffect, useState} from 'react';
import axiosInstance from '../api/axiosInstance';
import {Link} from 'react-router-dom';

const StudentList = () => {
  const [students, setStudents] = useState([]);
  
  useEffect(() => {
    fetchStudents();
  }, []);
  
  const fetchStudents = async () => {
    try {
      const response = await axiosInstance.get('/students');
      setStudents(response.data.content); // because it's paged
    } catch (error) {
      console.error('Failed to fetch students', error);
    }
  };
  
  return (
    <div>
      <h2>Students</h2>
      <Link to="/students/add">
        <button>Add Student</button>
      </Link>
      <ul>
        {students.map(student => (
          <li key={student.id}>
            {student.firstName} {student.lastName}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default StudentList;
