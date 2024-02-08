import React, { useState } from 'react';
import axios from 'axios';
const token = JSON.parse(localStorage.getItem("token"))

const MakeThread = () => {
  const [postData, setPostData] = useState({
    idPrzedmiotu: 1,
    idUzytkownika: JSON.parse(localStorage.getItem("userId")),
    temat: '',
    tresc: '',
    dataWpis: new Date().toISOString().slice(0, 19)
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPostData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/api/forum/wpisy', postData, {
        headers: {
          Authorization: `Bearer ${token}`, 
        },
      });

      console.log('Wpis dodany. Link do nowego wpisu:', response.data);
    } catch (error) {
      console.error('Błąd podczas dodawania wpisu:', error);
    }
    window.location.reload(false);
  };

  return (
    <form className="thread-form" onSubmit={handleSubmit}>
      <label>
        Temat:
        <input type="text" name="temat" value={postData.temat} onChange={handleChange} />
      </label>
      <br />
      <label>
        Treść:
        <textarea name="tresc" value={postData.tresc} onChange={handleChange} style={{ width: "800px", height: "200px"}}/>
      </label>
      <br />
      <button type="submit">Dodaj wpis</button>
    </form>
  );
};

export default MakeThread;