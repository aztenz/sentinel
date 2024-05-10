import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ProductsPage.css'; 
import { Link, useNavigate } from 'react-router-dom';

const ProductsPage = ({ authToken, setAuthToken }) => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/products', {
          headers: { Authorization: `Bearer ${authToken}` },
        });
        setProducts(response.data);
      } catch (err) {
        setError(err.response.data.message);
      }
    };

    fetchProducts();
  }, [authToken]);

  const handleLogout = () => {
    setAuthToken('');
    navigate('/login');
  };

  const handleBuyNow = (productId, productName, productPrice) => {
    // Navigate to PaymentPage with product details
    navigate(`/payment/${productId}/${encodeURIComponent(productName)}/${productPrice}`);
  };

  const handleUsersList = () => {
    navigate('/usersList');
  };

  return (
    <div className="products-container">
      <div className="header">
        <h2 className="products-title">Home</h2>
        <Link to="/profile" className="view-profile">View Profile</Link>
        <button className="logout-button" onClick={handleLogout}>Logout</button>
        <button className="users-list-button" onClick={handleUsersList}>User List</button>
      </div>
      {products.length > 0 ? (
        <ul className="products-list">
          {products.map((product) => (
            <li key={product.id} className="products-item">
              <div className="product-details">
                <p className="product-name">Name: {product.name}</p>
                <p className="product-price">Price: {product.price}</p>
              </div>
              <button className="buy-button" onClick={() => handleBuyNow(product.id, product.name, product.price)}>Buy Now</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>No products available.</p>
      )}
      {error && <p>{error}</p>}
    </div>
  );
};

export default ProductsPage;
