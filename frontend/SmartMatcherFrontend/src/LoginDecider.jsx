import {useNavigate} from "react-router-dom";


function LoginDecider() {

    const navigation = useNavigate();

    function toCandidateLogin(event) {
        event.preventDefault();
        navigation("/login/candidate");
    }

    function toCompanyLogin(event) {
        event.preventDefault();
        navigation("/login/company");
    }


    return (
        <div>
            <h1>What do you want to login as?</h1>
            <form onSubmit={event => toCandidateLogin(event)}>
                <button type="submit">Candidate</button>
            </form>
            <form onSubmit={event => toCompanyLogin(event)}>
                <button type="submit">Company</button>
            </form>
        </div>
    );
}

export default LoginDecider;