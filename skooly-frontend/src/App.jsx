import {Link, Route, Routes} from "react-router-dom";
import Home from "./pages/Home.jsx";
import StaffPage from "./pages/StaffPage.jsx";
import Navbar from "./components/Navbar.jsx";

function App() {
  
  return (
    <>
      <Navbar>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/staff">Staff Management</Link></li>
          {/* Future modules like Teacher, Student can go here */}
        </ul>
      </Navbar>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/staff/*" element={<StaffPage/>}/>
      </Routes>
    </>
  )
}

export default App
