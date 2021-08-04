var cadastroPessoaApp = angular.module('cadastroPessoaApp', []);

cadastroPessoaApp.controller('CadastroPessoaController', cadastroPessoaController);

var arquivos = [];

function cadastroPessoaController($scope, $http, $window) {
    $scope.submit = function () {
        post($scope, $http, $window);
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