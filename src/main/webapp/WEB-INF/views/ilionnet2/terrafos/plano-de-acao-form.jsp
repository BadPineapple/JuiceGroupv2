<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Planos de Ação - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Planos de Ação</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Terrafós</li>
		  <li><a href="<ilion:url/>ilionnet/terrafos/fazendas">Planos de Ação</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
					<form:form commandName="planoDeAcao" method="post" action="?" cssClass="form-horizontal" enctype="multipart/form-data">
						
						<c:if test="${not empty hasErrors}">
						<div role="alert" class="alert alert-danger alert-dismissible">
							<button aria-label="Close" data-dismiss="alert" class="close" type="button">
								<span aria-hidden="true">×</span>
							</button>
							<form:errors path="*"/>
						</div>
						</c:if>
						
						<div class="row">
						
							<div class="col-sm-2">
								<div class="form-group">
									<label for="dataVisita">Data da Visita</label>
									<form:input path="dataVisita" id="dataVisita" cssClass="form-control input-sm" maxlength="10" cssStyle="text-align:center;" />
								</div>
							</div>
						
						</div>
						<div class="row">

							<div class="col-sm-12">
								<div class="form-group">
									<label for="oQue">O quê?</label>
									<form:textarea path="oQue" id="oQue" cssClass="form-control input-sm" />
								</div>
							</div>
							
						</div>
						<div class="row">

							<div class="col-sm-12">
								<div class="form-group">
									<label for="como">Como?</label>
									<form:textarea path="como" id="como" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>
						<div class="row">

							<div class="col-sm-12">
								<div class="form-group">
									<label for="porQue">Porquê?</label>
									<form:textarea path="porQue" id="porQue" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>
						<div class="row">

							<div class="col-sm-12">
								<div class="form-group">
									<label for="quem">Quem?</label>
									<form:textarea path="quem" id="quem" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>
						<div class="row">

							<div class="col-sm-12">
								<div class="form-group">
									<label for="quando">Quando?</label>
									<form:textarea path="quando" id="quando" cssClass="form-control input-sm" />
								</div>
							</div>
							
						</div>
						<div class="row">
							
							<div class="col-sm-4">
								<div class="form-group">
									<label for="custo">Status</label>
									<form:select path="status" cssClass="form-control input-sm">
										<form:option value="EM_ANDAMENTO">Em Andamento</form:option>
										<form:option value="AGUARDANDO">Aguardando</form:option>
										<form:option value="CONCLUIDO">Concluído</form:option>
									</form:select>
								</div>
							</div>
						
						</div>
						<div class="row">
							
							<div class="col-sm-2">
								<div class="form-group">
									<label for="custo">Custo R$</label>
									<form:input path="custo" id="custo" cssClass="form-control input-sm" cssStyle="text-align:right;" />
								</div>
							</div>
						
						</div>

						
						
						
						<button type="submit" class="btn btn-primary pmd-checkbox-ripple-effect">Salvar</button>
						<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/planos-de-acao" class="btn btn-default">Cancelar</a>
						
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
