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

<div style="float: left;">
<div class="hierarquia">Web Site</div>
</div>
<div style="float: right;">
<select class="forms2" onchange="location='gc.sp?idioma='+this.value;">
		<option value="br" <c:if test="${idiomaSelecionado == 'br'}">selected</c:if>>Português Brasil</option>
		<option value="en" <c:if test="${idiomaSelecionado == 'en'}">selected</c:if>>English</option>
        </select>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Enquetes</td>
</tr>
</table>

<div style="clear: both;padding: 10px 0 10px 0;">
    <input type="button" name="button" value="Incluir Nova Enquete" onclick="location='enquete-form.sp';" class="botao" style="margin-right: 5px;"/>
</div>

<c:if test="${empty enquetes}">
N&atilde;o h&aacute; enquetes cadastradas.
</c:if>

<c:if test="${not empty enquetes}">
<table class="vlhTabela1" width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <th width="*">Enquete</th>
    <th width="150">Op&ccedil;&otilde;es</th>
    <th width="100">Status</th>
    <th width="100">Data Publica&ccedil;&atilde;o</th>
	<th width="50">&nbsp;</th>
  </tr>
<c:forEach var="enquete" items="${enquetes}">
  <tr>
    <td><a href="enquete-form.sp?id=<c:out value='${enquete.id}'/>"><c:out value="${enquete.assunto}"/></a></td>
    <td align="center"><select class="forms">
								<c:if test="${enquete.opcao1!=''}"><option><c:out value="${enquete.opcao1}"/> --&gt; <c:out value="${enquete.votosOpcao1}"/></c:if>
								<c:if test="${enquete.opcao2!=''}"><option><c:out value="${enquete.opcao2}"/> --&gt; <c:out value="${enquete.votosOpcao2}"/></c:if>
								<c:if test="${enquete.opcao3!=''}"><option><c:out value="${enquete.opcao3}"/> --&gt; <c:out value="${enquete.votosOpcao3}"/></c:if>
								<c:if test="${enquete.opcao4!=''}"><option><c:out value="${enquete.opcao4}"/> --&gt; <c:out value="${enquete.votosOpcao4}"/></c:if>
								<c:if test="${enquete.opcao5!=''}"><option><c:out value="${enquete.opcao5}"/> --&gt; <c:out value="${enquete.votosOpcao5}"/></c:if>
							</select></td>
    <td align="center"><c:out value="${enquete.status}"/></td>
    <td align="center"><fmt:formatDate value='${enquete.dataPublicacao}' pattern='dd/MM/yyyy'/></td>
	<td align="center"><a href="javascript:if(confirm('Deseja realmente excluir esta enquete?')){location='enquete-excluir.sp?id=<c:out value='${enquete.id}'/>';}" style="color:red;">Excluir</a></td>
  </tr>
</c:forEach>
</table>
</c:if>

<div style="clear: both;padding: 10px 0 10px 0;">
    <input type="button" name="button" value="Incluir Nova Enquete" onclick="location='enquete-form.sp';" class="botao" style="margin-right: 5px;"/>
</div>
    
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