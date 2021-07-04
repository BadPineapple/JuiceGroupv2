<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Pastos - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
        
        
        
        <script src="http://maps.google.com/maps/api/js?libraries=drawing,places,geometry&key=${googleMapsKey}" type="text/javascript"></script>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Pastos</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Terrafós</li>
		  <li><a href="<ilion:url/>ilionnet/terrafos/fazendas">Pastos</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
					<form:form commandName="pasto" method="post" action="?" cssClass="form-horizontal" enctype="multipart/form-data">
						<form:input path="googleMapsJson" id="googleMapsJson" cssStyle="display:none;" />
						
						<c:if test="${not empty hasErrors}">
						<div role="alert" class="alert alert-danger alert-dismissible">
							<button aria-label="Close" data-dismiss="alert" class="close" type="button">
								<span aria-hidden="true">×</span>
							</button>
							<form:errors path="*"/>
						</div>
						</c:if>
						
						
		                <div class="row">
							
							<div class="col-sm-12">
                                <div class="img-responsive" id="map_canvas" style="width:100%; height:500px;margin: 20px 0;">
                                </div>
                            </div>
                        
                        </div>
		                <div class="row">
                            
                            <div class="col-sm-12">
                            	<button class="btn btn-info" id="botaoLimparPontos" type="button">Limpar &aacute;reas marcadas &nbsp; <i class="fa fa-trash-o"></i></button>
                            </div>
						
						</div>
						
						
						<br/>
						
						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="nome">Nome do Pasto</label>
									<form:input path="nome" id="nome" cssClass="form-control input-sm" />
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="areaTotal">Área Total (ha)</label>
									<form:input path="areaTotal" id="areaTotal" cssClass="form-control input-sm" readonly="true"/>
								</div>
							</div>
						
						</div>

						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="retiro">Retiro</label>
									<form:select path="retiro.id" id="retiro" cssClass="form-control input-sm">
									  	<form:option value=""></form:option>
									  	<form:options items="${retiros}" itemValue="id" itemLabel="nome" />
									</form:select>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="form-group">
									<label for="forrageira">Forragem predominante</label>
									<form:select path="forrageira.id" id="forrageira" cssClass="form-control input-sm">
									  	<form:option value=""></form:option>
									  	<form:options items="${forrageiras}" itemValue="id" itemLabel="especie" />
									</form:select>
								</div>
							</div>
						
						</div>

						
						
						
						<button type="submit" class="btn btn-primary pmd-checkbox-ripple-effect">Salvar</button>
						<a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/pastos" class="btn btn-default">Cancelar</a>
						
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

<script>

FAZENDA_ID='${fazendaSessao.id}';
PASTO_ID='${pasto.id}';

</script>

<script type="text/javascript" src="<ilion:url/>assets/js/terrafos/pasto-form-selecionar-area.js?v1"></script>
<script type="text/javascript" src="<ilion:url/>assets/js/terrafos/listar-cidades-por-estado.js" charset="utf-8"></script>

<script type="text/javascript">
	
	(function() {
		$('#cadastros').click();
		
	})();

</script>

</body>

</html>
