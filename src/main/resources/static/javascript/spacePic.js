
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("spacePic-form")
const spacePicContainer = document.getElementById("spacePic-container")
//const allSpacePicHeader = document.querySelector('#spacePic-mode')
//const allSpacePicBtn = document.getElementById("getAll-btn")
//const allSpacePicsContainer = document.getElementById("allSpacePics-container")
const allPicsForm = document.getElementById("allSpacePics-form")
const favPicsForm = document.getElementById("favSpacePics-form")
const userPicsForm = document.getElementById("userSpacePics-form")

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
        description: document.getElementById("spacePic-descrip").value,
        imageUrl: document.getElementById("spacePic-url").value
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
        return getUserSpacePics(userId);
    }
}

async function getUserSpacePics(userId) {
    await fetch(`${baseUrl}/myPic/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createSpacePicCards(data))
        .catch(err => console.error(err))
}

const handleGetAllPics = async (e) => {
    e.preventDefault()
    await fetch(`${baseUrl}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createSpacePicCards(data))
//        .then(changeHeader)
        .catch(err => console.error(err))
}

const handleGetFavoritePics = async (e) => {
    e.preventDefault()
    await fetch(`${baseUrl}/user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createSpacePicCards(data))
//        .then(changeHeader)
        .catch(err => console.error(err))
}

async function handleDelete(spacePicId){
    await fetch(`${baseUrl}/${spacePicId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getUserSpacePics(userId);
}

async function getSpacePicById(spacePicId){
    await fetch(`${baseUrl}/${spacePicId}`, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function changeSpacePicFavorite(spacePicId){
    let favBtn=document.getElementById("fav-btn");
    console.log(favBtn.value);
    if(favBtn.value=="Favorite"){
       favBtn.innerHTML="Unfavorite";
    }else{
        favBtn.innerHTML=="Favorite";
    }

    await fetch(`${baseUrl}/${spacePicId}`, {
        method: "PUT",
        headers: headers
    }).catch(err => console.error(err))

    return getUserSpacePics(userId);
}

const createSpacePicCards = (array) => {
    console.log(array);
    spacePicContainer.innerHTML = ''
    array.forEach(obj => {
        console.log(obj);
        let spacePicCard = document.createElement("div")
        spacePicCard.classList.add("m-2")
        if(userId==obj.userId && obj.favoritePic==false){
        spacePicCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; ">
                    <img class="card-text" src="${obj.imageUrl}">
                    <p class="card-text">${obj.description}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.imageId})">Delete</button>
                        <button id="fav-btn" onclick="changeSpacePicFavorite(${obj.imageId})" type="button" class="btn btn-primary"> Favorite </button>
                    </div>
                </div>
            </div>`
            }
            else if(userId==obj.userId && obj.favoritePic==true){
            spacePicCard.innerHTML = `
                <div class="card d-flex" style="width: 18rem; ">
                        <img class="card-text" src="${obj.imageUrl}">
                        <p class="card-text">${obj.description}</p>
                        <div class="d-flex justify-content-between">
                            <button class="btn btn-danger" onclick="handleDelete(${obj.imageId})">Delete</button>
                            <button id="fav-btn" onclick="changeSpacePicFavorite(${obj.imageId})" type="button" class="btn btn-primary"> Unfavorite </button>
                        </div>
                    </div>
                </div>`
            }

            else{
            spacePicCard.innerHTML = `
                <div class="card d-flex" style="width: 18rem; ">
                    <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                        <img class="card-text" src="${obj.imageUrl}">
                        <p class="card-text">${obj.description}</p>
                    </div>
                </div>`
            }

        spacePicContainer.append(spacePicCard);
    })
}


const populateModal = (obj) =>{
console.log(obj);
    spacePicBody.innerText = ''
    spacePicBody.innerText = obj
    updateSpacePicBtn.setAttribute('data-spacePic-id', obj.id)
}

// document.querySelector('button').addEventListener('click', getFetch)


getUserSpacePics(userId);

submitForm.addEventListener("submit", handleSubmit);
allPicsForm.addEventListener("submit", handleGetAllPics);
favPicsForm.addEventListener("submit", handleGetFavoritePics);
userPicsForm.addEventListener("submit", getUserSpacePics);



