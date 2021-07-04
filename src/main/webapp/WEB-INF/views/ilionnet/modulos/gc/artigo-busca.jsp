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
<c:if test="${ ! categoriaArtigo.subCategoriaConfig.possuiSubCategorias}">
<c:out value='${categoriaArtigo.nome}'/> &gt; 
</c:if>
<c:if test="${categoriaArtigo.subCategoriaConfig.possuiSubCategorias}">
<a href="gc-busca.sp?nomeCategoria=<c:out value='${categoriaArtigo.nome}'/>"><c:out value='${categoriaArtigo.nome}'/></a> &gt; 
<c:out value='${subCategoria.nome}'/> &gt;
</c:if>

Artigos</div>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Artigos</td>
</tr>
</table>

<form method="post" action="gc-busca.sp" style="margin: 10px 0 10px 0;">
	<input type="hidden" name="setarParametros" value="true"/>
	<input type="hidden" name="pagingPage" value="1"/>
	<input type="hidden" name="site" value="${siteSelecionado}"/>
	<input type="hidden" name="nomeCategoria" value="<c:out value='${categoriaArtigo.nome}'/>"/>
	<input type="hidden" name="idSubCategoria" value="<c:out value='${subCategoria.id}'/>"/>
	Palavra chave:
	<input type="text" name="palavraChave" class="forms2" value="<c:out value='${vlhForm.palavraChave}'/>"/> 
	<input type="submit" name="submit" value="Buscar" class="botao"/>
</form>

    <div style="clear: both;margin: 5px 0 5px 0;">
    <input type="button" name="button" value="Incluir Novo Artigo" onclick="location='artigo-form.sp?idCategoria=<c:out value='${categoriaArtigo.id}'/>&idSubCategoria=<c:out value='${subCategoria.id}'/>';" class="botao" style="margin-right: 5px;"/>
	
	<c:if test="${categoriaArtigo.subCategoriaConfig.possuiSubCategorias}">
		<input type="button" name="button" value="Sub-Categorias" onclick="location='gc-busca.sp?site=${categoriaArtigo.site}&nomeCategoria=${categoriaArtigo.nome}';" class="botao" style="margin-right: 5px;"/>
	</c:if>
	
	<c:if test="${categoriaArtigo.artigoConfig.possuiRSS}">
	<input type="button" name="button" value="RSS" onclick="window.open('endereco-rss-form.sp?nomeClasse=CategoriaArtigo&nomeCategoria=<c:out value='${categoriaArtigo.nome}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');" class="botao" style="margin-right: 5px;"/>
	<input type="button" name="button" value="Excluir todas as notï¿½cias RSS" onclick="if(confirm('Tem certeza da exclusï¿½o de todas as notï¿½cias desta categoria?')){location='artigo-todos-excluir.sp?nomeCategoria=<c:out value='${categoriaArtigo.nome}'/>';}" class="botao" style="margin-right: 5px;"/>
	</c:if>
	<c:if test="${categoriaArtigo.subCategoriaConfig.possuiRssSubCategoria}">
	<input type="button" name="button" value="RSS" onclick="window.open('endereco-rss-form.sp?nomeClasse=SubCategoriaArtigo&idObjeto=<c:out value='${subCategoria.id}'/>','noticasRSS','scrollbars=yes,resizable=yes,width=650,height=440,screenX=1,screenY=1,left=150,top=60');" class="botao" style="margin-right: 5px;"/>
	</c:if>
    </div>
    
<c:if test="${artigos.valueListInfo.totalNumberOfEntries==0}">N&atilde;o h&aacute; artigos cadastrados na categoria <c:out value='${categoriaArtigo.nome}'/>.</c:if>
<c:if test="${artigos.valueListInfo.totalNumberOfEntries!=0}">

<valuelist:root value="artigos" url="?setarParametros=true&" includeParameters="site|nomeCategoria|idCategoria|idSubCategoria|palavraChave|#">
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
		<c:if test="${categoriaArtigo.artigoConfig.possuiArtigoMenu}">
  		<valuelist:column title="Tipo de menu">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="100"/>
				<c:choose>
					<c:when test="${artigo.artigoMenuEnum=='LINK'}">
						Link
					</c:when>
					<c:otherwise>
						Conteúdo
					</c:otherwise>
				</c:choose>
        </valuelist:column>
		</c:if>
		<c:if test="${categoriaArtigo.artigoConfig.ordem!='posicao'}">
  		<valuelist:column title="Dt. Public.">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="100"/>
						<fmt:formatDate value="${artigo.dataPublicacao}" pattern="dd/MM/yyyy HH:mm"/>
        </valuelist:column>
		</c:if>
		<c:if test="${categoriaArtigo.artigoConfig.ordem=='posicao'}">
  		<valuelist:column title="Pos.">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="60"/>
						<c:out value="${artigo.posicao}"/><a title="Mover para cima" style="position:relative;top:3px" href="artigo-posicao-abaixo.sp?id=<c:out value='${artigo.id}'/>"><img src="design/imagens/arrow_up_16x16.gif" border="0"/></a><a title="Mover para baixo" style="position:relative;top:3px" href="artigo-posicao-acima.sp?id=<c:out value='${artigo.id}'/>"><img src="design/imagens/arrow_down_16x16.gif" border="0"/></a>
        </valuelist:column>
		</c:if>
  		<valuelist:column title="Status">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="90"/>
						<ilion:artigoStatus artigo="${artigo}" />
        </valuelist:column>
        <%-- 
        <c:if test="${categoriaArtigo.artigoConfig.possuiComentarios}">
        <valuelist:column title="&nbsp;">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="50"/>
						<a title="Visualizar comentï¿½rios" href="comentarios.sp?idArtigo=<c:out value='${artigo.id}'/>&setarParametros=true"><img style="vertical-align: middle;" src="design/imagens/icon-comment.gif" border="0"/> (<ilion:comentarioQtd obj='${artigo}'/>)</a>
        </valuelist:column>
        </c:if>
        --%>
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
		<input type="button" name="button" value="Sub-Categorias" onclick="location='gc-busca.sp?site=${categoriaArtigo.site}&nomeCategoria=${categoriaArtigo.nome}';" class="botao" style="margin-right: 5px;"/>
	</c:if>
    
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
<script type="text/javascript">
var m = "<c:out value='${param.m}'/>";
if(m=='categoria-nao-encontrada') {
	alert("Categoria não encontrada!");
} else if(m=='possui-comentarios') {
	alert("Este artigo não pode ser excluído, possui comentários!");
}
</script>
</body>
</html>