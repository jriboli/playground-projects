import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav>
      <ul>
        <li><Link to="/login">Login</Link></li>
        <li><Link to="/admin/dashboard">Admin Dashboard</Link></li>
        <li><Link to="/cheatsheets">Cheat Sheets</Link></li>
        <li><Link to="/flashcards">Flashcards</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;
