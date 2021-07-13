var buscaProfissionalApp = angular.module('buscaProfissionalApp', []);

buscaProfissionalApp.controller('BuscaProfissionalController', buscaProfissionalController);

function buscaProfissionalController($scope, $http, $window) {
    $scope.consultarProfissional = function () {
        consultarProfissional($scope, $http, $window);
    };
}

function consultarProfissional($scope, $http, $window) {
 
    $http.get("/resultado-de-busca/"+$scope.tipoProfissional+"/"+$scope.especialista)
        .then(function (response) {
           $window.location.href = "/listaProfissionais";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}