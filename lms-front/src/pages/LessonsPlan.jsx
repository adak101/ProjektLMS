import SchedulePage from "../Components/Schedule/SchedulePage";
import UserInfo from "../Components/HomePage/UserInfo";
import InfoTab from "../Components/HomePage/InfoTab";
import ScheduleContainer from "../Components/Schedule/ScheduleContainer";
import DayOfPlan from "../Components/Schedule/DayOfPlan";
function LessonsPlan() {
  return (
    <SchedulePage>
      <UserInfo />
      <InfoTab title="Schedule" />
      <ScheduleContainer>
        <DayOfPlan />
        <DayOfPlan />
        <DayOfPlan />
        <DayOfPlan />
        <DayOfPlan />
      </ScheduleContainer>
    </SchedulePage>
  );
}

export default LessonsPlan;
