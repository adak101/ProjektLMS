/* eslint-disable react/prop-types */
import TaskContentItem from "./TaskContentItem";
import Loader from "../Helpers/Loader";
import { v4 as uuidv4 } from "uuid";

function TaskContent({ contents, idTask }) {
  if (!contents) {
    return <Loader />;
  }

  return (
    <>
      <div>
        {contents.map((cont) => {
          return (
            <TaskContentItem key={uuidv4()} element={cont} idTask={idTask} />
          );
        })}
      </div>
      <button className="translate-x-[650px] translate-y-[80px] bg-blue px-6 py-2 text-white rounded-lg">
        Wyślij zadania
      </button>
    </>
  );
}

export default TaskContent;
