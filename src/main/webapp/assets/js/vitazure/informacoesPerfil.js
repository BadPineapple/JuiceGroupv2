var informacoesPerfilApp = angular.module('informacoesPerfilApp', []);
informacoesPerfilApp.controller('InformacoesPerfilController', informacoesPerfilController);
var arquivos = [];

var formacaoInformadas = new Array();

var endercosInformadas = new Array();

var horarioAtendimentos = new Array();

var especialidadeAtendimentos = new Array();

var idEnderecoExcluir;
var indiceEnderecoExcluir;

var temas = new Array();


class FormacaoAcademica{
  constructor(tipoFormacao,descricaoFormacao) {
    this.tipoFormacao = tipoFormacao;
    this.descricaoFormacao = descricaoFormacao;
  }
}
class Especialidade{
  constructor(especialidade) {
    this.especialidade = especialidade;
  }
}
class TemasTrabalho{
  constructor(tema) {
    this.tema = tema;
  }
}


class EnderecoAtendimento{
  constructor(linkGoogleMaps,estado,logradouro,complemento,cep,cidade,bairro , numero , id) {
	 this.linkGoogleMaps= linkGoogleMaps;
	 this.estado= estado;
	 this.logradouro= logradouro;
	 this.complemento= complemento; 
	 this.cep= cep;
	 this.cidade= cidade; 
	 this.bairro= bairro;
	 this.numero= numero;
	 this.id= id;
  }
}

class HorarioAtenimento{
  constructor(diaSemana,horaInicio,horaFim,horaAlmocoInicio,horaAlmocoFim,atendimentoOnline , enderecoSemanaHorario , atendimentoPresencial) {
	 this.diaSemana= diaSemana;
	 this.horaInicio= horaInicio;
	 this.horaFim= horaFim;
	 this.horaAlmocoInicio= horaAlmocoInicio; 
	 this.horaAlmocoFim= horaAlmocoFim;
	 this.atendimentoOnline= atendimentoOnline;
	 this.atendimentoPresencial= atendimentoPresencial;
	 const enderecoAtendimentoVO = enderecoSemanaHorario.split('.,');	 
     this.enderecoAtendimento= new EnderecoAtendimento(enderecoAtendimentoVO[0],enderecoAtendimentoVO[1],enderecoAtendimentoVO[2],enderecoAtendimentoVO[3],enderecoAtendimentoVO[4],enderecoAtendimentoVO[5],enderecoAtendimentoVO[6],enderecoAtendimentoVO[7],enderecoAtendimentoVO[8]);
  }
}



function informacoesPerfilController($scope, $http, $window) {
   
    $scope.contatos = new Array();

    $scope.perfilProfissional = function (menuValidar) {
        perfilProfissional(menuValidar,$scope, $http, $window);
    };
	$scope.adicionarFormacao = function () {
        adicionar($scope, $http, $window);
    };
    
    $scope.adicionarEndereco = function () {
        adicionarEndereco($scope, $http, $window);
    };

 	$scope.formacaoProfissional = function () {
       formacaoProfissional($scope, $http, $window);
    };
 	$scope.enderecoAtendimento = function () {
       enderecoAtendimento($scope, $http, $window);
    };

 	$scope.especialidadeAtendimento = function () {
       especialidadeAtendimento($scope, $http, $window);
    };
 	$scope.temasAtendimento = function () {
       temasAtendimento($scope, $http, $window);
    };
 	$scope.horarioAtendimento = function () {
       horarioAtendimento($scope, $http, $window);
    };
 	$scope.adicionarTemas= function () {
       adicionarTemas($scope, $http, $window);
    };
    $scope.adicionarHorarioAtendimento= function () {
       adicionarHorarioAtendimento($scope, $http, $window);
    };
    $scope.adicionarEspecialidades= function () {
       adicionarEspecialidades($scope, $http, $window);
    };

	$scope.salvarConta = function () {
    	salvarConta($scope, $http, $window);
	}
	$scope.apresentarCampoData = function (apresentarCampo) {
    	apresentarCampoData(apresentarCampo);
	}
	$scope.apresentarCampoConsulta40Mes = function (apresentarCampo) {
    	apresentarCampoConsulta40Mes(apresentarCampo);
	}
	$scope.apresentarPrimeiraConsultaCortesia = function (apresentarCampo) {
    	apresentarPrimeiraConsultaCortesia(apresentarCampo);
	}
	$scope.validarCampo = function (apresentarCampo) {
    	validarCampo(apresentarCampo , $scope, $http, $window);
	}
	$scope.validarCampoFerias = function () {
    	validarCampoFerias($scope, $http, $window);
	}
	$scope.excluirEndereco = function () {
    	ExcluirEndereco($scope, $http, $window);
	}
	$scope.salvarListEndereco = function () {
    	salvarListEndereco($scope, $http, $window);
	}
	$scope.salvarListEnderecoAtendimento = function () {
    	salvarListEnderecoAtendimento($scope, $http, $window);
	}
	
}

function perfilProfissional(menu,$scope, $http, $window) {

	// $scope.ProfissionalVH.profissional.conselhoProfissional = document.getElementById("conselhoProfissional").checked;
	$scope.ProfissionalVH.profissional.avisoFerias = document.getElementById("avisoFerias").checked;
	$scope.ProfissionalVH.profissional.habilitarDesconto40 = document.getElementById("habilitarDesconto40").checked;
	$scope.ProfissionalVH.profissional.atendimentoPorLibras = document.getElementById("atendimentoPorLibras").checked;
	$scope.ProfissionalVH.profissional.primeiraConsultaCortesia = document.getElementById("primeiraConsultaCortesia").checked;
	$scope.ProfissionalVH.profissional.pacote2com5Desconto = document.getElementById("pacote2com5Desconto").checked;
	$scope.ProfissionalVH.profissional.pacote3com10Desconto = document.getElementById("pacote3com10Desconto").checked;
	$scope.ProfissionalVH.profissional.pacote4com15Desconto = document.getElementById("pacote4com15Desconto").checked;
	$scope.ProfissionalVH.profissional.convenio40 = document.getElementById("convenio40").checked;
	$scope.ProfissionalVH.profissional.convenio50 = document.getElementById("convenio50").checked;
	$scope.ProfissionalVH.profissional.convenio60 = document.getElementById("convenio60").checked;
	if($scope.ProfissionalVH.profissional.avisoFerias){
	 $scope.ProfissionalVH.profissional.dataInicioAvisoFerias = document.getElementById("dataInicioAvisoFerias").value;
	 $scope.ProfissionalVH.profissional.dataFimAvisoFerias = document.getElementById("dataFimAvisoFerias").value;		
	}else{
	 $scope.ProfissionalVH.profissional.dataInicioAvisoFerias = '';
	 $scope.ProfissionalVH.profissional.dataFimAvisoFerias = '';	
	}
	$scope.ProfissionalVH.formacaoAcademica = formacaoInformadas;
	$scope.ProfissionalVH.enderecoAtendimento = endercosInformadas;
	$scope.ProfissionalVH.especialidade = especialidadeAtendimentos;
	$scope.ProfissionalVH.temasTrabalho = temas;
	$scope.ProfissionalVH.horarioAtendimento = horarioAtendimentos;
	if(arquivos.length != 0){
		$scope.ProfissionalVH.profissional.pessoa.foto = arquivos;
    }
	
	var valorconsultaOnline = document.getElementById("valorconsultaOnline").value;
	$scope.ProfissionalVH.profissional.valorConsultaOnline = valorconsultaOnline.replace('.','').replace('.','').replace(',','.')
	var valorConsultaPresencial = document.getElementById("valorConsultaPresencial").value;
	$scope.ProfissionalVH.profissional.valorConsultaPresencial = valorConsultaPresencial.replace('.','').replace('.','').replace(',','.')
	
    $scope.ProfissionalVH.menuValidar= menu;
$http.post("/vitazure/perfilProfissional", $scope.ProfissionalVH)
        .then(function (response) {
            alert_success(response.data.message, () => {
				$window.location.href = "/vitazure/informacoes-perfil";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

$(function ($scope) {
    var wrapper = $(".wrapper-upload-area");

    wrapper.click(function () {
        $("#avatar").click();
    });

    $("#avatar").change(function () {

      var files =$(this).prop('files');

      var fd = new FormData();
        $(files).each(function (index, row) {
            fd.append('files', row);
        });
        uploadData($scope , fd);
    });
});				

function uploadData($scope , formdata) {
    $.ajax({
        url: '/vitazure/imagem',
        type: 'POST',
        data: formdata,
        contentType: false,
        processData: false,
        dataType: 'json',
        success: function (response) {
           this.file = response;
           arquivos = response;
           toast_success('Ótimo', 'Upload foi concluído com sucesso!');
        }, error: function (response) {
            console.log("Erro!!" + response.responseText);
            if(response.responseText.includes('exceeds the configured maximum') || response.responseText.includes('413')){
			  alert_error("Verifique o tamanho da imagem esta dentro do limite permitido");
			}else{
              alert_error(response.responseText);
			}
        }
    });
}

function adicionar($scope, $http, $window) {
	if($scope.formacao == 'undefined' || $scope.formacao == null || $scope.formacao == ''){
		 alert_error("Informar Formação");
	 }else if($scope.descricaoFormacao == 'undefined' || $scope.descricaoFormacao == null || $scope.descricaoFormacao == ''){
		 alert_error("Informar Descrição Formação");
	 }
     else{
	   	const formacaoAcademica = new FormacaoAcademica($scope.formacao , $scope.descricaoFormacao) 
		formacaoInformadas.push(formacaoAcademica);
		$("#tblCadastro td").remove();
	    formacaoInformadas.forEach(AdicionarTabela);
		$scope.descricaoFormacao = '';
		$scope.formacao = '';
	 }
}

function AdicionarTabela(item, indice){
		$("#tblCadastro tbody").append(
			"<tr>"+
			"<td align='center'>"+item.tipoFormacao+"</td>"+
			"<td align='center'>"+(item.descricaoFormacao)+"</td>"+
			"<td align='center'><a  id='btn-excluir' onclick='Excluir("+indice+")' style='color: red;'><i class='fas fa-trash'></i></a></td>"+
			"</tr>");
		
};

function Excluir(x){
	    formacaoInformadas.splice(x, 1);
	     $("#tblCadastro td").remove();
	    formacaoInformadas.forEach(AdicionarTabela);
};


function validarCamposEnderecoAtendimento(){
  if(document.getElementById("cep").value == 'undefined' || document.getElementById("cep").value == null || document.getElementById("cep").value == ''){
	alert_error("Informar CEP");
	return false;
  }else if(document.getElementById("uf").value == 'undefined' || document.getElementById("uf").value == null || document.getElementById("uf").value == ''){
	alert_error("Informar Estado");
	return false;
  }else  if(document.getElementById("logradouro").value == 'undefined' || document.getElementById("logradouro").value == null || document.getElementById("logradouro").value == ''){
	alert_error("Informar Logradouro");
	return false;
  }else  if(document.getElementById("cidade").value == 'undefined' || document.getElementById("cidade").value == null || document.getElementById("cidade").value == ''){
	alert_error("Informar Cidade");
	return false;
  }else if(document.getElementById("bairro").value == 'undefined' || document.getElementById("bairro").value == null || document.getElementById("bairro").value == ''){
	alert_error("Informar Bairro");
	return false;
  }else if(document.getElementById("numero").value == 'undefined' || document.getElementById("numero").value == null || document.getElementById("numero").value == ''){
	alert_error("Informar numero");
	return false;
  }else if(document.getElementById("complemento").value == 'undefined' || document.getElementById("complemento").value == null || document.getElementById("complemento").value == ''){
	alert_error("Informar complemento");
	return false;
  }
   return true;
}

function adicionarEndereco($scope, $http, $window) {
    if(validarCamposEnderecoAtendimento()){
	    const enderecoAtendimento = new EnderecoAtendimento(document.getElementById("linkGoogleMaps").value,document.getElementById("uf").value,document.getElementById("logradouro").value,document.getElementById("complemento").value,document.getElementById("cep").value,document.getElementById("cidade").value,document.getElementById("bairro").value,document.getElementById("numero").value) 
		endercosInformadas.push(enderecoAtendimento);
		$("#tblCadastroEndereco td").remove();
	    endercosInformadas.forEach(AdicionarTabelaEndereco);
		document.getElementById("linkGoogleMaps").value = "";
		document.getElementById("uf").value = "";
		document.getElementById("logradouro").value = "";
		document.getElementById("complemento").value = "";
		document.getElementById("cep").value = "";
		document.getElementById("cidade").value = "";
		document.getElementById("bairro").value = "";
		document.getElementById("numero").value = "";
		return true;
    }	
}

function AdicionarTabelaEndereco(item, indice){
		$("#tblCadastroEndereco tbody").append(
			"<tr>"+
//			"<td align='center'>"+item.cep+"</td>"+
			"<td align='center'>"+item.logradouro+"</td>"+
			"<td align='center'>"+item.complemento+"</td>"+
//			"<td align='center'>"+item.bairro+"</td>"+
			"<td align='center'>"+item.cidade+"</td>"+
//			"<td align='center'>"+item.estado+"</td>"+
			"<td align='center'>"+item.linkGoogleMaps+"</td>"+
			"<td align='center'><a  id='myBtn' onclick='abrirModal("+indice+")' style='color: red;'><i class='fas fa-trash'></i></a></td>"+
			"</tr>");
		
};

function ExcluirEndereco($scope, $http, $window){
	if(idEnderecoExcluir == 'undefined' || idEnderecoExcluir == null || idEnderecoExcluir == 0){
	   endercosInformadas.splice(indiceEnderecoExcluir, 1);
	     $("#tblCadastroEndereco td").remove();
	     endercosInformadas.forEach(AdicionarTabelaEndereco);
         fecharModal();
		
	}else{
		$http.get("/vitazure/excluirEndereco/"+idEnderecoExcluir)
        .then(function (response) {
           		 endercosInformadas.splice(indiceEnderecoExcluir, 1);
	            $("#tblCadastroEndereco td").remove();
	            endercosInformadas.forEach(AdicionarTabelaEndereco);
                fecharModal();
                 alert_success(response.data);
        }).catch(function (response) {
        alert_error("Não foi possível excluir o endereço, pois está vinculado a um horário de atendimento.");
        fecharModal();
    })	
	}	
	   
};


function abrirModal(x){
	idEnderecoExcluir = endercosInformadas[x].id;
	indiceEnderecoExcluir = x;
	var modal = document.getElementById("myModal");		
		var btn = document.getElementById("myBtn");		
		var span = document.getElementsByClassName("close")[0];
		  modal.style.display = "block";
       span.onclick = function() {
		  modal.style.display = "none";
		}
}

function fecharModal(){
	var modal = document.getElementById("myModal");
	modal.style.display = "none";	
}


function formacaoProfissional($scope, $http, $window) {
    id = $scope.ProfissionalVH.profissional.id;
    $http.get("/vitazure/formacao/"+id)
        .then(function (response) {
           		 for (var i = 0; i < response.data.length; i++) {
           		     formacaoInformadas.push(response.data[i]);
           			$("#tblCadastro td").remove();
           			formacaoInformadas.forEach(AdicionarTabela);
    			}
        }).catch(function (response) {
        alert_error(response.data.message);
    })
};

function enderecoAtendimento($scope, $http, $window) {
    id = $scope.ProfissionalVH.profissional.id;
    $http.get("/vitazure/enderecoAtendimento/"+id)
        .then(function (response) {
           		 for (var i = 0; i < response.data.length; i++) {
           		     endercosInformadas.push(response.data[i]);
           			$("#tblCadastroEndereco td").remove();
           			endercosInformadas.forEach(AdicionarTabelaEndereco);
    			}
        }).catch(function (response) {
        alert_error(response.data.message);
    })
};

function especialidadeAtendimento($scope, $http, $window) {
    id = $scope.ProfissionalVH.profissional.id;
    $http.get("/vitazure/especialidadeAtendimento/"+id)
        .then(function (response) {
           		 for (var i = 0; i < response.data.length; i++) {
           		     especialidadeAtendimentos.push(response.data[i]);
           			$("#panelFiltrosEspecialidades a").remove();
           			especialidadeAtendimentos.forEach(AdicionarTabelaEspecialidades);
    			}
        }).catch(function (response) {
        alert_error(response.data.message);
    })
};

function temasAtendimento($scope, $http, $window) {
    id = $scope.ProfissionalVH.profissional.id;
    $http.get("/vitazure/temasAtendimento/"+id)
        .then(function (response) {
           		 for (var i = 0; i < response.data.length; i++) {
           		     temas.push(response.data[i]);
           			$("#panelFiltrosSelecionados a").remove();
           			temas.forEach(AdicionarTabelaTema);
    			}
        }).catch(function (response) {
        alert_error(response.data.message);
    })
};

function horarioAtendimento($scope, $http, $window) {
    id = $scope.ProfissionalVH.profissional.id;
    $http.get("/vitazure/horarioAtendimento/"+id)
        .then(function (response) {
           		 for (var i = 0; i < response.data.length; i++) {
           		     horarioAtendimentos.push(response.data[i]);
           			$("#tblCadastroHorarioAtendimento td").remove();
           			horarioAtendimentos.forEach(AdicionarTabelaHorarioAtendimento);
    			}
        }).catch(function (response) {
        alert_error(response.data.message);
    })
};

function adicionarTemas($scope, $http, $window){
	if($scope.temasTrabalho == 'undefined' || $scope.temasTrabalho == null){
		 alert_error("Selecionar Item Para Adicionar");
	  }else{
	  const tema = new TemasTrabalho($scope.temasTrabalho);
	  validarTemaAdicionado(tema);
	  $("#panelFiltrosSelecionados a").remove();
      temas.forEach(AdicionarTabelaTema);
	}
}

function AdicionarTabelaTema(item, indice){
		$("#panelFiltrosSelecionados").append(
			"<a type='text' onclick='ExcluirTema("+indice+")' class='line' style='height: 26px !important;margin: 5px 0 !important;box-shadow: 3px 3px 6px #7f6038;padding: 3px 12px;'>"+
			  item.tema+
	       "<i class='fas fa-times' style='color: #f11616;padding-left: 11px;'></i>"+
			"</a>"
			);		
};
function ExcluirTema(x){
	    temas.splice(x, 1);
	     $("#panelFiltrosSelecionados a").remove();
	     temas.forEach(AdicionarTabelaTema);
};

function adicionarHorarioAtendimento($scope, $http, $window) {
	if(validarCampos($scope.diaSemana,$scope.horaInicio,$scope.horaFim,$scope.atendimentoOnline , $scope.enderecoSemanaHorario , $scope.atendimentoPresencial)){
	   	const horarioAtendimento = new HorarioAtenimento($scope.diaSemana,$scope.horaInicio,$scope.horaFim,$scope.horaAlmocoInicio,$scope.horaAlmocoFim,$scope.atendimentoOnline , $scope.enderecoSemanaHorario , $scope.atendimentoPresencial) 
		horarioAtendimentos.push(horarioAtendimento);
		$("#tblCadastroHorarioAtendimento td").remove();
	    horarioAtendimentos.forEach(AdicionarTabelaHorarioAtendimento);
        $scope.diaSemana = '';
        $scope.horaInicio= '';
        $scope.horaFim= '';
        $scope.atendimentoOnline = false; 
        $scope.enderecoSemanaHorario = '';
        $scope.atendimentoPresencial = false;
		document.getElementById("divEndereco").style.display = "none"; 
		return true;
    }
	
}

function validarCampos(diaSemana,horaInicio,horaFim, atendimentoOnline , enderecoSemanaHorario , atendimentoPresencial){
	if(diaSemana == 'undefined' || diaSemana == null || diaSemana == ''){
		alert_error("Informar Dia da Semana");
		return false;
	}else if(horaInicio == 'undefined' || horaInicio == null || horaInicio == ''){
		alert_error("Informar Hora Inicio");
		return false;
	}else if(horaFim == 'undefined' || horaFim == null || horaFim == ''){
		alert_error("Informar Hora Fim");
		return false;
	}else if((enderecoSemanaHorario == 'undefined' && atendimentoPresencial == true ) || (enderecoSemanaHorario == null && atendimentoPresencial == true ) || (enderecoSemanaHorario == '' && atendimentoPresencial == true )){
		alert_error("Informar Endereço Atendimento");
		return false;
	}else if((atendimentoOnline == 'undefined' && atendimentoPresencial == 'undefined') || (atendimentoOnline == null && atendimentoPresencial == null) || (atendimentoOnline == 'false' && atendimentoPresencial  == 'false') || (atendimentoOnline == false && atendimentoPresencial  == false)){
		alert_error("Informar Tipo Atendimento Online ou Presencial");
		return false;
	}
//	for(var i = 0; i < horarioAtendimentos.length; i++){
//		if(diaSemana == horarioAtendimentos[i].diaSemana && (horaInicio > horarioAtendimentos[i].horaInicio && horaInicio < horarioAtendimentos[i].horaFim && horaFim > horarioAtendimentos[i].horaInicio && horaFim < horarioAtendimentos[i].horaFim)){
//			alert("Ja Existe")
//			return false;
//		}
//	}
	
	return true;
}

function AdicionarTabelaHorarioAtendimento(item, indice){
		$("#tblCadastroHorarioAtendimento tbody").append(
			"<tr>"+
			"<td align='center'>"+item.diaSemana+"</td>"+
			"<td align='center'>"+(item.horaInicio)+"</td>"+
			"<td align='center'>"+(item.horaFim)+"</td>"+
			"<td align='center'>"+(item.atendimentoOnline ? 'sim' : 'Não')+"</td>"+
			"<td align='center'>"+(item.atendimentoPresencial  ? 'sim' : 'Não')+"</td>"+
			"<td align='center'>"+(typeof item.enderecoAtendimento ===  "undefined" || typeof item.enderecoAtendimento.id ===  "undefined" || item.enderecoAtendimento.id ===  "undefined" ? '-' : item.enderecoAtendimento.logradouro)+"</td>"+
			"<td align='center'><a  id='btn-excluir' onclick='ExcluirHorarioAtendimento("+indice+")' style='color: red;'><i class='fas fa-trash'></i></a></td>"+
			"</tr>");
		
};

function ExcluirHorarioAtendimento(x){
	    horarioAtendimentos.splice(x, 1);
	     $("#tblCadastroHorarioAtendimento td").remove();
	    horarioAtendimentos.forEach(AdicionarTabelaHorarioAtendimento);
};

function adicionarEspecialidades($scope, $http, $window){
	  if($scope.especialidade == 'undefined' || $scope.especialidade == null){
		 alert_error("Selecionar Item Para Adicionar");
	  }else{
	  const valor = new Especialidade($scope.especialidade);
	  validarEspecialidadeAdicionado(valor);
	  $("#panelFiltrosEspecialidades a").remove();
      especialidadeAtendimentos.forEach(AdicionarTabelaEspecialidades);
	}
}



function AdicionarTabelaEspecialidades(item, indice){
		$("#panelFiltrosEspecialidades").append(
			"<a type='text' onclick='ExcluirEspecialidades("+indice+")' class='line' style='height: 26px !important;margin: 5px 0 !important;box-shadow: 3px 3px 6px #7f6038;padding: 3px 12px;'>"+
			  item.especialidade+
	       "<i class='fas fa-times' style='color: #f11616;padding-left: 11px;'></i>"+
			"</a>"
			);		
};
function ExcluirEspecialidades(x){
	    especialidadeAtendimentos.splice(x, 1);
	     $("#panelFiltrosEspecialidades a").remove();
	     especialidadeAtendimentos.forEach(AdicionarTabelaEspecialidades);
};

function salvarConta($scope, $http, $window) {

    window.onload = function() {

        $scope.profissional.tipoConta = document.getElementById("tipoConta").checked;

        $scope.profissional.banco = document.getElementById("banco").checked;

        $scope.profissional.agencia = document.getElementById("agencia").checked;R

        $scope.profissional.conta = document.getElementById("conta").checked;

        $scope.profissional.digitoVerificador = document.getElementById("digitoVerificador").checked;

        $scope.profissional.nomeFavorecido = document.getElementById("nomeFavorecido").checked;
	
    };
	
	var valorconsultaOnline = document.getElementById("valorconsultaOnline").value;
	$scope.ProfissionalVH.profissional.valorConsultaOnline = valorconsultaOnline.replace('.','').replace(',','.')
	var valorConsultaPresencial = document.getElementById("valorConsultaPresencial").value;
	$scope.ProfissionalVH.profissional.valorConsultaPresencial = valorConsultaPresencial.replace('.','').replace(',','.')
	document.getElementById("spinner").style.display = "inline-block";
	
    $http.post("/api/v1/registrar-cartao", $scope.ProfissionalVH.profissional)

        .then(function (response) {
			document.getElementById("spinner").style.display = "none";
            alert_success(response.data.message, () => {
                $window.location.href = "/vitazure/informacoes-perfil";
        });					

        }).catch(function (response) {
        document.getElementById("spinner").style.display = "none";
        alert_error(response.data.message);

    })




};

function apresentarCampoData(campo){
	  if(campo === 'true' || campo){
		document.getElementById("divCampoDataInicialAviso").style.display = "inline-block";
		document.getElementById("divCampoFinalDataAviso").style.display = "inline-block";
	  }else{
		document.getElementById("divCampoDataInicialAviso").style.display = "none"; 
		document.getElementById("divCampoFinalDataAviso").style.display = "none"; 
	  }
  }
function apresentarCampoConsulta40Mes(campo){
	  if(campo === 'true' || document.getElementById("habilitarDesconto40").checked){
		document.getElementById("divQuantidadeConsulta40Mes").style.display = "inline-block";
	  }else{
		document.getElementById("divQuantidadeConsulta40Mes").style.display = "none"; 
	  }
}

function apresentarPrimeiraConsultaCortesia(campo){
	  if(campo === 'true' || document.getElementById("primeiraConsultaCortesia").checked){
		document.getElementById("divPrimeiraConsultaCortesia").style.display = "inline-block";
	  }else{
		document.getElementById("divPrimeiraConsultaCortesia").style.display = "none"; 
	  }
}

function validarHora(valor , campoInformado){
	if(valor.length < 5){
		alert_error("Verificar Valor Informado.");
		document.getElementById(campoInformado).value = "";
	}else if(valor.substring(0,2) > 23){
		alert_error("Verificar Valor Informado.");
		document.getElementById(campoInformado).value = "";
	}else if(valor.substring(3,5) > 59){
		alert_error("Verificar Valor Informado.");
		document.getElementById(campoInformado).value = "";
	}else if(document.getElementById("horaFim").value !=  '' && (document.getElementById("horaInicio").value > document.getElementById("horaFim").value)){
		alert_error("Hora Início não pode ser maior que hora fim.");
		document.getElementById(campoInformado).value = "";
	}else if(document.getElementById("horaFim").value !=  '' && document.getElementById("horaInicio").value != ''){
		 var dtChegada  = document.getElementById("horaFim").value;
	  	 var dtPartida = document.getElementById("horaInicio").value;
		 validarTempoMinimo(dtPartida,dtChegada , campoInformado);
	}
}

function validarTempoMinimo(horaInicio , horaFim , campoInformado){
	  var tempoMinimoConsulta = document.getElementById("duracaoAtendimento").value == 'NAO_INFORMADO' || document.getElementById("duracaoAtendimento").value == 'QUARENTA_MINUTOS' ? 40 : document.getElementById("duracaoAtendimento").value == 'CINQUENTA_MINUTOS' ? 50 : 60;
	  var ms = moment(horaFim,"HH:mm").diff(moment(horaInicio,"HH:mm"));
	  var d = moment.duration(ms);
	  var hora = Math.floor(d.asHours());
	  var minuto = Math.floor(moment.utc(ms).format(" mm"));
	  if(hora == 0 && minuto < tempoMinimoConsulta){
		alert_error("O intervalo entre horarios não pode ser menor que o tempo de duração atendimento.");
		document.getElementById(campoInformado).value = "";
	  }
}


function validarEspecialidadeAdicionado(obj){
	let jaAdicionado = false;	
	for(var i = 0; i < especialidadeAtendimentos.length; i++){
         if(especialidadeAtendimentos[i].especialidade == obj.especialidade){
         	jaAdicionado = true;
         }   
      }

    if(!jaAdicionado){
      especialidadeAtendimentos.push(obj);	
    }else{
      alert_error('Especialidade já adicionada');	
    }

};
function validarTemaAdicionado(obj){
	let jaAdicionado = false;	
	for(var i = 0; i < temas.length; i++){
         if(temas[i].tema == obj.tema){
         	jaAdicionado = true;
         }   
      }

    if(!jaAdicionado){
      temas.push(obj);	
    }else{
      alert_error('Tema já adicionada');	
    }

};

function validarCampo(campo , $scope, $http, $window){
	if(campo == 'undefined' || campo == null || campo == ''){
		alert_error("Informar sobre mim.");
	}else{
		perfilProfissional('',$scope, $http, $window);
	}
}
function validarCampoFerias($scope, $http, $window){
		
	if(document.getElementById("avisoFerias").checked && (document.getElementById("dataInicioAvisoFerias").value == 'undefined' || document.getElementById("dataInicioAvisoFerias").value == null || document.getElementById("dataInicioAvisoFerias").value == '')){
		alert_error("Informar data inicio férias.");
	}else if(document.getElementById("avisoFerias").checked && (document.getElementById("dataFimAvisoFerias").value == 'undefined' || document.getElementById("dataFimAvisoFerias").value == null || document.getElementById("dataFimAvisoFerias").value == '')){
		alert_error("Informar data fim férias.");
	}
	else{
		perfilProfissional('',$scope, $http, $window);
	}
}
function salvarListEndereco($scope, $http, $window){
	if(validarItemPreenchido($scope, $http, $window)){
	   perfilProfissional('',$scope, $http, $window);
	}
}

function validarItemPreenchido($scope, $http, $window){
	if(document.getElementById("cep").value != ''){
	return adicionarEndereco($scope, $http, $window);
  }else  if(document.getElementById("logradouro").value != ''){
	return adicionarEndereco($scope, $http, $window);
  }else  if(document.getElementById("cidade").value != ''){
	return adicionarEndereco($scope, $http, $window);
  }else if(document.getElementById("bairro").value != ''){
	return adicionarEndereco($scope, $http, $window);
  }else if(document.getElementById("numero").value != ''){
	return adicionarEndereco($scope, $http, $window);
  }else if(document.getElementById("complemento").value != ''){
	return adicionarEndereco($scope, $http, $window);
  }
   return true;
}


function salvarListEnderecoAtendimento($scope, $http, $window){
	if(validarItemPreenchidoEndereco($scope, $http, $window)){
	   perfilProfissional('',$scope, $http, $window);
	}
}
function validarItemPreenchidoEndereco($scope, $http, $window){
	if($scope.diaSemana != ''){
		return adicionarHorarioAtendimento($scope, $http, $window);
	}else if($scope.horaInicio != ''){
		return adicionarHorarioAtendimento($scope, $http, $window);
	}else if($scope.horaFim != ''){
		return adicionarHorarioAtendimento($scope, $http, $window);
	}else if($scope.enderecoSemanaHorario != ''){
		return adicionarHorarioAtendimento($scope, $http, $window);
	}
	
	return true;
}

function validarValor(campo , campoInformado){
	  let valor = campo.replace('.','').replace(',','.');
	  if(valor > 10000.00){
		 alert_error('O valor limite para este cadastro é de R$ 10.000,00');
		 document.getElementById(campoInformado).value = "";
	  }
  }
