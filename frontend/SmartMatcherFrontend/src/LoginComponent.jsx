import {useState} from "react";


function LoginComponent() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function handleLogin(event) {
        event.preventDefault();
        console.log("Trying to handle Login with following credentials.\nE-Mail: " + email + ". Password: " + password);

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
            <div>Password: {password}</div>
        </div>

    );
}

export default LoginComponent;