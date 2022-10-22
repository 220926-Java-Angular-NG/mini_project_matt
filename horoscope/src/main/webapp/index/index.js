let bdate = document.getElementById("bdate");
bdate.setAttribute("max", new Date().toLocaleDateString('en-ca'));

let loginButton = document.getElementById("login")
loginButton.addEventListener('click', async(event)=>{
    event.preventDefault();
    let loginErrorLab=document.getElementById("login-error")
    loginErrorLab.innerHTML = "";

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
            
        })

    
        setTimeout( ()=>{
            window.location.replace("../home/home.html")
        }, 1000 )
    
    
    }catch(error){
        if (error=="Error: 406"){
            loginErrorLab.innerHTML = "Email/password combo does not match."
        } else if (error== "Error: 404"){
            loginErrorLab.innerHTML = "Email not registered."
        } else if (error=="Error:400"){
            loginErrorLab.innerHTML="Character tokens not accepted"
        }

        console.log(error)
    }

})

let newUserButton = document.getElementById("newUser")
newUserButton.addEventListener('click', async(event)=>{
    event.preventDefault();
    let errorLab=document.getElementById("email-error")
    errorLab.innerHTML="";

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
    console.log(userInfo);
    let json = JSON.stringify(userInfo);
    console.log(userInfo);
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
            window.location.replace("../home/home.html")
        }, 1000 )
    
    
    }catch(error){
        if (error=="Error: 406"){
            errorLab.innerHTML = "Email already taken."
        } else if (error== "Error: 404"){
            errorLab.innerHTML = "Database error, please try again."
        } else if (error=="Error:400"){
            errorLab.innerHTML="Character tokens not accepted"
        }

        console.log(error)
    }

})