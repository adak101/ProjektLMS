function FirstPeriod({ children }) {
  return (
    <div className="w-[30%] ">
      <h2 className="bg-darkBlueDiary text-white px-2 py-2 border-l-[1px] border-white">
        Oceny Cząstkowe 1 okres
      </h2>
      {children}
    </div>
  );
}

export default FirstPeriod;
