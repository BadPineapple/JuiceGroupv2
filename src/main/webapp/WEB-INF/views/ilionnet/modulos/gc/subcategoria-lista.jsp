<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
<script type="text/javascript">
function excluirSubCategoria(id) {
	if( confirm('Deseja realmente excluir esta sub-categoria?') ) {
		location='subcategoria-excluir.sp?id='+id;
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
 Sub-Categorias</div>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Sub-Categorias</td>
</tr>
</table>

    <div style="clear: both;padding: 10px 0 10px 0;">
    <input type="button" name="button" value="Incluir Nova Sub-Categoria" onclick="location='subcategoria-form.sp?idCategoria=<c:out value='${categoriaArtigo.id}'/>';" class="botao"/>
    </div>
    
    <c:if test="${empty subCategorias}">N&atilde;o h&aacute; sub-categorias cadastrados na categoria <c:out value='${nomeCategoria}'/>.</c:if>

<c:if test="${not empty subCategorias}">
      <table class="vlhTabela1" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th width="60">&nbsp;</th>
          <th width="60">&nbsp;</th> 
          <th width="*">Nome</th>
          <th width="60">Posi&ccedil;&atilde;o</th>
          <th width="90">Status</th>
          <th width="60">&nbsp;</th>
        </tr>
		<c:forEach var="subCategoria" items="${subCategorias}"> 
        <tr>
          <td align="center">
          	<a href="artigo-busca.sp?idCategoria=${subCategoria.categoriaartigo_id}&idSubCategoria=<c:out value='${subCategoria.id}'/>">
          	${subCategoria.qtd} artigo(s)
          	</a>
          </td>
          <td align="center"><a href="subcategoria-form.sp?id=<c:out value='${subCategoria.id}'/>">Editar</a></td> 
          <td><c:out value="${subCategoria.nome}"/></td>
          <td align="center"><c:out value="${subCategoria.posicao}"/></td>
          <td align="center"><c:out value="${subCategoria.status}"/></td>
          <td align="center"><a style="color: red;" href="subcategoria-excluir.sp?id=<c:out value='${subCategoria.id}'/>">Excluir</a></td>
        </tr>
        </c:forEach> 
       </table>
</c:if>
    
    <div style="clear: both;padding: 10px 0 10px 0;">
    	<input type="button" name="button" value="Incluir Nova Sub-Categoria" onclick="location='subcategoria-form.sp?idCategoria=<c:out value='${categoriaArtigo.id}'/>';" class="botao"/>
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
var m = "<%=request.getParameter("m")%>";
if(m=='categoria-nao-encontrada') {
	alert("Categoria não encontrada!");
} else if(m=='subcategoria-possui-artigos') {
	alert("Sub-Categoria não pode ser excluída, possui artigos!");
}
</script>
</body>
</html>