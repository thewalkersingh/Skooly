import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import staffService from '../../services/staffService';

function StaffList() {
  const [staffs, setStaffs] = useState([]);
  
  useEffect(() => {
    fetchStaff();
  }, []);
  
  const fetchStaff = async () => {
    try {
      const data = await staffService.getAll();
      setStaffs(data);
    } catch (error) {
      console.error("Error fetching staff:", error);
    }
  };
  
  const handleDelete = async (id) => {
    try {
      await staffService.deleteStaff(id);
      fetchStaff(); // Refresh list after deletion
    } catch (error) {
      console.error("Error deleting staff:", error);
    }
  };
  
  return (
    <div>
      <h3>Staff List</h3>
      <table border="1" cellPadding="8" cellSpacing="0">
        <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Phone Number</th>
          <th>Email</th>
          <th>Role</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        {staffs.map((staff) => (
          <tr key={staff.id}>
            <td>{staff.id}</td>
            <td>{staff.name}</td>
            <td>{staff.phoneNumber}</td>
            <td>{staff.email}</td>
            <td>{staff.role}</td>
            <td>
              <Link to={`/staff/edit/${staff.id}`}>Edit</Link>
              &nbsp;|&nbsp;
              <button onClick={() => handleDelete(staff.id)}>Delete</button>
            </td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default StaffList;
