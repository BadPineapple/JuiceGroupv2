<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Serviço - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Produtos</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Home Beauty</li>
		  <li><a href="<ilion:url/>ilionnet/homebeauty/servicos">Serviços</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
					<form:form commandName="servico" method="post" action="?" cssClass="form-horizontal" enctype="multipart/form-data">
						
						<c:if test="${not empty hasErrors}">
						<div role="alert" class="alert alert-danger alert-dismissible">
							<button aria-label="Close" data-dismiss="alert" class="close" type="button">
								<span aria-hidden="true">×</span>
							</button>
							<form:errors path="*"/>
						</div>
						</c:if>

						<div class="form-group pmd-textfield">
							<label for="categoria" class="col-sm-2 control-label">Categoria</label>
							<div class="col-sm-3">

								<div class="fg-line">
									<form:select path="categoria.id" id="categoria" cssClass="form-control input-sm">
									  	<form:option value=""></form:option>
									  	<form:options items="${categorias}" itemValue="id" itemLabel="nome" />
									</form:select>
								</div>
							</div>
						</div>

						<div class="form-group pmd-textfield">
							<label for="nome" class="col-sm-2 control-label">Nome</label>
							<div class="col-sm-10">

								<div class="fg-line">
									<form:input path="nome" id="nome" cssClass="form-control input-sm" maxlength="255" />
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="descricao" class="col-sm-2 control-label">Descri&ccedil;&atilde;o</label>
							<div class="col-sm-10">

								<div class="fg-line">
									<form:textarea path="descricao" id="descricao" cssClass="form-control input-sm" cssStyle="width:620px;height:80px;" />
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="preco" class="col-sm-2 control-label">Pre&ccedil;o</label>
							<div class="col-sm-2">

								<div class="fg-line">
									<form:input path="preco" id="preco" cssClass="form-control input-sm" cssStyle="text-align:right;" />
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="duracao" class="col-sm-2 control-label">Dura&ccedil;&atilde;o</label>
							<div class="col-sm-2">

								<div class="fg-line">
									<form:input path="duracao" id="duracao" cssClass="form-control input-sm" maxlength="50" />
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="palavrasChave" class="col-sm-2 control-label">Palavras-chave</label>
							<div class="col-sm-10">

								<div class="fg-line">
									<form:textarea path="palavrasChave" id="palavrasChave" cssClass="form-control input-sm" cssStyle="width:620px;height:80px;" />
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="" class="col-sm-2 control-label">Imagens</label>
							<div class="col-sm-2">

								<div class="fg-line">
									<a href="javascript:;">
								   		<img 
								   			title="Arquivos" 
								   			src="<ilion:url/>ilionnet/design/imagens/icon_popup.gif" 
								   			border="0" 
								   			onclick="window.open('<ilion:url/>ilionnet/imagem-form.sp?nomeClasse=Servico&idObjeto=${servico.id}&codigo=${servico.codArquivos}','arquivo','scrollbars=yes,resizable=yes,width=900,height=600,screenX=1,screenY=1,left=150,top=60');"
								   		/>
								   	</a>
								</div>
							</div>
						</div>
						
						<div class="form-group pmd-textfield">
							<label for="status" class="col-sm-2 control-label">Status</label>
							<div class="col-sm-2">

								<div class="fg-line">
									<form:select path="status" id="status" cssClass="form-control input-sm" >
										<form:option value="PUBLICADO" label="Publicado"/>
										<form:option value="NAO_PUBLICADO" label="Não Publicado"/>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary pmd-checkbox-ripple-effect">Salvar</button>
								<a href="<ilion:url/>ilionnet/homebeauty/servicos" class="btn btn-default">Cancelar</a>
							</div>
						</div>
						
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
		$('#homebeauty').click();
	})();

</script>

</body>

</html>
