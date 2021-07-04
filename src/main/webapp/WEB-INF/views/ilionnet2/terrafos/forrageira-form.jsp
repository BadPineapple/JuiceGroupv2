<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Forrageiras - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
		<style>
			.coluna_forrageira {width:200px;}
		</style>
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Forrageiras</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Forrageiras</li>
		</ol><!--breadcrum end--> 
		
		<form:form commandName="forrageira" method="post" action="?" cssClass="form-horizontal">
		
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
		                <table class="table table-bordered table_forrageira">
		                	<tr>
		                		<th class="text-center" colspan="14">Distribuição da Produção (%)</th>
		                		<th class="text-center" colspan="2">Manejo do capim</th>
		                	</tr>
					        <tr>
							    <th>Espécie</th>
							    <th class="text-center">
							    	Produção<br/>
							    	<span style="font-size:10px;">(kg/MS/há/ano)</span>
							    </th>
							    <th class="text-center">jan</th>
							    <th class="text-center">fev</th>
							    <th class="text-center">mar</th>
							    <th class="text-center">abr</th>
							    <th class="text-center">mai</th>
							    <th class="text-center">jun</th>
							    <th class="text-center">jul</th>
							    <th class="text-center">ago</th>
							    <th class="text-center">set</th>
							    <th class="text-center">out</th>
							    <th class="text-center">nov</th>
							    <th class="text-center">dez</th>
							    <th class="text-center">Entrada (cm)</th>
							    <th class="text-center">Saída (cm)</th>
					        	<th></th>
							</tr>
							<c:forEach var="forrageira" items="${forrageiras}">
							<tr>
								<td>${forrageira.especie}</td>
								<td class="text-center">${forrageira.producao}</td>
								<td class="text-center">${forrageira.jan}</td>
								<td class="text-center">${forrageira.fev}</td>
								<td class="text-center">${forrageira.mar}</td>
								<td class="text-center">${forrageira.abr}</td>
								<td class="text-center">${forrageira.mai}</td>
								<td class="text-center">${forrageira.jun}</td>
								<td class="text-center">${forrageira.jul}</td>
								<td class="text-center">${forrageira.ago}</td>
								<td class="text-center">${forrageira.set}</td>
								<td class="text-center">${forrageira.out}</td>
								<td class="text-center">${forrageira.nov}</td>
								<td class="text-center">${forrageira.dez}</td>
								<td class="text-center">${forrageira.entrada}</td>
								<td class="text-center">${forrageira.saida}</td>
								<td class="pmd-table-row-action">
									<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/forrageiras/${forrageira.id}/editar" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">edit</i>
									</a>
									<a href="javascript:;" onclick="if(confirm('Tem certeza?')){location='<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/forrageiras/${forrageira.id}/excluir';}" class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm">
										<i class="material-icons md-dark pmd-sm">delete</i>
									</a>
								</td>
							</tr>
							</c:forEach>
							<tr>
								<td><form:input path="especie" cssClass="form-control" placeholder="Espécie"/></td>
								<td><form:input path="producao" cssClass="form-control" placeholder="0" style="text-align: center;width:100px;"/></td>
								<td><form:input path="jan" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="fev" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="mar" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="abr" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="mai" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="jun" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="jul" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="ago" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="set" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="out" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="nov" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="dez" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="entrada" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
								<td><form:input path="saida" cssClass="form-control" placeholder="0" style="text-align: center;width:80px;"/></td>
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
