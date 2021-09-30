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
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                <div class="row" style="margin-top: 10px">
							<div class="col-12 funciona-text">
	                            <p>A Ouvidoria é um espaço aberto para receber elogios, sugestões, reclamações ou denúncias que você acredita não terem sido atendidos adequadamente pelos setores e pessoas responsáveis. Sua atuação fundamenta-se na ética, respeito, autonomia, imparcialidade e transparência nas decisões.</p>
							</div>
                        </div>
                    <div class="col-12">
                        <div class="col-12 text-center">
                            <h3>Ouvidoria</h3>
                        </div>
                    </div>

                    <div class="col-12  col-md-6 offset-md-3 col-xl-6 offset-xl-3">
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