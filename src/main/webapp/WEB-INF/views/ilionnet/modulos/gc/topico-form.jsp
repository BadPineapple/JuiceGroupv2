<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
<script type="text/javascript">
function excluirSubCategoria(id) {
	if( confirm('Deseja realmente excluir?') ) {
		location='topico-excluir.sp?id='+id;
	}
}
</script>

<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">

<div style="float: left;">
	<div class="hierarquia">
		T&oacute;picos
	</div>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Inclus&atilde;o/Altera&ccedil;&atilde;o de T&oacute;picos </td>
  </tr>
</table>

<form:form commandName="topico" method="post" action="topico-form.sp" cssStyle="margin:0 0 7px 0;">
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td width="20%" align="right" class="linkCinzaEscuro">Nome:</td>
  <td class="linkCinza"><form:input path="nome" cssClass="forms2" size="30" /> 
  	<input name="submit" type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/> 
  	<span style="color:#FF0000;"><form:errors path="*"/></span></td>
</tr>
</table>
</form:form>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">T&oacute;picos</td>
</tr>
</table>

    <c:if test="${empty topicos}">N&atilde;o h&aacute; tópicos cadastrados.</c:if>

<c:if test="${not empty topicos}">
      <table class="vlhTabela1" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th width="60">&nbsp;</th> 
          <th width="*">Nome</th>
          <th width="60">&nbsp;</th>
        </tr>
		<c:forEach var="topico" items="${topicos}"> 
        <tr>
          <td align="center"><a href="topico-form.sp?id=<c:out value='${topico.id}'/>">Editar</a></td> 
          <td><c:out value="${topico.nome}"/></td>
          <td align="center"><a style="color: red;" href="topico-excluir.sp?id=<c:out value='${topico.id}'/>">Excluir</a></td>
        </tr>
        </c:forEach> 
       </table>
</c:if>
    
    
    
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
var m = "<%=request.getParameter("m")%>";
if(m=='categoria-nao-encontrada') {
	alert("Categoria não encontrada!");
}
</script>
</body>
</html>