<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="contatoApp" ng-controller="ContatoController">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/contatoForm.js"></script>
    <style type="text/css">
        @media (min-width: 700px){
            .title-banner {
                font-weight: 800 !important;
                font-size: 45px;
            }
        }
    </style>
	
</head>

<body id="index" class="home">
<div class="divSpinner" id="spinner">   
   <img alt="" src="../../assets/images/logo-square.png" style="width: 126px;position: relative;top: 167px;left: 49%;">
   <div class="spinner"></div>
</div>
    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />

        <div class="banner-content para-sua-empresa">
            <div class="container">
                <div class="row">
                    <div class="col-12  col-md-9 offset-md-3 col-xl-9 offset-xl-3">
                        <h2 class="title-gray">Eleve a produtividade e a qualidade de vida de seus colaboradores.</h2>

                        <div class="sou-profissional-button">
                            <a href="#saiba-mais" class="button-secundary button-consulta">Quero saber mais.</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="saiba-mais" class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="title-internas">
                            <h3>Para sua Empresa</h3>
                
                            <div class="pages-internas">
                                <a href="/home">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>
                
                                    Home     
                                </a>
                
                                <span>></span>
                
                                <a href="#">Para sua Empresa</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12" style="margin: 50px 0 20px 0">
                        <div class="row">
							<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
								<img src="../assets/images/time-de-sucesso.jpg" class="img-fluid" style="border-radius:7px" />
							</div>
							
							<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
								<span class="d-md-none d-lg-none"><br/></span>
								<h2>Cuidamos do seu time, oferecendo valores especiais de convenio:</h2>
							</div>
                        </div>

                        <div class="row">
							<div class="col-12">
								<ul style="list-style: none; ">
									<li style="margin: 20px 0 0 0; font-size: 2rem">
									   <span><img src="../assets/images/blue-dot.png"> Atendimento psicológico online em todas as abordagens</span>
									</li>

									<li style="margin: 20px 0 0 0; font-size: 2rem">
										<span><img src="../assets/images/blue-dot.png"> Check-up emocional com avaliação e prevenção em síndrome de Burnout e Gerenciamento do Estresse</span>
									</li>

									<li style="margin: 20px 0 0 0; font-size: 2rem">
										<span><img src="../assets/images/blue-dot.png"> Atendimento Breve e Focal</span>
									</li>

									<li style="margin: 20px 0 20px 0; font-size: 2rem">
										<span><img src="../assets/images/blue-dot.png"> Oficina das emoções para líderes</span>
									</li>
								</ul>
							</div>
                        </div>

                        <div class="row" style="margin-top: 10px">
							<div class="col-12">
	                            <p style="font-size: 2.3rem"> Saúde Mental é o melhor investimento para que seu time saiba que você os valoriza como pessoas e assim, mantê-los mais felizes e produtivos. Seja uma empresa reconhecida pelos seus colaboradores pelo ambiente de trabalho emocionalmente seguro! Seja  <span style="color: #0097d6;">Vitazure!</span></p>
							</div>
                        </div>

                    </div>

                    <div class="col-12  col-md-6 col-xl-6">
                        <form class="form-default"  ng-submit="enviarContatoEmpresa()"  id="contatoApp">
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
                            <h2>Preecha o formulário ao lado. Entraremos em contato o quanto antes.</h2>
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