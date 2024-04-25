import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProfilePage = ({ authToken }) => {
  const [user, setUser] = useState(null);
  const [error, setError] = useState('');

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
    <div>
      <h2>User Profile</h2>
      {user ? (
        <div>
          <p>Username: {user.username}</p>
          <p>Email: {user.email}</p>
          <p>Role: {user.role}</p>
        </div>
      ) : (
        <p>Loading profile...</p>
      )}
      {error && <p>{error}</p>}
    </div>
  );
};

export default ProfilePage;
