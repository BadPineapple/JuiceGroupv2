<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Importar Funcion�rio - <ilion:nomeEmpresa/></title>
         <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
    </head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">
    <div class="container-fluid">
        <h1 class="section-title">
			<span>Importar Funcion�rio</span>
		</h1><!-- End Title -->
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Vitazure</li>
		  <li class="active">Importar Funcion�rio </li>
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
							  <button class="btn btn-primary" style="min-width: 10px;" title="Importar"><i class="fas fa-file-import" style="padding-right: 10px;"></i>Importar</button>
							</div>  
						</div>	
					   </form>
					</div>
			    <c:if test="${not empty mensagem }">
					<fieldset style="width:100%;padding: 15px;">
						<legend>Resultado da importa��o</legend>
						<c:out value="${mensagem}" escapeXml="{true}"/>
					</fieldset>
					</c:if>
	                     <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="border: solid 1px;padding: 15px;margin: 18px;">
	                        <label class="control-label">Instru��es para importar uma lista de funcion�rios:</label>
							<br/>
							1 - A lista deve ser formatada em arquivo Excel. com o nome do arquivo funcionarios.xls ou funcionarios.xlsx;<br/>
							2 - Cada linha deve conter somente o NOME, CPF, OPERA��O e CLASSIFICA��O;<br/>
							3 - Os dados devem ser separados em colunas, N�O coloque t�tulo nas colunas, SOMENTE os dados dos funcion�rios;<br/>
							4 - O Campo OPERA��O deve receber os seguintes valores:<br/>
							&nbsp;&nbsp;1 (um) - Quando um novo funcion�rio ser� inclu�do;<br/>
							&nbsp;&nbsp;2 (dois) - Quando um funcion�rio que j� est� inclu�do na base de dados dever� ser desligado do conv�nio;<br/>
							&nbsp;&nbsp;3 (tr�s) - Quando um Funcion�rio que j� exista na base de dados dever� ser inclu�do no conv�nio.<br/>
							5 - O Campo CLASSIFICA��O deve receber os seguintes valores:<br/>
							&nbsp;&nbsp;1 (um) - Cliente titular;<br/>
							&nbsp;&nbsp;2 (dois) - Cliente dependente;<br/>
							6 - Os dados devem ser separados em colunas, N�O coloque t�tulo nas colunas, SOMENTE os dados dos funcion�rios;<br/>
							<br/>
	                   </div>
			    </div>
		    </div>
		</div>
    </div>
</div><!--end content area-->

<jsp:include page="../../../ilionnet2/inc/include-footer.jsp" flush="true"/>
<jsp:include page="../../../ilionnet2/inc/include-js-footer.jsp" flush="true"/>

<c:if test="${not empty mensagem}">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="Dados Importado com sucesso."
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

<c:if test="${param.m != 'ok' && param.m != null}">
	<button 
		type="button" 
		data-positionX="right" 
		data-positionY="top" 
		data-effect="fadeInUp" 
		data-message="${param.m}"
		data-type="error" 
		class="btn pmd-ripple-effect btn-error pmd-z-depth pmd-alert-toggle"
		id="alertMsgError"
		style="display:none;">
		Erro
	</button>
	<script type="text/javascript">
		(function() {
			setTimeout(function() {
				$('#alertMsgError').click();
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
