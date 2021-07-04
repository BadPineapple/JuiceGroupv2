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
<!-- inicio corpo usuario-busca -->    

	<jsp:include page="../include-admin-links.jsp" flush="true"/>
	
	<jsp:include page="include-email-links.jsp" flush="true"/>

	<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
	  <tr> 
	    <td class="tituloDestaque">Envio de E-mails (Servi&ccedil;o)</td>
	  </tr>
	</table>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="vlhTabela1">
		<tr>
			<th width="80" align="center"></th>
			<th width="80" align="center"></th>
			<th align="center"><b>Tipo</b></th>
			<th align="left"><b>Nome</b></th>
			<th align="center"><b>Prioridade</b></th>
			<th><b>Dados</b></th>
		</tr>
	<c:forEach var="e" items="${emailSenders}">
		<tr>
			<td align="center"><a href="<ilion:url/>ilionnet/email-senders/${e.id}/editar">editar</a></td>
			<td align="center"><a href="javascript:;" onclick="testarEnvio(${e.id});">testar</a></td>
			<td align="center">${e.emailSenderEnum}</td>
			<td>${e.nome}</td>
			<td align="center">${e.prioridade}</td>
			<td><pre style="overflow: auto;">${e.dadosJson}</pre></td>
		</tr>
	</c:forEach>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr> 
	    <td class="divisaoAzul"></td>
	  </tr>
	</table>
	
	<input 
		type="button" 
		class="botao" 
		value="Incluir Novo (SMTP)" 
		onclick="location='<ilion:url/>ilionnet/email-senders/smtp/novo'"
	/>
	
	<input 
		type="button" 
		class="botao" 
		value="Incluir Novo (Sendgrid)" 
		onclick="location='<ilion:url/>ilionnet/email-senders/sendgrid/novo'"
	/>

	<c:if test="${not empty exception}">
	<fieldset>
		<legend>Erro: </legend>
		<pre>${exception}</pre>
	</fieldset>
	</c:if>

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

<script type="text/javascript">

	function testarEnvio(id) {
		var url = '<ilion:url/>ilionnet/email-senders/'+id+'/testar';
		
		var email = prompt('E-mail de destino:');
		
		if( email ) {
			
			location = url+'?emailTo='+email;
		}
	}
	
	(function() {
		
		var m = "${param.m}";
		if( m == "envio-ok" ) {
			alert('Envio efetuado com sucesso.');
		}
		
	})();

</script>

</body>
</html>