<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
</head>

<body id="index" class="home">
	
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Assinatura</h3>
                        <p>Escolha um plano que melhor atende às suas expectativas.</p>
                        <div class="planos">
                            <div class="row">
                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="box-planos">
                                        <div class="planos-title">
                                            <strong>Plano Trimestral</strong>
                                            <p class="price">R$ <span>450,00</span></p>
                                            <p>Cobrado mensalmente no<br/> cartão de crédito.</p>
                                        </div>
                                        <div class="planos-description">
                                            <ul>
                                                <li>Anúncio de perfil no portal</li>

                                                <li>Faça atendimentos  ilimitados</li>

                                                <li>Atendimento online por vídeo</li>

                                                <li>Controle de seus atendimentos</li>

                                                <li>Acompanhamento de recebíveis</li>
                                            </ul>
                                            <button onclick="chamada(1, 'Plano Trimestral')" class="button-secundary">Comece Agora</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="box-plano plano-semestral">
                                        <div class="planos-title semestral">
                                            <strong>Plano semestral</strong>
                                            <p class="price">R$ <span>800,00</span></p>
                                            <p>Pague uma vez e tenha acesso <br/> por seis meses com desconto de 11%.</p>
                                        </div>
                                        <div class="planos-description">
                                            <ul>
                                                <li>Anúncio de perfil no portal</li>
                                                <li>Faça atendimentos  ilimitados</li>
                                                <li>Atendimento online por vídeo</li>
                                                <li>Controle de seus atendimentos</li>
                                                <li>Acompanhamento de recebíveis</li>
                                            </ul>
                                            <button onclick="chamada(1, 'Plano Semestral')" class="button-secundary">Comece Agora</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 col-xl-4">
                                    <div class="box-planos">
                                        <div class="planos-title">
                                            <strong>Plano Anual</strong>
                                            <p class="price">R$ <span>1.500,00</span></p>
                                            <p>Pague uma vez e tenha acesso<br/> por um ano e desconto de 16%.</p>
                                        </div>
                                        <div class="planos-description">
                                            <ul>
                                                <li>Anúncio de perfil no portal</li>
                                                <li>Faça atendimentos  ilimitados</li>
                                                <li>Atendimento online por vídeo</li>
                                                <li>Controle de seus atendimentos</li>
                                                <li>Acompanhamento de recebíveis</li>
                                            </ul>
                                            <button onclick="chamada(1, 'Plano Anual')" class="button-secundary">Comece Agora</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>