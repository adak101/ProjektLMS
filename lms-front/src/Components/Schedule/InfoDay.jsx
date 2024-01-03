import SubjectInfo from "./SubjectInfo";
import TitleInfoDay from "./TitleInfoDay";
function InfoDay() {
  return (
    <div className="bg-lightSlate mb-5 mt-5 rounded-lg pb-10">
      <TitleInfoDay />
      <div className="bg-white w-[90%] mx-auto rounded-lg overflow-y-scroll scrollbar-thin scrollbar-thumb-green scrollbar-track-white max-h-[400px]">
        <SubjectInfo />
        <SubjectInfo />
        <SubjectInfo />
        <SubjectInfo />
        <SubjectInfo />
        <SubjectInfo />
      </div>
    </div>
  );
}

export default InfoDay;
