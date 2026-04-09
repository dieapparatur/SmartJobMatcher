import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import CandidateLoginComponent from "./Candidate/CandidateLoginComponent.jsx";
import CompanyLoginComponent from "./Company/CompanyLoginComponent.jsx";
import LoginDecider from "./LoginDecider.jsx";

function App() {

  return (
    <>
        <BrowserRouter>
            <Routes>
                <Route path="/decideLogin" element={<LoginDecider />} />
                <Route path="/login/candidate" element={<CandidateLoginComponent />} />
                <Route path="/login/company" element={<CompanyLoginComponent />} />
            </Routes>
        </BrowserRouter>
    </>
  )
}

export default App
