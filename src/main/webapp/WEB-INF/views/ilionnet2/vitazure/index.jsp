<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR">

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	<link rel="icon" type="image/png" sizes="32x32" href="../assets/images/logo-square.png">
	<title>Vitazure</title>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	
</head>

<body id="index" class="home">
	
    <div id=app>
        <jsp:include page="includes/include-header.jsp" flush="true" />
    
        <div class="banner-content banner-home">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-7 col-xl-7">
                        <h1>Seu psicólogo e terapia online!</h1>

                        <p>Faça sua consulta pelo celular, tablet ou computador, a qualquer hora com privacidade e segurança garantidas.</p>

                        <form class="form-highlight">
                            <select>
                                <option value="">Tipo de profissional</option>
                                <option value="psicologo">Psicólogo</option>
                                <option value="psicanalista">Psicanalista</option>
                            </select>

                            <select>
                                <option value="">Especialidade</option>
                                <option value="psicologico">Psicológico</option>
                                <option value="psicanalise">Psicanálise</option>
                            </select>

                            <button class="button-secundary">Buscar</button>
                        </form>
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
                            <p><span>Prático</span> <span>Seguro</span> <span>Ecônomico</span></p>

                            <h5>Conheça nossos profissionais licenciados</h5>

                            <p>Psicólogos(as), profissionais graduados e registrados no Conselho de Psicologia para o exercício profissional da Psicologia, com cadastro específico (e-Psi) para atendimento por meio de TICs. Todos passam por um processo seguro de verificação e credenciamento na plataforma, além de aderirem a rigorosos códigos de ética e sigilo.</p>

                            <p>Você é um psicólogo?</p>

                            <a href="#" class="button-purple line">
                                Junte-se a nós

                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M15.2931 6.70711C14.9026 6.31658 14.9026 5.68342 15.2931 5.29289C15.6837 4.90237 16.3168 4.90237 16.7074 5.29289L22.7073 11.2929C23.0979 11.6834 23.0979 12.3166 22.7073 12.7071L16.7074 18.7071C16.3168 19.0976 15.6837 19.0976 15.2931 18.7071C14.9026 18.3166 14.9026 17.6834 15.2931 17.2929L19.586 13H2.01103C1.45265 13 1 12.5523 1 12C1 11.4477 1.45265 11 2.01103 11H19.586L15.2931 6.70711Z" fill="#8C30F5"/>
                                </svg>
                                    
                            </a>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="parceiros text-center">
                            <p>Instituições parceiras</p>

                            <div class="partners">
                                <figure>
                                    <img src="assets/images/ufg.png" alt="">
                                </figure>

                                <figure>
                                    <img src="assets/images/puc.png" alt="">
                                </figure>

                                <figure>
                                    <img src="assets/images/google.png" alt="">
                                </figure>

                                <figure>
                                    <img src="assets/images/unip.png" alt="">
                                </figure>

                                <figure>
                                    <img src="assets/images/rede.png" alt="">
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
                                    
        
                                <p>Se inspire nestas histórias.</p>
                            </div>
        
                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Estímulo para ir em busca do que quero. Estou me sentindo bem mais tranquila e motivada para ir em busca do que quero. Terapia e Terapeuta Nota 10.</p>
        
                                <div class="testimonial-person">
                                    <div class="person-img">
                                        <figure>
                                            <img src="assets/images/person-1.png" alt="">
                                        </figure>
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
                        <div class="right-side">
                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Ótimo atendimento <br/>Profissional super atenciosa e simpática. Me sinto à vontade e sem a sensação de julgamento. Me fez refletir mais sobre alguns pontos importantes. Super recomendo!</p>
        
                                <div class="testimonial-person">
                                    <div class="person-img">
                                        <figure>
                                            <img src="assets/images/person-2.png" alt="">
                                        </figure>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>Pedro Henríque</strong>
                                        <span>Estudante</span>
                                    </div>
                                </div>
                            </div>

                            <div class="testimonial-box">
                                <svg width="143" height="120" viewBox="0 0 143 120" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M142.373 18.7419C121.049 29.1541 110.387 41.3882 110.387 55.4447C119.476 56.4859 126.992 60.1735 132.934 66.5076C138.877 72.8417 141.849 80.1735 141.849 88.5033C141.849 97.3536 138.965 104.816 133.197 110.889C127.428 116.963 120.175 120 111.435 120C101.647 120 93.1701 116.052 86.0037 108.156C78.8374 100.26 75.2542 90.6725 75.2542 79.3926C75.2542 45.553 94.306 19.089 132.41 0L142.373 18.7419ZM67.1186 18.7419C45.6196 29.1541 34.8702 41.3882 34.8702 55.4447C44.134 56.4859 51.7373 60.1735 57.6801 66.5076C63.6229 72.8417 66.5943 80.1735 66.5943 88.5033C66.5943 97.3536 63.6666 104.816 57.8112 110.889C51.9557 116.963 44.6584 120 35.919 120C26.1308 120 17.6973 116.052 10.6184 108.156C3.53942 100.26 0 90.6725 0 79.3926C0 45.553 18.9643 19.089 56.8935 0L67.1186 18.7419Z" fill="#2EC5CE"/>
                                </svg>
                                    
        
                                <p>Acessível. Ótimo atendimento, super atenciosa e comunicação acessível.</p>
        
                                <div class="testimonial-person">
                                    <div class="person-img">
                                        <figure>
                                            <img src="assets/images/person-2.png" alt="">
                                        </figure>
                                    </div>
        
                                    <div class="person-description">
                                        <strong>José Henríque</strong>
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
                        <h2 style="margin-bottom: 8px;">Principais Benefícios</h2>

                        <p>As pessoas estão descobrindo e aproveitando diferentes formas de realizar suas atividades<br/> diárias. E o atendimento psicológico online, que já é uma tendência. se tornou uma realidade.</p>
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

                                <p>Profissionais registrados no Conselho de Psicologia para o exercício profissional.</p>
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

                                <p>Você pode agendar um horário de atendimento que mais se encaixe na sua disponibilidade.</p>
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

                                <p>O atendimento é online. Você pode usar seu celular, tablet ou computador.</p>
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

                                <p>O atendimento psicológico online é uma  importante ferramenta para nosso momento de crise sanitária.</p>
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

                                <p>Poderão ser cadastrados profissionais de todo o país. Não há limite ou restrição de local, cidade ou estado.</p>
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

                                <p>O cliente paga apenas pela consulta. Não há taxa adicional ou mensalidade recorrente. É viável.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-cell">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-6 col-xl-6">
                        <h2>Acompanhe suas consultas também pelo app.</h2>

                        <p>Com mais essa facilidade você poderá acompanhar suas consultas pelo celular, tablet ou computador.</p>

                        <div class="lojas-aplicativos">
                            <p>Disponível para Android e iOS.</p>

                            <div class="aplicativos">
                                <a href="#">
                                    <figure>
                                        <img src="assets/images/google-play.png" alt="">
                                    </figure>
                                </a>

                                <a href="#">
                                    <figure>
                                        <img src="assets/images/apple-store.png" alt="">
                                    </figure>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="blog">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h3>Confira as últimas do Blog</h3>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3">
                        <div class="post">
                            <a href="#">
                                <div class="post-img">
                                    <figure>
                                        <img src="assets/images/post-1.jpg" alt="">
                                    </figure>
                                </div>
        
                                <div class="post-title">
                                    <p>Transtorno Explosivo Intermitente: causas e tratamentos dos ataques.</p>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3">
                        <div class="post">
                            <a href="#">
                                <div class="post-img">
                                    <figure>
                                        <img src="assets/images/post-2.jpg" alt="">
                                    </figure>
                                </div>
        
                                <div class="post-title">
                                    <p>Corona blues: um novo conceito de saúde mental na pandemia.</p>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3">
                        <div class="post">
                            <a href="#">
                                <div class="post-img">
                                    <figure>
                                        <img src="assets/images/post-3.jpg" alt="">
                                    </figure>
                                </div>
        
                                <div class="post-title">
                                    <p>Masculinidade tóxica: entenda o contexto histórico e seu problema atual</p>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-xl-3">
                        <div class="post">
                            <a href="#">
                                <div class="post-img">
                                    <figure>
                                        <img src="assets/images/post-4.jpg" alt="">
                                    </figure>
                                </div>
        
                                <div class="post-title">
                                    <p>Abuso no ambiente de trabalho: como a terapia pode ajudar?</p>
                                </div>
                            </a>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="more-post line">
                            <a href="#">
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

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>