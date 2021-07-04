<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Notificações - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">

		<div>
			
			<div class="pull-right table-title-top-action">
				<form action="?">
					<input name="setarParametros" type="hidden" value="true"/>
      				<input name="pagingPage" type="hidden" value="1"/>
					<div class="pmd-textfield pull-left">
					  <input type="text" name="palavraChave" id="palavraChave" class="form-control" placeholder="Palavra-chave..."><span class="pmd-textfield-focused"></span>
					</div>
					<button class="btn btn-primary pmd-btn-raised add-btn pmd-ripple-effect pull-left">Buscar</button>
				</form>
			</div>
			
			<!-- Title -->
			<h1 class="section-title">
				<span>Notificações</span>
			</h1><!-- End Title -->
				
				<!--breadcrum start-->
			<ol class="breadcrumb text-left">
			  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
			  <li>Home Beauty</li>
			  <li class="active">Notificações</li>
			</ol><!--breadcrum end--> 
		</div>

        
		<div class="col-md-6">
			<a href="<ilion:url/>ilionnet/homebeauty/notificacoes/nova/" class="btn btn-primary" role="button">Novo</a>
		</div>
		
		<div class="col-md-6">
			<ilion:vlhPagination valueListInfo="${notificacoes.valueListInfo}" navCssClass="pull-right"/>
		</div>

		
		<div class="col-md-12">
		    <div class="component-box">
				
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <thead>
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Entidade</th>
							    <th class="text-center">Tipo</th>
							    <th class="text-center">Título</th>
							    <th class="text-center">Conteúdo</th>
							    <th class="text-center">Pedido</th>
							    <th class="text-center">Data</th>
					        	<th></th>
							</tr>
							</thead>
							<c:forEach var="m" items="${notificacoes}">
							<tbody>
							<tr>
								<td class="text-center">${m.id}</td>
								<td class="text-center">${m.nomeEntidade}/${m.idEntidade}</td>
								<td class="text-center">${m.tipo}</td>
								<td class="text-center">${m.titulo}</td>
								<td class="text-center">${m.conteudo}</td>
								<td class="text-center">
									<a href="<ilion:url/>ilionnet/homebeauty/pedidos/${m.pedido.id}/detalhes">${m.pedido.id}</a>
								</td>
								<td class="text-center">${m.dataCriacao}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/homebeauty/notificacoes/${m.id}/detalhes" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
								</td>
							</tr>
							</tbody>
							</c:forEach>
						</table>
		            </div>
		
			        
					
		        </div>
		        
		    </div>
			 
		</div>
		
		<div class="col-md-6">
			<a href="<ilion:url/>ilionnet/homebeauty/notificacoes/nova/" class="btn btn-primary" role="button">Novo</a>
		</div>
		
		<div class="col-md-6">
			<ilion:vlhPagination valueListInfo="${notificacoes.valueListInfo}" navCssClass="pull-right"/>
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

<script>

	(function() {
		$('#homebeauty').click();
	})();

</script>

</body>

</html>
