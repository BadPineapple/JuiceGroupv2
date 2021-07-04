<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true" />
</head>

<body>

<table id="container" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true" />
		<table width="100%" border='0' align='center' cellpadding='0'
			cellspacing='0'>
			<tr>
				<td class="bodyPrin">

				<div style="float: left;">
				<div class="hierarquia">
					Web Site &gt; ${siteSelecionado} 
				</div> 
				
				</div>
				<%-- 
				<div style="float: right;">
					<select 
						class="forms2"
						onchange="location='gc.sp?site='+this.value;">
							<option value="oab" <c:if test="${siteSelecionado == 'oab'}">selected</c:if>>OAB</option>
							<option value="esa" <c:if test="${siteSelecionado == 'esa'}">selected</c:if>>ESA</option>
							<option value="cel" <c:if test="${siteSelecionado == 'cel'}">selected</c:if>>CEL</option>
            				<option value="eleicoeslimpas" <c:if test="${siteSelecionado == 'eleicoeslimpas'}">selected</c:if>>Elei&ccedil;&otilde;es Limpas</option>
            				<option value="advogadodigital" <c:if test="${siteSelecionado == 'advogadodigital'}">selected</c:if>>Advogado Digital</option>
            				<option value="portaldatransparencia" <c:if test="${siteSelecionado == 'portaldatransparencia'}">selected</c:if>>Portal da Transpar&ecirc;ncia</option>
				</select></div>
				--%>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tituloDestaque">Home</td>
					</tr>
				</table>

			<form method="post" action="gc-busca-geral.sp" style="margin: 10px 0 10px 0;">
				<input type="hidden" name="setarParametros" value="true" /> 
				<input type="hidden" name="pagingPage" value="1" /> 
				Palavra chave: <input type="text" name="palavraChave" class="forms2" /> 
				<input type="submit" name="submit" value="Buscar" class="botao" />
			</form>

<div style="height:100px;clear: both;">

<c:forEach var="g" items="${grupos}" varStatus="varStatus">
<table align="left" cellpadding="0" cellspacing="0">
	<tr>
		<td width="150" valign="top" align="center">
				<span style="font-size: 1.5em;font-weight: bold;"><c:out value='${g.nome}'/></span><br/><br/>
	
				<c:forEach var="c" items="${g.categorias}">
					<a href="gc-busca.sp?site=${c.site}&nomeCategoria=${c.nome}" style="font-size: 11px;font-weight: none;"><c:out value='${c.nome}'/></a><br/>
				</c:forEach>
		</td>
	</tr>
</table>

<c:if test="${(varStatus.index+1)%6==0}"><c:out value='</div><div style="height:100px;clear: both;">' escapeXml='{true}'/></c:if>

</c:forEach>

</div>

				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="">


		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tituloDestaque" align="left">Artigos em Aprova&ccedil;&atilde;o</td>
			</tr>
		</table>

<c:if test="${empty artigosEmAprovacao}">
<div style="margin: 10px;">Nenhum artigo em aprova&ccedil;&atilde;o.</div>
</c:if>

<c:if test="${not empty artigosEmAprovacao}">
<div style="height:80px;overflow: auto;">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="vlhTabela1">
<tbody>
<c:forEach var="artigo" items="${artigosEmAprovacao}">
<tr>
	<td align="center" width="60">&nbsp;<a href="artigo-form.sp?nomeCategoria=<c:out value='${artigo.categoriaArtigo.nome}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>&id=<c:out value='${artigo.id}'/>">Editar</a>&nbsp;</td>
	<td align="left" width="*">&nbsp;<span id="tituloSpan6"><c:out value='${artigo.titulo}'/></span> &nbsp;</td>
	<td align="center" width="100">&nbsp;<fmt:formatDate value="${artigo.dataPublicacao}" pattern="dd/MM/yyyy HH:mm"/>&nbsp;</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</c:if>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
	<tr>
		<td class="tituloDestaque" align="left">Coment&aacute;rios em Aprova&ccedil;&atilde;o</td>
	</tr>
</table>

<c:if test="${empty comentariosEmAprovacao}">
<div style="margin: 10px;">Nenhum coment&aacute;rio em aprova&ccedil;&atilde;o.</div>
</c:if>

<c:if test="${not empty comentariosEmAprovacao}">
<div style="height:80px;overflow: auto;">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="vlhTabela1">
<c:forEach var="comentario" items="${comentariosEmAprovacao}">
<tr>
	<td align="center" width="150">&nbsp;<a href="comentarios.sp?idArtigo=<c:out value='${comentario.artigo.id}'/>">Ver coment&aacute;rios</a>&nbsp;</td>
	<td align="left" width="*">&nbsp;<span id="tituloSpan6"><c:out value='${comentario.nome}'/></span>&nbsp;</td>
	<td align="left" width="*">&nbsp;<span id="tituloSpan6"><c:out value='${comentario.email}'/></span>&nbsp;</td>
	<td align="center" width="100">&nbsp;<fmt:formatDate value="${comentario.data}" pattern="dd/MM/yyyy HH:mm"/>&nbsp;</td>
</tr>
</c:forEach>
</table>
</div>
</c:if>
		
		
		</td>
	</tr>
	<tr>
		<td class="rodape">Ilion - <a href="http://www.ilion.com.br/"
			target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
	</tr>
</table>

<script type="text/javascript">
var m = "<%=request.getParameter("m")%>";
if(m=='categoria-nao-encontrada') {
	alert("Categoria n�o encontrada!");
}
</script>
</body>
</html>