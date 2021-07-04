angular.module('farmaciaApp', [])
    .controller(
        'farmaciaController',
        ['$scope', '$http', function ($scope, $http) {

            $scope.farmacia = {};
            $scope.sending = false;

            $scope.submit = function () {

                var files = document.getElementById('arquivosFarmacia').files;

                if( files && files.length > 0 ) {

                    $scope.farmacia.arquivos = [];

                    addFiles(files, function() {
                        if (files.length === $scope.farmacia.arquivos.length) {
                            $scope.salvar();
                        }
                    });

                } else {
                    $scope.salvar();
                }

            };

            $scope.salvar = function () {

            	if( ! $scope.farmacia.arquivos || $scope.farmacia.arquivos.length === 0 ) {
            		alert('Por favor, anexe seus documentos.');
            		return;
            	}
                
                $scope.sending = true;

                $http({
                    method: "POST",
                    url: ENDERECO_SITE+'api/farmacia',
                    params: {
                    },
                    data: $scope.farmacia
                }).then(function (response) {

                    $scope.sending = false;
                    console.log('farmacia resp: ', response.data);
                    $scope.farmacia = {};
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
                            $scope.farmacia.arquivos.push({
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

                console.log('farmaciaApp init');

            };

            $scope.init();

        }]);

(function () {
    angular.bootstrap(document.getElementById('farmaciaApp'), ['farmaciaApp']);
})();