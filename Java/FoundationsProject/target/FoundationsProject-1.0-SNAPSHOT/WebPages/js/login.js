function login() {
    let username = document.getElementById('username').value;
    let pw = document.getElementById('pw').value;

    let user = {
        "username": username,
        "pw": pw
    };

    let xhttp = new XMLHttpRequest();

    xhttp.onload = (resp) => {

        if (xhttp.status === 200) {
            let role = JSON.parse(xhttp.getResponseHeader("user")).role;
            if (role === "employee") {
                console.log("You are an employee.");
            } else {
                console.log("You are a manager.");
            }
        } else {
            alert("Your information is incorrect. Try again.");
        }
    };

    xhttp.open('POST', '../users/login');

    xhttp.send(JSON.stringify(user));
}