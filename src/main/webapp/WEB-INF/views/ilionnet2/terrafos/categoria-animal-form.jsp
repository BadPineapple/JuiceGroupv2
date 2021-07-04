<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Categoria Animal - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Categoria Animal</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Categoria Animal</li>
		</ol><!--breadcrum end--> 
		
		<form:form commandName="categoriaAnimal" method="post" action="?" cssClass="form-horizontal">
		
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
							    <th>Lote</th>
							    <th class="text-center" style="width:90px;">Sigla</th>
							    <th class="text-center">Categoria Animal</th>
							    <th class="text-center">Nasc. (sexo)</th>
							    <th class="text-center">Observação</th>
					        	<th></th>
							</tr>
							<c:forEach var="ca" items="${categoriasAnimal}">
							<tr>
								<td>${ca.lote}</td>
								<td class="text-center">${ca.sigla}</td>
								<td class="text-center">${ca.nome}</td>
								<td class="text-center">${ca.nascimentosSexo.nome}</td>
								<td class="text-center">${ca.observacao}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/categorias-animal/${ca.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<a href="javascript:;" onclick="if(confirm('Tem certeza?')){location='<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/categorias-animal/${ca.id}/excluir';}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a>
								</td>
							</tr>
							</c:forEach>
							<tr>
								<td><form:input path="lote" cssClass="form-control" placeholder="Lote"/></td>
								<td><form:input path="sigla" cssClass="form-control" placeholder="Sigla" style="text-align: center;" maxlength="2"/></td>
								<td><form:select path="nome" cssClass="form-control">
									<form:option value="" label="- Categoria Animal -" />
									<form:option value="Macho, 0 a 12 meses" label="Macho, 0 a 12 meses" />
									<form:option value="Fêmea, 0 a 12 meses" label="Fêmea, 0 a 12 meses" />
									<form:option value="Macho, 13 a 24 meses" label="Macho, 13 a 24 meses" />
									<form:option value="Fêmea, 13 a 24 meses" label="Fêmea, 13 a 24 meses" />
									<form:option value="Macho, 25 a 36 meses" label="Macho, 25 a 36 meses" />
									<form:option value="Fêmea, 25 a 36 meses" label="Fêmea, 25 a 36 meses" />
									<form:option value="Macho, acima de 36 meses" label="Macho, acima de 36 meses" />
									<form:option value="Fêmea, acima de 36 meses" label="Fêmea, acima de 36 meses" />
								</form:select></td>
								<td><form:select path="nascimentosSexo" cssClass="form-control">
									<form:option value="" label="- Nasc. Sexo -" />
									<form:option value="MACHO" label="Macho" />
									<form:option value="FEMEA" label="Fêmea" />
								</form:select></td>
								<td><form:input path="observacao" cssClass="form-control" placeholder="Observação" style="text-align: center;"/></td>
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
