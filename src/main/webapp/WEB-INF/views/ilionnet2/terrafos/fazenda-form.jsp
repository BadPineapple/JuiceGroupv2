<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Fazendas - <ilion:nomeEmpresa/></title>
        
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
			<span>Fazendas</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Terrafós</li>
		  <li><a href="<ilion:url/>ilionnet/terrafos/fazendas">Fazendas</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					
					<form:form commandName="fazenda" method="post" action="?" cssClass="form-horizontal" enctype="multipart/form-data">
					
						<form:input path="googleMapsJson" id="googleMapsJson" cssStyle="display:none;" />
						
						<c:if test="${not empty hasErrors}">
						<div role="alert" class="alert alert-danger alert-dismissible">
							<button aria-label="Close" data-dismiss="alert" class="close" type="button">
								<span aria-hidden="true">×</span>
							</button>
							<form:errors path="*"/>
						</div>
						</c:if>
						
						<%-- <div class="row">
							
							<div class="col-sm-3">
		                        <label for="">Escolha o estado:</label>
	                        	<select name="__uf" id="uf" class="form-control">
	                          	  <option value="">Selecione o estado:</option>
		                          <c:forEach items="${estados}" var="estado">
		                           <option value="${estado}"><c:out value="${estado.nome}"/></option>
		                          </c:forEach>
	                     		</select>
		                    </div>
		                    
		                    <div class="col-sm-3">
		                        <label for="">Cidade:</label>
								 <select name="cidade" id="cidade" class="form-control">
			                         <option value="">Selecione a cidade:</option>
			                     </select>
		                    </div>
		
		                    <div class="col-sm-3">
		                        <button class="btn btn-info" onclick="novoLocal()" type="button" style="margin-top: 27px;">Carregar no mapa</button>
		                    </div>
		                
		                </div> --%>
		                <div class="row">
							
							<div class="col-sm-12">
                                <div class="img-responsive" id="map_canvas" style="width:100%; height:500px;margin: 20px 0;">
                                </div>
                            </div>
                        
                        </div>
						
						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="nome">Nome da Fazenda</label>
									<form:input path="nome" id="nome" cssClass="form-control input-sm" />
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group">
									<label for="areaTotal">Área Total (ha)</label>
									<form:input path="areaTotal" id="areaTotal" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>

						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="proprietario">Proprietário</label>
									<form:input path="proprietario" id="proprietario" cssClass="form-control input-sm" />
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group">
									<label for="telefone">Telefone</label>
									<form:input path="telefone" id="telefone" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>

						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="ufForm">UF</label>
									<form:input path="uf" id="ufForm" cssClass="form-control input-sm" />
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group">
									<label for="municipio">Município</label>
									<form:input path="municipio" id="municipio" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>
						
						<h3>Distribuição da Produção da MS</h3>
						
						<div class="row">
						
							<div class="col-sm-6">
								<div class="form-group">
									<label for="safra">Safra (Águas)</label>
									<form:input path="safra" id="safra" cssClass="form-control input-sm" />
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group">
									<label for="entreSafra">Entresafra</label>
									<form:input path="entreSafra" id="entreSafra" cssClass="form-control input-sm" />
								</div>
							</div>
						
						</div>
						
						<h3>Pluviometria</h3>
						
						<table class="table">
					      <thead>
					        <tr>
					          <th>Jan</th>
					          <th>Fev</th>
					          <th>Mar</th>
					          <th>Abr</th>
					          <th>Mai</th>
					          <th>Jun</th>
					          <th>Jul</th>
					          <th>Ago</th>
					          <th>Set</th>
					          <th>Out</th>
					          <th>Nov</th>
					          <th>Dez</th>
					          <th>Acumul.</th>
					        </tr>
					      </thead>
					      <tbody>
					        <tr>
					          <td><form:input path="pluviometriaJan" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaFev" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaMar" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaAbr" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaMai" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaJun" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaJul" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaAgo" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaSet" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaOut" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaNov" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>
					          <td><form:input path="pluviometriaDez" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>					          
					          <td><form:input path="pluviometriaAcumulado" cssClass="form-control input-sm" cssStyle="width:60px;text-align:center;" /></td>					          
					        </tr>
					      </tbody>
					    </table>
						
						
						<button type="submit" class="btn btn-primary pmd-checkbox-ripple-effect">Salvar</button>
						<a href="<ilion:url/>ilionnet/terrafos/fazendas" class="btn btn-default">Cancelar</a>
						
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

FAZENDA_ID='${fazenda.id}';

</script>

<script type="text/javascript" src="<ilion:url/>assets/js/terrafos/fazenda-form-marcar-centro-zoom.js"></script>
<script type="text/javascript" src="<ilion:url/>assets/js/terrafos/listar-cidades-por-estado.js" charset="utf-8"></script>

<script type="text/javascript">

	(function() {
		$('#terrafos').click();
	})();

</script>

</body>

</html>
