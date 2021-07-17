var clienteApp = angular.module('clienteApp', []);

clienteApp.controller('ClienteController', clienteController);

function clienteController($scope, $http, $window) {
    
    $scope.submit = function () {
        post($scope, $http, $window);
    };
         
    $scope.validarDataMaiorAtual = function (data) {
        validarDataMaiorAtual(data);
    };
    
}

function post($scope, $http, $window) {
    $http.post("/vitazure/pessoa", $scope.pessoa)
        .then(function (response) {
            alert_success(response.data.message, () => {
            });
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}


function validarDataMaiorAtual(data){
	  let partes = data.split('/') 
	  let dataAtual = new Date()      
	  data2 = new Date(partes[2], partes[1] - 1, partes[0])
	 var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])      [\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|31)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
     if (((!((data.match(RegExPattern)))) && ( data != "" )) || data2 >= dataAtual) {
          alert_error('Data informada esta inválida.');
          document.getElementById('dataAniversario').value = '';
     }
}