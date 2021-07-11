var loginVitazureApp = angular.module('loginVitazureApp', []);

loginVitazureApp.controller('LoginVitazureController', loginVitazureController);

function loginVitazureController($scope, $http, $window) {
    $scope.logar = function () {
        logar($scope, $http, $window);
    };
}

function logar($scope, $http, $window) {
 
    $http.post("/vitazure/login", $scope.pessoa)
        .then(function (response) {
            alert_success(response.data.message, () => {
                $window.location.href = "/vitazure/informacoes-perfil";
            });
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}