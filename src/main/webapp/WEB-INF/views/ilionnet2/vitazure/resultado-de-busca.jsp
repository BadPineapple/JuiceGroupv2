<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">
<head>
<jsp:include page="includes/include-head.jsp" flush="true" />
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="../assets/js/vitazure/agendamento.js"></script>
</head>
<body id="index" class="home">
	<div id="app">
		<jsp:include page="includes/include-header-internas.jsp" flush="true" />
		<jsp:include page="includes/include-menu-painel.jsp" flush="true" />
		<jsp:include page="includes/include-painel-person.jsp" flush="true" />
		<div class="area-white">
			<div class="container">
				<angular-initializer ng-init="online='true';idTemp = ''; agenda = '';" />
				<div class="row">
					<div class="col-12">
						<h4 style="font-weight: 800; margin-bottom: 4.8rem;">Sugerimos
							os profissionais listados abaixo:</h4>
						<c:forEach var="profissional" items="${listProfissionais}">
							<div class="perfil-psicologo">
								<div class="row">
									<div class="col-12 col-md-3 col-xl-3">
										<div class="psicologo">
											<figure>
												<img
													src="${profissional.pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : profissional.pessoa.foto.link}"
													alt="">
											</figure>

											<div class="valor-consulta">
												<p>
													R$<span>${profissional.valorConsultaOnline}</span>
												</p>
											</div>
											<div class="valor-consulta">
												<p>
													R$<span>${profissional.valorConsultaPresencial}</span>
												</p>
											</div>
											<div class="tempo-consulta">
												<p>${profissional.duracaoAtendimento.nomeApresentar}</p>
											</div>
										</div>
									</div>

									<div class="col-12 col-md-4 col-xl-4">
										<div class="psicologo-name">
											<h3>${profissional.pessoa.nome}</h3>
											<p>${profissional.documentoCrpCrm}-
												${profissional.cadastroEpsi}</p>
										</div>

										<div class="psicologo-description">
											<strong>Sobre min:</strong>
											<p>${profissional.biografia}</p>
											<a href="<ilion:url/>vitazure/perfil-do-profissional/${profissional.id}" class="button-perfil">Perfil Completo</a>
										</div>
									</div>

									<div class="col-12 col-md-5 col-xl-5">
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
													<strong style="width: 70px;"> <a ng-click="consultarDatasProfissional('${agenda}' , '${profissional.id}')"><ilion:formatarDataSemana	value="${agenda}" /></a>
													</strong>
												</div>
											</c:forEach>
										</div>

										<div class="horarios-disponiveis">
											<div class="menu d-none d-md-block" layout="block" id="panelFiltrosSelecionados${profissional.id}">
											</div>
											<a href="#" ng-click="efetuarPagamento('${profissional.id}' , '${profissional.valorConsultaOnline}' , '${profissional.valorConsultaPresencial}')"  class="button-secundary">Agendar consulta</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="includes/include-footer.jsp" flush="true" />
		<script src="../assets/js/bundle.libs.ilionnet.js"></script>
		<script src="../assets/js/bundle.scripts.ilionnet.js"></script>
		<script src="../assets/js/bundle.libs.angular.js"></script>
	</div>
	
</body>
</html>
