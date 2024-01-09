import { useContext } from "react";
import UserContext from "../Context/UserContext";
import LogOutComponent from "./LogOutComponent";
import Loader from "../Helpers/Loader";
function UserInfo() {
  const { userData } = useContext(UserContext);

  if (!userData) {
    return <Loader />;
  }

  return (
    <div className="flex items-center justify-between">
      <div className="flex items-center gap-x-5">
        <div className="w-[60px] h-auto ml-5 mt-5 rounded-lg ">
          <img
            src={`data:image/jpeg;base64,${userData.zdjecie}`}
            alt="person"
          />
        </div>
        <h3 className="translate-y-[13px] ">
          {userData.imie} {userData.nazwisko}
        </h3>
      </div>

      <LogOutComponent />
      <div className="w-10 h-10 flex items-center justify-center rounded-full  mr-5 cursor-pointer relative">
        <svg
          width="30px"
          height="30px"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <g clipPath="url(#clip0_15_159)">
            <rect width="24" height="24" fill="white" />
            <path
              d="M9.5 19C8.89555 19 7.01237 19 5.61714 19C4.87375 19 4.39116 18.2177 4.72361 17.5528L5.57771 15.8446C5.85542 15.2892 6 14.6774 6 14.0564C6 13.2867 6 12.1434 6 11C6 9 7 5 12 5C17 5 18 9 18 11C18 12.1434 18 13.2867 18 14.0564C18 14.6774 18.1446 15.2892 18.4223 15.8446L19.2764 17.5528C19.6088 18.2177 19.1253 19 18.382 19H14.5M9.5 19C9.5 21 10.5 22 12 22C13.5 22 14.5 21 14.5 19M9.5 19C11.0621 19 14.5 19 14.5 19"
              stroke="#000000"
              strokeLinejoin="round"
            />
            <path
              d="M12 5V3"
              stroke="#000000"
              strokeLinecap="round"
              strokeLinejoin="round"
            />
          </g>
          <defs>
            <clipPath id="clip0_15_159">
              <rect width="24" height="24" fill="white" />
            </clipPath>
          </defs>
        </svg>
        <span className="w-2 h-2 rounded-full bg-red absolute top-1 right-1"></span>
      </div>
    </div>
  );
}

export default UserInfo;
