import React, { useEffect, useState } from 'react';
import RepliesToThread from './RepliesToThread';
import GetUsername from './Username';
import GetPFP from './GetPFP';
import MakeReply from './AddReply';
const token = JSON.parse(localStorage.getItem("token"))

const ThreadView = ({ idWpis }) => {
  const [thread, setThread] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchForumThreads = async () => {
      try {
        const response = await fetch(`/api/forum/wpisy/${idWpis}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch forum threads');
        }

        const data = await response.json();
        setThread(data);
        setLoading(false);
      } 
      catch (error) {
        console.error(error.message);
        setLoading(false);
      }
      console.log(thread.idWpis)
    };

    fetchForumThreads();
  }, [idWpis]);

  return (
    <div>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div>
          <div className='thread-view'>
            <h3> <strong>{thread.temat}</strong></h3>
            <div className='thread-comment'>
              <div className='reply-flex'>
                <GetPFP userId={thread.idUzytk}/>
                <div className='reply-column'>
                  <p><strong><GetUsername userId={thread.idUzytk}/></strong> {thread.dataWpis.slice(0, 10)} {thread.dataWpis.slice(11, 16)}</p>
                  <p className='thread-comment-text'>{thread.tresc}</p>
                </div>
              </div>
            </div>
            <RepliesToThread threadId={thread.idWpis}/>
          </div>
          <MakeReply threadId={thread.idWpis}/>
        </div>
      )}
    </div>
  );  
};

export default ThreadView;