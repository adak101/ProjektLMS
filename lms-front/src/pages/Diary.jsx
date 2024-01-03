import DiaryPage from "../Components/Diary/DiaryPage";
import UserInfo from "../Components/HomePage/UserInfo";
import InfoTab from "../Components/HomePage/InfoTab";
import DiaryContainer from "../Components/Diary/DiaryContainer";
import SubjectDiary from "../Components/Diary/SubjectDiary";
import SingleSubject from "../Components/Diary/singleSubject";
import FirstPeriod from "../Components/Diary/FirstPeriod";
import RatingPeriod from "../Components/Diary/RatingPeriod";
import FinalGradeDesc from "../Components/Diary/FinalGradeDesc";
import FinalGrade from "../Components/Diary/FinalGrade";
function Diary() {
  return (
    <DiaryPage>
      <UserInfo />
      <InfoTab title="Diary" />
      <DiaryContainer>
        <SubjectDiary>
          <SingleSubject />
          <SingleSubject bgColor="lightSlate" />
          <SingleSubject />
          <SingleSubject bgColor="lightSlate" />
          <SingleSubject />
          <SingleSubject bgColor="lightSlate" />
        </SubjectDiary>
        <FirstPeriod>
          <RatingPeriod />
          <RatingPeriod bgColor="lightSlate" />
          <RatingPeriod />
          <RatingPeriod bgColor="lightSlate" />
          <RatingPeriod />
          <RatingPeriod bgColor="lightSlate" />
        </FirstPeriod>
        <FinalGradeDesc>
          <FinalGrade />
          <FinalGrade bgColor="lightSlate" />
          <FinalGrade />
          <FinalGrade bgColor="lightSlate" />
          <FinalGrade />
          <FinalGrade bgColor="lightSlate" />
        </FinalGradeDesc>
        <FirstPeriod>
          <RatingPeriod />
          <RatingPeriod bgColor="lightSlate" />
          <RatingPeriod />
          <RatingPeriod bgColor="lightSlate" />
          <RatingPeriod />
          <RatingPeriod bgColor="lightSlate" />
        </FirstPeriod>
        <FinalGradeDesc>
          <FinalGrade />
          <FinalGrade bgColor="lightSlate" />
          <FinalGrade />
          <FinalGrade bgColor="lightSlate" />
          <FinalGrade />
          <FinalGrade bgColor="lightSlate" />
        </FinalGradeDesc>
      </DiaryContainer>
    </DiaryPage>
  );
}

export default Diary;
