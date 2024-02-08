import React, { useEffect, useState } from 'react';
const token = JSON.parse(localStorage.getItem("token"))

const Username = ({ userId }) => {
  const [user, setUser] = useState([]);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await fetch(`/api/uzytkownik/${userId}`, {
          headers: {Authorization: `Bearer ${token}`,},
        });

        if (!response.ok) {
          throw new Error('Failed to fetch user');
        }
        const data = await response.json();
        setUser(data);
      } 
      catch (error) {
        console.error(error.message);
      }
    };

    fetchUser();
  }, [userId]);
  
  return(
      String(user.imie + ' ' + user.nazwisko)
  );
};

export default Username;