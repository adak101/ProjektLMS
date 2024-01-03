function TitleSubject() {
  return (
    <div className="flex justify-between ]">
      <h4 className="text-2xl">TimeTable</h4>
      <div className="flex gap-x-5 cursor-pointer">
        <p className="base-timeTable active-timeTable">Pon</p>
        <p className="base-timeTable">Wt</p>
        <p className="base-timeTable">Sr</p>
      </div>
    </div>
  );
}

export default TitleSubject;
