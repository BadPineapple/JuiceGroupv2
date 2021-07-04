<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Relatórios / Resumo em Gráficos - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../../inc/include-head.jsp" flush="true"/>
		
		<style>
			.table-lotacao-media thead tr th {font-size:12px;}
			.table-lotacao-media tbody tr td {font-size:12px;}
		</style>
		
    </head>

<body>

<jsp:include page="../../inc/include-header.jsp" flush="true"/>

<jsp:include page="../../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title" id="services">
			<span>Resumo em Gráficos</span>
		</h1><!-- End Title -->
			
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li>Home</li>
		  <li>Relatórios</li>
		  <li class="active">Resumo em Gráficos</li>
		</ol><!--breadcrum end--> 
		
		<div class="pmd-card pmd-z-depth">
			<div class="pmd-card-body">
			
			<div class="row">
				<div class="col-sm-6">
					<img src="<ilion:url/>static/graficos/distribuicao-das-forragens.jpg?<%= System.currentTimeMillis() %>" />
				</div>
				
				<div class="col-sm-6">
					<img src="<ilion:url/>static/graficos/escore-das-invasoras.jpg?<%= System.currentTimeMillis() %>" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">
					<img src="<ilion:url/>static/graficos/aguadas-por-tipo.jpg?<%= System.currentTimeMillis() %>" />
				</div>
				
				<div class="col-sm-6">
					<img src="<ilion:url/>static/graficos/area.jpg?<%= System.currentTimeMillis() %>" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-8">
				
					<h1>Lotação Média em UA</h1>
					
					<div class="table-responsive">
		                <table class="table table-bordered table-lotacao-media">
		                	<thead>
					        <tr>
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
							</thead>
							<tbody>
							<tr>
								
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalJan}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalFev}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalMar}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalAbr}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalMai}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalJun}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalJul}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalAgo}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalSet}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalOut}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalNov}"/></td>
								<td class="text-center lotacao-media"><ilion:formatNumber value="${avaliacaoDosPastos.lotacaoMediaTotalDez}"/></td>
								
							</tr>
							</tbody>
						</table>
		            </div>
				
				
				</div>
				
				<div class="col-sm-4">
					<img src="<ilion:url/>static/graficos/qualidade-das-aguadas.jpg?<%= System.currentTimeMillis() %>" />
				</div>
			</div>
			
			
			</div><!--end component header-->
		</div>


    </div>


</div><!--end content area-->



<jsp:include page="../../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../../inc/include-js-footer.jsp" flush="true"/>


<script type="text/javascript">

	(function() {
		$('#relatorios').click();
	})();
	


</script>

</body>

</html>
