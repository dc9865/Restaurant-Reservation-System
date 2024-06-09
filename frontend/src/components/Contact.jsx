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
      <p>Address: 14 Wisteria Way, Commack, USA</p>
      <p>Phone: (929) 318-9330</p>
      <p>Email: dyhcho@gmail.com</p>

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
