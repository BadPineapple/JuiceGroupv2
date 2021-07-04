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
<div class="hierarquia"><a href="gc.sp?idioma=<c:out value='${idiomaSelecionado}'/>">Web Site</a> &gt; 
<c:out value='${categoriaProp.grupo}'/> &gt; 
<c:if test="${ ! categoriaProp.subCategorias}">
<c:out value='${nomeCategoria}'/> &gt; 
</c:if>
<c:if test="${categoriaProp.subCategorias}">
<a href="gc-busca.sp?nomeCategoria=<c:out value='${nomeCategoria}'/>"><c:out value='${nomeCategoria}'/></a> &gt; 
<c:out value='${subCategoria.nome}'/> &gt;
</c:if>

Artigos</div>
</div>
<div style="float: right;">
<select class="forms2" onchange="location='gc.sp?idioma='+this.value;">
		<option value="br" <c:if test="${idiomaSelecionado == 'br'}">selected</c:if>>Portugu�s Brasil</option>
		<option value="en" <c:if test="${idiomaSelecionado == 'en'}">selected</c:if>>English</option>
        </select>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Artigos &gt; Resultado da busca</td>
</tr>
</table>

<form method="post" action="gc-busca-geral.sp" style="margin: 10px 0 10px 0;">
<input type="hidden" name="setarParametros" value="true"/>
<input type="hidden" name="pagingPage" value="1"/>
Palavra chave: <input type="text" name="palavraChave" class="forms2" value="<c:out value='${vlhForm.palavraChave}'/>"/> <input type="submit" name="submit" value="Buscar" class="botao"/>
</form>

<c:if test="${empty artigos}">Nenhum artigo cadastrado.</c:if>
<c:if test="${not empty artigos}">

<valuelist:root value="artigos" url="?setarParametros=true&" includeParameters="palavraChave|#">
    <table>
      <tr>
          <td width="100%" align="right" class="texto"><c:out value="${artigos.valueListInfo.totalNumberOfEntries}"/> 
            item(ns)</td>
        <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
      </tr>
    </table>
      <table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0">
<valuelist:row bean="artigo">
		<valuelist:column title="Categoria">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="120"/>
						<a href="gc-busca.sp?nomeCategoria=<c:out value='${artigo.categoriaArtigo.nome}'/>"><c:out value='${artigo.categoriaArtigo.nome}'/></a>
        </valuelist:column>
		<valuelist:column title="Sub-Categoria">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="120"/>
						<a href="gc-busca.sp?nomeCategoria=<c:out value='${artigo.categoriaArtigo.nome}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>"><c:out value='${artigo.subCategoria.nome}'/></a>
        </valuelist:column>
		<valuelist:column title="&nbsp;">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="60"/>
						<a href="artigo-form.sp?nomeCategoria=<c:out value='${artigo.categoriaArtigo.nome}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>&id=<c:out value='${artigo.id}'/>">Editar</a>
        </valuelist:column>
  		<valuelist:column title="T�tulo">
		<valuelist:attribute name="width" value="*"/>
		<valuelist:attribute name="align" value="left"/>
						<span id="tituloSpan<c:out value='${artigo.id}'/>"><c:out value="${artigo.titulo}"/></span>
        </valuelist:column>
		<c:if test="${categoriaProp.order!='posicao'}">
  		<valuelist:column title="Dt. Publica��o">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="100"/>
						<fmt:formatDate value="${artigo.dataPublicacao}" pattern="dd/MM/yyyy"/>
        </valuelist:column>
		</c:if>
		<c:if test="${categoriaProp.order=='posicao'}">
  		<valuelist:column title="Posi��o">
  		<valuelist:attribute name="align" value="center"/>
		<valuelist:attribute name="width" value="60"/>
						<c:out value="${artigo.posicao}"/>
        </valuelist:column>
		</c:if>
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
    <table>
      <tr>
          <td width="100%" align="right" class="texto"><c:out value="${artigos.valueListInfo.totalNumberOfEntries}"/> 
            item(ns)</td>
        <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
      </tr>
    </table>
</valuelist:root>

</c:if>
    
   
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