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

<script type="text/javascript" src="design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="design/script/common.js"></script>
<script type="text/javascript">
document.write(getCalendarStyles());
var cal1x = new CalendarPopup("testdiv1");
//cal1x.setCssPrefix("TEST");
</script>
<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Enquetes &gt; Inclus&atilde;o / Altera&ccedil;&atilde;o</td>
</tr>
</table>



<form:form commandName="enquete" method="post" action="enquete-form.sp">

<div style="color: red;clear: both;"><form:errors path="*"/></div>

<div style="margin:5px 0 5px 0;">
<input type="submit" class="botao" name="acao" value=" Salvar " style="margin-right: 10px;"/>
<input type="button" class="botao" onclick="location='gc-busca.sp?nomeCategoria=enquete';" value=" Sair "/>
</div>


<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td width="110" align="right" valign="middle" class="linkCinzaEscuro">Enquete:</td>
    <td width="*" class="linkCinza"><form:input path="assunto" cssClass="forms2" cssStyle="width:450px;"/></td>
  </tr>
  <tr> 
      <td width="110" align="right" valign="middle" class="linkCinzaEscuro">Op&ccedil;&atilde;o 1:</td>
    <td width="*" class="linkCinza"><form:input path="opcao1" cssClass="forms2" size="20"/></td>
  </tr>
  <tr> 
      <td align="right" valign="middle" class="linkCinzaEscuro">Op&ccedil;&atilde;o 2:</td>
    <td class="linkCinza"><form:input path="opcao2" cssClass="forms2" size="20"/></td>
  </tr>
  <tr> 
      <td align="right" valign="middle" class="linkCinzaEscuro">Op&ccedil;&atilde;o 3:</td>
    <td class="linkCinza"><form:input path="opcao3" cssClass="forms2" size="20"/></td>
  </tr>
  <tr> 
      <td align="right" valign="middle" class="linkCinzaEscuro">Op&ccedil;&atilde;o 4:</td>
    <td class="linkCinza"><form:input path="opcao4" cssClass="forms2" size="20"/></td>
  </tr>
    <tr> 
      <td align="right" valign="middle" class="linkCinzaEscuro">Op&ccedil;&atilde;o 5:</td>
    <td class="linkCinza"><form:input path="opcao5" cssClass="forms2" size="20"/></td>
  </tr>
<tr> 
     <td width="110" align="right" valign="middle" class="linkCinzaEscuro">Data publicação: </td>
      <td width="*" class="linkCinza"><form:input path="dataPublicacao" id="dataPublicacao" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10"/>
               <a href="javascript:;" 
onclick="cal1x.select(document.getElementById('dataPublicacao'),'linkDataPublicacao','dd/MM/yyyy'); return false;" id="linkDataPublicacao" name="linkDataPublicacao"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a></td>
  </tr>
  <tr> 
      <td align="right" valign="middle" class="linkCinzaEscuro">Data expira&ccedil;&atilde;o: </td>
      <td class="linkCinza"><form:input path="dataExpiracao" id="dataExpiracao" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10"/>
               <a href="javascript:;" 
onclick="cal1x.select(document.getElementById('dataExpiracao'),'linkDataExpiracao','dd/MM/yyyy'); return false;" id="linkDataExpiracao" name="linkDataExpiracao"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a></td>
  </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">Status:</td>
    <td class="linkCinza"><form:radiobutton path="status" id="statusPublicado" value="Publicado" cssStyle="position:relative;top:2px;"/> <label for="statusPublicado">Publicado</label> 
<form:radiobutton path="status" id="statusNaoPublicado" value="Não Publicado" cssStyle="position:relative;top:2px;"/> <label for="statusNaoPublicado">Não Publicado </label></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<div style="margin:5px 0 5px 0;">
<input type="submit" class="botao" name="acao" value=" Salvar " style="margin-right: 10px;"/>
<input type="button" class="botao" onclick="location='gc-busca.sp?nomeCategoria=enquete';" value=" Sair "/>
</div>
</form:form>

    
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