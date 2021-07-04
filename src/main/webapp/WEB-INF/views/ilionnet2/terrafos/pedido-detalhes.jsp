<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>Pedido - <ilion:nomeEmpresa/></title>
        
        <jsp:include page="../inc/include-head.jsp" flush="true"/>
		
		<style>
			.texto-log{font-size:12px;}
			.pre-log{overflow: auto; width: 1000px; height: 500px;}
		</style>
		
    </head>

<body>

<jsp:include page="../inc/include-header.jsp" flush="true"/>

<jsp:include page="../inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">

		<div>
			
			<!-- Title -->
			<h1 class="section-title">
				<span>Pedido</span>
			</h1><!-- End Title -->
			
			<!--breadcrum start-->
			<ol class="breadcrumb text-left">
			  <li><a href="<ilion:url/>ilionnet/home">Home</a></li>
			  <li>Home Beauty</li>
			  <li>Pedido</li>
			  <li class="active">Detalhes</li>
			</ol><!--breadcrum end--> 
		</div>

        
		
		<div class="col-md-12">
		    <div class="component-box">
			
				<a href="<ilion:url/>ilionnet/homebeauty/pedidos" class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i> Voltar</a>
			
				<h2>Cliente: ${pedido.cliente.nome} (ID: ${pedido.cliente.id})</h2>
				
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tbody>
							<tr>
								<td style="width:20%;" class="text-right">Nome</td>
								<td>${pedido.cliente.nome}</td>
							</tr>
							<tr>
								<td class="text-right">E-mail</td>
								<td>${pedido.cliente.email}</td>
							</tr>
							<%-- <tr>
								<td class="text-right">CNPJ</td>
								<td>${pedido.cliente.cnpj}</td>
							</tr> --%>
							<%-- <tr>
								<td class="text-right">Senha</td>
								<td>${pedido.cliente.senha}</td>
							</tr> --%>
							<tr>
								<td class="text-right">Telefone</td>
								<td>${pedido.cliente.telefone}</td>
							</tr>
							<tr>
								<td class="text-right">Avaliação</td>
								<td>
									<c:if test="${pedido.avaliacaoProfissional>-1}">
										<c:forEach var="i" begin="1" end="${pedido.avaliacaoProfissional}">
									  		<i class="glyphicon glyphicon-star"></i> 
									  	</c:forEach>
									</c:if>
								</td>
							</tr>
							
							</tbody>
						</table>
		            </div>
		
		        </div>
		        
		        <h2>Endereço:</h2>
				
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tbody>
							<tr>
								<td style="width:20%;" class="text-right">CEP</td>
								<td>${pedido.endereco.cep}</td>
							</tr>
							<tr>
								<td style="width:20%;" class="text-right">Endereço</td>
								<td>${pedido.endereco.logradouro}</td>
							</tr>
							<tr>
								<td class="text-right">Número</td>
								<td>${pedido.endereco.numero}</td>
							</tr>
							<tr>
								<td class="text-right">Bairro</td>
								<td>${pedido.endereco.bairro}</td>
							</tr>
							<tr>
								<td class="text-right">Complemento</td>
								<td>${pedido.endereco.complemento}</td>
							</tr>
							<tr>
								<td class="text-right">Cidade/UF</td>
								<td>${pedido.endereco.cidade}/${pedido.endereco.uf}</td>
							</tr>
							
							</tbody>
						</table>
		            </div>
		
		        </div>
		        
		       <%--  <h2>Distribuidor: ${pedido.distribuidor.razaoSocial} (ID: ${pedido.distribuidor.id})</h2>
		        
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tbody>
							<tr>
								<td style="width:20%;" class="text-right">Razão Social</td>
								<td>${pedido.distribuidor.razaoSocial}</td>
							</tr>
							<tr>
								<td class="text-right">E-mail</td>
								<td>${pedido.distribuidor.email}</td>
							</tr>
							<tr>
								<td class="text-right">CNPJ</td>
								<td>${pedido.distribuidor.cnpj}</td>
							</tr>
							<tr>
								<td class="text-right">Telefone</td>
								<td>${pedido.distribuidor.telefone}</td>
							</tr>
							
							</tbody>
						</table>
		            </div>
		
		        </div> --%>

				<h2>Pedido: ${pedido.id} <small>feito em ${pedido.dataCriacaoFormatada}</small></h2>
				
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tbody>
							<tr>
								<td style="width:20%;" class="text-right">Valor Total</td>
								<td>R$ <ilion:formatNumber value="${pedido.valorTotal}"/></td>
							</tr>
							<%-- <tr>
								<td class="text-right">Método de Pagamento</td>
								<td>${pedido.metodoPagamento.nome}</td>
							</tr> --%>
							
							<tr>
								<td class="text-right">Status</td>
								<td>
									${pedido.status.nome}
								</td>
							</tr>
							<tr>
								<td class="text-right">Alterar Status</td>
								<td>
									<form class="form-inline" method="post" action="<ilion:url/>ilionnet/homebeauty/pedidos/${pedido.id}/status">
									  <select name="status" class="form-control">
									  	<option value="">---</option>
									  	<c:forEach var="sEnum" items="${pedidoStatusLista}">
									  	<option value="${sEnum}">${sEnum.nome}</option>
									  	</c:forEach>
									  </select>
									  <button class="btn btn-primary"><i class="glyphicon glyphicon-floppy-save"></i> Alterar status</button>
									</form>
								</td>
							</tr>
							
							<tr>
								<td class="text-right">Ver e-mail</td>
								<td>
									<form 
										class="form-inline" 
										method="get" 
										action="<ilion:url/>ilionnet/homebeauty/pedidos/${pedido.id}/ver-email"
										target="_blank"
									>
									  <select name="pedidoStatus" class="form-control">
									  	<option value="">---</option>
									  	<c:forEach var="sEnum" items="${pedidoStatusLista}">
									  	<option value="${sEnum}">${sEnum.nome}</option>
									  	</c:forEach>
									  </select>
									  <button class="btn btn-primary"><i class="glyphicon glyphicon-floppy-save"></i> Ver e-mail</button>
									</form>
								</td>
							</tr>
													
							</tbody>
						</table>
		            </div>
		
		        </div>
		              
		        <h2>Itens comprados</h2>
		        
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
		                	<thead>
		                		<tr>
								  <th class="text-center">Id Serv.</th>
								  <th class="text-center">Categoria</th>
								  <th class="text-center">Nome</th>
								  <th class="text-center">Qtd.</th>
								  <th style="width:100px;" class="text-right">Valor Unit. R$</th>
								  <th style="width:100px;" class="text-right">Valor Total R$</th>
								</tr>
		                	</thead>
					        <tbody>
							<c:forEach var="item" items="${pedido.pedidoItens}">
								<tr>
								  <td class="text-center">${item.idServico}</td>
								  <td class="text-center">${item.categoria.nome}</td>
								  <td class="text-center">${item.nome}</td>
								  <td class="text-center">${item.qtd}</td>
								  <td class="text-right"><ilion:formatNumber value="${item.valorItem}"/></td>
								  <td class="text-right"><ilion:formatNumber value="${item.valorTotal}"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						
						<table class="table table-bordered">
					        <tbody>
							<%-- <tr>
								<td class="text-right">Valor Sub-total R$</td>
								<td style="width:100px;" class="text-right"><ilion:formatNumber value="${pedido.valorSubTotal}"/></td>
							</tr> --%>
							<tr>
								<td class="text-right">Valor Total R$</td>
								<td style="width:100px;" class="text-right"><ilion:formatNumber value="${pedido.valorTotal}"/></td>
							</tr>
							</tbody>
						</table>
		            </div>
		
		        </div>
		        
		        <h2>Profissional</h2>
				
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
					        <tbody>
							<tr>
								<td style="width:20%;" class="text-right">Status do Profissional</td>
								<td>
									${pedido.profissionalStatus.nome}<br/>
									<button type="button" onclick="location='<ilion:url/>ilionnet/homebeauty/pedidos/${pedido.id}/procurar-profissional'">Procurar Profissional</button>
								</td>
							</tr>
							<tr>
								<td class="text-right">Data/Hora</td>
								<td>${pedido.dataHoraAguardandoProfissional}</td>
							</tr>
							<tr>
								<td class="text-right">Profissional</td>
								<td>${pedido.profissional.nome}</td>
							</tr>
							<tr>
								<td class="text-right">Avaliação</td>
								<td>
									<c:if test="${pedido.avaliacaoCliente>-1}">
										
										<c:forEach var="i" begin="1" end="${pedido.avaliacaoCliente}">
									  		<i class="glyphicon glyphicon-star"></i> 
									  	</c:forEach>
										
									</c:if>
								</td>
							</tr>
							</tbody>
						</table>
		            </div>
		
		        </div>
		        
		        <br/>

		        <h2>Logs</h2>
		        
		        <div class="pmd-card pmd-z-depth pmd-card-custom-view">
					
		            <div class="table-responsive">
		                <table class="table table-bordered">
		                	<thead>
		                		<tr>
								  <th class="text-center">Data/Hora</th>
								  <th class="text-center">Usu&aacute;rio</th>
								  <th class="text-center">Descri&ccedil;&atilde;o</th>
								</tr>
		                	</thead>
					        <tbody>
							<c:forEach var="log" items="${pedidoLogs}">
								<tr>
								  <td class="text-center"><fmt:formatDate value="${log.data}" pattern="dd/MM/yyyy HH:mm"/></td>
								  <td class="text-center">${log.usuario.nome}</td>
								  <td class="texto-log">
								  	  ${log.descricao}
								  </td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						
		            </div>
		
		        </div>
		        
		        <br/>
		        
		        <a href="<ilion:url/>ilionnet/homebeauty/pedidos" class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i> Voltar</a>
		        
		    </div>
			 
		</div>

		
    </div>


</div><!--end content area-->



<jsp:include page="../inc/include-footer.jsp" flush="true"/>

<jsp:include page="../inc/include-js-footer.jsp" flush="true"/>

<script>

	(function() {
		$('#homebeauty').click();
	})();
	
	//${pedido.idsProfissionaisJaProcurados}

</script>

</body>

</html>
