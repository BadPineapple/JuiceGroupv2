<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="buscaProfissionalApp" ng-controller="BuscaProfissionalController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script src="../assets/js/vitazure/buscaProfissional.js"></script>
</head>

<body id="index" class="home">
	
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <jsp:include page="includes/include-painel-person.jsp" flush="true" />
        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="busca-profissional">
                            <figure style="margin-bottom: 10px">
                                <img src="../assets/images/lupa.png" alt="">
                            </figure>

                            <h3>Inicie a busca pelo<br/> Profissional.</h3>

                            <p>Escolha as opções de filtros</p>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-6">
                        <form class="form-opcoes">
                            <div class="row">
                                <div class="col-12 col-md-6 col-xl-6">
                                    <select ng-model="tipoProfissional" style="width: 100%">
                                        <option value="">Tipo de profissional</option>
                                        <c:forEach var="tipoProfissional" items="${tiposProfissional}">
                                            <option ng-if="${tipoProfissional == 'PSICOLOGO'}" value="${tipoProfissional}">${tipoProfissional.valor}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 col-xl-6">
	                                    <select ng-model="especialista" style="width: 100%;background-position-x: 95%;">
	                                        <option value="">Especialidade</option>
	                                        <c:forEach items="${especialidades}" var="especialidade">
	                                            <option ng-if="tipoProfissional == 'PSICOLOGO' && ${especialidade.tipoProfissional == 'PSICOLOGO'}" value="${especialidade.valor}">${especialidade.valor}</option>
	                                        </c:forEach>
	                                    </select>
	                                </div>
                            </div>  
                                <div class="row">
		                              <div class="col-12 col-md-6 col-xl-6">
	                                    <select ng-model="estado" style="width: 100%" ng-blur="buscaCidades();">
	                                        <option value="">Estado</option>
	                                        <c:forEach var="estado" items="${estados}">
	                                            <option value="${estado}">${estado.valor}</option>
	                                        </c:forEach>
	                                    </select>
	                                  </div>
	                                  <div class="col-12 col-md-6 col-xl-6">
		                                    <select ng-model="cidade" id="cidade" style="width: 100%">
		                                        <option value="">Cidade</option>
		                                    </select>
		                              </div>
								</div>
								<div class="row">
	                                <div class="col-12 col-md-12 col-xl-12">
	                                    <input type="text" placeholder="Palavra Chave" ng-model="palavraChave" style="width: 100%">
	                                </div>
<!--                                 <div class="col-12 col-md-6 col-xl-6"> -->
<!--                                     <select class="select-data" style="width: 100%"> -->
<!--                                         <option value=""> -->
<!--                                             A partir da data -->
<!--                                             <svg width="17" height="19" viewBox="0 0 17 19" fill="none" xmlns="http://www.w3.org/2000/svg"> -->
<!--                                                 <path fill-rule="evenodd" clip-rule="evenodd" d="M2.43 18.1781C1.08795 18.1781 0 16.9693 0 15.4781V4.6781C0 3.18693 1.08795 1.9781 2.43 1.9781H3.24V1.0781C3.24 0.581044 3.60265 0.178101 4.05 0.178101C4.49735 0.178101 4.86 0.581044 4.86 1.0781V1.9781H11.34V1.0781C11.34 0.581044 11.7027 0.178101 12.15 0.178101C12.5974 0.178101 12.96 0.581044 12.96 1.0781V1.9781H13.77C15.1121 1.9781 16.2 3.18693 16.2 4.6781V15.4781C16.2 16.9693 15.1121 18.1781 13.77 18.1781H2.43ZM4.85982 13.6781H1.61982V15.4781C1.61982 15.9752 1.98247 16.3781 2.42982 16.3781H4.85982V13.6781ZM9.72018 13.6781H6.48018V16.3781H9.72018V13.6781ZM14.5796 13.6781H11.3396V16.3781H13.7696C14.217 16.3781 14.5796 15.9752 14.5796 15.4781V13.6781ZM4.85982 9.1781H1.61982V11.8781H4.85982V9.1781ZM9.72018 9.1781H6.48018V11.8781H9.72018V9.1781ZM14.5796 9.1781H11.3396V11.8781H14.5796V9.1781ZM3.23982 3.7781H2.42982C1.98247 3.7781 1.61982 4.18104 1.61982 4.6781V7.3781H14.5798V4.6781C14.5798 4.18104 14.2172 3.7781 13.7698 3.7781H12.9598V4.6781C12.9598 5.17516 12.5972 5.5781 12.1498 5.5781C11.7025 5.5781 11.3398 5.17516 11.3398 4.6781V3.7781H4.85982V4.6781C4.85982 5.17516 4.49717 5.5781 4.04982 5.5781C3.60247 5.5781 3.23982 5.17516 3.23982 4.6781V3.7781Z" fill="black"/> -->
<!--                                             </svg> -->
<!--                                         </option> -->
<!--                                         <option value="">01/07</option> -->
<!--                                         <option value="">02/07</option> -->
<!--                                         <option value="">03/07</option> -->
<!--                                         <option value="">04/07</option> -->
<!--                                         <option value="">05/07</option> -->
<!--                                     </select> -->
<!--                                 </div> -->
	                                <div class="col-12 d-md-block col-md-12 col-xl-12">
	                                    <button class="button-secundary" ng-click="consultarProfissional()" style="width: 100%">Buscar</button>
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