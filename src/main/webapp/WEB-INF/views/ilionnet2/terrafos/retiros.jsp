<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Retiros - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Retiros</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Retiros</li>
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
					        	<th></th>
							</tr>
							<c:forEach var="retiro" items="${retiros}">
							<tr>
								<td class="text-center">${retiro.id}</td>
								<td align="center">${retiro.nome}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/retiros/${retiro.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
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
			
			<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/retiros/novo" class="btn btn-primary" role="button">Adicionar Retiro</a>
			 
		</div>

		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script type="text/javascript">

	(function() {
		$('#cadastros').click();
	})();

</script>

</body>

</html>
