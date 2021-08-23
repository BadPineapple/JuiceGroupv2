<%@ include file="/ilionnet/taglibs.jsp"%>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <a href="#">
                    <figure>
                        <img src="../../assets/images/logo-footer.png" alt="">
                    </figure>
                </a>

                <div class="footer-up-menu">
                    <ul>
                        <li><a href="https://www.vitazure.com.br/home" class="line">Home</a></li>

                        <li><a href="https://www.vitazure.com.br/como-funciona" class="line">Como funciona</a></li>

                        <li><a href="https://www.vitazure.com.br/aqui-e-para-voce" class="line">Aqui &eacute; pra voc&ecirc;</a></li>

                        <li><a href="https://www.vitazure.com.br/sou-profissional" class="line">Sou profissional</a></li>

                        <li><a href="https://www.vitazure.com.br/para-sua-empresa" class="line">Aqui &eacute; para sua empresa</a></li>


                        <li><a href="<ilion:url/>entreContato" class="line">Entre em contato</a></li>
                    </ul>
                </div>

                <div class="footer-down-menu">
                    <ul>

                        <ilion:arquivoCategoriaLista categoria="documentos" order="posicao" layout="lateral" varRetorno="art"/>



                        <c:forEach items="${art}" var="arq">

                            <c:if test="${arq.title == \"Código de ética\"}">
                                <c:set var="arqEtica" value="${arq.url}"/>
                            </c:if>

                            <c:if test="${arq.title == \"Resolução\"}">
                                <c:set var="arqResolucao" value="${arq.url}"/>
                            </c:if>

                            <c:if test="${arq.title == \"Termos e condições\"}">
                                <c:set var="arqTermos" value="${arq.url}"/>
                            </c:if>

                            <c:if test="${arq.title == \"Política de privacidade\"}">
                                <c:set var="arqPolitica" value="${arq.url}"/>
                            </c:if>

                        </c:forEach>

                        <li><a href="${arqEtica}" class="line" target="_blank">Código de ética profissional do Psicólogo</a></li>

                        <li><a href="${arqResolucao}" class="line" target="_blank">Resolução CFP nº 11/2018</a></li>

                        <li><a href="${arqTermos}" class="line" target="_blank">Termos e condições de uso</a></li>

                        <li><a href="${arqPolitica}" class="line" target="_blank">Política de privacidade</a></li>

                    </ul>
                </div>
            </div>

            <div class="col-12">
                <div class="copyright">

                    <p>&copy; 2021. Todos os direitos reservados.</p>

                    <div class="redes-sociais">
                        <a href="http://instagram.com/vitazure" target="_blank">
                            <figure>
                                <img src="../assets/images/instagram.png" alt="">
                            </figure>
                        </a>

                        <a href="http://blog.vitazure.com.br/" target="_blank">
                            <figure>
                                <img src="../assets/images/icon-blog.png" alt="">
                            </figure>
                        </a>

                        <a href="https://www.youtube.com/channel/vitazure" target="_blank">
                            <figure>
                                <img src="../assets/images/youtube.png" alt="" style="width: 30px">
                            </figure>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>

<nav id="menu">
    <ul>
        <li><a href="<ilion:url/>home">Home</a></li>
        <li><a href="<ilion:url/>como-funciona" class="line">Como funciona</a></li>
        <li><a href="<ilion:url/>aqui-e-para-voce" class="line">Aqui &eacute; pra voc&ecirc;</a></li>
        <li><a href="<ilion:url/>sou-profissional" class="line">Sou Profissional</a></li>
        <li><a href="<ilion:url/>para-sua-empresa" class="line">Aqui &eacute; para sua empresa</a></li>
        <c:if test="${pessoa == null}">
         <li><a href="<ilion:url/>entrar">Entrar</a></li>
         <li><a href="<ilion:url/>cadastre-se">Crie sua conta</a></li>
		</c:if>
		<c:if test="${pessoa != null}">
		  <a href="<ilion:url/>/vitazure/informacoes-perfil">Painel Principal</a>
		  <a href="<ilion:url/>/deslogar">Sair</a>
		</c:if>
    </ul>
</nav>

<style>
   .mm-menu a, .mm-menu a:active, .mm-menu a:hover, .mm-menu a:link, .mm-menu a:visited {
        text-decoration: none;
        color: #fff;
        background: #0097d6;
        font-size: 1.3rem;
        padding: 1.5rem;
    }

    .mm-listitem:after {
        left: 0;
    }   

    .mm-panels>.mm-panel {
        background: #0097d6;
    }
</style>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery.mmenu/8.5.6/mmenu.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
<!-- <script src="../../assets/js/calendario.js"></script> -->
<script src="https://assets.pagar.me/checkout/1.1.0/checkout.js"></script>
<script src="../../assets/js/pagar-me.js"></script>
    
<script>
    Mmenu.configs.offCanvas.page.selector = "#app";

    document.addEventListener(
        "DOMContentLoaded", () => {
            new Mmenu( "#menu", {
                extensions: ["theme-dark", "pagedim-black", "position-right"]
            });
        }
    );

    $('.slider-dias').slick({
        dots: false,
        infinite: true,
        speed: 300,
        slidesToShow: 5,
        slidesToScroll: 1,
        arrows: true,
        prevArrow: '<img class="slick-prev" src="../../assets/images/left-arrow.png" alt="">',
        nextArrow: '<img class="slick-next" src="../../assets/images/right-arrow.png" alt="">',
        responsive: [
            {
            breakpoint: 1024,
            settings: {
                slidesToShow: 3,
                slidesToScroll: 3,
                infinite: true,
                dots: true
            }
            },
            {
            breakpoint: 600,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2
            }
            },
            {
            breakpoint: 480,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1
            }
            }
        ]
    });

    $(window).scroll(function(){ scrollSite(); });
    scrollSite();

    function scrollSite(){
        if($(this).scrollTop() > 100){
            $('header').addClass('active');
        };
        if($(this).scrollTop() < 100){
            $('header').removeClass('active');
        };
    }

    $('.toggle-header').on('click', function(){
        $(this).parent().toggleClass('active');
    });

    const buttonOnline = document.querySelector('.online');
    const buttonPresencial = document.querySelector('.presencial');
    const agendaPresencial = document.querySelector('.agenda-presencial');
    const agendaOnline = document.querySelector('.agenda-online');

    buttonOnline.addEventListener('click', () => {
        buttonOnline.classList.add('active');
        buttonPresencial.classList.remove('active');

        agendaPresencial.style.display = 'none';
        agendaOnline.style.display = 'block';
    })

    buttonPresencial.addEventListener('click', function() {
        buttonPresencial.classList.add('active');
        buttonOnline.classList.remove('active');

        agendaOnline.style.display = 'none';
        agendaPresencial.style.display = 'block';

        $('.slider-dias').slick('slickNext');
    })
</script>