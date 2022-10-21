class User{
    constructor(first,last,wzodiac, czodiac){
        this.fname = first;
        this.lname = last;
        this.wzodiac = wzodiac;
        this.czodiac = czodiac;
    }
}




async function getData(user){
try{
    const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscope/${user.wzodiac}/today`)
    if (!raw_response.ok){
        throw new Error(raw_response.status)
    }
    const json_data = await raw_response.json();
    console.log(json_data);
    let wpara = document.getElementById("western");
    wpara.innerHTML = json_data.horoscope;

} catch (error){
    console.log(error);
}
}

var user = new User("matt", "hanson", "scorpio","snake");
let welcome = document.getElementById("welcome");
welcome.innerHTML=`Welcome ${user.fname} ${user.lname}. Reading for today:`
let whead = document.getElementById("western-head");
whead.innerHTML = user.wzodiac;
let chead = document.getElementById("chinese-head");
chead.innerHTML = user.czodiac;
console.log(user);
getData(user);