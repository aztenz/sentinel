import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate} from 'react-router-dom';
import './ProfilePage.css';


const ProfilePage = ({ authToken, setAuthToken }) => {
  const [user, setUser] = useState(null);
  const [error, setError] = useState('');
  const navigate = useNavigate();
  
  const handleLogout = () => {
    setAuthToken('');
    navigate('/login');
  };

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/users/profile', {
          headers: { Authorization: `Bearer ${authToken}` },
        });
        setUser(response.data);
      } catch (err) {
        setError(err.response.data.message);
      }
    };

    fetchUserProfile();
  }, [authToken]);

  return (
    <div className="profile-container">
      <h2>User Profile</h2>
      <Link to="/products" className="back-link">Back to Products</Link>
      <button className="logout-button" onClick={handleLogout}>Logout</button>

      {user ? (
        <div>
          <p>Username: {user.username}</p>
          <p>Email: {user.email}</p>
          <p>Role: {user.role}</p>
        </div>
      ) : (
        <p className="loading-message">Loading profile...</p>
      )}
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default ProfilePage;
