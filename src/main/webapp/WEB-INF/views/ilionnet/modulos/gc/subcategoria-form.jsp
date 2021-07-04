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
<div class="hierarquia"><a href="gc.sp?idioma=<c:out value='${idiomaSelecionado}'/>">Web-Site</a> &gt; ${siteSelecionado} &gt;  
<c:out value='${categoriaArtigo.grupo}'/> &gt;
<a href="gc-busca.sp?site=${subCategoria.categoriaArtigo.site}&nomeCategoria=${subCategoria.categoriaArtigo.nome}"><c:out value='${subCategoria.categoriaArtigo.nome}'/></a> &gt;  
Sub-Categorias &gt;
Inclus&atilde;o / Altera&ccedil;&atilde;o </div>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Sub-Categorias &gt; Inclus&atilde;o / Altera&ccedil;&atilde;o</td>
</tr>
</table>

    <div style="clear: both;height: 10px;">
    </div>

<link href="<c:url value='/ilionnet/design/script/jquery/iliontheme.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/ilionnet/design/script/jquery/iliontheme.dialog.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/ilionnet/design/script/jquery/iliontheme.resizable.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery-1.2.6.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery-ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery-ui.dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery-ui.resizable.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery-ui.draggable.js'/>"></script>

<script type="text/javascript" src="design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="design/script/common.js"></script>
<script type="text/javascript">
document.write(getCalendarStyles());
var cal1x = new CalendarPopup("testdiv1");
//cal1x.setCssPrefix("TEST");
</script>
<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></div>

<c:if test="${possuiRss}">
<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top:5px;">
  <tr> 
  	<td><a href="javascript:;" onClick="window.open('GCnoticiasXmlForm.sp?simpleName=SubCategoriaArtigo&id=<c:out value='${subCategoria.id}'/>&site=<c:out value='${siteSelected}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');">Configurar recebimento de noticias via RSS</a></td>
    </tr>
</table>
</c:if>

<form:form commandName="subCategoria" name="formSubCategoria" method="post" action="subcategoria-form.sp">
<input type="hidden" name="nomeCategoria" value="<c:out value='${artigo.categoriaArtigo.nome}'/>"/>
<div style="color: red;clear: both;"><form:errors path="*"/></div>

<input type="submit" class="botao" value=" Salvar " style="margin-right: 10px;"/>
<input type="button" class="botao" onClick="location='gc-busca.sp?site=${subCategoria.categoriaArtigo.site}&nomeCategoria=${subCategoria.categoriaArtigo.nome}';" value=" Sair "/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin-top: 5px;">
  <tr> 
    <td class="tituloDestaque">Inclus&atilde;o/Altera&ccedil;&atilde;o de Sub-Categoria</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
 <tr> 
   <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Nome:</td>
   <td width="*" class="linkCinza"><form:input path="nome" cssClass="forms2" size="53"/></td>
 </tr>
 <c:if test="${not empty subCategoria.id}"> 
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Url:</td>
    <td class="linkCinza"><ilion:link obj="${subCategoria}"/></td>
  </tr>
</c:if>
 <c:if test="${subCategoria.categoriaArtigo.subCategoriaConfig.possuiDescricao}"> 
 <tr> 
   <td align="right" valign="middle" class="linkCinzaEscuro">Descri&ccedil;&atilde;o:</td>
   <td class="linkCinza"><form:textarea path="descricao" cssClass="forms2" rows="2" cols="50"/></td>
 </tr>
</c:if>
 <c:if test="${subCategoria.categoriaArtigo.subCategoriaConfig.possuiTelefone}"> 
 <tr> 
   <td align="right" valign="middle" class="linkCinzaEscuro">Telefone:</td>
   <td class="linkCinza"><form:input path="telefone" cssClass="forms2" size="20" maxlength="30"/></td>
 </tr>
</c:if>
 <c:if test="${subCategoria.categoriaArtigo.subCategoriaConfig.possuiEmail}"> 
 <tr> 
   <td align="right" valign="middle" class="linkCinzaEscuro">E-mail:</td>
   <td class="linkCinza"><form:input path="email" cssClass="forms2" size="50" maxlength="100"/></td>
 </tr>
</c:if>
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro"><a href="javascript:;"><img src="design/imagens/icon_popup.gif" width="17" height="11" border="0" onClick="window.open('imagem-form.sp?nomeClasse=SubCategoriaArtigo&idObjeto=<c:out value='${subCategoria.id}'/>&codigo=<c:out value='${subCategoria.codControle}'/>','','scrollbars=yes,resizable=yes,width=900,height=600,screenX=1,screenY=1,left=150,top=60')"></a>Fotos:</td>
              <td class="linkCinza">Edi&ccedil;&atilde;o de fotos</td>
            </tr>
            <tr> 
              <td align="right" valign="middle" class="linkCinzaEscuro">Status:</td>
              <td class="linkCinza"><form:radiobutton path="status" id="statusPublicado" value="Publicado" cssStyle="position:relative;top:2px;"/> <label for="statusPublicado">Publicado</label> 
				  <form:radiobutton path="status" id="statusNaoPublicado" value="Não Publicado" cssStyle="position:relative;top:2px;"/> <label for="statusNaoPublicado">Não Publicado </label></td>
            </tr>
<tr> 
              <td align="right" valign="middle" class="linkCinzaEscuro">Data publicação: </td>
              <td width="*" class="linkCinza"><form:input path="dataPublicacao" id="dataPublicacao" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10" />
               <a href="javascript:;" 
onClick="cal1x.select(document.getElementById('dataPublicacao'),'linkDataPublicacao','dd/MM/yyyy'); return false;" id="linkDataPublicacao" name="linkDataPublicacao"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0"/></a></td>
 </tr>
 <tr> 
   <td align="right" valign="middle" class="linkCinzaEscuro">Data expira&ccedil;&atilde;o: </td>
   <td class="linkCinza"><form:input path="dataExpiracao" id="dataExpiracao" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10" />
               <a href="javascript:;" 
onClick="cal1x.select(document.getElementById('dataExpiracao'),'linkDataExpiracao','dd/MM/yyyy'); return false;" id="linkDataExpiracao" name="linkDataExpiracao"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0"/></a></td>
 </tr>
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Posi&ccedil;&atilde;o:</td>
   <td class="linkCinza"><form:input path="posicao" cssClass="forms2" size="5" cssStyle="text-align:center;"/></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<input type="submit" class="botao" value=" Salvar " style="margin-right: 10px;"/>
<input type="button" class="botao" onClick="location='gc-busca.sp?site=${subCategoria.categoriaArtigo.site}&nomeCategoria=${subCategoria.categoriaArtigo.nome}';" value=" Sair "/>

</form:form>

<div id="alertSucesso" class="iliontheme texto" style="width:170px;text-align:center;padding:5px;display:none;">Sub-Categoria atualizada com sucesso!</div>
<div id="alertExclusaoNaoPermitida" class="iliontheme texto" style="width:170px;text-align:center;padding:5px;display:none;">Sub-categoria possui artigos e não pode ser excluída!</div>
<script type="text/javascript">
var m = "<%=request.getParameter("m")%>";
if(m=='ok') {
	setTimeout(function(){
		document.getElementById('alertSucesso').style.display='';
		$('#alertSucesso').dialog({
			title: 'Edição de conteúdo',
			width:200,
			height:70
		});
		setTimeout(function(){
			$('#alertSucesso').dialog('destroy');
		},3000);
	}, 100);
} else if(m=='exclusao-nao-permitida') {
	setTimeout(function(){
		document.getElementById('alertExclusaoNaoPermitida').style.display='';
		$('#alertExclusaoNaoPermitida').dialog({
			title: 'Edição de conteúdo',
			width:200,
			height:70
		});
		setTimeout(function(){
			$('#alertExclusaoNaoPermitida').dialog('destroy');
		},3000);
	}, 100);
}
</script>
    
    

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