<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="ouvidoriaApp" ng-controller="OuvidoriaController">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/ouvidoria.js"></script>
	
</head>

<body id="index" class="home">
<div class="divSpinner" id="spinner">   
   <img alt="" src="../../assets/images/logo-square.png" style="width: 126px;position: relative;top: 167px;left: 49%;">
   <div class="spinner"></div>
</div>
        <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
        <div class="content-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                  <h2 class="title-gray">A <span style="color: #0097d6;">Vitazure</span> valoriza a transparência e sua segurança.</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                <div class="row" style="margin-top: 10px">
                     		<div class="col-12">
                                <div class="toggle-header" style="background: none !important; padding: 0 !important">
                                    <strong style="padding-left: 0 !important; cursor: default">Fale com a gente:</strong>
                                </div>
                                <p style=" margin-bottom: 3rem;">Atendimento de segunda à sexta-feira, das 8h às 18h através do nosso chat disponível na plataforma.</p>
                            </div>
                     		<div class="col-12">
                                <div class="toggle-header" style="background: none !important; padding: 0 !important">
                                    <strong style="padding-left: 0 !important; cursor: default">COMO ACESSAR A OUVIDORIA?</strong>
                                </div>
                                <p style=" margin-bottom: 3rem;">A Ouvidoria Vitazure é o espaço no qual clientes que normalmente não conseguiram solucionar demandas por meio dos canais de atendimento habituais, possam buscar auxílio.
								   </br>Este espaço é designado para reclamações, sugestões de melhoria e até mesmo elogios.</p>
                            </div>
                        </div>
<!--                     <div class="col-12"> -->
<!--                         <div class="col-12 text-center"> -->
<!--                             <h3>Ouvidoria</h3> -->
<!--                         </div> -->
<!--                     </div> -->

                    <div class="col-12  col-md-6 col-xl-6">
                        <form class="form-default"  ng-submit="enviarOuvidoria()"  id="ouvidoriaApp">
                            <div class="row">
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Nome</label>
                                        <input type="text" id="nome" ng-model="contato.nome" placeholder="Nome" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>E-mail</label>
                                        <input type="text" id="email" ng-model="contato.email" placeholder="E-mail" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Telefone</label>
                                        <input type="text" id="telefone" ng-model="contato.telefone" data-mask="(00)0000-00000" placeholder="(00) 0000-00000" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Mensagem</label>
                                        <textarea cols="20" rows="5" id="mensagem" ng-model="contato.mensagem" placeholder="Informe seu texto aqui."></textarea>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <button class="button-secundary" type="submit" style="font-size: 1.6rem;">Enviar mensagem</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-12 col-md-5 offset-md-1 col-xl-5 offset-xl-1">
                        <div class="right-input">
                            <img src="../assets/images/ouvidoria.png" class="img-fluid" />
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
</body>
</html>