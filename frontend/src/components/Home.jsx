// src/components/Home.js
import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div>
      <h1>Welcome to Our Restaurant</h1>
      <p>Experience the best dining with us.</p>
      <nav>
        <ul>
          <li><Link to="/reservations">Make a Reservation</Link></li>
          <li><Link to="/menu">View Menu</Link></li>
          <li><Link to="/contact">Contact Us</Link></li>
        </ul>
      </nav>
    </div>
  );
}

export default Home;
