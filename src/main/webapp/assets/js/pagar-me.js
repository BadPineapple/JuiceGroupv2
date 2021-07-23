
function chamada (valor) {

    var confirma = 0;

    $.ajax({
        url: 'v1/getAPI',
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



        //valor passado para o checkout deve ser em centavos
        var valorTotal = valor * 100;

        var button = document.querySelector('button');

        function handleSuccess(data) {
            console.log(data);
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


            checkout.open({
                paymentButtonText: 'Finalizar',
                amount: valorTotal,
                maxInstallments: 12,
                defaultInstallment: 1,
                customerData: 'true',
                createToken: 'true',
                paymentMethods: 'boleto,credit_card,pix',
                uiColor: '#0097D6',
                boletoDiscountPercentage: 0,
                boletoExpirationDate: '12/12/2021',
                pixExpirationDate: '2021-12-31',

                items: [{
                    id: '1',
                    title: 'ItemZero',
                    unit_price: 10000,
                    quantity: 1,
                    tangible: 'false'
                }]
            });
        }
    });
}