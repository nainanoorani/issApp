
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const currentCrewForm = document.getElementById("currentCrew-form")
const crewMemberContainer = document.getElementById("crewMember-container")
const missionCrewForm = document.getElementById("missionCrew-form")

//Modal Elements
//let crewMemberBody = document.getElementById(`crewMember-body`)

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/crew"

function getMission(){
var e = document.getElementById("mission-select");
var selectedMissionDropdown = e.options[e.selectedIndex].value;
return selectedMissionDropdown
}

const getCrewByMission = async(e) => {
    e.preventDefault()
    let mission = getMission();
    console.log(mission)
    await fetch(`${baseUrl}/${mission}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCrewMemberCards(data))
        .catch(err => console.error(err))
}

const handleGetCurrentCrew = async (e) => {
    e.preventDefault()
    await fetch(`${baseUrl}/`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCrewMemberCards(data))
        .catch(err => console.error(err))
}






//async function getSpacePicById(spacePicId){
//    await fetch(`${baseUrl}/${spacePicId}`, {
//        method: "GET",
//        headers: headers
//    })
//        .then(res => res.json())
//        .then(data => populateModal(data))
//        .catch(err => console.error(err.message))
//}


const createCrewMemberCards = (array) => {
    console.log(array);
    crewMemberContainer.innerHTML = ''
    array.forEach(obj => {
        console.log(obj);
        let crewMemberCard = document.createElement("div")
        crewMemberCard.classList.add("m-2")
        crewMemberCard.innerHTML = `

            <div class="card d-flex" style="width: 18rem; ">

                    <img class="card-image" src="${obj.crewPic}">
                    <p class="card-title">${obj.crewName}</p>
                    <p class="card-text">country: ${obj.country}</p>
                    <p class="card-text">specialization: ${obj.specialization}</p>
                    <p class="card-text">mission: ${obj.mission}</p>

                </div>

        `
        crewMemberContainer.append(crewMemberCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

//const populateModal = (obj) =>{
//console.log(obj);
//    crewMemberBody.innerText = ''
//    crewMemberBody.innerText = obj
//    updateCrewMemberBtn.setAttribute('data-crewMember-id', obj.id)
//}


//getCrewMembers();

currentCrewForm.addEventListener("submit", handleGetCurrentCrew);
missionCrewForm.addEventListener("submit", getCrewByMission)

