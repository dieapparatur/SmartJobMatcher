import {useState} from "react";
import {candidateLogin} from "../userService.js"
import {BrowserRouter, Routes, Route, useNavigate} from "react-router-dom";

function CandidateLoginComponent() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    async function handleLogin(event) {
        event.preventDefault();
        console.log("Trying to handle Login with following credentials.\nE-Mail: " + email + ". Password: " + password);
        const loginResponse = await candidateLogin(email, password);
        console.log("Response from backend (likely JWT): " + loginResponse);
        localStorage.setItem('accessToken', loginResponse);
        navigate("/candidate/me")
    }


    return (
        <div>
            <h1>Candidate Login</h1>
            <form onSubmit={event => handleLogin(event)}>
                <div>E-Mail <input type="email" value={email} onChange={event => setEmail(event.target.value)} /></div>
                <div>Password <input type="password" value={password} onChange={event => setPassword(event.target.value)} /></div>
                <button type="submit">Login</button>
            </form>
            <div>Email: {email}</div>
            <div>Password: {password}</div>
        </div>
    );
}

export default CandidateLoginComponent;