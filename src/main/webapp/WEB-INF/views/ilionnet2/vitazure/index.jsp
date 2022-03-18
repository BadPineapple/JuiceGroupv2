<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctypehtml>
<html class="no-js" lang="pt-BR" ng-app="buscaProfissionalAplicativo" ng-controller="BuscaProfissionalControlador">

<cabeça>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/buscaProfissional.js"></script>
	
</head>

<body id="index" class="home">
<estilo>

	.show-policy {
        margem inferior: 0px !importante;
    }

    .hidde-policy {
        margem inferior: -1000px !importante;
    }

    .política de Privacidade{
       exibição: flexível;
	    alinhar-itens: centro;
	    justificar-conteúdo: espaço-entre;
	    largura: 100%;
	    min-height: herdar;
    }
    .privacy-policy-modal {
        posição: fixa;
	    índice z: 2147483647;
	    inferior: 16px;
	    esquerda: 16px;
	    direita: 16px;
	    margem: automático;
	    largura máxima: 1334px;
	    altura mínima: 70px;
	    box-shadow: 0 2px 4px 0 rgb(0 0 0 / 40%);
	    borda: sólido 1px #eeeeee;
	    cor de fundo: #fff;
    }
    .privacy-policy-text {
       alinhamento vertical: meio;
       preenchimento: 16px;
    }
    
    .botão de política de privacidade {
        preenchimento: 8px 16px;
	    raio da borda: 3px;
	    família de fontes: opensans, helvetica, arial, sans-serif;
	    tamanho da fonte: 14px;
	    intensidade da fonte: Negrito;
	    estiramento de fonte: normal;
	    estilo de fonte: normal;
	    altura da linha: 1;
	    espaçamento entre letras: normal;
	    alinhamento de texto: centro;
	    cor: #ff;
	    borda: 0;
	    cursor: ponteiro;
    
    }
    .privacy-policy-text-span {
       família de fontes: opensans, helvetica, arial, sans-serif;
	    tamanho da fonte: 12px;
	    peso da fonte: normal;
	    estiramento de fonte: normal;
	    estilo de fonte: normal;
	    altura da linha: normal;
	    espaçamento entre letras: -0,45px;
	    cor: #333;
    }

    .botão-aceitar,
    .button-accept:hover {
        fundo: #2b6199;
        raio da borda: 0;
        cor: #ffffff;
        área de grade: botão;
        transição: .5s;
        largura: 150px;
    }

    .button-accept:hover {
        fundo: #2b6199;
        cor: #ffffff;
        transformar: escala(1,1);
        largura: 150px;
    }
    .banner-conteúdo{
        altura: 115vh;
    }
    @media (largura mínima: 700px){
        .title-banner{
            peso da fonte: 800 !importante;
            tamanho da fonte: 45px;
        }
    }

/* @media (largura máxima: 768px) { */
/* .privacy-policy-modal { */
/* display: grade!importante; */
/* grid-template-áreas: */
/* "texto" */
/* "botão" */
/*; */
/* grid-template-rows: 1fr 1fr; */
/* altura: 200px; */
/* preenchimento: 0rem 2rem; */
/* } */

/* .privacy-policy-text { */
/* preenchimento: 0 .5rem; */
/* largura: 100%!importante; */
/* } */

/* .botão-aceitar, */
/* .button-accept:hover { */
/* largura: 100%!importante; */
/* } */

/* @media (largura máxima: 501px) { */
/* .privacy-policy-modal { */
/* altura: 270px; */
/* } */
/* } */

/* @media (largura máxima: 608px) e (largura mínima: 500px) { */
/* .privacy-policy-modal { */
/* altura: 220px; */
/* } */
/* } */
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
         <span class="privacy-policy-text-span">Nós usamos cookies e outras tecnologias semelhantes para melhorar sua experiência em nossos serviços, personalizar publicidade e recomendar. Ao utilizar nossos serviços, você concorda com tal monitoramento descrito em nossa <a href="${arqPolitica}" target="_blank">Política de Privacidade</a></span>
    </div>
    <div style="preenchimento: 16px 16px 16px 0;">
      <button class="btn privacy-policy-button btn-primary" onclick="closePolicy()">Prossegir</button>
    </div>
    </div>
</div>

<script>
    function showPolicy() {
        setTimeout(function(){
            var modal = document.querySelector('.privacy-policy-modal');
            modal.classList.add('show-policy');
            modal.classList.remove('hidde-policy');
        }, 2000)
    }
    function closePolicy() {
        var modal = document.querySelector('.privacy-policy-modal');
        modal.classList.add('hidde-policy');
        modal.classList.remove('show-policy');
        lastAceitar();
    }
    function lastAceitar() {
        localStorage.setItem('lastAccept', 'true');
    }
    função uma vezAdia() {
        if (localStorage.getItem('lastAccept') !== 'true') {
            showPolicy();
        }outro {
            closePolicy();
        }
    }
    uma vez por dia();
    console.log("teste: " + localStorage.getItem('lastAccept'));
</script>

    <div id=aplicativo>
        <jsp:include page="includes/include-header.jsp" flush="true" />
    
        <div class="banner-content banner-home">
            <div class="container">
                <div class="linha">
                    <div class="col-12 col-md-7 col-xl-7">
                        <h1 class="title-banner">Seus sentimentos merecem toda a nossa atenção, aqui na <span style="color: #0097d6;">Vitazure</span> você vai encontrar quem te entende.</h1>

                        <p>Faça sua consulta pelo celular, tablet ou computador, qualquer hora com privacidade e segurança garantida.</p>

                        <form ng-submit="consultarProfissionalExterna()" class="form-highlight">
                          <input id="cidade" style="display: contents;">
                            <select ng-model="tipoProfissional">
                                <option value="">Tipo de profissional</option>
                                <c:forEach var="tipoProfissional" items="${tiposProfissional}">
                                    <option ng-if="${tipoProfissional == 'PSICOLOGO'}" value="${tipoProfissional}">${tipoProfissional.valor}</option>
                                </c:forEach>
                            </select>

                            <select ng-model="especialista">
                                <option value="">Especialidade</option>
                                <c:forEach items="${especialidades}" var="especialidade">
                                    <option ng-if="tipoProfissional == 'PSICOLOGO' && ${especialidade.tipoProfissional == 'PSICOLOGO'}" value="${especialidade.valor}">${especialidade.valor}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="button-secundary">Buscar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="linha">
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="faces-felizes">
                            <div class="up-photos">
                                <figure class="first-photo">
                                    <img src="assets/images/img-1.jpg" alt="">
                                </figura>

                                <figure class="second-photo">
                                    <img src="assets/images/img-2.jpg" alt="">
                                </figura>
                            </div>

                            <div class="down-photos">
                                <figure class="terceira foto">
                                    <img src="assets/images/img-3.jpg" alt="">
                                </figura>

                                <figure class="fourth-photo">
                                    <img src="assets/images/img-4.jpg" alt="">
                                </figura>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="join-us">
                            <p><span>Prático</span> <span>Seguro</span> <span>Econômico</span></p>

                            <h5>Conheça nossa rede de profissionais licenciados</h5>

                            <p>Nossa rede de profissionais cobre uma variedade de especialidades para atender às suas necessidades específicas. São Profissionais graduados e registrados no Conselho Federal de Psicologia, autorizados (e-Psi) a prestarem serviços por meio de Tecnologias da Informação e da Comunicação (TIC). São profissionais que passam por processo criterioso, sujeitos a credenciamento de código de ética e sigilo.</p>

                            <p>Você é um psicólogo?</p>

                            <a href="<ilion:url/>cadastro-se" class="button-purple line">
                                Junte-se a nós

                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <Caminho d = "M15.2931 6.70711C14.9026 6,31658 14,9026 5,68342 15,2931 4,90237 16,3168 4,90237 5.29289C15.6837 16,7074 5.29289L22.7073 11.2929C23.0979 11,6834 23,0979 12,3166 22,7073 12.7071L16.7074 18.7071C16.3168 19,0976 15,6837 19,0976 15,2931 18.7071C14 .9026 18.3166 14.9026 17.6834 15.2931 17.2929L19.586 13h2.01103c1.45265 13 1 12.5523 1 12C1 11.4477 1.45265 11 2.01103 11h19.586L15.2931 6.70711Z "preencher =" # 8c30f5 "/>
                                </svg>
                                    
                            </a>
                        </div>
                    </div>


                    <div class="col-12">
                        <div class="parceiros text-center">
                            <p>Instituições parceiras</p>

                            <div class="parceiros">
                                <figura>
                                    <img src="assets/images/inpro.png" alt="Instituto Projeção">
                                </figura>

                                <figura>
                                    <img src="assets/images/ilion_marca.png" style="width: 160px;" alt="Ilion">
                                </figura>

                                <figura>
                                    <img src="assets/images/caderno_virtual.png" style="width: 160px;" alt="Caderno virtual">
                                </figura>
                                
                                <figura>
                                    <img src="assets/images/believe.jpg" styeue="largura: 160px;" alt="Acredite">
                                </figura>

                                <figura>
                                    <img src="assets/images/cardioHortiz.png" style="largura: 160px;" alt="Cardio">
                                </figura>

                                <figura estilo="posição: relativo;topo: -32px;">
                                    <img src="assets/images/logo-hospital-goiania-leste.png" style="width: 160px;" alt="Hospital Goiânia leste">
                                </figura>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="testemunho">
            <div class="container">
                <div class="linha">
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="lado esquerdo">
                            <div class="testimonial-title">
                                <h2>Depoimentos de alguns clientes</h2>
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <Caminho fill-regra = "EvenOdd" clip-regra = "EvenOdd" d = "M142.373 18.7419C121.049 29,1541 110,387 41,3882 110,387 55.4447C119.476 56,4859 126,992 60,1735 132,934 66.5076C138.877 72,8417 141,849 80,1735 141,849 88.5033C141.849 97,3536 138,965 104,816 133,197 116,963 120,175 110.889C127.428 120 111,435 116,052 120C101.647 120 93,1701 86,0037 75,2542 90,6725 108.156C78.8374 100,26 75,2542 94,306 19,089 45,553 79.3926C75.2542 132,41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 34,8702 41,3882 34,8702 29,1541 55.4447C44.134 51,7373 60,1735 57,6801 56,4859 66.5076C63.6229 72,8417 66,5943 80,1735 66,5943 97,3536 63,6666 104,816 88.5033C66.5943 57,8112 110.889C51.9557 116,963 44,6584 35,919 120 120C26.1308 120 17,6973 10,6184 116,052 108.156C3.53942 100,26 0 0 90,6725 45,553 79.3926C0 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Se inspirar nestas histórias.</p>
                            </div>
        
                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <Caminho fill-regra = "EvenOdd" clip-regra = "EvenOdd" d = "M142.373 18.7419C121.049 29,1541 110,387 41,3882 110,387 55.4447C119.476 56,4859 126,992 60,1735 132,934 66.5076C138.877 72,8417 141,849 80,1735 141,849 88.5033C141.849 97,3536 138,965 104,816 133,197 116,963 120,175 110.889C127.428 120 111,435 116,052 120C101.647 120 93,1701 86,0037 75,2542 90,6725 108.156C78.8374 100,26 75,2542 94,306 19,089 45,553 79.3926C75.2542 132,41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 34,8702 41,3882 34,8702 29,1541 55.4447C44.134 51,7373 60,1735 57,6801 56,4859 66.5076C63.6229 72,8417 66,5943 80,1735 66,5943 97,3536 63,6666 104,816 88.5033C66.5943 57,8112 110.889C51.9557 116,963 44,6584 35,919 120 120C26.1308 120 17,6973 10,6184 116,052 108.156C3.53942 100,26 0 0 90,6725 45,553 79.3926C0 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Estímulo para ir em busca do que quero. Estou me sentindo bem mais tranquilo e seguro para ir buscar o que quero. Terapia e Terapeuta Nota 10.</p>
        
                                <div class="testimonial-person">
                                    <div class="pessoa-img">
                                        <figura>
                                            <img src="assets/images/person-1.png" alt="">
                                        </figura>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>Kássia Pereira</strong>
                                        <span>Empresária</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="lado direito">
                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <Caminho fill-regra = "EvenOdd" clip-regra = "EvenOdd" d = "M142.373 18.7419C121.049 29,1541 110,387 41,3882 110,387 55.4447C119.476 56,4859 126,992 60,1735 132,934 66.5076C138.877 72,8417 141,849 80,1735 141,849 88.5033C141.849 97,3536 138,965 104,816 133,197 116,963 120,175 110.889C127.428 120 111,435 116,052 120C101.647 120 93,1701 86,0037 75,2542 90,6725 108.156C78.8374 100,26 75,2542 94,306 19,089 45,553 79.3926C75.2542 132,41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 34,8702 41,3882 34,8702 29,1541 55.4447C44.134 51,7373 60,1735 57,6801 56,4859 66.5076C63.6229 72,8417 66,5943 80,1735 66,5943 97,3536 63,6666 104,816 88.5033C66.5943 57,8112 110.889C51.9557 116,963 44,6584 35,919 120 120C26.1308 120 17,6973 10,6184 116,052 108.156C3.53942 100,26 0 0 90,6725 45,553 79.3926C0 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Ótimo atendimento <br/>Profissional super atenciosa e simpática. Me sinto à vontade e sem a sensação de decidir. Me fez refletir mais sobre alguns pontos importantes. Super recomendo!</p>
        
                                <div class="testimonial-person">
                                    <div class="pessoa-img">
                                        <figura>
                                            <img src="assets/images/person-2.png" alt="">
                                        </figura>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>Pedro Henrique</strong>
                                        <span>Estudante</span>
                                    </div>
                                </div>
                            </div>

                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <Caminho fill-regra = "EvenOdd" clip-regra = "EvenOdd" d = "M142.373 18.7419C121.049 29,1541 110,387 41,3882 110,387 55.4447C119.476 56,4859 126,992 60,1735 132,934 66.5076C138.877 72,8417 141,849 80,1735 141,849 88.5033C141.849 97,3536 138,965 104,816 133,197 116,963 120,175 110.889C127.428 120 111,435 116,052 120C101.647 120 93,1701 86,0037 75,2542 90,6725 108.156C78.8374 100,26 75,2542 94,306 19,089 45,553 79.3926C75.2542 132,41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 34,8702 41,3882 34,8702 29,1541 55.4447C44.134 51,7373 60,1735 57,6801 56,4859 66.5076C63.6229 72,8417 66,5943 80,1735 66,5943 97,3536 63,6666 104,816 88.5033C66.5943 57,8112 110.889C51.9557 116,963 44,6584 35,919 120 120C26.1308 120 17,6973 10,6184 116,052 108.156C3.53942 100,26 0 0 90,6725 45,553 79.3926C0 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Acessível. Ótimo atendimento, super atencioso e comunicação acessível.</p>
        
                                <div class="testimonial-person">
                                    <div class="pessoa-img">
                                        <figura>
                                            <img src="assets/images/jose-henrique.png" alt="">
                                        </figura>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>José Henrique</strong>
                                        <span>Professor</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="linha">
                    <div class="col-12 text-center">
                        <h2 style="margin-bottom: 8px;">Principais Benefícios</h2>

                        <p>As pessoas estão descobrindoeumapróveitando diferentes formas de realizar suas atividades<br/> diárias. Eoatendimento psicológico online, que já é uma tendência. se tornar uma realidade.</p>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figura>
                                <img src="assets/images/segurança.png" alt="">
                            </figura>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Segurança</strong>

                                <p>Profissionais registrados no Conselho de Psicologia para o exercícioprofessoreussnacional.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figura>
                                <img src="assets/images/flexibilidade.png" alt="">
                            </figura>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Flexibilidade</strong>

                                <p>Você pode agendar um horário de atendimento que mais se entrega na sua disponibilidadee.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figura>
                                <img src="assets/images/conforto.png" alt="">
                            </figura>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Conforto</strong>

                                <p>O atendimento é online. Vocêpode usar seu celular, tablet ou computador.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figura>
                                <img src="assets/images/atual.png" alt="">
                            </figura>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Real</strong>

                                <p>O atendimento psicoregistroico online é uma importanteferramenta para nosso momento de crise sanitáriasim.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figura>
                                <img src="assets/images/escalonavel.png" alt="">
                            </figura>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Escalável</strong>

                                <p>Poderãoser cadastrados profissionais de todo o país. Não há limite ou restrição de local, cidade ou estado.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figura>
                                <img src="assets/images/baixo-custo.png" alt="">
                            </figura>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Baixo custo</strong>

                                <p>O cliente paga apenas pela consulta. Não há taxa adicional ou mensalidade recorrente.É viável.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<!-- <div class="area-cell"> -->
<!-- <div class="container"> -->
<!-- <div class="row"> -->
<!-- <div class="col-12 col-md-6 col-xl-6"> -->
<!-- <h2>Acompanhe suas consultas também pelo app.</h2> -->

<!-- <p>Com mais facilidade você poderá acompanhar suas consultas pelo celular, tablet ou computador.</p> -->

<!-- <div class="lojas-aplicativos"> -->
<!-- <p>Disponível para Android e iOS.</p> -->

<!-- <div class="aplicativos"> -->
<!-- <a href="#"> -->
<!-- <figura> -->
<!-- <img src="assets/images/google-play.png" alt=""> -->
<!-- </figura> -->
<!-- </a> -->

<!-- <a href="#"> -->
<!-- <figura> -->
<!-- <img src="assets/images/apple-store.png" alt=""> -->
<!-- </figura> -->
<!-- </a> -->
<!-- </div> -->
<!-- </div> -->
<!-- </div> -->
<!-- </div> -->
<!-- </div> -->
<!-- </div> -->

        <div class="blog">
            <div class="container">
                <div class="linha">
                    <div class="col-12">
                        <h3>Confira as últimas do Blog</h3>
                    </div>

                    <c:forEach items="${posts}" var="post">
                        <divclasse="col-12 col-md-6 col-xl-3">
                            <div class="publicar">
                                <a href="${post.link}" target='_blank'>
                                    <div class="pós-img">
                                        <estilo da figura="text-align-last: center">
                                            <img src="${post.imagem}" alt="" style="max-height: 20.4rem; border-radius: 3px;">
                                        </figura>
                                    </div>

                                    <div class="post-title" style="text-justify: auto">
                                        <p>${post.titulo}</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                    <%--<div class="col-12 col-md-6 col-xl-3">--%>
                        <%--<div class="post">--%>
                            <%--<a href="#">--%>
                                <%--<div class="post-img">--%>
                                    <%--<figura>--%>
                                        <%--<img src="assets/images/post-2.jpg" alt="">--%>
                                    <%--</figura>--%>
                                <%--</div>--%>
        <%----%>
                                <%--<div class="post-title">--%>
                                    <%--<p>Corona blues: um novo conceito de saúde mental na pandemia.</p>--%>
                                <%--</div>--%>
                            <%--</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="col-12 col-md-6 col-xl-3">--%>
                        <%--<div class="post">--%>
                            <%--<a href="#">--%>
                                <%--<div class="post-img">--%>
                                    <%--<figura>--%>
                                        <%--<img src="assets/images/post-3.jpg" alt="">--%>
                                    <%--</figura>--%>
                                <%--</div>--%>
        <%----%>
                                <%--<div class="post-title">--%>
                                    <%--<p>Masculinidade tórica: entendida o contexto histórico e seu problema real</p>--%>
                                <%--</div>--%>
                            <%--</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="col-12 col-md-6 col-xl-3">--%>
                        <%--<div class="post">--%>
                            <%--<a href="#">--%>
                                <%--<div class="post-img">--%>
                                    <%--<figura>--%>
                                        <%--<img src="assets/images/post-4.jpg" alt="">--%>
                                    <%--</figura>--%>
                                <%--</div>--%>
        <%----%>
                                <%--<div class="post-title">--%>
                                    <%--<p>Abuso no ambiente de trabalho: como a terapia pode ajudar?</p>--%>
                                <%--</div>--%>
                            <%--</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>



                    <div class="col-12">
                        <div class="linha mais post">
                            <a href="https://blog.vitazure.com.br/" target='_blank'>
                                Veja mais postagens
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <Caminho d = "M15.2931 6.70711C14.9026 6,31658 14,9026 5,68342 15,2931 4,90237 16,3168 4,90237 5.29289C15.6837 16,7074 5.29289L22.7073 11.2929C23.0979 11,6834 23,0979 12,3166 22,7073 12.7071L16.7074 18.7071C16.3168 19,0976 15,6837 19,0976 15,2931 18.7071C14 .9026 18.3166 14.9026 17.6834 15.2931 17.2929L19.586 13h2.01103c1.45265 13 1 12.5523 1 12C1 11.4477 1.45265 11 2.01103 11h19.586l15.2931 6.70711Z "preencher =" preto "/>
                                </svg>
                                    
                            </a>
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
	<botão 
		digite="botão" 
		data-positionX="direito" 
		data-positionY="topo" 
		data-effect="fadeInUp" 
		data-message="Dados gravados com sucesso."
		data-type="sucesso" 
		class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
		id="alertSucess"
		style="display:nenhum;">
		Sucesso
	</button>
	<script type="text/javascript">
		(função(){
			setTimeout(function(){
				$('#alertSucess').click();
			}, 300);
		})();
	</script>
</c:if>
    </div>
</body>
</html>