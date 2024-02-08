import React from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    let navigate = useNavigate();
  const ClearLogin = () => {
    localStorage.clear();
    navigate("/login")
  };

  return (
    <button className='logout-button' onClick={ClearLogin}>
      Wyloguj
    </button>
  );
};

export default Logout;