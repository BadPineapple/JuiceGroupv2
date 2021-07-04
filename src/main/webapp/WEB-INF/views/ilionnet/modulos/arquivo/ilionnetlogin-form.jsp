<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Gerência de arquivos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="design/css/cssPrincipal.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr> 
          <td class="tituloDestaque"><b>Cadastrar Imagens do Login </b></td>
        </tr>
      </table>
<form:form commandName="arquivo" name="formArquivo" method="post" enctype="multipart/form-data" action="ilionnet-login-form.sp"> 	  
	  <table width="100%" border="0" cellspacing="1" cellpadding="1">
        <tr>
          <td class="linkCinzaEscuro">Tipo:</td>
          <td colspan="3" class="linkCinza"><form:select cssClass="forms2" path="nomeClasse">
		  <form:option value="login-topo" label="Imagem do Topo"/>
		  <form:option value="login-logo" label="Logo da empresa"/>
		  </form:select></td>
        </tr>
        <tr> 
          <td class="linkCinzaEscuro">Nome:</td>
          <td colspan="3" class="linkCinza"><form:input path="texto" cssClass="forms2" size="40"/></td>
          </tr>
<c:if test="${empty arquivo.id}">
		  <tr>
			<td width="120" class="linkCinzaEscuro">Upload:</td>
			<td class="linkCinza" colspan="3"><input type="file" name="arquivo" class="forms2" size="30"/></td>
		  </tr>
</c:if>
		  <tr> 
			<td width="120" class="linkCinzaEscuro">Tamanho original:</td>
			<td width="260" class="linkCinza"><form:checkbox path="tamanhoOriginal" value="true"/></td>
			<td width="120" class="linkCinzaEscuro">Largura em pixels:</td>
			<td class="linkCinza"><form:input path="larguraPequena" size="5" cssClass="forms2"/></td>
		  </tr>
        <tr> 
          <td width="120" class="linkCinzaEscuro">Arquivo 1:</td>
          <td class="linkCinza"><c:out value='${arquivo.arquivo1}'/></td>
          <td class="linkCinzaEscuro">Publicado:</td>
          <td class="linkCinza"><form:radiobutton path="naoPublicado" value="false"/> Sim <form:radiobutton path="naoPublicado" value="true"/> 
          N&atilde;o </td>
        </tr>
        <tr> 
          <td class="linkCinzaEscuro">&nbsp;</td>
          <td colspan="3" class="linkCinza">
          	<input type="submit" class="botao" name="acao" value="Salvar"/>
            &nbsp;
            <input type="button" class="botao" value="Fechar" onClick="window.close();"/>
          </td>
        </tr>
        <tr> 
          <td colspan="4" class="texto"><font color="#FF0000"><form:errors path="*"/></font></td>
        </tr>
     </table>
</form:form>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr> 
          <td class="tituloDestaque"><b>Imagens cadastradas </b></td>
        </tr>
      </table>
<c:forEach var="arquivo" items="${arquivos}">
<table width="100%" border="0" cellspacing="1" cellpadding="2">
              <tr>
                <td><img src="<ilion:enderecoArquivos/><c:url value='${arquivo.arquivo1}'/>" border="0"/></td>
			 </tr>
             <tr>
                <td width="*" valign="top">
                  <table width="100%" border="0" cellspacing="1" cellpadding="1">
                    <tr>
                      <td class="linkCinzaEscuro">Tipo:</td>
                      <td colspan="3" class="linkCinza"><c:out value="${arquivo.nomeClasse}"/></td>
                    </tr>
                    <tr>
                      <td class="linkCinzaEscuro">Nome:</td>
                      <td width="383" class="linkCinza"><c:out value="${arquivo.texto}"/></td>
                      <td width="119" class="linkCinzaEscuro">Largura em pixels: </td>
                      <td width="321" class="linkCinza"><c:out value="${arquivo.largura}"/></td>
                    </tr>
                    <tr> 
                      <td width="112" class="linkCinzaEscuro">Arquivo 1:</td>
                      <td class="linkCinza"><c:out value="${arquivo.arquivo1}"/></td>
                      <td class="linkCinza">Publicado:</td>
                      <td class="linkCinza"><c:if test="${empty arquivo.naoPublicado || ! arquivo.naoPublicado}"><span style="color:blue;">Sim</span></c:if>
                      <c:if test="${arquivo.naoPublicado}"><span style="color:red;">N&atilde;o</span></c:if></td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellspacing="1" cellpadding="1">
                    <tr> 
                      <td align="center" class="linkCinza"><input name="button" type="button" class="botao" value="Alterar" onClick="location='ilionnet-login-form.sp?id=<c:out value='${arquivo.id}'/>';"/> 
                        &nbsp;&nbsp; <input name="button2" type="button" class="botao" value="Excluir" onClick="location='ilionnet-login-excluir.sp?idExcluir=<c:out value='${arquivo.id}'/>';"/></td>
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
</html>