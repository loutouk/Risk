var stompClient = null;

function setConnected(connected) {
    $("#join").attr("disabled", false);
    $("#launch").attr("disabled", false);
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game', function (resp) {
            showGame(resp);
        });

        stompClient.subscribe('/topic/connectionId/tempName', function (resp) {
            $("#greetings").append("<tr><td>Connection: " + JSON.parse(resp.body).id + " for " + JSON.parse(resp.body).content + " </td></tr>");
        });

        stompClient.subscribe('/topic/game/askreinforce', function (resp) {
            showGame(resp);
        });
        stompClient.subscribe('/topic/game/putreinforce', function (resp) {
            showGame(resp);
        });
        stompClient.subscribe('/topic/game/attack', function (resp) {
            showGame(resp);
        });
        stompClient.subscribe('/topic/game/skipattack', function (resp) {
            showGame(resp);
        });
        stompClient.subscribe('/topic/game/fortify', function (resp) {
            showGame(resp);
        });
        stompClient.subscribe('/topic/game/skipfortify', function (resp) {
            showGame(resp);
        });
        stompClient.subscribe('/topic/game/skipturn', function (resp) {
            showGame(resp);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function joinServer() {
    stompClient.send("/app/connection/tempName", {}, $("#name").val());
}

function launchGame() {
    stompClient.send("/app/launch", {});
}

function demandeRenfort() {
    stompClient.send("/app/askreinforce", {});
}

function deposeRenfort() {
    stompClient.send("/app/putreinforce", {}, $("#renfortInput").val());
}

function passerAttaque() {
    stompClient.send("/app/skipattack", {});
}

function faireAttaque() {
    stompClient.send("/app/attack", {}, $("#attaqueInput").val());
}

function passerFortification() {
    stompClient.send("/app/skipfortify", {});
}

function faireFortification() {
    stompClient.send("/app/fortify", {}, $("#fortifInput").val());
}

function terminerTour() {
    stompClient.send("/app/skipturn", {});
}
function showGreeting(message) {
    $("#join").attr("disabled", true);
    $("#greetings").append("<tr><td>" + JSON.parse(message.body).content + "</td></tr>");
}

function showGame(message) {
    $("#greetings").empty();
    var obj = JSON.parse(message.body);
    console.log(obj);
    var str = "";
    $.each(obj.content.continents, function(index, cont) {
        $.each(cont.territories, function(index, ter) {
            str = str + ter.id + " belongs to " + ter.owner.name + " (" + ter.army + " army.) <br>";
        });
    });
    $("#greetings").append(obj.content.gameMessage);
    $("#greetings").append("<tr><td>" + str + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#join" ).click(function() { joinServer(); });
    $( "#launch" ).click(function() { launchGame(); });
    $( "#renfortDemande" ).click(function() { demandeRenfort(); });
    $( "#renfortDepot" ).click(function() { deposeRenfort(); });
    $( "#passerAttaque" ).click(function() { passerAttaque(); });
    $( "#faireAttaque" ).click(function() { faireAttaque(); });
    $( "#passerFortif" ).click(function() { passerFortification(); });
    $( "#faireFortif" ).click(function() { faireFortification(); });
    $( "#terminerTour" ).click(function() { terminerTour(); });
});

