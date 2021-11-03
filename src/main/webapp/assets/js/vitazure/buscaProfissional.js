var buscaProfissionalApp = angular.module('buscaProfissionalApp', []);

buscaProfissionalApp.controller('BuscaProfissionalController', buscaProfissionalController);

function buscaProfissionalController($scope, $http, $window) {
    $scope.consultarProfissional = function () {
        consultarProfissional($scope, $http, $window);
    };
	$scope.buscaCidades = function () {
        buscaCidades($scope, $http, $window);
    };
	$scope.consultarProfissionalExterna = function () {
        consultarProfissionalExterna($scope, $http, $window);
    };
}

function consultarProfissional($scope, $http, $window) {
	var especialista = $scope.especialista;
	if(typeof especialista != 'undefined'){
		especialista = $scope.especialista.replace('/','&');
	}
	var cidade = document.getElementById("cidade").value === '' ? null : document.getElementById("cidade").value;
    $http.get("/resultado-de-busca/"+$scope.palavraChave+"/"+especialista+"/"+$scope.estado+"/"+cidade)
        .then(function (response) {
           $window.location.href = "/vitazure/profissionais";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

function buscaCidades($scope, $http, $window) {
 if($scope.estado != "" && typeof $scope.estado != 'undefined' && $scope.estado != 'NAO_INFORMADO'){
    $http.get("/api/cidades/"+$scope.estado)
        .then(function (response) {
           var $cidade = $("#cidade");
			$cidade.empty();
			$.each(response.data, function() {
				$cidade.append($("<option />").val(this.nome).text(this.nome));
			});
			if( callback ) {
				callback();
			}
        }).catch(function (response) {
        alert_error(response.data.message);
    })
  }
}

function consultarProfissionalExterna($scope, $http, $window) {
	var especialista = $scope.especialista;
	if(typeof especialista != 'undefined'){
		especialista = $scope.especialista.replace('/','&');
	}
    $http.get("/resultado-de-busca-externa/"+especialista)
        .then(function (response) {
           $window.location.href = "/vitazure/profissionais-externa";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}