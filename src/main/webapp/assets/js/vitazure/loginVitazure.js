var loginVitazureApp = angular.module('loginVitazureApp', []);

loginVitazureApp.controller('LoginVitazureController', loginVitazureController);

function loginVitazureController($scope, $http, $window) {
    $scope.logar = function () {
        logar($scope, $http, $window);
    };
    $scope.deslogar = function () {
        deslogar($scope, $http, $window);
    };
	
	$scope.esqueceuSenha = function() {
		email = $scope.email;
		esqueceuSenha($scope, $http, $window , email);
	};

}

function logar($scope, $http, $window) {
 
    $http.post("/vitazure/login", $scope.pessoa)
        .then(function (response) {
                $window.location.href = "/vitazure/informacoes-perfil";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}
function deslogar($scope, $http, $window) {
	$http.get("/deslogar")
        .then(function (response) {
           $window.location.href = "/home";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

function esqueceuSenha($scope, $http, $window , email) {
	$http.post("/vitazure/esqueciMinhaSenha/", email)
        .then(function (response) {
	        alert_success(response.data.message, () => {
                $window.location.href = "/entrar";
            });
        }).catch(function (response) {
        alert_error(response.data.message);
    })    
}