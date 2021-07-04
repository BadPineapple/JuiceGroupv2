<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
<style type="text/css">
.boxItemCurriculo{width:800px;margin:15px 0;padding:0;float:left;clear:both;font-size:11px;line-height:normal;text-align: center;}
.boxItemCurriculo h5{font-size:14px;color:#3F3F3F;}
.boxItemCurriculo .txtSmall{font-size:10px;color:#3F3F3F;}
</style>
</head>

<body>
<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo  -->

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Curriculo - Detalhes </td>
  </tr>
</table>


<div style="margin: 0 0 8px 0;">
<input type="button" class="botao" value=" &lt;&lt; Voltar" onclick="location='curriculo.sp'" style="margin-right: 5px;"/>

<input type="button" class="botao" onclick="if(confirm('Deseja realmente EXCLUIR este currículo?')){location='curriculo-excluir.sp?id=<c:out value='${curriculo.id}'/>';} else {return false;}" value="Excluir" />

</div>

<!--- inicio curriculo --->
<div >
	
</div>
<div class="boxItemCurriculo">
<h5>Dados Pessoais</h5>
   <table align="center" width="800" border="0" cellspacing="0" cellpadding="3">
  <tr>
    <td width="30%" align="right" valign="middle"><b>Nome:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.nome}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>CPF:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.cpf}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>E-mail:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.email}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Sexo:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.sexo}"/></td>
  </tr>
  
  <tr>
    <td align="right" valign="middle"><b>Estado Civil:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.estadoCivilEnum}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Data de Nascimento:</b></td>
    <td align="left" valign="middle"><fmt:formatDate value="${curriculo.dataNascimento}" pattern="dd/MM/yyyy"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Naturalidade:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.naturalidadeCidade}"/> / <c:out value="${curriculo.naturalidadeEstado}"/></td>
  </tr>
  <%-- <tr>
    <td align="right" valign="middle"><b>Logradouro:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.logradouro}"/> 
                    &nbsp;&nbsp;&nbsp;<b>Qd.:</b> 
                    <c:out value="${curriculo.quadra}"/> 
                    &nbsp;&nbsp;<b>Lt.:</b> 
                    <c:out value="${curriculo.lote}"/>
                    &nbsp;&nbsp;<b>N&ordm;.:</b> 
                    <c:out value="${curriculo.numero}"/></td>
  </tr> --%>
  <tr>
    <td align="right" valign="middle"><b>Endereço:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.endereco}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Bairro:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.bairro}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Estado:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.estadoEnum}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Cidade:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.cidade}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Telefone (res):</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.telefoneRes}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Telefone (com):</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.telefoneCom}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Celular:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.celular}"/></td>
  </tr>
</table>

        </div>
        <div class="boxItemCurriculo">
                	<h5>Gradua&ccedil;&atilde;o</h5>
          <table align="center" width="800" border="0" cellspacing="0" cellpadding="3">
  <tr>
    <td width="30%" align="right" valign="middle"><b>Institui&ccedil;&atilde;o:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.instituicao}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Curso:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.curso}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Per&iacute;odo:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.periodo}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Especializa&ccedil;&otilde;es:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.especializacoes}"/></td>
  </tr>
  
  <tr>
    <td align="right" valign="middle"><b>Idiomas:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.idiomas}"/></td>
  </tr>
  <tr>
    <td height="25" colspan="2" align="right" valign="middle">&nbsp;</td>
    </tr>
  <tr>
    <td align="right" valign="middle"><b>&Aacute;rea de Trabalho:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.areaDeTrabalho}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Per&iacute;odo de Trabalho:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.periodoEnum}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Pretens&atilde;o Salarial:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.pretensaoSalarial}"/></td>
  </tr>
</table>

</div>
<div class="boxItemCurriculo">
    	<h5>Dados do emprego atual ou &uacute;ltimo emprego</h5>
        <table align="center" width="800" border="0" cellspacing="0" cellpadding="3">
  <tr>
    <td width="30%" align="right" valign="middle"><b>Empresa:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.empresa1}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Estado:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.estadoEmpresa1Enum}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Cidade:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.cidadeEmpresa1}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Telefone:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.telefoneEmpresa1}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Cargos Ocupados:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.cargosEmpresa1}"/></td>
  </tr>
  
 <%--  <tr>
    <td align="right" valign="middle"><b>Atividades:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.atividadesEmpresa1}"/></td>
  </tr> --%>
  
  <%-- <tr>
    <td align="right" valign="middle"><b>Data da Admiss&atilde;o:</b></td>
    <td align="left" valign="middle"><fmt:formatDate value="${curriculo.dataAdmissaoEmpresa1}" pattern="dd/MM/yyyy"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Data da Sa&iacute;da:</b></td>
    <td align="left" valign="middle"><fmt:formatDate value="${curriculo.dataSaidaEmpresa1}" pattern="dd/MM/yyyy"/></td>
  </tr> --%>
</table>

        </div>
        <div class="boxItemCurriculo">
                	<h5>Dados do pen&uacute;ltimo emprego</h5>
                    <table align="center" width="800" border="0" cellspacing="0" cellpadding="3">
  <tr>
    <td width="30%" align="right" valign="middle"><b>Empresa:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.empresa2}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Estado:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.estadoEmpresa2Enum}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Cidade:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.cidadeEmpresa2}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Telefone:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.telefoneEmpresa2}"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Cargos Ocupados:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.cargosEmpresa2}"/></td>
  </tr>
  
 <%--  <tr>
    <td align="right" valign="middle"><b>Atividades:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.atividadesEmpresa2}"/></td>
  </tr>
   --%>
  <%-- <tr>
    <td align="right" valign="middle"><b>Data da Admiss&atilde;o:</b></td>
    <td align="left" valign="middle"><fmt:formatDate value="${curriculo.dataAdmissaoEmpresa2}" pattern="dd/MM/yyyy"/></td>
  </tr>
  <tr>
    <td align="right" valign="middle"><b>Data da Sa&iacute;da:</b></td>
    <td align="left" valign="middle"><fmt:formatDate value="${curriculo.dataSaidaEmpresa2}" pattern="dd/MM/yyyy"/></td>
  </tr> --%>
  <tr>
    <td height="25" colspan="2" align="right" valign="middle">&nbsp;</td>
  </tr>
  <%-- <tr>
    <td align="right" valign="middle"><b>Resumo das Habilidades e Cursos Realizados:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.habilitacoes}"/></td>
  </tr> --%>
  <tr>
    <td align="right" valign="middle"><b>Resumo das Habilidades:</b></td>
    <td align="left" valign="middle"><c:out value="${curriculo.experiencias}"/></td>
  </tr>
  
  
  
  <tr>
    <td align="right" valign="middle"><b>Cargos Pretendidos:</b></td>
    <td align="left" valign="middle">
                    <c:if test="${ehAnalistaAdministrativo eq true}"> Analista Administrativo 
					<br/>
                    </c:if>
					<c:if test="${curriculo.ehAnalistaDeptoPessoal eq true}"> Analista Depto Pessoal 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAnalistaTreinamento eq true}"> Analista Treinamento 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAssistenteECommerce eq true}"> Assistente E-commerce 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxAdministrativo eq true}"> Aux. Administrativo 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxDeptoPessoal eq true}"> Aux. Depto Pessoal 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxiliarDeCompras eq true}"> Auxiliar de Compras 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehComprador eq true}"> Comprador  
					<br/>
					</c:if>
					<c:if test="${curriculo.ehCoordenadorDeptoPessoal eq true}"> Coordenador Dept Pessoal 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehCoordenadorFinanceiro eq true}"> Coordenador Financeiro 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehEstagio eq true}"> Estágio 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehOperadorImpressoraOuCopiadora eq true}"> Operador Impressora/Copiadora 
					<br/>
					</c:if>
					<c:if test="${curriculo.ehProgramador eq true}"> Programador 
					<br/>
					</c:if>
					
					<c:if test="${curriculo.ehAnalistaMarketing eq true}"> Analista Marketing
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAssistenteComercial eq true}"> Assistente Comercial
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxVendas eq true}"> Aux. Vendas
					<br/>
					</c:if>
					<c:if test="${curriculo.ehCoordenadorMarketing eq true}"> Coordenador Marketing
					<br/>
					</c:if>
					<c:if test="${curriculo.ehOperadorDeCaixaValparaiso eq true}"> OP.CAIXA VALPARAISO
					<br/>
					</c:if>
					<c:if test="${curriculo.ehSupervisorEquipe eq true}"> Supervisor Equipe
					<br/>
					</c:if>
					<c:if test="${curriculo.ehSupervisoTelevendas eq true}"> Supervisor Televendas
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTelevendas eq true}"> Televendas
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedor eq true}"> Vendedor
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorCorporativo eq true}"> Vendedor corporativo
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorDeShopping eq true}"> Vendedor de Shopping
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorExterno eq true}"> Vendedor Externo
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorExternoCatalao eq true}"> VENDEDOR EXTERNO CATALAO
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorExternoCeres eq true}"> VENDEDOR EXTERNO CERES
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorExternoLuziania eq true}"> VENDEDOR EXTERNO LUZIANIA
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorLicitacao eq true}"> Vendedor Licitação
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorRevenda eq true}"> Vendedor Revenda
					<br/>
					</c:if>
					<c:if test="${curriculo.ehVendedorValparaiso eq true}"> VENDEDOR VALPARAISO
					<br/>
					</c:if>
					<c:if test="${curriculo.ehGerenteContasGoverno eq true}"> Gerente Contas Governo
					<br/>
					</c:if>
					<c:if test="${curriculo.ehPreVenda eq true}"> Pre Venda
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAnalistaDeCredito eq true}"> Analista de crédito
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAnalistaFinanceiro eq true}"> Analista Financeiro
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxiliarContabil eq true}"> Auxiliar Contábil
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxiliarFinanceiro eq true}"> Auxiliar Financeiro
					<br/>
					</c:if>
					<c:if test="${curriculo.ehCaixa eq true}"> Caixa
					<br/>
					</c:if>
					<c:if test="${curriculo.ehContador eq true}"> Contador
					<br/>
					</c:if>
					<c:if test="${curriculo.ehController eq true}"> Controller
					<br/>
					</c:if>
					<c:if test="${curriculo.ehGerenciaComercial eq true}"> Gerencia Comercial
					<br/>
					</c:if>
					<c:if test="${curriculo.ehGerenteAdministrativoFinanceiro eq true}"> Gerente Administrativo Financeiro
					<br/>
					</c:if>
					<c:if test="${curriculo.ehGerenteRH eq true}"> Gerente de RH
					<br/>
					</c:if>
					<c:if test="${curriculo.ehGerenteDeVendas eq true}"> Gerente de Vendas
					<br/>
					</c:if>
					<c:if test="${curriculo.ehGerenteDeEstoque eq true}"> Gerente Estoque
					<br/>
					</c:if>
					<c:if test="${curriculo.ehSupervisoDeAssistenciaTecnica eq true}"> Supervisor de Assistência Técnica
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxiliarEstoque eq true}"> Auxiliar Estoque
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAuxiliarRMA eq true}"> Auxiliar RMA
					<br/>
					</c:if>
					<c:if test="${curriculo.ehEstoquista eq true}"> Estoquista
					<br/>
					</c:if>
					<c:if test="${curriculo.ehMotorista eq true}"> Motorista
					<br/>
					</c:if>
					<c:if test="${curriculo.ehAnalistaDeSuporte eq true}"> Analista de Suporte
					<br/>
					</c:if>
					<c:if test="${curriculo.ehEstagioEmInformaticaOuAssistenciaTecnica eq true}"> Estagio em Informatica/Assistencia Técnica
					<br/>
					</c:if>
					<c:if test="${curriculo.ehProgramador eq true}"> Programador
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTecnicoAtendimentoExterno eq true}"> Técnico Atendimento Externo
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTecnicoEmMicros eq true}"> Técnico em Micros
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTecnicoEmNotebooks eq true}"> Técnico em Notebooks
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTecnicoEmRedesEServidores eq true}"> Técnico em Redes e Servidores
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTecnicoSmartfoneETablet eq true}"> Tecnico Smartfone/Tablet
					<br/>
					</c:if>
					<c:if test="${curriculo.ehTecnicoTvLCDeLED eq true}"> Tecnico TV LCD/LED</c:if>

  
    </td>
  </tr>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
</table>

</div>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>


<!-- fim corpo -->
    </td>
  </tr>
</table>
</td>
  </tr>
    <tr>
     <td class="rodape">Ilion - <a href="http://www.ilion.com.br/" target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
  </tr>
</table>


<script type="text/javascript">
document.getElementById('palavraChave').value = "<c:out value='${vlhForm.palavraChave}'/>";

var m = "<%=request.getParameter("m")%>";
if(m=='contato-excluido') {
	alert("Contato excluído com sucesso!");
} 

</script>

</body>
</html>