import React, { useState } from 'react';

function ReservationForm() {
  const [formData, setFormData] = useState({
    restaurantId: '',
    restaurantName: '',
    restaurantAddress: '',
    fullName: '', // changed from userFirstName and userLastName
    userEmail: '',
    userPhoneNumber: '',
    numberOfPeople: 0,
    checkedIn: false,
    reservationTime: '',
    reservationDate: '',
    requests: '',
  });

  const [responseMessage, setResponseMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const [userFirstName, userLastName] = formData.fullName.split(' '); // split the fullName here
    const finalFormData = { ...formData, userFirstName, userLastName }; // add the split names to the formData
    delete finalFormData.fullName; // remove the fullName from the formData


    try {
      e.preventDefault();
      // Fetch the restaurant ID based on the name and address
      const restaurantResponse = await fetch(`http://localhost:8080/restaurants/searchByNameAndAddress/${encodeURIComponent(formData.restaurantName)}/${encodeURIComponent(formData.restaurantAddress)}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
      });

      console.log(restaurantResponse);

      const restaurantData = await restaurantResponse.json();

      if (!restaurantData.id) {
        setResponseMessage('Failed to find restaurant.');
        return;
      }
      
      finalFormData.restaurantId = restaurantData.id;

      console.log(finalFormData);
      const response = await fetch(`http://localhost:8080/reservations/create`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(finalFormData),
      });
      if (response.status === 201) {
        setResponseMessage('Reservation created successfully!');
      } else {
        setResponseMessage('Failed to create reservation.');
      }
    } catch (error) {
      console.error('Error details:', error);
      setResponseMessage('An error occurred while creating the reservation.');
    }
  };

  return (
      <div>
          <br></br>
            <div className = "container">
              <div className = "form">
                <form onSubmit={handleSubmit}>
                  <ul>
                    <h2>Reservation Form</h2>
                    <li>
                      <label>Name:</label>
                      <input type="text" name="fullName" value={formData.fullName} onChange={handleChange} required />
                    </li>
                    <li>
                      <label>Contact:</label>
                      <input type="tel" name="userPhoneNumber" value={formData.userPhoneNumber} onChange={handleChange} required />
                    </li>
                    <li>
                      <label>Email:</label>
                      <input type="text" name="userEmail" value={formData.userEmail} onChange={handleChange} required />
                    </li>
                    <li>
                      <label>Date:</label>
                      <input type="date" name="reservationDate" value={formData.reservationDate} onChange={handleChange} required />
                    </li>
                    <li>
                      <label>Time:</label>
                      <input type="time" name="reservationTime" value={formData.reservationTime} onChange={handleChange} required />
                    </li>
                    <li>
                      <label>Guests:</label>
                      <input type="number" name="numberOfPeople" value={formData.numberOfPeople} onChange={handleChange} required />
                    </li>
                    <br></br>
                    <li>
                      <label>Restaurant Name:</label>
                      <input type="text" name="restaurantName" value={formData.restaurantName} onChange={handleChange} required />
                    </li>
                    <li>
                      <label>Restaurant Address:</label>
                      <input type="text" name="restaurantAddress" value={formData.restaurantAddress} onChange={handleChange} required />
                    </li>
                    <br></br>
                    <li>
                      <label>Special Requests:</label>
                      <textarea name="requests" value={formData.requests} onChange={handleChange}></textarea>
                    </li>
                    <br></br>
                    <button type="submit">Submit Reservation</button>
                  </ul>
                  <br></br>
                </form>
              </div>
            </div>
            {responseMessage && <p>{responseMessage}</p>}
      </div>
  );
}

export default ReservationForm;