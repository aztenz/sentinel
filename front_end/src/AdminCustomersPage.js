import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AdminCustomersPage.css';

const AdminCustomersPage = ({ authToken }) => {
  const [customers, setCustomers] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/users', {
          headers: { Authorization: `Bearer ${authToken}` },
        });
        setCustomers(response.data);
      } catch (err) {
        setError(err.response.data.message);
      }
    };

    fetchCustomers();
  }, [authToken]);

  return (
    <div className="admin-customers-container">
      <h2>Admin View Customers</h2>
      {customers.length > 0 ? (
        <ul>
          {customers.map((customer) => (
            <li key={customer.id}>
              <p>Username: {customer.username}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>No customers found.</p>
      )}
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default AdminCustomersPage;
