/* eslint-disable react/prop-types */
import LabelFormComponent from "./LabelFormComponent";
import { createRef, useRefs, useState } from "react";

function CloseQuestion({ element }) {
  const refAnswer = [createRef(), createRef(), createRef(), createRef()];
  const [isDisabled, setIsDisabled] = useState(false);
  const [closeObj, setCloseObj] = useState({});

  let obj = {
    typ: "",
    odpowiedz: null,
    punkty: 0,
  };
  function handleSubmit(e) {
    e.preventDefault();
    if (!refAnswer) return;
    const textTab = [];
    refAnswer.forEach((answer, index) => {
      const inpt = answer.current.children[1];
      if (inpt.checked) {
        const textCont = answer.current.textContent.split(" ")[1];
        textTab.push(textCont);
      }
    });

    obj = {
      typ: element.typ,
      odpowiedz: textTab,
      punkty: 0,
    };
    setIsDisabled((flag) => !flag);
    setCloseObj(obj);
  }

  return (
    <div className="mt-10 border-t-[1px] pt-3 border-gray border-opacity-20">
      <h2 className="text-lg text-green py-5">Pytanie: </h2>
      <p className="mt-3 text-gray opacity-70">{element.pytanie}</p>
      <div className="mt-3">
        <h3 className="text-blue">Odpowiedzi</h3>
        <form className="grid grid-cols-2 mt-10 space-y-2">
          {element.odpowiedz.map((answer, index) => (
            <LabelFormComponent
              key={index}
              id={index}
              content={answer}
              refAnswer={refAnswer[index]}
              isDisabled={isDisabled}
            />
          ))}
        </form>
        <div className="flex justify-between items-center mt-2 pb-3">
          <button
            className="w-[80px] mt-5"
            onClick={handleSubmit}
            disabled={isDisabled}
          >
            Wyślij
          </button>
          <p>0/{element.punkty}</p>
        </div>
        {isDisabled && <p className="text-green">Rozwiazano zadanie</p>}
      </div>
    </div>
  );
}

export default CloseQuestion;
