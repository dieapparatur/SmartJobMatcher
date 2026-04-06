import {myAxios} from "./helper.js";


export async function login (loginEmail, loginPaswword) {
    try {
        return myAxios.post("http://localhost:8080/login/company",
            {
                email: loginEmail,
                password: loginPaswword
            })
            .then((response) => response.data);
    } catch (e) {
        console.log(e);
    }
}

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