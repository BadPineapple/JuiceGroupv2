<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctypehtml>
<html class="no-js" lang="pt-BR" ng-app="buscaProfissionalApp" ng-controller="BuscaProfissionalController">
<cabeça>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/buscaProfissional.js"></script>

    <style type="text/css">
        .banner-conteúdo{
            altura: 125vh;
        }
        @media (largura mínima: 700px){
            .title-banner {
                peso da fonte: 800 !importante;
                tamanho da fonte: 45px;
            }
        }

    </style>
    
</head>
<body id="index" class="home">
    <div id="aplicativo">
        <jsp:include page="includes/include-header.jsp" flush="true" />
        <div class="banner-content banner-internas">

            <div class="container">
                <div class="linha">
                    <div class="col-12 col-xl-9">
                        <h1 class="title-banner" style="font-size: 5.0rem;">A Terapia faz, permita <br>que haja bem transformação. em sua<br>existência Fale com seu psicólogo.</h1>
                        Com a ajuda do psicólogo, é possível, busque por <br> ou especialidade.</p>
                        <formulário ng-submit="cemsultarProfissionalExterna()" class="form-highlight">
                            <select ng-model="tipoProfissional">
                                <option value="">Tipo de profissional</option>
                                <c:forEach var="tipoProfissional" items="${tiposProfissional}">
                                    <option ng-if="${tipoProfissional == 'PSICOLOGO'}" value="${tipoProfissional}">${tipoProfissional.valor}</option>
                                </c:forEach>
                            </select>

                            <select ng-model="especialista">
                                <option value="">Especialidade</option>
                                <c:forEach items="${especialidades}" var="especialidade">
                                    <option ng-if="tipoProfissional == 'PSICOLOGO' && ${especialidade.tipoProfissional == 'PSICOLOGO'}" value="${especialidade.valor}">${especialidade.valor}</option>
                                </c:forEach>
                            </selecionar>
                            <button type="submit" class="button-secundary">Buscar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="linha">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Aqui é para você</h3>

                            <div class="pages-internas">
                                <a href="#">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <Path Fill-Rule = "Evenodd" clip-regra = "d =" d = "m4.83331 17.3334c3.4526 17,33334.433332 16.2141 2.33332 14.8334v9.83342h1.49998c0.757561 9.83342 0,385756 8.9358 0.910726 8.41083l8.41073 0.910826c8.73616 0.585389 9,2638 0.585389 9,58924 0.910826L17.0892 8.41083C17.6142 8,9358 17,2424 9,83342 16,5 9.83342H15.6666V14.8334C15.6666 16,2141 14,5474 17,3334 13,1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8,33792 3,99998 8,64272 3,99998 9.00007V14.8334C3 0,99998 15,2936 4,37308 15,6667 4,83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 10,6667 8,16665 7,24618 11,4129 10.6667H9.83332C10.7538 10,6667 11,5 11,4129 11,5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15,6667 14 15,2936 14 14.8334V9 .00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="preto"/>
                                    </svg>

                                    Lar     
                                </a>

                                <span>></span>

                                <a href="#">Aqui é para você</a>
                            </div>
                        </div>

                        <div class="agendar-consulta"style="text-align: center; preenchimento: 6rem 0 4.8rem;">
                            <h2>Como agendar minha<br/> consulta online?</h2>

                            <p>As pessoas estão descobrindo e aproveitando diferentes formas de realizar suas atividades<br/> diárias. E a consulta online, que já é uma tendência. se tornar uma realidade.</p>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure class="d-none d-md-block">
                                    <img src="../assets/images/busque.png" alt="">
                                </figura>

                                <figure class="d-block d-md-none">
                                    <img src="../assets/images/busque-mobile.png" alt="">
                                </figura>
                            </div>

                            <div class="consulta-title">
                                <strong>Busca</strong>
                                <p>Busque o profissional de saúde que se indique.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure class="d-none d-md-block">
                                    <img src="../assets/images/agende.png" alt="">
                                </figura>

                                <figure class="d-block d-md-none">
                                    <img src="../assets/images/agende-mobile.png" alt="">
                                </figura>
                            </div>

                            <div class="consulta-title">
                                <strong>Agenda</strong>
                                <p>Escolher o melhor dia e hora para sua consulta online.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure class="d-none d-md-block">
                                    <img src="../assets/images/pague.png" alt="">
                                </figura>

                                <figure class="d-block d-md-none">
                                    <img src="../assets/images/pague-mobile.png" alt="">
                                </figura>
                            </div>

                            <div class="consulta-title">
                                <strong>Pague</strong>
                                <p>Efetue o pagamento da consulta e pronto.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-3col-xl-3">
                        <div class="consulta-online">
                            <div class="consultar-img">
                                <figura>
                                    <img src="../assets/images/converse.png" alt="">
                                </figura>
                            </div>

                            <div class="consulta-título">
                                <strong>Conversar</strong>
                                <p>Fale por videoconsulta com seu psicólogo, com sigilo e segurança.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12centro de texto">
                        <a href="<ilion:url/>cadastro-se" class="button-secundary button-consulta">Agendar minha consulta</a>

                        <div class="psicologia-online">
                            <h2>Psicologia Online</h2>

                            <p style="padding: 1rem 3rem;">O atendimento psicológico online é uma abordagem equivalente à psicoterapia presencial e desde novembro de 2018 tal prática é permitida pelo Conselho Federal de Psicologia, conforme Resolução CFP 11/2018. As sessões de 60 minutos, duram até o pagamento, e são oferecidos através de teleatendimento seguro e de privacidade, mediante agendamento e aviso prévio. Esse serviço não é indicado para casos de saúde graves. Crianças e menores de 18 anos precisam de autorização por escrito do seu responsável..</p>
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
	<botão 
		digite="botão" 
		data-positionX="direito" 
		data-positionY="topo" 
		data-effect="fadeInUp" 
		data-message="Dados gravados com sucesso."
		data-type="sucesso" 
		class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
		id="alertSucess"
		style="display:nenhum;">
		Sucesso
	</button>
	<script type="text/javascript">
		(função(){
			setTimeout(function(){
				$('#alertSucess').click();
			}, 300);
		})();
	</script>
</c:if>
    </div>
</body>
</html>