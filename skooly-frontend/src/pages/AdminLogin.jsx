import React, {useState} from 'react';
import axios from 'axios';

const AdminLogin = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', null, {
        params: {username, password},
      });
      localStorage.setItem('token', response.data);
      alert('Login successful!');
      // Redirect to dashboard or homepage
      window.location.href = '/dashboard';
    } catch (error) {
      alert('Login failed. Please check credentials.');
    }
  };
  
  return (
    <div className="login-container">
      <h2>Admin Login</h2>
      <form onSubmit={handleLogin}>
        <input type="text" placeholder="Username"
               value={username} onChange={(e) => setUsername(e.target.value)} required/>
        <input type="password" placeholder="Password"
               value={password} onChange={(e) => setPassword(e.target.value)} required/>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default AdminLogin;
