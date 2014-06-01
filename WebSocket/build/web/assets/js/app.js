angular.module('websocketApp', []).controller("websocketCtrl", function($scope, $window, $timeout) {

    var socket = new WebSocket("ws://localhost:8080/WebSocket/WebSocket");
    socket.onmessage = onMessage;

    $scope.listUsers = [];
    $scope.userAction = {};

    $scope.sair = function(idUser) {        
        $scope.removeUser(idUser);
        $window.location.href = "getOut.jsp";
    };

    $scope.inicio = function(nameUser, idUser, emailUser) {
        angular.extend($scope, {
            user: {
                id: idUser,
                name: nameUser || "",
                email: emailUser || ""
            }
        });
        $scope.addUser($scope.user);
    };

    $scope.addUser = function(objUser) {
        $scope.userAction = {
            action: "add",
            name: objUser.name,
            id: objUser.id,
            email: objUser.email
        };
        $timeout(function() {
            socket.send(JSON.stringify($scope.userAction));
        }, 300);

    };

    $scope.removeUser = function(obj) {        
        $scope.userAction = {
            action: "remove",
            id: parseInt(obj)
        };
        socket.send(JSON.stringify($scope.userAction));
    };

    function onMessage(event) {
        var user = JSON.parse(event.data);
        if (user.action === "add") {
            $timeout(function() {
                $scope.listUsers.push(user);
            });
        }
        if (user.action === "remove") {            
            for (var i = 0; i < $scope.listUsers.length; i++) {
                if ($scope.listUsers[i].id == user.id) {
                    $scope.listUsers.splice(i, 1);
                    i--;
                }
            }
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        }
    }

});



