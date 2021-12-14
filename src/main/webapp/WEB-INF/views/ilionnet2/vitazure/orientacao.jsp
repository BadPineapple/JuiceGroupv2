<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="informacoesPerfilApp" ng-controller="InformacoesPerfilController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>	
	<script src="../assets/js/vitazure/informacoesPerfil.js"></script>
	<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery.js'/>"></script>
	<script src="../assets/js/vitazure/cep.js"></script>
	<script type="text/javascript" src="<c:url value='../ilionnet/design/script/tiny_mce/tiny_mce.js'/>"></script>
<script type="text/javascript" src="<c:url value='../ilionnet/design/script/funcoesTinyMCE.js'/>"></script>
<script type="text/javascript" src="../ilionnet/design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="../ilionnet/design/script/common.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.4.0/moment.min.js"></script>
	<script type="text/javascript">
	document.write(getCalendarStyles());
	var cal1x = new CalendarPopup("testdiv1");
	</script>
	<style>
	
	.modal {
    display: none;
    position: fixed;
    z-index: 1;
    padding-top: 100px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0,0,0);
    background-color: rgba(0,0,0,0.4);
}
	
	.modal-content {
	    background-color: #fefefe;
	    margin: auto;
	    padding: 20px;
	    border: 1px solid #888;
	    width: 80%;
	    max-width: 500px;
	    margin: 1.75rem auto;
	}

.modal-header {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: start;
    align-items: flex-start;
    -ms-flex-pack: justify;
    justify-content: space-between;
    border-bottom: 1px solid #dee2e6;
    border-top-left-radius: calc(.3rem - 1px);
    border-top-right-radius: calc(.3rem - 1px);
    padding: 0;
}
.close {
  font-size: 2.5rem;
}
.swal2-title{
     display: contents !important;
}	
	</style>
		<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div>
</head>

<body>
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
       <div class="area-blue">
		    <div class="container">
		        <div class="row">
		            <div class="col-12">
		            </div>
		         </div>
		      </div>
		</div>            
        <div class="area-white">
            <div class="container">
                <div class="row" style="text-align: left !important;">
<!--                     <div class="col-12"> -->
<!--                         <h1 class="title-banner">Basta um clique para iniciar sua trajetória profissional de ainda mais sucesso e com a gente impulsionando sua carreira!</span>.</h3> -->
<!--                     </div> -->
                        <form class="form-default">
                            <div class="col-12">
                                <p style=" margin-bottom: 4rem;font-size: 32px;font-weight: 800;line-height: 3.4rem;font-family: 'Manrope', sans-serif;">Basta um clique para iniciar sua trajetória profissional de ainda mais sucesso e com a gente impulsionando sua carreira! </p>
							</div>
							<div class="col-12" style="padding-left:40px;">
                                <p style=" margin-bottom: 3rem;font-size: 22px;font-weight: 800;line-height: 3.4rem;font-family: 'Manrope', sans-serif;">1.&nbsp;&nbsp;Não há cobrança de assinatura, apenas cobraremos uma taxa de manutenção da plataforma de 10% (dez por cento), sobre o valor da consulta. Dessa forma, se o valor de sua consulta for R$ 100,00 por exemplo, R$ 90,00 será o seu crédito e R$ 10,00 será a taxa da Vitazure. Lembrando que a partir de agora você poderá fazer a gestão da sua agenda de forma virtual, seus atendimentos com segurança e cuidaremos que todos seus atendimentos sejam pagos e repassados a você com tranquilidade.</p>
							</div>
							<div class="col-12" style="padding-left:40px;">
                                <p style=" margin-bottom: 3rem;font-size: 22px;font-weight: 800;line-height: 3.4rem;font-family: 'Manrope', sans-serif;">2.&nbsp;&nbsp;Você pode dedicar seu tempo aos seus atendimentos pois a gestão financeira será mensal e cada valor da consulta, subtraído da taxa de manutenção, será acrescido ao seu saldo, e assim que liquidado, transferido à cada 30 dias, para sua conta corrente cadastrada na plataforma.</p>
							</div>
							<div class="col-12" style="padding-left:40px;">
                                <p style=" margin-bottom: 3rem;font-size: 22px;font-weight: 800;line-height: 3.4rem;font-family: 'Manrope', sans-serif;">3.&nbsp;&nbsp;Se você for correntista do Banco Bradesco estará isento da taxa de DOC, caso contrário a plataforma de gerenciamento de pagamentos (PAGAR.ME) cobrará de você uma taxa relativa à DOC, no valor de R$ 3,67 para fazer a transferência do seu saldo mensal para sua conta corrente. Este é um procedimento financeiro seguro e que trará maior comodidade para seus recebimentos.</p>
							</div>
							<div class="col-12" style="padding-left:40px;">
                                <p style=" margin-bottom: 3rem;font-size: 22px;font-weight: 800;line-height: 3.4rem;font-family: 'Manrope', sans-serif;">4.&nbsp;&nbsp;A partir de agora o controle das suas consultas é fácil, online e em tempo real. Você poderá acompanhar seu saldo quando quiser. Cada consulta creditada aparecerá no seu relatório financeiro com o nome do paciente, data de pagamento, data do processamento e data de recebimento na sua conta.</p>
							</div>
							<div class="col-12" style="text-align: center;">
                               <a href="<ilion:url/>vitazure/informacoes-perfil-completarCadastro" class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;width: 170px;">Continuar</a>
                            </div>
					   </form>		
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
        <script src="../assets/js/bundle.libs.ilionnet.js"></script>
			<script src="../assets/js/bundle.scripts.ilionnet.js"></script>
			<script src="../assets/js/bundle.libs.angular.js"></script>
            
            <c:if test="${param.m == 'ok'}">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="Dados gravados com sucesso."
		data-type="success" 
		class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
		id="alertSucess"
		style="display:none;">
		Sucesso
	</button>
	<script type="text/javascript">
		(function() {
			setTimeout(function() {
				$('#alertSucess').click();
			}, 300);
		})();
	</script>
</c:if>
</div>
</body>
</html>