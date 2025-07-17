import { Link } from "react-router-dom";

const Home = () => {
    return (
        <>
            <header>
                <div>EMS</div>
                <nav>
                    <Link to="/login" >Login</Link>
                    <Link to="/register">Register</Link>
                </nav>
            </header>

            <main>
                <h1>Welcome to EMS System</h1>
                <p>Your centralized Employee Management platform.</p>
            </main>

            <footer>
                <p>© 2025 EMS. All rights reserved.</p>
            </footer>
        </>
    );
};


export default Home;