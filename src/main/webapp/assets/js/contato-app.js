angular.module('contatoApp', [])
    .controller(
        'contatoController',
        ['$scope', '$http', function ($scope, $http) {

            $scope.contato = {};
            $scope.sending = false;

            $scope.submit = function () {
                
                $scope.sending = true;

                $http({
                    method: "POST",
                    url: ENDERECO_SITE+'rest/contato-registrar',
                    params: {
                    },
                    data: $scope.contato
                }).then(function (response) {

                    $scope.sending = false;
                    console.log('contato resp: ', response.data);
                    $scope.contato = {};
                    alert('Obrigado pela seu contato.');

                }, function (error) {

                    $scope.sending = false;
                    console.log('error: ', error);
                    alert(error.data.message);

                });

            };
            
            $scope.init = function () {

                console.log('contatoApp init');

            };

            $scope.init();

        }]);

(function () {
    angular.bootstrap(document.getElementById('contatoApp'), ['contatoApp']);
})();