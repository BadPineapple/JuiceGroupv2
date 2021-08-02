<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="../../assets/js/vitazure/agendamento.js"></script>
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
                                            <img src="${profissional.pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : profissional.pessoa.foto.link}" alt="">
                                        </figure>
                                        <div class="valor-consulta">
                                            <p>R$<span>${profissional.valorConsultaOnline}</span></p>
                                        </div>
                                        <div class="tempo-consulta">
                                            <p>50 min</p>

                                            <p style="font-size: 1.8rem;">${profissional.pessoa.cidade} - ${profissional.pessoa.estado}</p>
                                        </div>
                                        <div class="compartilhar-perfil">
                                            <a href="#" class="line">
                                                <img src="../../assets/images/compartilhar.png" alt="">
                                                Compartilhar Perfil
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 col-xl-5">
                                    <div class="psicologo-name">
                                        <h3>${profissional.pessoa.nome}</h3>
                                        <p>${profissional.documentoCrpCrm}-${profissional.cadastroEpsi}</p>
                                    </div>
                                    <div class="psicologo-description">
                                        <strong>Sobre min:</strong>
                                        <p>${profissional.biografia}</p>
                                    </div>
                                    <div class="temas">
                                        <p>Temas de Trabalho</p>
                                        <c:forEach var="temas" items="${temasTrabalho}">
	                                        <span class="tipos-temas">
	                                           ${temas.tema.valor}
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
                                                 <span id="${profissional.id}.online"  onclick='marcardesmarcar(${profissional.id},"online");'>Online</span>
                                                 <span id="${profissional.id}.presencial" onclick='marcardesmarcar(${profissional.id},"presencial");'>Presencial</span>
                                            </div>
											</div>
										</div>
										<div class="slider-dias">
											<c:forEach var="agenda"	items="${profissional.datasPossivelAgendamento}">
												<div class="dias" id="${profissional.id}.${agenda}">
													<strong style="font-size: 1.2rem;"> <a ng-click="consultarDatasProfissional('${agenda}' , '${profissional.id}')"><ilion:formatarDataSemana	value="${agenda}" /></a></strong>
												</div>
											</c:forEach>
										</div>

										<div class="horarios-disponiveis">
											<div class="menu d-none d-md-block" layout="block" id="panelFiltrosSelecionados${profissional.id}">
											</div>
											 <strong>Rua Expedicionário Lellis, 1500 - Centro,<br/> Sertãozinho - SP, 14160-750</strong>

                                        <a href="https://goo.gl/maps/eMWSQnXSzewP1TxW8" target="_blank" class="localizacao line">
                                            <img src="../../assets/images/localizacao.png" alt="">
                                            Confira localização no Mapa
                                        </a>
											<a href="#" ng-click="agendar('${profissional.id}')"  class="button-secundary">Agendar consulta</a>
										</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
        <script src="../../assets/js/bundle.libs.ilionnet.js"></script>
		<script src="../../assets/js/bundle.scripts.ilionnet.js"></script>
		<script src="../../assets/js/bundle.libs.angular.js"></script>
    </div>
</body>
</html>