<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Ra�as - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Ra�as</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terraf�s</li>
		  <li class="active">Ra�as</li>
		</ol><!--breadcrum end--> 
		
		<form:form commandName="raca" method="post" action="?" cssClass="form-horizontal">
		
		<c:if test="${not empty hasErrors}">
		<div role="alert" class="alert alert-danger alert-dismissible">
			<button aria-label="Close" data-dismiss="alert" class="close" type="button">
				<span aria-hidden="true">�</span>
			</button>
			<form:errors path="*"/>
		</div>
		</c:if>
		
		<div class="col-md-4">
		    <div class="component-box">
		
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tr>
							    <th>Nome</th>
							    <th></th>
							</tr>
							<c:forEach var="r" items="${racas}">
							<tr>
								<td class="text-center">${r.nome}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/racas/${r.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<%-- <a href="javascript:;" onclick="if(confirm('Tem certeza?')){location='<ilion:url/>ilionnet/terrafos/fazendas/${fazenda.id}/excluir';}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a> --%>
								</td>
							</tr>
							</c:forEach>
							<tr>
								<td><form:input path="nome" cssClass="form-control" placeholder="Nome" style="text-align: center;"/></td>
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
