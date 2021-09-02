var contatoApp = angular.module('contatoApp', []);

contatoApp.controller('ContatoController', contatoController);

var regEmail = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

function contatoController($scope, $http, $window , $timeout) {
    $scope.submit = function () {
        submit($scope, $http, $window , $timeout);
    };
}

function consultarProfissional($scope, $http, $window , $timeout) {
 
    $http.get("/resultado-de-busca/"+$scope.tipoProfissional+"/"+$scope.especialista)
        .then(function (response) {
           $window.location.href = "/listaProfissionais";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}





function submit($scope, $http, $window , $timeout) {

		if( ! $scope.contato.nome ){
			alert("Digite seu nome");
			return;
		}
		if( ! $scope.contato.email ){
			alert("Digite seu e-mail");
			return;
		}
		if( ! $scope.contato.telefone ){
			alert("Digite seu telefone");
			return;
		}
		if( ! $scope.contato.mensagem){
			alert("Digite a mensagem");
			return;
		}
		
		$http.post("/rest/contato-registrar", $scope.contato)
        .then(function (response) {
            alert_success('Sua mensagem foi encaminhada,em breve retornaremos.', () => {
				$window.location.href = "/entreContato";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}



(function() {
	
	angular.bootstrap(document.getElementById('contatoApp'), ['contatoApp']);
	
})();
