<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Relatórios / Movimentação de Rebanho - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../../inc/include-head.jsp" flush="true"/>
		
		
    </head>

<body>

<jsp:include page="../../inc/include-header.jsp" flush="true"/>

<jsp:include page="../../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title" id="services">
			<span>Movimentação de Rebanho</span>
		</h1><!-- End Title -->
			
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li>Home</li>
		  <li>Relatórios</li>
		  <li class="active">Movimentação de Rebanho</li>
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
						
						<div class="col-sm-12">
						
							<table class="table table-striped">
							  <thead>
							  	<tr>
							  		<th>Categoria</th>
							  		<th>Estoque Inicial</th>
							  		<th>Nascimento</th>
							  		<th>Compra</th>
							  		<th>Transf. (+)</th>
							  		<th>Venda</th>
							  		<th>Morte</th>
							  		<th>Transf. (-)</th>
							  		<th>Estoque Final</th>
							  	</tr>
							  </thead>
							  <tbody>
							  	<c:forEach var="m" items="${relatorio.linhas}">
							  	<tr>
							  		<td>${m.nome}</td>
							  		<td>${m.estoqueInicial}</td>
							  		<td>${m.nascimento}</td>
							  		<td>${m.compra}</td>
							  		<td>${m.transferenciaEntrada}</td>
							  		<td>${m.venda}</td>
							  		<td>${m.morte}</td>
							  		<td>${m.transferenciaSaida}</td>
							  		<td>${m.estoqueFinal}</td>
							  	</tr>
							  	</c:forEach>
							  	<tr>
							  		<td>TOTAL</td>
							  		<td>${relatorio.total.estoqueInicial}</td>
							  		<td>${relatorio.total.nascimento}</td>
							  		<td>${relatorio.total.compra}</td>
							  		<td>${relatorio.total.transferenciaEntrada}</td>
							  		<td>${relatorio.total.venda}</td>
							  		<td>${relatorio.total.morte}</td>
							  		<td>${relatorio.total.transferenciaSaida}</td>
							  		<td>${relatorio.total.estoqueFinal}</td>
							  	</tr>
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
