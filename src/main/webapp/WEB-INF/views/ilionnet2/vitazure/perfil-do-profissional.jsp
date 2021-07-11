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
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
    
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12">

                        <div class="perfil-psicologo perfil-profissional">
                            <div class="row">
                                <div class="col-12 col-md-3 col-xl-3">
                                    <div class="psicologo">
                                        <figure>
                                            <img src="images/cristina.jpg" alt="">
                                        </figure>

                                        <div class="valor-consulta">
                                            <p>R$<span>250,00</span></p>
                                        </div>

                                        <div class="tempo-consulta">
                                            <p>50 min</p>

                                            <p style="font-size: 1.8rem;">Sertãozinho - SP</p>
                                        </div>
                                        
                                        <div class="compartilhar-perfil">
                                            <a href="#" class="line">
                                                
                                                <img src="images/compartilhar.png" alt="">

                                                Compartilhar Perfil
                                                
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 col-xl-5">
                                    <div class="psicologo-name">
                                        <h3>Cristina Tereza</h3>

                                        <p>CRP 7ª Região - 098097</p>

                                        <p>Psicologa Clínica e Consultora de Imagem</p>
                                    </div>

                                    <div class="psicologo-description">
                                        <strong>Sobre min:</strong>

                                        <p>Olá! Meu nome é Maria Júlia. Estou aqui para ouvir suas questões com atenção, e sem julgamentos. Em minha concepção a psicoterapia é uma jornada, no qual caminhamos, em alguns momentos, por trilhas desconhecidas, na busca de autoconhecimento, entendimento de traumas, dificuldades e sentimentos. 
                                        Compreendo cada indivíduo e caminhada como únicos, e procuro através da terapia ofertar um espaço de acolhimento e escuta, através de linguagem acessível e acolhedora. 
                                        Posso te auxiliar durante esta caminhada. </p>
                                    </div>

                                    <div class="temas">
                                        <p>Temas de Trabalho</p>

                                        <span class="tipos-temas">
                                            Acompanhamento psicológico
                                        </span>

                                        <span class="tipos-temas">
                                            Ansiedade
                                        </span>

                                        <span class="tipos-temas">
                                            Anorexia nervosa
                                        </span>

                                        <span class="tipos-temas">
                                            Autismo
                                        </span>

                                        <span class="tipos-temas">
                                            Autoestima
                                        </span>

                                        <span class="tipos-temas">
                                            Conflitos amorosos
                                        </span>

                                        <span class="tipos-temas">
                                            Conflitos familiares
                                        </span>

                                        <span class="tipos-temas">
                                            Depressão
                                        </span>
                                    </div>

                                    <div class="match-toggle">
                                        <div class="toggle-header formacao-academica">
                                            <strong>Formação Acadêmica</strong>
                                        </div>
                                        <div class="toggle-body formacao">
                                            
                                            <ul>
                                                <li>Formada em psicologia</li>

                                                <li>Pós graduada em psicologia comportamental</li>

                                                <li>Mestrado em psicologia comportamental</li>

                                            </ul>
                                        
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-5 col-xl-4">
                                    <div class="online-presencial">
                                        <div class="agenda-title">
                                            <h3>Agenda
                                                <span>Selecione uma data</span>
                                            </h3>

                                            <div class="button-agenda">
                                                <span class="online active">Online</span>

                                                <span class="presencial">Presencial</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="agenda-online">	
                                        <section>
                                            <div class="main">
                                                <div class="custom-calendar-wrap">
                                                    <div id="custom-inner" class="custom-inner">
                                                            <div class="custom-header clearfix">
                                                                <nav>
                                                                        <span id="custom-prev" class="custom-prev"></span>
                                                                        <span id="custom-next" class="custom-next"></span>
                                                                </nav>
                                                                <h2 id="custom-month" class="custom-month"></h2>
                                                                <h3 id="custom-year" class="custom-year"></h3>
                                                            </div>
                                                            <div id="calendar" class="fc-calendar-container"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>
                                    </div>

                                    <div class="agenda-presencial" style="display: none;">
                                        <div class="slider-dias">
                                                
                                            <div class="dias">
                                                <p>Hoje</p>
    
                                                <strong>19/06</strong>
                                            </div>
                                        
                                        
                                            <div class="dias active">
                                                <p>seg</p>
    
                                                <strong>20/06</strong>
                                            </div>
                                        
    
                                        
                                            <div class="dias active">
                                                <p>ter</p>
    
                                                <strong>21/06</strong>
                                            </div>
                                        
    
                                        
                                            <div class="dias">
                                                <p>qua</p>
    
                                                <strong>22/06</strong>
                                            </div>
                                        
    
                                        
                                            <div class="dias">
                                                <p>qui</p>
    
                                                <strong>23/06</strong>
                                            </div>
                                        
    
                                        
                                            <div class="dias">
                                                <p>sex</p>
    
                                                <strong>24/06</strong>
                                            </div>
                                        
    
                                        
                                            <div class="dias">
                                                <p>sab</p>
    
                                                <strong>25/06</strong>
                                            </div>
                                        
    
                                        
                                            <div class="dias">
                                                <p>dom</p>
    
                                                <strong>26/06</strong>
                                            </div>
                                            
                                        </div>
                                    </div>

                                    <div class="horarios-disponiveis">
                                        <p>Horários disponíveis em 19/06</p>

                                        <div class="horarios">
                                            <span class="active">14:00</span>

                                            <span class="active">16:00</span>

                                            <span>18:00</span>

                                            <span>19:00</span>
                                        </div>

                                        <strong>Rua Expedicionário Lellis, 1500 - Centro,<br/> Sertãozinho - SP, 14160-750</strong>

                                        <a href="https://goo.gl/maps/eMWSQnXSzewP1TxW8" target="_blank" class="localizacao line">
                                            <img src="images/localizacao.png" alt="">

                                            Confira localização no Mapa
                                        </a>

                                        <a href="#" class="button-secundary">Agendar consulta</a>
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