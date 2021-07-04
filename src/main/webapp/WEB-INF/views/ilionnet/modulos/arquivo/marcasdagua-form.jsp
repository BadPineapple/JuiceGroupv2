<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Gerência de arquivos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="design/css/cssPrincipal.css" rel="stylesheet" type="text/css">
<link href="../../../design/css/cssPrincipal.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr> 
          <td class="tituloDestaque"><b>Cadastrar arquivos para marca d'&aacute;gua </b></td>
        </tr>
      </table>
	  
	  <table width="100%" border="0" cellspacing="1" cellpadding="1">
        <form:form commandName="marcadagua" name="formArquivo" method="post" enctype="multipart/form-data" action="marcasdagua-form.sp"> 
        <tr> 
          <td class="linkCinzaEscuro">Nome:</td>
          <td colspan="3" class="linkCinza"><form:input path="texto" cssClass="forms2" size="40"/></td>
          </tr>
<c:if test="${empty marcadagua.id}">
		  <tr>
			<td width="120" class="linkCinzaEscuro"><b>Upload:</b></td>
			<td class="linkCinza" colspan="3"><input name="multipartFile" type="file" class="forms2" size="30"></td>
		  </tr>
</c:if>
		  <tr> 
			<td width="120" class="linkCinzaEscuro">Tamanho original:</td>
			<td width="260" class="linkCinza"><form:checkbox path="tamanhoOriginal" value="true"/></td>
			<td width="120" class="linkCinzaEscuro">Largura em pixels:</td>
			<td class="linkCinza"><form:input path="larguraPequena" size="5" cssClass="forms2"/></td>
		  </tr>
<c:if test="${not empty marcadagua.id}">
        <tr> 
          <td width="120" class="linkCinzaEscuro"><b>Arquivo 1:</b></td>
          <td colspan="3" class="linkCinza"><c:out value='${marcadagua.arquivo1}'/></td>
        </tr>
</c:if>
        <tr> 
          <td class="linkCinzaEscuro">&nbsp;</td>
          <td colspan="3" class="linkCinza"><input type="submit" class="botao" value="Salvar">
            &nbsp;<input type="button" class="botao" value="Cancelar" onClick="location='marcasdagua-form.sp';">
            &nbsp;<input type="button" class="botao" value="Fechar" onClick="window.close();"></td>
        </tr>
        <tr> 
          <td colspan="4" class="texto"><font color="#FF0000"><form:errors path="*"/></font></td>
        </tr>
        </form:form> 
      </table>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr> 
          <td class="tituloDestaque"><b>Exibir arquivos do conte&uacute;do</b></td>
        </tr>
      </table>
<c:forEach var="arquivo" items="${arquivos}">
<table width="100%" border="0" cellspacing="1" cellpadding="2">
              <tr>
                <td><div style="width:inherit;height:150px;overflow:auto;"><img src="<c:url value='/arquivos/${arquivo.arquivo1}'/>" border="0"></div></td>
			 </tr>
             <tr>
                <td width="*" valign="top">
                  <table width="100%" border="0" cellspacing="1" cellpadding="1">
                    <tr>
                      <td class="linkCinzaEscuro">Nome:</td>
                      <td width="383" class="linkCinza"><c:out value="${arquivo.texto}"/></td>
                      <td width="119" class="linkCinzaEscuro">Largura em pixels: </td>
                      <td width="321" class="linkCinza"><c:out value="${arquivo.largura}"/></td>
                    </tr>
                    <tr> 
                      <td width="112" class="linkCinzaEscuro">Arquivo 1:</td>
                      <td colspan="3" class="linkCinza"><c:out value="${arquivo.arquivo1}"/></td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellspacing="1" cellpadding="1">
                    <tr> 
                      <td align="center" class="linkCinza"><input name="button" type="button" class="botao" value="Alterar" onClick="location='marcasdagua-form.sp?id=<c:out value='${arquivo.id}'/>';"> 
                        &nbsp;&nbsp; <input name="button2" type="button" class="botao" value="Excluir" onClick="location='marcasdagua-excluir.sp?idExcluir=<c:out value='${arquivo.id}'/>';"></td>
                    </tr>
                  </table>
            
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td class="divisaoAzul"></td>
		</tr>
     </table>
			 
		    </td>
		</tr>
     </table>
</c:forEach>

    </td>
  </tr>
</table>
</body>
<script type="text/javascript">
function atualizarSelects() {
	window.opener.limparSelectsMarcaDAgua();
	window.opener.addOptionMarcaDAgua('---', '');
	<c:forEach var="arquivo" items="${arquivos}">
	window.opener.addOptionMarcaDAgua('<c:out value='${arquivo.texto}'/>', '<c:out value='${arquivo.arquivo1}'/>');
	</c:forEach>
}
atualizarSelects();
</script>
</html>