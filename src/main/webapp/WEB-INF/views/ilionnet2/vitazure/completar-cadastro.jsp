<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR"  ng-app="cadastroPessoaApp" ng-controller="CadastroPessoaController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/cadastroPessoa.js"></script>	
</head>

<body id="index" class="home">

    <div id="app">
<%--         <jsp:include page="includes/include-header.jsp" flush="true" /> --%>
		 <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <c:if test="${pessoa.psicologo}">
	        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />
        </c:if>
        <c:if test="${pessoa.cliente}">
           <jsp:include page="includes/include-painel-person.jsp" flush="true" />
        </c:if>
        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>${pessoa.psicologo ? 'Profissional' : 'Cliente'} complete seu cadastro</h3>
                    </div>
                    <div class="col-12 col-md-6 offset-md-3 col-xl-6 offset-xl-3">
                        <form ng-submit="submit()" class="form-default" style="padding: 3rem 0; font-weight: 800;">
                          <angular-initializer ng-init="pessoa.id='${pessoa.id}';
						                     pessoa.nome='${pessoa.nome}';
						                     pessoa.email='${pessoa.email}';
						                     pessoa.telefone='${pessoa.telefone}';
						                     pessoa.celular='${pessoa.celular}';
						                     pessoa.senha='${pessoa.senha}';
						                     pessoa.dataNascimento='${pessoa.dataNascimento}';
						                     pessoa.cpf='${pessoa.cpf}';
						                     pessoa.cep='${pessoa.cep}';
						                     pessoa.genero='${pessoa.genero}';
                          					 pessoa.cliente ='${pessoa.cliente}';
                         					 pessoa.psicologo ='${pessoa.psicologo}';
                         					 pessoa.foto.id='${pessoa.foto.id}';
						                     pessoa.foto.link='${pessoa.foto.link}';
                          					 "/>
                          
                            <div class="row">
                             <c:if test="${pessoa.cliente}">
                               <div class="col-12">
                                            <div class="perfil">
                                                <label for="avatar" class="photo-perfil">
                                                    <input type="file" name="avatar" id="avatar" style="display: none;">
                                                    <figure>
                                                        <img id="img" src="${pessoa.foto.link == null ? '../assets/images/perfil.png' : pessoa.foto.link}" alt="">
                                                    </figure>
                                                    <p>Alterar foto</p>
                                                </label>
                                            </div>
                                        </div>
                              </c:if>           
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Nome</label>
                                        <input type="text" ng-model="pessoa.nome" required />
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Gênero</label>
                                        <select ng-model="pessoa.genero" class="form-control input-sm">
						   					<option value="M">Masculino</option>
						   					<option value="F">Feminino</option>
										</select>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>CPF</label>
                                        <input type="text" data-mask="000.000.000-00" ng-model="pessoa.cpf" required />
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Data de Nascimento</label>
                                        <input type="text" data-mask="00/00/0000" ng-model="pessoa.dataNascimento" required/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Telefone</label>
                                        <input type="text" data-mask="(00)0000-0000" ng-model="pessoa.telefone"/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Celular</label>
                                        <input type="text" data-mask="(00)00000-0000" ng-model="pessoa.celular" required/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Email</label>
                                        <input type="text" ng-model="pessoa.email" required/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>CEP</label>
                                        <input type="text" data-mask="00000-000" ng-model="pessoa.cep" required />
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="button-secundary" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Completar cadastro</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
        <script src="../assets/js/bundle.libs.ilionnet.js"></script>
			<script src="../assets/js/bundle.scripts.ilionnet.js"></script>
			<script src="../assets/js/bundle.libs.angular.js"></script>
            
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
 $(function(){
	 $('#avatar').change(function(){
	 	const file = $(this)[0].files[0]
	 	const fileReader = new FileReader()
	 	fileReader.onloadend = function(){
			$('#img').attr('src',fileReader.result)
		}
	 	fileReader.readAsDataURL(file)
	 })
 })

</script>    
    </div>
</body>
</html>