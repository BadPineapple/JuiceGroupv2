<%@ include file="/ilionnet/taglibs.jsp"%>

<html>
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../../include-head.jsp" flush="false"/>
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
	    <td class="tituloDestaque">Emails</td>
	  </tr>
	</table>
	
	<span class="texto">Envio via: ${emailSender.nome} (${emailSender.emailSenderEnum})</span>
	
	<valuelist:root value="emails" url="?setarParametros=true&" includeParameters="#"> 
	      <table>
	        <tr> 
	          <td width="100%" align="right" class="texto"><c:out value="${emails.valueListInfo.totalNumberOfEntries}"/>
	            item(ns)</td>
	          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
	        </tr>
	      </table>
	      <table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0">
	        <valuelist:row bean="email"> 
			<valuelist:column title="Para" sortable="asc" property="toEmail"> 
				<valuelist:attribute name="align" value="center"/>
				${email.toEmail}/${email.toName}
			</valuelist:column> 
			<valuelist:column title="Responder a"> 
				<valuelist:attribute name="align" value="center"/>
				${email.replyToEmail}/${email.replyToName}
			</valuelist:column> 
			<valuelist:column title="Assunto"> 
		        ${email.subject}
	        </valuelist:column> 
			<valuelist:column title="Data" sortable="asc" property="createdAt">
				<valuelist:attribute name="align" value="center"/> 
		        <fmt:formatDate value="${email.createdAt}" pattern="dd/MM HH:mm"/>
	        </valuelist:column> 
			<valuelist:column title="Qtd. tentativas" sortable="asc" property="errorCount">
				<valuelist:attribute name="align" value="center"/>
		        ${email.errorCount}
	        </valuelist:column> 
			</valuelist:row> 
	      </table>
	      <table>
	        <tr> 
	          <td width="100%" align="right" class="texto"><c:out value="${emails.valueListInfo.totalNumberOfEntries}"/>
	            item(ns)</td>
	          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
	        </tr>
	      </table>
	</valuelist:root> 

      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td class="divisaoAzul"></td>
        </tr>
      </table>



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