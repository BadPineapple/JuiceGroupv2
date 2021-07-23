<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" ng-app="clienteApp" ng-controller="ClienteController">
    <head>
        <title>Profissional - <ilion:nomeEmpresa/></title>
        <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
</head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Profissional</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Protons</li>
		  <li><a href="<ilion:url/>ilionnet/protons/usuario">Profissional</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					<form ng-submit="submit()">
						          <angular-initializer
						            ng-init="pessoa.id='${pessoa.id}';
						                     pessoa.nome='${pessoa.nome}';
						                     pessoa.email='${pessoa.email}';
						                     pessoa.telefone='${pessoa.telefone}';
						                     pessoa.celular='${pessoa.celular}';
						                     pessoa.senha='${pessoa.senha}';
						                     pessoa.dataNascimento='${pessoa.dataNascimento}';
						                     pessoa.cpf='${pessoa.cpf}';
						                     pessoa.cep='${pessoa.cep}';
						                     pessoa.cidade='${pessoa.cidade}';
						                     pessoa.estado='${pessoa.estado}';
						                     pessoa.endereco='${pessoa.endereco}';
						                     pessoa.setor='${pessoa.setor}';
						                     pessoa.psicologo='true'
						                     "/>
							<div class="row">
						          <div class="col-md-12 col-lg-12 col-sm-12">
						            <div class="col-md-6 col-lg-6 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="nome" class="control-label">Nome</label>
						                <input type="text" id="nome" ng-model="pessoa.nome" class="form-control input-group-lg" required/>
						              </div>
						            </div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
						               <div class=" form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label>Cpf</label>
						                <input type="text" class="form-control" data-mask="000.000.000-00"  id="cpf"  ng-model="pessoa.cpf">
						              </div>
						         	</div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
						               <div class=" form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label>Data Nascimento</label>
						                <input type="text" class="form-control" data-mask="00/00/0000"  id="dataAniversario"  ng-model="pessoa.dataNascimento" ng-blur="">
						              </div>
						         	</div>
					       		 </div>
					       		 
					       		 <div class="col-md-12 col-lg-12 col-sm-12">
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="email" class="control-label">Email</label>
						                <input type="email" id="email" ng-model="pessoa.email" class="form-control input-group-lg" required/>
						              </div>
						            </div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
								        <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
								           <label for="senha" class="control-label">Senha</label>
								           <input type="password" id="senha" ng-model="pessoa.senha" class="form-control input-group-lg" required/>
								        </div>
								    </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="telefone" class="control-label">Telefone</label>
						                <input type="Large" id="telefone" ng-model="pessoa.telefone" data-mask="(00) 00000-0000" class="form-control input-group-lg"/>
						              </div>
						            </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="celular" class="control-label">Celular</label>
						                <input type="Large" id="celular" ng-model="pessoa.celular" data-mask="(00) 00000-0000" class="form-control input-group-lg"/>
						              </div>
						            </div>
					       		 </div>
							<div class="col-md-12 col-lg-12 col-sm-12">
								   <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="cep" class="control-label">CEP</label>
						                <input type="Large" id="cep" ng-model="pessoa.cep" data-mask="00.000-000" class="form-control input-group-lg" required/>
						              </div>
						            </div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
								        <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
								           <label for="endereco" class="control-label">Endereco</label>
								           <input type="Large" id="endereco" ng-model="pessoa.endereco" class="form-control input-group-lg" required/>
								        </div>
								    </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="setor" class="control-label">Bairro</label>
						                <input type="Large" id="setor" ng-model="pessoa.setor" class="form-control input-group-lg"/>
						              </div>
						            </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="cidade" class="control-label">Cidade</label>
						                <input type="Large" id="cidade" ng-model="pessoa.cidade" class="form-control input-group-lg"/>
						              </div>
						            </div>
							</div>	
							<div class="col-md-12 col-lg-12 col-sm-12" style="text-align: center;padding-top: 30px;padding-bottom: 50px;">
							    <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
										<button type="submit" class="btn btn-success pmd-checkbox-ripple-effect">Salvar</button>
										<a href="<ilion:url/>profissional" class="btn btn-warning">Cancelar</a>
								</div>		
						   </div>	
					</div>	
				</form>		
			</div>
</div>
<jsp:include page="../../../ilionnet2/inc/include-footer.jsp" flush="true"/>
<jsp:include page="../../../ilionnet2/inc/include-js-footer.jsp" flush="true"/>

<script src="/assets/js/angular.min.js"></script>
<script src="/assets/js/bundle.libs.ilionnet.js"></script>
<script src="/assets/js/bundle.scripts.ilionnet.js"></script>
<script src="/assets/js/bundle.libs.angular.js"></script>
<script src="/assets/js/vitazure/cliente.js"></script>

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

</body>

</html>
