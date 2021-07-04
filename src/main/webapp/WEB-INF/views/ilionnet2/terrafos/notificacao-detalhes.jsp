<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Notificações - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Notificações</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
		  <li>Carreira</li>
		  <li><a href="<ilion:url/>ilionnet/homebeauty/notificacoes/">Notificações</a></li>
		  <li class="active">${notificacao.titulo}</li>
		</ol><!--breadcrum end--> 
		
		
		<div class="col-md-12">
		    <div class="component-box">
			
				<a href="<ilion:url/>ilionnet/homebeauty/notificacoes" class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i> Voltar</a>
				<a href="javascript:;" onclick="enviarPush()" class="btn btn-primary"><i class="glyphicon glyphicon-send"></i> Enviar Notificação</a>
				
				
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tbody>
							<tr>
								<td style="width:20%;" class="text-right">Notificação para</td>
								<td>
									${notificacao.nomeEntidade}/${notificacao.idEntidade}
									<%-- <c:choose>
										<c:when test="${not empty notificacao.user}">
											${notificacao.user.fullName}
										</c:when>
										<c:otherwise>
											Todos
										</c:otherwise>
									</c:choose> --%>
								</td>
							</tr>
							<tr>
								<td class="text-right">Tipo</td>
								<td>${notificacao.tipo}</td>
							</tr>
							<tr>
								<td class="text-right">Título</td>
								<td>${notificacao.titulo}</td>
							</tr>
							<tr>
								<td class="text-right">Conteúdo</td>
								<td>${notificacao.conteudo}</td>
							</tr>
							<tr>
								<td class="text-right">Pedido</td>
								<td>
									<a href="<ilion:url/>ilionnet/homebeauty/pedidos/${notificacao.pedido.id}/detalhes">${notificacao.pedido.id}</a>
								</td>
							</tr>
							<tr>
								<td class="text-right">Criada em</td>
								<td>${notificacao.dataCriacao}</td>
							</tr>
							
							</tbody>
						</table>
		            </div>
		
		        </div>
		        
		        <br/>
		        
		        
		        <a href="<ilion:url/>ilionnet/homebeauty/notificacoes" class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i> Voltar</a>
		        <a href="javascript:;" onclick="enviarPush()" class="btn btn-primary"><i class="glyphicon glyphicon-send"></i> Enviar Notificação</a>
				
		        
		    </div>
			 
		</div>
		
		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script type="text/javascript">
	
	function enviarPush() {
		if( ! confirm('Confirma o envio?') ) {
			return;
		}
		
		location = '<ilion:url/>ilionnet/homebeauty/notificacoes/${notificacao.id}/send/';
	}
	
	(function() {
		$('#homebeauty').click();
	})();

</script>

</body>

</html>
