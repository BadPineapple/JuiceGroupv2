var acessoAgendaApp = angular.module('acessoAgendaApp', []);
acessoAgendaApp.controller('AcessoAgendaController', acessoAgendaController);

function acessoAgendaController($scope, $http, $window) {
	
	$scope.acessarSala = function () {
        acessarSala($scope, $http, $window);
    };		
}


function acessarSala($scope, $http, $window){
	alert('Teste');
}