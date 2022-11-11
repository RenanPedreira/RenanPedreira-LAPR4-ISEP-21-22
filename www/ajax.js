
function statusCheck() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("status");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(statusCheck, 4000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(statusCheck, 100);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(statusCheck, 5000);
    };

    request.open("GET", "/status", true);
    request.timeout = 2000;
    request.send();
}





function positionCheck() {
    var request1 = new XMLHttpRequest();
    var vBoard1=document.getElementById("position");

    request1.onload = function() {
        vBoard1.innerHTML = this.responseText;
        vBoard1.style.color="white";
        setTimeout(positionCheck, 2000);
    };

    request1.ontimeout = function() {
        vBoard1.innerHTML = "Server timeout, still trying ...";
        vBoard1.style.color="red";
        setTimeout(positionCheck, 100);
    };

    request1.onerror = function() {
        vBoard1.innerHTML = "No server reply, still trying ...";
        vBoard1.style.color="red";
        setTimeout(positionCheck, 5000);
    };

    request1.open("GET", "/position", true);
    request1.timeout = 1000;
    request1.send();

}
