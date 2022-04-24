<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">
<head>
	<jsp:include page="includes/include-head-nova-consulta.jsp" flush="true" />
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="../../assets/js/vitazure/agendamento.js"></script>
</head>
<body id="index" class="home">
    <div id="app">
	 		<div class="divSpinner" id="spinner">   
			   <img alt="" src="../../assets/images/logo-square.png" style="width: 126px;position: relative;top: 167px;left: 49%;">
			   <div class="spinner"></div>
			</div>
            <div class="container">
              <angular-initializer ng-init="online='true';idTemp = ''; agenda = '';" />
                <div class="row">
                    <div class="col-12">
                        <div class="perfil-psicologo perfil-profissional">
                            <div class="row">
                                <div class="col-12 col-md-3 col-xl-3">
                                    <div class="psicologo">
                                        <figure>
                                            <img src="${profissional.pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : profissional.pessoa.foto.link}" alt="">
                                        </figure>
                                        <div class="valor-consulta" id="${profissional.id}.valorOnline">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorOnlineFormatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorPresencial" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorPresencialFormatado}</span>
												</p>
											</div>
											<div class="tempo-consulta">
												<p>${profissional.duracaoAtendimento.nomeApresentar}</p>
											</div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 col-xl-5">
                                    <div class="psicologo-name">
                                        <h3>${profissional.pessoa.nome}</h3>
                                        <p>${profissional.tipoProfissional == 'PSICOLOGO' ? profissional.conselhoProfissional.CRP : profissional.conselhoProfissional.CRM} - ${profissional.documentoCrpCrm}</p>
										<p>e-Psi: ${profissional.cadastroEpsi}</p>
										<p>${cidadeProfissional}</p>
                                    </div>
                                    <div class="psicologo-description">
                                        <strong>Sobre mim:</strong>
                                        <p>${profissional.biografia}</p>
                                    </div>
                                    <div class="temas">
                                        <p>Temas de Trabalho</p>
                                        <c:forEach var="temas" items="${temasTrabalho}">
	                                        <span class="tipos-temas">
	                                           ${temas.tema}
	                                        </span>
										</c:forEach>
                                    </div>
                                    <div class="temas">
                                        <p>Especialidade</p>
                                        <c:forEach var="especialidade" items="${especialidades}">
	                                        <span class="tipos-temas">
	                                           ${especialidade.especialidade}
	                                        </span>
										</c:forEach>
                                    </div>

                                    <div class="match-toggle">
                                        <div class="toggle-header formacao-academica">
                                            <strong>Formação Acadêmica</strong>
                                        </div>
                                        <div class="toggle-body formacao">
                                            <ul>
                                                <c:forEach var="formacao" items="${formacoes}">
                                                  <li>${formacao.tipoFormacao}</li>
                                                  <li>${formacao.descricaoFormacao}</li>
                                                </c:forEach>  
                                            </ul>
                                        
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-md-5 col-xl-4">
                                    <div class="online-presencial">
											<div class="agenda-title">
												<h3>
													Agenda <span>Selecione uma data</span>
												</h3>
 												 <div class="button-agenda">
                                                 <span id="${profissional.id}.online"  onclick='marcardesmarcar(${profissional.id},"online");' class="active marcar">Online</span>
                                                 <span id="${profissional.id}.presencial" onclick='marcardesmarcar(${profissional.id},"presencial");'>Presencial</span>
                                            </div>
											</div>
										</div> 
										<div class="slider-dias">
											<c:forEach var="agenda"	items="${profissional.datasPossivelAgendamento}" varStatus="stat">
											<c:if test="${stat.first}">
												<div class="dias" id="${profissional.id}.${agenda}" data-id="${profissional.id}.${agenda}" data-ng-init="consultarDatasProfissional('${agenda}' , '${profissional.id}', 'true')">
													<strong style="font-size: 1.2rem;"> <a ng-click="consultarDatasProfissional('${agenda}' , '${profissional.id}')"><ilion:formatarDataSemana	value="${agenda}" /></a></strong>
												</div>
    										</c:if>
    										<c:if test="${!stat.first}">
												 <div class="dias" id="${profissional.id}.${agenda}" data-id="${profissional.id}.${agenda}" >
													<strong style="font-size: 1.2rem;"> <a ng-click="consultarDatasProfissional('${agenda}' , '${profissional.id}', 'false')"><ilion:formatarDataSemana	value="${agenda}" /></a></strong>
												</div> 
											</c:if>
											</c:forEach>
										</div>

										<div class="horarios-disponiveis">
											<div class="menu d-none d-md-block" style="display: block !important;" layout="block" id="panelFiltrosSelecionados${profissional.id}">
											</div>
											<div id="enderecoProfissional${profissional.id}" class="col-12" style="padding-top: 15px;">
											</div>
											<div id="enderecoLinkLocaliazacaoProfissional${profissional.id}" class="col-12" style="padding-top: 10px;">
											</div>
											<a href="#" ng-click="efetuarPagamento('${profissional.id}' , '${profissional.valorConsultaOnline}' , '${profissional.valorConsultaPresencial}')" class="button-secundary">Agendar consulta</a>
										</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>



<script src="../../assets/js/nova-consulta-slick.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery.mmenu/8.5.6/mmenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
<!-- <script src="../../assets/js/calendario.js"></script> -->
<script src="https://assets.pagar.me/checkout/1.1.0/checkout.js"></script>
<script src="../../assets/js/pagar-me.js"></script>
<script>
    Mmenu.configs.offCanvas.page.selector = "#app";

    document.addEventListener(
        "DOMContentLoaded", () => {
            new Mmenu( "#menu", {
                extensions: ["theme-dark", "pagedim-black", "position-right"]
            });
        }
    );

    $('.slider-dias').slick({
        dots: false,
        infinite: true,
        speed: 300,
        slidesToShow: 5,
        slidesToScroll: 1,
        arrows: true,
        prevArrow: '<img class="slick-prev" src="../../assets/images/left-arrow.png" alt="">',
        nextArrow: '<img class="slick-next" src="../../assets/images/right-arrow.png" alt="">',
        responsive: [
            {
            breakpoint: 1024,
            settings: {
                slidesToShow: 3,
                slidesToScroll: 3,
                infinite: true,
                dots: true
            }
            },
            {
            breakpoint: 600,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2
            }
            },
            {
            breakpoint: 480,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1
            }
            }
        ]
    });
    
    $('.slider-dias').on('swipe', function(event, slick, direction){
   	  console.log(direction);
   	  // left
   	});

    $(window).scroll(function(){ scrollSite(); });
    scrollSite();

    function scrollSite(){
        if($(this).scrollTop() > 100){
            $('header').addClass('active');
        };
        if($(this).scrollTop() < 100){
            $('header').removeClass('active');
        };
    }

    $('.toggle-header').on('click', function(){
        $(this).parent().toggleClass('active');
    });

    const buttonOnline = document.querySelector('.online');
    const buttonPresencial = document.querySelector('.presencial');
    const agendaPresencial = document.querySelector('.agenda-presencial');
    const agendaOnline = document.querySelector('.agenda-online');

    buttonOnline.addEventListener('click', () => {
        buttonOnline.classList.add('active');
        buttonPresencial.classList.remove('active');

        agendaPresencial.style.display = 'none';
        agendaOnline.style.display = 'block';
    })

    buttonPresencial.addEventListener('click', function() {
        buttonPresencial.classList.add('active');
        buttonOnline.classList.remove('active');

        agendaOnline.style.display = 'none';
        agendaPresencial.style.display = 'block';

        $('.slider-dias').slick('slickNext');
    })
</script>
        <script src="../../assets/js/bundle.libs.ilionnet.js"></script>
		<script src="../../assets/js/bundle.scripts.ilionnet.js"></script>
		<script src="../../assets/js/bundle.libs.angular.js"></script>
 </div>
</body>
</html>