<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="../../assets/js/vitazure/agendamento.js"></script>
<style type="text/css">
 .slick-track {    
    display: flex;
}
</style>
</head>
<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <div class="area-white">
            <div class="container">
              <angular-initializer ng-init="idTemp = ''; agenda = '';opcaoReagendamento(${profissional.id},${agenda.online});" />
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
<!--                                         <div class="compartilhar-perfil"> -->
<!--                                             <a href="#" class="line"> -->
<!--                                                 <img src="../../assets/images/compartilhar.png" alt=""> -->
<!--                                                 Compartilhar Perfil -->
<!--                                             </a> -->
<!--                                         </div> -->
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 col-xl-5">
                                    <div class="psicologo-name">
                                        <h3>${profissional.pessoa.nome}</h3>
                                        <p>${profissional.tipoProfissional == 'PSICOLOGO' ? profissional.conselhoProfissional.CRP : profissional.conselhoProfissional.CRM}-${profissional.cadastroEpsi}</p>
										<p>e-Psi: ${profissional.cadastroEpsi}</p>
										<p>${cidadeProfissional}</p>
                                    </div>
                                    <div class="psicologo-description">
                                        <strong>Sobre min:</strong>
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
													Agenda <span>Selecione uma nova data</span>
												</h3>
 												 <div class="button-agenda">
 												 
                                                 <span id="${profissional.id}.online">Online</span>
                                                 <span id="${profissional.id}.presencial">Presencial</span>
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
											<div class="menu d-none d-md-block" style="display: block !important;" layout="block" id="panelFiltrosSelecionados${profissional.id}">
											</div>
											<div id="enderecoProfissional${profissional.id}" class="col-12" style="padding-top: 15px;">
											</div>
											<div id="enderecoLinkLocaliazacaoProfissional${profissional.id}" class="col-12" style="padding-top: 10px;">
											</div>
											<a href="#" ng-click="concluirReagendamento('${profissional.id}' , '${agenda.id}')" class="button-secundary">Reagendar consulta</a>
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