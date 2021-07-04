<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Pedidos - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">

		<div>
			
			<!-- <div class="pull-right table-title-top-action">
				<form>
					<input name="setarParametros" type="hidden" value="true"/>
      				<input name="pagingPage" type="hidden" value="1"/>
					<div class="pmd-textfield pull-left">
					  <input type="text" name="palavraChave" id="palavraChave" class="form-control" placeholder="O que voc&ecirc; procura..."><span class="pmd-textfield-focused"></span>
					</div>
					<button class="btn btn-primary pmd-btn-raised add-btn pmd-ripple-effect pull-left">Buscar</button>
				</form>
			</div> -->
			
			<!-- Title -->
			<h1 class="section-title">
				<span>Pedidos</span>
			</h1><!-- End Title -->
				
				<!--breadcrum start-->
			<ol class="breadcrumb text-left">
			  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
			  <li>Home Beauty</li>
			  <li class="active">Pedidos</li>
			</ol><!--breadcrum end--> 
		</div>

        
		<div class="col-sm-12">	
			<div class="component-box">
			
			<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
				<form class="form-inline" method="get" action="?">
			      <input name="sp" type="hidden" value="true"/>
			      <input name="pagingPage" type="hidden" value="1"/>
					
					<!-- <div class="form-group pmd-textfield pmd-textfield-floating-label">
			  		<select name="metodoPagamento" id="metodoPagamento" class="form-control">
						<option value="">- Forma Pagto -</option>
						<option value="NENHUM">Nenhum</option>
						<option value="CARTAO_DE_CREDITO">Cartão de Crédito</option>
					</select>
					</div> -->
					
					<%-- <div class="form-group pmd-textfield pmd-textfield-floating-label">
			  		<select name="pedidoStatus" id="pedidoStatus" class="form-control">
						<option value="">- Status -</option>
						<c:forEach var="s" items="${pedidoStatusLista}">
						<option value="${s}">${s.nome}</option>
						</c:forEach>
					</select>
					</div> --%>
			  		
			  		<div class="form-group pmd-textfield pmd-textfield-floating-label">
						<input type="text" name="palavraChave" id="palavraChave" class="form-control" placeholder="Palavra-chave"/>
						<span class="pmd-textfield-focused"></span>
					</div> 
					<button class="btn btn-primary">Buscar</button>
					<%-- <button type="button" class="btn btn-primary" onclick="location='<ilion:url/>ilionnet/pcm/pedido-exportar-xls';">Exportar XLS</button> --%>
					
				</form>
				</div>
			</div>
			</div>
		</div>
		
		
		<div class="col-md-12">
		    <div class="component-box">
		
				<ilion:vlhPagination valueListInfo="${pedidos.valueListInfo}" navCssClass="pull-right"/>
		
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <thead>
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Cliente</th>
							    <th class="text-center">E-mail</th>
							    <th class="text-center">Data</th>
							    <!-- <th class="text-center">Pagto.</th> -->
							    <th class="text-center">Vl. Total R$</th>
							    <th class="text-center">Profissional</th>
							    <th class="text-center">Status</th>
					        	<th></th>
							</tr>
							</thead>
							<c:forEach var="c" items="${pedidos}">
							<tbody>
							<tr>
								<td class="text-center">${c.id}</td>
								<td class="text-center">
									${c.cliente.nome}
									<c:if test="${c.avaliacaoProfissional>-1}">
										<i class="glyphicon glyphicon-star"></i> (${c.avaliacaoProfissional})
									</c:if>
								</td>
								<td class="text-center">${c.cliente.email}</td>
								<td class="text-center">${c.dataCriacaoFormatada}</td>
								<%-- <td class="text-center">${c.metodoPagamento.nome}</td> --%>
								<td class="text-center"><ilion:formatNumber value="${c.valorTotal}"/></td>
								<td class="text-center">
									${c.profissional.nome}
									<c:if test="${c.avaliacaoCliente>-1}">
										<i class="glyphicon glyphicon-star"></i> (${c.avaliacaoCliente})
									</c:if>
								</td>
								<td class="text-center">
									${c.status.nome}
								</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/homebeauty/pedidos/${c.id}/detalhes" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<%-- <a href="javascript:;" onclick="if(confirm('Tem certeza?'))location='<ilion:url/>ilionnet/satelite/pedidos/${c.id}/excluir';" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a> --%>
								</td>
							</tr>
							</tbody>
							</c:forEach>
						</table>
		            </div>
		
			        
					
		        </div>
		        
				<ilion:vlhPagination valueListInfo="${pedidos.valueListInfo}" navCssClass="pull-right"/>
		        
		    </div>
			 
		</div>

		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script>

	(function() {
		
		/* $('#metodoPagamento').val('${param.metodoPagamento}');
		$('#pedidoStatus').val('${param.pedidoStatus}');
		$('#palavraChave').val('${param.palavraChave}');
		
		$('.CONFIRMADO').addClass('label-success');
		$('.FATURADO').addClass('label-success');
		$('.RECUSADO').addClass('label-danger');
		$('.CANCELADO').addClass('label-danger'); */
		
	})();
	
</script>

<script>

	(function() {
		$('#homebeauty').click();
	})();

</script>

</body>

</html>
