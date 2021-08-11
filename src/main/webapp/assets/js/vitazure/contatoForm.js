var contatoApp = angular.module('contatoApp', []);

contatoApp.controller('ContatoController', contatoController);

$scope.contato = {};

$scope.regEmail = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

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
            alert_success(response.data.message, () => {
	            'Obrigado pelo seu contato.'
				$window.location.href = "/vitazure/informacoes-perfil";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

        $http({
            method: "POST",
            url: `${ENDERECO_SITE}/rest/contato-registrar`,
            headers: {
                'Content-Type': 'application/json;charset=utf8'
            },
            params: {
            },
            data: $scope.contato
        }).then(function (response) {

            $scope.sending = false;
            $scope.contato = {
                tipo: 'CONTATO'
            };

            setTimeout(() => {
                Swal.fire({
                    title: 'Obrigado pelo seu contato.',
                    text: '',
                    icon: 'success',
                    confirmButtonText: 'OK'
                });
            }, 100);
            
        }, function (error) {
        	
            $scope.sending = false;
            console.log('error: ', error);
            //alert(error.data.message);
            Swal.fire({
                title: 'Atenção!',
                text: 'Ocorreu um erro, por favor tente novamente.',
                icon: 'error',
                confirmButtonText: 'OK'
            });

        });


	};

(function() {
	
	angular.bootstrap(document.getElementById('contatoApp'), ['contatoApp']);
	
})();
