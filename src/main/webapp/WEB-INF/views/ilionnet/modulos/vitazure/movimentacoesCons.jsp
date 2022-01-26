<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Movimentações - <ilion:nomeEmpresa/></title>
         <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
    </head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">
    <div class="container-fluid">
        <h1 class="section-title">
			<span>Movimentações</span>
		</h1><!-- End Title -->
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Vitazure</li>
		  <li class="active">Movimentações </li>
		</ol><!--breadcrum end--> 
		<div class="col-md-12">
		    <div class="component-box">
		    	<div class="pmd-card pmd-z-depth">
					<div class="pmd-card-body">	
						<form class="form-inline" method="get" action="?">
						<div class="row">
					      <input name="sp" type="hidden" value="true"/>
					      <input name="pagingPage" type="hidden" value="1"/>
						      <div class="col-md-10 col-lg-10 col-sm-12">
						          <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed" style="width: 100% !important;">
						           	<label for="name" class="control-label">Palavra-Chave</label>
									<input type="Large" name="palavraChave" id="palavraChave" class="form-control input-group-lg" style="width: 100% !important;"/>
								</div> 
							</div>
							<div class="col-md-2 col-lg-2 col-sm-12 text-center" style="padding-top: 35px;">	
							  <button class="btn btn-primary">Buscar</button>
							</div>  
						</div>	
						</form>
					</div>
			    </div>
		        <!-- Basic Bootstrap Table example -->
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
		            <div class="table-responsive">
		                <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <td style="text-align: center;">id</td>
                                        <td style="text-align: center;">Agenda</td>
                                        <td style="text-align: center;">Data Transação</td>
                                        <td style="text-align: center;">Data Consulta</td>
                                        <td style="text-align: center;">Tipo Transação</td>
                                        <td style="text-align: center;">Profissional</td>
                                        <td style="text-align: center;">Valor Pago</td>
                                        <td style="text-align: center;">Status</td>
                                    </tr>
                                </thead>
    
                                <tbody>
                                <c:forEach var="pagamento" items="${listPagamentos}">
                                    <tr>
                                        <td style="text-align: center;">${pagamento.id}</td>
                                        <td style="text-align: center;">${pagamento.agenda > 0 ? pagamento.agenda : '-'}</td>
                                        <td style="text-align: center;">${pagamento.dataFormatada}</td>
                                        <td style="text-align: center;">${pagamento.dataAgenda}</td>
                                        <td style="text-align: center;">${pagamento.agenda > 0 ? 'Consulta Agendada' : 'Plano'}</td>
                                        <td style="text-align: left;">${pagamento.profissional}</td>
                                        <td style="text-align: center;">${pagamento.valorPago}</td>
                                        <td style="text-align: center;">${pagamento.status}</div>
                                        </td>
                                    </tr>
    							</c:forEach>
                                </tbody>
                            </table>
		            </div>
		        </div>
	             <div class="row" >
		            <div class="col-md-10" >
		             	<ilion:vlhPagination valueListInfo="${listPagamentos.valueListInfo}" navCssClass="pull-right"/>
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
