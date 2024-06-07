// src/components/ReservationList.js
import React, { useEffect, useState } from 'react';

function ReservationList() {
  const [reservations, setReservations] = useState([]);

  useEffect(() => {
    // Fetch reservations from the backend API
    fetch('/api/reservations')
      .then(response => response.json())
      .then(data => setReservations(data));
  }, []);

  return (
    <div>
      <h1>All Reservations</h1>
      <ul>
        {reservations.map(reservation => (
          <li key={reservation.id}>
            <p>Name: {reservation.name}</p>
            <p>Date: {reservation.date}</p>
            <p>Time: {reservation.time}</p>
            <p>Contact: {reservation.contact}</p>
            <p>Requests: {reservation.requests}</p>
            {/* Add buttons to approve, edit, or delete reservation */}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ReservationList;
