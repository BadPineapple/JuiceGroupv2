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

        <jsp:include page="includes/include-painel-person.jsp" flush="true" />

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h4 style="font-weight: 800; margin-bottom: 4.8rem;">Sugerimos os profissionais listados abaixo:</h4>

                        <div class="perfil-psicologo">
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
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="psicologo-name">
                                        <h3>Cristina Tereza</h3>

                                        <p>CRP 7ª Região - 098097</p>

                                        <p>Psicologa Clínica e Consultora de Imagem</p>
                                    </div>

                                    <div class="psicologo-description">
                                        <strong>Sobre min:</strong>

                                        <p>Psicóloga com 15 anos de formação. Trabalho embasado na Psicoterapia Psicanalítica e terapia EMDR. O EMDR é uma abordagem que trabalha com o reprocessamento cerebral. Reconhecida pela OMS como um tratamento eficaz no tratamento do transto ...</p>

                                        <a href="#" class="button-perfil">Perfil Completo</a>
                                    </div>
                                </div>

                                <div class="col-12 col-md-5 col-xl-5">
                                    <div class="online-presencial">
                                        <div class="agenda-title">
                                            <h3>Agenda
                                                <span>Selecione uma data</span>
                                            </h3>

                                            <div class="button-agenda">
                                                <span class="active marcar" onclick='marcardesmarcar();'>Online</span>

                                                <span>Presencial</span>
                                            </div>
                                        </div>
                                    </div>

                                
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

                                    <div class="horarios-disponiveis">
                                        <p>Horários disponíveis em 19/06</p>

                                        <div class="horarios">
                                            <span class="active">14:00</span>

                                            <span class="active">16:00</span>

                                            <span>18:00</span>

                                            <span>19:00</span>
                                        </div>

                                        <a href="#" class="button-secundary">Agendar consulta</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="perfil-psicologo">
                            <div class="row">
                                <div class="col-12 col-md-3 col-xl-3">
                                    <div class="psicologo">
                                        <figure>
                                            <img src="images/img-3.jpg" alt="">
                                        </figure>

                                        <div class="valor-consulta">
                                            <p>R$<span>200,00</span></p>
                                        </div>

                                        <div class="tempo-consulta">
                                            <p>40 min</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="psicologo-name">
                                        <h3>Pedro Henrique Silva</h3>

                                        <p>CRM GO - 987345</p>

                                        <p>Médico Psiquiatra</p>
                                    </div>

                                    <div class="psicologo-description">
                                        <strong>Sobre min:</strong>

                                        <p>Psicóloga com 15 anos de formação. Trabalho embasado na Psicoterapia Psicanalítica e terapia EMDR. O EMDR é uma abordagem que trabalha com o reprocessamento cerebral. Reconhecida pela OMS como um tratamento eficaz no tratamento do transto ...</p>

                                        <a href="#" class="button-perfil">Perfil Completo</a>
                                    </div>
                                </div>

                                <div class="col-12 col-md-5 col-xl-5">
                                    <div class="online-presencial">
                                        <div class="agenda-title">
                                            <h3>Agenda
                                                <span>Selecione uma data</span>
                                            </h3>

                                            <div class="button-agenda">
                                                <span class="active">Online</span>

                                                <span>Presencial</span>
                                            </div>
                                        </div>
                                    </div>

                                   
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

                                    <div class="horarios-disponiveis">
                                        <p>Horários disponíveis em 19/06</p>

                                        <div class="horarios">
                                            <span class="active">14:00</span>

                                            <span class="active">16:00</span>

                                            <span>18:00</span>

                                            <span>19:00</span>
                                        </div>

                                        <a href="#" class="button-secundary">Agendar consulta</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="perfil-psicologo">
                            <div class="row">
                                <div class="col-12 col-md-3 col-xl-3">
                                    <div class="psicologo">
                                        <figure>
                                            <img src="images/terezinha.jpg" alt="">
                                        </figure>

                                        <div class="valor-consulta">
                                            <p>R$<span>250,00</span></p>
                                        </div>

                                        <div class="tempo-consulta">
                                            <p>50 min</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="psicologo-name">
                                        <h3>Terezinha Ribas</h3>

                                        <p>CRP 7ª Região - 098097</p>

                                        <p>Psicologa Clínica e Consultora de Imagem</p>
                                    </div>

                                    <div class="psicologo-description">
                                        <strong>Sobre min:</strong>

                                        <p>Psicóloga com 15 anos de formação. Trabalho embasado na Psicoterapia Psicanalítica e terapia EMDR. O EMDR é uma abordagem que trabalha com o reprocessamento cerebral. Reconhecida pela OMS como um tratamento eficaz no tratamento do transto ...</p>

                                        <a href="#" class="button-perfil">Perfil Completo</a>
                                    </div>
                                </div>

                                <div class="col-12 col-md-5 col-xl-5">
                                    <div class="online-presencial">
                                        <div class="agenda-title">
                                            <h3>Agenda
                                                <span>Selecione uma data</span>
                                            </h3>

                                            <div class="button-agenda">
                                                <span class="active">Online</span>

                                                <span>Presencial</span>
                                            </div>
                                        </div>
                                    </div>

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

                                    <div class="horarios-disponiveis">
                                        <p>Horários disponíveis em 19/06</p>

                                        <div class="horarios">
                                            <span class="active">14:00</span>

                                            <span class="active">16:00</span>

                                            <span>18:00</span>

                                            <span>19:00</span>
                                        </div>

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

<!-- <script>

document.getElementsByClassName("marcar")[0].onclick = function() {teste()};
function teste() {
    alert("Deu Certo!");
}

    
</script> -->