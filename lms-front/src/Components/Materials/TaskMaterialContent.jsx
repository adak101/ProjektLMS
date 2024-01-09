import useApi from "./Hooks/useApi";
import Loader from "../Helpers/Loader";
import TaskContent from "./TaskContent";
function TaskMaterialContent({ task }) {
  let linkUrl = null;
  let contents = null;
  let idTask = null;
  if (task) {
    linkUrl = `api/${task.split("/api/")[1]}`;
  }
  const { data, isLoading, error } = useApi(`/${linkUrl}`);
  if (data) {
    contents = JSON.parse(data.tresc);
    idTask = data.id;
  }

  return (
    <div className="mt-5 border-b border-gray border-opacity-20 pb-4 text-gray">
      {" "}
      {isLoading ? (
        <Loader />
      ) : (
        <>
          <span className="text-xl text-green">Zadanie do wykonania: </span>{" "}
          {task ? (
            <TaskContent contents={contents} idTask={idTask} />
          ) : (
            "Brak zadania"
          )}
        </>
      )}
    </div>
  );
}

export default TaskMaterialContent;
