<%@ include file="/ilionnet/taglibs.jsp"%>

<%@page import="ilion.gc.negocio.GCNegocio"%>
<%@page import="ilion.gc.negocio.SubCategoriaArtigo"%>
<%@page import="ilion.util.Uteis"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>

</head>

<body>
<script type="text/javascript">
function excluirArtigo(id) {
	if( confirm('Deseja realmente excluir este artigo?') ) {
		location='artigo-excluir.sp?id='+id;
	}
}
</script>

<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">

<div style="float: left;">
<div class="hierarquia"><a href="gc.sp?idioma=<c:out value='${idiomaSelecionado}'/>">Web Site</a> &gt; ${siteSelecionado} &gt; 
<c:out value='${categoriaArtigo.grupo}'/> &gt; 
<c:out value='${categoriaArtigo.nome}'/> &gt; 
Not&iacute;cias</div>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Notícias</td>
</tr>
</table>

<form method="post" action="noticias-busca.sp" style="margin: 10px 0 10px 0;">
	<input type="hidden" name="setarParametros" value="true"/>
	<input type="hidden" name="pagingPage" value="1"/>
	<input type="hidden" name="nomeCategoria" value="<c:out value='${categoriaArtigo.nome}'/>"/>
	<select name="idSubCategoria" id="idSubCategoria" class="forms2">
		<option value="">- sub-categoria -</option>
		<c:forEach var="s" items="${subCategorias}">
			<option value="${s.id}">${s.nome}</option>
		</c:forEach>
	</select>
	<input type="text" name="palavraChave" class="forms2" value="<c:out value='${vlhForm.palavraChave}'/>"/> 
	<input type="submit" name="submit" value="Buscar" class="botao"/>
</form>

    <div style="clear: both;margin: 5px 0 5px 0;">
    <input type="button" name="button" value="Incluir Novo Artigo" onclick="location='artigo-form.sp?idCategoria=<c:out value='${categoriaArtigo.id}'/>&idSubCategoria=<c:out value='${subCategoria.id}'/>';" class="botao" style="margin-right: 5px;"/>
	
	<c:if test="${categoriaArtigo.subCategoriaConfig.possuiSubCategorias}">
		<input type="button" name="button" value="Sub-Categorias" onclick="location='subcategorias-busca.sp?site=${categoriaArtigo.site}&nomeCategoria=${categoriaArtigo.nome}';" class="botao" style="margin-right: 5px;"/>
	</c:if>
	
	<input type="button" name="button" value="Tópicos" onclick="location='topico-form.sp';" class="botao" style="margin-right: 5px;"/>
	
	<c:if test="${categoriaArtigo.artigoConfig.possuiRSS}">
	<input type="button" name="button" value="RSS" onclick="window.open('endereco-rss-form.sp?nomeClasse=CategoriaArtigo&nomeCategoria=<c:out value='${categoriaArtigo.nome}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');" class="botao" style="margin-right: 5px;"/>
	<input type="button" name="button" value="Excluir todas as notícias RSS" onclick="if(confirm('Tem certeza da exclusão de todas as notícias desta categoria?')){location='artigo-todos-excluir.sp?nomeCategoria=<c:out value='${categoriaArtigo.nome}'/>';}" class="botao" style="margin-right: 5px;"/>
	</c:if>
	<c:if test="${categoriaArtigo.subCategoriaConfig.possuiRssSubCategoria}">
	<input type="button" name="button" value="RSS" onclick="window.open('endereco-rss-form.sp?nomeClasse=SubCategoriaArtigo&idObjeto=<c:out value='${subCategoria.id}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');" class="botao" style="margin-right: 5px;"/>
	</c:if>
    </div>
    
<c:if test="${artigos.valueListInfo.totalNumberOfEntries==0}">N&atilde;o h&aacute; artigos cadastrados na categoria <c:out value='${categoriaArtigo.nome}'/>.</c:if>
<c:if test="${artigos.valueListInfo.totalNumberOfEntries!=0}">

<valuelist:root value="artigos" url="?setarParametros=true&" includeParameters="idCategoria|idSubCategoria|palavraChave|#">
    <table width="100%">
      <tr>
          <td width="100%" align="right" class="texto"><c:out value="${artigos.valueListInfo.totalNumberOfEntries}"/> 
            item(ns)</td>
        <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
      </tr>
    </table>
      <table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0">
<valuelist:row bean="artigo">
		<valuelist:column title="&nbsp;">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="60"/>
						<a href="artigo-form.sp?idCategoria=<c:out value='${artigo.categoriaArtigo.id}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>&id=<c:out value='${artigo.id}'/>">Editar</a> 
        </valuelist:column>
  		<valuelist:column title="Título">
		<valuelist:attribute name="width" value="*"/>
		<valuelist:attribute name="align" value="left"/>
						<span id="tituloSpan<c:out value='${artigo.id}'/>"><c:out value="${artigo.titulo}"/></span> <span style="color: #666666;"><c:out value='${artigo.secoesFormatadas}'/></span>
        </valuelist:column>
		<valuelist:column title="Sub-categoria">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="100"/>
				<c:out value="${artigo.subCategoria.nome}" />
        </valuelist:column>
		<valuelist:column title="Dt. Publicação">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="100"/>
				<fmt:formatDate value="${artigo.dataPublicacao}" pattern="dd/MM/yyyy HH:mm"/>
        </valuelist:column>
  		<valuelist:column title="Status">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="90"/>
				<ilion:artigoStatus artigo="${artigo}" />
        </valuelist:column>
        <valuelist:column title="&nbsp;">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="50"/>
				<a href="javascript:excluirArtigo('<c:out value='${artigo.id}'/>');" style="color:red;">Excluir</a>
        </valuelist:column>
<c:if test="${artigo.status!='Publicado'}">
<script type="text/javascript">
document.getElementById('tituloSpan<c:out value='${artigo.id}'/>').style.color='#999999';
</script>
</c:if>
</valuelist:row>
      </table>
    <table width="100%">
      <tr>
          <td width="100%" align="right" class="texto"><c:out value="${artigos.valueListInfo.totalNumberOfEntries}"/> 
            item(ns)</td>
        <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
      </tr>
    </table>
</valuelist:root>

</c:if>
    
    <div style="clear: both;padding: 10px 0 10px 0;">
    
    <input type="button" name="button" value="Incluir Novo Artigo" onclick="location='artigo-form.sp?idCategoria=<c:out value='${categoriaArtigo.id}'/>&idSubCategoria=<c:out value='${subCategoria.id}'/>';" class="botao" style="margin-right: 5px;"/>
    
    <c:if test="${categoriaArtigo.subCategoriaConfig.possuiSubCategorias}">
		<input type="button" name="button" value="Sub-Categorias" onclick="location='subcategorias-busca.sp?site=${categoriaArtigo.site}&nomeCategoria=${categoriaArtigo.nome}';" class="botao" style="margin-right: 5px;"/>
	</c:if>
    
    <input type="button" name="button" value="Tópicos" onclick="location='topico-form.sp';" class="botao" style="margin-right: 5px;"/>
    
	<c:if test="${categoriaArtigo.artigoConfig.possuiRSS}"><input type="button" name="button" value="RSS" onclick="window.open('artigo-rss-form.sp?nomeClasse=CategoriaArtigo&nomeCategoria=<c:out value='${categoriaArtigo.nome}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');" class="botao" style="margin-right: 5px;"/></c:if>
    <c:if test="${categoriaArtigo.subCategoriaConfig.possuiRssSubCategoria}">
	<input type="button" name="button" value="RSS" onclick="window.open('endereco-rss-form.sp?nomeClasse=SubCategoriaArtigo&idObjeto=<c:out value='${subCategoria.id}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');" class="botao" style="margin-right: 5px;"/>
	</c:if>
    </div>
    
    </td>
  </tr>
</table>
</td>
  </tr>
    <tr>
     <td class="rodape">Ilion - <a href="http://www.ilion.com.br/" target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
  </tr>
</table>
<script type="text/javascript" src="<ilion:url/>ilionnet/design/script/jquery/jquery.js"></script>
<script type="text/javascript">

$(function() {
	$('#idSubCategoria').val( '${vlhForm.idSubCategoria}' );
	$('#palavraChave').val( '${vlhForm.palavraChave}' );
});

var m = "<c:out value='${param.m}'/>";
if(m=='categoria-nao-encontrada') {
	alert("Categoria não encontrada!");
} else if(m=='possui-comentarios') {
	alert("Este artigo não pode ser excluído, possui comentários!");
}
</script>
</body>
</html>