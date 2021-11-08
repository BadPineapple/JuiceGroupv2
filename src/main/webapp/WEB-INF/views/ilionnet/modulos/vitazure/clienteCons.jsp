<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cliente - <ilion:nomeEmpresa/></title>
         <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
    </head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">
    <div class="container-fluid">
        <h1 class="section-title">
			<span>Cliente</span>
		</h1><!-- End Title -->
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Vitazure</li>
		  <li class="active">Cliente </li>
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
						</form>
					</div>
			    </div>
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Nome</th>
							    <th class="text-center">Email</th>
							    <th class="text-center">Empresa</th>
							    <th class="text-center">Opcoes</th>
							</tr>
							<c:forEach var="cliente" items="${clientes}">
							<tr>
								<td class="text-center">${cliente.id}</td>
								<td >${cliente.nome}</td>
								<td >${cliente.email}</td>
								<td >${cliente.empresaImportacao}</td>
								<td class="pmd-table-row-action" align="center">
									<a href="<ilion:url/>vitazure/cliente/${cliente.id}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<a href="<ilion:url/>vitazure/cliente/excluir/${cliente.id}" title="Excluir" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
                       					 <i class="material-icons md-dark pmd-sm">delete</i>
                      				</a>
								</td>
							</tr>
							</c:forEach>
						</table>
		            </div>
		        </div>
	             <div class="row" >
			        <div class="col-md-2" style="padding-top: 17px;">
						  <a href="<ilion:url/>vitazure/cliente/0" class="btn btn-primary" role="button">Novo</a>
		            </div>
		            <div class="col-md-10" >
		             	<ilion:vlhPagination valueListInfo="${clientes.valueListInfo}" navCssClass="pull-right"/>
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
