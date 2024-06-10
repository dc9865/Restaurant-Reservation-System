import React, { useState, useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import './Registration.jsx'
import '../loginStyle.css';

const LOGIN_URL = "http://localhost:8080/auth/login";

function Login() {

    const userRef = useRef();
    const inputRef = useRef();
    const errRef = useRef();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [success, setSuccess] = useState(false);
    const [errMsg, setErrorMessage] = useState('');

    useEffect(() => {
        userRef.current.focus();
    },[])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(LOGIN_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({username, password}),
            })
            if (!response.ok) {
                throw new Error('HTTP error! status: ${response.status}');
            }

            const data = await response.json();
            console.log(JSON.stringify(data));
            // Save the token to the local storage
            localStorage.setItem('jwtToken', data.token);

            setSuccess(true);

        } catch (err) {
            if (!err?.response) {
                setErrorMessage('No Server Response');
            } else if (err.response?.status === 400) {
                setErrorMessage('Invalid username or Password');
            } else if (err.response?.status === 401) {
                setErrorMessage('Unauthorized');
            } else {
                setErrorMessage('Login failed');
            }
            errRef.current.focus();
        }
        
    }
    return (
        <>
            {success ? ( 
                <section>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <a href="/">Go to Home</a>
                    </p>
                </section>
            ) : (
                <section>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Log in</h1>
                    <form onSubmit={handleSubmit}>
                        <input 
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            placeholder="username"
                            onChange={(e) => {setUsername(e.target.value)}}
                            required
                        />
                        <br></br>
                        <input 
                            type="password" 
                            id="password" 
                            placeholder="Password"
                            onChange={(e) => {setPassword(e.target.value)}}
                            required
                        />
                        <br></br>
                        <button type="submit" ref={inputRef}>Log in</button>

                        <p>Don't have an account? <Link to="/auth/register">Register</Link></p>
                    </form>
                </section>
            )}
        </>
    );
}

export default Login;