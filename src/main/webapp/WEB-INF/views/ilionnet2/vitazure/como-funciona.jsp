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
                                    <strong style="padding-left: 0 !important; cursor: default">O que ?? a Vitazure?</strong>
                                </div>
                                <p style=" margin-bottom: 3rem;">A <span style="color: #0097d6;">Vitazure</span> ?? uma plataforma que conecta profissionais e pacientes, com o uso de recursos tecnol??gicos,<br> facilitando que pessoas encontrem o profissional certo para suas necessidades, com total seguran??a e<br> sigilo, tornando suas vidas mais felizes e saud??veis. </p>
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
                                    <p>A <span style="color: #0097d6; font-style: normal">Vitazure</span> ?? formada por pessoas que acreditam e trabalham para oferecer as melhores condi????es de bem-estar, equil??brio emocional e f??sico para as pessoas, possibilitando acesso ?? sa??de por meio de plataforma tecnol??gica simples, segura, acess??vel, totalmente online, que conecta pacientes a profissionais especializados em qualquer lugar e a qualquer hora.
                                    </p>

                                    <span style="color: #0097d6; font-style: normal">Acreditamos que pessoas integrais, fazem escolhas mais saud??veis e transformam o mundo em um lugar melhor para viver. E esse ?? o nosso prop??sito.</span>
                                </div>
                            </div>
                        </div>

                        <div style="padding-top: 25px;">
                            <p>Ainda sentiu falta de algum esclarecimento?<br>Navegue pelas perguntas mais frequentes para saber mais.</p>
                        </div>

                        <div class="buttons-internas">
                            <a onclick="profissionalAtivo()" class="button-secundary" id="button-profissional">Sou profissional</a>

                            <a onclick="pacienteAtivo()" class="button-white" id="button-paciente">Sou paciente</a>
                        </div>

                        <!-- Inicio como funciona profissional -->

                        <div id="funciona-profissional" class="painel-ativo">

                            <%--<div class="match-toggle">--%>
                                <%--<div class="toggle-header">--%>
                                    <%--<strong>O que ?? a Vitazure?</strong>--%>
                                <%--</div>--%>
                                <%--<div class="toggle-body vitazure">--%>

                                    <%--<p>A Vitazure ?? uma plataforma queconecta profissionais epacientes, com o uso derecursostecnol??gicos. Facilitando que pessoas encontrarem o profissional certo para suas necessidades, com total seguran??a e sigilo, tornando suas vidas mais felizes e saud??veis.</p>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="match-toggle">--%>
                                <%--<div class="toggle-header">--%>
                                    <%--<strong>Quem faz parte da equipe Vitazure?</strong>--%>
                                <%--</div>--%>
                                <%--<div class="toggle-body vitazure">--%>

                                    <%--<p>A Vitazure ?? formada por pessoas que acreditam e trabalham para oferecer as melhores condi????es de bem-estar, equil??brio emocional e f??sico para as pessoas, possibilitando acesso ?? sa??de por meio de plataforma tecnol??gica simples, segura, acess??vel, totalmente online, que conecta pacientes a profissionais especializados em qualquer lugar e a qualquer hora.</p><p>Acreditamos que pessoas integrais, fazem escolhas mais saud??veis e transformam o mundo em um lugar melhor para viver. E esse ?? o nosso prop??sito.</p>--%>

                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como me cadastrar?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Se voc?? est?? buscando atendimento psicol??gico, basta clicar no bot??o abaixo ???Quero me cadastrar??? e efetuar o seu cadastro. Isso leva menos de 3 minutos. Agora, se voc?? ?? Psic??logo, basta entrar em ???Sou profissional???, no menu superior, efetuar seu cadastro e aguardar o contato de nosso departamento comercial em at?? 12 horas, simples assim.</p>

                                    <a href="http://vitazure.com.br/cadastre-se" class="button-secundary">Quero me cadastrar</a>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>A plataforma e?? segura?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>?? muito segura. Os dados informados por voc?? s??o tratados de forma confidencial, s??o criptografados, ou seja, n??o podem ser lidos por outros recursos tecnol??gicos. Usamos certificado de seguran??a SSL, isso d?? a seguran??a para a plataforma, voc?? pode verificar na barra de endere??os o conjunto de caracteres ???https???, indicando o uso do recurso.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Vou receber lembretes dos meus agendamentos? </strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Sim. Ser?? enviado e-mail e SMS, para o paciente e profissional, com os detalhes do agendamento, logo ap??s a confirma????o da autoriza????o de pagamento, assim fica garantido o recebimento por parte do psic??logo</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Serei avaliado pelos meus pacientes?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Os profissionais ser??o avaliados ap??s a realiza????o da consulta/sess??o, o paciente ser?? convidado a avaliar o servi??o prestado. Para ser bem avaliado ?? necess??rio prestar um atendimento de qualidade.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Qual o valor da consulta?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Os valores das consultas/sess??es s??o definidos pelos profissionais. Pesquise o valor que considere mais adequado para voc??. Normalmente o custo de uma consulta aqui na <span style="color: #0097d6;">Vitazure</span> ?? menor do que o cobrado em um consult??rio convencional.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como eu recebo o valor da consulta?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Basta solicitar o resgate do valor no site da plataforma pagadora (pagar.me), observando que os prazos para resgate variam de acordo com a forma de pagamento do paciente, cart??o de cr??dito 30 dias ap??s o pagamento. O profissional tem acesso a ??rea do consult??rio virtual, com informa????es dos agendamentos e valores das consultas/sess??es pagas.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como acontece a consulta online?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>A consulta/sess??o acontece como uma videoconfer??ncia, para inici??-la basta entrar na plataforma e voc?? ser?? direcionado para a ??rea do consult??rio virtual. Outro modo de acesso ?? pelo link enviado por e-mail, 30 minutos do in??cio da consulta/sess??o.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como o paciente me encontra?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Para que voc?? apare??a nas buscas ?? fundamental que tenha agendas e hor??rios cadastrados na plataforma. Ter um perfil bem elaborado ser?? importante para que seu paciente decida por contrat??-lo. O paciente pode fazer buscas para consulta/sess??o online ou presencial.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Qual diferen??a de agenda online para agenda presencial?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Ser?? necess??rio cadastrar agendas distintas, para aparecer nas buscas de atendimento presencial ?? necess??rio o cadastro de uma agenda e para aparecer nas buscas de atendimento online ?? necess??rio o cadastro de outra agenda.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>As consultas s??o gravadas?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>N??o. Nenhuma consulta ?? gravada em nossa plataforma, somente voc?? e o paciente tem acesso ao consult??rio no momento da consulta.</p>

                                </div>
                            </div>
                            
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Quais recursos ser??o necess??rios para meu atendimento online?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Se for usar um computador desktop ou notebook, sugerimos observar os seguintes equipamentos:</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;1- Webcam - recomendamos uma c??mara (1280 x 720 pixels) com sistema HD de 720p??no m??nimo;
    									</br>&nbsp;&nbsp;&nbsp;&nbsp;2- Microfone de Lapela;
    									</br>&nbsp;&nbsp;&nbsp;&nbsp;3- ??Fone de ouvido.	</p>
    								<p>Os notebooks mais recentes (2021) j?? possuem os componentes acima, exce????o do fone de ouvido, recomenda????o do seu uso tamb??m nos notebooks.
									</br>Se voc?? pensa em usar o aparelho celular, observe os seguintes equipamentos:</p>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;1- ??Fone de ouvido;
    								</br>&nbsp;&nbsp;&nbsp;&nbsp;2- Anel de led (Ring Light) ??? em ambientes com pouca luz, recomendamos o uso das luzes de led.</p>		
                                	<p>Link de comunica????o (internet):
									</br>Recomendamos que o link seja de no m??nimo 15MB;
									</br>N??o ?? recomendado uso de links de dados m??veis, seu uso deve ser somente em caso de impossibilidade extrema de contar com recursos aqui recomendados.</p>
                                </div>
                            </div>
							
							 <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Que cuidados preciso ter para o atendimento online?</strong>
                                </div>
                                <div class="toggle-body vitazure">
                                    <p>Diferentemente do atendimento presencial, no atendimento online a(o) Psic??loga(o) precisa considerar, al??m do seu ambiente f??sico e virtual, o ambiente f??sico e virtual da(o) cliente em conex??o. Nessa modalidade de atendimento a garantia de sigilo profissional tamb??m envolve a(o) cliente, que deve ser orientada(o) quanto aos cuidados a serem adotados como: estar em um ambiente da casa que n??o tenha interfer??ncias externas (excesso de ru??do, animais de estima????o, circula????o de outras pessoas, etc.), ter um bom antiv??rus e internet de qualidade e fazer utiliza????o de fones de ouvido.</p>
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

                                    <p>Se voc?? est?? buscando atendimento psicol??gico, basta clicar no bot??o abaixo ???Quero me cadastrar??? e efetuar o seu cadastro. Isso leva menos de 3 minutos. Agora, se voc?? ?? Psic??logo, basta entrar em ???Sou profissional???, no menu superior, efetuar seu cadastro e aguardar o contato de nosso departamento comercial em at?? 12 horas, simples assim.</p>

                                    <a href="http://vitazure.com.br/cadastre-se" class="button-secundary">Quero me cadastrar</a>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>A plataforma e?? segura?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>?? muito segura. Os dados informados por voc?? s??o tratados de forma confidencial, s??o criptografados, ou seja, n??o podem ser lidos por outros recursos tecnol??gicos. Usamos certificado de seguran??a SSL, isso d?? a seguran??a para a plataforma, voc?? pode verificar na barra de endere??os o conjunto de caracteres ???https???, indicando o uso do recurso.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Vou receber lembretes dos meus agendamentos? </strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Sim. Ser?? enviado e-mail e SMS, para o paciente e profissional, com os detalhes do agendamento, logo ap??s a confirma????o da autoriza????o de pagamento, assim fica garantido o recebimento por parte do psic??logo.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Qual o valor da consulta?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Os valores da consulta s??o definidos pelos profissionais, podem variar de acordo com cada profissional.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como e?? realizado o pagamento?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>O pagamento ?? realizado atrav??s de cart??o de cr??dito ou boleto banc??rio dentro da plataforma, com total seguran??a. Os dados de pagamento, n??o s??o armazenados pela plataforma, s??o usados somente no momento da transa????o pelo site da Pagar.me.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Aceitam plano de sau??de?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>No momento n??o atendemos nenhum plano de sa??de. No entanto, voc?? pode consultar o contrato com seu plano de sa??de, para saber mais sobre reembolso de valores.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>A informac??a??o do meu carta??o de cre??dito esta?? segura?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Est?? segura. Os dados do seu cart??o de cr??dito n??o s??o armazenados pela plataforma, s??o transmitidos de forma criptografada com seguran??a para as verifica????es pela administradora do cart??o. Nosso sistema de pagamento ?? utilizado por grandes corpora????es, garantindo seguran??a em todas as transa????es financeiras.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>E se eu precisar de um atendimento de emerge??ncia?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>A Vitazure n??o oferece tratamento ou aconselhamento imediato para pessoas em crise suicida, em caso de crise ligue para 188 (CVV) ou acesse ww.cvv.org.br, em caso de emerg??ncia procure atendimento em um hospital mais pr??ximo.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>E se eu estiver descontente com o meu psic??logo?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Havendo algum descontentamento com o seu profissional, basta entrar em contato com nossa equipe de atendimento por meio do chat da plataforma. Voc?? ser?? atendido rapidamente, queremos o seu bem-estar.</p>

                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Como funcionam as consultas/sess??es online?</strong>
                                </div>
                                <div class="toggle-body vitazure">
                                    <p>As consultas/sess??es acontecem por meio do consult??rio virtual da plataforma Vitazure, s??o realizadas de forma online. Atendemos os padr??es de seguran??a recomendados, diferentemente de plataformas populares como WhatsApp, Skype e outras, que n??o possuem prote????o sigilo de pacientes. O uso do consult??rio virtual n??o exige nenhuma instala????o de aplicativo, voc?? consegue entrar diretamente pela plataforma, fazendo seu login.</p>
                                </div>
                            </div>

                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Posso realizar minha consulta/sess??o pelo celular?</strong>
                                </div>
                                <div class="toggle-body vitazure">

                                    <p>Pode. A consulta/sess??o pode ser realizada pelo computador ou celular.</p>

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