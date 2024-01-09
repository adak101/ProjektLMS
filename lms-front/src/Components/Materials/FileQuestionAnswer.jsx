import { useRef, useState } from "react";

const fileToBase64 = (file, callback) => {
  const reader = new FileReader();
  reader.onloadend = () => {
    callback(reader.result);
  };
  reader.readAsDataURL(file);
};

function FileQuestionAnswer({ points, element }) {
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
  );
}

export default FileQuestionAnswer;
