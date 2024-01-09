import { useState } from "react";

function OpenQuestionAnswer({ points, element }) {
  const [textValue, setTextValue] = useState("");
  const [userObj, setUserObj] = useState({});
  const [isDisabled, setIsDisabled] = useState(false);
  let obj = {
    typ: "",
    odpowiedz: "",
    punkty: 0,
  };
  function handleSentData(e) {
    e.preventDefault();
    obj = {
      typ: element.typ,
      odpowiedz: textValue,
      punkty: 0,
    };
    setIsDisabled((flag) => !flag);
    setUserObj(obj);
  }

  return (
    <form className="mt-5 flex flex-col gap-y-5">
      <textarea
        className="w-[800px] border-[1px] border-blue rounded-lg h-[250px] pt-5 pl-2 focus:outline-none"
        value={textValue}
        onChange={(e) => setTextValue(e.target.value)}
        disabled={isDisabled}
      ></textarea>
      <div className="flex justify-between">
        <button
          className="w-[80px]"
          onClick={handleSentData}
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

export default OpenQuestionAnswer;
