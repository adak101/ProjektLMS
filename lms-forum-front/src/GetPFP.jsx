import React, { useEffect, useState } from 'react';
const token = JSON.parse(localStorage.getItem("token"))

const GetPFP = ({ userId }) => {
  const [imageSrc, setImageSrc] = useState(null);

  useEffect(() => {
    const fetchUserPFP = async () => {
      try {
        const response = await fetch(`/api/uzytkownik/${userId}`, {
          headers: {Authorization: `Bearer ${token}`,},
        });

        if (!response.ok) {
          throw new Error('Failed to fetch user');
        }

        const data = await response.json();
        if (data.zdjecie) {
            setImageSrc(`data:image/png;base64,${data.zdjecie}`);
        }
      } 
      catch (error) {
        console.error(error.message);
      }
    };
    fetchUserPFP();
  }, [userId]);
  
  return(
    imageSrc ? (
        <img className='user-icon' src={imageSrc} alt="User Image" />
      ) : (
        <p>No image available</p>
      )
  );
};

export default GetPFP;