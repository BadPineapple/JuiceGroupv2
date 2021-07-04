<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Monitoramento / Altura das Forragens - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../../inc/include-head.jsp" flush="true"/>
		
		<ilion:googleMapsKey varRetorno="googleMapsKey"/>
        
        <script src="http://maps.google.com/maps/api/js?libraries=drawing,places,geometry&key=${googleMapsKey}" type="text/javascript"></script>
		
    </head>

<body>

<jsp:include page="../../inc/include-header.jsp" flush="true"/>

<jsp:include page="../../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title" id="services">
			<span>Altura das Forragens</span>
		</h1><!-- End Title -->
			
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li>Home</li>
		  <li>Monitoramento</li>
		  <li class="active">Altura das Forragens</li>
		</ol><!--breadcrum end--> 
		
		<div class="componant-title-bg"> 
			
			<c:choose>
				<c:when test="${not empty fazendaSessao}">

					<h1>${fazendaSessao.nome}</h1>
					
					<div class="row" id="conteudo">
					
						<div class="col-sm-12">
						
							<div id="mapa_pasto" style="width:100%; height:500px;margin: 20px 0;"></div>
						
						</div>
						
					</div>
					
					
				</c:when>
				<c:otherwise>
					
					<div class="alert alert-danger" role="alert">
						<em>Nenhuma fazenda est� vinculada ao seu usu�rio.</em>
						<p>Por favor, contate o administrador.</p>					
					</div>
				
				</c:otherwise>
			</c:choose>
			
		</div><!--end component header-->


    </div>


</div><!--end content area-->



<jsp:include page="../../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../../inc/include-js-footer.jsp" flush="true"/>

<script>

FAZENDA_ID='${fazendaSessao.id}';

</script>

<script src="<ilion:url/>assets/js/terrafos/monitoramento/altura-das-forragens.js"></script>

<script type="text/javascript">

	(function() {
		$('#monitoramento').click();
	})();

</script>

</body>

</html>
