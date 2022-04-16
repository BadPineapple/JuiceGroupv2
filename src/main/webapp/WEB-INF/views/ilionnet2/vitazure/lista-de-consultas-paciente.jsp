<%@page import="ilion.vitazure.model.Profissional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="agendamentoApp" ng-controller="AgendamentoController">
<style>
.modal-dialog{
    position: relative;
    display: table; /* This is important */ 
    overflow-y: auto;    
    overflow-x: hidden;
    width: auto;
    min-width: 900px;  
}
</style>
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script src="../assets/js/vitazure/agendamento.js"></script>
	<link rel="stylesheet" type="text/css" href="<ilion:url/>ilionnet/pmd-admin/assets/css/bootstrap.min.css">
</head>
<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
         <jsp:include page="includes/include-painel-person.jsp" flush="true" />
        <div class="area-white">
            <div class="container">
            	<div class="modal fade" id="modalNovaConsulta" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" >
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				       <div class="modal-body" id="body-modal" >
				      	
				      	<iframe id="iframe-consulta" src="" style="top:0; left:0; bottom:0; right:0; width:100%; height:700px; border:none; margin:0; padding:0; overflow:hidden; z-index:999999;"></iframe>
				
				      </div>
				    </div>
				  </div>
				</div>
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Minhas Consultas</h3>
                        <p>Confira suas consultas. É necessário o aceite.</p>
                    </div>
                    <div class="col-12 col-xl-12">
                        <div class="search">
                            <form class="form-inline" method="get" action="?">
								<div class="col-12">
							      <input name="sp" type="hidden" value="true"/>
							      <input name="pagingPage" type="hidden" value="1"/>
								      <div class="col-md-10 col-lg-10 col-sm-12">
								          <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100% !important;">
								           	<label for="name" class="control-label" style="justify-content: left;">Palavra-Chave</label>
											<input type="Large" name="palavraChave" id="palavraChave" class="form-control input-group-lg" style="width: 100% !important;"/>
										</div> 
									</div>
									<div class="col-md-2 col-lg-2 col-sm-12 text-center" style="padding-top: 20px;">	
									  <button class="btn btn-primary" style="height: 45px;width: 100%;">Buscar</button>
									</div>  
								</div>	
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
                                        <td style="text-align: center;">Nr. Transação</td>
                                        <td style="text-align: center;">Opções</td>
                                    </tr>
                                </thead>
    
                                <tbody>
                                <c:forEach var="agenda" items="${listAgendas}">
                                    <tr>
                                        <td ><i class="fas fa-info-circle" style="padding: 8px;font-size: 17px;" data-toggle="tooltip" data-placement="top" data-html="true" 
                                         title="Email:${agenda.profissional.pessoa.email}</br>Telefone:${agenda.profissional.pessoa.telefone}</br>Celular:${agenda.profissional.pessoa.celular}"></i>${agenda.profissional.pessoa.nome}</td>
                                        <td style="text-align: center;">${agenda.online ? 'Online' : 'Endereco'}</td>
                                        <td style="text-align: center;">${agenda.dataHoraApresentar}</td>
                                        <td style="text-align: center;">${agenda.profissional.duracaoAtendimento.nomeApresentar}</td>
                                        <td style="text-align: center;">
                                            <div class="status ${agenda.status == 'CONFIRMADO' || agenda.status == 'REALIZADO' ?  'realizada' : agenda.status == 'REMARCADO' || agenda.status == 'CANCELADO' ? 'cancelada' : 'pendente'}" style="width: 100%;">
                                                ${agenda.status.nome}
                                            </div>
                                        </td>
                                        <td style="text-align: center;"> ${agenda.idTransacao}
                                        
                                        </td>
                                        <td style="text-align: center;">
                                          <c:if test="${agenda.status == 'PENDENTE' || agenda.status == 'CONFIRMADO' || agenda.status == 'AGUARDANDO_REMARCACAO'}">
	                                           <button  class="btn btn-danger" ng-click="definirAgendamento('${agenda.id}' , 'CANCELADO')">Cancelar</button>
	                                           <a href="<ilion:url/>vitazure/reagendar/${agenda.id}"  class="btn btn-warning">Remarcar</a>
	                                      </c:if>    
	                                      <c:if test="${agenda.status == 'REALIZADO'}">
	                                     	 <button style="font-size: 12px;"  class="btn btn-primary" data-backdrop="false" ng-click="definirNovoAgendamento('${agenda.profissional.id}')" >Nova Consulta</button>
	                                      </c:if>
	                                      <c:if test="${agenda.status == 'AG_PAGAMENTO' && agenda.urlPagamento != null}">
	                                     	   <a href="${agenda.urlPagamento}?format=pdf" class="btn btn-primary" target="_blank">Re-imprimir Boleto</a>
	                                      </c:if>  
<%--                                            <c:if test="${agenda.online && agenda.status == 'CONFIRMADO'}"> --%>
<%-- 	                                           <a href="${agenda.urlAtendimentoOnline}" target="_blank"> --%>
<!-- 	                                             <button  class="btn btn-primary" >Entrar Consulta</button> -->
<!-- 	                                           </a>   -->
<%--                                            </c:if> --%>
                                        </td>
                                    </tr>
    							</c:forEach>
                                </tbody>
                            </table>
                       </div>
                         <div class="row" >
		            <div class="col-md-12" >
		             	<ilion:vlhPagination valueListInfo="${listAgendas.valueListInfo}" navCssClass="pull-right"/>
				    </div>
			    </div>
                    </div>
                </div>
            </div>
        </div>
		<script>
		$(function () {
			  $('[data-toggle="tooltip"]').tooltip()
			})
		</script>
        <jsp:include page="includes/include-footer.jsp" flush="true" />
        <script src="../assets/js/bundle.libs.ilionnet.js"></script>
		<script src="../assets/js/bundle.scripts.ilionnet.js"></script>
		<script src="../assets/js/bundle.libs.angular.js"></script>
    </div>
</body>
</html>