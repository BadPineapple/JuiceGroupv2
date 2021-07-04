<%@ include file="/ilionnet/taglibs.jsp"%>

<html>
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../../include-head.jsp" flush="true"/>

</head>

<body>
<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo -->    

	<jsp:include page="../include-admin-links.jsp" flush="true"/>
	
	<jsp:include page="include-email-links.jsp" flush="true"/>

	<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
	  <tr> 
	    <td class="tituloDestaque">Envio de E-mails (Servi&ccedil;o) &gt; Inclus&atilde;o / Altera&ccedil;&atilde;o</td>
	  </tr>
	</table>
	
	<form:form commandName="emailSender" method="post" action="?" cssStyle="margin:0;">
	<span style="color:#FF0000;"><form:errors path="*"/></span>
	
	<div style="padding:5px 0 5px 0;">
		<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
		<input type="button" class="botao" value="Sair" onClick="location='<ilion:url/>ilionnet/email-senders'" style="margin-right: 5px;"/>
	</div>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	  <tr> 
	    <td class="tituloDestaque"><strong><font color="#003366">Dados do Servi&ccedil;o</font></strong></td>
	  </tr>
	</table>
	      
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	  <tr> 
	    <td width="20%" align="right" class="linkCinzaEscuro">Servi&ccedil;o:</td>
	    <td class="linkCinza">${emailSender.emailSenderEnum}</td>
	  </tr>
	  <tr> 
	    <td align="right" class="linkCinzaEscuro">* Nome:</td>
	    <td class="linkCinza"><form:input path="nome" cssClass="forms2" cssStyle="width:200px;"/></td>
	  </tr>
	  <tr> 
	    <td width="20%" align="right" class="linkCinzaEscuro">* Prioridade:</td>
	    <td class="linkCinza"><form:select path="prioridade" cssClass="forms2">
	    	<form:option value=""></form:option>
	    	<form:option value="1">1</form:option>
	    	<form:option value="2">2</form:option>
	    	<form:option value="3">3</form:option>
	    	<form:option value="4">4</form:option>
	    	<form:option value="5">5</form:option>
	    	<form:option value="6">6</form:option>
	    	<form:option value="-1">-1 (inativo)</form:option>
	    </form:select></td>
	  </tr>
	  <tr> 
	    <td align="right" class="linkCinzaEscuro" colspan="2"></td>
	  </tr>
	  <tr> 
	    <td align="right" class="linkCinzaEscuro">* E-mail:</td>
	    <td class="linkCinza"><form:input path="sendGridVH.email" cssClass="forms2" cssStyle="width:200px;"/></td>
	  </tr>
	  <tr> 
	    <td align="right" class="linkCinzaEscuro">* API Key:</td>
	    <td class="linkCinza"><form:input path="sendGridVH.apiKey" cssClass="forms2" cssStyle="width:400px;"/></td>
	  </tr>
	</table>
	
		
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td class="divisaoAzul"></td>
	  </tr>
	</table>
	
	<div style="padding:5px 0 5px 0;">
	
	<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
	<input type="button" class="botao" value="Sair" onClick="location='<ilion:url/>ilionnet/email-senders'" style="margin-right: 5px;"/>
	
	</form:form>



<!-- fim corpo --></td>
  </tr>
</table>
</td>
  </tr>
    <tr>
     <td class="rodape">Ilion - <a href="http://www.ilion.com.br/" target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
  </tr>
</table>
<script type="text/javascript">
var m = "<%= request.getParameter("m") %>";
if(m == "ok") {
	alert("Dados gravados com sucesso.");
}
</script>
</body>
</html>