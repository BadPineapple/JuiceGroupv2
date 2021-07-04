<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>

<script type="text/javascript" src="design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="design/script/common.js"></script>
<script type="text/javascript">
document.write(getCalendarStyles());
var cal1x = new CalendarPopup("testdiv1");
//cal1x.setCssPrefix("TEST");
</script>
<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div
><table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo  -->

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Curriculo - Busca </td>
  </tr>
</table>

    <form name="formConsultaInicial" method="post" action="curriculo.sp" style="margin:0;">
      <input name="setarParametros" type="hidden" value="true"/>
      <input name="pagingPage" type="hidden" value="1"/>
 <table width="100%" border="0" cellspacing="1" cellpadding="0">
    <tr> 
      <td width="16%" class="linkAzulLefth texto">Palavra chave:
        <input type="text" name="palavraChave" id="palavraChave" class="forms2" style="width:150px;margin-right:5px;"/></td>
      <td width="16%" class="linkAzulLefth texto">Cargo Pretendido: 
        <input type="text" name="areaTrabalho" id="areaTrabalho" class="forms2" style="width:150px;margin-right:5px;"/> </td>
		<td width="14%" class="linkAzulLefth texto">Data in�cio: 
        <input type="text" name="dataInicio" id="dataInicio" readonly="true" class="forms2" style="width:80px;margin-right:5px;"/> <a href="javascript:;" 
onClick="cal1x.select(document.getElementById('dataInicio'),'linkDataInicio','dd/MM/yyyy'); return false;" id="linkDataInicio" name="linkDataInicio"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a></td>
		 <td width="13%" class="linkAzulLefth texto">Data fim: 
        <input type="text" name="dataFim" readonly="true" id="dataFim" class="forms2" style="width:80px;margin-right:5px;"/> <a href="javascript:;" 
onClick="cal1x.select(document.getElementById('dataFim'),'linkDataFim','dd/MM/yyyy'); return false;" id="linkDataFim" name="linkDataFim"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a></td>
		<td width="18%" class="linkAzulLefth texto">Sexo : 
        <INPUT TYPE="radio" NAME="sexo" VALUE="M" class="forms2">
              Masc. 
              <INPUT TYPE="radio" NAME="sexo" VALUE="F" class="forms2">
              Fem. </td>
		<td width="17%" class="linkAzulLefth texto">Curso : 
        <input type="text" name="curso" id="curso" class="forms2" style="width:150px;margin-right:5px;"/> </td>
		<td width="6%" class="linkAzulLefth texto"><input type="submit" class="botao" value="Buscar"/></td>
     </tr>
   </table>
</form>

<valuelist:root value="curriculos" url="?setarParametros=true&" includeParameters="#|palavraChave"> 
      <table>
        <tr> 
          <td width="100%" align="right" class="texto"><c:out value="${curriculos.valueListInfo.totalNumberOfEntries}"/>
            item(ns)</td>
          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
        </tr>
      </table>
      
      <table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0">
        <valuelist:row bean="curriculo"> 
		<valuelist:column title=""> 
		<valuelist:attribute name="align" value="center"/>
			<a href="curriculo-detalhes.sp?id=<c:out value='${curriculo.id}'/>">Detalhes</a>
		</valuelist:column> 
		<valuelist:column title="Nome" sortable="asc" property="nome">
		<valuelist:attribute name="align" value="left"/>
			<c:out value="${curriculo.nome}"/>
		</valuelist:column> 
		<valuelist:column title="E-mail" sortable="asc" property="email">
		<valuelist:attribute name="align" value="center"/> 
        	<a href="mailto:<c:out value='${curriculo.email}'/>" class="texto"><c:out value='${curriculo.email}'/></a>
        </valuelist:column>
		<valuelist:column title="Tel. Res. / Tel. Com. / Celular">
		<valuelist:attribute name="align" value="center"/> 
        	<c:out value="${curriculo.telefoneRes}"/> / <c:out value="${curriculo.telefoneCom}"/> / <c:out value="${curriculo.celular}"/>
        </valuelist:column> 
		<valuelist:column title="Cidade/UF">
		<valuelist:attribute name="align" value="center"/> 
        	<c:out value="${curriculo.cidade}"/>/<c:out value="${curriculo.estado}"/>
        </valuelist:column>
		</valuelist:row> 
      </table>

      <table>
        <tr> 
          <td width="100%" align="right" class="texto"><c:out value="${curriculos.valueListInfo.totalNumberOfEntries}"/>
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


<script type="text/javascript">
document.getElementById('palavraChave').value = "<c:out value='${vlhForm.palavraChave}'/>";

var m = "<%=request.getParameter("m")%>";
if(m=='contato-excluido') {
	alert("Contato exclu�do com sucesso!");
} 

</script>

</body>
</html>