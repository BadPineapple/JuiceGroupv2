var cadastroPessoaApp = angular.module('cadastroPessoaApp', []);

cadastroPessoaApp.controller('CadastroPessoaController', cadastroPessoaController);

function cadastroPessoaController($scope, $http, $window) {
    $scope.submit = function () {
        post($scope, $http, $window);
    };
}

function post($scope, $http, $window) {
    if($scope.tipoConta != '' && $scope.tipoConta == 'CL'){
	  $scope.pessoa.cliente='true';
	  $scope.pessoa.psicologo='false';
	}
	if($scope.tipoConta != '' && $scope.tipoConta == 'PS'){
		$scope.pessoa.psicologo='true';
		$scope.pessoa.cliente='false';
	}
    $http.post("/vitazure/pessoa", $scope.pessoa)
        .then(function (response) {
            alert_success(response.data.message, () => {
                $window.location.href = "/vitazure/informacoes-perfil";
            });
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}