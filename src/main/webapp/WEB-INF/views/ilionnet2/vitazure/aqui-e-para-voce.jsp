<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="pt-BR"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="pt-BR" <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="pt-BR"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="pt-BR">
<!--<![endif]-->

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	
	<title>Vitazure</title>
	
	<jsp:include page="includes/include-head.jsp" flush="true" />
	
</head>

<body id="index" class="home">
	
    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
    

        <div class="banner-content banner-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-xl-9">
                        <h1 class="title-gray">Fale com seu<br/> Psicólogo Online do<br/> conforto de sua casa.</h1>

                        <p>Procure por categoria profissional ou especialidade.</p>

                        <form class="form-highlight">
                            <select>
                                <option value="">Tipo de profissional</option>
                                <option value="psicologo">Psicólogo</option>
                                <option value="psicanalista">Psicanalista</option>
                            </select>

                            <select>
                                <option value="">Especialidade</option>
                                <option value="psicologico">Psicológico</option>
                                <option value="psicanalise">Psicanálise</option>
                            </select>

                            <button class="button-secundary">Buscar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Aqui é para você</h3>

                            <div class="pages-internas">
                                <a href="#">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>

                                    Home     
                                </a>

                                <span>></span>

                                <a href="#">Aqui é para você</a>
                            </div>
                        </div>

                        <div class="agendar-consulta"style="text-align: center; padding: 6rem 0 4.8rem;">
                            <h2>Como agendar minha<br/> consulta online?</h2>

                            <p>As pessoas estão descobrindo e aproveitando diferentes formas de realizar suas atividades<br/> diárias. E a consulta online, que já é uma tendência. se tornou uma realidade.</p>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure class="d-none d-md-block">
                                    <img src="images/busque.png" alt="">
                                </figure>

                                <figure class="d-block d-md-none">
                                    <img src="images/busque-mobile.png" alt="">
                                </figure>
                            </div>

                            <div class="consulta-title">
                                <strong>Busque</strong>
                                <p>Busque o profissional de saúde que se identifique.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure class="d-none d-md-block">
                                    <img src="images/agende.png" alt="">
                                </figure>

                                <figure class="d-block d-md-none">
                                    <img src="images/agende-mobile.png" alt="">
                                </figure>
                            </div>

                            <div class="consulta-title">
                                <strong>Agende</strong>
                                <p>Escolha o melhor dia e horário para sua consulta online.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure class="d-none d-md-block">
                                    <img src="images/pague.png" alt="">
                                </figure>

                                <figure class="d-block d-md-none">
                                    <img src="images/pague-mobile.png" alt="">
                                </figure>
                            </div>

                            <div class="consulta-title">
                                <strong>Pague</strong>
                                <p>Efetue o pagamento da consulta e pronto.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-3 col-xl-3">
                        <div class="consulta-online">
                            <div class="consulta-img">
                                <figure>
                                    <img src="images/converse.png" alt="">
                                </figure>
                            </div>

                            <div class="consulta-title">
                                <strong>Converse</strong>
                                <p>Fale por videoconsulta com seu psicólogo.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 text-center">
                        <a href="#" class="button-secundary button-consulta">Agendar minha consulta</a>

                        <div class="psicologia-online">
                            <h2>Psicologia Online</h2>

                            <p style="padding: 1rem 3rem;">O atendimento psicológico online é um serviço realizado por um psicólogo online, via vídeo. Trata-se de uma abordagem equivalente a psicoterapia presencial. Desde novembro de 2018 o atendimento online é permitido pelo Consegui Federal de Psicologia sem limite de sessões. As sessões duram 50 minutos e são feitas via video mediante agendamento e pagamento prévios. Esse serviço não é indicado para casos de saúde graves. Crianças e menores de 18 anos precisam de autorização por escrito de um responsável.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>