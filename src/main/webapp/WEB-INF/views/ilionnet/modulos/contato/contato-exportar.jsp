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
    <td class="tituloDestaque">Exportar contatos </td>
  </tr>
</table>

<form method="post" name="upfotoForm" enctype="multipart/form-data" action="contato-exportar-executar.sp" style="margin:0;">
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
            <td class="linkCinza"><select name="idContatoGrupo" class="forms2">
            <option value="">Todos os grupos</option>
		 <ilion:contatoGrupoLista varRetorno="contatoGrupos"/>
		 <c:forEach var="c" items="${contatoGrupos}">
		  <option value="<c:out value='${c.id}'/>"><c:out value='${c.nome}'/> - <c:out value='${c.qtd}'/></option>
		  </c:forEach>
		</select></td>
          </tr>
         <tr> 
            <td align="right" valign="middle" class="linkCinzaEscuro">Permiss&atilde;o para<br/> recebimento de e-mail:</td>
            <td class="linkCinza"><input type="radio" name="permissaoEmail" value="Todos" checked="checked"/> Todos 
            <input type="radio" name="permissaoEmail" value="true"/> Sim 
            <input type="radio" name="permissaoEmail" value="false"/> Não</td>
          </tr>
          <tr> 
            <td align="right" valign="middle" class="linkCinzaEscuro">Exportar para:</td>
            <td class="linkCinza"><input type="radio" name="exportarPara" value="txt" checked="checked"/> .txt (texto) 
            <input type="radio" name="exportarPara" value="xls"/> .xls (excel)</td>
          </tr>
          <tr>
            <td align="right" valign="middle" nowrap class="linkCinzaEscuro">&nbsp;</td>
            <td class="linkCinza">&nbsp;</td>
          </tr>
          <tr>
            <td align="right" valign="middle" nowrap class="linkCinzaEscuro">&nbsp;</td>
            <td class="linkCinza">
              <input name="submit" type="submit" class="botao" value="Exportar" style="margin-right: 5px;"/>
              <input type="button" class="botao" value=" Sair " onclick="location='contato.sp'" style="margin-right:5px;"/></td>
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
</form>
<script type="text/javascript">
var m = "<c:out value='${param.m}'/>";
if(m=='nenhum-contato-exportado') {
	alert("Nenhum contato foi exportado!");
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