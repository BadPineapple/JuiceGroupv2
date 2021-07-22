var informacoesPerfilApp = angular.module('informacoesPerfilApp', []);
var arquivos = []

var formacaoInformadas = new Array();

var endercosInformadas = new Array();



class FormacaoAcademica{
  constructor(tipoFormacao,descricaoFormacao) {
    this.tipoFormacao = tipoFormacao;
    this.descricaoFormacao = descricaoFormacao;
  }
}


class EnderecoAtendimento{
  constructor(linkGoogleMaps,estado,logradouro,complemento,cep,cidade,bairro , numero) {
	 this.linkGoogleMaps= linkGoogleMaps;
	 this.estado= estado;
	 this.logradouro= logradouro;
	 this.complemento= complemento; 
	 this.cep= cep;
	 this.cidade= cidade; 
	 this.bairro= bairro;
	 this.numero= numero;
  }
}

informacoesPerfilApp.controller('InformacoesPerfilController', informacoesPerfilController);

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
}

function perfilProfissional($scope, $http, $window) {
	
	$scope.ProfissionalVH.profissional.avisoFerias = document.getElementById("avisoFerias").checked;
	$scope.ProfissionalVH.profissional.habilitarDesconto40 = document.getElementById("habilitarDesconto40").checked;
	$scope.ProfissionalVH.profissional.atendimentoPorLibras = document.getElementById("atendimentoPorLibras").checked;
	$scope.ProfissionalVH.profissional.primeiraConsultaCortesia = document.getElementById("primeiraConsultaCortesia").checked;
	$scope.ProfissionalVH.profissional.pacote2com30Desconto = document.getElementById("pacote2com30Desconto").checked;
	$scope.ProfissionalVH.profissional.pacote3com40Desconto = document.getElementById("pacote3com40Desconto").checked;
	$scope.ProfissionalVH.profissional.pacote4com50Desconto = document.getElementById("pacote4com50Desconto").checked;
	$scope.ProfissionalVH.profissional.convenio20 = document.getElementById("convenio20").checked;
	$scope.ProfissionalVH.profissional.convenio30 = document.getElementById("convenio30").checked;
	$scope.ProfissionalVH.profissional.convenio40 = document.getElementById("convenio40").checked;
	$scope.ProfissionalVH.profissional.convenio50 = document.getElementById("convenio50").checked;
	$scope.ProfissionalVH.profissional.convenio60 = document.getElementById("convenio60").checked;
	$scope.ProfissionalVH.formacaoAcademica = formacaoInformadas;
	$scope.ProfissionalVH.enderecoAtendimento = endercosInformadas;
	
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