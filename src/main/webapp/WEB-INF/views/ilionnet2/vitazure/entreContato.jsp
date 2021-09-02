<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="contatoApp" ng-controller="ContatoController">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/contatoForm.js"></script>
	
</head>

<body id="index" class="home">
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
                    <div class="col-12">
                        <div class="col-12 text-center">
                            <h3>Entre em contato</h3>
                        </div>
                    </div>

                    <div class="col-12  col-md-6 offset-md-3 col-xl-6 offset-xl-3">
                        <form class="form-default"  ng-submit="submit()"  id="contatoApp">
                            <div class="row">
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Nome</label>
                                        <input type="text" ng-model="contato.nome" placeholder="Nome" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>E-mail</label>
                                        <input type="text" ng-model="contato.email" placeholder="E-mail" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Celular</label>
                                        <input type="text" ng-model="contato.telefone" data-mask="(00)00000-0000" placeholder="(00) 0000-0000" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Mensagem</label>
                                        <textarea cols="20" rows="5" ng-model="contato.mensagem" placeholder="Informe seu texto aqui."></textarea>
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