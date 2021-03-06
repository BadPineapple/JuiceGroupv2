<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR"  ng-app="cadastroPessoaApp" ng-controller="CadastroPessoaController">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	<title>Vitazure</title>
	<link rel="icon" type="image/png" sizes="32x32" href="../assets/images/logo-square.png">
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/cadastroPessoa.js"></script>	
	<jsp:include page="includes/include-head.jsp" flush="true" />
	
</head>

<body id="index" class="home">
	
        <div id="app">
            <jsp:include page="includes/include-header.jsp" flush="true" />
    

            <div class="content-internas">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="title-internas">
                                <h3>Crie sua conta</h3>

                                <div class="pages-internas">
                                    <a href="#">
                                        <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                        </svg>

                                        Home     
                                    </a>

                                    <span>></span>

                                    <a href="#">Crie sua conta</a>
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
                            <h3>Registre-se agora como cliente.</h3>

                            <span class="new-cadastro">
                                J?? tem uma conta?
                                <div class="button-blue line">
                                    <a href="<ilion:url/>entrar">Entre agora</a>
                                </div>
                            </span>
                        </div>

                        <div class="col-12 col-md-6 offset-md-3 col-xl-6 offset-xl-3">
                            <form ng-submit="submit()" class="form-default" style="padding: 3rem 0; font-weight: 800;">
						          <angular-initializer
						            ng-init="pessoa.cliente='true';"/>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Nome completo</label>
                                            <input type="text"  ng-model="pessoa.nome" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Celular</label>
                                            <input type="text" placeholder="(00) 0000 0000" ng-model="pessoa.celular" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>E-mail (Ser?? seu Login)</label>
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
                                                Eu declaro que li e concordo com os
                                                <div class="button-blue line">
                                                    <a href="#">Termos de servi??o.</a>
                                                </div>
                                            </span>
                                        </div>
                                    </div>

                                    <!-- <div class="col-12">
                                        <div class="sou-robo">
                                            <figure style=" text-align: center; padding: 2rem 0;">
                                                <img src="images/nao-sou-robo.png" alt="">
                                            </figure>
                                        </div>
                                    </div> -->

                                    <div class="col-12">
                                        <button type="submit" class="button-secundary" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Criar conta</button>
                                    </div>
                                </div>
                            </form>

                            <span class="new-cadastro">
                                J?? tem uma conta?
                                <div class="button-blue line">
                                    <a href="<ilion:url/>entrar"> Entre agora</a>
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