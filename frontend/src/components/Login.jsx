import React, { useState, useEffect, useRef } from 'react';
import '../style.css';

const LOGIN_URL = "http://localhost:8080/users/searchByNameAndPassword"

function Login() {

    const userRef = useRef();
    const inputRef = useRef();
    const errRef = useRef();

    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [success, setSuccess] = useState(false);
    const [errMsg, setErrorMessage] = useState('');

    useEffect(() => {
        inputRef.current.addEventListener('click', handleSubmit);
    })

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
                body: JSON.stringify({userName, password}),
            })
            console.log(JSON.stringify(response?.data));
            setSuccess(true);

        } catch (err) {
            if (!err?.response) {
                setErrorMessage('No Server Response');
            } else if (err.response?.status === 400) {
                setErrorMessage('Invalid Username or Password');
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
                            placeholder="Username"
                            onChange={(e) => {setUserName(e.target.value)}}
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
                    </form>
                </section>
            )}
        </>
    );
}

export default Login;