<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Serviços - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Serviços</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Home Beauty</li>
		  <li class="active">Serviços</li>
		</ol><!--breadcrum end--> 
		
		<div class="col-sm-12">	
			<div class="component-box">
			
			<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
				<form class="form-inline" method="get" action="?">
			      <input name="sp" type="hidden" value="true"/>
			      <input name="pagingPage" type="hidden" value="1"/>
					
					<div class="form-group pmd-textfield pmd-textfield-floating-label">
						<input type="text" name="palavraChave" id="palavraChave" class="form-control" placeholder="Palavra-chave"/>
						<span class="pmd-textfield-focused"></span>
					</div> 
					<button class="btn btn-primary">Buscar</button>
					
				</form>
				</div>
			</div>
			</div>
		</div>
		
		<div class="col-md-12">
		    <div class="component-box">
		    
		    	<ilion:vlhPagination valueListInfo="${servicos.valueListInfo}" navCssClass="pull-right"/>
		
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Categoria</th>
							    <th class="text-center">Nome</th>
							    <th class="text-center">Preço</th>
							    <th class="text-center">Status</th>
					        	<th></th>
							</tr>
							<c:forEach var="produto" items="${servicos}">
							<tr>
								<td class="text-center">${produto.id}</td>
								<td align="center">${produto.categoria.nome}</td>
								<td align="center">${produto.nome}</td>
								<td align="center"><ilion:formatNumber value="${produto.preco}"/></td>
								<td align="center">${produto.status.nome}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/homebeauty/servicos/${produto.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
								</td>
							</tr>
							</c:forEach>
						</table>
		            </div>
		
		        </div>
		        
	            <ilion:vlhPagination valueListInfo="${servicos.valueListInfo}" navCssClass="pull-right"/>
	            
		    </div>
			
			<a href="<ilion:url/>ilionnet/homebeauty/servicos/novo" class="btn btn-primary" role="button">Novo</a>
			 
		</div>

		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script>

	(function() {
		
		$('#palavraChave').val('${param.palavraChave}');
		
	})();
	
</script>

<script type="text/javascript">

	(function() {
		$('#homebeauty').click();
	})();

</script>

</body>

</html>
