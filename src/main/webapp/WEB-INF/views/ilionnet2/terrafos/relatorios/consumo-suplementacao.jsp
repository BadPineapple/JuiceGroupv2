<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Relatórios / Consumo Suplementação - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../../inc/include-head.jsp" flush="true"/>
		
		
    </head>

<body>

<jsp:include page="../../inc/include-header.jsp" flush="true"/>

<jsp:include page="../../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title" id="services">
			<span>Consumo Suplementação</span>
		</h1><!-- End Title -->
			
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li>Home</li>
		  <li>Relatórios</li>
		  <li class="active">Consumo Suplementação</li>
		</ol><!--breadcrum end--> 
		
		<div class="pmd-card pmd-z-depth">
			<div class="pmd-card-body">
			
			<c:choose>
				<c:when test="${not empty fazendaSessao}">
					
					<div class="row">
					
						<div class="col-sm-12">
						
							<form method="get" class="form-inline" action="?">
							  <div class="form-group">
							    <label for="dataInicio" class="sr-only">Data Inicial</label>
							    <input type="text" class="form-control" id="dataInicio" name="dataInicio" placeholder="Data Início">
							  </div>
							  <div class="form-group">
							    <label for="dataFim" class="sr-only">Data Fim</label>
							    <input type="text" class="form-control" id="dataFim" name="dataFim" placeholder="Data Fim">
							  </div>
							  <button type="submit" class="btn btn-default">Buscar</button>
							</form>
						
						</div>
						
					</div>
					
					<br/>
					
					<div class="row">
						
						<div class="col-sm-4">
						
							<table class="table table-striped">
							  <thead>
							  	<tr>
							  		<th>Produto</th>
							  		<th>Consumo (Kg)</th>
							  	</tr>
							  </thead>
							  <tbody>
							  	<c:forEach var="r" items="${registros}">
							  	<tr>
							  		<td>${r.produto_nome}</td>
							  		<td>${r.consumo}</td>
							  	</tr>
							  	</c:forEach>
							  </tbody>
							</table>
						
						</div>
						
					</div>
					
					
				</c:when>
				<c:otherwise>
					
					<div class="alert alert-danger" role="alert">
						<em>Nenhuma fazenda está vinculada ao seu usuário.</em>
						<p>Por favor, contate o administrador.</p>					
					</div>
				
				</c:otherwise>
			</c:choose>
			
		</div><!--end component header-->
		</div>


    </div>


</div><!--end content area-->



<jsp:include page="../../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../../inc/include-js-footer.jsp" flush="true"/>

<script src="<ilion:url/>assets/js/jquery.maskedinput.min.js"></script>

<script type="text/javascript">

	(function() {
		$('#relatorios').click();
	})();
	
	$(function(jQuery){
		$("#dataInicio").mask("99/99/9999");
		$("#dataFim").mask("99/99/9999");
		
		$('#dataInicio').val('${param.dataInicio}');
		$('#dataFim').val('${param.dataFim}');
	});

</script>

</body>

</html>
