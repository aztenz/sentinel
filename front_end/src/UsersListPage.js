import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './UsersListPage.css';

const UsersListPage = ({ authToken }) => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/users', {
          headers: { Authorization: `Bearer ${authToken}` },
        });
        setUsers(response.data);
      } catch (err) {
        setError(err.response.data.message);
      }
    };

    fetchUsers();
  }, [authToken]);

  return (
    <div className="users-list-container">
      <div className="header">
        <h2>Users List</h2>
        <Link to="/products" className="back-link">Back to Products</Link>
      </div>
      {users.length > 0 ? (
        <table className="users-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Email</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.username}</td>
                <td>{user.email}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No users found.</p>
      )}
      {error && <p>{error}</p>}
    </div>
  );
};

export default UsersListPage;
