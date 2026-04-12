import {myAxios} from "./helper.js";



export async function candidateLogin (loginEmail, loginPassword) {
    try {
        const response = await myAxios.post(
            "http://localhost:8080/login/candidate",
            {
                email: loginEmail,
                password: loginPassword
            }
        );

        return response.data;
    } catch (e) {
        console.log(e);
    }
}



export async function companyLogin (email, password) {
    try {
        const response = await myAxios.post(
            "http://localhost:8080/login/company",
            {
                email: email,
                password: password
            }
        );

        return response.data;
    } catch (e) {
        console.log(e);
    }
}


//axios method for GET requests
export async function getAxios(routing) {
    console.log(routing);
    try {
        const response = await myAxios.get(`http://localhost:8080/${routing}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            }
        });
        return response.data;
    } catch (e) {
        console.log(e);
    }
}