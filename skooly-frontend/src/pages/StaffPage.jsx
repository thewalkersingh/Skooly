import React from 'react';
import {Routes, Route, Link} from 'react-router-dom';
import StaffList from '../components/staff/StaffList';
import StaffForm from '../components/staff/StaffForm';

function StaffPage() {
  return (
    <div>
      <h2>Staff Management</h2>
      <nav>
        <ul>
          <li><Link to="">Staff List</Link></li>
          <li><Link to="add">Add Staff</Link></li>
        </ul>
      </nav>
      <Routes>
        <Route path="/" element={<StaffList/>}/>
        <Route path="add" element={<StaffForm/>}/>
        <Route path="edit/:id" element={<StaffForm/>}/>
      </Routes>
    </div>
  );
}

export default StaffPage;
