var cadastroPessoaApp = angular.module('cadastroPessoaApp', []);

cadastroPessoaApp.controller('CadastroPessoaController', cadastroPessoaController);

function cadastroPessoaController($scope, $http, $window) {
    $scope.submit = function () {
        post($scope, $http, $window);
    };
}

function post($scope, $http, $window) {
 
    $http.post("/vitazure/pessoa", $scope.pessoa)
        .then(function (response) {
            alert_success(response.data.message, () => {
                $window.location.href = "/vitazure/informacoes-perfil";
            });
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}