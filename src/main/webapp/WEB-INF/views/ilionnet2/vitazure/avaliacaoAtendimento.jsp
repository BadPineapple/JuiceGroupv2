<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="avaliarAtendimentoApp" ng-controller="AvaliarAtendimentoController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>	
	<script src="../assets/js/vitazure/avaliacaoAtendimento.js"></script>
	<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery.js'/>"></script>
	
	<style type="text/css">
	  .starDesmarcada{
	    font-size: 60px;
    	color: #0097d6;
	  }
	</style>
</head>
<body>
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <div class="area-white">
            <div class="container" ng-init="agenda.id = '${agendaConcluida.id}';agenda.avaliacaoAtendimentoObservacao = '';agenda.avaliacaoAtendimentoNota = '0';">
                <div class="row">
                    <div class="col-12 col-md-12 col-xl-12 text-center">
                        <h3>Avaliação Atendimento</h3>
                    </div >
                     <div class="col-12 col-md-12 col-xl-12 text-center">
                       <p>Como foi a seu atendimento?</p>
                     </div>
                     <div class="col-12 text-center">    
                       <a href="javascript:void(0)" ng-click="avaliarNota(1)">
                        <i class="far fa-star starDesmarcada" id="s1"></i>
					   </a> 
						<a href="javascript:void(0)" ng-click="avaliarNota(2)">
						 <i class="far fa-star starDesmarcada" id="s2"></i>
						</a> 
						<a href="javascript:void(0)" ng-click="avaliarNota(3)">
						 <i class="far fa-star starDesmarcada" id="s3"></i>
						</a> 
						<a href="javascript:void(0)" ng-click="avaliarNota(4)">
						 <i class="far fa-star starDesmarcada" id="s4"></i>
						</a> 
						<a href="javascript:void(0)" ng-click="avaliarNota(5)">
						 <i class="far fa-star starDesmarcada" id="s5"></i>
						</a> 
                      </div>
                    </div>
                    <div class="row" style="padding: 42px !important;">
    					<div class="col-12 col-md-12 col-xl-12 text-center">
                          <div class="input-block">
                              <textarea cols="20" rows="5" ng-model="agenda.avaliacaoAtendimentoObservacao"  placeholder="Observação sobre atendimento." style="color: #A6A6A6"></textarea>
                           </div>
                        </div>
                     <div class="col-12 col-md-12 col-xl-12 text-center">
                          <button class="button-secundary checkbox-button"  ng-click="avaliarAtendimento(${agendaConcluida.id})" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Enviar avaliação</button>
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