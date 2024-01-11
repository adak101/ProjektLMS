/* eslint-disable react/prop-types */

import Loader from "../Helpers/Loader";
import { v4 as uuidv4 } from "uuid";
import CloseQuestion from "./CloseQuestion";
import OpenQuestion from "./OpenQuestion";
import TrueFalseQuestion from "./TrueFalseQuestion";
import FileQuestion from "./FileQuestion";
import { useState, useCallback, useRef, useMemo, useEffect } from "react";
function TaskContent({ contents, idTask }) {
  const [sentData, setSentData] = useState(false);
  const [finalData, setFinalData] = useState([]);

  function handleSubmit(e) {
    e.preventDefault();
    setSentData(true);
  }
  useEffect(
    function () {
      if (finalData.length !== 4) return;
      console.log(finalData);
    },
    [finalData]
  );
  if (!contents) {
    return <Loader />;
  }

  return (
    <>
      <div>
        {contents.map((cont, index) => {
          if (cont.typ === "OTWARTE")
            return (
              <OpenQuestion
                key={index}
                element={cont}
                sentData={sentData}
                setFinalData={setFinalData}
                setSentData={setSentData}
              />
            );
          if (cont.typ === "ZAMKNIETE") {
            return (
              <CloseQuestion
                key={index}
                element={cont}
                sentData={sentData}
                setFinalData={setFinalData}
              />
            );
          }
          if (cont.typ === "PRAWDA_FALSZ") {
            return (
              <TrueFalseQuestion
                key={index}
                element={cont}
                sentData={sentData}
                setFinalData={setFinalData}
                setSentData={setSentData}
              />
            );
          }
          if (cont.typ === "PLIK") {
            return (
              <FileQuestion
                key={index}
                element={cont}
                sentData={sentData}
                setFinalData={setFinalData}
              />
            );
          }
        })}
      </div>
      <button
        className="translate-x-[650px] translate-y-[80px] bg-blue px-6 py-2 text-white rounded-lg"
        onClick={handleSubmit}
      >
        Wyślij zadania
      </button>
    </>
  );
}

export default TaskContent;
