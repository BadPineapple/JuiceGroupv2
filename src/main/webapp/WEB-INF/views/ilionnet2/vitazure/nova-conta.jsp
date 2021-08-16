<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="cadastroPessoaApp" ng-controller="CadastroPessoaController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/cadastroPessoa.js"></script>
</head>

<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
        <div class="content-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Complete seu cadastro</h3>

                            <div class="pages-internas">
                                <a href="/home">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>

                                    Home     
                                </a>

                                <span>></span>

                                <a href="#">Complete seu cadastro</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Criar nova conta</h3>
                    </div>
                    <div class="col-12 col-md-6 offset-md-3 col-xl-6 offset-xl-3">
                      <form ng-submit="submit()" class="form-default" style="padding: 3rem 0; font-weight: 800;">
						          <angular-initializer
						            ng-init="pessoa.cliente='true';tipoConta='CL';"/>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Tipo Conta</label>
		                                    <select ng-model="tipoConta">
		                                        <option value="CL">Cliente</option>
		                                        <option value="PS">Profissional</option>
		                                    </select>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Nome completo</label>
                                            <input type="text"  ng-model="pessoa.nome" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Celular</label>
                                            <input type="text" placeholder="(00) 0000 0000" data-mask="(00) 00000-0000" ng-model="pessoa.celular" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>E-mail (Será seu Login)</label>
                                            <input type="text" ng-model="pessoa.email" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Senha</label>
                                            <input type="text" ng-model="pessoa.senha" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="termos">
                                            <input type="checkbox" required />
                                            <span class="new-cadastro">
                                                Marque esta caixa para continuar  
                                                <div class="button-blue line">

                                                    <%--<ilion:artigoConsulta categoria="termos-de-uso" artigo="termos-de-uso-vitazure" order="posicao" varRetorno="art"/>--%>
                                                    <ilion:arquivoConsulta nomeClasse="Artigo" idObjeto="211" layout="lateral" varRetorno="arq"/>
                                                    <a href="${arq.url}" target="_blank" >&nbsp;&nbsp;&nbsp;&nbsp;Termos e condições de uso.</a>
                                                </div>
                                            </span>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <button type="submit" class="button-secundary" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Criar conta</button>
                                    </div>
                                </div>
                            </form>

                        <span class="new-cadastro">
                            Já tem uma conta?
                            <div class="button-blue line">
                                 <a href="/entrar">Entre agora</a>
                            </div>
                        </span>
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