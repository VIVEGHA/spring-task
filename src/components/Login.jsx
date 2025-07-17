import { useState} from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate()

    async function handleLogin(event){
        event.preventDefault();
        try{
            const response = await axios.post("http://localhost:3001/api/auth/login",{userName,password})
            console.log(response);

            const{token,username,roles} = response.data;

            localStorage.setItem("token",token);
            localStorage.setItem("username",username);

            const rolesArray = response.data.roles.split(',').map(role=>role.trim())
            localStorage.setItem("roles",JSON.stringify(rolesArray))

            alert("Login successful!");

            navigate("/dashboard")
        } catch (e){
            console.log("Login Error", e);
            alert("Invalid Cred")
        }
        console.log("Form Submitted");
    }
    return (
        <div>
            <h2>Login</h2>
            <div>
                <form onSubmit={handleLogin}>
                    <label htmlFor="userName">User Name</label>
                    <input
                        id="userName"
                        name="userName"
                        value={userName}
                        type="text"
                        onChange={(e) => setUserName(e.target.value)}
                    />
                    <br /> <br />
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        name="password"
                        value={password}
                        type="password"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <br />
                    <br />
                    <button type="submit">Login</button>
                </form>
            </div>
        </div>
    );
};
export default Login;