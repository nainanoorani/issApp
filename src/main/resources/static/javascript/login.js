const loginForm = document.getElementById('login-form');
const loginUsername = document.getElementById('login-username');
const loginPassword = document.getElementById('login-password');

const headers = {
'Content-Type': 'application/json'
}
const baseUrl = 'http://localhost:8080/api/v1/users'

const handleSubmit = async(e) => {
    e.preventDefault()
    let bodyObj = {
        username: loginUsername.value,
        password: loginPassword.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if(response.status === 200){
        window.location.replace(responseArr[0])
    }
}

loginForm.addEventListener("submit", handleSubmit);
