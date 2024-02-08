import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Username from './Username';
import MakeThread from './MakeThread';
import Logout from './Logout';
const token = JSON.parse(localStorage.getItem("token"))
const userId = JSON.parse(localStorage.getItem("userId"))

const ThreadLinks = () => {
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
  // const filteredthreads = threads.filter(thread => thread.idWpis === 17);
  const sortnewthreads = threads.slice(0).reverse().map(element => {return element;});
  return (
    <div>
        <div className='user-bar'>
          <p className='user-bar-element'> Witaj, <strong><Username userId={userId}/></strong></p>
          <p className='user-bar-element'><Logout/></p>
        </div>
        <ol className='thread-list'>
          {sortnewthreads.map((thread) => (
            <li className='thread-header' key={thread.idWpis}>
                <img className="forum-icon" src="../forum-icon.webp" alt="Forum Icon"/>
                <div className='thread-text'>
                <Link to={`/threads/${thread.idWpis}`}><strong>{thread.temat}</strong></Link>
                <p className='tight'>Rozpoczęte przez: <strong><Username userId={thread.idUzytk} /></strong></p>
                <p className='tight'>Data rozpoczęcia: <strong>{thread.dataWpis.slice(0, 10)}</strong> o godzinie <strong>{thread.dataWpis.slice(11, 16)}</strong></p>
                </div>
            </li>
          ))}
      </ol>
      <MakeThread />
    </div>
  );  
};

export default ThreadLinks;