import { useEffect, useState, useReducer } from "react";

function apiReducer(state, action) {
  switch (action.type) {
    case "fetching":
      return { ...state, isLoading: true };
    case "success":
      return {
        data: action.payload,
        isLoading: false,
        error: null,
        hasData: true,
      };
    case "error":
      return {
        data: null,
        isLoading: false,
        error: action.payload,
        hasData: false,
      };
    default:
      return state;
  }
}

async function doFetch(path) {
  const token = JSON.parse(localStorage.getItem("token"));

  const headers = {
    Authorization: `Bearer ${token}`,
  };

  const fetchData = {
    method: "GET",
    headers: headers,
  };
  const res = await fetch(path, fetchData);
  if (res.ok) {
    return res.json();
  }
  throw await res.json();
}

export default function useApi(path) {
  const [response, dispatch] = useReducer(apiReducer, {
    data: null,
    isLoading: false,
    error: null,
    hasData: false,
  });
  const [isMounted, setIsMounted] = useState(true);

  useEffect(
    function () {
      setIsMounted(true);
      const getData = async function () {
        dispatch({ type: "fetching" });
        try {
          const data = await doFetch(path);
          if (isMounted) {
            dispatch({ type: "success", payload: data });
          }
        } catch (error) {
          if (isMounted) dispatch({ type: "error", payload: "error Data" });
        }
      };
      getData();
      return () => {
        setIsMounted(false);
      };
    },
    [path, isMounted]
  );

  return response;
}

// import { useEffect, useState, useReducer } from "react";

// const token =
//   "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzLsWbd2llcmN6IiwiaW1pZSI6IlN6eW1vbiIsIm5hendpc2tvIjoixZp3aWVyY3oiLCJlbWFpbCI6InN6eW1vbi5zd2llcmN6QGV4YW1wbGUuY29tIiwiZXhwIjoxOTE5Nzg2NzM0fQ.RIzCpGsmXBmK5E7RrHfc9_EUraG67RtDVvONW7ToH-k";

// const headers = {
//   Authorization: `Bearer ${token}`,
// };

// const fetchData = {
//   method: "GET",
//   headers: headers,
// };

// function apiReducer(state, action) {
//   switch (action.type) {
//     case "fetching":
//       return { ...state, isLoading: true };
//     case "success":
//       return {
//         data: action.payload,
//         isLoading: false,
//         error: null,
//         hasData: true,
//       };
//     case "error":
//       return {
//         data: null,
//         isLoading: false,
//         error: action.payload,
//         hasData: false,
//       };
//     default:
//       return state;
//   }
// }

// async function doFetch(path) {
//   const res = await fetch(path, fetchData);
//   if (res.ok) {
//     return res.json();
//   }
//   throw await res.json();
// }

// export default function useApi(path) {
//   const [response, dispatch] = useReducer(apiReducer, {
//     data: null,
//     isLoading: false,
//     error: null,
//     hasData: false,
//   });
//   const [isMounted, setIsMounted] = useState(true);

//   useEffect(
//     function () {
//       setIsMounted(true);
//       const getData = async function () {
//         dispatch({ type: "fetching" });
//         try {
//           const data = await doFetch(path);
//           if (isMounted) {
//             dispatch({ type: "success", payload: data });
//           }
//         } catch (error) {
//           if (isMounted) dispatch({ type: "error", payload: "error Data" });
//         }
//       };
//       getData();
//       return () => {
//         setIsMounted(false);
//       };
//     },
//     [path, isMounted]
//   );

//   return response;
// }
