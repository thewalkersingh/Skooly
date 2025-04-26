import React, {useState, useEffect} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import staffService from '../../services/staffService';
import '../../assets/style/StaffForm.css'

const StaffForm = () => {
  const navigate = useNavigate();
  const {id} = useParams();
  const isEditMode = Boolean(id);
  
  const [staff, setStaff] = useState({
    name: '',
    age: '',
    gender: '',
    address: '',
    phoneNumber: '',
    email: '',
    role: ''
  });
  
  useEffect(() => {
    if (isEditMode) {
      staffService.getById(id)
        .then((data) => setStaff(data))
        .catch((err) => console.error("Error fetching staff:", err));
    }
  }, [id, isEditMode]);
  
  const handleChange = (e) => {
    setStaff({...staff, [e.target.name]: e.target.value});
  };
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditMode) {
        await staffService.updateStaff(id, staff);
      } else {
        await staffService.addStaff(staff);
      }
      navigate('/staff');
    } catch (error) {
      console.error("Error saving staff:", error);
    }
  };
  
  return (
    <div className="container">
      <h3 className="heading">{isEditMode ? "Edit Staff" : "Add Staff"}</h3>
      <form onSubmit={handleSubmit} className="form">
        <div className="inputGroup">
          <label className="label">Name:</label>
          <input
            type="text"
            name="name"
            value={staff.name}
            onChange={handleChange}
            required
            className="input"
          />
        </div>
        <div className="inputGroup">
          <label className="label">Age:</label>
          <input
            type="number"
            name="age"
            value={staff.age}
            onChange={handleChange}
            className="input"
          />
        </div>
        <div className="inputGroup">
          <label className="label">Gender:</label>
          <input
            type="text"
            name="gender"
            value={staff.gender}
            onChange={handleChange}
            className="input"
          />
        </div>
        <div className="inputGroup">
          <label className="label">Address:</label>
          <input
            type="text"
            name="address"
            value={staff.address}
            onChange={handleChange}
            required
            className="input"
          />
        </div>
        <div className="inputGroup">
          <label className="label">Phone Number:</label>
          <input
            type="text"
            name="phoneNumber"
            value={staff.phoneNumber}
            onChange={handleChange}
            required
            className="input"
          />
        </div>
        <div className="inputGroup">
          <label className="label">Email:</label>
          <input
            type="email"
            name="email"
            value={staff.email}
            onChange={handleChange}
            required
            className="input"
          />
        </div>
        <div className="inputGroup">
          <label className="label">Role:</label>
          <select
            name="role"
            value={staff.role}
            onChange={handleChange}
            required
            className="select"
          >
            <option value="">Select Role</option>
            <option value="PRINCIPAL">PRINCIPAL</option>
            <option value="ADMIN">ADMIN</option>
            <option value="MANAGER">MANAGER</option>
            <option value="ACCOUNTANT">ACCOUNTANT</option>
            <option value="PEON">PEON</option>
            <option value="SECURITY">SECURITY</option>
            <option value="LAB_ASSISTANT">LAB_ASSISTANT</option>
            <option value="CLEANER">CLEANER</option>
          </select>
        </div>
        <button type="submit" className="button">
          {isEditMode ? "Update" : "Add"}
        </button>
      </form>
    </div>
  );
};

export default StaffForm;
