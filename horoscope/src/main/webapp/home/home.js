let currentUser = localStorage.getItem("currentUser");
currentUser = JSON.parse(currentUser);
console.log(currentUser);

getData(currentUser);

let welcome = document.getElementById("welcome");
welcome.innerHTML=`Welcome ${user.first_name} ${user.last_name}. Reading for today:`
let whead = document.getElementById("western-head");
whead.innerHTML = user.wzodiac_Display;
let chead = document.getElementById("chinese-head");
chead.innerHTML = user.czodiac_Display;
let cpara = document.getElementById("chinese");
cpara = currentUser.czDescription


let moodButt = document.getElementById("mood")
moodButt.addEventListener('click', ()=>{
    let moodHead = document.createElement("h3");
    moodHead.innerHTML = currentUser.mood;
    moodButt.append(moodHead);
    let topInt = document.getElementById("topInt");
    let intHead = document.createElement("h3");
    intHead.innerHTML= "Mood %";
    topInt.append(intHead);

    let intBar = document.getElementById("intensity");
    intBar.setAttribute("height"=localStorage.intensity);
})









async function getData(currentUser){
    try{
        const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscope/${currentUser.wzodiac_Display}/today`)
        if (!raw_response.ok){
            throw new Error(raw_response.status)
        }
        const json_data = await raw_response.json();
        console.log(json_data);
        let wpara = document.getElementById("western");
        wpara.innerHTML = json_data.horoscope;

        currentUser.mood = json_data.mood.mood;

        localStorage.setItem("intensity",json_data.mood.intensity);
        
    } catch (error){
        console.log(error);
    }
    }