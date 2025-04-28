// src/pages/Dashboard.jsx
import React from 'react';
import {Link} from 'react-router-dom';

const Dashboard = () => {
  return (
    <div className="dashboard-container">
      <h1>Admin Dashboard</h1>
      <ul>
        <li><Link to="/students">Manage Students</Link></li>
        <li><Link to="/teachers">Manage Teachers</Link></li>
        <li><Link to="/staff">Manage Staff</Link></li>
      </ul>
    </div>
  );
};

export default Dashboard;
