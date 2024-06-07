// src/components/ErrorPage.js
import React from 'react';
import { Link } from 'react-router-dom';

function ErrorPage() {
  return (
    <div>
      <h1>Oops! Something went wrong.</h1>
      <p>We're sorry, but the page you were looking for doesn't exist or an error occurred.</p>
      <Link to="/">Go to Home</Link>
    </div>
  );
}

export default ErrorPage;
