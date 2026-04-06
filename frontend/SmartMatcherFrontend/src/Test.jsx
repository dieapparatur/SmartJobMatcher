//import {securedTest} from "./userService.js";
import {getAxios} from "./userService.js";


function Test() {

    async function testThing(event) {
        event.preventDefault();
        const serverMessage = await getAxios("me")
        console.log(serverMessage)
    }


    return (
        <div>
            <h1>Security Test</h1>
            <form onClick={event => testThing(event)}>
                <div>SecuredHealthCheck</div>
                <button type="click">Login</button>
            </form>
        </div>

    );
}

export default Test;