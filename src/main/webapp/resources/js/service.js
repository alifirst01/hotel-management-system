function updateService() {
    var xmlhttp = new XMLHttpRequest();
    var bed = 0;
    var internet = 0;
    var carpet = 0;
    if(document.forms["reg_form"]["bed"].checked)
        bed = 1;
    if(document.forms["reg_form"]["internet"].checked)
        internet = 1;
    if(document.forms["reg_form"]["carpet"].checked)
        carpet = 1;
    var id = document.forms["reg_form"]["id"].value;

    var url = "/room/addroomservice?id="+ id + "&bed=" + bed + "&carpet=" + carpet + "&internet=" + internet;
    xmlhttp.onreadystatechange = function () {
        debugger;
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            alert("Success");
            if (xmlhttp.responseText == "Success") {
                alert("Success");
            }
            else
                alert("Failure");

        }
    };
    try {
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } catch (e) {
        alert("Failure");
    }
}