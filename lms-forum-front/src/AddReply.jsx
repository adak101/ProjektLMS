import React, { useState } from 'react';
import axios from 'axios';
const token = JSON.parse(localStorage.getItem("token"))

const MakeReply = ({ threadId }) => {
  const id = threadId;
  const [postData, setReplyData] = useState({
    idUzytkownika: localStorage.getItem("userId"),
    idWpisu: id,
    tresc: '',
    dataWpis: new Date().toISOString().slice(0, 19)
  });
  console.log(threadId)

  const handleChange = (e) => {
    const { name, value } = e.target;
    setReplyData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/api/forum/odpowiedzi', postData, {
        headers: {
          Authorization: `Bearer ${token}`, 
        },
      });
      
    } catch (error) {
      console.error('Błąd podczas dodawania odpowiedzi:', error);
    }
    window.location.reload(false);
  };

  return (
    <form className="thread-form" onSubmit={handleSubmit}>
      <label>
        Treść:
        <textarea name="tresc" value={postData.tresc} onChange={handleChange} style={{ width: "800px", height: "200px"}}/>
      </label>
      <br />
      <button type="submit">Dodaj odpowiedź</button>
    </form>
  );
};

export default MakeReply;