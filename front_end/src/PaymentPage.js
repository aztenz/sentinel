import React from 'react';
import { Link } from 'react-router-dom';

const PaymentPage = ({ productName, productPrice }) => {
  const handleDone = () => {
    // Redirect to ProductsPage
    window.location.href = '/products';
  };

  return (
    <div className="payment-container">
      <h2>Payment</h2>
      <p>Product Name: {decodeURIComponent(productName)}</p>
      <p>Product Price: {productPrice}</p>
      <button onClick={handleDone}>Done</button>
    </div>
  );
};

export default PaymentPage;
