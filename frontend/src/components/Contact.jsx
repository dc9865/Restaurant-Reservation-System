// src/components/Contact.js
import React, { useState } from 'react';

function Contact() {
  const [contactData, setContactData] = useState({
    name: '',
    email: '',
    message: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setContactData({ ...contactData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Send contactData to the backend API
  };

  return (
    <div>
      <h1>Contact Us</h1>
      <p>Address: 123 Main Street, Hometown, USA</p>
      <p>Phone: (123) 456-7890</p>
      <p>Email: contact@restaurant.com</p>

      <form onSubmit={handleSubmit}>
        <label>Name:</label>
        <input type="text" name="name" value={contactData.name} onChange={handleChange} required />
        
        <label>Email:</label>
        <input type="email" name="email" value={contactData.email} onChange={handleChange} required />
        
        <label>Message:</label>
        <textarea name="message" value={contactData.message} onChange={handleChange} required></textarea>
        
        <button type="submit">Send Message</button>
      </form>
    </div>
  );
}

export default Contact;
