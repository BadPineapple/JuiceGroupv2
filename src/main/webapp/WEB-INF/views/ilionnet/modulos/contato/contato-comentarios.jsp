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
    <td class="tituloDestaque">Contato - Coment&aacute;rios</td>
  </tr>
</table>

<input type="button" class="botao" value="Sair" onclick="location='contato.sp'" style="margin-right: 5px;"/>

	<table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0" style="margin-top: 10px;">
       <tbody>
       <tr>    
        <th>&nbsp;&nbsp;</th>
	    <th>&nbsp;Nome</th>
	    <th>&nbsp;Telefone&nbsp;</th>
	    <th>&nbsp;E-mail</th>
	    <th>&nbsp;Empresa&nbsp;</th>
	    <th>&nbsp;Newsletter&nbsp;</th>
	    <th>&nbsp;Grupos&nbsp;</th>
	</tr>
	<tr> 
		<td align="center">&nbsp;<a href="contato-form.sp?id=<c:out value='${contato.id}'/>">editar</a>&nbsp;</td>
		<td><c:out value='${contato.nome}'/></td>
		<td align="center"><c:out value='${contato.telefone}'/></td>
		<td align="center">&nbsp;<a href="mailto:<c:out value='${contato.email}'/>" class="texto"><c:out value='${contato.email}'/></a>&nbsp;</td>
		<td><c:out value='${contato.empresa}'/></td>
		<td align="center">&nbsp;<c:if test='${contato.permissaoEmail}'>Sim</c:if>&nbsp;</td>
		<td align="center"><c:out value="${contato.gruposFormatados}"/></td>
	</tr>
 
      </tbody>
     </table>


	<table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0" style="margin-top: 10px;">
       <thead>
       <tr>
	    <th width="120px">Data</th>
	    <th>Grupo</th>
	    <th>Coment&aacute;rio</th>
	   </tr>
	  </thead>
	  <tbody>
		<c:forEach var="cc" items="${contatoComentarios}">
		<tr> 
			<td align="center"><ilion:formatDate value="${cc.data}" pattern="dd/MM/yyyy HH:mm"/></td>
			<td align="center"><c:out value='${cc.grupo}'/></td>
			<td><c:out value='${cc.comentario}'/></td>
		</tr>
		</c:forEach>
     </tbody>
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

</body>
</html>