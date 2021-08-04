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
                <div class="row">
                    <div class="col-12 text-center">
                        <h2>Minhas Consultas</h2>
                        <p>Confira suas consultas. É necessário o aceite.</p>
                    </div>
                    <div class="col-12 col-xl-4">
                        <div class="search">
                            <form>
                                <input type="text" placeholder="Buscar">
                                <button href="#" class="search-button">
                                    <img src="images/search.png" alt="">
                                </button>
                            </form>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="tabela-consultas">
                                <thead>
                                    <tr>
                                        <td style="text-align: center;">Profissional</td>
                                        <td style="text-align: center;">Localização</td>
                                        <td style="text-align: center;">Data</td>
                                        <td style="text-align: center;">Duração</td>
                                        <td style="text-align: center;">Status</td>
                                        <td style="text-align: center;">Opções</td>
                                    </tr>
                                </thead>
    
                                <tbody>
                                <c:forEach var="agenda" items="${listAgendas}">
                                    <tr>
                                        <td style="text-align: center;">${agenda.profissional.pessoa.nome}</td>
                                        <td style="text-align: center;">${agenda.online ? 'Online' : 'Endereco'}</td>
                                        <td style="text-align: center;">${agenda.dataHoraApresentar}</td>
                                        <td style="text-align: center;">${agenda.profissional.duracaoAtendimento.nomeApresentar}</td>
                                        <td style="text-align: center;">
                                            <div class="status ${agenda.status == 'CONFIRMADO' || agenda.status == 'CONCLUIDO' ?  'realizada' : agenda.status == 'REMARCADO' || agenda.status == 'CANCELADO' ? 'cancelada' : 'pendente'}" style="width: 100%;">
                                                ${agenda.status}
                                            </div>
                                        </td>
                                        <td style="text-align: center;">
                                          <c:if test="${agenda.status == 'ANDAMENTO' || agenda.status == 'CONFIRMADO'}">
	                                           <button  class="btn btn-danger" ng-click="definirAgendamento('${agenda.id}' , 'CANCELADO')">Cancelar</button>
	                                           <button  class="btn btn-warning" ng-click="definirAgendamento('${agenda.id}' , 'REMARCADO')">Remarcar</button>
	                                      </c:if>     
                                           <c:if test="${agenda.online && agenda.status == 'CONFIRMADO'}">
	                                           <a href="${agenda.urlAtendimentoOnline}" target="_blank">
	                                             <button  class="btn btn-primary" >Entrar Consulta</button>
	                                           </a>  
                                           </c:if>
                                        </td>
                                    </tr>
    							</c:forEach>
                                </tbody>
                            </table>
                        </div>
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