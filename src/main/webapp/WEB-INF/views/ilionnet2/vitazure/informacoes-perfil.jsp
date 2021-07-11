<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	
	<title>Vitazure</title>
	
	<jsp:include page="includes/include-head.jsp" flush="true" />
	
</head>

<body>
	
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />

        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />

        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Meu cadastro</h3>

                        <p>Preencha todos os tópicos para seu Perfil  Profissional</p>
                    </div>

                    <div class="col-12 col-md-8 offset-md-2 col-xl-8 offset-xl-2">
                        <form class="form-default">
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Perfil Profissional</strong>

                                    <div class="duvidas">
                                        <span>?</span>
                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body">
                                    <p>Preencha aqui os dados importantes para a exibição do seu perfil. Essas informações serão apresentadas para o paciente.</p>
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="perfil">
                                                <label for="avatar" class="photo-perfil">
                                                
                                                    <input type="file" name="avatar" id="avatar" style="display: none;">
                                                    <figure>
                                                        <img src="../assets/images/perfil.png" alt="">
                                                    </figure>

                                                    <p>Alterar foto</p>
                                                    
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>CPF</label>
                                                <input type="text" required/>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Data de Nascimento</label>
                                                <input type="text" required/>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Documento do (CRP/CRM)</label>
                                                <input type="text" required/>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Cadastro do E-Psi</label>
                                                <input type="text" required/>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Título Profissional</label>
                                                <input type="text" required/>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tipo Profissional</label>
                                                <select>
                                                    <option></option>
                                                    <option value="psicólogo">psicólogo</option>
                                                    <option value="psicanalista">psicanalista</option>
                                                    <option value="terapeuta">terapeuta</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Especialidade</label>
                                                <select>
                                                    <option></option>
                                                    <option value="relacionamento">relacionamento</option>
                                                    <option value="Psicopedagogia">Psicopedagogia</option>
                                                    <option value="Psicomotricidade">Psicomotricidade</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Temas de Trabalho</label>
                                                <select style="background: none;">
                                                    <option></option>
                                                    <option value="relacionamento">relacionamento</option>
                                                    <option value="Psicopedagogia">Psicopedagogia</option>
                                                    <option value="Psicomotricidade">Psicomotricidade</option>
                                                </select>

                                                <!-- <div class="temas-title select">
                                                    <p>Adicionar área</p>
                                                    <figure>
                                                        <img src="images/plus.png" alt="">
                                                    </figure>
                                                </div> -->

                                                <strong>
                                                    Acompanhamento psicológico, Ansiedade, Anorexia nervisa,  Autismo, Autoestima, Conflitos amorosos, Conflitos familiáres, Depressão.
                                                </strong>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tempo de duração do atendimento</label>
                                                <select>
                                                    <option></option>
                                                    <option value="50 minutos">50 minutos</option>
                                                    <option value="60 minutos">60 minutos</option>
                                                    <option value="70 minutos">70 minutos</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Bibliografia</label>
                                                <textarea cols="20" rows="5" placeholder="Informe aqui sua vida profissional. Procure ser mais claro possível." style="color: #A6A6A6"></textarea>
                                            </div>
                                        </div>

                                        <div class="col-12" style="margin-bottom: 3rem">
                                            <div class="input-title">
                                                <p>Formação</p>

                                                <!-- <div class="temas-title">
                                                    <p>Adicionar Formação</p>
                                                    <figure>
                                                        <img src="images/plus.png" alt="">
                                                    </figure>
                                                </div> -->
                                            </div>

                                            <div class="row">
                                                <div class="col-12">
                                                    <div class="input-block">
                                                        <label>Nome da Formação</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12">
                                                    <div class="input-block">
                                                        <label>Descrição da Formação</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-title">
                                                <p>Endereço</p>

                                                <!-- <div class="temas-title">
                                                    <p>Adicionar mais um endereço</p>
                                                    <figure>
                                                        <img src="images/plus.png" alt="">
                                                    </figure>
                                                </div> -->
                                            </div>

                                            <div class="row">
                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>CEP</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Logradouro</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Número</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Bairro</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Estado</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Cidade</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Complemento</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Link do Gogle Maps</label>
                                                        <input type="text" required/>
                                                    </div>
                                                </div>

                                                <div class="col-12">
                                                    <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Valores de consulta</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>

                                <div class="toggle-body vitazure">
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tempo de consulta:</label>
                                                <select>
                                                    <option></option>
                                                    <option value="10">10</option>
                                                    <option value="20">20</option>
                                                    <option value="30">30</option>
                                                    <option value="40">40</option>
                                                    <option value="50">50</option>
                                                    <option value="60">60</option>
                                                </select>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Valor da consulta online:</label>
                                                <input type="text" required/>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Valor da consulta presencial:</label>
                                                <input type="text" required />
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tempo de antecendencia:</label>
                                                <select>
                                                    <option></option>
                                                    <option value="1 hora">1 hora</option>
                                                    <option value="2 horas">2 horas</option>
                                                    <option value="3 horas">3 horas</option>
                                                </select>
    
                                                <p>Tempo de antecêndia minimo para o paciênte marcar a consulta.</p>
                                                <p>O tempo padrão é de 1 hora.</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Dados bancários para recebimento de consultas</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>O Pagar.me é um sistema seguro de recebimento de valores. Para que você tenha acesso aos valores pagos pelo seus pacientes você deve preencher os dados abaixo. Os valores serão resgatados diariamente em até 48h após pagamento por boletos e 30 dias após pagamento por cartões de crédito em sua conta configurada.</p>

                                                <img src="../assets/images/pagar-me.jpeg" alt="" style="width: 35%;">

                                                <p>Qualquer dúvida à respeito dos seus pagamentos, por favor enviar e-mail para contato@vitazure.com.br</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tipo da conta:</label>
                                                <select>
                                                    <option></option>
                                                    <option value="conta corrente">Conta corrente</option>
                                                    <option value="poupança">Poupança</option>
                                                </select>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Banco:</label>
                                                <select>
                                                    <option></option>
                                                    <option value="Itaú">Itaú</option>
                                                    <option value="Bradesco">Bradesco</option>
                                                    <option value="Caixa">Caixa</option>
                                                    <option value="Nubank">Nubank</option>
                                                </select>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Agência:</label>
                                                <input type="text" required />
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Conta:</label>
                                                <input type="text" required />
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Conta (dígito verificador):</label>
                                                <input type="text" required />
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Nome do favorecido</label>
                                                <input type="text" required />
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <div class="pix">
                                                    <img src="images/pix.png" alt="">
                                                    <p>Novidade! Informe sua chave pix</p>
                                                </div>

                                                <label>Pix</label>
                                                <input type="text" required />
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Convênios</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>O Portal Vitazure oferece a oportunidade para os psicólogos atenderem diversas empresas ou planos de saúde. Ao marcar a opção abaixo você aceita oferecer o desconto estipulado para os funcionários e dependentes das empresas conveniadas. O desconto será aplicado automaticamente quando um paciente for identificado pelo sistema.</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$20,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$30,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$40,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$50,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$60,00</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Pacotes</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>O Portal Vitazure oferece a oportunidade para os psicólogos disponibilizarem pacotes de descontos com valores mais acessíveis para fidelizar os pacientes. Ao marcar as opções abaixo você aceita oferecer os valores estipulados. O desconto será aplicado automaticamente quando um paciente for identificado pelo sistema. Marque os pacotes que deseja oferecer abaixo:</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Pacote com 2 consultas por 30% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Pacote com 3 consultas por 40% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Pacote com 4 consultas por 50% de desconto cada</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Primeira consulta cortesia</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure"> 
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>Quando um paciente novo se cadastrar e não realizar consultas em um determinado tempo, será disponibilizado para ele um voucher com uma consulta cortesia, sem custo, a ser direcionado para os psicólogos que marcarem essa opção. Este serviço é um atendimento psicológico pontual, focado em um problema específico. O paciente receberá do psicólogo um aconselhamento ou orientação, com o objetivo de direcionar e ajudar a lidar com aquele problema.</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar opção para uma primeira consulta cortesia dos novos pacientes</label>
                                            </div>
    
                                            <p>Quantidade de consultas cortesias no mês: <span>1</span></p>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Atendimento por libras</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">
                                        <div class="col-12">
    
                                            <p>Habilitando essa informação você aparecerá como psicólogo fluente em Libras</p>
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Confirmar que atende em Libras</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Consulta com desconto</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>Habilitando essa opção será concedido um desconto de R$40,00 aleatório distribuído pelo sistema. Exemplo: se você atende por R$90,00, com o desconto aplicado pelo cliente você irá receber R$50,00.</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Habilitar desconto de R$40,00</label>
                                            </div>
    
                                            <p>Quantidade de consultas cortesias no mês: <span>5</span></p>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Aviso de férias</strong>

                                    <div class="duvidas">
                                        <span>?</span>

                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>Se marcado essa opção todos seus horários ficarão inativos, impossibilitando um possível agendamento de paciente. Essa opção é geralmente utilizada quando o psicólogo vai sair de férias. A sua assinatura do Vitazure não será pausada as cobranças das mensalidades vão ocorrer da mesma forma.</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" required />
                                                <label>Retirar agenda do perfil de forma temporária</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>