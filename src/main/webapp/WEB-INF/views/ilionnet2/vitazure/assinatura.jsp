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
        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Assinatura</h3>
                        <p>Escolha um plano que melhor atende às suas expectativas.</p>
                        <span class="notice-text">
                            Todos os agendamentos seguem o horário de Brasília - 
                             <div id="hora" style="padding-left: 4px;"></div>
                        </span>
                        <div class="planos">
                            <div class="row">
                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="box-planos">
                                        <div class="planos-title">
                                            <strong>Plano Mensal</strong>

											<p class="price" style="transform: scale(0.5); margin-top: -10px; margin-left: -85px; margin-bottom: -12px; color: #c1c1c1;"><span style="text-decoration: line-through;">de R$ 120,00</span></p>
                                            <p class="price">R$ <span>89,00</span></p>

                                            <p>Cobrado mensalmente no cartão de crédito.</p>
                                        </div>

                                        <div class="planos-description">
                                            <ul style="padding-bottom: 20px;">
                                                <li>Agendamento online e presencial ilimitado</li>

                                                <li>Anúncio de perfil no portal</li>

                                                <li>Faça atendimentos  ilimitados</li>

                                                <li>Atendimento online por vídeo</li>

                                                <li>Controle de seus atendimentos</li>

                                                <li>Acompanhamento de recebíveis</li>
                                            </ul>
                                         <div class="col-12" style="text-align: center;">
                                            <button onclick="chamada('89','plano_mensal',1)" class="button-secundary">Assine agora</button>
                                         </div>   
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 col-xl-4 text-left">
                                    <div class="box-plano plano-semestral">
                                        <div class="planos-title semestral">
                                            <strong>Plano Semestral</strong>

                                            <p class="price" style="transform: scale(0.5); margin-top: -10px; margin-left: -85px; margin-bottom: -12px; color: #c1c1c1;"><span style="text-decoration: line-through;">de R$ 720,00</span></p>
                                            <p class="price">R$ <span>469,69</span></p>

                                            <p>Pague uma vez e tenha R$ 250,31 de desconto, que corresponde a 34,8% do valor de tabela.</p>
                                        </div>

                                        <div class="planos-description">
                                            <ul>
                                                <li>Agendamento online e presencial ilimitado</li>

                                                <li>Anúncio de perfil no portal</li>

                                                <li>Faça atendimentos  ilimitados</li>

                                                <li>Atendimento online por vídeo</li>

                                                <li>Controle de seus atendimentos</li>

                                                <li>Acompanhamento de recebíveis</li>
                                            </ul>
                                            <div class="col-12" style="text-align: center;">
                                            <button onclick="chamada('469.69','plano_semestral',6)" class="button-secundary">Assine agora</button>
                                         </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="box-planos">
                                        <div class="planos-title">
                                            <strong>Plano Anual</strong>

                                            <p class="price" style="transform: scale(0.5); margin-top: -10px; margin-left: -85px; margin-bottom: -12px; color: #c1c1c1;"><span style="text-decoration: line-through;">de R$ 1440,00</span></p>
                                            <p class="price">R$ <span>799,97</span></p>

                                            <p>Pague uma vez e tenha R$ 640,03 de desconto, que corresponde a 44% do valor de tabela.</p>
                                        </div>

                                        <div class="planos-description">
                                            <ul>
                                                <li>Agendamento online e presencial ilimitado</li>

                                                <li>Anúncio de perfil no portal</li>

                                                <li>Faça atendimentos  ilimitados</li>

                                                <li>Atendimento online por vídeo</li>

                                                <li>Controle de seus atendimentos</li>

                                                <li>Acompanhamento de recebíveis</li>
                                            </ul>
                                           <div class="col-12" style="text-align: center;">
                                            <button onclick="chamada('799.97','plano_anual',12)" class="button-secundary">Assine agora</button>
                                         </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
    
    <script>
    var myVar = setInterval(myTimer ,1000);
    function myTimer() {
        var d = new Date(), displayDate;
       if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
          displayDate = d.toLocaleTimeString('pt-BR');
       } else {
          displayDate = d.toLocaleTimeString('pt-BR', {timeZone: 'America/Belem'});
       }
          document.getElementById("hora").innerHTML = displayDate.substr(0, 5);
    }
    
    </script>
    
</body>
</html>