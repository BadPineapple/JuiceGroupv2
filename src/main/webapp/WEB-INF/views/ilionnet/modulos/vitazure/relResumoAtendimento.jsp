<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cliente - <ilion:nomeEmpresa/></title>
         <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
		<script type="text/javascript" src="../../ilionnet/design/script/CalendarPopup.js"></script>
		<script type="text/javascript" src="../ilionnet/design/script/common.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.4.0/moment.min.js"></script>
		<script type="text/javascript">
			document.write(getCalendarStyles());
			var cal1x = new CalendarPopup("testdiv1");
		</script>
		<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div>
    </head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>
<!--content area start-->

<div id="content" class="pmd-content">
    <div class="container-fluid">
        <h1 class="section-title">
			<span>Relatório Atendimento Empresa</span>
		</h1><!-- End Title -->
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Vitazure</li>
		  <li class="active">Relatório Atendimento Empresa</li>
		</ol><!--breadcrum end--> 
		<div class="col-md-12">
		    <div class="component-box">
		    	<div class="pmd-card pmd-z-depth">
					<div class="pmd-card-body">	
						<form class="form-inline" method="get" action="?">
						<div class="row">
					      <input name="sp" type="hidden" value="true"/>
					      <input name="pagingPage" type="hidden" value="1"/>
						      <div class="col-md-2 col-lg-2 col-sm-12">
						           	<label for="name">Empresa</label>
						          <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100% !important;">
						           	<select name="palavraChave" id="palavraChave" class="form-control input-group-lg" style="width: 100% !important;">
									   <c:forEach var="empresa" items="${empresas}">
				   					      <option value="${empresa}">${empresa}</option>
									   </c:forEach>
									</select>
								</div> 
							</div>
							<div class="col-md-6 col-lg-6 col-sm-12">
			                 	<label>Periodo Consulta</label>
			                   <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100%;">
			                       <div class="col-md-5 col-lg-5 col-sm-12">
				                        <input type="date" name="dataInicio" id="dataInicio" class="form-control input-group-lg" style="width: 100%;font-size: 16px;"/>
			                        </div>
			                        <div class="col-md-1 col-lg-1 col-sm-12" style="top: 22px;"> 
			                          <p>a</p>
			                        </div>  
			                        <div class="col-md-5 col-lg-5 col-sm-12">   
				                        <input type="date" name="dataFim" id="dataFim" class="form-control input-group-lg" style="width: 100%;font-size: 16px;"/>
			                        </div>   
			                   </div>
			                 </div>
			                <div class="col-md-2 col-lg-2 col-sm-12">
						           	<label for="name">Status</label>
						          <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100% !important;">
						           	<select name="statusAgenda" id="statusAgenda" class="form-control input-group-lg" style="width: 100% !important;">
									   <option value="">Todas</option>
									   <c:forEach var="status" items="${status}">
				   					      <option value="${status}">${status.nome}</option>
									   </c:forEach>
									</select>
								</div> 
							</div> 
							<div class="col-md-2 col-lg-2 col-sm-12 text-center" style="padding-top: 35px;display: flex;">
							   <div style="padding-right: 5px;">
							    <button class="btn btn-primary" style="min-width: 10px;" title="buscar"><i class="fas fa-search"></i></button>
						       </div>
						  </form>
						          <div style="padding-right: 5px;">
									  <form  method="post" enctype="multipart/form-data" action="download-pdf.sp">
									     <button name="submit" type="submit" value="Exportar" class="btn btn-danger" style="min-width: 10px;" title="Imprimir PDF"><i class="far fa-file-pdf"></i></button>
									  </form>
								  </div>
								  <div style="padding-right: 5px;">
									  <form  method="post" enctype="multipart/form-data" action="download-excel.sp">
									     <button name="submit" type="submit" value="Exportar" class="btn btn-success" style="min-width: 10px;" title="Imprimir Excel"><i class="fal fa-file-csv"></i></i></button>
									  </form>
								  </div>
							</div>  
						</div>
					</div>
			    </div>
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Profissional</th>
							    <th class="text-center">Paciente</th>
							    <th class="text-center">Data consulta</th>
							    <th class="text-center">Tempo Consulta</th>
							    <th class="text-center">Situação</th>
							</tr>
							<c:forEach var="agenda" items="${agendas}">
							<tr>
								<td class="text-center">${agenda.id}</td>
								<td align="left">${agenda.profissional.pessoa.nome}</td>
								<td align="left">${agenda.paciente.nome}</td>
								<td align="center">${agenda.dataHoraApresentar}</td>
								<td align="center">${agenda.profissional.duracaoAtendimento.nomeApresentar}</td>
								<td align="center">${agenda.status}</td>
							</tr>
							</c:forEach>
						</table>
		            </div>
		        </div>
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
<script src="/assets/js/angular.min.js"></script>
<script src="/assets/js/bundle.libs.ilionnet.js"></script>
<script src="/assets/js/bundle.scripts.ilionnet.js"></script>
<script src="/assets/js/bundle.libs.angular.js"></script>

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
		$('#dataInicio').val('${param.dataInicio}');
		$('#dataFim').val('${param.dataFim}');
		$('#statusAgenda').val('${param.statusAgenda}');
	})();
</script>
<script type="text/javascript">
	(function() {
		$('#vitazure').click();
	})();

</script>

</body>

</html>
