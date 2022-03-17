<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="buscaProfissionalApp" ng-controller="BuscaProfissionalController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/buscaProfissional.js"></script>

    <style type="text/css">
        .banner-content{
            height: 125vh;
        }
        @media (min-width: 700px){
            .title-banner {
                font-weight: 800 !important;
                font-size: 45px;
            }
        }

    </style>
    
</head>
<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
        <div class="banner-content banner-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-xl-9">
                        <h1 class="title-banner" style="font-size: 5.54rem;line-height: 9rem;font-weight: 800;margin-top: 16rem;margin-bottom: 3rem;">Está precisando <br>de atendimento?</h1>
                        <p style="font-size: 2rem;">Este espaço permite o acesso social à saúde mental. As instituições
                        <br>que fazem parte da Vitazure Clinica Escola, poderão aceitar ou rejeitar
                        <br>sua inscrição conforme os requisitos adotados. As vagas são
                        <br>limitadas.
                        </p>
                   		 <a href="<ilion:url/>registre-se-como-paciente" class="button-secundary button-consulta" style="margin: 0;">Inscreva-se agora!</a>   
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Quero atendimento</h3>

                            <div class="pages-internas">
                                <a href="#">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>

                                    Home     
                                </a>

                                <span>></span>

                                <a href="#">Quero atendimento</a>
                            </div>
                        </div>

                        <div class="agendar-consulta"style="text-align: center; padding: 6rem 0 4.8rem;">
                            <h2>Consulte abaixo as instituiçoes<br/>parceiras da Vitazure Clínica Escola:</h2>

                            <p>Encontre a instituição mais próxima e solicite um agendamento.</p>
                        </div>
                    </div>
					 <div class="col-12">
					   <h3 style="font-size: 2.6rem;"><i class="fal fa-angle-right" style="padding-right: 20px;"></i>Instituto Projeção</h3>
					   <p>Departamento de Psicologia</p>
					   <p>Professor: Paulo Cesar Farias</p>
					   <p>Telefone: 62 3949-2938</p>
					   <p>E-mail: dp@ufg.org.br</p>
					 </div>
					 <div class="col-12">
					   <h3 style="font-size: 2.6rem;"><i class="fal fa-angle-right" style="padding-right: 20px;"></i>Pontifica Universitária Católica de Goiás</h3>
					   <p>Departamento de Psicologia</p>
					   <p>Professor: Ricardo Augusto</p>
					   <p>Telefone: 62 3949-2938</p>
					   <p>E-mail: dp@ucg.org.br</p>
					 </div>
					 <div class="col-12">
					   <h3 style="font-size: 2.6rem;"><i class="fal fa-angle-right" style="padding-right: 20px;"></i>UniEvangélica - Universidade Evangélica de Goiás</h3>
					   <p>Departamento de Psicologia</p>
					   <p>Professor: Pedro Antônio</p>
					   <p>Telefone: 62 3949-2938</p>
					   <p>E-mail: dp@unievangelica.com.br</p>
					 </div>
                    <div class="col-12 text-center">
                        <div class="psicologia-online">
                            <h2>Psicologia Online</h2>
                            <p style="padding: 1rem 3rem;">O atendimento psicológico online é uma abordagem equivalente a psicoterapia presencial e desde novembro de 2018 tal prática é permitida pelo Conselho Federal de Psicologia, conforme Resolução CFP 11/2018. As sessões duram até 60 minutos, são conduzidas através de teleatendimento, em ambiente seguro e de privacidade, mediante agendamento prévio. Esse serviço não é indicado para casos de saúde graves. Crianças e menores de 18 anos precisam de autorização por escrito do seu responsável..</p>
                        </div>
                    </div>
                    
                    <div class="blog">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3>Confira as últimas do Blog</h3>
                    </div>

                    <c:forEach items="${posts}" var="post">
                        <div class="col-12 col-md-6 col-xl-3">
                            <div class="post">
                                <a href="${post.link}" target='_blank'>
                                    <div class="post-img">
                                        <figure style=" text-align-last: center">
                                            <img src="${post.imagem}" alt="" style="max-height: 20.4rem; border-radius: 3px;">
                                        </figure>
                                    </div>

                                    <div class="post-title" style="text-justify: auto">
                                        <p>${post.titulo}</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>

                    <div class="col-12">
                        <div class="more-post line">
                            <a href="https://blog.vitazure.com.br/" target='_blank'>
                                Veja mais posts
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M15.2931 6.70711C14.9026 6.31658 14.9026 5.68342 15.2931 5.29289C15.6837 4.90237 16.3168 4.90237 16.7074 5.29289L22.7073 11.2929C23.0979 11.6834 23.0979 12.3166 22.7073 12.7071L16.7074 18.7071C16.3168 19.0976 15.6837 19.0976 15.2931 18.7071C14.9026 18.3166 14.9026 17.6834 15.2931 17.2929L19.586 13H2.01103C1.45265 13 1 12.5523 1 12C1 11.4477 1.45265 11 2.01103 11H19.586L15.2931 6.70711Z" fill="black"/>
                                </svg>
                                    
                            </a>
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
</body>
</html>