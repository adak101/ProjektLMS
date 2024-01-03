import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppLayout from "./pages/AppLayout";
import Home from "./pages/Home";
import LessonsPlan from "./pages/LessonsPlan";
import Diary from "./pages/Diary";
import Materials from "./pages/Materials";
import SingleMaterial from "./Components/Materials/SingleMaterial";
import Test from "./pages/Test";
import Profile from "./pages/Profile";
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppLayout />}>
          <Route index element={<Home />} />
          <Route path="home" element={<Home />} />
          <Route path="lessonsPlan" element={<LessonsPlan />} />
          <Route path="diary" element={<Diary />} />
          <Route path="materials" element={<Materials />}>
            <Route path="Przedmiot" element={<SingleMaterial />} />
          </Route>
          <Route path="test" element={<Test />} />
          <Route path="profile" element={<Profile />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
