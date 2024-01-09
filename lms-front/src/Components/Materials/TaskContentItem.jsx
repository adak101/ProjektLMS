import CloseQuestionAnswer from "./CloseQuestionAnswer";
import FileQuestionAnswer from "./FileQuestionAnswer";
import OpenQuestionAnswer from "./OpenQuestionAnswer";
import TrueFalseQuestionAnswer from "./TrueFalseQuestionAnswer";

function TaskContentItem({ element }) {
  const type = element.typ;

  return (
    <div className="mt-10 border-t-[1px] pt-3 border-gray border-opacity-20">
      <h2 className="text-lg text-green py-5">Pytanie: </h2>
      <p className="mt-3 text-gray opacity-70">{element.pytanie}</p>
      <div className="mt-3">
        <h3 className="text-blue">Odpowiedzi</h3>
        {type === "OTWARTE" && <OpenQuestionAnswer element={element} />}
        {type === "PRAWDA_FALSZ" && (
          <TrueFalseQuestionAnswer element={element} />
        )}
        {type === "PLIK" && <FileQuestionAnswer element={element} />}
        {type === "ZAMKNIETE" && <CloseQuestionAnswer element={element} />}
      </div>
    </div>
  );
}

export default TaskContentItem;
