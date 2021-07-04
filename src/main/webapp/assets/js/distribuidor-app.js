angular.module('distribuidorApp', [])
    .controller(
        'distribuidorController',
        ['$scope', '$http', function ($scope, $http) {

            $scope.distribuidor = {};
            $scope.sending = false;

            $scope.submit = function () {

                var files = document.getElementById('arquivosDistribuidor').files;

                if( files && files.length > 0 ) {

                    $scope.distribuidor.arquivos = [];

                    addFiles(files, function() {
                        if (files.length === $scope.distribuidor.arquivos.length) {
                            $scope.salvar();
                        }
                    });

                } else {
                    $scope.salvar();
                }

            };

            $scope.salvar = function () {

            	if( ! $scope.distribuidor.arquivos || $scope.distribuidor.arquivos.length === 0 ) {
            		alert('Por favor, anexe seus documentos.');
            		return;
            	}
                
                $scope.sending = true;

                $http({
                    method: "POST",
                    url: ENDERECO_SITE+'api/distribuidor',
                    params: {
                    },
                    data: $scope.distribuidor
                }).then(function (response) {

                    $scope.sending = false;
                    console.log('distribuidor resp: ', response.data);
                    $scope.distribuidor = {};
                    alert('Obrigado pelo seu cadastro.');

                }, function (error) {

                    $scope.sending = false;
                    console.log('error: ', error);
                    alert(error.data.message);

                });

            };

            function addFiles(files, callback) {

                if (!files.length)
                    return;

                for (var i = 0; i < files.length; i++) {
                    getBase64(files[i], function(base64File, fileName) {
                        if (base64File) {
                            base64File = base64File.substring(base64File.indexOf(',') + 1, base64File.length);
                            $scope.distribuidor.arquivos.push({
                                titulo: fileName,
                                extensao: fileName.substring(fileName.indexOf('.')+1),
                                base64File: base64File
                            })
                            callback();
                        }

                    });
                }

            }

            function getBase64(file, callback) {
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function() {
                    callback(reader.result, file.name);
                };
                reader.onerror = function(error) {
                    console.log('Error: ', error);
                    callback(null);
                };
            };

            $scope.init = function () {

                console.log('distribuidorApp init');

            };

            $scope.init();

        }]);

(function () {
    angular.bootstrap(document.getElementById('distribuidorApp'), ['distribuidorApp']);
})();