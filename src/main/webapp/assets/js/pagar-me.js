function chamada (valor, item, id) {
    var confirma = 0;
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

        var valorTotal = valor * 100;

        var button = document.querySelector('button');

        function handleSuccess(data) {
	        document.getElementById("spinner").style.display = "inline-block";
            salvarTransacao(data , item);
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
                headerText: 'Total a pagar <span style="font-size: 1.6rem !important; color: #0097D6; font-weight: bold">{price_info}</span>',
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
                    title: item,
                    unit_price: 10000,
                    quantity: 1,
                    tangible: 'false'
                }]
            });
        }
    });
}

function salvarTransacao(data , plano){
	 var retornoToken = JSON.stringify({
    "token": data.token,
    "payment_method": data.payment_method,
    "plano": plano
  });
	  $.ajax({
	    url:  "/api/v1/salvarContratacaoPlano",
	    type: "post",
	    data: retornoToken,
	    contentType: "application/json",
	    success: function(response) {
		   document.getElementById("spinner").style.display = "none";
           alert(window.location.href);
	        window.location.reload();
	    },
	    error: function(error) {
		document.getElementById("spinner").style.display = "none";
		    if(window.location.href.indexOf("alteraMinhaAssinatura")){
			   window.location.href = "/vitazure/minhaAssinatura";
			} else{
	          window.location.reload();
			}
	    }
	  });
}