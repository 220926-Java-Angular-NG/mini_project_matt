let currentUser = localStorage.getItem("currentUser");
currentUser = JSON.parse(currentUser);
console.log(currentUser);

getData(currentUser);

let welcome = document.getElementById("welcome");
welcome.innerHTML=`Welcome ${currentUser.first_name} ${currentUser.last_name}. Reading for today:`
let whead = document.getElementById("western-head");
whead.innerHTML = currentUser.wzodiac_Display;
let chead = document.getElementById("chinese-head");
chead.innerHTML = currentUser.czodiac_Display;
let cpara = document.getElementById("chinese");
cpara.innerHTML = currentUser.czDescription

let topInt = document.getElementById("topInt");
let moodButt = document.getElementById("mood")
moodButt.addEventListener('click', async()=>{
    if (!topInt.style.visibility){  
        let moodErrLab = document.getElementById("mood-error")
        moodErrLab.innerHTML = "";
        let mood = {
            email:currentUser.email,
            mood:currentUser.mood
        }
    
        let json = JSON.stringify(mood);
    
        try {
    
            const raw_response = await fetch(`http://localhost:8080/user`,{
                method:"PUT", // we hdd the http to be executed
                body:json
            });
        
            if(!raw_response.ok){
                throw new Error(raw_response.status)
            }
            moodErrLab = "Mood Updated!"
            console.log(raw_response)
            /*
            raw_response.json().then( (data) => {
        
                let loggedInUser = JSON.stringify(data)
        
                localStorage.setItem("currentUser",loggedInUser)
                
            })
            */
        }catch(error){
            if (error=="Error: 406"){
                moodErrLab.innerHTML = "Mood not set"
            } else if (error== "Error: 404"){
                moodErrLab.innerHTML = "Cant find user"
            } else if (error=="Error:400"){
                moodErrLab.innerHTML="Character tokens not accepted"
            }
    
        }
        let moodPara = document.getElementById("moodBox");
        let moodHead = document.createElement("p");
        moodHead.innerHTML = currentUser.mood;
        moodPara.append(moodHead);
        let intHead = document.createElement("h3");
        topInt.style.visibility="visible";
        intHead.innerHTML= `Mood ${localStorage.intensity} `;
        topInt.append(intHead);

        let intBar = document.getElementById("intensity");
        intBar.style.height=localStorage.intensity;
    } else {
        console.log(topInt.style.visibility)
        moodErrLab.innerHTML = "";
    }
})

let logout = document.getElementById("logout")
logout.addEventListener('click', ()=>{
    localStorage.currentUser = null;
    localStorage.intensity = null;
    window.location.replace("../index/index.html")
})

async function getData(currentUser){
    try{
        let display_lower = currentUser.wzodiac_Display.toLowerCase()
        console.log(display_lower);
        const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscope/${display_lower}/today`)
        if (!raw_response.ok){
            throw new Error(raw_response.status)
        }
        const json_data = await raw_response.json();
        console.log(json_data);
        let wpara = document.getElementById("western");
        wpara.innerHTML = json_data.horoscope;

        currentUser.mood = json_data.meta.mood;

        localStorage.setItem("intensity",json_data.meta.intensity);
        
    } catch (error){
        console.log(error);
    }
    }