let bdate = document.getElementById("bdate");
bdate.setAttribute("max", new Date().toLocaleDateString('en-ca'));

let loginButton = document.getElementById("login")
loginButton.addEventListener('click', async(event)=>{
    event.preventDefault();

    let email = document.getElementById("email").value;
    let pass = document.getElementById("pass").value;

    let userInfo = {
        email:email,
        password:pass
    }

    let json = JSON.stringify(userInfo);

    try {

        const raw_response = await fetch(`http://localhost:8080/login`,{
            method:"POST", // we hdd the http to be executed
            body:json
        });
    
        if(!raw_response.ok){
            throw new Error(raw_response.status)
        }
    
        raw_response.json().then( (data) => {
    
            let loggedInUser = JSON.stringify(data)
    
            localStorage.setItem("currentUser",loggedInUser)
            console.log(loggedInUser);
            
        })
    
        setTimeout( ()=>{
            window.location.replace("home.html")
        }, 1000 )
    
    
    }catch(error){
        console.log(error)
    }

})

let newUserButton = document.getElementById("newUser")
newUserButton.addEventListener('click', async(event)=>{
    event.preventDefault();

    let newEmail = document.getElementById("email-signup").value;
    let newPass = document.getElementById("pass-signup").value;
    let fname = document.getElementById("fname").value;
    let lname = document.getElementById("lname").value;
    let birthdate = document.getElementById("bdate").value;

    let userInfo = {
        email: newEmail,
        password:newPass,
        first_name:fname,
        last_name:lname,
        birthdate:birthdate
    }

    let json = JSON.stringify(userInfo);

    try {

        const raw_response = await fetch(`http://localhost:8080/user`,{
            method:"POST", // we hdd the http to be executed
            body:json
        });
    
        if(!raw_response.ok){
            throw new Error(raw_response.status)
        }
    
        raw_response.json().then( (data) => {
    
            let loggedInUser = JSON.stringify(data)
    
            localStorage.setItem("currentUser",loggedInUser)
            console.log(loggedInUser);
            
        })
    
        setTimeout( ()=>{
            window.location.replace("home.html")
        }, 1000 )
    
    
    }catch(error){
        console.log(error)
    }

})