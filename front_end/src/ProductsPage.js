import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProductsPage = ({ authToken }) => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState('');

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

  return (
    <div>
      <h2>Products</h2>
      {products.length > 0 ? (
        <ul>
          {products.map((product) => (
            <li key={product.id}>
              <p>Name: {product.name}</p>
              <p>Price: {product.price}</p>
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
