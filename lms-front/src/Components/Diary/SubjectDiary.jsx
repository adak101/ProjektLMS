function SubjectDiary({ children }) {
  return (
    <div className="w-[20%] ">
      <h1 className="text-white bg-darkBlueDiary px-2 py-2">Przedmiot</h1>
      {children}
    </div>
  );
}

export default SubjectDiary;
