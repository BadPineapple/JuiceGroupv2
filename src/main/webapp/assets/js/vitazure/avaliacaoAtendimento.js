var avaliarAtendimentoApp = angular.module('avaliarAtendimentoApp', []);
avaliarAtendimentoApp.controller('AvaliarAtendimentoController', avaliarAtendimentoController);

var avaliacao = 0;

function avaliarAtendimentoController($scope, $http, $window) {
    $scope.avaliarNota = function (estrela) {
        avaliarNota(estrela);
    };
    $scope.avaliarAtendimento = function (idAgenda) {
        avaliarAtendimento(idAgenda,$scope, $http, $window);
    };
}


function avaliarAtendimento(idAgenda,$scope, $http, $window){	
	$scope.agenda.avaliacaoAtendimentoNota = avaliacao;
	$http.post("/vitazure/finalizarAvaliacaoAtendimento", $scope.agenda)
        .then(function (response) {
            alert_success(response.data, () => {
				$window.location.href = "/vitazure/lista-de-consultas";
			});
        }).catch(function (response) {
        alert_error(response.data);
    })
}


function avaliarNota(estrela) {
 var url = window.location;
 url = url.toString()
 url = url.split("index.html");
 url = url[0];
 

if (estrela == 5){ 
		 document.getElementById("s1").className = "fas fa-star starDesmarcada";
		 document.getElementById("s2").className = "fas fa-star starDesmarcada";
		 document.getElementById("s3").className = "fas fa-star starDesmarcada";
		 document.getElementById("s4").className = "fas fa-star starDesmarcada";
		 document.getElementById("s5").className = "fas fa-star starDesmarcada";
		 avaliacao = 5;
}
 
 //ESTRELA 4
if (estrela == 4){ 
 document.getElementById("s1").className = "fas fa-star starDesmarcada";
 document.getElementById("s2").className = "fas fa-star starDesmarcada";
 document.getElementById("s3").className = "fas fa-star starDesmarcada";
 document.getElementById("s4").className = "fas fa-star starDesmarcada";
 document.getElementById("s5").className = "far fa-star starDesmarcada";
 avaliacao = 4;
 }

//ESTRELA 3
if (estrela == 3){ 
 document.getElementById("s1").className = "fas fa-star starDesmarcada";
 document.getElementById("s2").className = "fas fa-star starDesmarcada";
 document.getElementById("s3").className = "fas fa-star starDesmarcada";
 document.getElementById("s4").className = "far fa-star starDesmarcada";
 document.getElementById("s5").className = "far fa-star starDesmarcada";
 avaliacao = 3;
 }
 
//ESTRELA 2
if (estrela == 2){ 
 document.getElementById("s1").className = "fas fa-star starDesmarcada";
 document.getElementById("s2").className = "fas fa-star starDesmarcada";
 document.getElementById("s3").className = "far fa-star starDesmarcada";
 document.getElementById("s4").className = "far fa-star starDesmarcada";
 document.getElementById("s5").className = "far fa-star starDesmarcada";
 avaliacao = 2;
 }
 
 //ESTRELA 1
if (estrela == 1){ 
 document.getElementById("s1").className = "fas fa-star starDesmarcada";
 document.getElementById("s2").className = "far fa-star starDesmarcada";
 document.getElementById("s3").className = "far fa-star starDesmarcada";
 document.getElementById("s4").className = "far fa-star starDesmarcada";
 document.getElementById("s5").className = "far fa-star starDesmarcada";
 avaliacao = 1;
}
 
}