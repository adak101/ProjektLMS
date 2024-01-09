import TestContainer from "../Components/Test/TestContainer";
import UserInfo from "../Components/HomePage/UserInfo";
import InfoTab from "../Components/HomePage/InfoTab";
import TestContainerInfo from "../Components/Test/TestContainerInfo";
import TitleContainer from "../Components/Test/TitleContainer";
import TestItem from "../Components/Test/TestItem";

function Test() {
  return (
    <TestContainer>
      <UserInfo />
      <InfoTab title="Tests" />
      <TitleContainer />
      <TestContainerInfo>
        <TestItem />
        <TestItem />
        <TestItem />
        <TestItem />
        <TestItem />
        <TestItem />
        <TestItem />
        <TestItem />
      </TestContainerInfo>
    </TestContainer>
  );
}

export default Test;
