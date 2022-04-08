<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
                            <c:choose>
                            	<c:when test="${ paymentUrl != null && paymentUrl != ''}">
									<h3>Seu agendamento será confirmado no seu e-mail em até 72 horas após o pagamento</h3>
                            	</c:when>
                            	<c:otherwise>
                            		<h3>Seu agendamento foi concluído com sucesso!</h3>
                            	</c:otherwise>
                            </c:choose>
                            <p>${ paymentUrl == null && qrCode == null 
                            	? "Por favor, aguarde a confirmação do agendamendo pelo profissional selecionado." 
                            	: paymentUrl != null && paymentUrl != '' ? " Por favor, após o pagamento, aguarde a confirmação do agendamendo pelo profissional selecionado."
                            	: "Use o QrCode abaixo para realizar o seu pagamento, aguarde a confirmação do agendamento que será enviada no seu e-mail."}</p>
                            
                            <c:choose>
                            	<c:when test="${ paymentUrl == null || paymentUrl == ''}">
                             	</c:when>
                             	<c:otherwise>
                             		<p><a href="${paymentUrl}?format=pdf" class="button-secundary" target="_blank">Gerar boleto</a>
                             	</c:otherwise>
                            </c:choose>
                            <c:choose>
                            	<c:when test="${ qrCode == null || qrCode == ''}">
                             		</p>
                             </c:when>
                             <c:otherwise>
							      <img id='barcode' 
							            src="https://api.qrserver.com/v1/create-qr-code/?data=${qrCode}" 
							            alt="" 
							            title="Pix" 
							            width="150" 
							            height="150" />
							    
                             </p>
                             </c:otherwise>
                            </c:choose>
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
