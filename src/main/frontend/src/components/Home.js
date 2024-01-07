import React from 'react';
import { useNavigate } from 'react-router-dom';

import '../css/home.css';

function LoginRegisterButtons() {
  const navigate = useNavigate();

  const handleLogin = async () => {
    navigate('/login');
  };

  const handleRegister = async () => {
    navigate('/register');
  };

  const handleEditor = () => {
    navigate('/code-editor');
  }

  return (
    <div className="button-container">
      <button className="pretty-button" onClick={handleLogin}>Login</button>
      <button className="pretty-button" onClick={handleRegister}>Register</button>
      <button className="pretty-button" onClick={handleEditor}>Editor</button>
    </div>
  );
}

export default LoginRegisterButtons;