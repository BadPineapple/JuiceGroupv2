<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="cadastroPessoaApp" ng-controller="CadastroPessoaController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/cadastroPessoa.js"></script>
	
	<style>
	  .not-active {
		  pointer-events: none;
		  cursor: default;
		  text-decoration: none;
		  color: #f3f3f3;
		  background: #ddd;
		}
		
	</style>
	
</head>

<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
        <div class="content-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Crie sua conta</h3>

                            <div class="pages-internas">
                                <a href="/home">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>

                                    Home     
                                </a>

                                <span>></span>

                                <a href="#">Crie sua conta</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Crie sua conta</h3>
                    </div>
                    <div class="col-12 col-md-6 offset-md-3 col-xl-6 offset-xl-3">
                      <form ng-submit="submit()" class="form-default" style="padding: 3rem 0; font-weight: 800;">
						          <angular-initializer
						            ng-init="pessoa.cliente='true';tipoConta='CL';"/>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Tipo Conta</label>
		                                    <select ng-model="tipoConta">
		                                        <option value="CL">Cliente</option>
		                                        <option value="PS">Profissional</option>
		                                    </select>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Nome completo</label>
                                            <input type="text"  ng-model="pessoa.nome" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Celular</label>
                                            <input type="text" placeholder="(00) 0000 0000" data-mask="(00) 00000-0000" ng-model="pessoa.celular" id="txtCelular" required onblur="validarDDD(this.value)"/>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>E-mail (Será seu Login)</label>
                                            <input type="text" ng-model="pessoa.email" required />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="input-block">
                                            <label>Senha</label>
                                            <input type="text" ng-model="pessoa.senha" required onblur="validarSenha(this.value)" maxlength="8">
                                              <i class="far fa-info-circle" data-toggle="tooltip" data-html="true" style="font-size: 28px;position: absolute;top: 34px;left: 89%;" title="Senha com 8 caracteres, pelo menos uma letra maiúscula e um número"></i>
                                            </input>
                                            
                                            <div id="divNivelSenha" style="border: 1px solid;width: 100%;height: 30px;border-radius: 8px;display: none;">
                                               <div id="nivelSenhaFraca" style="background: red;width: 35%;height: 28px;text-align: center;position: absolute;border-radius: 8px;display: none;"><p style="position: relative;top: -10px;color: white;">Fraca</p></div>
                                               <div id="nivelSenhaMedia" style="background: #e3e025;width: 70%;height: 28px;text-align: center;position: absolute;border-radius: 8px;display: none;"><p style="position: relative;top: -10px;color: white;">Média</p></div>
                                               <div id="nivelSenhaForte" style="background: green;width: 94%;height: 28px;text-align: center;position: absolute;border-radius: 8px;display: none;"><p style="position: relative;top: -10px;color: white;">Forte</p></div>
                                            </div>   
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="termos">
                                            <input type="checkbox" required oninvalid="this.setCustomValidity('Marque esta caixa para continuar')" onchange="try{setCustomValidity('')}catch(e){}"/>
                                            <span class="new-cadastro">
                                                Eu declaro que li e concordo com os 
                                                <div class="button-blue line">

                                                    <%--<ilion:artigoConsulta categoria="termos-de-uso" artigo="termos-de-uso-vitazure" order="posicao" varRetorno="art"/>--%>
                                                    <ilion:arquivoCategoriaLista categoria="documentos" order="posicao" layout="lateral" varRetorno="art"/>

                                                        <c:forEach var="arq" items="${art}">

                                                            <c:if test="${arq.title == \"Termos e condições\"}">

                                                                <c:set var="arqTermos" value="${arq.url}"/>

                                                            </c:if>

                                                        </c:forEach>

                                                    <a href="${arqTermos}" target="_blank" >&nbsp;&nbsp;&nbsp;&nbsp;Termos e condições de uso.</a>
                                                </div>
                                            </span>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <button type="submit" id="criarusuario" class="not-active button-secundary" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Criar conta</button>
                                    </div>
                                </div>
                            </form>

                        <span class="new-cadastro">
                            Já tem uma conta?
                            <div class="button-blue line">
                                 <a href="/entrar">Entre agora</a>
                            </div>
                        </span>
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
function validarDDD(telefone) {
	if(telefone.length < 15){
    	document.getElementById('txtCelular').value="";       
    	alert("Telefone Inválido");
	}else if(telefone.substring(1,3) < 11){
		document.getElementById('txtCelular').value="";       
    	alert("Telefone Inválido");
	}	
	
}

function validarSenha(senha){
	var numero = senha.match(/[0-9]+/);
	var maiuscula = senha.match(/[A-Z]+/);
		var divNivelSenha = document.getElementById("divNivelSenha");
		var nivelFraco = document.getElementById("nivelSenhaFraca");
		var nivelMedia = document.getElementById("nivelSenhaMedia");
		var nivelForte = document.getElementById("nivelSenhaForte");
		document.getElementById("criarusuario").className = "not-active button-secundary";
	if(senha.length < 8){
		divNivelSenha.style.display = "block";
		nivelFraco.style.display = "block";
		nivelMedia.style.display = "none";
		nivelForte.style.display = "none";
		document.getElementById("criarusuario").className = "not-active button-secundary";
		return false;
	}else if(numero == null || maiuscula == null){
		divNivelSenha.style.display = "block";
		nivelFraco.style.display = "none";
		nivelMedia.style.display = "block";
		nivelForte.style.display = "none";
		document.getElementById("criarusuario").className = "not-active button-secundary";
		return false;
	}else if(senha.length >= 8 && numero.length  > 0 && maiuscula.length  > 0){
		divNivelSenha.style.display = "block";
		nivelFraco.style.display = "none";
		nivelMedia.style.display = "none";
		nivelForte.style.display = "block";
		document.getElementById("criarusuario").className = "button-secundary";
		return false;
	}
	
}
</script>

    </div>
</body>
</html>