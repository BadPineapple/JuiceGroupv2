<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" ng-app="clienteApp" ng-controller="ClienteController">
    <head>
        <title>Profissional - <ilion:nomeEmpresa/></title>
        <jsp:include page="../../../ilionnet2/inc/include-head.jsp" flush="true"/>
        
     <style>
     .divTitulo{
    	 display: flex;
	     flex-direction: row;
	     justify-content: space-between;
	     align-items: center;
	     padding-bottom: 5px;
	     padding-top: 7px;
	     border-bottom: 1px solid #1795d4;
	     margin-bottom: 2rem;	 
     }
     </style>   
        
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
		  <li>Vitazure</li>
		  <li><a href="<ilion:url/>profissional">Profissional</a></li>
		  <li class="active">Edi&ccedil;&atilde;o</li>
		</ol><!--breadcrum end--> 
		
		<div class="pmd-card pmd-z-depth">
				<div class="pmd-card-body">	
					<form ng-submit="submitProfissional()">
						          <angular-initializer
						            ng-init=" profissional.id='${profissional.id}';
						                     profissional.documentoCrpCrm='${profissional.documentoCrpCrm}';
						                    profissional.conselhoProfissional='${profissional.conselhoProfissional}';
						                    profissional.cadastroEpsi='${profissional.cadastroEpsi}';
						                    profissional.tituloProfissional='${profissional.tituloProfissional}';
						                    profissional.tipoProfissional='${profissional.tipoProfissional}';
						                    profissional.especialidade='${profissional.especialidade}';
						                    profissional.temasTrabalho='${profissional.temasTrabalho}';
						                    profissional.duracaoAtendimento='${profissional.duracaoAtendimento}';
						                    profissional.biografia='${profissional.biografia}';						                    						                    
						                    profissional.duracaoAtendimentoValor='${profissional.duracaoAtendimentoValor}';
						                    profissional.valorConsultaOnline='${profissional.valorOnlineFormatado}';
						                    profissional.valorConsultaPresencial='${profissional.valorPresencialFormatado}';
						                    profissional.tempoAntecendencia='${profissional.tempoAntecendencia}';
						                    profissional.adolescentes='${profissional.adolescentes}';
						                    profissional.adultos='${profissional.adultos}';
						                    profissional.casais='${profissional.casais}';
						                    profissional.idosos='${profissional.idosos}';
						                    profissional.crianca='${profissional.crianca}';
						                    profissional.tipoConta='${profissional.tipoConta}';
						                    profissional.banco='${profissional.banco}';
						                    profissional.agencia='${profissional.agencia}';
						                    profissional.conta='${profissional.conta}';
     					                    profissional.digitoVerificador='${profissional.digitoVerificador}';
						                    profissional.nomeFavorecido='${profissional.nomeFavorecido}';
										    profissional.convenio40='${profissional.convenio40}';
										    profissional.convenio50='${profissional.convenio50}';
										    profissional.convenio60='${profissional.convenio60}';
										    profissional.convenio56='${profissional.convenio56}';
										    profissional.pacote2com5Desconto='${profissional.pacote2com5Desconto}';
										    profissional.pacote3com10Desconto='${profissional.pacote3com10Desconto}';
										    profissional.pacote4com15Desconto='${profissional.pacote4com15Desconto}';
										    profissional.primeiraConsultaCortesia='${profissional.primeiraConsultaCortesia}';
										    profissional.quantidadeConsultaCortesiaMes='${profissional.quantidadeConsultaCortesiaMes}';
										    profissional.atendimentoPorLibras='${profissional.atendimentoPorLibras}';
										    profissional.habilitarDesconto40='${profissional.habilitarDesconto40}';
										    profissional.quantidadeConsultaDesconto40Mes='${profissional.quantidadeConsultaDesconto40Mes}';
										    profissional.avisoFerias='${profissional.avisoFerias}';
										    profissional.dataInicioAvisoFerias='${profissional.dataInicioAvisoFerias}';
										    profissional.dataFimAvisoFerias='${profissional.dataFimAvisoFerias}';
										    profissional.dataValidadeEpsi='${profissional.dataValidadeEpsi}';
										    profissional.ativo='${profissional.ativo}';
										    profissional.dadosCompleto='${profissional.dadosCompleto}';
										    profissional.plano='${profissional.plano}';
										    profissional.dataInicioPlano='${profissional.dataInicioPlano}';
										    profissional.dataFimPlano='${profissional.dataFimPlano}';
										    profissional.tokenTransacaoPlano='${profissional.tokenTransacaoPlano}';
										    profissional.idRecebedor='${profissional.idRecebedor}';
										    profissional.idConta='${profissional.idConta}';
										    profissional.situacaoAprovacaoProfissional='${profissional.situacaoAprovacaoProfissional}';
										    profissional.aceiteContrato='${profissional.aceiteContrato}';
										    profissional.dataAceiteContrato='${profissional.dataAceiteContrato}';
										    profissional.ipAceiteContrato='${profissional.ipAceiteContrato}';
										    profissional.ipExternoAceiteContrato='${profissional.ipExternoAceiteContrato}';
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
						                    profissional.pessoa.foto.layout='${profissional.pessoa.foto.layout}';
						                    profissional.pessoa.genero='${profissional.pessoa.genero}';
                          					profissional.pessoa.cliente ='${profissional.pessoa.cliente}';
                         					profissional.pessoa.psicologo ='${profissional.pessoa.psicologo}';
                         					profissional.pessoa.confirmado='${profissional.pessoa.confirmado}';
                         					profissional.pessoa.relacaoContato='${profissional.pessoa.relacaoContato}';
						                    profissional.pessoa.nomeContato='${profissional.pessoa.nomeContato}';
						                    profissional.pessoa.celularContato='${profissional.pessoa.celularContato}';
						                    profissional.pessoa.clienteAtivo='${profissional.pessoa.clienteAtivo}';
                         					formacaoAcademica ='${formacaoAcademica}';
                         					 enderecoAtendimento ='';
                         					descricaoFormacao ='${descricaoFormacao}';
                         					formacaoAcademica ='${formacaoAcademica}';
                         					enderecoAtendimento ='${enderecoAtendimento}';
						                     "/>
							<div class="row">
							  <div class="col-md-12 col-lg-12 col-sm-12">
							    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
							  	 <strong style="color: #1795d4;">Dados Basicos</strong>
							  	</div> 
							  </div>
						          <div class="col-md-12 col-lg-12 col-sm-12">
						            <div class="col-md-3 col-lg-3 col-sm-12">
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
						             <div class="col-md-3 col-lg-3 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Situação Autorização</label>
                                                <select ng-model="profissional.situacaoAprovacaoProfissional" class="form-control input-group-lg">
                                                   <c:forEach var="situacao" items="${situacaoAtendimento}">
				   							          <option value="${situacao}">${situacao.nome}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                      </div>
					       		 </div>
<!-- 					       		 <div class="col-md-12 col-lg-12 col-sm-12"> -->
<!-- 						             <div class="col-md-3 col-lg-3 col-sm-12"> -->
<!--                                             <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed"> -->
<!--                                                 <label class="control-label">Plano</label> -->
<!--                                                 <select ng-model="profissional.plano" class="form-control input-group-lg"> -->
<!-- 				   							          <option value=""> </option> -->
<!-- 				   							          <option value="plano_mensal">Plano Mensal</option> -->
<!-- 				   							          <option value="plano_mensal_gratuito">Plano Mensal Gratuito</option> -->
<!-- 				   							          <option value="plano_semestral">Plano Semestral</option> -->
<!-- 				   							          <option value="plano_semestral_gratuito">Plano Semestral Gratuito</option> -->
<!-- 				   							          <option value="plano_anual">Plano Anual</option> -->
<!-- 				   							          <option value="plano_anual_gratuito">Plano Anual Gratuito</option> -->
<!-- 									           </select> -->
<!--                                             </div> -->
<!--                                       </div> -->
<!-- 						             <div class="col-md-3 col-lg-3 col-sm-12"> -->
<!-- 						               <div class=" form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed"> -->
<!-- 						                <label>Data Inicio Plano</label> -->
<!-- 						                <input type="text" class="form-control" data-mask="00/00/0000"  id="dataInicioPlano"  ng-model="profissional.dataInicioPlano" ng-blur=""> -->
<!-- 						              </div> -->
<!-- 						         	</div> -->
<!-- 						             <div class="col-md-3 col-lg-3 col-sm-12"> -->
<!-- 						               <div class=" form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed"> -->
<!-- 						                <label>Data Fim Plano</label> -->
<!-- 						                <input type="text" class="form-control" data-mask="00/00/0000"  id="dataFimPlano"  ng-model="profissional.dataFimPlano" ng-blur=""> -->
<!-- 						              </div> -->
<!-- 						         	</div> -->
<!-- 					       		 </div> -->
					       		 
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
						                <label for="cep" class="control-label">Data Aceite Contrato</label>
						                <input type="Large" id="dataAceiteContrato" disabled="true" ng-model="profissional.dataAceiteContrato" class="form-control input-group-lg" required/>
						              </div>
						            </div>
								   <div class="col-md-3 col-lg-3 col-sm-12">
						              <div class="  form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
						                <label for="cep" class="control-label">Ip Aceite Contrato</label>
						                <input type="Large" id="ipAceiteContrato" disabled="true" ng-model="profissional.ipAceiteContrato" class="form-control input-group-lg" required/>
						              </div>
						            </div>
							</div>
							<div class="col-md-12 col-lg-12 col-sm-12">
							    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
							  	 <strong style="color: #1795d4;">Dados Profissionais</strong>
							  	</div> 
							  </div>
							<div class="col-md-12 col-lg-12 col-sm-12">
									 <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Tipo Profissional</label>
                                                <select ng-model="profissional.tipoProfissional" class="form-control input-group-lg">
													<c:forEach var="tipoProfissional" items="${tiposProfissional}">
				   							          <option value="${tipoProfissional}">${tipoProfissional.valor}</option>
										            </c:forEach>
									           </select>
                                            </div>
                                        </div>
							          <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label>Conselho profissional</label>
	                                            <select ng-model="profissional.conselhoProfissional" class="form-control input-group-lg">
	                                              <c:forEach items="${conselhoProfissional}" var="conselho">
	                                                <option ng-if="profissional.tipoProfissional == 'PSICOLOGO'" value="${conselho}">${conselho.valor} - ${conselho.CRP}</option>
	                                                <option ng-if="profissional.tipoProfissional == 'MEDICO'" value="${conselho}">${conselho.valor} - ${conselho.CRM}</option>
	                                              </c:forEach>
	                                            </select>
                                            </div>
                                        </div>
							          <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Registro profissional (CRP/CRM)</label>
                                                <input type="text" ng-model="profissional.documentoCrpCrm"  class="form-control input-group-lg"/>
                                            </div>
                                        </div>

							</div>
							<div class="col-md-12 col-lg-12 col-sm-12">
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Cadastro do E-Psi</label>
                                                <input type="text" ng-model="profissional.cadastroEpsi"  class="form-control input-group-lg"/>
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Data de validade do e-Psi</label>
                                                <input class="form-control input-group-lg" type="text" data-mask="00/00/0000" ng-model="profissional.dataValidadeEpsi" id="dataValidadeEpsi">
			                                    </input>  
                                            </div>
                                        </div>
							</div>
							<div class="col-md-12 col-lg-12 col-sm-12" style="padding-left:30px;">
											<div lass="col-md-12 col-lg-12 col-sm-12">
										  		<div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
			                                                 <label class="control-label">Faixa de atendimento</label>
			                                	</div>
			                                 </div> 	                 
	                                            <div class="col-md-3 col-lg-3 col-sm-12">
	                                               <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
	                                                <input type="checkbox" ng-model="profissional.adolescentes" id="adolescentes" ng-checked="${profissional.adolescentes}"/>
	                                                <label>adolescentes</label>
	                                               </div> 
	                                            </div>
	                                            <div class="col-md-2 col-lg-2 col-sm-12">
	                                               <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
	                                                <input type="checkbox" ng-model="profissional.adultos" id="adultos" ng-checked="${profissional.adultos}"/>
	                                                <label>adultos</label>
	                                               </div> 
	                                            </div>
	                                            <div class="col-md-2 col-lg-2 col-sm-12">
	                                              <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
	                                                <input type="checkbox" ng-model="profissional.casais" id="casais" ng-checked="${profissional.casais}"/>
	                                                <label>casais</label>
	                                              </div>  
	                                            </div>
	                                            <div class="col-md-2 col-lg-2 col-sm-12">
	                                              <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
	                                                <input type="checkbox" ng-model="profissional.idosos" id="idosos" ng-checked="${profissional.idosos}"/>
	                                                <label>idosos</label>
	                                              </div>  
	                                            </div>
	                                            <div class="col-md-3 col-lg-3 col-sm-12">
	                                              <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
	                                                <input type="checkbox" ng-model="profissional.crianca" id="crianca" ng-checked="${profissional.crianca}"/>
	                                                <label>crianças</label>
	                                               </div> 
	                                            </div>
	                            </div>                 
							 <div class="col-md-12 col-lg-12 col-sm-12">
							    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
							  	 <strong style="color: #1795d4;">Sobre Mim</strong>
							  	</div> 
							  </div>
							    <div class="col-md-12 col-lg-12 col-sm-12">
                                    <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                           <textarea class="form-control input-group-lg" cols="20" rows="5" ng-model="profissional.biografia" placeholder="Informe aqui sua vida profissional. Procure ser mais claro possível." style="color: #A6A6A6"></textarea>
                                     </div>
                                 </div>
                                 <div class="col-md-12 col-lg-12 col-sm-12">
							    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
							  	 <strong style="color: #1795d4;">Tempo de duração do atendimento</strong>
							  	</div> 
							  </div>
                                 <div class="col-md-12 col-lg-12 col-sm-12">
                                        <div class="col-md-12 col-lg-12 col-sm-12">
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
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Valores de consulta</strong>
									  	</div> 
								  </div>
								   <div class="col-md-12 col-lg-12 col-sm-12">	        
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Valor da consulta online:</label>
                                                <input type="text" ng-model="profissional.valorConsultaOnline" id="valorconsultaOnline" class="valorMonetario form-control input-group-lg"  />
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
                                                <label class="control-label">Valor da consulta presencial:</label>
                                                <input type="text" ng-model="profissional.valorConsultaPresencial" id="valorConsultaPresencial" class="valorMonetario form-control input-group-lg"   />
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-lg-4 col-sm-12">
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
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Dados bancários</strong>
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
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Convênios</strong>
									  	</div> 
									  </div>
                                 <div class="col-md-12 col-lg-12 col-sm-12" style="padding-left:30px;">       
<!--                                         <div class="col-md-4 col-lg-4 col-sm-12"> -->
<!--                                             <div class="checkbox"> -->
<%--                                                 <input type="checkbox" ng-model="profissional.convenio40" id="convenio40" ng-checked="${profissional.convenio40}"/> --%>
<!--                                                 <label>atendimento para os convênios no valor de R$45,00</label> -->
<!--                                             </div> -->
<!--                                         </div> -->
                                        <div class="col-md-12 col-lg-12 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.convenio56" id="convenio56" ng-checked="${profissional.convenio56}"/>
                                                <label>atendimento para os convênios no valor de R$56,00</label>
                                            </div>
                                        </div>
<!--                                         <div class="col-md-4 col-lg-4 col-sm-12"> -->
<!--                                             <div class="checkbox"> -->
<%--                                                 <input type="checkbox" ng-model="profissional.convenio60" id="convenio60" ng-checked="${profissional.convenio60}"/> --%>
<!--                                                 <label>atendimento para os convênios no valor de R$67,00</label> -->
<!--                                             </div> -->
<!--                                         </div> -->
                                  </div>
                                  <div class="col-md-12 col-lg-12 col-sm-12">
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Pacotes</strong>
									  	</div> 
									  </div>
                                  <div class="col-md-12 col-lg-12 col-sm-12" style="padding-left:30px;">

                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.pacote2com5Desconto" id="pacote2com5Desconto" ng-checked="${profissional.pacote2com5Desconto}"/>
                                                <label>Pacote com 2 consultas por 5% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.pacote3com10Desconto" id="pacote3com10Desconto" ng-checked="${profissional.pacote3com10Desconto}"/>
                                                <label>Pacote com 3 consultas por 10% de desconto cada</label>
                                            </div>
                                        </div>
    
                                        <div class="col-md-4 col-lg-4 col-sm-12">
                                            <div class="checkbox">
                                                <input type="checkbox" ng-model="profissional.pacote4com15Desconto" id="pacote4com15Desconto" ng-checked="${profissional.pacote4com15Desconto}"/>
                                                <label>Pacote com 4 consultas por 15% de desconto cada</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Primeira consulta cortesia/Atendimento por libras</strong>
									  	</div> 
									  </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12" style="padding-left:30px;">
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
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Aviso de férias/Afastamento</strong>
									  	</div> 
									  </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">
                                            <div class="checkbox col-md-4 col-lg-4 col-sm-12" style="padding-top: 39px;padding-left: 32px;">
                                                <input type="checkbox" ng-model="profissional.avisoFerias" id="avisoFerias" ng-checked="${profissional.avisoFerias}"/>
                                                <label>Incluir data início e data fim do afastamento</label>
                                            </div>
                                            <div class="col-md-4 col-lg-4 col-sm-12" id="divCampoDataInicialAviso">
			                                    <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
			                                        <label class="control-label">Data Inicio</label>
			                                        <input class="form-control input-group-lg" type="text" data-mask="00/00/0000" ng-model="profissional.dataInicioAvisoFerias" id="dataInicioAvisoFerias"/>
			                                    </div>
			                                </div>
											
											<div class="col-md-4 col-lg-4 col-sm-12" id="divCampoFinalDataAviso">
			                                       <div class="form-group pmd-textfield pmd-textfield-floating-label form-group-lg textfield-floating-label-completed pmd-textfield-floating-label-completed">
			                                        <label class="control-label">Data Fim</label>
			                                        <input class="form-control input-group-lg" type="text" data-mask="00/00/0000" ng-model="profissional.dataFimAvisoFerias" id="dataFimAvisoFerias"/>
			                                    </div>
			                                </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">
								    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
								  	 <strong style="color: #1795d4;">Especialidade</strong>
								  	</div> 
								  </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">  
	                                    <div class="pmd-card pmd-z-depth pmd-card-custom-view">
								            <div class="table-responsive">
								                <table class="table table-bordered">
											        <tr>
													    <th class="text-center">ID</th>
													    <th class="text-center">Especialidade</th>
													</tr>
													<c:forEach var="especialidade" items="${especialidades}">
													<tr>
														<td class="text-center">${especialidade.id}</td>
														<td align="center">${especialidade.especialidade}</td>
													</tr>
													</c:forEach>
												</table>
								            </div>
								        </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">
								    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
								  	 <strong style="color: #1795d4;">Temas</strong>
								  	</div> 
								  </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">  
	                                    <div class="pmd-card pmd-z-depth pmd-card-custom-view">
								            <div class="table-responsive">
								                <table class="table table-bordered">
											        <tr>
													    <th class="text-center">ID</th>
													    <th class="text-center">Tema</th>
													</tr>
													<c:forEach var="tema" items="${temasTrabalho}">
													<tr>
														<td class="text-center">${tema.id}</td>
														<td align="center">${tema.tema}</td>
													</tr>
													</c:forEach>
												</table>
								            </div>
								        </div>
                                    </div>
                                    <div class="col-md-12 col-lg-12 col-sm-12">
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Formação</strong>
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
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Endereço</strong>
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
                                    <div class="col-md-12 col-lg-12 col-sm-12">
									    <div class="col-md-12 col-lg-12 col-sm-12 divTitulo">
									  	 <strong style="color: #1795d4;">Horário Atendimento</strong>
									  	</div> 
									  </div>
                                   <div class="col-md-12 col-lg-12 col-sm-12">  
	                                    <div class="pmd-card pmd-z-depth pmd-card-custom-view">
								            <div class="table-responsive">
								                <table class="table table-bordered">
											        <tr>
													    <th class="text-center">Dia</th>
													    <th class="text-center">Hora Inicio</th>
													    <th class="text-center">Hora Fim</th>
													    <th class="text-center">Online</th>
													    <th class="text-center">Presencial</th>
													    <th class="text-center">Logradouro</th>
													</tr>
													<c:forEach var="enderecoAtendimento" items="${horarioAtendimento}">
													<tr>
														<td class="text-center">${enderecoAtendimento.diaSemana}</td>
														<td align="center">${enderecoAtendimento.horaInicio}</td>
														<td align="center">${enderecoAtendimento.horaFim}</td>
														<td class="text-center">${enderecoAtendimento.atendimentoOnline == 'true' ? 'Sim' : 'Não'}</td>
														<td align="center">${enderecoAtendimento.atendimentoPresencial == 'true' ? 'Sim' : 'Não'}</td>
														<td align="center">${enderecoAtendimento.enderecoAtendimento.logradouro}</td>
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
$('.valorMonetario').mask('#.##0,00', {reverse: true});
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
