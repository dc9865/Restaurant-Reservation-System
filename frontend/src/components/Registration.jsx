import React, { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../loginStyle.css';

const REGISTER_URL = "http://localhost:8080/auth/register";

function Register() {
    const userRef = useRef();
    const errRef = useRef();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [address, setAddress] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const [success, setSuccess] = useState(false);
    const [errMsg, setErrorMessage] = useState('');

    useEffect(() => {
        userRef.current.focus();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(REGISTER_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ firstName, lastName, username, 
                    password, address, phoneNumber}),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            setSuccess(true);

        } catch (err) {
            if (!err?.response) {
                setErrorMessage('No Server Response');
            } else if (err.response?.status === 400) {
                setErrorMessage('Registration failed');
            } else {
                setErrorMessage('Registration failed');
            }
            errRef.current.focus();
        }
    };

    return (
        <>
            {success ? (
                <section>
                    <h1>Registration Successful!</h1>
                    <br />
                    <p>
                        <Link to={"/auth/login"}>Go to Login</Link>
                    </p>
                </section>
            ) : (
                <section className="form-container">
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Register</h1>
                    <form onSubmit={handleSubmit}>
                        <input 
                            type="text"
                            id="firstName"
                            ref={userRef}
                            autoComplete="off"
                            placeholder="First Name"
                            onChange={(e) => setFirstName(e.target.value)}
                            required
                        />
                        <br />
                        <input 
                            type="text"
                            id="lastName"
                            placeholder="Last Name"
                            onChange={(e) => setLastName(e.target.value)}
                            required
                        />
                        <br />
                        <input 
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            placeholder="Username"
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                        <br />
                        <input 
                            type="password" 
                            id="password" 
                            placeholder="Password"
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <br />
                        <input 
                            type="text"
                            id="address"
                            placeholder="Address"
                            onChange={(e) => setAddress(e.target.value)}
                            required
                        />
                        <br />
                        <input 
                            type="text"
                            id="phoneNumber"
                            placeholder="Phone Number"
                            onChange={(e) => setPhoneNumber(e.target.value)}
                            required
                        />
                        <br />
                        <button type="submit">Register</button>
                    </form>
                </section>
            )}
        </>
    );
}

export default Register;
