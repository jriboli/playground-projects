import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './components/Login';
import AdminDashboard from './components/AdminDashboard';
import ErrorBoundary from './components/ErrorBoundary';

function App() {
  const [token, setToken] = useState(localStorage.getItem("token") || null);

  return (
    <ErrorBoundary>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/login" element={<Login setToken={setToken} />} />
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
          {/* Add routes for Flashcards and Cheat Sheets */}
        </Routes>
      </Router>
    </ErrorBoundary>
  );
}

export default App;
