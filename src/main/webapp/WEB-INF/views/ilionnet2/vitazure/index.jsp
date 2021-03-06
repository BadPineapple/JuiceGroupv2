<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="buscaProfissionalApp" ng-controller="BuscaProfissionalController">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/buscaProfissional.js"></script>
	
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

/*     @media (max-width: 768px) { */
/*         .privacy-policy-modal { */
/*             display: grid!important; */
/*             grid-template-areas: */
/*                 "text" */
/*                 "button" */
/*         ; */
/*             grid-template-rows: 1fr 1fr; */
/*             height: 200px; */
/*             padding: 0rem 2rem; */
/*         } */

/*         .privacy-policy-text { */
/*             padding: 0 .5rem; */
/*             width: 100%!important; */
/*         } */

/*         .button-accept, */
/*         .button-accept:hover { */
/*             width: 100%!important; */
/*         } */

/*         @media (max-width: 501px) { */
/*             .privacy-policy-modal { */
/*                 height: 270px; */
/*             } */
/*         } */

/*         @media (max-width: 608px) and (min-width: 500px) { */
/*             .privacy-policy-modal { */
/*                 height: 220px; */
/*             } */
/*         } */
    }
</style>

<div class="privacy-policy-modal show-policy">
   <div class="privacy-policy"> 
    <div class="privacy-policy-text">
        <ilion:arquivoCategoriaLista categoria="documentos" order="posicao" layout="lateral" varRetorno="art"/>
        <c:forEach items="${art}" var="arq">
            <c:if test="${arq.title == \"Pol??tica de privacidade\"}">
                <c:set var="arqPolitica" value="${arq.url}"/>
            </c:if>
        </c:forEach>
         <span class="privacy-policy-text-span">N??s usamos cookies e outras tecnologias semelhantes para melhorar a sua experi??ncia em nossos servi??os, personalizar publicidade e recomendar conte??do de seu interesse. Ao utilizar nossos servi??os, voc?? concorda com tal monitoramento descrito em nossa??<a href="${arqPolitica}" target="_blank">Pol??tica de Privacidade</a></span>
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
                    <div class="col-12 col-md-7 col-xl-7">
                        <h1 class="title-banner">Seus sentimentos merecem toda nossa aten????o, aqui na <span style="color: #0097d6;">Vitazure</span> voc?? vai encontrar quem te entende.</h1>

                        <p>Fac??a sua consulta pelo celular, tablet ou computador, a qualquer hora com privacidade e seguranc??a garantidas.</p>

                        <form ng-submit="consultarProfissionalExterna()"  class="form-highlight">
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
		<div class="blog">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3>Confira as ??ltimas do Blog</h3>
                    </div>
                    <c:forEach items="${posts}" var="post">
                        <div class="col-12 col-md-6 col-xl-3">
                            <div class="post">
                                <a href="${post.link}" target='_blank'>
                                    <div class="post-img">
                                        <figure style=" text-align-last: center">
                                            <img src="${post.imagem}" alt="" style="max-height: 20.4rem; border-radius: 3px;">
                                        </figure>
                                    </div>

                                    <div class="post-title" style="text-justify: auto">
                                        <p>${post.titulo}</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-12">
                        <div class="more-post line">
                            <a href="https://blog.vitazure.com.br/" target='_blank'>
                                Veja mais posts
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M15.2931 6.70711C14.9026 6.31658 14.9026 5.68342 15.2931 5.29289C15.6837 4.90237 16.3168 4.90237 16.7074 5.29289L22.7073 11.2929C23.0979 11.6834 23.0979 12.3166 22.7073 12.7071L16.7074 18.7071C16.3168 19.0976 15.6837 19.0976 15.2931 18.7071C14.9026 18.3166 14.9026 17.6834 15.2931 17.2929L19.586 13H2.01103C1.45265 13 1 12.5523 1 12C1 11.4477 1.45265 11 2.01103 11H19.586L15.2931 6.70711Z" fill="black"/>
                                </svg>
                                    
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="happy-faces">
                            <div class="up-photos">
                                <figure class="first-photo">
                                    <img src="assets/images/img-1.jpg" alt="">
                                </figure>

                                <figure class="second-photo">
                                    <img src="assets/images/img-2.jpg" alt="">
                                </figure>
                            </div>

                            <div class="down-photos">
                                <figure class="third-photo">
                                    <img src="assets/images/img-3.jpg" alt="">
                                </figure>

                                <figure class="fourth-photo">
                                    <img src="assets/images/img-4.jpg" alt="">
                                </figure>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="join-us">
                            <p><span>Pr??tico</span> <span>Seguro</span> <span>Ec??nomico</span></p>

                            <h5>Conhe??a nossa rede de profissionais licenciados</h5>

                            <p>Nossa rede de profissionais cobre uma variedade de especialidades para atender ??s suas necessidades espec??ficas. S??o Profissionais graduados e registrados no Conselho Federal de Psicologia, autorizados (e-Psi) a prestarem servi??os por meio de Tecnologias da Informa????o e da Comunica????o (TIC). S??o Profissionais que passam por processo criterioso de credenciamento, sujeitos a rigoroso c??digo de ??tica e sigilo.</p>

                            <p>Voc?? ?? um psic??logo?</p>

                            <a href="<ilion:url/>cadastre-se" class="button-purple line">
                                Junte-se a n??s

                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M15.2931 6.70711C14.9026 6.31658 14.9026 5.68342 15.2931 5.29289C15.6837 4.90237 16.3168 4.90237 16.7074 5.29289L22.7073 11.2929C23.0979 11.6834 23.0979 12.3166 22.7073 12.7071L16.7074 18.7071C16.3168 19.0976 15.6837 19.0976 15.2931 18.7071C14.9026 18.3166 14.9026 17.6834 15.2931 17.2929L19.586 13H2.01103C1.45265 13 1 12.5523 1 12C1 11.4477 1.45265 11 2.01103 11H19.586L15.2931 6.70711Z" fill="#8C30F5"/>
                                </svg>
                                    
                            </a>
                        </div>
                    </div>


                    <div class="col-12">
                        <div class="parceiros text-center">
                            <p>Institui????es parceiras</p>

                            <div class="partners">
                                <figure>
                                    <img src="assets/images/inpro.png" alt="Instituto Proje????o">
                                </figure>

                                <figure>
                                    <img src="assets/images/ilion_marca.png" style="width: 160px;" alt="Ilion">
                                </figure>

                                <figure>
                                    <img src="assets/images/caderno_virtual.png" style="width: 160px;" alt="Caderno virtual">
                                </figure>
                                
                                <figure>
                                    <img src="assets/images/believe.jpg" style="width: 160px;" alt="Believe">
                                </figure>

                                <figure>
                                    <img src="assets/images/cardioHortiz.png" style="width: 160px;" alt="Cardio">
                                </figure>

                                <figure style="position: relative;top: -32px;">
                                    <img src="assets/images/logo-hospital-goiania-leste.png" style="width: 160px;" alt="Hospital Goi??nia leste">
                                </figure>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="testimonial">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="left-side">
                            <div class="testimonial-title">
                                <h2>Depoimentos de  alguns Clientes</h2>
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Se inspire nestas hist??rias.</p>
                            </div>
        
                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Est??mulo para ir em busca do que quero. Estou me sentindo bem mais tranquila e motivada para ir em busca do que quero. Terapia e Terapeuta Nota 10.</p>
        
                                <div class="testimonial-person">
                                    <div class="person-img">
                                        <figure>
                                            <img src="assets/images/person-1.png" alt="">
                                        </figure>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>K??ssia Pereira</strong>
                                        <span>Empres??ria</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-6">
                        <div class="right-side">
                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>??timo atendimento <br/>Profissional super atenciosa e simp??tica. Me sinto ?? vontade e sem a sensa????o de julgamento. Me fez refletir mais sobre alguns pontos importantes. Super recomendo!</p>
        
                                <div class="testimonial-person">
                                    <div class="person-img">
                                        <figure>
                                            <img src="assets/images/person-2.png" alt="">
                                        </figure>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>Pedro Henr??que</strong>
                                        <span>Estudante</span>
                                    </div>
                                </div>
                            </div>

                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Acess??vel. ??timo atendimento, super atenciosa e comunica????o acess??vel.</p>
        
                                <div class="testimonial-person">
                                    <div class="person-img">
                                        <figure>
                                            <img src="assets/images/jose-henrique.png" alt="">
                                        </figure>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>Jos?? Henr??que</strong>
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
                <div class="row">
                    <div class="col-12 text-center">
                        <h2 style="margin-bottom: 8px;">Principais Benef??cios</h2>

                        <p>As pessoas est??o descobrindo e aproveitando diferentes formas de realizar suas atividades<br/> di??rias. E o atendimento psicol??gico online, que j?? ?? uma tend??ncia. se tornou uma realidade.</p>
                    </div>

                    <div class="col-12 col-md-4 col-xl-4">
                        <div class="beneficios">
                            <div class="beneficios-img">
                            <figure>
                                <img src="assets/images/seguran??a.png" alt="">
                            </figure>         
                            </div>

                            <div class="beneficios-title">
                                <strong>Seguran??a</strong>

                                <p>Profissionais registrados no Conselho de Psicologia para o exerc??cio profissional.</p>
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

                                <p>Voc?? pode agendar um hor??rio de atendimento que mais se encaixe na sua disponibilidade.</p>
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

                                <p>O atendimento ?? online. Voc?? pode usar seu celular, tablet ou computador.</p>
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

                                <p>O atendimento psicol??gico online ?? uma  importante ferramenta para nosso momento de crise sanit??ria.</p>
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
                                <strong>Escalon??vel</strong>

                                <p>Poder??o ser cadastrados profissionais de todo o pa??s. N??o h?? limite ou restri????o de local, cidade ou estado.</p>
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

                                <p>O cliente paga apenas pela consulta. N??o h?? taxa adicional ou mensalidade recorrente. ?? vi??vel.</p>
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