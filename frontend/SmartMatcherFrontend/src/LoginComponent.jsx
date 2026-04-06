import {useState} from "react";
import {login} from "./userService.js"


function LoginComponent() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin(event) {
        event.preventDefault();
        console.log("Trying to handle Login with following credentials.\nE-Mail: " + email + ". Password: " + password);
        const loginResponse = await login(email, password);
        console.log("Response from backend (likely JWT): " + loginResponse);
        localStorage.setItem('accessToken', loginResponse);
        //const token = localStorage.getItem('accessToken');
    }


    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={event => handleLogin(event)}>
                <div>E-Mail <input type="email" value={email} onChange={event => setEmail(event.target.value)} /></div>
                <div>Password <input type="password" value={password} onChange={event => setPassword(event.target.value)} /></div>
                <button type="submit">Login</button>
            </form>
            <div>Email: {email}</div>
            <div>Password: {password}</div>s
        </div>
    );
}

export default LoginComponent;