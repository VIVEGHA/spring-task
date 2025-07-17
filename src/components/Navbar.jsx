// src/components/Navbar.jsx
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="nav-left">Employee Management System</div>
      <div className="nav-right">
        <Link to="/signup">Sign Up</Link>
        <Link to="/login">Login</Link>
        <Link to="/">Logout</Link>
      </div>
    </nav>
  );
};

export default Navbar;
