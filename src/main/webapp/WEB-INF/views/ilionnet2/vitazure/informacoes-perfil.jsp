<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="informacoesPerfilApp" ng-controller="InformacoesPerfilController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>	
	<script src="../assets/js/vitazure/informacoesPerfil.js"></script>
	<script src="../assets/js/vitazure/cep.js"></script>
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
                          <angular-initializer ng-init="ProfissionalVH.profissional.id='${profissional.id}';
						                    ProfissionalVH.profissional.documentoCrpCrm='${profissional.documentoCrpCrm}';
						                    ProfissionalVH.profissional.cadastroEpsi='${profissional.cadastroEpsi}';
						                    ProfissionalVH.profissional.tituloProfissional='${profissional.tituloProfissional}';
						                    ProfissionalVH.profissional.tipoProfissional='${profissional.tipoProfissional}';
						                    ProfissionalVH.profissional.especialidade='${profissional.especialidade}';
						                    ProfissionalVH.profissional.temasTrabalho='${profissional.temasTrabalho}';
						                    ProfissionalVH.profissional.duracaoAtendimento='${profissional.duracaoAtendimento}';
						                    ProfissionalVH.profissional.biografia='${profissional.biografia}';
						                    linkGoogleMaps='${linkGoogleMaps}';
						                    estado='${estado}';
						                    logradouro='${logradouro}';
						                    complemento='${complemento}';
						                    cep='${cep}';
						                    cidade='${cidade}';
						                    bairro='${bairro}';
						                    numero='${numero}';
						                    ProfissionalVH.profissional.duracaoAtendimentoValor='${profissional.duracaoAtendimentoValor}';
						                    ProfissionalVH.profissional.valorConsultaOnline='${profissional.valorConsultaOnline}';
						                    ProfissionalVH.profissional.valorConsultaPresencial='${profissional.valorConsultaPresencial}';
						                    ProfissionalVH.profissional.tempoAntecendencia='${profissional.tempoAntecendencia}';
						                    ProfissionalVH.profissional.tipoConta='${profissional.tipoConta}';
						                    ProfissionalVH.profissional.banco='${profissional.banco}';
						                    ProfissionalVH.profissional.agencia='${profissional.agencia}';
						                    ProfissionalVH.profissional.conta='${profissional.conta}';
     					                    ProfissionalVH.profissional.digitoVerificador='${profissional.digitoVerificador}';
						                    ProfissionalVH.profissional.nomeFavorecido='${profissional.nomeFavorecido}';
						                    ProfissionalVH.profissional.convenio20='${profissional.convenio20}';
										    ProfissionalVH.profissional.convenio30='${profissional.convenio30}';
										    ProfissionalVH.profissional.convenio40='${profissional.convenio40}';
										    ProfissionalVH.profissional.convenio50='${profissional.convenio50}';
										    ProfissionalVH.profissional.convenio60='${profissional.convenio60}';
										    ProfissionalVH.profissional.pacote2com30Desconto='${profissional.pacote2com30Desconto}';
										    ProfissionalVH.profissional.pacote3com40Desconto='${profissional.pacote3com40Desconto}';
										    ProfissionalVH.profissional.pacote4com50Desconto='${profissional.pacote4com50Desconto}';
										    ProfissionalVH.profissional.primeiraConsultaCortesia='${profissional.primeiraConsultaCortesia}';
										    ProfissionalVH.profissional.quantidadeConsultaCortesiaMes='${profissional.quantidadeConsultaCortesiaMes}';
										    ProfissionalVH.profissional.atendimentoPorLibras='${profissional.atendimentoPorLibras}';
										    ProfissionalVH.profissional.habilitarDesconto40='${profissional.habilitarDesconto40}';
										    ProfissionalVH.profissional.quantidadeConsultaDesconto40Mes='${profissional.quantidadeConsultaDesconto40Mes}';
										    ProfissionalVH.profissional.avisoFerias='${profissional.avisoFerias}';
						                    ProfissionalVH.profissional.pessoa.id='${profissional.pessoa.id}';
						                    ProfissionalVH.profissional.pessoa.nome='${profissional.pessoa.nome}';
						                    ProfissionalVH.profissional.pessoa.email='${profissional.pessoa.email}';
						                    ProfissionalVH.profissional.pessoa.telefone='${profissional.pessoa.telefone}';
						                    ProfissionalVH.profissional.pessoa.celular='${profissional.pessoa.celular}';
						                    ProfissionalVH.profissional.pessoa.senha='${profissional.pessoa.senha}';
						                    ProfissionalVH.profissional.pessoa.dataNascimento='${profissional.pessoa.dataNascimento}';
						                    ProfissionalVH.profissional.pessoa.cpf='${profissional.pessoa.cpf}';
						                    ProfissionalVH.profissional.pessoa.cep='${profissional.pessoa.cep}';
						                    ProfissionalVH.profissional.pessoa.cidade='${profissional.pessoa.cidade}';
						                    ProfissionalVH.profissional.pessoa.estado='${profissional.pessoa.estado}';
						                    ProfissionalVH.profissional.pessoa.endereco='${profissional.pessoa.endereco}';
						                    ProfissionalVH.profissional.pessoa.setor='${profissional.pessoa.setor}';
						                    ProfissionalVH.profissional.pessoa.foto.id='${profissional.pessoa.foto.id}';
						                    ProfissionalVH.profissional.pessoa.foto.link='${profissional.pessoa.foto.link}';
                          					ProfissionalVH.profissional.pessoa.cliente ='${profissional.pessoa.cliente}';
                         					ProfissionalVH.profissional.pessoa.psicologo ='${profissional.pessoa.psicologo}';
                         					ProfissionalVH.formacaoAcademica ='${formacaoAcademica}';
                         					ProfissionalVH.enderecoAtendimento ='${enderecoAtendimento}';
                         					descricaoFormacao ='${descricaoFormacao}';
                         					formacao ='${formacao}';
                         					formacaoProfissional();
                         					enderecoAtendimento();
                          					 "/>
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
                                                        <img id="img" src="${profissional.pessoa.foto.imagemApresentar == null ? '../assets/images/perfil.png' : profissional.pessoa.foto.link}" alt="">
                                                    </figure>
                                                    <p>Alterar foto</p>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>CPF</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.pessoa.cpf" data-mask="000.000.000-00"  />
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Data de Nascimento</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.pessoa.dataNascimento" data-mask="00/00/0000"  />
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Documento do (CRP/CRM)</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.documentoCrpCrm"  />
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Cadastro do E-Psi</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.cadastroEpsi"  />
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Título Profissional</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.tituloProfissional"  />
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tipo Profissional</label>
                                                <select ng-model="ProfissionalVH.profissional.tipoProfissional" class="form-control input-sm">
													<c:forEach var="tipoProfissional" items="${tiposProfissional}">
				   							          <option value="${tipoProfissional}">${tipoProfissional.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Especialidade</label>
                                                <select ng-model="ProfissionalVH.profissional.especialidade" class="form-control input-sm">
													<c:forEach var="especialidade" items="${especialidades}">
				   							          <option value="${especialidade}">${especialidade.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Temas de Trabalho</label>
                                               <select ng-model="ProfissionalVH.profissional.temasTrabalho" class="form-control input-sm">
													<c:forEach var="temas" items="${temasTrabalho}">
				   							          <option value="${temas}">${temas.valor}</option>
										            </c:forEach>
									           </select>
                                                <strong>
                                                    Acompanhamento psicológico, Ansiedade, Anorexia nervisa,  Autismo, Autoestima, Conflitos amorosos, Conflitos familiáres, Depressão.
                                                </strong>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tempo de duração do atendimento</label>
                                                <select ng-model="ProfissionalVH.profissional.duracaoAtendimento" class="form-control input-sm">
													<c:forEach var="duracaoAtendimento" items="${duracoes}">
				   							          <option value="${duracaoAtendimento}">${duracaoAtendimento.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Bibliografia</label>
                                                <textarea cols="20" rows="5" ng-model="ProfissionalVH.profissional.biografia" placeholder="Informe aqui sua vida profissional. Procure ser mais claro possível." style="color: #A6A6A6"></textarea>
                                            </div>
                                        </div>

 										<div class="col-12">
                                                    <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                         </div>
                                    </div>
                                </div>
                            </div>
    						
    						<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Formação</strong>
                                    <div class="duvidas">
                                        <span>?</span>
                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
    						<div class="toggle-body">
                                 <div class="row">
                                         <div class="col-12" style="margin-bottom: 3rem">
                                           <div class="input-title">
                                                <div col-md-11 col-lg-11 col-sm-12>
                                                  <p>Formação</p>
                                                </div>  
                                                <div class="col-md-1 col-lg-1 col-sm-12" style="margin-top: 28px;">
														<a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"  ng-click =adicionarFormacao()>
															<i class="fas fa-plus" style="font-size: 23px;"></i>
														</a>
												</div>  
                                            </div>
                                            <div class="row">
                                                <div class="col-6">
                                                    <div class="input-block">
                                                        <label>Formação</label>
                                                        <select ng-model="formacao" class="form-control input-sm">
															<c:forEach var="formacao" items="${formacoes}">
						   							          <option value="${formacao.valor}">${formacao.valor}</option>
												            </c:forEach>
											           </select>
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="input-block">
                                                        <label>Descrição da Formação</label>
                                                        <input type="text" ng-model="descricaoFormacao" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                     <div class="col-12">  
                                        <div class="table-responsive">
							                <table id="tblCadastro" class="table table-bordered">         
											      <thead style="font-size: 15px;">
												     <tr>
												       <th class="text-center">Formação</th>
												       <th class="text-center">Descrição da Formação</th>
												       <th class="text-center">Opção</th>
												     </tr>
												   </thead>
											      <tbody style="font-size: 15px;">
											      </tbody>
											   </table>
						            	</div>
						            </div>	
                                          <div class="col-12">
                                                    <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
    						
    						
    						
    						<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Endereço de atendimento presencial</strong>
                                    <div class="duvidas">
                                        <span>?</span>
                                        <p><b>Dicas de como Cobrar</b> <br/>É importante que o valor da consulta online seja menor que o da consulta presencial. Afinal não estão presentes vários custos relativos a estrutura física de um consultório.</p>
                                    </div>
                                </div>
    
                                <div class="toggle-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="input-title">
                                                <div col-md-11 col-lg-11 col-sm-12>
                                                  <p>Endereço</p>
                                                </div>  
                                                <div class="col-md-1 col-lg-1 col-sm-12" style="margin-top: 28px;">
														<a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"  ng-click =adicionarEndereco()>
															<i class="fas fa-plus" style="font-size: 23px;"></i>
														</a>
												</div>  
                                            </div>
                                            <div class="row">
                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>CEP</label>
                                                        <input type="text" ng-model="cep" onblur="buscaEndereco(this.value)" id="cep" data-mask="00.000-000"  />
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Logradouro</label>
                                                        <input type="text" ng-model="logradouro" id="logradouro"   />
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Número</label>
                                                        <input type="text" ng-model="numero" id="numero" />
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Bairro</label>
                                                        <input type="text" ng-model="bairro" id="bairro"  />
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Estado</label>
                                                        <select ng-model="estado" id="uf" class="form-control input-sm">
															<c:forEach var="estado" items="${estados}">
						   							          <option value="${estado}">${estado.valor}</option>
												            </c:forEach>
											           </select>
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Cidade</label>
                                                        <input type="text" ng-model="cidade" id="cidade"  />
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Complemento</label>
                                                        <input type="text" ng-model="complemento" id="complemento" />
                                                    </div>
                                                </div>

                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>Link do Gogle Maps</label>
                                                        <input type="text" ng-model="linkGoogleMaps" id="linkGoogleMaps"  />
                                                    </div>
                                                </div>
                                     <div class="col-12">  
                                        <div class="table-responsive">
							                <table id="tblCadastroEndereco" class="table table-bordered">         
											      <thead style="font-size: 15px;">
												     <tr>
												       <th class="text-center">Logradouro</th>
												       <th class="text-center">Complemento</th>
												       <th class="text-center">Cidade</th>
												       <th class="text-center">linkGoogleMaps</th>
												       <th class="text-center">Opção</th>
												     </tr>
												   </thead>
											      <tbody style="font-size: 15px;">
											      </tbody>
											   </table>
						            	</div>
						            </div>	

                                                <div class="col-12">
                                                    <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                <select ng-model="ProfissionalVH.profissional.duracaoAtendimentoValor" class="form-control input-sm">
													<c:forEach var="duracaoAtendimento" items="${duracoes}">
				   							          <option value="${duracaoAtendimento}">${duracaoAtendimento.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Valor da consulta online:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.valorConsultaOnline" id="valorconsultaOnline" class="valorMonetario"  />
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Valor da consulta presencial:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.valorConsultaPresencial" class="valorMonetario"   />
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tempo de antecendencia:</label>
                                                <select ng-model="ProfissionalVH.profissional.tempoAntecendencia" class="form-control input-sm">
													<c:forEach var="tempo" items="${tempoAntecendencia}">
				   							          <option value="${tempo}">${tempo.valor}</option>
										            </c:forEach>
									           </select>
                                                <p>Tempo de antecêndia minimo para o paciênte marcar a consulta.</p>
                                                <p>O tempo padrão é de 1 hora.</p>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                 <select ng-model="ProfissionalVH.profissional.tipoConta" class="form-control input-sm">
														<c:forEach var="tipoConta" items="${tiposConta}">
				   							                <option value="${tipoConta}">${tipoConta.valor}</option>
										                </c:forEach>
									                </select>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Banco:</label>
                                               <select ng-model="ProfissionalVH.profissional.banco" class="form-control input-sm">
														<c:forEach var="banco" items="${bancos}">
				   							                <option value="${banco}">${banco.valor}</option>
										                </c:forEach>
									            </select>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Agência:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.agencia"   />
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Conta:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.conta"   />
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Conta (dígito verificador):</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.digitoVerificador" />
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Nome do favorecido</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.nomeFavorecido"   />
                                            </div>
                                        </div>

<!--                                         <div class="col-12"> -->
<!--                                             <div class="input-block"> -->
<!--                                                 <div class="pix"> -->
<!--                                                     <img src="images/pix.png" alt=""> -->
<!--                                                     <p>Novidade! Informe sua chave pix</p> -->
<!--                                                 </div> -->

<!--                                                 <label>Pix</label> -->
<!--                                                 <input type="text"   /> -->
<!--                                             </div> -->
<!--                                         </div> -->

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.convenio20" id="convenio20" ng-checked="${profissional.convenio20}"/>
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$20,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.convenio30" id="convenio30" ng-checked="${profissional.convenio30}"/>
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$30,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.convenio40" id="convenio40" ng-checked="${profissional.convenio40}"/>
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$40,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.convenio50" id="convenio50" ng-checked="${profissional.convenio50}"/>
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$50,00</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.convenio60" id="convenio60" ng-checked="${profissional.convenio60}"/>
                                                <label>Habilitar opção para realizar atendimento para os convênios no valor de R$60,00</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.pacote2com30Desconto" id="pacote2com30Desconto" ng-checked="${profissional.pacote2com30Desconto}"/>
                                                <label>Pacote com 2 consultas por 30% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.pacote3com40Desconto" id="pacote3com40Desconto" ng-checked="${profissional.pacote3com40Desconto}"/>
                                                <label>Pacote com 3 consultas por 40% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.pacote4com50Desconto" id="pacote4com50Desconto" ng-checked="${profissional.pacote4com50Desconto}"/>
                                                <label>Pacote com 4 consultas por 50% de desconto cada</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.primeiraConsultaCortesia" id="primeiraConsultaCortesia" ng-checked="${profissional.primeiraConsultaCortesia}"/>
                                                <label>Habilitar opção para uma primeira consulta cortesia dos novos pacientes</label>
                                            </div>
    
                                            <p>Quantidade de consultas cortesias no mês:    <input type="text" style="width: 32px;" ng-model="ProfissionalVH.profissional.quantidadeConsultaCortesiaMes"/></p>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                <input type="checkbox"  ng-model="ProfissionalVH.profissional.atendimentoPorLibras" id="atendimentoPorLibras"  ng-checked="${profissional.atendimentoPorLibras}" />
                                                <label>Confirmar que atende em Libras</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.habilitarDesconto40" id="habilitarDesconto40" ng-checked="${profissional.habilitarDesconto40}" />
                                                <label>Habilitar desconto de R$40,00</label>
                                            </div>
    
                                            <p>Quantidade de consultas cortesias no mês: <input type="text" style="width: 32px;" ng-model="ProfissionalVH.profissional.quantidadeConsultaDesconto40Mes"/></p>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Aviso de férias/Afastamento</strong>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.avisoFerias" id="avisoFerias" ng-checked="${profissional.avisoFerias}"/>
                                                <label>Incluir data início e data fim do afastamento</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
$('.valorMonetario').mask('#.##0.00', {reverse: true});
</script>
<script>
 $(function(){
	 $('#avatar').change(function(){
	 	const file = $(this)[0].files[0]
	 	const fileReader = new FileReader()
	 	fileReader.onloadend = function(){
			$('#img').attr('src',fileReader.result)
		}
	 	fileReader.readAsDataURL(file)
	 })
 })

</script>
    </div>
</body>
</html>