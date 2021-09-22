var ouvidoriaApp = angular.module('ouvidoriaApp', []);

ouvidoriaApp.controller('OuvidoriaController', ouvidoriaController);

var regEmail = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

function ouvidoriaController($scope, $http, $window , $timeout) {
 	$scope.enviarOuvidoria = function () {
        enviarOuvidoria($scope, $http, $window , $timeout);
    };
}

function enviarOuvidoria($scope, $http, $window , $timeout) {

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
		$scope.contato.ouvidoria = true;
		document.getElementById("spinner").style.display = "inline-block";
		$http.post("/rest/contato-registrar", $scope.contato)
        .then(function (response) {
            alert_success('Sua mensagem foi encaminhada,em breve retornaremos.', () => {
			document.getElementById("nome").value = "";
			document.getElementById("email").value = "";
			document.getElementById("telefone").value = "";
			document.getElementById("mensagem").value = "";
			document.getElementById("spinner").style.display = "none";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
		document.getElementById("spinner").style.display = "none";
    })
}


(function() {
	angular.bootstrap(document.getElementById('ouvidoriaApp'), ['ouvidoriaApp']);
})();
