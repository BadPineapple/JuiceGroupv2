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
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Terrafós</li>
		  <li><a href="<ilion:url/>ilionnet/terrafos/fazendas">Retiros</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
					<form:form commandName="retiro" method="post" action="?" cssClass="form-horizontal" enctype="multipart/form-data">
						
						<c:if test="${not empty hasErrors}">
						<div role="alert" class="alert alert-danger alert-dismissible">
							<button aria-label="Close" data-dismiss="alert" class="close" type="button">
								<span aria-hidden="true">×</span>
							</button>
							<form:errors path="*"/>
						</div>
						</c:if>
						
						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="nome">Nome do Retiro</label>
									<form:input path="nome" id="nome" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>

						
						
						
						<button type="submit" class="btn btn-primary pmd-checkbox-ripple-effect">Salvar</button>
						<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/retiros" class="btn btn-default">Cancelar</a>
						
					</form:form>
				</div>
			</div>

		
		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<c:if test="${param.m == 'ok'}">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="Dados gravados com sucesso."
		data-type="success" 
		class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
		id="alertSucess"
		style="display:none;">
		Sucesso
	</button>
	<script type="text/javascript">
		(function() {
			setTimeout(function() {
				$('#alertSucess').click();
			}, 300);
		})();
	</script>
</c:if>

<script type="text/javascript">

	(function() {
		$('#cadastros').click();
	})();

</script>

</body>

</html>
