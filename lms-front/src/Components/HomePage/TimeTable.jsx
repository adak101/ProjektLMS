import Subject from "./Subject";
import TitleSubject from "./TitleSubject";
import SubjectContainer from "./SubjectContainer";
import InfoTimeTable from "./InfoTimeTable";
import InfoTimeSubjectContainer from "./InfoTimeSubjectContainer";
function TimeTable() {
  return (
    <div className="ml-5 mt-10 w-[100%]">
      <TitleSubject />
      <InfoTimeSubjectContainer>
        <InfoTimeTable />
        <SubjectContainer>
          <Subject />
          <Subject />
          <Subject />
          <Subject />
          <Subject />
          <Subject />
        </SubjectContainer>
      </InfoTimeSubjectContainer>
    </div>
  );
}

export default TimeTable;
