import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './LoginPage';
import RegistrationPage from './RegistrationPage';
import ProfilePage from './ProfilePage';
import AdminCustomersPage from './AdminCustomersPage';
import ProductsPage from './ProductsPage';

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
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/profile" element={<ProfilePage authToken={authToken} />} />
        <Route path="/admin/customers" element={<AdminCustomersPage authToken={authToken} />} />
        <Route path="/products" element={<ProductsPage authToken={authToken} />} />
      </Routes>
    </Router>
  );
};

export default App;
