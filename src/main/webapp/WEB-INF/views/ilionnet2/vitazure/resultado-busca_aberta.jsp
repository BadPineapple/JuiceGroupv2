<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">
<head>
<jsp:include page="includes/include-head.jsp" flush="true" />
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script src="../assets/js/vitazure/agendamento.js"></script>

<style type="text/css">
 .slick-track {    
    display: flex;
}
</style>
 <div id="hora" style="padding-left: 4px;display: none;"></div>

</head>
<body id="index" class="home">
	<div id="app">
		<jsp:include page="includes/include-header-internas.jsp" flush="true" />
		<div class="area-white" style="padding-top: 100px;">
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
											
											<div class="valor-consulta" id="${profissional.id}.valorOnline">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorOnlineFormatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorOnlinePacote2Formatado" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorOnlinePacote2Formatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorOnlinePacote3Formatado" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorOnlinePacote3Formatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorOnlinePacote4Formatado" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorOnlinePacote4Formatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorPresencial" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorPresencialFormatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorPresencialPacote2Formatado" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorPresencialPacote2Formatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorPresencialPacote3Formatado" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorPresencialPacote3Formatado}</span>
												</p>
											</div>
											<div class="valor-consulta" id="${profissional.id}.valorPresencialPacote4Formatado" style="display:none;">
												<p>Valor sessão:<br/></p>
												<p>
													R$<span>${profissional.valorPresencialPacote4Formatado}</span>
												</p>
											</div>
											<div class="tempo-consulta">
												<p style="margin-bottom: 0px">Duração:<br/></p>
												<p>${profissional.duracaoAtendimento.nomeApresentar}</p>
											</div>
										</div>
									</div>

									<div class="col-12 col-md-4 col-xl-4">
										<div class="psicologo-name">
											<h3>${profissional.pessoa.nome}</h3>
											<p>${profissional.tipoProfissional == 'PSICOLOGO' ? profissional.conselhoProfissional.CRP : profissional.conselhoProfissional.CRM}-${profissional.cadastroEpsi}</p>
											<p>e-Psi: ${profissional.cadastroEpsi}</p>
											<p>${profissional.pessoa.cidade}</p>
										</div>

										<div class="psicologo-description">
											<strong>Sobre mim:</strong>
											<p >${profissional.biografiaApresentar}</p>
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
                                                 <span id="${profissional.id}.online"  onclick='marcardesmarcar(${profissional.id},"online");' class="active marcar">Online</span>
                                                 <span id="${profissional.id}.presencial" onclick='marcardesmarcar(${profissional.id},"presencial");'>Presencial</span>
                                            </div>
											</div>
										</div>
										<div class="slider-dias">
											<c:forEach var="agenda"	items="${profissional.datasPossivelAgendamento}">
												<div class="dias" id="${profissional.id}.${agenda}">
													<strong style="width: 70px; cursor: pointer;"> <a ng-click="consultarDatasProfissional('${agenda}' , '${profissional.id}')" ><ilion:formatarDataSemana	value="${agenda}" /></a>
													</strong>
												</div>
											</c:forEach>
										</div>

										<div class="horarios-disponiveis">
											<div class="menu d-none d-md-block" style="display: block !important;" layout="block" id="panelFiltrosSelecionados${profissional.id}"></div>
											<div id="enderecoProfissional${profissional.id}" class="col-12" style="padding-top: 15px;"></div>
											<div id="enderecoLinkLocaliazacaoProfissional${profissional.id}" class="col-12" style="padding-top: 10px;"></div>
											<a href="#" ng-click="efetuarPagamento('${profissional.id}' , '${profissional.valorConsultaOnline}' , '${profissional.valorConsultaPresencial}' 
											,'${profissional.valorOnlinePacote2}' ,'${profissional.valorOnlinePacote3}' ,'${profissional.valorOnlinePacote4}'
											,'${profissional.valorPresencialPacote2}' ,'${profissional.valorPresencialPacote3}' ,'${profissional.valorPresencialPacote4}')" class="button-secundary">Agendar consulta</a>
										</div>
										<div class="button-agenda">
                                                 <span id="${profissional.id}.pacote2" ng-if="${profissional.pacote2com5Desconto}"  onclick='selecionarPacote(${profissional.id},"pacote2");'>Pacote com 2 consultas por 5% de desconto</span>
                                                 <span id="${profissional.id}.pacote3" ng-if="${profissional.pacote3com10Desconto}"  onclick='selecionarPacote(${profissional.id},"pacote3");'>Pacote com 3 consultas por 10% de desconto</span>
                                                 <span id="${profissional.id}.pacote4" ng-if="${profissional.pacote4com15Desconto}"  onclick='selecionarPacote(${profissional.id},"pacote4");'>Pacote com 4 consultas por 15% de desconto</span>
                                            </div>
									</div>
								</div>
							</div>
						</c:forEach>
					 <div class="row">
						<div class="col-12 col-md-6 col-xl-6" style="margin: auto">
							<div class="busca-profissional">
								<figure style="margin-bottom: 10px">
									<img src="../assets/images/lupa.png" alt="">
								</figure>
								<h3>Ainda não encontrou seu profissional ?<br/>Continue a busca por aqui:</h3>
								<p>Escolha as opções de filtros</p>
							</div>
						</div>

						<div class="col-12 col-md-6 col-xl-6">
                        <form class="form-opcoes">
                            <div class="row">
                                <div class="col-12 col-md-6 col-xl-6">
                                    <select ng-model="tipoProfissional" style="width: 100%">
                                        <option value="">Tipo de profissional</option>
                                        <c:forEach var="tipoProfissional" items="${tiposProfissional}">
                                            <option ng-if="${tipoProfissional == 'PSICOLOGO'}" value="${tipoProfissional}">${tipoProfissional.valor}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 col-xl-6">
	                                    <select ng-model="especialista" style="width: 100%;background-position-x: 95%;">
	                                        <option value="">Especialidade</option>
	                                        <c:forEach items="${especialidades}" var="especialidade">
	                                            <option ng-if="tipoProfissional == 'PSICOLOGO' && ${especialidade.tipoProfissional == 'PSICOLOGO'}" value="${especialidade.valor}">${especialidade.valor}</option>
	                                        </c:forEach>
	                                    </select>
	                                </div>
                            </div>  
                                <div class="row">
		                              <div class="col-12 col-md-6 col-xl-6">
	                                    <select ng-model="estado" style="width: 100%" ng-blur="buscaCidades();">
	                                        <option value="">Estado</option>
	                                        <c:forEach var="estado" items="${estados}">
	                                            <option value="${estado}">${estado.valor}</option>
	                                        </c:forEach>
	                                    </select>
	                                  </div>
	                                  <div class="col-12 col-md-6 col-xl-6">
		                                    <select ng-model="cidade" id="cidade" style="width: 100%">
		                                        <option value="">Cidade</option>
		                                    </select>
		                              </div>
								</div>
								<div class="row">
	                                <div class="col-12 col-md-12 col-xl-12">
	                                    <input type="text" placeholder="Palavra Chave" ng-model="palavraChave" style="width: 100%">
	                                </div>
	                                <div class="col-12 d-md-block col-md-12 col-xl-12">
	                                    <button class="button-secundary" ng-click="consultarProfissionalAberta()" style="width: 100%">Buscar</button>
	                                </div>
                            </div>
                        </form>
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
		<script>
		  if(${empty listProfissionais}){
		   alert_error(" Profissional com as condições selecionadas não disponível, tente outros parâmetros");		
		  }
		</script>
		 <script>
		    var myVar = setInterval(myTimer ,1000);
		    function myTimer() {
		        var d = new Date(), displayDate;
		       if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
		          displayDate = d.toLocaleTimeString('pt-BR');
		       } else {
		          displayDate = d.toLocaleTimeString('pt-BR', {timeZone: 'America/Belem'});
		       }
		          document.getElementById("hora").innerHTML = displayDate.substr(0, 5);
		    }
    </script>
	</div>
	
</body>
</html>
