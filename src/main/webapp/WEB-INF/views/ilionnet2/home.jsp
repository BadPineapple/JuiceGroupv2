<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Home - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="inc/include-head.jsp" flush="true"/>
		
		
        
        <script src="http://maps.google.com/maps/api/js?libraries=drawing,places,geometry&key=${googleMapsKey}" type="text/javascript"></script>
		
    </head>

<body>

<jsp:include page="inc/include-header.jsp" flush="true"/>

<jsp:include page="inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title" id="services">
			<span>Home</span>
		</h1><!-- End Title -->
			
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li class="active">Home</li>
		</ol><!--breadcrum end--> 
		
		<div class="componant-title-bg"> 
			
			<c:choose>
				<c:when test="${not empty fazendaSessao}">

					<h1>${fazendaSessao.nome}</h1>
					
					<div class="row">
					
						<div class="col-sm-12">
						
							<div id="mapa_pasto" style="width:100%; height:500px;margin: 20px 0;"></div>
						
						</div>
					
					</div>
					
				</c:when>
				<c:otherwise>
					
					<div class="alert alert-danger" role="alert">
						<em>Nenhuma fazenda está vinculada ao seu usuário.</em>
						<p>Por favor, contate o administrador.</p>					
					</div>
				
				</c:otherwise>
			</c:choose>
			
		</div><!--end component header-->


    </div>


</div><!--end content area-->



<jsp:include page="inc/include-footer.jsp" flush="true"/>

<jsp:include page="inc/include-js-footer.jsp" flush="true"/>

<script>

FAZENDA_ID='${fazendaSessao.id}';

</script>

<script src="<ilion:url/>assets/js/terrafos/pastos-no-mapa.js?v1"></script>

</body>

</html>
