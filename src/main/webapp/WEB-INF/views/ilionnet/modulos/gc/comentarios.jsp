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
function excluirComentario(id,idArtigo) {
	if( confirm('Deseja realmente excluir este comentário?') ) {
		location='comentario-excluir.sp?id='+id+'&idArtigo='+idArtigo;
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

Artigos &gt; Coment&aacute;rios</div>
</div>
<div style="float: right;">
<select class="forms2" onchange="location='gc.sp?idioma='+this.value;">
		<option value="br" <c:if test="${idiomaSelecionado == 'br'}">selected</c:if>>Português Brasil</option>
		<option value="en" <c:if test="${idiomaSelecionado == 'en'}">selected</c:if>>English</option>
        </select>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Coment&aacute;rios</td>
</tr>
</table>

<div style="clear:both;margin: 8px 0;">
<input type="button" class="botao" onclick="location='gc-busca.sp?nomeCategoria=<c:out value='${artigo.categoriaArtigo.nome}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>';" value=" &lt;&lt; Voltar "/>
</div>

<c:if test="${comentarios.valueListInfo.totalNumberOfEntries==0}">N&atilde;o h&aacute; coment&aacute;rios neste artigo.</c:if>
<c:if test="${comentarios.valueListInfo.totalNumberOfEntries!=0}">

<valuelist:root value="comentarios" url="?setarParametros=true&" includeParameters="idArtigo|#">
    <table>
      <tr>
          <td width="100%" align="right" class="texto"><c:out value="${comentarios.valueListInfo.totalNumberOfEntries}"/> 
            item(ns)</td>
        <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
      </tr>
    </table>
<c:forEach var="c" items="${comentarios}">
	      <table width="100%" border="0" cellspacing="1" cellpadding="2">
                    <tr> 
                      <td width="57%" class="linkCinzaEscuro">Comentado por: <b><c:out value="${c.nome}"/></b> em <fmt:formatDate value='${c.data}' pattern='dd/MM/yyyy - HH:mm'/></td>
                      <td width="*" class="linkCinzaEscuro">
                      <c:if test="${c.status=='Publicado'}">
                      <span style="color:green;font-weight:bold;">Publicado</span> (<a href="comentario-status.sp?id=<c:out value='${c.id}'/>&status=NaoPublicado">Despublicar</a>)
                      </c:if>
                      <c:if test="${c.status=='NaoPublicado'}">
                      <span style="color:#a80000;font-weight:bold;">N&atilde;o Publicado</span> (<a href="comentario-status.sp?id=<c:out value='${c.id}'/>&status=Publicado">Publicar</a>)
                      </c:if>
                      <c:if test="${c.status=='EmAprovacao'}">
                      <span style="color:#e1bc0f;font-weight:bold;">Em Aprovação</span> (<a href="comentario-status.sp?id=<c:out value='${c.id}'/>&status=Publicado">Publicar</a> <a href="comentario-status.sp?id=<c:out value='${c.id}'/>&status=NaoPublicado">Despublicar</a>)
                      </c:if>
                      </td>
				      <td width="5%" align="center" class="linkCinzaEscuro"><a href="javascript:excluirComentario('<c:out value='${c.id}'/>','<c:out value='${artigo.id}'/>');"><img src="design/imagens/exclui_s.gif" width="16" height="13" border="0"/></a></td>
                     </tr>
                  </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="3" style="margin-bottom: 5px;">
        <tr> 
          <td class="linkCinza"><c:out value="${c.comentario}" escapeXml="{true}"/></td>
        </tr>
      </table>
</c:forEach>
    <table>
      <tr>
          <td width="100%" align="right" class="texto"><c:out value="${comentarios.valueListInfo.totalNumberOfEntries}"/> 
            item(ns)</td>
        <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
      </tr>
    </table>
</valuelist:root>

</c:if></td>
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
}
</script>
</body>
</html>