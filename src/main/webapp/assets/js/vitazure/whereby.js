var consultaWherebyApp = angular.module('consultaWherebyApp', []);
consultaWherebyApp.controller('ConsultaWherebyController', consultaWherebyController);

var min, seg;

function consultaWherebyController($scope, $http, $window) {
	
	$scope.atualizarCronometro = function () {
        atualizarCronometro($scope, $http, $window);
    };

$scope.finalizacaoAtendimento = function () {
	finalizacaoAtendimento($scope, $http, $window);
}	
	
}

function atualizarCronometro($scope, $http, $window){
		definirTempoCronometro($scope, $http, $window);		
}


function finalizacaoConsulta(urlAcessar,$scope, $http, $window){
	$window.location.href = urlAcessar;
}

function finalizacaoAtendimento($scope, $http, $window){
	var identificador = document.getElementById("id").value;
	$.ajax({
		        url: '/vitazure/finalizarAtendimento',
		        type: 'post',
		        data: identificador,		        
		        contentType: 'application/json',
		        success: function (response) {
		           alert_success('Atendimento finalizado com sucesso!');
				   finalizacaoConsulta(response,$scope, $http, $window)
		        }, error: function (response) {		           
		           alert_success('Atendimento finalizado com sucesso!');
				   finalizacaoConsulta(response,$scope, $http, $window)
		        }
		    });	
}

function relogio($scope, $http, $window){
		
		if((min == 0) && (seg == 0)){				
			var identificador = document.getElementById("id").value;
			$.ajax({
		        url: '/vitazure/finalizarAtendimento',
		        type: 'post',
		        data: identificador,		        
		        contentType: 'application/json',
		        success: function (response) {
		           alert_success('O tempo da sua consulta expirou,seu atendimento foi encerrado!');
				    document.getElementById("finalizarAtendimento").click();
		        }, error: function (response) {		           
		              alert_success('O tempo da sua consulta expirou,seu atendimento foi encerrado!');
				    document.getElementById("finalizarAtendimento").click();
		        }
		    });			
		}			
		else{
			
			if(seg == 0){					
				seg = 59;					
				min = min - 1	
			}				
			else{					
				seg = seg - 1;				
			}				
			if(min.toString().length == 1){					
				min = "0" + min;				
			}				
			if(seg.toString().length == 1){					
				seg = "0" + seg;				
			}				
			document.getElementById('spanRelogio').innerHTML = min + ":" + seg;
			if(min < 5){
				document.getElementById('spanRelogio').style.color = "red";
			}
			setTimeout('relogio()', 1000);				
			
		}		
};


function validarAgendaConcluida($scope, $http, $window){
		var identificador = document.getElementById("id").value;
			
			$.ajax({
		        url: '/vitazure/validarFinalizarAtendimento',
		        type: 'GET',
		        data: 'identificador ='+identificador,
		        contentType: 'application/json',
		        success: function (response) {
		           alert_success('O tempo da sua consulta expirou,seu atendimento foi encerrado!');
		        }, error: function (response) {		           
		        }
		    });			
			setTimeout('validarAgendaConcluida()', 10000);				
};

	
function definirTempoCronometro($scope, $http, $window) {
	       var d = new Date(), displayDate;
	       if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
	          displayDate = d.toLocaleTimeString('pt-BR');
	       } else {
	          displayDate = d.toLocaleTimeString('pt-BR', {timeZone: 'America/Belem'});
	       }
	       
	       if (displayDate.substr(0, 5) >= document.getElementById("horaInicio").value) {
		      var hrIni = new Date('1990/07/03 '.concat(document.getElementById("horaFim").value).concat(":00"));
		      var hrF = new Date('1990/07/03 '.concat(displayDate));	         
	          var teste = moment(hrIni, "YYYY/MM/DD HH:mm:ss").diff(moment(hrF, "YYYY/MM/DD HH:mm:ss"));	          
	          var opa = moment.duration(teste).asMinutes();
	          const tempo = String(opa).split('.');	          
	          min = tempo[0];
	          if(typeof tempo[1] === 'undefined'){
	        	  seg = "00";
	          }else if(tempo[1].length > 2){
	        	  seg = tempo[1].substr(0, 2); 
	          }else{
	            seg = tempo[1];
	          }	         
	          relogio($scope, $http, $window);
	          validarAgendaConcluida($scope, $http, $window);
	       }else{
	    	   setTimeout('definirTempoCronometro()', 1000); 
	       }  	          
	    }	