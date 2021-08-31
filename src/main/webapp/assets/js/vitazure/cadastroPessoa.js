var cadastroPessoaApp = angular.module('cadastroPessoaApp', []);

cadastroPessoaApp.controller('CadastroPessoaController', cadastroPessoaController);

var arquivos = [];

function cadastroPessoaController($scope, $http, $window) {
    $scope.submit = function () {
        post($scope, $http, $window);
    };

	$scope.completarCadastro = function () {
        completarCadastro($scope, $http, $window);
    };
}

function post($scope, $http, $window) {
    if($scope.tipoConta != '' && $scope.tipoConta == 'CL'){
	  $scope.pessoa.cliente='true';
	  $scope.pessoa.psicologo='false';
	}
	if($scope.tipoConta != '' && $scope.tipoConta == 'PS'){
		$scope.pessoa.psicologo='true';
		$scope.pessoa.cliente='false';
	}
	if(arquivos.length != 0){
		$scope.pessoa.foto = arquivos;
    }
    $http.post("/vitazure/pessoa", $scope.pessoa)
        .then(function (response) {
            alert_success(response.data.message, () => {
                $window.location.href = "/cadastre-se";
            });
        }).catch(function (response) {
        alert_error(response.data.message);
    })
}

function completarCadastro($scope, $http, $window) {
    
	if(arquivos.length != 0){
		$scope.pessoa.foto = arquivos;
    }
    $http.post("/vitazure/completarCadastro", $scope.pessoa)
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
            if(response.responseText.includes('exceeds the configured maximum') || response.responseText.includes('413')){
			  alert_error("Verifique o tamanho da imagem esta dentro do limite permitido");
			}else{
              alert_error(response.responseText);
			}
        }
    });
}


function validarCep(e) {
	let valorCep = e;
	if( ! valorCep || valorCep.trim() == '' || valorCep.length < 9 ) {
		alert_error('CEP não informado ou invalido.');
		return;
	}

	let urlService = 'https://viacep.com.br/ws/' + valorCep.replace(/[.-]/, "") + '/json/'
	
	$.get(urlService, _processaRetornoCep);
	
}

function _processaRetornoCep(retorno) {
	if(retorno.erro){
		alert_error('Problema no Cep Informado.');
		document.getElementById('cep').value=""; 
	}	
}

function validarDDD(telefone , id) {
	if(telefone.length < 13){
    	document.getElementById(id).value="";       
    	alert_error("Telefone Inválido");
	}else if(telefone.substring(1,3) < 11){
		document.getElementById(id).value="";       
    	alert_error("Telefone Inválido");
	}	
	
}

function validarDataMaiorAtual(data){
	  let partes = data.split('/') 
	  let dataAtual = new Date()
      var anoAtual = dataAtual.getFullYear();
       var idade = anoAtual - partes[2];      
	  data2 = new Date(partes[2], partes[1] - 1, partes[0])
	 var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])      [\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|31)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
     if (((!((data.match(RegExPattern)))) && ( data != "" )) || data2 >= dataAtual) {
          alert_error('Data informada esta inválida.');
          document.getElementById('dataNasc').value = '';
     }else if(idade < 18){
	   alert_error('Menor de 18 anos não pode ser cadastrado.');
       document.getElementById('dataNasc').value = '';
}
}