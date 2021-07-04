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
    <td class="tituloDestaque">Importar contatos aquivo de texto </td>
  </tr>
</table>

<form method="post" name="upfotoForm" enctype="multipart/form-data" action="contato-importar-executar.sp" style="margin:0;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>

	<table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
            <td align="right" valign="middle" class="linkCinzaEscuro">&nbsp;</td>
            <td class="linkCinza">&nbsp;</td>
          </tr>
          <tr> 
            <td width="155" align="right" valign="middle" class="linkCinzaEscuro">Grupo de contato:</td>
            <td class="linkCinza"><select name="id" class="forms2">
            <option value="">Selecione</option>
		 <ilion:contatoGrupoLista varRetorno="contatoGrupos"/>
		 <c:forEach var="c" items="${contatoGrupos}">
		  <option value="<c:out value='${c.id}'/>"><c:out value='${c.nome}'/> - <c:out value='${c.qtd}'/></option>
		  </c:forEach>
					  </select> <input type="radio" name="charset" value="ISO-8859-1" checked="checked"/> ISO-8859-1 <input type="radio" name="charset" value="UTF-8"/> UTF-8 </td>
          </tr>
          <tr> 
            <td align="right" valign="middle" nowrap class="linkCinzaEscuro">Arquivo 
              de texto:</td>
            <td class="linkCinza"><input type="file" name="arquivo" class="forms2" accept="*" size="30" /></td>
          </tr>
          <tr>
            <td align="right" valign="middle" nowrap class="linkCinzaEscuro">&nbsp;</td>
            <td class="linkCinza">&nbsp;</td>
          </tr>
          <tr>
            <td align="right" valign="middle" nowrap class="linkCinzaEscuro">&nbsp;</td>
            <td class="linkCinza">
              <input name="submit" type="submit" class="botao" value="Importar" style="margin-right: 5px;"/>
              <input type="button" class="botao" value=" Sair " onclick="location='contato.sp'" style="margin-right:5px;"/></td>
          </tr>
        </table>
        
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td class="divisaoAzul"></td>
        </tr>
      </table>

<c:if test="${not empty mensagem }">
<fieldset style="width:100%;padding: 15px;">
	<legend>Resultado da importação</legend>
	<c:out value="${mensagem}" escapeXml="{true}"/>
</fieldset>
</c:if>
      
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
          <tr> 
            <td class="texto">
            
<strong>Instruções para importar uma lista de contatos:</strong><br/>
<br/>
1 - A lista deve ser formatada em arquivo txt. Ex.: contatos.txt;<br/>
2 - A cada linha deve conter os dados de um contato com seu Nome, e-mail, Nome da Empresa e telefone. Sendo que os dados mínimos são nome e e-mail;<br/>
3 - Os dados devem ser separador por ; (ponto e vírgula).<br/>
<br/>
Ex:<br/>
Nome;e-mail;empresa;telefone;<br/>
Nome;e-mail;empresa;telefone;<br/>
Nome;e-mail;empresa;telefone;<br/>
<br/>
ou:<br/>
<br/>
Nome;e-mail;<br/>
Nome;e-mail;<br/>
Nome;e-mail;<br/>
<br/>
ou:<br/>
<br/>
e-mail;e-mail;<br/>
e-mail;e-mail;<br/>
e-mail;e-mail;<br/>
<br/>
Obs.:<br/>
- Não tem espaço nem antes e nem depois do ponto e vírgula.<br/>
- Os cadastrados com todos os dados devem ser separados do que tiverem só [ nome;e-mail; ], deve ser montado dois arquivos TXT:<br/>
<br/>
Um TXT<br/>
<br/>
Nome;e-mail;empresa;telefone;<br/>
Nome;e-mail;empresa;telefone;<br/>
<br/>
Outro TXT<br/>
<br/>
e-mail;e-mail;<br/>
e-mail;e-mail;<br/>
            
            </td>
          </tr>
        </table>
      </td>
  </tr>
</table>
</form>
<script type="text/javascript">
var m = "<c:out value='${param.m}'/>";
if(m=='nenhum-arquivo') {
	alert("Nenhum arquivo para importar!");
} else if(m=='grupo-nao-selecionado') {
	alert("Selecione o tipo!");
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