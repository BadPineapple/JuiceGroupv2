<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
</head>

<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />
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
                                        <td style="width: 12.5%;">Número</td>
                                        <td style="width: 16%;">Status</td>
                                        <td style="width: 16%;">Cliente</td>
                                        <td style="width: 19.5%;">Localização</td>
                                        <td style="width: 16%;">Data</td>
                                        <td style="width: 10%;">Hora Fim</td>
                                        <td style="width: 10%;">Duração</td>
                                    </tr>
                                </thead>
    
                                <tbody>
                                <c:forEach var="agenda" items="${listAgendas}">
                                    <tr>
                                        <td>#${agenda.id}</td>
                                        <td>
                                            <div class="status pendente">
                                                ${agenda.status}
                                            </div>
                                        </td>
                                        <td>${agenda.paciente.nome}</td>
                                        <td>${agenda.online ? 'Online' : 'Endereco'}</td>
                                        <td>${agenda.dataHoraApresentar}</td>
                                        <td>18:15</td>
                                        <td>${agenda.profissional.duracaoAtendimento.nomeApresentar}</td>
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