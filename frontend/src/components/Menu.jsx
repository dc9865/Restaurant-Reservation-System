// src/components/Menu.js
import React, { useEffect, useState } from 'react';

function Menu() {
  const [menuItems, setMenuItems] = useState([]);

  useEffect(() => {
    // Fetch menu items from the backend API
    fetch('/api/menu')
      .then(response => response.json())
      .then(data => setMenuItems(data));
  }, []);

  return (
    <div>
      <h1>Our Menu</h1>
      <ul>
        {menuItems.map(item => (
          <li key={item.id}>
            <h2>{item.name}</h2>
            <p>{item.description}</p>
            <p>Price: ${item.price}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Menu;
