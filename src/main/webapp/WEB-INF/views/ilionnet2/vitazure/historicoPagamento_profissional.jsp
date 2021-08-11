<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">

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
                        <h3 style="padding-bottom: 52px;">Historico Pagamento</h3>
                    </div>
<!--                     <div class="col-12 col-xl-4"> -->
<!--                         <div class="search"> -->
<!--                             <form> -->
<!--                                 <input type="text" placeholder="Buscar"> -->
<!--                                 <button href="#" class="search-button"> -->
<!--                                     <img src="images/search.png" alt=""> -->
<!--                                 </button> -->
<!--                             </form> -->
<!--                         </div> -->
<!--                     </div> -->

                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="tabela-consultas">
                                <thead>
                                    <tr>
                                        <td style="text-align: center;">id</td>
                                        <td style="text-align: center;">Agenda</td>
                                        <td style="text-align: center;">Data Transação</td>
                                        <td style="text-align: center;">Valor Recebido</td>
                                        <td style="text-align: center;">Status</td>
                                    </tr>
                                </thead>
    
                                <tbody>
                                <c:forEach var="pagamento" items="${listPagamentos}">
                                    <tr>
                                        <td style="text-align: center;">${pagamento.id}</td>
                                        <td style="text-align: center;">${pagamento.agenda > 0 ? pagamento.agenda : '-'}</td>
                                        <td style="text-align: center;">${pagamento.dataFormatada}</td>
                                        <td style="text-align: center;">${pagamento.agenda > 0 ? pagamento.valorPago : '-'}</td>
                                        <td style="text-align: center;">${pagamento.status}</div>
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