
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("spacePic-form")
const spacePicContainer = document.getElementById("spacePic-container")

//Modal Elements
let spacePicBody = document.getElementById(`spacePic-body`)
let updateSpacePicBtn = document.getElementById('update-spacePic-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/spacePic"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        description: document.getElementById("spacePic-descrip").value;
        spacePicUrl: document.getElementById("spacePic-url").value;
        userId: userId;
    }
    await addSpacePic(bodyObj);
    document.getElementById("spacePic-url").value = '';
    document.getElementById("spacePic-descrip").value = '';

}

async function addSpacePic(bodyObj) {
    const response = await fetch(`${baseUrl}/user/${userId}`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getSpacePics(userId);
    }
}

async function getSpacePics(userId) {
    await fetch(`${baseUrl}/user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createSpacePicCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(spacePicId, userId){
    await fetch(baseUrl + spacePicId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getSpacePics(userId);
}

async function getSpacePicById(spacePicId){
    await fetch(baseUrl + spacePicId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleSpacePicEdit(spacePicId){
    let bodyObj = {
        id: spacePicId,
        body: spacePicBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getSpacePics(userId);
}

const createSpacePicCards = (array) => {
    spacePicContainer.innerHTML = ''
    array.forEach(obj => {
        let spacePicCard = document.createElement("div")
        spacePicCard.classList.add("m-2")
        spacePicCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getSpacePicById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#spacePic-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        spacePicContainer.append(spacePicCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    spacePicBody.innerText = ''
    spacePicBody.innerText = obj.body
    updateSpacePicBtn.setAttribute('data-spacePic-id', obj.id)
}

getSpacePics(userId);

submitForm.addEventListener("submit", handleSubmit);

updateSpacePicBtn.addEventListener("click", (e)=>{
    let spacePicId = e.target.getAttribute('data-spacePic-id')
    handleSpacePicEdit(spacePicId);
})