import { useState } from "react";

function TrueFalseQuestionAnswer({ points, element }) {
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
  );
}

export default TrueFalseQuestionAnswer;
