<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
</head>
<body id="index" class="home">
	
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <jsp:include page="includes/include-painel-person.jsp" flush="true" />
        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-7 col-xl-7 text-center">
                        <div class="checkout">
                            <h3>Seu agendamento foi concluído com sucesso!</h3>
                            <p>Por favor, aguarde a confirmação do agendamendo pelo profissional selecionado.</p>
                        </div>
                    </div>
                    <div class="col-12 col-md-5 col-xl-5 checkout-img">
                        <figure>
                            <img src="../assets/images/checkout.jpg" alt="">
                        </figure>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>