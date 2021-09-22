<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="informacoesPerfilApp" ng-controller="InformacoesPerfilController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>	
	<script src="../assets/js/vitazure/informacoesPerfil.js"></script>
	<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery.js'/>"></script>
	<script src="../assets/js/vitazure/cep.js"></script>
	<script type="text/javascript" src="<c:url value='../ilionnet/design/script/tiny_mce/tiny_mce.js'/>"></script>
<script type="text/javascript" src="<c:url value='../ilionnet/design/script/funcoesTinyMCE.js'/>"></script>
<script type="text/javascript" src="../ilionnet/design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="../ilionnet/design/script/common.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.4.0/moment.min.js"></script>
	<script type="text/javascript">
	document.write(getCalendarStyles());
	var cal1x = new CalendarPopup("testdiv1");
	</script>
	<style>
	
	.modal {
    display: none;
    position: fixed;
    z-index: 1;
    padding-top: 100px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0,0,0);
    background-color: rgba(0,0,0,0.4);
}
	
	.modal-content {
	    background-color: #fefefe;
	    margin: auto;
	    padding: 20px;
	    border: 1px solid #888;
	    width: 80%;
	    max-width: 500px;
	    margin: 1.75rem auto;
	}

.modal-header {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: start;
    align-items: flex-start;
    -ms-flex-pack: justify;
    justify-content: space-between;
    border-bottom: 1px solid #dee2e6;
    border-top-left-radius: calc(.3rem - 1px);
    border-top-right-radius: calc(.3rem - 1px);
    padding: 0;
}
.close {
  font-size: 2.5rem;
}
.swal2-title{
     display: contents !important;
}	
	</style>
		<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div>
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
                        <h3>Meu perfil</h3>
                        <p>Preencha todos os tópicos para seu Perfil  Profissional</p>
                    </div>

                    <div class="col-12 col-md-8 offset-md-2 col-xl-8 offset-xl-2">
                        <form class="form-default">
                          <angular-initializer ng-init="ProfissionalVH.profissional.id='${profissional.id}';
						                    ProfissionalVH.profissional.documentoCrpCrm='${profissional.documentoCrpCrm}';
						                    ProfissionalVH.profissional.conselhoProfissional='${profissional.conselhoProfissional}';
						                    ProfissionalVH.profissional.cadastroEpsi='${profissional.cadastroEpsi}';
						                    ProfissionalVH.profissional.tituloProfissional='${profissional.tituloProfissional}';
						                    ProfissionalVH.profissional.tipoProfissional='${profissional.tipoProfissional}';
						                    ProfissionalVH.profissional.especialidade='${profissional.especialidade}';
						                    ProfissionalVH.profissional.temasTrabalho='${profissional.temasTrabalho}';
						                    ProfissionalVH.profissional.duracaoAtendimento='${profissional.duracaoAtendimento}';
						                    ProfissionalVH.profissional.biografia='${profissional.biografia}';
						                    linkGoogleMaps='';
						                    estado='';
						                    logradouro='';
						                    complemento='';
						                    cep='';
						                    cidade='';
						                    bairro='';
						                    numero='';						                    
						                    ProfissionalVH.profissional.duracaoAtendimentoValor='${profissional.duracaoAtendimentoValor}';
						                    ProfissionalVH.profissional.valorConsultaOnline='${profissional.valorOnlineFormatado}';
						                    ProfissionalVH.profissional.valorConsultaPresencial='${profissional.valorPresencialFormatado}';
						                    ProfissionalVH.profissional.tempoAntecendencia='${profissional.tempoAntecendencia}';
						                    ProfissionalVH.profissional.adolescentes='${profissional.adolescentes}';
						                    ProfissionalVH.profissional.adultos='${profissional.adultos}';
						                    ProfissionalVH.profissional.casais='${profissional.casais}';
						                    ProfissionalVH.profissional.idosos='${profissional.idosos}';
						                    ProfissionalVH.profissional.tipoConta='${profissional.tipoConta}';
						                    ProfissionalVH.profissional.banco='${profissional.banco}';
						                    ProfissionalVH.profissional.agencia='${profissional.agencia}';
						                    ProfissionalVH.profissional.conta='${profissional.conta}';
     					                    ProfissionalVH.profissional.digitoVerificador='${profissional.digitoVerificador}';
						                    ProfissionalVH.profissional.nomeFavorecido='${profissional.nomeFavorecido}';
										    ProfissionalVH.profissional.convenio40='${profissional.convenio40}';
										    ProfissionalVH.profissional.convenio50='${profissional.convenio50}';
										    ProfissionalVH.profissional.convenio60='${profissional.convenio60}';
										    ProfissionalVH.profissional.pacote2com5Desconto='${profissional.pacote2com5Desconto}';
										    ProfissionalVH.profissional.pacote3com10Desconto='${profissional.pacote3com10Desconto}';
										    ProfissionalVH.profissional.pacote4com15Desconto='${profissional.pacote4com15Desconto}';
										    ProfissionalVH.profissional.primeiraConsultaCortesia='${profissional.primeiraConsultaCortesia}';
										    ProfissionalVH.profissional.quantidadeConsultaCortesiaMes='${profissional.quantidadeConsultaCortesiaMes}';
										    ProfissionalVH.profissional.atendimentoPorLibras='${profissional.atendimentoPorLibras}';
										    ProfissionalVH.profissional.habilitarDesconto40='${profissional.habilitarDesconto40}';
										    ProfissionalVH.profissional.quantidadeConsultaDesconto40Mes='${profissional.quantidadeConsultaDesconto40Mes}';
										    ProfissionalVH.profissional.avisoFerias='${profissional.avisoFerias}';
										    ProfissionalVH.profissional.dataInicioAvisoFerias='${profissional.dataInicioAvisoFerias}';
										    ProfissionalVH.profissional.dataFimAvisoFerias='${profissional.dataFimAvisoFerias}';
										    ProfissionalVH.profissional.ativo='${profissional.ativo}';
										    ProfissionalVH.profissional.dadosCompleto='${profissional.dadosCompleto}';
										    ProfissionalVH.profissional.plano='${profissional.plano}';
										    ProfissionalVH.profissional.dataInicioPlano='${profissional.dataInicioPlano}';
										    ProfissionalVH.profissional.dataFimPlano='${profissional.dataFimPlano}';
										    ProfissionalVH.profissional.tokenTransacaoPlano='${profissional.tokenTransacaoPlano}';
										    ProfissionalVH.profissional.idRecebedor='${profissional.idRecebedor}';
										    ProfissionalVH.profissional.idConta='${profissional.idConta}';
										    ProfissionalVH.profissional.situacaoAprovacaoProfissional='${profissional.situacaoAprovacaoProfissional}';
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
						                    ProfissionalVH.profissional.pessoa.foto.layout='${profissional.pessoa.foto.layout}';
						                    ProfissionalVH.profissional.pessoa.genero='${profissional.pessoa.genero}';
                          					ProfissionalVH.profissional.pessoa.cliente ='${profissional.pessoa.cliente}';
                         					ProfissionalVH.profissional.pessoa.psicologo ='${profissional.pessoa.psicologo}';
                         					ProfissionalVH.profissional.pessoa.confirmado='${profissional.pessoa.confirmado}';
                         					ProfissionalVH.formacaoAcademica ='${formacaoAcademica}';
                         					ProfissionalVH.enderecoAtendimento ='';
                         					ProfissionalVH.menuValidar= '';
                         					descricaoFormacao ='${descricaoFormacao}';
                         					formacao ='${formacao}';
                         					formacaoProfissional();
                         					enderecoAtendimento();
                         					especialidadeAtendimento();
                         					temasAtendimento();
                         					horarioAtendimento();
                         					apresentarCampoData('${profissional.avisoFerias}');
                         					apresentarCampoConsulta40Mes('${profissional.habilitarDesconto40}');
                         					apresentarPrimeiraConsultaCortesia('${profissional.primeiraConsultaCortesia}');
                         					diaSemana='';
                         					horaInicio='';
                         					horaFim='';
                         					enderecoSemanaHorario='';
                         					atendimentoOnline='false';
                         					atendimentoPresencial='false';
                          					 "/>
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Dados Profissional</strong>
                                </div>
                                <div class="toggle-body">
                                    <p>Preencha aqui os dados importantes para a exibição do seu perfil. Essas informações serão apresentadas para o paciente.</p>
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="perfil">
                                                <label for="avatar" class="photo-perfil">
                                                    <input type="file" name="avatar" id="avatar" style="display: none;">
                                                    <figure>
                                                        <img id="img" src="${profissional.pessoa.foto.link == null ? '../assets/images/perfil.png' : profissional.pessoa.foto.link}" alt="">
                                                    </figure>
                                                    <p>Alterar foto</p>
                                                </label>
                                                <p>Tamanho Máximo da Imagem 500Kb</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                          <div class="input-block">
                                            <label>Tipo Profissional</label>
                                            <select ng-model="ProfissionalVH.profissional.tipoProfissional" class="form-control input-sm" id="tipoProfissional">
                                              <c:forEach var="tipoProfissional" items="${tiposProfissional}">
                                                <option value="${tipoProfissional}">${tipoProfissional.valor}</option>
                                              </c:forEach>
                                            </select>
                                          </div>
                                        </div>
                                        
                                        <div class="col-12 col-md-6 col-xl-6">
                                          <div class="input-block">
                                            <label>Conselho profissional</label>
                                            <select ng-model="ProfissionalVH.profissional.conselhoProfissional" required>
                                              <c:forEach items="${conselhoProfissional}" var="conselho">
                                                <option ng-if="ProfissionalVH.profissional.tipoProfissional == 'PSICOLOGO'" value="${conselho}">${conselho.valor} - ${conselho.CRP}</option>
                                                <option ng-if="ProfissionalVH.profissional.tipoProfissional == 'MEDICO'" value="${conselho}">${conselho.valor} - ${conselho.CRM}</option>
                                              </c:forEach>
                                            </select>
                                          </div>
                                        </div>
                                        
                                        <div class="col-12 col-md-6 col-xl-6">
                                            <div class="input-block">
                                                <label>Documento do (CRP/CRM)</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.documentoCrpCrm"/>
                                            </div>
                                        </div>

                                        <div class="col-12 col-md-12 col-xl-12">
                                            <div class="input-block">
                                                <label>Cadastro do e-Psi</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.cadastroEpsi"  />
                                            </div>
                                        </div>

                                        


                                        <div class="col-12 col-md-12 col-xl-12">
                                            <div class="input-block">
                                                <label>Especialidade</label>
                                                <select ng-model="especialidade" class="form-control input-sm" style="width: 95%">
													<c:forEach var="especialidade" items="${especialidades}">
				   							           <option  ng-if="ProfissionalVH.profissional.tipoProfissional == 'PSICOLOGO' && ${especialidade.tipoProfissional == 'PSICOLOGO'}" value="${especialidade.valor}">${especialidade.valor}</option>
				   							           <option  ng-if="ProfissionalVH.profissional.tipoProfissional == 'MEDICO' && ${especialidade.tipoProfissional == 'MEDICO'}" value="${especialidade.valor}">${especialidade.valor}</option>
										            </c:forEach>
									           </select>
									           <a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm" style="position: absolute;left: 94%;top: 32px;"  ng-click =adicionarEspecialidades()>
													<i class="fas fa-plus" style="font-size: 23px;"></i>
												</a>
                                            </div>
                                        </div>
                                        <div class="col-12" style="padding-bottom: 15px;">  
                                        				<div class="menu d-none d-md-block" style="display: block !important;" layout="block">
                       											 <span id="panelFiltrosEspecialidades">
 																  </span>
																</div>
                                               </div>

                                           <div class="col-12 col-md-12 col-xl-12">
	                                            <div class="input-block">
	                                                <label>Temas de Trabalho</label>
	                                               <select ng-model="temasTrabalho" class="form-control input-sm" style="width: 95%">
														<c:forEach var="temas" items="${temasTrabalho}">
					   							          <option  value="${temas.valor}">${temas.valor}</option>
											            </c:forEach>
										           </select>
	                                            <a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm" style="position: absolute;left: 94%;top: 32px;"  ng-click =adicionarTemas()>
													<i class="fas fa-plus" style="font-size: 23px;"></i>
												</a>
	                                            </div>
	                                         </div> 
											 <div class="col-12" style="padding-bottom: 15px;">  
                                        				<div class="menu d-none d-md-block" style="display: block !important;" layout="block">
                       											 <span id="panelFiltrosSelecionados">
 																  </span>
																</div>
                                               </div>
                                               <div class="col-12 input-block" style="padding-bottom: 15px;">
                                                 <label>Faixa de atendimento</label>
	                                           </div>   
	                                            <div class="checkbox col-md-3 col-lg-3 col-sm-12">
	                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.adolescentes" id="adolescentes" ng-checked="${profissional.adolescentes}"/>
	                                                <label>adolescentes</label>
	                                            </div>
	                                            <div class="checkbox col-md-3 col-lg-3 col-sm-12">
	                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.adultos" id="adultos" ng-checked="${profissional.adultos}"/>
	                                                <label>adultos</label>
	                                            </div>
	                                            <div class="checkbox col-md-3 col-lg-3 col-sm-12">
	                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.casais" id="casais" ng-checked="${profissional.casais}"/>
	                                                <label>casais</label>
	                                            </div>
	                                            <div class="checkbox col-md-3 col-lg-3 col-sm-12">
	                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.idosos" id="idosos" ng-checked="${profissional.idosos}"/>
	                                                <label>idosos</label>
	                                            </div>
 										<div class="col-12">
                                                    <button class="button-secundary checkbox-button" ng-click="perfilProfissional('dadosProfissional')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                         </div>
                                    </div>
                                </div>
                            </div>
    						
    						<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Formação</strong>
                                </div>
    
    						<div class="toggle-body">
                                 <div class="row">
                                         <div class="col-12" style="margin-bottom: 3rem">
                                           <div class="input-title">
                                                <div class="col-md-11 col-lg-11 col-sm-12">
                                                  <p>Formação</p>
                                                </div>  
                                                <div class="col-md-1 col-lg-1 col-sm-12" style="display: flex;">
															<a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"  ng-click =adicionarFormacao()>
																<i class="fas fa-plus" style="font-size: 23px;"></i>
															</a>
															<p style="display: contents;color: red;">*</p>
												</div>
                                            </div>
                                            <div class="row">
                                                <div class="col-12">
                                                    <div class="input-block">
                                                        <label>Formação</label>
                                                        <input type="text" ng-model="formacao" />
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="input-block">
                                                        <label>Descrição da Formação</label>
                                                              <textarea cols="20" rows="5" ng-model="descricaoFormacao"  style="color: #A6A6A6"></textarea>
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
                                                    <button class="button-secundary checkbox-button" ng-click="perfilProfissional('formacao')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                                </div>
                                            </div>
                                        <p style="color: red;font-size: 15px;">* O "<i class="fas fa-plus" style="font-size: 14px;"></i>" é utilizado para adicionar uma Formação à sua lista.Após informar os dados, selecione o "<i class="fas fa-plus" style="font-size: 14px;"></i>" e o item será adicionado a sua lista.</p>
                                        </div>
                                    </div>
    						<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Sobre Mim</strong>
                                </div>
    						<div class="toggle-body">
                                       <div class="col-12">
                                          <p>Esta informação será mostrada para o paciente. Neste espaço fale de você numa linguagem de fácil entendimento, destacando sua paixão pela profissão e seu interesse em ajudar as pessoas.</p>
                                       </div>
                                    <div class="row">
    						            <div class="col-12">
                                            <div class="input-block">
                                                <textarea cols="20" rows="5" ng-model="ProfissionalVH.profissional.biografia" placeholder="Informe aqui sua vida profissional. Procure ser mais claro possível." style="color: #A6A6A6"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                                    <button class="button-secundary checkbox-button"  ng-click="validarCampo(ProfissionalVH.profissional.biografia)" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                         </div>
                                      </div>
                                  </div>      
    						</div>
    						
    						<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Endereço de atendimento presencial</strong>
                                </div>
    
                                <div class="toggle-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="input-title">
                                                <div col-md-11 col-lg-11 col-sm-12>
                                                  <p>Endereço</p>
                                                </div>  
                                                <div class="col-md-1 col-lg-1 col-sm-12" style="display: flex">
														<a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"  ng-click =adicionarEndereco()>
															<i class="fas fa-plus" style="font-size: 23px;"></i>
														</a>
													<p style="display: contents;color: red;">*</p>	
												</div>  
                                            </div>
                                            <div class="row">
                                                <div class="col-12 col-md-6 col-xl-6">
                                                    <div class="input-block">
                                                        <label>CEP</label>
                                                        <input type="text" ng-model="cep" onblur="buscaEndereco(this.value)" id="cep" data-mask="00.000-000"/>
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
                                                        <label>Link do Google Maps</label>
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
                            <div id="myModal" class="modal">
						  <div class="modal-content col-md-12">
							    <div class="modal-header">
						          <h4>Deseja Realmente Excluir?</h4>
						          <div id="modalBtnClick" class="close" data-dismiss="modal"><i class="fas fa-times"></i></div>
						        </div>
							    <div class="modal-body" style="width:100%;padding: 33px;">
						              <form>
						                <div class="row" style="text-align: center;">						                
						                  <div class="col-12 col-md-6 col-xl-6">
						                     <button class="btn btn-success" ng-click="excluirEndereco()" style="width: 70%;height: 38px;font-size: 15px;">Sim</button>
						                  </div>
						                  <div class="col-12 col-md-6 col-xl-6">
						                    <button class="btn btn-danger" onclick="fecharModal()" style="width: 70%;height: 38px;font-size: 15px;">Não</button>
						                  </div>
						                </div>
						              </form>
						  </div>
					</div>
                        </div>
						            	</div>
						            </div>	

                                                <div class="col-12">
                                                    <button class="button-secundary checkbox-button" ng-click="salvarListEndereco()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                                </div>
                                            </div>
                                          <p style="color: red;font-size: 15px;">* O "<i class="fas fa-plus" style="font-size: 14px;"></i>" é utilizado para adicionar um endereço de atendimento à sua lista.Após informar os dados, selecione o "<i class="fas fa-plus" style="font-size: 14px;"></i>" e o item será adicionado a sua lista.</p>  
                                        </div>
                                    </div>
                                </div>
    						</div>
    					<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Tempo de duração do atendimento</strong>
                                </div>
                                <div class="toggle-body vitazure">	
		    						<div class="col-12">
		                                            <div class="input-block">
		                                                <label>Tempo de duração do atendimento</label>
		                                                <select ng-model="ProfissionalVH.profissional.duracaoAtendimento" id="duracaoAtendimento" class="form-control input-sm">
															<c:forEach var="duracaoAtendimento" items="${duracoes}">
						   							          <option value="${duracaoAtendimento}">${duracaoAtendimento.valor}</option>
												            </c:forEach>
											           </select>
		                                            </div>
		                                        </div>
		 										<div class="col-12">
		                                                    <button class="button-secundary checkbox-button" ng-click="perfilProfissional('tempoDuracao')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                         </div>
                                      </div>
                          </div>               
    						
    						
    						<div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Horário Atendimento</strong>
                                </div>
                                <div class="toggle-body vitazure">
                                  <div class="col-12">
                                        <div class="input-title">
                                                <div class="col-md-11 col-lg-11 col-sm-12">
                                                  <p>Horário Atendimento</p>
                                                </div>  
                                                <div class="col-md-1 col-lg-1 col-sm-12" style="display: flex;">
														<a class="btn pmd-btn-fab pmd-btn-flat pmd-ripple-effect btn-default btn-sm"  ng-click =adicionarHorarioAtendimento()>
															<i class="fas fa-plus" style="font-size: 23px;"></i>
														</a>
													<p style="display: contents;color: red;">*</p>	
												</div>  
                                        </div>
                                    <div class="row">
                                        <div class="col-12 col-md-4 col-xl-4">
                                                    <div class="input-block">
                                                        <label>Dia Semana</label>
                                                        <select ng-model="diaSemana" class="form-control input-sm">
															<c:forEach var="diaSemana" items="${diasSemana}">
						   							          <option value="${diaSemana}">${diaSemana.valor}</option>
												            </c:forEach>
											           </select>
                                                    </div>
                                                </div>
                                        <div class="col-12 col-md-4 col-xl-4">
                                                    <div class="input-block">
                                                        <label>Hora Início</label>
                                                        <input type="text" ng-model="horaInicio" id="horaInicio" data-mask="00:00" onchange="validarHora(this.value,'horaInicio')"/>
                                                    </div>
                                         </div>
                                        <div class="col-12 col-md-4 col-xl-4">
                                                    <div class="input-block">
                                                        <label>Hora Fim</label>
                                                        <input type="text" ng-model="horaFim" id="horaFim" data-mask="00:00"  onchange="validarHora(this.value ,'horaFim')"/>
                                                    </div>
                                         </div>
                                        <div class="col-12 col-md-12 col-xl-12">
                                                    <div class="input-block">
                                                        <label>Endereço Atendimento</label>
                                                        <select ng-model="enderecoSemanaHorario" class="form-control input-sm">
															<c:forEach var="endereco" items="${enderecoAtendimento}">
						   							          <option value="${endereco}">${endereco.logradouro} ${endereco.complemento}</option>
												            </c:forEach>
											           </select>
                                                    </div>
                                         </div>
										<div class="col-12 col-md-6 col-xl-6">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="atendimentoOnline" ng-checked="${atendimentoOnline}"/>
                                                <label>Atendimento Online</label>
                                            </div>
                                        </div>
										<div class="col-12 col-md-6 col-xl-6">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="atendimentoPresencial" ng-checked="${atendimentoPresencial}"/>
                                                <label>Atendimento Presencial</label>
                                            </div>
                                        </div>
                                        
                                        <div class="col-12" style="padding-top: 18px;">  
                                        <div class="table-responsive">
							                <table id="tblCadastroHorarioAtendimento" class="table table-bordered">         
											      <thead style="font-size: 15px;">
												     <tr>
												       <th class="text-center">Dia</th>
												       <th class="text-center">Hora Inicio</th>
												       <th class="text-center">Hora Fim</th>
												       <th class="text-center">Online</th>
												       <th class="text-center">Presencial</th>
												       <th class="text-center">Logradouro</th>
												       <th class="text-center">Opção</th>
												     </tr>
												   </thead>
											      <tbody style="font-size: 15px;">
											      </tbody>
											   </table>
						            	</div>
						            	
						            </div>	
                                        
                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="salvarListEnderecoAtendimento()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                       <p style="color: red;font-size: 15px;">* O "<i class="fas fa-plus" style="font-size: 14px;"></i>" é utilizado para adicionar um horário de atendimento à sua lista.Após informar os dados, selecione o "<i class="fas fa-plus" style="font-size: 14px;"></i>" e o item será adicionado a sua lista.</p>  
                                  </div>  
                                </div>
                            </div>
    						
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Valores de consulta</strong>
                                </div>
                                <div class="toggle-body vitazure">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Valor da consulta online:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.valorConsultaOnline" id="valorconsultaOnline" class="valorMonetario"  />
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Valor da consulta presencial:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.valorConsultaPresencial" id="valorConsultaPresencial" class="valorMonetario"   />
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Tempo minimo de antecendencia:</label>
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
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional('')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Dados bancários para recebimento de consultas</strong>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>O Pagar.me é um sistema seguro de recebimento de valores. Para que você tenha acesso aos valores pagos pelos seus pacientes você deve preencher os dados abaixo. Os valores serão resgatados semanalmente, sendo 30 dias após pagamento por cartões de crédito em sua conta configurada.</p>

                                                <img src="../assets/images/pagar-me.jpeg" alt="" style="width: 35%;">

                                                <p>Qualquer dúvida a respeito dos seus pagamentos, por favor enviar e-mail para atendimento@vitazure.com.br</p>
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
                                                <input type="text" ng-model="ProfissionalVH.profissional.agencia"  data-mask="0000"/>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Conta:</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.conta"   data-mask="0000000000000"/>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Conta (dígito verificador):</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.digitoVerificador" data-mask="0"/>
                                            </div>
                                        </div>
        
                                        <div class="col-12">
                                            <div class="input-block">
                                                <label>Nome do favorecido</label>
                                                <input type="text" ng-model="ProfissionalVH.profissional.nomeFavorecido"/>
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
                                            <button class="button-secundary checkbox-button" ng-click="salvarConta()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Convênios</strong>
                                </div>
    
                                <div class="toggle-body vitazure">
                                    <div class="row">

                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>O Portal Vitazure oferece a oportunidade para os psicólogos atenderem diversas empresas. Ao marcar a opção abaixo você aceita oferecer o desconto estipulado para os funcionários e dependentes das empresas conveniadas. O desconto será aplicado automaticamente quando um paciente for identificado pelo sistema.</p>
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
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional('')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Pacotes</strong>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.pacote2com5Desconto" id="pacote2com5Desconto" ng-checked="${profissional.pacote2com5Desconto}"/>
                                                <label>Pacote com 2 consultas por 5% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.pacote3com10Desconto" id="pacote3com10Desconto" ng-checked="${profissional.pacote3com10Desconto}"/>
                                                <label>Pacote com 3 consultas por 10% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.pacote4com15Desconto" id="pacote4com15Desconto" ng-checked="${profissional.pacote4com15Desconto}"/>
                                                <label>Pacote com 4 consultas por 15% de desconto cada</label>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional('')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Primeira consulta cortesia</strong>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.primeiraConsultaCortesia" id="primeiraConsultaCortesia" ng-checked="${profissional.primeiraConsultaCortesia}" onclick="apresentarPrimeiraConsultaCortesia(this.checked)"/>
                                                <label>Habilitar opção para uma primeira consulta cortesia dos novos pacientes</label>
                                            </div>
                                            <div class="col-12" style="display:none;" id="divPrimeiraConsultaCortesia">
                                             <p>Quantidade de consultas cortesias no mês: <input type="text" id="quantidadeConsultaCortesiaMes" style="width: 32px;text-align: center;" onchange="validarQuantidadeCortesiaMes(this.value)" ng-model="ProfissionalVH.profissional.quantidadeConsultaCortesiaMes" data-mask="00"/></p>
                                       		</div>
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional('')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Atendimento por libras</strong>
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
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional('')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Consulta com desconto</strong>
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
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.habilitarDesconto40" id="habilitarDesconto40" ng-checked="${profissional.habilitarDesconto40}" onclick="apresentarCampoConsulta40Mes(this.checked)" />
                                                <label>Habilitar desconto de R$40,00</label>
                                            </div>
    										<div class="col-12" style="display:none;" id="divQuantidadeConsulta40Mes">
                                               <p>Quantidade de consultas cortesias no mês: <input type="text" id="quantidadeConsultaDesconto40Mes" onchange="validarQuantidadeConsultaDesconto40Mes(this.value)" style="width: 32px;text-align: center;" ng-model="ProfissionalVH.profissional.quantidadeConsultaDesconto40Mes" data-mask="00"/></p>
                                            </div>   
                                        </div>

                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="perfilProfissional('')" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
    
                            <div class="match-toggle">
                                <div class="toggle-header">
                                    <strong>Aviso de férias/Afastamento</strong>
                                </div>
                                <div class="toggle-body vitazure">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="toogle-title">
                                                <p>Se marcado essa opção todos seus horários ficarão indisponíveis, impossibilitando o agendamento de paciente. Essa opção é geralmente utilizada quando o profissional vai sair de férias e/ou afastar-se por um período determinado. A sua assinatura do Vitazure não será pausada as cobranças das mensalidades vão ocorrer da mesma forma.</p>
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="ProfissionalVH.profissional.avisoFerias" id="avisoFerias" ng-checked="${profissional.avisoFerias}" onclick="apresentarCampoData(this.checked)"/>
                                                <label>Incluir data início e data fim do afastamento</label>
                                            </div>
                                        </div>
											<div class="col-6" style="padding-top: 20px;display:none;" id="divCampoDataInicialAviso">
			                                    <div class="input-block">
			                                        <label>Data Inicio</label>
			                                        <input type="text" data-mask="00/00/0000" ng-model="ProfissionalVH.profissional.dataInicioAvisoFerias" id="dataInicioAvisoFerias"/>
			                                        <a href="javascript:;" style="position: absolute;left: 86%;top: 55%;" onClick="cal1x.select(document.getElementById('dataInicioAvisoFerias'),'linkDataInicioAvisoFerias','dd/MM/yyyy'); return false;" id="linkDataInicioAvisoFerias" name="linkDataInicioAvisoFerias">
			                                           <i class="fas fa-calendar-week" style="font-size: 20px;"></i>
			                                        </a>
			                                    </div>
			                                </div>
											
											<div class="col-6" style="padding-top: 20px;display:none;" id="divCampoFinalDataAviso">
			                                    
			                                       <div class="input-block">
			                                        <label>Data Fim</label>
			                                        <input type="text" data-mask="00/00/0000" ng-model="ProfissionalVH.profissional.dataFimAvisoFerias" id="dataFimAvisoFerias"/>
			                                        <a href="javascript:;" style="position: absolute;left: 86%;top: 55%;" onClick="cal1x.select(document.getElementById('dataFimAvisoFerias'),'linkDataFimAvisoFerias','dd/MM/yyyy'); return false;" id="linkDataFimAvisoFerias" name="linkDataFimAvisoFerias">
			                                           <i class="fas fa-calendar-week" style="font-size: 20px;"></i>
			                                        </a>
			                                    </div>
			                                </div>
                                        <div class="col-12">
                                            <button class="button-secundary checkbox-button" ng-click="validarCampoFerias()" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Salvar</button>
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
        <script src="../assets/js/vitazure/informacoesPerfil.js"></script>
        <script src="../assets/js/vitazure/cep.js"></script>
            
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
$('.valorMonetario').mask('#.##0,00', {reverse: true});
</script>

<script>
$(function(){
	 $('#avatar').change(function(){
	 	const file = $(this)[0].files[0]
	 	if(file.size <= '500000'){	 		
		 	const fileReader = new FileReader()
		 	fileReader.onloadend = function(){
				$('#img').attr('src',fileReader.result)
			}
		 	fileReader.readAsDataURL(file)
	 	}
	 })
})
</script>
<script>
  function apresentarCampoData(campo){
	  if(campo == true || campo == 'true'){
		document.getElementById("divCampoDataInicialAviso").style.display = "inline-block";
		document.getElementById("divCampoFinalDataAviso").style.display = "inline-block";
	  }else{
		document.getElementById("divCampoDataInicialAviso").style.display = "none"; 
		document.getElementById("divCampoFinalDataAviso").style.display = "none"; 
	  }
  }
</script>

<script>
function validarQuantidadeCortesiaMes(valor){
	if (valor == 0 || valor == null) {
		alert("Quantidade de consultas cortesias no mês: dever ser maior que zero.");
		document.getElementById("quantidadeConsultaCortesiaMes").value = "1";
	}
	
}
function validarQuantidadeConsultaDesconto40Mes(valor){
	if (valor == 0 || valor == null) {
		alert("Quantidade de consultas com desconto no mês: dever ser maior que zero.");
		document.getElementById("quantidadeConsultaDesconto40Mes").value = "1";
	}
	
}
</script>

    </div>
</body>
</html>