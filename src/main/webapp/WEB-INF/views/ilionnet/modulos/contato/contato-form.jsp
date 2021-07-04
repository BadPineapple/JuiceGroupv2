<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
<script type="text/javascript" language="javascript" src="design/script/jquery/jquery-1.2.6.js"></script>
<script type="text/javascript" language="javascript" src="design/script/jquery/jquery.maskedinput-1.2.1.js"></script>

<script type="text/javascript" language="javascript">
jQuery.noConflict();
jQuery(function(jQuery){
	jQuery("#telefone").mask("(99) 9999-9999");
	jQuery("#celular").mask("(99) 9999-9999");
    jQuery("#cep").mask("99999999");
});
</script>

<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo -->

<jsp:include page="include-contato-links.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Contato - Inclus&atilde;o/Altera&ccedil;&atilde;o </td>
  </tr>
</table>

<form:form commandName="contato" method="post" action="contato-form.sp" cssStyle="margin:0;">

<span style="color:red;"><form:errors path="*"/></span>

<div style="margin: 0 0 8px 0;">
<ilion:permissao tipo="contato-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<c:if test="${not empty contato.id}">
<input type="button" class="botao" value="Excluir" onclick="if(confirm('Deseja realmente EXCLUIR este contato?')){location='contato-excluir.sp?id=<c:out value='${contato.id}'/>';}" style="margin-right: 5px;"/>
</c:if>
<input type="button" class="botao" value="Incluir Novo" onclick="location='contato-form.sp'" style="margin-right: 5px;"/>
<input type="button" class="botao" value="Sair" onclick="location='contato.sp'" style="margin-right: 5px;"/>
</div>


<table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td class="tituloDestaque">Dados pessoais / empresa</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td width="155" align="right" valign="middle" class="linkCinzaEscuro"><form:label path="nome" cssErrorClass="textoErro">Nome:</form:label></td>
            <td width="35%" class="linkCinza"><form:input path="nome" cssClass="forms2" cssStyle="width:90%;" maxlength="255"/></td>
            <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Empresa:</td>
            <td width="35%" class="linkCinza"><form:input path="empresa" cssClass="forms2" cssStyle="width:90%;" maxlength="255"/></td>
          </tr>
          <tr> 
            <td width="155" align="right" valign="middle" class="linkCinzaEscuro"><form:label path="email" cssErrorClass="textoErro">E-mail:</form:label></td>
            <td width="35%" class="linkCinza"><form:input path="email" cssClass="forms2" size="30" maxlength="100"/></td>
            <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">&nbsp;</td>
            <td width="35%" class="linkCinza">&nbsp;</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td class="tituloDestaque">Endere&ccedil;o</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Endere&ccedil;o:</td>
            <td width="35%" class="linkCinza"><form:input path="endereco" cssClass="forms2" size="30" maxlength="255"/></td>
            <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">CEP:</td>
            <td width="35%" class="linkCinza"><form:input path="cep" id="cep" cssClass="forms2" maxlength="8" cssStyle="width:70px;"/></td>
          </tr>
          <tr> 
            <td align="right" valign="middle" class="linkCinzaEscuro">Complemento:</td>
            <td class="linkCinza"><form:input path="complemento" cssClass="forms2" size="30" maxlength="100"/></td>
            <td align="right" valign="middle" class="linkCinzaEscuro">Setor:</td>
            <td class="linkCinza"><form:input path="setor" cssClass="forms2" size="20" maxlength="50"/></td>
          </tr>
          <tr> 
            <td align="right" valign="middle" class="linkCinzaEscuro">Cidade / UF:</td>
            <td class="linkCinza"><form:input path="cidade" cssClass="forms2" cssStyle="width:50%;" maxlength="100"/> / <form:select path="estado" cssClass="forms2"> 
                                            <form:option value="" label=""/> 
											<form:option value="AC" label="Acre"/> 
                                            <form:option value="AL" label="Alagoas"/> 
                                            <form:option value="AP" label="Amapá"/> 
                                            <form:option value="AM" label="Amazonas"/> 
                                            <form:option value="BA" label="Bahia"/> 
                                            <form:option value="CE" label="Ceará"/> 
                                            <form:option value="DF" label="Distrito Federal"/> 
                                            <form:option value="ES" label="Espírito Santo"/> 
                                            <form:option value="GO" label="Goiás"/> 
                                            <form:option value="MA" label="Maranhão"/> 
                                            <form:option value="MT" label="Mato Grosso"/> 
                                            <form:option value="MS" label="Mato Grosso do Sul"/> 
                                            <form:option value="MG" label="Minas Gerais"/> 
                                            <form:option value="PA" label="Pará"/> 
                                            <form:option value="PB" label="Paraíba"/> 
                                            <form:option value="PR" label="Paraná"/> 
                                            <form:option value="PE" label="Pernambuco"/> 
                                            <form:option value="PI" label="Piauí"/> 
                                            <form:option value="RJ" label="Rio de Janeiro"/> 
                                            <form:option value="RN" label="Rio Grande do Norte"/> 
                                            <form:option value="RS" label="Rio Grande do Sul"/> 
                                            <form:option value="RO" label="Rondônia"/> 
                                            <form:option value="RR" label="Roraima"/> 
                                            <form:option value="SC" label="Santa Catarina"/> 
                                            <form:option value="SP" label="São Paulo"/> 
                                            <form:option value="SE" label="Sergipe"/> 
                                            <form:option value="TO" label="Tocantins"/> 
                                            </form:select></td>
            <td align="right" valign="middle" class="linkCinzaEscuro">Telefone / Celular:</td>
            <td class="linkCinza"><form:input path="telefone" id="telefone" cssClass="forms2" cssStyle="width:85px;" maxlength="14"/> / <form:input path="celular" id="celular" cssClass="forms2" cssStyle="width:85px;" maxlength="14"/></td>
          </tr>
          <tr> 
            <td align="right" valign="middle" class="linkCinzaEscuro">País:</td>
            <td class="linkCinza"><form:input path="pais" cssClass="forms2" size="20" maxlength="50"/></td>
            <td align="right" valign="middle" class="linkCinzaEscuro">&nbsp;</td>
            <td class="linkCinza">&nbsp;</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td class="tituloDestaque">Configuração do contato</td>
          </tr>
        </table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr> 
          <td width="155" align="right" valign="middle" class="linkCinzaEscuro">Receber Informativos:</td>
          <td width="35%" class="linkCinza"><form:radiobutton path="permissaoEmail" value="true"/> Sim <form:radiobutton path="permissaoEmail" value="false"/> 
            N&atilde;o</td>
          <td width="15%" align="right" valign="middle" nowrap class="linkCinzaEscuro">Criado por:</td>
          <td width="35%" class="linkCinza"><c:if test="${not empty contato.id}"><c:out value="${contato.cadastradoPor}" default="---"/> em <fmt:formatDate value="${contato.dataCriacao}" pattern="dd/MM/yyyy HH:mm"/></c:if></td>
        </tr>
        <tr> 
          <td align="right" valign="middle" class="linkCinzaEscuro">Grupos:</td>
          <ilion:contatoGrupoLista varRetorno="contatoGrupos"/>
          <td colspan="3" class="linkCinza"><c:forEach items="${contatoGrupos}" var="grupo">
		  <div style="float: left;"><form:checkbox path="idsGrupos" value="${grupo.id}"/> <c:out value="${grupo.nome}"/></div>
 		  </c:forEach></td>
        </tr>
        <tr> 
          <td align="right" valign="middle" class="linkCinzaEscuro">Observa&ccedil;&otilde;es:</td>
          <td colspan="3" class="linkCinza"><form:textarea path="obs" cssClass="forms2" cssStyle="width:70%;height:100px;"/></td>
        </tr>
      </table>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<div style="margin: 0 0 8px 0;">
<ilion:permissao tipo="contato-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<c:if test="${not empty contato.id}">
<input type="button" class="botao" value="Excluir" onclick="if(confirm('Deseja realmente EXCLUIR este contato?')){location='contato-excluir.sp?id=<c:out value='${contato.id}'/>';}" style="margin-right: 5px;"/>
</c:if>
<input type="button" class="botao" value="Incluir Novo" onclick="location='contato-form.sp'" style="margin-right: 5px;"/>
<input type="button" class="botao" value="Sair" onclick="location='contato.sp'" style="margin-right: 5px;"/>
</div>

</form:form>
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
var m = "<c:out value='${param.m}'/>";
if(m == 'ok') {
	alert("Contato gravado com sucesso!");
}
</script>
</body>
</html>