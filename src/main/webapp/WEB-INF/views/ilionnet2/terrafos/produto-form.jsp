<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Produtos - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Produtos</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Produtos</li>
		</ol><!--breadcrum end--> 
		
		<form:form commandName="produto" method="post" action="?" cssClass="form-horizontal">
		
		<c:if test="${not empty hasErrors}">
		<div role="alert" class="alert alert-danger alert-dismissible">
			<button aria-label="Close" data-dismiss="alert" class="close" type="button">
				<span aria-hidden="true">×</span>
			</button>
			<form:errors path="*"/>
		</div>
		</c:if>
		
		<div class="col-md-12">
		    <div class="component-box">
		
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th>Produto</th>
							    <th class="text-center" style="width:60px;">Unidade</th>
							    <th class="text-center">Apresentação</th>
							    <th class="text-center">Estoque Inicial</th>
							    <th class="text-center" style="width:150px;">Data</th>
					        	<th></th>
							</tr>
							<c:forEach var="produto" items="${produtos}">
							<tr>
								<td>${produto.nome}</td>
								<td class="text-center">${produto.unidade}</td>
								<td class="text-center">${produto.apresentacao}</td>
								<td class="text-center">${produto.estoqueInicial}</td>
								<td class="text-center"><fmt:formatDate value="${produto.data}" pattern="dd/MM/yyyy"/></td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/produtos/${produto.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<%-- <a href="javascript:;" onclick="if(confirm('Tem certeza?')){location='<ilion:url/>ilionnet/terrafos/fazendas/${fazenda.id}/excluir';}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a> --%>
								</td>
							</tr>
							</c:forEach>
							<tr>
								<td><form:input path="nome" cssClass="form-control" placeholder="Nome"/></td>
								<td><form:input path="unidade" cssClass="form-control" placeholder="Unid." style="text-align: center;"/></td>
								<td><form:input path="apresentacao" cssClass="form-control" placeholder="Apresentação" style="text-align: center;"/></td>
								<td><form:input path="estoqueInicial" cssClass="form-control" placeholder="Estoque Inicial" style="text-align: center;"/></td>
								<td><form:input path="data" cssClass="form-control" placeholder="Data" style="text-align: center;"/></td>
								<td class="pmd-table-row-action">
									<button class="btn btn-primary">Salvar</button>
								</td>
							</tr>
						</table>
		            </div>
		
		        </div>
		    </div>
			 
		</div>
		
		</form:form>

		
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
