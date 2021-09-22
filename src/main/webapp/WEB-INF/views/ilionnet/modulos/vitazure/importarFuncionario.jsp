<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Importar Funcionário - <ilion:nomeEmpresa/></title>
         <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
    </head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">
    <div class="container-fluid">
        <h1 class="section-title">
			<span>Importar Funcionário</span>
		</h1><!-- End Title -->
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Vitazure</li>
		  <li class="active">Importar Funcionário </li>
		</ol><!--breadcrum end--> 
		<div class="col-md-12">
		    <div class="component-box">
		    	<div class="pmd-card pmd-z-depth">
					<div class="pmd-card-body">	
					  <form class="form-inline" method="post" name="upfotoForm" enctype="multipart/form-data" action="funcionario-importar-executar.sp" style="margin:0;">
						<div class="row">
						      <div class="col-md-10 col-lg-10 col-sm-12">
						          <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100% !important;padding-top: 20px;">
						           	<td class="linkCinza"><input type="file" name="arquivo" class="forms2" accept="*" size="30" /></td>
								</div> 
							</div>
							<div class="col-md-2 col-lg-2 col-sm-12 text-center" style="padding-top: 12px;">	
							  <button class="btn btn-primary">Importar</button>
							</div>  
						</div>	
					   </form>
					</div>
			    <c:if test="${not empty mensagem }">
					<fieldset style="width:100%;padding: 15px;">
						<legend>Resultado da importação</legend>
						<c:out value="${mensagem}" escapeXml="{true}"/>
					</fieldset>
					</c:if>
	                     <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="border: solid 1px;padding: 15px;margin: 18px;">
	                        <label class="control-label">Instruções para importar uma lista de contatos:</label>
							<br/>
							1 - A lista deve ser formatada em arquivo txt. Ex.: contatos.txt;<br/>
							2 - A cada linha deve conter os dados de um contato com seu Nome, e-mail e telefone.<br/>
							3 - Os dados devem ser separador por ; (ponto e vírgula).<br/>
							4 - Após importar será enviado um email de confirmação para o funcionário informado no arquivo.<br/>
							5 - A senha inicial de acesso dos funcionários importados: "Vitazure1".<br/>
							<br/>
							Ex:<br/>
							Nome;e-mail;telefone;<br/>
							Nome;e-mail;telefone;<br/>
							Nome;e-mail;telefone;<br/>
							<br/>
	                   </div>
			    </div>
		    </div>
		</div>
    </div>
</div><!--end content area-->

<jsp:include page="../../../ilionnet2/inc/include-footer.jsp" flush="true"/>
<jsp:include page="../../../ilionnet2/inc/include-js-footer.jsp" flush="true"/>

<c:if test="${param.m == 'ok'}">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="Dados Excluido com sucesso."
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


<script>
	(function() {
		$('#palavraChave').val('${param.palavraChave}');
	})();
</script>
<script type="text/javascript">
	(function() {
		$('#vitazure').click();
	})();

</script>

</body>

</html>
