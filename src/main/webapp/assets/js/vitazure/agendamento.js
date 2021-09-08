var agendamentoApp = angular.module('agendamentoApp', []);

var idTemp = '';

var idHoraTemp = '';

var tipoAgendamento = '';

var dataSelecionada = '';

var idTempSelecionado = '';

var idRemoveHist = '';

var idProfissionalHorario = '';


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
    $scope.efetuarPagamento = function (id ,valorOnline ,valorPresencial) {
        efetuarPagamento($scope, $http, $window , id,valorOnline ,valorPresencial);
    };
     $scope.definirAgendamento = function (idAgenda, situacaoAlterar) {
        definirAgendamento($scope, $http, $window , idAgenda, situacaoAlterar);
    };
     $scope.consultarAgenda = function () {
        consultarAgenda($scope, $http, $window);
    };
	
    $scope.consultarProfissional = function () {
        consultarProfissional($scope, $http, $window);
    };
	$scope.buscaCidades = function () {
        buscaCidades($scope, $http, $window);
    };
	
	 $scope.concluirReagendamento = function (idProfissional , idAgendaReagendada) {
        concluirReagendamento($scope, $http, $window , idProfissional , idAgendaReagendada);
    };
	 $scope.opcaoReagendamento = function (idProfissional , tipo) {
        var tipoAgendamento = "";
       if(tipo){
			tipoAgendamento = "online";
	   }else{
		tipoAgendamento = "presencial";
	}
		opcaoReagendamento(idProfissional , tipoAgendamento);
    };

}

function consultarDatasProfissional($scope, $http, $window , id , profissional) {
   
    document.getElementById(profissional+'.'+id).className = "dias active slick-slide slick-current slick-active";
	if(idTemp != ''){
	  document.getElementById(idTemp).className = "dias slick-slide slick-cloned";
	} 
	 idTemp = profissional+'.'+id;
     dataSelecionada = id;
	 definirTipo(profissional);
	 if(idRemoveHist != '' && idRemoveHist != profissional){
		removerHorariosHist(profissional);
	 }else{
		idRemoveHist = profissional;
	}
	
	$http.get("/vitazure/consultarDatasProfissional/"+id+"/"+profissional)
        .then(function (response) {
	           var idExcluir = "#panelFiltrosSelecionados"+profissional+" a";
           		$(idExcluir).remove();
	            horariosDisponiveisAtendimento = new Array();
                 horariosDisponiveisAtendimento = new Array();
			   var enderecoProfissional = "#enderecoProfissional"+profissional+" strong";
		       var enderecoLinkLocaliazacaoProfissional = "#enderecoLinkLocaliazacaoProfissional"+profissional +" a";
		       $(enderecoProfissional).remove();
		       $(enderecoLinkLocaliazacaoProfissional).remove();
           		 for (var i = 0; i < response.data.length; i++) {
           		    horariosDisponiveisAtendimento.push(response.data[i]);
    			}
           		horariosDisponiveisAtendimento.forEach(AdicionarHorariosDisponiveis);
        }).catch(function (response) {
          alert(response.data);
    })
}


function AdicionarHorariosDisponiveis(item, indice){
	    var idIncluir = "#panelFiltrosSelecionados"+item.codigoProfissional;
        var idHora = indice+"/"+item.codigoProfissional;
		$(idIncluir).append(
			"<a type='text' id='"+idHora+"'  onclick='selecionarHora("+indice+","+item.codigoProfissional+" )' class='line' style='cursor: pointer;background: #B8DFED;border-radius: 10px;text-align: center; margin: 10px 10px;transition: all .3s ease-in-out;padding: 5px; width: 74px;font-size: 1.6rem;line-height: 2rem;font-weight: 700;'>"+
			  item.horaPossivelAtendiemnto+
			"</a>"
			);	
};

function selecionarHora(id , codigoProfissional){
	var idHora = id+"/"+codigoProfissional;
    idProfissionalHorario = codigoProfissional;
	document.getElementById(idHora).style = "border: 3px solid #83B2CD;background: #B8DFED;border-radius: 10px;text-align: center; margin: 10px 10px;transition: all .3s ease-in-out;padding: 5px; width: 74px;font-size: 1.6rem;line-height: 2rem;font-weight: 700;";
	if(idTempSelecionado != ''){
	  document.getElementById(idTempSelecionado).style = "background: #B8DFED;border-radius: 10px;text-align: center; margin: 10px 10px;transition: all .3s ease-in-out;padding: 5px; width: 74px;font-size: 1.6rem;line-height: 2rem;font-weight: 700;";
	} 
	 idHoraTemp = id;
     idTempSelecionado = idHora;
      var enderecoProfissional = "#enderecoProfissional"+codigoProfissional+" strong";
      var enderecoLinkLocaliazacaoProfissional = "#enderecoLinkLocaliazacaoProfissional"+codigoProfissional +" a";
      $(enderecoProfissional).remove();
      $(enderecoLinkLocaliazacaoProfissional).remove();
	 if(definirTipo(codigoProfissional) == 'presencial'){
		for (var i = 0; i < horariosDisponiveisAtendimento.length; i++) {
	        if(id == i){
		       var enderecoProfissional = "#enderecoProfissional"+codigoProfissional;
		       $(enderecoProfissional).append(
		          "<strong style='font-size: 15px;'>"+horariosDisponiveisAtendimento[i].enderecoatendimento+"</strong>"
				);
			   var enderecoLinkLocaliazacaoProfissional = "#enderecoLinkLocaliazacaoProfissional"+codigoProfissional;		
		       $(enderecoLinkLocaliazacaoProfissional).append(
		          "<a href='"+horariosDisponiveisAtendimento[i].linkGoogleMaps+"' target='_blank' class='localizacao line'>"+
		             "<img src='../../assets/images/localizacao.png'>"+
		             "Confira localização no Mapa "+
		           "</a>"
				);	
	        }
	    }
	}
};

function marcardesmarcar(idProfissional,id) {
	if(id == 'online'){
  		 document.getElementById(idProfissional+'.online').className = "active marcar"
         document.getElementById(idProfissional+'.presencial').className = ""
         document.getElementById(idProfissional+'.valorOnline').style.display = "inline-block";
		document.getElementById(idProfissional+'.valorPresencial').style.display = "none";
	}else{
		 document.getElementById(idProfissional+'.online').className = ""
         document.getElementById(idProfissional+'.presencial').className = "active marcar"
		 document.getElementById(idProfissional+'.valorOnline').style.display = "none";
		 document.getElementById(idProfissional+'.valorPresencial').style.display = "inline-block";
	}
	
	tipoAgendamento = id;
}
function opcaoReagendamento(idProfissional,id) {
	if(id == 'online'){
  		 document.getElementById(idProfissional+'.online').className = "active marcar"
         document.getElementById(idProfissional+'.presencial').className = ""
         document.getElementById(idProfissional+'.valorOnline').style.display = "inline-block";
		document.getElementById(idProfissional+'.valorPresencial').style.display = "none";
	}else{
		 document.getElementById(idProfissional+'.online').className = ""
         document.getElementById(idProfissional+'.presencial').className = "active marcar"
		 document.getElementById(idProfissional+'.valorOnline').style.display = "none";
		 document.getElementById(idProfissional+'.valorPresencial').style.display = "inline-block";
	}
	tipoAgendamento = id;
}

function agendar(respostaPagamento ,$scope, $http, $window , id) {
	var idProfissional = id;
	var horarioPossivelAtendimento  = horariosDisponiveisAtendimento[idHoraTemp].horaPossivelAtendiemnto;
	var tipoAtendimento  = definirTipo(idProfissional);
	var data  = dataSelecionada;
	var retornoToken = JSON.stringify({
    "idProfissional":idProfissional,
    "horarioPossivelAtendimento":horarioPossivelAtendimento,
    "dataAtendimento":data,
    "tipoAtendimento":tipoAtendimento,
    "token": respostaPagamento.token,
    "payment_method": respostaPagamento.payment_method,
  });

$http.post("/vitazure/agendar/" , retornoToken)
        .then(function (response) {
            alert_success(response.data.message, () => {
	            document.getElementById("spinner").style.display = "none";
				$window.location.href = "/vitazure/telaAgradecimento";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}


function efetuarPagamento($scope, $http, $window , id ,valorOnline ,valorPresencial) {
    var confirma = 0;
    var tipoAtendimento  = definirTipo(id);
    document.getElementById("spinner").style.display = "inline-block";
    if(idProfissionalHorario != id){
      alert_error("Hora Agendamento Não Informado");
	  document.getElementById("spinner").style.display = "none";
    }else{
	    $.ajax({
	        url: '/api/v1/getencryption',
	        type: 'GET',
	        contentType: 'text/plain',
	        error: function (data, textStatus, xhr) {
	            alert(data.responseText + " error");
	            confirma = 0;
	        }
	    }).done(function (data, textStatus, jqXHR) {
	        confirma = 1;
	    }).then(function (data, textStatus, jqXHR) {
	        var ccae6d912a41bfefd569a77b5cd86603cde92e53cdd45813cba9e5bf080b3734 =  jqXHR.responseText;
	        var valorTotal = (definirTipo(id) == 'online' ? valorOnline : valorPresencial) * 100;
	        var button = document.querySelector('button');
	        function handleSuccess(data) {
		        document.getElementById("spinner").style.display = "inline-block";
	            agendar(data ,$scope, $http, $window , id);
	        }
	        function handleError(data) {
	            console.log(data);
	        }
	        if (confirma === 1) {
	            var checkout = new PagarMeCheckout.Checkout({
	                encryption_key: ccae6d912a41bfefd569a77b5cd86603cde92e53cdd45813cba9e5bf080b3734,
	                success: handleSuccess,
	                error: handleError
	            });
	            document.getElementById("spinner").style.display = "none";
	            checkout.open({
	                paymentButtonText: 'Finalizar',
					amount: valorTotal,
					maxInstallments: 1,
					defaultInstallment: 1,
					customerData: 'true',
					createToken: 'true',
					postbackUrl: 'https://www.vitazure.com.br/api/v1/retornoPagarMe',
					paymentMethods: 'credit_card',
					uiColor: '#0097D6',
					boletoDiscountPercentage: 0,
					boletoExpirationDate: '12/12/2021',
					pixExpirationDate: '2021-12-31',
	                items: [{
	                    id: id,
	                    title: "Pagamento Agendamento Consulta",
	                    unit_price: 10000,
	                    quantity: 1,
	                    tangible: 'false'
	                }]
	            });
	        }
	    });
    }
}

function definirAgendamento($scope, $http, $window , idAgenda , situacaoAlterar) {
var jsonAlterar = JSON.stringify({
    "idAgenda":idAgenda,
    "situacaoAlterar":situacaoAlterar
  });	
$http.post("/vitazure/alterarSituacaoAgenda" , jsonAlterar)
        .then(function (response) {
            alert_success(response.data.message, () => {
				$window.location.href = "/vitazure/lista-de-consultas";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

function consultarAgenda($scope, $http, $window) {
	
	$http.get("/vitazure/consultarDatasProfissional/"+id+"/"+profissional)
        .then(function (response) {
        }).catch(function (response) {
          alert(response);
    })
}

function definirTipo(idProfissional){
  var online =  document.getElementById(idProfissional+'.online').className;
  var presencial = document.getElementById(idProfissional+'.presencial').className;
  if(presencial.includes('active')){
	return 'presencial';
  }else{
	return 'online';
 }  
}
function removerHorariosHist(profissional){
   var idExcluir = "#panelFiltrosSelecionados"+idRemoveHist+" a";
   $(idExcluir).remove();
  idRemoveHist = profissional;
}

function consultarProfissional($scope, $http, $window) {
	var cidade = document.getElementById("cidade").value === '' ? null : document.getElementById("cidade").value;
    $http.get("/resultado-de-busca/"+$scope.palavraChave+"/"+$scope.especialista+"/"+$scope.estado+"/"+cidade)
        .then(function (response) {
           $window.location.href = "/vitazure/profissionais";
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

function buscaCidades($scope, $http, $window) {
 if($scope.estado != "" && typeof $scope.estado != 'undefined'){
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

function concluirReagendamento($scope, $http, $window , idProfissional , idAgendaReagendada) {
	 document.getElementById("spinner").style.display = "inline-block";
	var idProfissional = idProfissional;
	var idAgendaReagendada = idAgendaReagendada;
	var horarioPossivelAtendimento  = horariosDisponiveisAtendimento[idHoraTemp].horaPossivelAtendiemnto;
	var tipoAtendimento  = definirTipo(idProfissional);
	var data  = dataSelecionada;
	var jsonReagendamento = JSON.stringify({
    "idProfissional":idProfissional,
    "horarioPossivelAtendimento":horarioPossivelAtendimento,
    "dataAtendimento":data,
    "tipoAtendimento":tipoAtendimento,
    "idAgendaReagendada":idAgendaReagendada,    
  });

$http.post("/vitazure/concluirReagendamento/" , jsonReagendamento)
        .then(function (response) {
            alert_success(response.data.message, () => {
	            document.getElementById("spinner").style.display = "none";
				$window.location.href = "/vitazure/telaAgradecimento";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
		document.getElementById("spinner").style.display = "none";
    })
}