
import React, { useReducer } from 'react';
import './App.css'; // Import your CSS file if it's not already imported in your component
import ForumThreads from './ThreadRoutes';
import { BrowserRouter as BrowserRouter } from 'react-router-dom';
const userToken = JSON.parse(localStorage.getItem("token"))

function App() {
  return (
    <div>
      <div className="banner">
        <p className="banner-text">LMS Forum</p>
      </div>
      <div>
        <BrowserRouter>
          <ForumThreads token={userToken} />
        </BrowserRouter>
      </div>
    </div>
  );
}

export default App;