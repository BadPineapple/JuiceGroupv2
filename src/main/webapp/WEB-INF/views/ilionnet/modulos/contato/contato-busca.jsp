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
    let cal1x = new CalendarPopup("testdiv1");
    //cal1x.setCssPrefix("TEST");
</script>

<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div>

<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo  -->

<jsp:include page="include-contato-links.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Contato - Busca</td>
  </tr>
</table>

<div style="margin: 0 0 8px 0;">
<ilion:permissao tipo="contato-form.sp">
<input type="button" class="botao" value="Incluir Novo" onclick="location='contato-form.sp'" style="margin-right: 5px;"/>
</ilion:permissao>
</div>

    <form name="formConsultaInicial" method="post" action="contato.sp" style="margin:0;">
      <input name="setarParametros" type="hidden" value="true"/>
      <input name="pagingPage" type="hidden" value="1"/>
      <input name="letraInicial" type="hidden" value=""/>
          <table width="100%" border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td class="linkAzulLefth texto">Buscar:
                <select name="idGrupoContato" id="idGrupoContato" class="forms2" style="margin-right:5px;">
                    <option value="">--- Grupo ---</option>
                    <ilion:contatoGrupoLista varRetorno="contatoGrupos"/>
                    <c:forEach var="c" items="${contatoGrupos}">
                    <option value="<c:out value='${c.id}'/>"><c:out value='${c.nome}'/> - <c:out value='${c.qtd}'/></option>
                    </c:forEach>
                </select> <input type="text" name="palavraChave" id="palavraChave" class="forms2" style="width:150px;margin-right:5px;"/> </td>
                <td width="14%" class="linkAzulLefth texto">Data início:
                    <input type="text" name="dataInicio" id="dataInicio" class="forms2" style="width:80px;margin-right:5px;"/> <a href="javascript:;"
                     onClick="cal1x.select(document.getElementById('dataInicio'),'linkDataInicio','dd/MM/yyyy'); return false;" id="linkDataInicio" name="linkDataInicio">
                     <img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a>
                </td>
                <td width="13%" class="linkAzulLefth texto">Data fim:
                    <input type="text" name="dataFim" readonly="true" id="dataFim" class="forms2" style="width:80px;margin-right:5px;"/> <a href="javascript:;"
                     onClick="cal1x.select(document.getElementById('dataFim'),'linkDataFim','dd/MM/yyyy'); return false;" id="linkDataFim" name="linkDataFim">
                     <img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a>
                </td>
                <td><input type="submit" class="botao" value="Buscar"/></td>
            </tr>
           </table>
    </form>

<form name="formConsultaInicial" method="post" action="contato.sp" style="margin:0;">
          <input name="setarParametros" type="hidden" value="true"/>
          <input name="pagingPage" type="hidden" value="1"/>
      <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr class="tDcre01Center"> 
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="A"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="B"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="C"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="D"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="E"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="F"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="G"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="H"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="I"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="J"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="K"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="L"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="M"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="N"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="O"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="P"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="Q"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="R"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="S"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="T"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="U"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="V"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="X"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="Y"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="W"/></td>
            <td align="center" class="linkCinza"><input name="letraInicial" type="submit" class="botaoBusca" value="Z"/></td>
          </tr>
      </table>
        </form>

<form method="post" action="contato-excluir-selecionados.sp" onsubmit="return confirm('Deseja realmente excluir todos os contatos selecionados?');">
<c:set var="cont" value="0"/>
<valuelist:root value="contatos" url="?setarParametros=true&" includeParameters="#|idGrupoContato|palavraChave|letraInicial|dataInicio|dataFim">
      <table width="100%">
        <tr> 
          <td width="100%" align="right" class="texto"><c:out value="${contatos.valueListInfo.totalNumberOfEntries}"/>
            item(ns)</td>
          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
        </tr>
      </table>
      <table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0">
        <valuelist:row bean="contato"> 
		<valuelist:column title=""> 
		<valuelist:attribute name="align" value="center"/>
			<a href="contato-form.sp?id=<c:out value='${contato.id}'/>">editar</a>
		</valuelist:column> 
		<valuelist:column title=""> 
		<valuelist:attribute name="align" value="center"/>
			<a href="contato-comentarios.sp?id=<c:out value='${contato.id}'/>" title="Hist&oacute;rico de coment&aacute;rios">coment&aacute;rios</a>
		</valuelist:column> 
		<valuelist:column title="Nome" sortable="asc" property="nome"> 
		<valuelist:attribute name="align" value="left"/>
			<c:out value="${contato.nome}"/>
		</valuelist:column> 
		<valuelist:column title="Telefone">
		<valuelist:attribute name="align" value="center"/> 
        	<c:out value="${contato.telefone}"/>
        </valuelist:column> 
		<valuelist:column title="E-mail" sortable="asc" property="email">
		<valuelist:attribute name="align" value="center"/> 
        	<a href="mailto:<c:out value='${contato.email}'/>" class="texto"><c:out value='${contato.email}'/></a>
        </valuelist:column>
		<valuelist:column title="Newsletter">
		<valuelist:attribute name="align" value="center"/> 
        	<c:if test="${contato.permissaoEmail}">Sim</c:if>
        </valuelist:column>
        <valuelist:column title="Data">
            <valuelist:attribute name="align" value="center"/>
            <fmt:formatDate value="${ contato.dataCriacao }"  pattern="dd/MM/yyyy HH:mm:ss"/>
        </valuelist:column>
        <valuelist:column title="Grupos">
		<valuelist:attribute name="align" value="center"/> 
			<c:out value="${contato.gruposFormatados}"/>
        </valuelist:column>
		<valuelist:column title="<input type='checkbox' onclick='selecionarTodos(this);'/>">
		<valuelist:attribute name="width" value="30"/>
		<valuelist:attribute name="align" value="center"/>
			<input type="checkbox" name="idsContatos" id="check-<c:out value='${cont}'/>" value="<c:out value='${contato.id}'/>"/>
        </valuelist:column>
<c:set var="cont" value="${cont+1}"/>
		</valuelist:row> 
      </table>
	
	<div style="padding:10px;text-align:right;"><input type="submit" class="botao" value="Excluir Contato(s) selecionados"/></div>
	
      <table width="100%">
        <tr> 
          <td width="100%" align="right" class="texto"><c:out value="${contatos.valueListInfo.totalNumberOfEntries}"/>
            item(ns)</td>
          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
        </tr>
      </table>
</valuelist:root> 

</form>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<div style="margin: 0 0 5px 0;">
<ilion:permissao tipo="contato-form.sp">
<input type="button" class="botao" value="Incluir Novo" onclick="location='contato-form.sp'" style="margin-right: 5px;"/>
</ilion:permissao>
</div>


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
function selecionarTodos(checkbox) {
	for(var i=0;i<150;i++) {
		var obj = document.getElementById('check-'+i);
		if(obj != null) {
			obj.checked = checkbox.checked;
		}
	}
}

function selecionarOption(idSelect, valor) {
	var select = document.getElementById(idSelect);
	for(var i=0;i<select.length;i++) {
		if(select.options[i].value==valor) {
			select.options[i].selected = true;
		}
	}
}

selecionarOption('idGrupoContato', '<c:out value='${vlhForm.idGrupoContato}'/>');

document.getElementById('palavraChave').value = "<c:out value='${vlhForm.palavraChave}'/>";

document.getElementById('dataInicio').value = "<fmt:formatDate value="${vlhForm.dataInicio}"  pattern="dd/MM/yyyy"/>";

document.getElementById('dataFim').value = "<fmt:formatDate value="${vlhForm.dataFim}"  pattern="dd/MM/yyyy"/>";


var qtd = "<c:out value='${param.qtdExcluidos}' default='Nenhum'/>";
if(qtd != 'Nenhum' && qtd != 'null') {
	alert(qtd+' contatos excluídos com SUCESSO.');
}

var m = "<c:out value='${param.m}'/>";
if(m=='contato-excluido') {
	alert("Contato excluído com sucesso!");
} 

</script>

</body>
</html>