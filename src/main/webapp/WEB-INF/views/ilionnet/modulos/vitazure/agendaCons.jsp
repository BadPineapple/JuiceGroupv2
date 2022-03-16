<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Agenda - <ilion:nomeEmpresa/></title>
         <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
         <style>
           th {
			   cursor: pointer;
			}
         </style>
         
    </head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">
    <div class="container-fluid">
        <h1 class="section-title">
			<span>Agenda</span>
		</h1><!-- End Title -->
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Vitazure</li>
		  <li class="active">Agenda </li>
		</ol><!--breadcrum end--> 
		<div class="col-md-12">
		    <div class="component-box">
		    	<div class="pmd-card pmd-z-depth">
					<div class="pmd-card-body">	
						<form class="form-inline" method="get" action="?">
						<div class="row">
					      <input name="sp" type="hidden" value="true"/>
					      <input name="pagingPage" type="hidden" value="1"/>
						      <div class="col-md-10 col-lg-10 col-sm-12">
						          <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100% !important;">
						           	<label for="name" class="control-label">Palavra-Chave</label>
									<input type="Large" name="palavraChave" id="palavraChave" class="form-control input-group-lg" style="width: 100% !important;"/>
								</div> 
							</div>
							<div class="col-md-2 col-lg-2 col-sm-12 text-center" style="padding-top: 35px;">	
							  <button class="btn btn-primary">Buscar</button>
							</div>  
						</div>
					</div>
			    </div>
		        <!-- Basic Bootstrap Table example -->
		     <valuelist:root value="agendas" url="?setarParametros=true&" includeParameters="palavraChave">    
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		            <div class="table-responsive">
		                <table class="table table-bordered" id="tabelaAgenda">
		                   <valuelist:row bean="agenda">
		                        <valuelist:column title="ID"> 
							        <c:out value="${agenda.id}"/>
						        </valuelist:column> 
								<valuelist:column title="Profissional" sortable="asc" property="pessoa.nome"> 
								<valuelist:attribute name="align" value="left"/>
									<c:out value="${agenda.profissional.pessoa.nome}"/>
								</valuelist:column> 
								<valuelist:column title="Paciente" sortable="asc" property="pessoa.nome"> 
							        <c:out value="${agenda.paciente.nome}"/>
						        </valuelist:column> 
								<valuelist:column title="Convênio"> 
						        	<c:out value="${agenda.paciente.empresaImportacao != '' ? 'Empresa' : 'Particular'}"/> 
						        </valuelist:column> 
								<valuelist:column title="Data consulta" sortable="asc" property="dataHoraAgendamento"> 
							        <c:out value="${agenda.dataHoraApresentar}"/>
						        </valuelist:column> 
								<valuelist:column title="Tempo Consulta" > 
							        <c:out value="${agenda.profissional.duracaoAtendimento.nomeApresentar}"/>
						        </valuelist:column> 
								<valuelist:column title="Situação" sortable="asc" property="status"> 
							        <c:out value="${agenda.status}"/>
						        </valuelist:column> 
								<valuelist:column title="Avaliação"> 
							        <c:out value="${agenda.avaliacaoAtendimentoNota}"/>
						        </valuelist:column> 
							</valuelist:row>
<!-- 					        <tr> -->
<!-- 							    <th class="text-center">ID</th> -->
<!-- 							    <th class="text-center">Profissional <button type="submit" > <i class="fas fa-sort-amount-up"></i></button></th> -->
<!-- 							    <th class="text-center">Paciente</th> -->
<!-- 							    <td class="text-center">Convênio</td> -->
<!-- 							    <th class="text-center">Data consulta</th> -->
<!-- 							    <th class="text-center">Tempo Consulta</th> -->
<!-- 							    <th class="text-center">Situação</th> -->
<!-- 							    <th class="text-center">Avaliação</th> -->
<!-- 							</tr> -->
<%-- 							<c:forEach var="agenda" items="${agendas}"> --%>
<!-- 							<tr> -->
<%-- 								<td class="text-center">${agenda.id}</td> --%>
<%-- 								<td align="left">${agenda.profissional.pessoa.nome}</td> --%>
<%-- 								<td align="left">${agenda.paciente.nome}</td> --%>
<%-- 								<td align="center">${agenda.paciente.empresaImportacao != "" ? "Empresa" : "Particular"}</td> --%>
<%-- 								<td align="center">${agenda.dataHoraApresentar}</td> --%>
<%-- 								<td align="center">${agenda.profissional.duracaoAtendimento.nomeApresentar}</td> --%>
<%-- 								<td align="center">${agenda.status}</td> --%>
<%-- 								<td align="center">${agenda.avaliacaoAtendimentoNota}</td> --%>
<!-- 							</tr> -->
<%-- 							</c:forEach> --%>
						</table>
		            </div>
		        </div>
		      </valuelist:root>
		      </form>	  
	             <div class="row" >
		            <div class="col-md-10" >
		             	<ilion:vlhPagination valueListInfo="${agendas.valueListInfo}" navCssClass="pull-right"/>
				    </div>
			    </div>
		    </div>
		</div>
    </div>
</div><!--end content area-->

<jsp:include page="../../../ilionnet2/inc/include-footer.jsp" flush="true"/>
<jsp:include page="../../../ilionnet2/inc/include-js-footer.jsp" flush="true"/>
<c:if test="${param.m == 'ok'}">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="Dados Excluido com sucesso."
		data-type="success" 
		class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
		id="alertSucess"
		style="display:none;">
		Sucesso
	</button>
	<script type="text/javascript">
		(function() {
			setTimeout(function() {
				$('#alertSucess').click();
			}, 300);
		})();
	</script>
</c:if>


<script>

	(function() {
		$('#palavraChave').val('${param.palavraChave}');
	})();
</script>
<script type="text/javascript">
	(function() {
		$('#vitazure').click();
	})();

</script>

</body>

</html>
