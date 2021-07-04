<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="include-head.jsp" flush="true"/>
</head>

<body>
<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">

<ilion:artigoLista categoria="abertura-ilionnet" order="posicao" varRetorno="artigosAberturaIlionnet"/>
<style type="text/css">
.artigoConteudoDiv {
clear:both;
}
</style>

<c:forEach var="artigo" items="${artigosAberturaIlionnet}">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="20" rowspan="4" class="texto01">&nbsp;</td>
          <td class="texto01">&nbsp;</td>
        </tr>
        <tr> 
          <td class="tituloDestaque"><c:out value="${artigo.titulo}"/></td>
        </tr>
        <tr> 
          <td class="texto01">&nbsp;</td>
        </tr>
        <tr> 
          <td class="texto"><ilion:artigoConteudoImprime obj="${artigo}" layout="texto" /></td>
        </tr>
        <tr> 
          <td class="texto01">&nbsp;</td>
        </tr>
      </table>
	  </c:forEach>

	</td>
  </tr>
</table>
</td>
  </tr>
    <tr>
     <td class="rodape">Ilion - <a href="http://www.ilion.com.br/" target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
  </tr>
</table>

<c:if test="${ qtdEmailsParaEnvio>30 }">
<script type="text/javascript">
	(function() {
		alert('Há ${qtdEmailsParaEnvio} email(s) para ser(em) disparados.');
	})();
</script>
</c:if>

</body>
</html>