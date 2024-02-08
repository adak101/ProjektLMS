import React, { useEffect, useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import ThreadView from './ThreadView';
import ThreadLinks from './ThreadLinks';
import UserContextProvider from "./Context/UserContextProvider";
import StartPageLayout from "./LoginAndRegistration/StartPageLayout";
const token = JSON.parse(localStorage.getItem("token"))

const ForumThreads = () => {
  const [threads, setThreads] = useState([]);

  useEffect(() => {
    const fetchForumThreads = async () => {
      try {
        const response = await fetch('/api/forum/wpisy/all', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch forum threads');
        }

        const data = await response.json();
        setThreads(data._embedded.ForumWpisy);
      } 
      catch (error) {
        console.error(error.message);
      }
    };

    fetchForumThreads();
  });  

  return (
    <div>
      <UserContextProvider>
        <Routes>
          <Route index element={<StartPageLayout />} />
          <Route path="/login" element={<StartPageLayout />} />
          <Route path="/threads" element={<ThreadLinks token={token}/>}/>
          {threads.map((thread) => (
            <Route 
              key={thread.idWpis} 
              path={`/threads/${thread.idWpis}`}
              element={<ThreadView idWpis={thread.idWpis}/>}/>
          ))}
        </Routes>
      </UserContextProvider>
    </div>
  );  
};

export default ForumThreads;