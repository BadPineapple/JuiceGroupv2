<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
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
    <td class="tituloDestaque">Grupos de contatos </td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0" class="vlhTabela1">
	<tr>
		<th width="70" align="center">&nbsp;</th>
		<th width="*" align="left"><b>Nome</b></th>
		<th width="100" align="center"><b>Qtd. Contatos</b></th>
		<th width="70" align="center">&nbsp;</th>
<ilion:permissao tipo="contato-grupo-excluir-contatos.sp">
		<th width="180" align="center">&nbsp;</th>
</ilion:permissao>
	</tr>
	<ilion:contatoGrupoLista varRetorno="contatoGrupos"/>
<c:forEach var="t" items="${contatoGrupos}">
	<tr>
		<td align="center"><a href="contato-grupo-form.sp?id=<c:out value='${t.id}'/>">Editar</a></td>
		<td><c:out value='${t.nome}'/></td>
		<td align="center"><c:out value='${t.qtd}'/></td>
		<td align="center"><a style="color: #a80000;" href="javascript:if(confirm('Tem certeza da exclusão deste grupo?')){location='contato-grupo-excluir.sp?id=<c:out value='${t.id}'/>';}">Excluir</a></td>
<ilion:permissao tipo="contato-grupo-excluir-contatos.sp">
		<td align="center"><c:if test="${t.qtd > 0}"><a style="color: #a80000;" href="javascript:if(confirm('Tem certeza da exclusão todos os contatos deste grupo?')){location='contato-grupo-excluir-contatos.sp?id=<c:out value='${t.id}'/>';}"><c:if test="${t.qtd == 1}">Excluir contato</c:if><c:if test="${t.qtd > 1}">Excluir TODOS os <c:out value='${t.qtd}'/> contatos</c:if></a></c:if></td>
</ilion:permissao>
	</tr>
</c:forEach>
</table>


<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Inclus&atilde;o/Altera&ccedil;&atilde;o de Grupo de Contato </td>
  </tr>
</table>

<form:form commandName="grupoContato" method="post" action="contato-grupo-form.sp" cssStyle="margin:0;">
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td width="20%" align="right" class="linkCinzaEscuro">Grupo:</td>
  <td class="linkCinza"><form:input path="nome" cssClass="forms2" size="30" /> <input name="submit" type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/> <span style="color:#FF0000;"><form:errors path="*"/></span></td>
</tr>
</table>
</form:form>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Transfer&ecirc;ncia de contatos entre grupos </td>
  </tr>
</table>

<form method="post" action="contato-grupo-transferir.sp" style="margin:0;">
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td width="20%" align="right" valign="middle" nowrap class="linkCinzaEscuro">Transferir de:</td>
  <td class="linkCinza"><select name="idGrupoContatoOrigem" id="idGrupoContatoOrigem" class="forms2">
<option value="">--- Selecione ---</option>
<c:forEach var="grupo" items="${contatoGrupos}">
<option value="<c:out value='${grupo.id}'/>"><c:out value='${grupo.nome}'/> - <c:out value='${grupo.qtd}'/></option>
</c:forEach>
 </select> <select name="qtd" class="forms2">
 	<option value="todos">Todos os contatos</option>
 	<option value="500">500 contatos</option>
 	<option value="250">250 contatos</option>
 	<option value="100">100 contatos</option>
 </select></td>
 </tr>
 <tr> 
  <td align="right" valign="middle" nowrap class="linkCinzaEscuro">para:</td>
  <td class="linkCinza"><select name="idGrupoContatoDestino" id="idGrupoContatoDestino" class="forms2">
<option value="">--- Selecione ---</option>
<c:forEach var="grupo" items="${contatoGrupos}">
<option value="<c:out value='${grupo.id}'/>"><c:out value='${grupo.nome}'/> - <c:out value='${grupo.qtd}'/></option>
</c:forEach>
 </select></td>
 </tr>
<tr> 
  <td align="right" class="linkCinzaEscuro">&nbsp;</td>
  <td class="linkCinza"><input name="submit" type="submit" class="botao" value="Salvar"/></td>
</tr>
</table>
</form>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<script type="text/javascript">
var m = "<c:out value='${param.m}'/>";
if(m=='nenhum-arquivo') {
	alert("Nenhum arquivo para importar!");
} else if(m=='grupo-nao-selecionado') {
	alert("Selecione o grupo!");
} else if(m == 'grupo-possui-contatos') {
	alert("Tipo não pode ser excluído, possui contatos!");
} else if(m == 'contatos-excluidos') {
	alert("Contatos excluídos com sucesso!");
}

var mensagem = "<c:out value='${param.mensagem}'/>";
if(mensagem != '' && mensagem != null && mensagem != 'null') {
	alert(mensagem);
}
</script>


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

</body>
</html>