import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './LoginPage';
import RegistrationPage from './RegistrationPage';
import ProfilePage from './ProfilePage';
import AdminCustomersPage from './AdminCustomersPage';
import ProductsPage from './ProductsPage';
import UsersListPage from './UsersListPage';

const App = () => {
  const [authToken, setAuthToken] = useState(localStorage.getItem('authToken') || '');

  useEffect(() => {
    if (authToken) {
      localStorage.setItem('authToken', authToken);
    } else {
      localStorage.removeItem('authToken');
    }
  }, [authToken]);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage setAuthToken={setAuthToken} />} />
        <Route path="/" element={<RegistrationPage />} />
        <Route path="/profile" element={<ProfilePage authToken={authToken} setAuthToken={setAuthToken} />} />
        <Route path="/admin/customers" element={<AdminCustomersPage authToken={authToken} />} />
        <Route path="/usersList" element={<UsersListPage authToken={authToken} setAuthToken={setAuthToken}/>} />
        <Route path="/products" element={<ProductsPage authToken={authToken} setAuthToken={setAuthToken}/>} />
      </Routes>
    </Router>
  );
};

export default App;
