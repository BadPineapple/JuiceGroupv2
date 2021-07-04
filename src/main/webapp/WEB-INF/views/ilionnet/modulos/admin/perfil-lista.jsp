<%@ include file="/ilionnet/taglibs.jsp"%>

<html>
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
<!-- inicio corpo usuario-busca -->    

<jsp:include page="include-admin-links.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Perfil</td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0" class="vlhTabela1">
	<tr>
		<th width="*" align="left"><b>Nome</b></th>
		<th width="150" align="center"><b>Acesso ao sistema</b></th>
	</tr>
<c:forEach var="p" items="${perfis}">
	<tr>
		<td><a href="perfil-form.sp?id=<c:out value='${p.id}'/>"><c:out value='${p.nome}'/></a></td>
		<td align="center"><c:if test='${p.acessoAoSistema}'>Sim</c:if><c:if test='${!p.acessoAoSistema}'>N&atilde;o</c:if></td>
	</tr>
</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<ilion:permissao tipo="perfil-form.sp">
<input type="button" class="botao" value="Incluir Novo" onClick="location='perfil-form.sp'"/>
</ilion:permissao>


<!-- fim corpo usuario busca -->
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