<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="pt-BR"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="pt-BR" <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="pt-BR"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="pt-BR">
<!--<![endif]-->

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	
	<title>Vitazure</title>
	
	<jsp:include page="includes/include-head.jsp" flush="true" />
	
</head>

<body>
	
    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
    

        <div class="content-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Como funciona a Vitazure</h3>

                            <div class="pages-internas">
                                <a href="#">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>

                                    Home     
                                </a>

                                <span>></span>

                                <a href="#">Como funciona</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12">

                        <div class="row">
                            <div class="col-12">
                                <div class="toggle-header" style="background: none !important; padding: 0 !important">
                                    <strong style="padding-left: 0 !important; cursor: default">O que é a Vitazure?</strong>
                                </div>
                                <p style=" margin-bottom: 3rem;">A <span style="color: #0097d6;">Vitazure</span> é uma plataforma que conecta profissionais e pacientes, com o uso de recursos tecnológicos,<br> facilitando que pessoas encontrem o profissional certo para suas necessidades, com total segurança e<br> sigilo, tornando suas vidas mais felizes e saudáveis. </p>
                            </div>

                            <div class="col-12 col-md-4 col-xl-4">
                                <div class="happy-faces" style="margin-bottom: 5rem;">
                                    <div class="funciona up-photos">
                                        <figure class="funciona first-photo">
                                            <img src="../assets/images/img-1.jpg" alt="">
                                        </figure>

                                        <figure class="funciona second-photo">
                                            <img src="../assets/images/img-2.jpg" alt="">
                                        </figure>
                                    </div>

                                    <div class="funciona down-photos">
                                        <figure class="funciona third-photo">
                                            <img src="../assets/images/img-3.jpg" alt="">
                                        </figure>

                                        <figure class="funciona fourth-photo">
                                            <img src="../assets/images/img-4.jpg" alt="">
                                        </figure>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-8 col-md-8">
                                <div class="funciona-text">
                                    <div class="toggle-header" style="background: none !important; padding: 0 !important; cursor: default;">
                                        <strong style="padding-left: 0 !important">Quem faz parte da Vitazure?</strong>
                                    </div>
                                    <p>A <span style="color: #0097d6; font-style: normal">Vitazure</span> é formada por pessoas que acreditam e trabalham para oferecer as melhores condições de bem-estar, equilíbrio emocional e físico para as pessoas, possibilitando acesso à saúde por meio de plataforma tecnológica simples, segura, acessível, totalmente online, que conecta pacientes a profissionais especializados em qualquer lugar e a qualquer hora.
                                    </p>

                                    <span style="color: #0097d6; font-style: normal">Acreditamos que pessoas integrais, fazem escolhas mais saudáveis e transformam o mundo em um lugar melhor para viver. E esse é o nosso propósito.</span>
                                </div>
                            </div>
                        </div>

                        <div class="buttons-internas">
                            <a onclick="profissionalAtivo()" class="button-secundary" id="button-profissional">Sou profissional</a>

                            <a onclick="pacienteAtivo()" class="button-white" id="button-paciente">Sou paciente</a>
                        </div>

                        <!-- Inicio como funciona profissional -->

                        <div id="funciona-profissional" class="painel-ativo">

                            <%--<div class="match-toggle">--%>
                                <%--<div class="toggle-header">--%>
                                    <%--<strong>O que é a Vitazure?</strong>--%>
                                <%--</div>--%>
                                <%--<div class="toggle-body vitazure">--%>

                                    <%--<p>A Vitazure é uma plataforma queconecta profissionais epacientes, com o uso derecursostecnológicos. Facilitando que pessoas encontrarem o profissional certo para suas necessidades, com total segurança e sigilo, tornando suas vidas mais felizes e saudáveis.</p>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="match-toggle">--%>
                                <%--<div class="toggle-header">--%>
                                    <%--<strong>Quem faz parte da equipe Vitazure?</strong>--%>
                                <%--</div>--%>
                                <%--<div class="toggle-body vitazure">--%>

                                    <%--<p>A Vitazure é formada por pessoas que acreditam e trabalham para oferecer as melhores condições de bem-estar, equilíbrio emocional e físico para as pessoas, possibilitando acesso à saúde por meio de plataforma tecnológica simples, segura, acessível, totalmente online, que conecta pacientes a profissionais especializados em qualquer lugar e a qualquer hora.</p><p>Acreditamos que pessoas integrais, fazem escolhas mais saudáveis e transformam o mundo em um lugar melhor para viver. E esse é o nosso propósito.</p>--%>

                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como me cadastrar?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Se você está buscando atendimento psicológico, basta clicar no botão abaixo “Quero me cadastrar” e efetuar o seu cadastro. Isso leva menos de 3 minutos. Agora, se você é Psicólogo, basta entrar em “Sou profissional”, no menu superior, efetuar seu cadastro e aguardar o contato de nosso departamento comercial em até 12 horas, simples assim.</p>

                                    <a href="http://vitazure.com.br/cadastre-se" class="button-secundary">Quero me cadastrar</a>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>A plataforma é segura?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>É muito segura. Os dados informados por você são tratados de forma confidencial, são criptografados, ou seja, não podem ser lidos por outros recursos tecnológicos. Usamos certificado de segurança SSL, isso dá a segurança para a plataforma, você pode verificar na barra de endereços o conjunto de caracteres ”https”, indicando o uso do recurso.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Vou receber lembretes dos meus agendamentos? </strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Sim. Será enviado e-mail e SMS, para o paciente e profissional, com os detalhes do agendamento, logo após a confirmação da autorização de pagamento, assim fica garantido o recebimento por parte do psicólogo</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Serei avaliado pelos meus pacientes?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Os profissionais serão avaliados após a realização da consulta/sessão, o paciente será convidado a avaliar o serviço prestado. Para ser bem avaliado é necessário prestar um atendimento de qualidade.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Qual o valor da consulta?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Os valores das consultas/sessões são definidos pelos profissionais. Pesquise o valor que considere mais adequado para você. Normalmente o custo de uma consulta aqui na <span style="color: #0097d6;">Vitazure</span> é menor do que o cobrado em um consultório convencional.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como eu recebo o valor da consulta?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Basta solicitar o resgate do valor no site da plataforma pagadora (pagar.me), observando que os prazos para resgate variam de acordo com a forma de pagamento do paciente, cartão de crédito 30 dias após o pagamento. O profissional tem acesso a área do consultório virtual, com informações dos agendamentos e valores das consultas/sessões pagas.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como acontece a consulta online?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>A consulta/sessão acontece como uma videoconferência, para iniciá-la basta entrar na plataforma e você será direcionado para a área do consultório virtual. Outro modo de acesso é pelo link enviado por e-mail, 30 minutos do início da consulta/sessão.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como o paciente me encontra?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Para que você apareça nas buscas é fundamental que tenha agendas e horários cadastrados na plataforma. Ter um perfil bem elaborado será importante para que seu paciente decida por contratá-lo. O paciente pode fazer buscas para consulta/sessão online ou presencial.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Qual diferença de agenda online para agenda presencial?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Será necessário cadastrar agendas distintas, para aparecer nas buscas de atendimento presencial é necessário o cadastro de uma agenda e para aparecer nas buscas de atendimento online é necessário o cadastro de outra agenda.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>As consultas são gravadas?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Não. Nenhuma consulta é gravada em nossa plataforma, somente você e o paciente tem acesso ao consultório no momento da consulta.</p>

                                </div>
                            </div>


                            <!-- End como funciona profissional -->
                        </div>

                        <!-- Inicio como funciona paciente -->

                        <div id="funciona-paciente" class="painel">
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como me cadastrar?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Se você está buscando atendimento psicológico, basta clicar no botão abaixo “Quero me cadastrar” e efetuar o seu cadastro. Isso leva menos de 3 minutos. Agora, se você é Psicólogo, basta entrar em “Sou profissional”, no menu superior, efetuar seu cadastro e aguardar o contato de nosso departamento comercial em até 12 horas, simples assim.</p>

                                    <a href="http://vitazure.com.br/cadastre-se" class="button-secundary">Quero me cadastrar</a>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>A plataforma é segura?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>É muito segura. Os dados informados por você são tratados de forma confidencial, são criptografados, ou seja, não podem ser lidos por outros recursos tecnológicos. Usamos certificado de segurança SSL, isso dá a segurança para a plataforma, você pode verificar na barra de endereços o conjunto de caracteres ”https”, indicando o uso do recurso.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Vou receber lembretes dos meus agendamentos? </strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Sim. Será enviado e-mail e SMS, para o paciente e profissional, com os detalhes do agendamento, logo após a confirmação da autorização de pagamento, assim fica garantido o recebimento por parte do psicólogo.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Qual o valor da consulta?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Os valores da consulta são definidos pelos profissionais, podem variar de acordo com cada profissional.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como é realizado o pagamento?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>O pagamento é realizado através de cartão de crédito ou boleto bancário dentro da plataforma, com total segurança. Os dados de pagamento, não são armazenados pela plataforma, são usados somente no momento da transação pelo site da Pagar.me.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Aceitam plano de saúde?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>No momento não atendemos nenhum plano de saúde. No entanto, você pode consultar o contrato com seu plano de saúde, para saber mais sobre reembolso de valores.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>A informação do meu cartão de crédito está segura?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Está segura. Os dados do seu cartão de crédito não são armazenados pela plataforma, são transmitidos de forma criptografada com segurança para as verificações pela administradora do cartão. Nosso sistema de pagamento é utilizado por grandes corporações, garantindo segurança em todas as transações financeiras.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>E se eu precisar de um atendimento de emergência?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>A Vitazure não oferece tratamento ou aconselhamento imediato para pessoas em crise suicida, em caso de crise ligue para 141 (CVV) ou acesse ww.cvv.org.br, em caso de emergência procure atendimento em um hospital mais próximo.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>E se eu estiver descontente com o meu psicólogo?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Havendo algum descontentamento com o seu profissional, basta entrar em contato com nossa equipe de atendimento por meio do chat da plataforma. Você será atendido rapidamente, queremos o seu bem-estar.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como funcionam as consultas/sessões online?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>As consultas/sessões acontecem por meio do consultório virtual da plataforma Vitazure, são realizadas de forma online. Atendemos os padrões de segurança recomendados, diferentemente de plataformas populares como WhatsApp, Skype e outras, que não possuem proteção sigilo de pacientes. O uso do consultório virtual não exige nenhuma instalação de aplicativo, você consegue entrar diretamente por um link enviado no seu e-mail 15 minutos antes da consulta/sessão ou também diretamente pela plataforma, fazendo seu login.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Posso realizar minha consulta/sessão pelo celular?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Pode. A consulta/sessão pode ser realizada pelo computador ou celular.</p>

                                </div>
                            </div>

                            <!-- End como funciona paciente -->
                        </div>
                        <div class="img-responsavel">
                            <figure>
                                <img src="../assets/images/responsavel-tecnico.jpg" alt="">
                            </figure>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>