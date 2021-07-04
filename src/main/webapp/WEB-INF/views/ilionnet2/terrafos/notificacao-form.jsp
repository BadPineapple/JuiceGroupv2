<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Notificação - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Notificação</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Home Beauty</li>
		  <li><a href="<ilion:url/>ilionnet/homebeauty/notificacoes/">Notificação</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
					<form:form commandName="notificacao" method="post" action="?" cssClass="form-horizontal" enctype="multipart/form-data">
						
						<c:if test="${not empty hasErrors}">
						<div role="alert" class="alert alert-danger alert-dismissible">
							<button aria-label="Close" data-dismiss="alert" class="close" type="button">
								<span aria-hidden="true">x</span>
							</button>
							<form:errors path="*"/>
						</div>
						</c:if>
						
						<div class="form-group pmd-textfield">
							<label for="nomeEntidade" class="col-sm-2 control-label">Entidade</label>
							<div class="col-sm-3">

								<div class="fg-line">
									<form:select path="nomeEntidade" id="nomeEntidade" cssClass="form-control input-sm">
									  	<form:option value=""></form:option>
									  	<form:option value="Cliente">Cliente</form:option>
									  	<form:option value="Profissional">Profissional</form:option>									  	
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="idEntidade" class="col-sm-2 control-label">ID Entidade</label>
							<div class="col-sm-2">

								<div class="fg-line">
									<form:input path="idEntidade" id="idEntidade" cssClass="form-control input-sm" />
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="titulo" class="col-sm-2 control-label">Título</label>
							<div class="col-sm-10">

								<div class="fg-line">
									<form:input path="titulo" id="titulo" cssClass="form-control input-sm" />
								</div>
							</div>
						</div>

						<div class="form-group pmd-textfield">
							<label for="conteudo" class="col-sm-2 control-label">Conteúdo</label>
							<div class="col-sm-10">

								<div class="fg-line">
									<form:input path="conteudo" id="conteudo" cssClass="form-control input-sm" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary pmd-checkbox-ripple-effect">Salvar</button>
								<a href="<ilion:url/>ilionnet/homebeauty/notificacoes/" class="btn btn-default">Cancelar</a>
							</div>
						</div>
						
					</form:form>
				</div>
			</div>

		
		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script type="text/javascript">

	(function() {
		$('#homebeauty').click();
	})();

</script>

</body>

</html>
