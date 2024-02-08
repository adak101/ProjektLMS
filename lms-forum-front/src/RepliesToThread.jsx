// get all replies 
import React, { useEffect, useState } from 'react';
import GetUsername from './Username';
import GetPFP from './GetPFP';
const token = JSON.parse(localStorage.getItem("token"))

const RepliesToThread = ({ threadId }) => {
  const [replies, setReplies] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchReplies = async () => {
      try {
        const response = await fetch('/api/forum/odpowiedzi/all', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch replies');
        }

        const data = await response.json();
        setReplies(data._embedded.ForumOdpowiedzi);
        setLoading(false);
      } 
      catch (error) {
        console.error(error.message);
        setLoading(false);
      }
    };

    fetchReplies();
  }, [threadId]);


  const idWpisReplies = replies.filter(reply => reply.idWpis === threadId)
  return (
    <div>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {idWpisReplies.map((reply) => (
            <li className='thread-comment' key={reply.idWpis}>
              <div className='reply-flex'>
                <GetPFP userId={reply.idUzytk}/>
                <div className='reply-column'>
                <p><strong><GetUsername userId={reply.idUzytk}/></strong> {reply.dataWpis.slice(0, 10)} {reply.dataWpis.slice(11, 16)} </p>
                <p className='thread-comment-text'>{reply.tresc}</p>
                </div>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );  
};

export default RepliesToThread;