import {Link} from "react-router-dom";

const Header = ()=>{
    return(
        <header className = "header">
            <div className = "logo">EMS</div>
            <nav className = "nav-links">
                <Link to = "/register">Register</Link>
                <Link to = "/login">Login</Link>
                <Link to = "/employee">Employee</Link>
            </nav>
        </header>
    )
}

export default Header;