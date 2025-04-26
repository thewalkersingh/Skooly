import React from 'react';
import {Link} from 'react-router-dom';
import '../assets/style/Navbar.css';

const Navbar = () => {
  return (
    <nav className="nav">
      <div className="logo">
        <Link to="/" className="link">
          School Management
        </Link>
      </div>
      <ul className="navList">
        <li className="navItem">
          <Link to="/" className="link">Home</Link>
        </li>
        <li className="navItem">
          <Link to="/staff" className="link">Staff</Link>
        </li>
        {/* Future modules can be added here */}
      </ul>
    </nav>
  );
};

export default Navbar;
