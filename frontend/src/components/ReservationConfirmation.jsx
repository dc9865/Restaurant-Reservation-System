// src/components/ReservationConfirmation.js
import React from 'react';
import { Link } from 'react-router-dom';

function ReservationConfirmation({ reservation }) {
  return (
    <div>
      <h1>Reservation Confirmed!</h1>
      <p>Thank you, {reservation.name}. Your reservation is confirmed for {reservation.date} at {reservation.time}.</p>
      <p>We look forward to serving you!</p>
      <Link to="/">Go to Home</Link>
      <Link to="/reservations">Make Another Reservation</Link>
    </div>
  );
}

export default ReservationConfirmation;
