/* eslint-disable react/prop-types */
import { useState } from "react";
function TrueFalseQuestion({ element }) {
  const [selectedOption, setSelectedOption] = useState(null);
  const [trueFalseObj, setTrueFalseObj] = useState({});
  const [isDisabled, setIsDisabled] = useState(false);
  let obj = {
    typ: "",
    odpowiedz: "",
    punkty: 0,
  };

  function handleSubmit(e) {
    e.preventDefault();
    obj = {
      typ: element.typ,
      odpowiedz: selectedOption,
      punkty: 0,
    };
    setTrueFalseObj(obj);
    setIsDisabled((flag) => !flag);
  }

  return (
    <div className="mt-10 border-t-[1px] pt-3 border-gray border-opacity-20">
      <h2 className="text-lg text-green py-5">Pytanie: </h2>
      <p className="mt-3 text-gray opacity-70">{element.pytanie}</p>
      <div className="mt-3">
        <h3 className="text-blue">Odpowiedzi</h3>
        <form className="flex flex-col mt-5">
          <div className="flex gap-x-10">
            <label className="flex gap-x-3 ">
              True
              <input
                name="myRadio"
                type="radio"
                onChange={(e) => setSelectedOption(e.target.value)}
                disabled={isDisabled}
              />
            </label>
            <label className="flex gap-x-3 ">
              False
              <input
                name="myRadio"
                type="radio"
                onChange={(e) => selectedOption(e.target.value)}
                disabled={isDisabled}
              />
            </label>
          </div>
          <div className="flex justify-between">
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
        </form>
      </div>
    </div>
  );
}

export default TrueFalseQuestion;
