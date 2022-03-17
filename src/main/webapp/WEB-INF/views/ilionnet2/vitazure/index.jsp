<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="contatoApp" ng-controller="ContatoController">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/contatoForm.js"></script>
	
</head>

<body id="index" class="home">
<style>

	.show-policy {
        margin-bottom: 0px !important;
    }

    .hidde-policy {
        margin-bottom: -1000px !important;
    }

    .privacy-policy{
       display: flex;
	    align-items: center;
	    justify-content: space-between;
	    width: 100%;
	    min-height: inherit;
    }
    .privacy-policy-modal {
        position: fixed;
	    z-index: 2147483647;
	    bottom: 16px;
	    left: 16px;
	    right: 16px;
	    margin: auto;
	    max-width: 1334px;
	    min-height: 70px;
	    box-shadow: 0 2px 4px 0 rgb(0 0 0 / 40%);
	    border: solid 1px #eeeeee;
	    background-color: #fff;
    }
    .privacy-policy-text {
       vertical-align: middle;
       padding: 16px;
    }
    
    .privacy-policy-button {
        padding: 8px 16px;
	    border-radius: 3px;
	    font-family: opensans, helvetica, arial, sans-serif;
	    font-size: 14px;
	    font-weight: bold;
	    font-stretch: normal;
	    font-style: normal;
	    line-height: 1;
	    letter-spacing: normal;
	    text-align: center;
	    color: #fff;
	    border: 0;
	    cursor: pointer;
    
    }
    .privacy-policy-text-span {
       font-family: opensans, helvetica, arial, sans-serif;
	    font-size: 12px;
	    font-weight: normal;
	    font-stretch: normal;
	    font-style: normal;
	    line-height: normal;
	    letter-spacing: -0.45px;
	    color: #333;
    }

    .button-accept,
    .button-accept:hover {
        background: #2b6199;
        border-radius: 0;
        color: #ffffff;
        grid-area: button;
        transition: .5s;
        width: 150px;
    }

    .button-accept:hover {
        background: #2b6199;
        color: #ffffff;
        transform: scale(1.1);
        width: 150px;
    }
    .banner-content{
        height: 115vh;
    }
    @media (min-width: 700px){
        .title-banner{
            font-weight: 800 !important;
            font-size: 45px;
        }
    }

    }
</style>

<div class="privacy-policy-modal show-policy">
   <div class="privacy-policy"> 
    <div class="privacy-policy-text">
        <ilion:arquivoCategoriaLista categoria="documentos" order="posicao" layout="lateral" varRetorno="art"/>
        <c:forEach items="${art}" var="arq">
            <c:if test="${arq.title == \"Política de privacidade\"}">
                <c:set var="arqPolitica" value="${arq.url}"/>
            </c:if>
        </c:forEach>
         <span class="privacy-policy-text-span">Nós usamos cookies e outras tecnologias semelhantes para melhorar a sua experiência em nossos serviços, personalizar publicidade e recomendar conteúdo de seu interesse. Ao utilizar nossos serviços, você concorda com tal monitoramento descrito em nossa <a href="${arqPolitica}" target="_blank">Política de Privacidade</a></span>
    </div>
    <div style="padding: 16px 16px 16px 0;">
      <button class="btn privacy-policy-button btn-primary" onclick="closePolicy()">Prossegir</button>
    </div>
    </div>
</div>

<script>
    function showPolicy() {
        setTimeout(function() {
            var modal = document.querySelector('.privacy-policy-modal');
            modal.classList.add('show-policy');
            modal.classList.remove('hidde-policy');
        }, 2000)
    }
    function closePolicy() {
        var modal = document.querySelector('.privacy-policy-modal');
        modal.classList.add('hidde-policy');
        modal.classList.remove('show-policy');
        lastAccept();
    }
    function lastAccept() {
        localStorage.setItem('lastAccept', 'true');
    }
    function onceADay() {
        if (localStorage.getItem('lastAccept') !== 'true') {
            showPolicy();
        }else {
            closePolicy();
        }
    }
    onceADay();
    console.log("teste: " + localStorage.getItem('lastAccept'));
</script>

    <div id=app>
        <jsp:include page="includes/include-header.jsp" flush="true" />
    
        <div class="banner-content banner-home">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-7 col-xl-7" style="flex: 0 0 68.333%;max-width: 67.333%;">
                        <h1 class="title-banner">As portas para o futuro profissional se abrem na Clínica Escola</h1>

                        <p>Instituições que oferecem mais suporte para o conhecimento do<br>aluno,promovem a satisvação no aprendizado, além de oferecer a <br>oportunidade do atendimento social.</p>
                        <button type="submit" class="button-secundary" style="width: 28rem;height: 6rem;font-size: 2.4rem;">Quero saber mais.</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
					
					<div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h2 style="margin-bottom: 8px;">Principais Benefícios</h2>
                        <p>Como um ambiente propicio para a experimentação e para a prática, a Vitazure Clinica Escola 
                          <br>oferece inúmeros benefícios não apenas para os alunos, mais também para a sociedade e
                          <br>para sua instituição de ensino. Confira agora mesmo algumas dessas vantagens:
                        </p>
                        
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/segurança.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Segurança</strong>

                                <p>O atendimento a população será sempre supervisionado por um professor da instituição.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/flexibilidade.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Flexibilidade</strong>

                                <p>Os atendimentos serão sempre agendados conforme a disponibilidade de alunos e professores,
                                 podemos ser de qualquer lugar onde estejam aluno e paciente.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/conforto.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Conforto</strong>

                                <p>O atendimento é online, aluno e paciente poderão usar seu celular, tablet ou computador.<br>Basta estar conectado à internet.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/atual.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Atual</strong>

                                <p>O atendimento psicoterápico online é uma  importante recurso tecnológico, que denicratiza e possibilita maior acesso das pessoas terapia.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/escalonavel.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Escalonável</strong>

                                <p>Poderão participar da Clinica Escola instituições de todo o país, independentes da sua localização geográfica.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/baixo-custo.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Baixo custo</strong>

                                <p>A instituição poderá negociar diretamente com a Vitazure sua participação, não há taxas de uso.É viável.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        			<div class="col-12 col-md-6 col-xl-6">
                        <div class="cellphone">
                            <figure>
                                <img src="../assets/images/cellphone_vitazure.jpg" alt="">
                            </figure>
                        </div>
                    </div>
 
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="join-us">
                            <h1 style="margin-top: 100px;">Benefícios</h1>
                            <p>Além dos alunos, sua instituição tambem sera beneficiada pela experiência da clínica escola.
                            Isso acontece, pois, alunos que possuem mais suporte para o conhecimento normalmente permanecerão
                             mais felizes e satisfeitos com a instituição da qual fazer parte, já que verão o emprenho dela
                             proporcionar novos métodos de aprendizado.
                             <br>Estudantes felizes com o local de estudo e contentes com 
                             as oportunidades de crescimento tornan-se mais dispostos e motivados. Nesse contexto, todos saem 
                             ganhando, não é mesmo?</p>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="parceiros text-center">
                            <p>Instituições parceiras</p>

                            <div class="partners">
                                <figure>
                                    <img src="assets/images/inpro.png" alt="Instituto Projeção">
                                </figure>
<!--                                 <figure> -->
<!--                                     <img src="assets/images/ilion_marca.png" style="width: 160px;" alt="Ilion"> -->
<!--                                 </figure> -->

<!--                                 <figure> -->
<!--                                     <img src="assets/images/caderno_virtual.png" style="width: 160px;" alt="Caderno virtual"> -->
<!--                                 </figure> -->
                                
<!--                                 <figure> -->
<!--                                     <img src="assets/images/believe.jpg" style="width: 160px;" alt="Believe"> -->
<!--                                 </figure> -->

<!--                                 <figure> -->
<!--                                     <img src="assets/images/cardioHortiz.png" style="width: 160px;" alt="Cardio"> -->
<!--                                 </figure> -->

<!--                                 <figure style="position: relative;top: -32px;"> -->
<!--                                     <img src="assets/images/logo-hospital-goiania-leste.png" style="width: 160px;" alt="Hospital Goiânia leste"> -->
<!--                                 </figure> -->

                            </div>
                             <p></p>
                        </div>
                    </div>
                    <div class="row">
                    <div class="col-12">
                        <div class="col-12 text-center">
                            <h3>Entre em contato agora</h3>
                            <p>Como você pôde perceber, as vantagens de uma clinica escolas são muitas.Além de oferecer
                            <br>atendimento e cuidados de forma acessível à população.Sua instituição estará será capaz de
                            <br>proporcionar diferentes saberes relacionados à área da saúde mental.</p>
                            
                        </div>
                    </div>

                    <div class="col-12  col-md-6 col-xl-6">
                        <form class="form-default"  ng-submit="submit()"  id="contatoApp">
                            <div class="row">
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Nome</label>
                                        <input type="text" ng-model="contato.nome" placeholder="Nome" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>E-mail</label>
                                        <input type="text" ng-model="contato.email" placeholder="E-mail" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Telefone</label>
                                        <input type="text" ng-model="contato.telefone" data-mask="(00)00000-0000" placeholder="(00) 0000-0000" required />
                                    </div>
                                </div>
                                
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Nome da Instituição</label>
                                        <input type="text" ng-model="contato.nomeInstituição" placeholder="Instituição" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Mensagem</label>
                                        <textarea cols="20" rows="5" ng-model="contato.mensagem" placeholder="Informe seu texto aqui."></textarea>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <button class="button-secundary" type="submit" style="font-size: 1.6rem;">Enviar mensagem</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-12 col-md-5 offset-md-1 col-xl-5 offset-xl-1">
                        <div class="right-input">
                            <h2>Preecha o formulário ao lado. Entraremos em contato o quanto antes.</h2>
                        </div>
                    </div>                   
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
    </div>
</body>
</html>