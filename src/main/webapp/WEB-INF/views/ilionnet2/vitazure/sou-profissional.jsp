<%@ include file="/ilionnet/taglibs.jsp"%>
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

        <div class="banner-content sou-profissional">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-9 offset-md-3 col-xl-9 offset-xl-3">
                        <h1 class="title-banner" style="text-align: right;">Sua carreira está prestes a decolar, venha fazer parte da <span style="color: #0097d6; font-style: normal">Vitazure</span></h1>
                        <p>Estamos muito felizes que você esteja próximo de se juntar a nós, aqui você terá seus serviços divulgados para além das fronteiras regionais, aumentando o alcance dos pacientes, definirá com mais segurança seus temas de trabalho e terá total controle financeiro sobre suas consultas.</p>
                        <div class="sou-profissional-button">
                            <a href="#vantagens" class="button-secundary button-consulta">Quero saber mais.</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Sou Profissional</h3>
                
                            <div class="pages-internas">
                                <a href="#">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>
                
                                    Home     
                                </a>
                
                                <span>></span>
                
                                <a href="#">Sou Profissional</a>
                            </div>
                        </div>
                    </div>
    
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="cellphone">
                            <figure>
                                <img src="../assets/images/cellphone.jpg" alt="">
                            </figure>
                        </div>
                    </div>
    
                    <div class="col-12 col-md-5 offset-md-1 col-xl-5 offset-xl-1">
                        <div class="cellphone-right">
                            <h5>Mais do que atendimento virtual, um consultório online</h5>
    
                            <p>Multiplique sua capacidade de atendimento com a consulta online e alcance qualquer paciente, em qualquer lugar. Faça uso do consultório online por um valor fixo mensal, muito mais baixo que o presencial.</p>
    
                            <a href="<ilion:url/>cadastre-se" class="button-secundary">Quero fazer parte!</a>
                        </div>
                        <div id="vantagens" style="display: block !important"></div>
                    </div>
    
                    <div class="col-12" >
                        <div class="vantagens">
                            <h2>Veja algumas vantagens<br/> de fazer parte</h2>
    
                            <div class="row">
                                <div class="col-12 col-md-6 col-xl-6">
                                    <div class="vantagens-img">
                                        <figure>
                                            <img src="../assets/images/diversidade.jpg" alt="">
                                        </figure>
            
                                        <figure>
                                            <img src="../assets/images/img-5.jpg" alt="">
                                        </figure>
                                    </div>
    
                                    <div class="vantagens-video">
                                        <figure>
                                            <img src="../assets/images/exemplo-video.jpg" alt="">
                                        </figure>
                                    </div>
                                </div>
    
                                <div class="col-12 col-md-6 col-xl-6">
                                    <div class="vantagens-list">
                                        <div class="list">
                                            <span>
                                                <img src="../assets/images/blue-dot.png" alt="">
                                                Seja Flexível
                                            </span>
        
                                            <p>Tenha liberdade para atender seus pacientes de onde estiver. Administre seus horários de atendimento de uma maneira simples.</p>  
                                        </div>
                                        
                                        <div class="list">
                                            <span>
                                                <img src="../assets/images/pink-dot.png" alt="">
                                                Baixo Custo
                                            </span>
        
                                            <p>Com nossa plataforma você fica  tranquilo(a), temos toda a infraestrutura que você precisa para atender seus pacientes.</p>  
                                        </div> 
    
                                        <div class="list">
                                            <span>
                                                <img src="../assets/images/green-dot.png" alt="">
                                                Seguro
                                            </span>
        
                                            <p>Nossa plataforma possui certificados de segurança internacionais impossibilitando o vazamento de informações durante a seção e garantindo a segurança dos dados!</p>  
                                        </div> 
                                    </div>
                                </div>
                                <div class="col-12 text-center">
                                    <h2 style="margin: 10rem 0 8rem;">Para participar da rede<br> <span style="color: #0097d6; font-style: normal">Vitazure</span>,todos os profissionais<br> devem possuir o seguinte:</h2>
                                </div>
                                <div class="col-12 col-md-5 col-xl-5">
                                    <div class="cadastro-profissional">
                                        <div class="lista-cadastro">
                                            <span>1.</span>

                                            <p>Cadastro no Conselho Regional<br> de Psicologia ativo;</p>
                                        </div>

                                        <div class="lista-cadastro">
                                            <span>2.</span>

                                            <p>Cadastro aprovado ou já ter solicitado junto ao Conselho Regional de Psicologia o cadastro relativo ao e-PSI.
											<br>Mais informações poderão ser obtidas nos Conselhos Regionais de Psicologia e no Diálogo Digital "Resolução 11/2018" que foi realizado no dia 06/11/2018, e está disponível <a href="https://e-psi.cfp.org.br/" target="_blank">clicando aqui</a>;</p>
                                        </div>

                                        <div class="lista-cadastro">
                                            <span>3.</span>

                                            <p>Cadastro na plataforma<br> Vitazure;</p>
                                        </div>

<!--                                         <div class="lista-cadastro"> -->
<!--                                             <span>4.</span> -->

<!--                                             <p>Assinatura mensal, semestral ou anual na plataforma;</p> -->
<!--                                         </div> -->

                                        <div class="lista-cadastro">
                                            <span>4.</span>

                                            <p style="transform: translateY(0px);">Conexão de internet confiável, se informe aqui.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 col-xl-6 offset-md-1 offsrt-xl-1">
                                    <div class="happy-faces">
                                        <div class="up-photos lista-cadastro">
                                            <figure class="first-photo lista-cadastro">
                                                <img src="../assets/images/img-3.jpg" alt="">
                                            </figure>

                                            <figure class="second-photo lista-cadastro">
                                                <img src="../assets/images/img-cadastro-2.jpg" alt="">
                                            </figure>
                                        </div>

                                        <div class="down-photos lista-cadastro">
                                            <figure class="third-photo lista-cadastro">
                                                <img src="../assets/images/img-cadastro-3.jpg" alt="">
                                            </figure>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 text-center">
                                    <div class="cellphone-right" style="margin: 0;">
                                        <a href="cadastre-se" class="button-secundary">Crie sua conta</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>