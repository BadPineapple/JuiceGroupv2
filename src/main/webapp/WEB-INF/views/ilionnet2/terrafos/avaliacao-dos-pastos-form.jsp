<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Avaliação dos Pastos - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
		<style>
			.lotacao-media {background-color:#f2d8d8;color:#333;}
			.lotacao-media-por-aeeha {background-color:#f0a1a3;color:#fff;}
		</style>
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Avaliação dos Pastos</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Terrafós</li>
		  <li class="active">Avaliação dos Pastos</li>
		</ol><!--breadcrum end--> 
		
		
		<form:form commandName="avaliacaoDosPastos" method="post" action="?" cssClass="form-horizontal">
		
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
							    <th class="text-center" colspan="16"> &nbsp; </th>
							    <th class="text-center" colspan="12">UA/total</th>
							    <th class="text-center" colspan="12">UA/ha</th>
							</tr>
					        <tr>
							    <th class="text-center">Fazenda</th>
							    <th class="text-center">Retiro</th>
							    <th class="text-center">Nome</th>
							    <th class="text-center">Área</th>
							    <th class="text-center">Forrageira</th>
							    <th class="text-center">AEE (%)</th>
							    <th class="text-center">AEE (ha)</th>
							    <th class="text-center">OF (%)</th>
							    <th class="text-center">Escore Invasoras</th>
							    <th class="text-center">Área cocho -cm</th>
							    <th class="text-center">Cobertura de cocho</th>
							    <th class="text-center">Acesso cocho</th>
							    <th class="text-center">Sub-estoque</th>
							    <th class="text-center">Aguadas</th>
							    <th class="text-center">Qualidade Aguadas</th>
							    <th class="text-center">Cercas</th>
							    <th class="text-center">Jan</th>
							    <th class="text-center">Fev</th>
							    <th class="text-center">Mar</th>
							    <th class="text-center">Abr</th>
							    <th class="text-center">Mai</th>
							    <th class="text-center">Jun</th>
							    <th class="text-center">Jul</th>
							    <th class="text-center">Ago</th>
							    <th class="text-center">Set</th>
							    <th class="text-center">Out</th>
							    <th class="text-center">Nov</th>
							    <th class="text-center">Dez</th>
							    <th class="text-center">Jan</th>
							    <th class="text-center">Fev</th>
							    <th class="text-center">Mar</th>
							    <th class="text-center">Abr</th>
							    <th class="text-center">Mai</th>
							    <th class="text-center">Jun</th>
							    <th class="text-center">Jul</th>
							    <th class="text-center">Ago</th>
							    <th class="text-center">Set</th>
							    <th class="text-center">Out</th>
							    <th class="text-center">Nov</th>
							    <th class="text-center">Dez</th>
							</tr>
							<c:forEach var="pasto" items="${avaliacaoDosPastos.pastos}" varStatus="varStatus">
							<tr>
								<td class="text-center">${pasto.retiro.fazenda.nome}</td>
								<td class="text-center">${pasto.retiro.nome}</td>
								<td class="text-center">${pasto.nome}</td>
								<td class="text-center"><ilion:formatNumber value="${pasto.areaTotal}"/></td>
								<td class="text-center">${pasto.forrageira.especie}</td>
								<td class="text-center">
									<form:input path="pastos[${varStatus.index}].avaliacaoDoPasto.aee" cssClass="form-control" cssStyle="width:60px;text-align:center;" />
								</td>
								<td class="text-center"><ilion:formatNumber value="${pasto.aeeHa}"/></td>
								<td class="text-center">
									<form:input path="pastos[${varStatus.index}].avaliacaoDoPasto.of" cssClass="form-control" cssStyle="width:60px;text-align:center;" />
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.escoreInvasoras" cssClass="form-control">
										<form:option label="0 - Pasto limpo" value="0"/>
										<form:option label="1 - Pasto até 10% de infestação de pragas" value="1"/>
										<form:option label="2 - Pasto entre 10 a 30% de infestação de pragas" value="2"/>
										<form:option label="3 - Pasto entre 30 a 60% de infestação de pragas" value="3"/>
										<form:option label="4 - Pasto entre 60 a 80% de infestação de pragas" value="4"/>
										<form:option label="5 - Pasto acima de 80% de infestação de pragas" value="5"/>
									</form:select>
								</td>
								<td class="text-center">
									<form:input path="pastos[${varStatus.index}].avaliacaoDoPasto.areaCocho" cssClass="form-control" cssStyle="width:60px;text-align:center;" />
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.coberturaDeCocho" cssClass="form-control" cssStyle="width:80px;">
										<form:option label="" value=""/>
										<form:option label="Sim" value="Sim"/>
										<form:option label="Não" value="Não"/>
									</form:select>
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.acessoCocho" cssClass="form-control" cssStyle="width:80px;">
										<form:option label="" value=""/>
										<form:option label="Bom" value="Bom"/>
										<form:option label="Ruim" value="Ruim"/>
									</form:select>
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.subEstoque" cssClass="form-control" cssStyle="width:80px;">
										<form:option label="" value=""/>
										<form:option label="Sim" value="Sim"/>
										<form:option label="Não" value="Não"/>
									</form:select>
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.aguadas" cssClass="form-control" cssStyle="width:100px;">
										<form:option label="" value=""/>
										<form:option label="Natural" value="Natural"/>
										<form:option label="Artificial" value="Artificial"/>
									</form:select>
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.qualidadeAguadas" cssClass="form-control" cssStyle="width:80px;">
										<form:option label="" value=""/>
										<form:option label="Boa" value="Boa"/>
										<form:option label="Ruim" value="Ruim"/>
									</form:select>
								</td>
								<td class="text-center">
									<form:select path="pastos[${varStatus.index}].avaliacaoDoPasto.cercas" cssClass="form-control" cssStyle="width:80px;">
										<form:option label="" value=""/>
										<form:option label="Boa" value="Boa"/>
										<form:option label="Retoque" value="Retoque"/>
									</form:select>
								</td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaJan}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaFev}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaMar}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaAbr}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaMai}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaJun}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaJul}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaAgo}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaSet}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaOut}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaNov}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${pasto.lotacaoMediaDez}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaJan}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaFev}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaMar}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaAbr}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaMai}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaJun}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaJul}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaAgo}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaSet}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaOut}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaNov}"/></td>
								<td class="text-center lotacao-media-por-aeeha"><ilion:formatNumber value="${pasto.lotacaoMediaPorAeeHaDez}"/></td>
								
							</tr>
							</c:forEach>
						</table>
		            </div>
		
		        </div>
		    </div>
			
			<button class="btn btn-primary" role="button">Salvar</button>
			 
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
