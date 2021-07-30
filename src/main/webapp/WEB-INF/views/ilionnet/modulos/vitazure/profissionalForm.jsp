<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" ng-app="clienteApp" ng-controller="ClienteController">
    <head>
        <title>Profissional - <ilion:nomeEmpresa/></title>
        <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
</head>

<body>

<jsp:include page="../../../ilionnet2/inc/include-header.jsp" flush="true"/>

<jsp:include page="../../../ilionnet2/inc/include-sidebar.jsp" flush="true"/>

<!--content area start-->

<div id="content" class="pmd-content">


    <div class="container-fluid">


        <h1 class="section-title">
			<span>Profissional</span>
		</h1><!-- End Title -->
		
		<!--breadcrum start-->
		<ol class="breadcrumb text-left">
		  <li><a href="<ilion:url/>ilionnet/home2">Home</a></li>
		  <li>Protons</li>
		  <li><a href="<ilion:url/>ilionnet/protons/usuario">Profissional</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					<form ng-submit="submitProfissional()">
						          <angular-initializer
						            ng-init="profissional.id='${profissional.id}';
						                    profissional.documentoCrpCrm='${profissional.documentoCrpCrm}';
						                    profissional.cadastroEpsi='${profissional.cadastroEpsi}';
						                    profissional.tituloProfissional='${profissional.tituloProfissional}';
						                    profissional.tipoProfissional='${profissional.tipoProfissional}';
						                    profissional.especialidade='${profissional.especialidade}';
						                    profissional.temasTrabalho='${profissional.temasTrabalho}';
						                    profissional.duracaoAtendimento='${profissional.duracaoAtendimento}';
						                    profissional.biografia='${profissional.biografia}';
						                    profissional.duracaoAtendimentoValor='${profissional.duracaoAtendimentoValor}';
						                    profissional.valorConsultaOnline='${profissional.valorConsultaOnline}';
						                    profissional.valorConsultaPresencial='${profissional.valorConsultaPresencial}';
						                    profissional.tempoAntecendencia='${profissional.tempoAntecendencia}';
						                    profissional.tipoConta='${profissional.tipoConta}';
						                    profissional.banco='${profissional.banco}';
						                    profissional.agencia='${profissional.agencia}';
						                    profissional.conta='${profissional.conta}';
     					                    profissional.digitoVerificador='${profissional.digitoVerificador}';
						                    profissional.nomeFavorecido='${profissional.nomeFavorecido}';
						                    profissional.convenio20='${profissional.convenio20}';
										    profissional.convenio30='${profissional.convenio30}';
										    profissional.convenio40='${profissional.convenio40}';
										    profissional.convenio50='${profissional.convenio50}';
										    profissional.convenio60='${profissional.convenio60}';
										    profissional.pacote2com30Desconto='${profissional.pacote2com30Desconto}';
										    profissional.pacote3com40Desconto='${profissional.pacote3com40Desconto}';
										    profissional.pacote4com50Desconto='${profissional.pacote4com50Desconto}';
										    profissional.primeiraConsultaCortesia='${profissional.primeiraConsultaCortesia}';
										    profissional.quantidadeConsultaCortesiaMes='${profissional.quantidadeConsultaCortesiaMes}';
										    profissional.atendimentoPorLibras='${profissional.atendimentoPorLibras}';
										    profissional.habilitarDesconto40='${profissional.habilitarDesconto40}';
										    profissional.quantidadeConsultaDesconto40Mes='${profissional.quantidadeConsultaDesconto40Mes}';
										    profissional.avisoFerias='${profissional.avisoFerias}';
						                    profissional.pessoa.id='${profissional.pessoa.id}';
						                    profissional.pessoa.nome='${profissional.pessoa.nome}';
						                    profissional.pessoa.email='${profissional.pessoa.email}';
						                    profissional.pessoa.telefone='${profissional.pessoa.telefone}';
						                    profissional.pessoa.celular='${profissional.pessoa.celular}';
						                    profissional.pessoa.senha='${profissional.pessoa.senha}';
						                    profissional.pessoa.dataNascimento='${profissional.pessoa.dataNascimento}';
						                    profissional.pessoa.cpf='${profissional.pessoa.cpf}';
						                    profissional.pessoa.cep='${profissional.pessoa.cep}';
						                    profissional.pessoa.cidade='${profissional.pessoa.cidade}';
						                    profissional.pessoa.estado='${profissional.pessoa.estado}';
						                    profissional.pessoa.endereco='${profissional.pessoa.endereco}';
						                    profissional.pessoa.setor='${profissional.pessoa.setor}';
						                    profissional.pessoa.foto.id='${profissional.pessoa.foto.id}';
						                    profissional.pessoa.foto.link='${profissional.pessoa.foto.link}';
                          					profissional.pessoa.cliente ='${profissional.pessoa.cliente}';
                         					profissional.pessoa.psicologo ='${profissional.pessoa.psicologo}';
                         					formacaoAcademica ='${formacaoAcademica}';
                         					enderecoAtendimento ='${enderecoAtendimento}';
						                     "/>
							<div class="row">
						          <div class="col-md-12 col-lg-12 col-sm-12">
						            <div class="col-md-6 col-lg-6 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="nome" class="control-label">Nome</label>
						                <input type="text" id="nome" ng-model="profissional.pessoa.nome" class="form-control input-group-lg" required/>
						              </div>
						            </div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
						               <div class=" form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label>Cpf</label>
						                <input type="text" class="form-control" data-mask="000.000.000-00"  id="cpf"  ng-model="profissional.pessoa.cpf">
						              </div>
						         	</div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
						               <div class=" form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label>Data Nascimento</label>
						                <input type="text" class="form-control" data-mask="00/00/0000"  id="dataAniversario"  ng-model="profissional.pessoa.dataNascimento" ng-blur="">
						              </div>
						         	</div>
					       		 </div>
					       		 
					       		 <div class="col-md-12 col-lg-12 col-sm-12">
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="email" class="control-label">Email</label>
						                <input type="email" id="email" ng-model="profissional.pessoa.email" class="form-control input-group-lg" required/>
						              </div>
						            </div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
								        <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
								           <label for="senha" class="control-label">Senha</label>
								           <input type="password" id="senha" ng-model="profissional.pessoa.senha" class="form-control input-group-lg" required/>
								        </div>
								    </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="telefone" class="control-label">Telefone</label>
						                <input type="Large" id="telefone" ng-model="profissional.pessoa.telefone" data-mask="(00) 00000-0000" class="form-control input-group-lg"/>
						              </div>
						            </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="celular" class="control-label">Celular</label>
						                <input type="Large" id="celular" ng-model="profissional.pessoa.celular" data-mask="(00) 00000-0000" class="form-control input-group-lg"/>
						              </div>
						            </div>
					       		 </div>
							<div class="col-md-12 col-lg-12 col-sm-12">
								   <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="cep" class="control-label">CEP</label>
						                <input type="Large" id="cep" ng-model="profissional.pessoa.cep" data-mask="00.000-000" class="form-control input-group-lg" required/>
						              </div>
						            </div>
						             <div class="col-md-3 col-lg-3 col-sm-12">
								        <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
								           <label for="endereco" class="control-label">Endereco</label>
								           <input type="Large" id="endereco" ng-model="profissional.pessoa.endereco" class="form-control input-group-lg" required/>
								        </div>
								    </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="setor" class="control-label">Bairro</label>
						                <input type="Large" id="setor" ng-model="profissional.pessoa.setor" class="form-control input-group-lg"/>
						              </div>
						            </div>
						            <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="cidade" class="control-label">Cidade</label>
						                <input type="Large" id="cidade" ng-model="profissional.pessoa.cidade" class="form-control input-group-lg"/>
						              </div>
						            </div>
							</div>
							
							<div class="col-md-12 col-lg-12 col-sm-12">
							          <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Documento do (CRP/CRM)</label>
                                                <input type="text" ng-model="profissional.documentoCrpCrm"  class="form-control input-group-lg"/>
                                            </div>
                                        </div>

                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Cadastro do E-Psi</label>
                                                <input type="text" ng-model="profissional.cadastroEpsi"  class="form-control input-group-lg"/>
                                            </div>
                                        </div>

                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Título Profissional</label>
                                                <input type="text" ng-model="profissional.tituloProfissional"  class="form-control input-group-lg"/>
                                            </div>
                                        </div>

                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Tipo Profissional</label>
                                                <select ng-model="profissional.tipoProfissional" class="form-control input-group-lg">
													<c:forEach var="tipoProfissional" items="${tiposProfissional}">
				   							          <option value="${tipoProfissional}">${tipoProfissional.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>
							</div>
							
							<div class="col-md-12 col-lg-12 col-sm-12">
							  <div class="col-md-4 col-lg-4 col-sm-12">
                                 <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                    <label class="control-label">Especialidade</label>
                                       <select ng-model="profissional.especialidade" class="form-control input-group-lg">
								 		<c:forEach var="especialidade" items="${especialidades}">
				   				          <option value="${especialidade}">${especialidade.valor}</option>
								        </c:forEach>
								      </select>
                                   </div>
                                 </div>
								<div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                               <label class="control-label">Temas de Trabalho</label>
                                               <select ng-model="profissional.temasTrabalho" class="form-control input-group-lg">
													<c:forEach var="temas" items="${temasTrabalho}">
				   							          <option value="${temas}">${temas.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>

                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Tempo de duração do atendimento</label>
                                                <select ng-model="profissional.duracaoAtendimento" class="form-control input-group-lg">
													<c:forEach var="duracaoAtendimento" items="${duracoes}">
				   							          <option value="${duracaoAtendimento}">${duracaoAtendimento.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>
							    </div>
							    <div class="col-md-12 col-lg-12 col-sm-12">
                                    <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                       <label class="control-label">Bibliografia</label>
                                           <textarea class="form-control input-group-lg" cols="20" rows="5" ng-model="profissional.biografia" placeholder="Informe aqui sua vida profissional. Procure ser mais claro possível." style="color: #A6A6A6"></textarea>
                                     </div>
                                 </div>
                                 
                                 <div class="col-md-12 col-lg-12 col-sm-12">
                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Tempo de consulta:</label>
                                                <select ng-model="profissional.duracaoAtendimentoValor" class="form-control input-group-lg">
													<c:forEach var="duracaoAtendimento" items="${duracoes}">
				   							          <option value="${duracaoAtendimento}">${duracaoAtendimento.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Valor da consulta online:</label>
                                                <input type="text" ng-model="profissional.valorConsultaOnline" id="valorconsultaOnline" class="valorMonetario form-control input-group-lg"  />
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Valor da consulta presencial:</label>
                                                <input type="text" ng-model="profissional.valorConsultaPresencial" class="valorMonetario form-control input-group-lg"   />
                                            </div>
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Tempo de antecendencia:</label>
                                                <select ng-model="profissional.tempoAntecendencia" class="form-control input-group-lg">
													<c:forEach var="tempo" items="${tempoAntecendencia}">
				   							          <option value="${tempo}">${tempo.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>
                                </div>
                                
                                <div class="col-md-12 col-lg-12 col-sm-12">
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Tipo da conta:</label>
                                                 <select ng-model="profissional.tipoConta" class="form-control input-group-lg">
														<c:forEach var="tipoConta" items="${tiposConta}">
				   							                <option value="${tipoConta}">${tipoConta.valor}</option>
										                </c:forEach>
									                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Banco:</label>
                                               <select ng-model="profissional.banco" class="form-control input-group-lg">
														<c:forEach var="banco" items="${bancos}">
				   							                <option value="${banco}">${banco.valor}</option>
										                </c:forEach>
									            </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Agência:</label>
                                                <input type="text" ng-model="profissional.agencia"  class="form-control input-group-lg" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">    
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Conta:</label>
                                                <input type="text" ng-model="profissional.conta"  class="form-control input-group-lg" />
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Conta (dígito verificador):</label>
                                                <input type="text" ng-model="profissional.digitoVerificador" class="form-control input-group-lg"/>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Nome do favorecido</label>
                                                <input type="text" ng-model="profissional.nomeFavorecido"  class="form-control input-group-lg"/>
                                            </div>
                                        </div>
                                     </div>   
                             	
                             	<div class="col-md-12 col-lg-12 col-sm-12">
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox"  ng-model="profissional.convenio20" id="convenio20" ng-checked="${profissional.convenio20}"/>
                                                <label>atendimento para os convênios no valor de R$20,00</label>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.convenio30" id="convenio30" ng-checked="${profissional.convenio30}"/>
                                                <label>atendimento para os convênios no valor de R$30,00</label>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.convenio40" id="convenio40" ng-checked="${profissional.convenio40}"/>
                                                <label>atendimento para os convênios no valor de R$40,00</label>
                                            </div>
                                        </div>
                                 </div>
                                 <div class="col-md-12 col-lg-12 col-sm-12">       
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.convenio50" id="convenio50" ng-checked="${profissional.convenio50}"/>
                                                <label>atendimento para os convênios no valor de R$50,00</label>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.convenio60" id="convenio60" ng-checked="${profissional.convenio60}"/>
                                                <label>atendimento para os convênios no valor de R$60,00</label>
                                            </div>
                                        </div>
                                  </div>
                                  
                                  <div class="col-md-12 col-lg-12 col-sm-12">

                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.pacote2com30Desconto" id="pacote2com30Desconto" ng-checked="${profissional.pacote2com30Desconto}"/>
                                                <label>Pacote com 2 consultas por 30% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.pacote3com40Desconto" id="pacote3com40Desconto" ng-checked="${profissional.pacote3com40Desconto}"/>
                                                <label>Pacote com 3 consultas por 40% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.pacote4com50Desconto" id="pacote4com50Desconto" ng-checked="${profissional.pacote4com50Desconto}"/>
                                                <label>Pacote com 4 consultas por 50% de desconto cada</label>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-12 col-lg-12 col-sm-12">
                                            <div class="checkbox col-md-4 col-lg-4 col-sm-12">
                                                <input type="checkbox" ng-model="profissional.primeiraConsultaCortesia" id="primeiraConsultaCortesia" ng-checked="${profissional.primeiraConsultaCortesia}"/>
                                                <label>primeira consulta cortesia dos novos pacientes</label>
                                            </div>
                                             <div class="col-md-4 col-lg-4 col-sm-12" style="padding-top: 6px;">
                                               <label>Quantidade de consultas cortesias no mês:</label>
                                               <input type="text" style="width: 29px;height: 24px;text-align: center;" ng-model="profissional.quantidadeConsultaCortesiaMes"/>
                                             </div>
                                             <div class="checkbox col-md-4 col-lg-4 col-sm-12">
                                                <input type="checkbox" ng-model="profissional.atendimentoPorLibras" id="atendimentoPorLibras"  ng-checked="${profissional.atendimentoPorLibras}" />
                                                <label>Confirmar que atende em Libras</label>
                                            </div>
                                    </div>
                                    
                                    <div class="col-md-12 col-lg-12 col-sm-12">
                                            <div class="checkbox col-md-4 col-lg-4 col-sm-12">
                                                <input type="checkbox" ng-model="profissional.habilitarDesconto40" id="habilitarDesconto40" ng-checked="${profissional.habilitarDesconto40}" />
                                                <label>Habilitar desconto de R$40,00</label>
                                            </div>
                                            <div class="col-md-4 col-lg-4 col-sm-12" style="padding-top: 6px;">
                                              <label>Quantidade de consultas cortesias no mês:</label>
                                              <input type="text" style="width: 29px;height: 24px;text-align: center;" ng-model="profissional.quantidadeConsultaDesconto40Mes"/>
                                           </div>
                                    
                                            <div class="checkbox col-md-4 col-lg-4 col-sm-12">
                                                <input type="checkbox" ng-model="profissional.avisoFerias" id="avisoFerias" ng-checked="${profissional.avisoFerias}"/>
                                                <label>Incluir data início e data fim do afastamento</label>
                                            </div>
                                    </div>
                                   <div class="col-md-12 col-lg-12 col-sm-12">  
	                                    <div class="pmd-card pmd-z-depth pmd-card-custom-view">
								            <div class="table-responsive">
								                <table class="table table-bordered">
											        <tr>
													    <th class="text-center">ID</th>
													    <th class="text-center">Tipo Formação</th>
													    <th class="text-center">Descrição Formação</th>
													</tr>
													<c:forEach var="formacao" items="${formacao}">
													<tr>
														<td class="text-center">${formacao.id}</td>
														<td align="center">${formacao.tipoFormacao}</td>
														<td align="center">${formacao.descricaoFormacao}</td>
													</tr>
													</c:forEach>
												</table>
								            </div>
								        </div>
                                    </div>
                                   <div class="col-md-12 col-lg-12 col-sm-12">  
	                                    <div class="pmd-card pmd-z-depth pmd-card-custom-view">
								            <div class="table-responsive">
								                <table class="table table-bordered">
											        <tr>
													    <th class="text-center">Cep</th>
													    <th class="text-center">Lagradouro</th>
													    <th class="text-center">Complemento</th>
													    <th class="text-center">Numero</th>
													    <th class="text-center">Bairro</th>
													    <th class="text-center">Cidade</th>
													    <th class="text-center">Estado</th>
													    <th class="text-center">Link Google Maps</th>
													</tr>
													<c:forEach var="endereco" items="${enderecoAtendimento}">
													<tr>
														<td class="text-center">${endereco.cep}</td>
														<td align="center">${endereco.logradouro}</td>
														<td align="center">${endereco.complemento}</td>
														<td align="center">${endereco.numero}</td>
														<td class="text-center">${endereco.bairro}</td>
														<td align="center">${endereco.cidade}</td>
														<td align="center">${endereco.estado}</td>
														<td align="center">${endereco.linkGoogleMaps}</td>
													</tr>
													</c:forEach>
												</table>
								            </div>
								        </div>
                                    </div>
								
							<div class="col-md-12 col-lg-12 col-sm-12" style="text-align: center;padding-top: 30px;padding-bottom: 50px;">
							    <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
										<button type="submit" class="btn btn-success pmd-checkbox-ripple-effect">Salvar</button>
										<a href="<ilion:url/>profissional" class="btn btn-warning">Cancelar</a>
								</div>		
						   </div>	
					</div>	
				</form>		
			</div>
</div>
<jsp:include page="../../../ilionnet2/inc/include-footer.jsp" flush="true"/>
<jsp:include page="../../../ilionnet2/inc/include-js-footer.jsp" flush="true"/>

<script src="/assets/js/angular.min.js"></script>
<script src="/assets/js/bundle.libs.ilionnet.js"></script>
<script src="/assets/js/bundle.scripts.ilionnet.js"></script>
<script src="/assets/js/bundle.libs.angular.js"></script>
<script src="/assets/js/vitazure/cliente.js"></script>
<script>
$('.valorMonetario').mask('#.##0.00', {reverse: true});
</script>
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

</body>

</html>
