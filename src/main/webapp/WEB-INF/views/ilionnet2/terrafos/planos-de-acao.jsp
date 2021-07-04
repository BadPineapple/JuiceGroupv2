<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Planos de Ação - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Planos de Ação</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Planos de Ação</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="col-md-12">
		    <div class="component-box">
		
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th class="text-center">ID</th>
							    <th class="text-center">Data Visita</th>
							    <th class="text-center">O quê?</th>
							    <th class="text-center">Como?</th>
							    <th class="text-center">Porque?</th>
							    <th class="text-center">Quem?</th>
							    <th class="text-center">Quando?</th>
							    <th class="text-center">Status</th>
							    <th class="text-center">Custo R$</th>
					        	<th></th>
							</tr>
							<c:forEach var="p" items="${planosDeAcao}">
							<tr>
								<td class="text-center">${p.id}</td>
								<td class="text-center"><fmt:formatDate value="${p.dataVisita}" pattern="dd/MM/yyyy"/></td>
								<td class="text-center">${p.oQue}</td>
								<td class="text-center">${p.como}</td>
								<td class="text-center">${p.porQue}</td>
								<td class="text-center">${p.quem}</td>
								<td class="text-center">${p.quando}</td>
								<td class="text-center">${p.status.nome}</td>
								<td class="text-center">${p.custo}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/planos-de-acao/${p.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<%-- <a href="javascript:;" onclick="if(confirm('Tem certeza?')){location='<ilion:url/>ilionnet/terrafos/fazendas/${fazenda.id}/excluir';}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a> --%>
								</td>
							</tr>
							</c:forEach>
						</table>
		            </div>
		
		        </div>
		    </div>
			
			<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/planos-de-acao/novo" class="btn btn-primary" role="button">Adicionar Plano de Ação</a>
			 
		</div>

		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script type="text/javascript">

	(function() {
		$('#avaliacaoTecnica').click();
	})();

</script>

</body>

</html>
