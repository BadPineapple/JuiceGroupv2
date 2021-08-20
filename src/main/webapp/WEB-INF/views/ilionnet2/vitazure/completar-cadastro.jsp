<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR"  ng-app="cadastroPessoaApp" ng-controller="CadastroPessoaController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/cadastroPessoa.js"></script>	
	<script type="text/javascript" src="<c:url value='../ilionnet/design/script/tiny_mce/tiny_mce.js'/>"></script>
<script type="text/javascript" src="<c:url value='../ilionnet/design/script/funcoesTinyMCE.js'/>"></script>
<script type="text/javascript" src="../ilionnet/design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="../ilionnet/design/script/common.js"></script>
<script type="text/javascript">
	document.write(getCalendarStyles());
	var cal1x = new CalendarPopup("testdiv1");
	</script>
	<style>
	  .not-active {
		  pointer-events: none;
		  cursor: default;
		  text-decoration: none;
		  color: #f3f3f3;
		  background: #ddd;
		}
	</style>
	<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;" ></div>
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
                                        <input type="text" data-mask="000.000.000-00" ng-model="pessoa.cpf" id="cpf" required onblur="validarCPF(this.value)"/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Data de Nascimento</label>
                                        <input type="text" data-mask="00/00/0000" ng-model="pessoa.dataNascimento" id="dataNasc" onblur="validarDataMaiorAtual(this.value)" required/>
                                        <a href="javascript:;" onblur="validarDataMaiorAtual(this.value)" style="position: absolute;left: 86%;top: 45%;" onClick="cal1x.select(document.getElementById('dataNasc'),'linkDataNasc','dd/MM/yyyy'); return false;" id="linkDataNasc" name="linkDataNasc">
			                                           <i class="fas fa-calendar-week" style="font-size: 20px;"></i>
			                                        </a>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Telefone</label>
                                        <input type="text" data-mask="(00) 00000-0000" ng-model="pessoa.telefone" id="telefone" onblur="validarDDD(this.value , 'telefone')"/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Celular</label>
                                        <input type="text" data-mask="(00) 00000-0000" ng-model="pessoa.celular" id="celular" onblur="validarDDD(this.value , 'celular')" required/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Email</label>
                                        <input type="text" ng-model="pessoa.email" class="not-active" required/>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>CEP</label>
                                        <input type="text" data-mask="00.000-000" ng-model="pessoa.cep" id="cep" required onblur="validarCep(this.value)"/>
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


<script>

function validarCPF(cpf) {
	if(!cpfValido(cpf)){
		alert("CPF inválido.")
		document.getElementById('cpf').value=""; 
	}
}

function cpfValido(cpf) {	
	cpf = cpf.replace(/[^\d]+/g,'');	
	if(cpf == '') return false;	
	if (cpf.length != 11 || 
		cpf == "00000000000" || 
		cpf == "11111111111" || 
		cpf == "22222222222" || 
		cpf == "33333333333" || 
		cpf == "44444444444" || 
		cpf == "55555555555" || 
		cpf == "66666666666" || 
		cpf == "77777777777" || 
		cpf == "88888888888" || 
		cpf == "99999999999")
			return false;		
	add = 0;	
	for (i=0; i < 9; i ++)		
		add += parseInt(cpf.charAt(i)) * (10 - i);	
		rev = 11 - (add % 11);	
		if (rev == 10 || rev == 11)		
			rev = 0;	
		if (rev != parseInt(cpf.charAt(9)))		
			return false;		
	add = 0;	
	for (i = 0; i < 10; i ++)		
		add += parseInt(cpf.charAt(i)) * (11 - i);	
	rev = 11 - (add % 11);	
	if (rev == 10 || rev == 11)	
		rev = 0;	
	if (rev != parseInt(cpf.charAt(10)))
		return false;		
	return true;   
}

</script>
    
    </div>
</body>
</html>