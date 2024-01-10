/* eslint-disable react/prop-types */

import Loader from "../Helpers/Loader";
import { v4 as uuidv4 } from "uuid";
import CloseQuestion from "./CloseQuestion";
import OpenQuestion from "./OpenQuestion";
import TrueFalseQuestion from "./TrueFalseQuestion";
import FileQuestion from "./FileQuestion";
import { useState } from "react";
function TaskContent({ contents, idTask }) {
  const [sentData, setSentData] = useState(false);

  if (!contents) {
    return <Loader />;
  }

  return (
    <>
      <div>
        {contents.map((cont) => {
          if (cont.typ === "OTWARTE")
            return (
              <OpenQuestion
                key={uuidv4()}
                element={cont}
                setSentDatasentData={setSentData}
              />
            );
          if (cont.typ === "ZAMKNIETE") {
            return <CloseQuestion key={uuidv4()} element={cont} />;
          }
          if (cont.typ === "PRAWDA_FALSZ") {
            return <TrueFalseQuestion key={uuidv4()} element={cont} />;
          }
          if (cont.typ === "PLIK") {
            return <FileQuestion key={uuidv4()} element={cont} />;
          }
        })}
      </div>
      <button className="translate-x-[650px] translate-y-[80px] bg-blue px-6 py-2 text-white rounded-lg">
        Wyślij zadania
      </button>
    </>
  );
}

export default TaskContent;
