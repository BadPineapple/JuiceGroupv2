var agendamentoApp = angular.module('agendamentoApp', []);

var idTemp = '';

var idHoraTemp = '';

var tipoAgendamento = '';

var dataSelecionada = '';

var idTempSelecionado = '';


 var horariosDisponiveisAtendimento = new Array();

class HorarioAtendimento{
  constructor(horario , dia) {
    this.horario = horario;
    this.dia = dia;
  }
}

agendamentoApp.controller('AgendamentoController', agendamentoController);

function agendamentoController($scope, $http, $window) {
    $scope.consultarDatasProfissional = function (id, profissional) {
        consultarDatasProfissional($scope, $http, $window , id , profissional);
    };
    $scope.agendar = function (id) {
        agendar($scope, $http, $window , id);
    };
}

function consultarDatasProfissional($scope, $http, $window , id , profissional) {
   
    document.getElementById(profissional+'.'+id).className = "dias active slick-slide slick-current slick-active";
	if(idTemp != ''){
	  document.getElementById(idTemp).className = "dias slick-slide slick-cloned";
	} 
	 idTemp = profissional+'.'+id;
     dataSelecionada = id;
	
	$http.get("/vitazure/consultarDatasProfissional/"+id+"/"+profissional)
        .then(function (response) {
	           var idExcluir = "#panelFiltrosSelecionados"+profissional+" a";
           		$(idExcluir).remove();
	                horariosDisponiveisAtendimento = new Array();
           		 for (var i = 0; i < response.data.length; i++) {
           		    horariosDisponiveisAtendimento.push(response.data[i]);
    			}
           		horariosDisponiveisAtendimento.forEach(AdicionarHorariosDisponiveis);
        }).catch(function (response) {
          alert('Teste');
    })
}


function AdicionarHorariosDisponiveis(item, indice){
	    var idIncluir = "#panelFiltrosSelecionados"+item.codigoProfissional;
        var idHora = indice+"/"+item.codigoProfissional;
		$(idIncluir).append(
			"<a type='text' id='"+idHora+"'  onclick='selecionarHora("+indice+","+item.codigoProfissional+" )' class='line' style='background: #B8DFED;border-radius: 10px;text-align: center; margin: 10px 10px;transition: all .3s ease-in-out;padding: 5px; width: 74px;font-size: 1.6rem;line-height: 2rem;font-weight: 700;'>"+
			  item.horaPossivelAtendiemnto+
			"</a>"
			);	
};

function selecionarHora(id , codigoProfissional){
	 var idHora = id+"/"+codigoProfissional;
	document.getElementById(idHora).style = "border: 3px solid #83B2CD;background: #B8DFED;border-radius: 10px;text-align: center; margin: 10px 10px;transition: all .3s ease-in-out;padding: 5px; width: 74px;font-size: 1.6rem;line-height: 2rem;font-weight: 700;";
	if(idTempSelecionado != ''){
	  document.getElementById(idTempSelecionado).style = "background: #B8DFED;border-radius: 10px;text-align: center; margin: 10px 10px;transition: all .3s ease-in-out;padding: 5px; width: 74px;font-size: 1.6rem;line-height: 2rem;font-weight: 700;";
	} 
	 idHoraTemp = id;
      idTempSelecionado = idHora;
};

function marcardesmarcar(idProfissional,id) {
	if(id == 'online'){
  		 document.getElementById(idProfissional+'.online').className = "active marcar"
         document.getElementById(idProfissional+'.presencial').className = ""
	}else{
		 document.getElementById(idProfissional+'.online').className = ""
         document.getElementById(idProfissional+'.presencial').className = "active marcar"
	}
	
	tipoAgendamento = id;
}

function agendar($scope, $http, $window , id) {
	var idProfissional = id;
	var horarioPossivelAtendimento  = horariosDisponiveisAtendimento[idHoraTemp].horaPossivelAtendiemnto;
	var tipoAtendimento  = tipoAgendamento;
	var data  = dataSelecionada;
$http.post("/vitazure/agendar/" +idProfissional+"/"+horarioPossivelAtendimento+"/"+data+"/"+tipoAtendimento)
        .then(function (response) {
            alert_success(response.data.message, () => {
				$window.location.href = "/vitazure/lista-de-consultas";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}
