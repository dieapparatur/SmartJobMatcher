import { createRoot } from 'react-dom/client'
import './index.css'
import CandidateLoginComponent from "./Candidate/CandidateLoginComponent.jsx";
import Test from "./Test.jsx";
import JobPosting from "./JobPosting.jsx";
import App from "./App.jsx";

createRoot(document.getElementById('root')).render(
    <>
        <App />
    </>
)
