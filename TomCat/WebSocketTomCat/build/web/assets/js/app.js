angular.module('websocketApp', []).controller("websocketCtrl", function($scope, $window, $timeout) {

    var username = "";
    
    $scope.inicio = function(nameUser, idUser, emailUser) {        
        username = nameUser;
    };
    
    $scope.sair = function() {        
        $window.location.href = "getOut.jsp";
    };

    function log(msg) {
        if (typeof console !== "undefined")
            console.log(msg);
    }
    

    if ('WebSocket' in window) {
        var websocket = new WebSocket("ws://localhost:8080/WebSocketTomCat/websocket?username=" + username);
    } else if ('MozWebSocket' in window) {
        var websocket = new WebSocket("ws://localhost:8080/WebSocketTomCat/websocket?username=" + username);
    } else {
        alert("Browser não suporta WebSocket");
    }
    if (websocket != undefined) {
        websocket.onopen = function() {
            log("Conectou com sucesso");
        };
        websocket.onclose = function() {
            log("Desconectou com sucesso");
            alert("Desconectou com sucesso");
        };
        websocket.onerror = function() {
            log("Aconteceu um erro");
        };
        websocket.onmessage = function(data) {
            log("Recebeu mensagem");
            log(data);
            var message = "<p>" + data.data + "</p>";
            var messageArea = document.getElementById("messageArea");
            messageArea.innerHTML += message;
            messageArea.scrollTop = messageArea.scrollHeight;
        };
        function sendMessage(msg) {
            log("Enviar mensagem (" + msg + ")");
            websocket.send(msg);
        }
        document.getElementById("btnEnviar").onclick = function() {
            var msg = document.getElementById("iptMessage").value;
            sendMessage(msg);
            return false;
        };
    }
});



