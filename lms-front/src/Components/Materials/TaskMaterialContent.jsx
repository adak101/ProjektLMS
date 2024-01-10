import { useState, useEffect } from "react";

import TaskContent from "./TaskContent";
function TaskMaterialContent({ task }) {
  const [contents, setContents] = useState(null);
  const [idTask, setIdTask] = useState(null);

  useEffect(
    function () {
      if (!task) return;
      let linkURL = `api/${task.split("/api/")[1]}`;
      const token = JSON.parse(localStorage.getItem("token"));
      const headers = {
        Authorization: `Bearer ${token}`,
      };

      const fetchData = {
        method: "GET",
        headers: headers,
      };
      const getData = async function () {
        try {
          const res = await fetch(`/${linkURL}`, fetchData);
          const data = await res.json();
          setContents(JSON.parse(data.tresc));
          setIdTask(data.id);
        } catch (err) {
          console.log(err);
        }
      };
      getData();
    },
    [task]
  );

  return (
    <div className="mt-5 border-b border-gray border-opacity-20 pb-4 text-gray">
      <>
        <span className="text-xl text-green">Zadanie do wykonania: </span>{" "}
        {contents ? (
          <TaskContent contents={contents} idTask={idTask} />
        ) : (
          "Brak zadania"
        )}
      </>
    </div>
  );
}

export default TaskMaterialContent;
