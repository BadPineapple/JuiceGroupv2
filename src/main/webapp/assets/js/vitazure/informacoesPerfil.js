var informacoesPerfilApp = angular.module('informacoesPerfilApp', []);
informacoesPerfilApp.controller('InformacoesPerfilController', informacoesPerfilController);
var arquivos = [];

var formacaoInformadas = new Array();

var endercosInformadas = new Array();

var horarioAtendimentos = new Array();

var especialidadeAtendimentos = new Array();

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
	 const enderecoAtendimentoVO = enderecoSemanaHorario.split(',');	 
     this.enderecoAtendimento= new EnderecoAtendimento(enderecoAtendimentoVO[0],enderecoAtendimentoVO[1],enderecoAtendimentoVO[2],enderecoAtendimentoVO[3],enderecoAtendimentoVO[4],enderecoAtendimentoVO[5],enderecoAtendimentoVO[6],enderecoAtendimentoVO[7],enderecoAtendimentoVO[8]);
  }
}



function informacoesPerfilController($scope, $http, $window) {
    $scope.perfilProfissional = function () {
        perfilProfissional($scope, $http, $window);
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
}

function perfilProfissional($scope, $http, $window) {

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
	$scope.ProfissionalVH.formacaoAcademica = formacaoInformadas;
	$scope.ProfissionalVH.enderecoAtendimento = endercosInformadas;
	$scope.ProfissionalVH.especialidade = especialidadeAtendimentos;
	$scope.ProfissionalVH.temasTrabalho = temas;
	$scope.ProfissionalVH.horarioAtendimento = horarioAtendimentos;
	if(arquivos.length != 0){
		$scope.ProfissionalVH.profissional.pessoa.foto = arquivos;
    }
    
$http.post("/vitazure/perfilProfissional", $scope.ProfissionalVH)
        .then(function (response) {
            alert_success(response.data.message, () => {
				$window.location.href = "/vitazure/informacoes-perfil";
			});
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

function salvarConta($scope, $http, $window) {
    window.onload = function() {
        $scope.profissional.tipoConta = document.getElementById("tipoConta").checked;
        $scope.profissional.banco = document.getElementById("banco").checked;
        $scope.profissional.agencia = document.getElementById("agencia").checked;
        $scope.profissional.conta = document.getElementById("conta").checked;
        $scope.profissional.digitoVerificador = document.getElementById("digitoVerificador").checked;
        $scope.profissional.nomeFavorecido = document.getElementById("nomeFavorecido").checked;
    };

    $http.post("/api/v1/registrar-cartao", $scope.ProfissionalVH.profissional)
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
            alert_error(response.responseText);
        }
    });
}

function adicionar($scope, $http, $window) {
   	const formacaoAcademica = new FormacaoAcademica($scope.formacao , $scope.descricaoFormacao) 
	formacaoInformadas.push(formacaoAcademica);
	$("#tblCadastro td").remove();
    formacaoInformadas.forEach(AdicionarTabela);
	
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


function adicionarEndereco($scope, $http, $window) {
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
			"<td align='center'><a  id='btn-excluir' onclick='ExcluirEndereco("+indice+")' style='color: red;'><i class='fas fa-trash'></i></a></td>"+
			"</tr>");
		
};

function ExcluirEndereco(x){
	    endercosInformadas.splice(x, 1);
	     $("#tblCadastroEndereco td").remove();
	    endercosInformadas.forEach(AdicionarTabelaEndereco);
};




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
	const tema = new TemasTrabalho($scope.temasTrabalho);
	  temas.push(tema);
	  $("#panelFiltrosSelecionados a").remove();
      temas.forEach(AdicionarTabelaTema);
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
   	const horarioAtendimento = new HorarioAtenimento($scope.diaSemana,$scope.horaInicio,$scope.horaFim,$scope.horaAlmocoInicio,$scope.horaAlmocoFim,$scope.atendimentoOnline , $scope.enderecoSemanaHorario , $scope.atendimentoPresencial) 
	horarioAtendimentos.push(horarioAtendimento);
	$("#tblCadastroHorarioAtendimento td").remove();
    horarioAtendimentos.forEach(AdicionarTabelaHorarioAtendimento);
	
}

function AdicionarTabelaHorarioAtendimento(item, indice){
		$("#tblCadastroHorarioAtendimento tbody").append(
			"<tr>"+
			"<td align='center'>"+item.diaSemana+"</td>"+
			"<td align='center'>"+(item.horaInicio)+"</td>"+
			"<td align='center'>"+(item.horaFim)+"</td>"+
			"<td align='center'>"+(item.atendimentoOnline ? 'sim' : 'Não')+"</td>"+
			"<td align='center'>"+(item.atendimentoPresencial  ? 'sim' : 'Não')+"</td>"+
			"<td align='center'>"+(item.enderecoAtendimento.logradouro)+"</td>"+
			"<td align='center'><a  id='btn-excluir' onclick='ExcluirHorarioAtendimento("+indice+")' style='color: red;'><i class='fas fa-trash'></i></a></td>"+
			"</tr>");
		
};

function ExcluirHorarioAtendimento(x){
	    horarioAtendimentos.splice(x, 1);
	     $("#tblCadastroHorarioAtendimento td").remove();
	    horarioAtendimentos.forEach(AdicionarTabelaHorarioAtendimento);
};

function adicionarEspecialidades($scope, $http, $window){
	  const valor = new Especialidade($scope.especialidade);
	  especialidadeAtendimentos.push(valor);
	  $("#panelFiltrosEspecialidades a").remove();
      especialidadeAtendimentos.forEach(AdicionarTabelaEspecialidades);
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

        $scope.profissional.agencia = document.getElementById("agencia").checked;

        $scope.profissional.conta = document.getElementById("conta").checked;

        $scope.profissional.digitoVerificador = document.getElementById("digitoVerificador").checked;

        $scope.profissional.nomeFavorecido = document.getElementById("nomeFavorecido").checked;

    };


    $http.post("/api/v1/registrar-cartao", $scope.ProfissionalVH.profissional)

        .then(function (response) {

            alert_success(response.data.message, () => {

                $window.location.href = "/vitazure/informacoes-perfil";

        });

        }).catch(function (response) {

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
