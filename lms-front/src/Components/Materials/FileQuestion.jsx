/* eslint-disable react/prop-types */
import { useRef, useState } from "react";

const fileToBase64 = (file, callback) => {
  const reader = new FileReader();
  reader.onloadend = () => {
    callback(reader.result);
  };
  reader.readAsDataURL(file);
};

function FileQuestion({ element }) {
  const fileRef = useRef(null);
  const [fileObj, setFileObj] = useState({});
  const [isDisabled, setIsDisabled] = useState(false);
  let obj = null;

  function handleSubmit(e) {
    e.preventDefault();
    const file = fileRef.current.files[0];
    if (!file) return;
    fileToBase64(file, (base64Data) => {
      const fileBase = base64Data.split(",")[1];

      obj = {
        typ: element.typ,
        odpowiedz: fileBase,
        punkty: 0,
      };
      setFileObj(obj);
      setIsDisabled((flag) => !flag);
    });
  }
  return (
    <div className="mt-10 border-t-[1px] pt-3 border-gray border-opacity-20">
      <h2 className="text-lg text-green py-5">Pytanie: </h2>
      <p className="mt-3 text-gray opacity-70">{element.pytanie}</p>
      <div className="mt-3">
        <h3 className="text-blue">Odpowiedzi</h3>
        <form className="flex flex-col mt-5">
          <label className="flex flex-col gap-y-2">
            Wybierz plik:
            <input type="file" ref={fileRef} disabled={isDisabled} />
          </label>
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

export default FileQuestion;
