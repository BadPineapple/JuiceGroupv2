<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Fazendas - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Fazendas</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Fazendas</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="col-md-12">
		    <div class="component-box">
		
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Nome</th>
							    <th class="text-center">Prop.</th>
					        	<th></th>
							</tr>
							<c:forEach var="fazenda" items="${fazendas}">
							<tr>
								<td class="text-center">${fazenda.id}</td>
								<td align="center">
									${fazenda.nome} <a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazenda.id}/selecionar">(selecionar)</a>
								</td>
								<td align="center">${fazenda.proprietario}</td>
								<td class="pmd-table-row-action">
								
									<ilion:permissaoExiste permissoes="terrafos_fazendas">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazenda.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<%-- <a href="javascript:;" onclick="if(confirm('Tem certeza?')){location='<ilion:url/>ilionnet/terrafos/fazendas/${fazenda.id}/excluir';}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a> --%>
									</ilion:permissaoExiste>
								</td>
							</tr>
							</c:forEach>
						</table>
		            </div>
		
		        </div>
		    </div>
			
			<ilion:permissaoExiste permissoes="terrafos_fazendas">
			<a href="<ilion:url/>ilionnet/terrafos/fazendas/novo" class="btn btn-primary" role="button">Adicionar Fazenda</a>
			</ilion:permissaoExiste>
			 
		</div>

		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script type="text/javascript">

	(function() {
		$('#terrafos').click();
	})();

</script>

</body>

</html>
