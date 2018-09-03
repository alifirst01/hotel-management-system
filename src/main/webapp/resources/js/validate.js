function checkusername() {
    var xmlhttp = new XMLHttpRequest();
    var username = document.forms["registration_form"]["username"].value;
    var url = "/user/availability?username=" + username;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (xmlhttp.responseText == "Failure") {
                document.getElementById("username").value = '';
                document.getElementById("error").innerHTML = "Username " + username + " is already in use.";
            }
            else
                document.getElementById("error").innerHTML =  '';

        }
    };
    try {
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } catch (e) {
    }
}

function checkemail() {
    var xmlhttp = new XMLHttpRequest();
    var email = document.forms["registration_form"]["email"].value;
    var url = "/email/availability?email=" + email;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (xmlhttp.responseText == "Failure") {
                document.getElementById("email").value = '';
                document.getElementById("error2").innerHTML = "Email " + email + " is already in use";
            }
            else
                document.getElementById("error2").innerHTML =  '';

        }
    };
    try {
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } catch (e) {
    }
}