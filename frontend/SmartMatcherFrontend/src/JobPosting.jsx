import {useState} from "react";


function JobPosting() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [location, setLocation] = useState("");
    const [employmentType, setEmploymentType] = useState("");
    const [field, setField] = useState("");
    const [salary, setSalary] = useState("");
    const [picture, setPicture] = useState("");

    async function handleJobPosting(event) {
        event.preventDefault();

    }


    return (
        <div>
            <h1>Post a job</h1>
            <form onSubmit={event => handleJobPosting(event)}>
                <div>Title <input type="text" value={title} onChange={event => setTitle (event.target.value)} /></div>
                <div>Description <input type="text" value={description} onChange={event => setDescription() (event.target.value)} /></div>
                <div>Location <input type="text" value={location} onChange={event => setLocation (event.target.value)} /></div>
                <div>Employment Type <input type="text" value={employmentType} onChange={event => setEmploymentType() (event.target.value)} /></div>
                <div>Field <input type="text" value={field} onChange={event => setField() (event.target.value)} /></div>
                <div>Salary <input type="text" value={salary} onChange={event => setSalary() (event.target.value)} /></div>
                <div>Picture <input type="text" value={picture} onChange={event => setPicture() (event.target.value)} /></div>
                <button type="submit">Save Job</button>
            </form>
        </div>
    );
}

export default JobPosting;