<%@ include file="/ilionnet/taglibs.jsp"%>
<%@page import="ilion.gc.negocio.ArtigoSiteNegocio"%>
<%@page import="ilion.SpringApplicationContext"%>

<%@page import="ilion.gc.negocio.ArtigoConteudo"%>
<%@page import="ilion.gc.negocio.GCSiteNegocio"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>

<style type="text/css">
.espacoopcoesconteudo {margin: 0 8px 0 8px;}

.clearboth{clear:both;}
.floatleft{float:left;}

.conteudoContainerDiv {float:left;clear:both;width:963px;position:relative;}
.conteudoContainerDiv:hover {float:left;clear:both;padding:8px;background: #fffec9;width:963px;}

.conteudoDiv {clear: both;float:left;width: 100%;position: relative;}

.acoesDiv {position: absolute;top: 5px;right: 5px;}
.acoesDiv input {font-size: .9em;border: 1px solid #666;font-weight: normal;}

.inserirNovoDiv {clear:both;text-align:center;padding:10px 0;}
</style>

<script type="text/javascript" src="<c:url value='/ilionnet/design/script/jquery/jquery.js'/>"></script>

</head>

<body>
<script type="text/javascript">
function mostrarDivAcoes(id) {
	document.getElementById('texto'+id).style.border='1px solid black';
	document.getElementById('acoes'+id).style.display='';
	document.getElementById('codControleConteudo').value = id;
}
function esconderDivAcoes(id) {
	document.getElementById('texto'+id).style.border='0';
	document.getElementById('acoes'+id).style.display='none';
	document.getElementById('codControleConteudo').value = null;
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
<c:out value='${artigo.categoriaArtigo.grupo}'/> &gt;
<c:out value='${nomeCategoria}'/> &gt;
<c:if test="${not empty artigo.subCategoria}"><a href="gc-busca.sp?nomeCategoria=<c:out value='${nomeCategoria}'/>"><c:out value='${artigo.subCategoria.nome}'/></a> &gt;</c:if> 
Artigos &gt; 
Inclus&atilde;o / Altera&ccedil;&atilde;o </div>
</div>


<script type="text/javascript" src="<c:url value='/ilionnet/design/script/tiny_mce/tiny_mce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ilionnet/design/script/funcoesTinyMCE.js'/>"></script>

<script type="text/javascript" src="design/script/CalendarPopup.js"></script>
<script type="text/javascript" src="design/script/common.js"></script>
<script type="text/javascript">
document.write(getCalendarStyles());
var cal1x = new CalendarPopup("testdiv1");
//cal1x.setCssPrefix("TEST");
</script>
<div id="testdiv1" style="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;z-index:10000;"></div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Artigos &gt; Inclus&atilde;o / Altera&ccedil;&atilde;o</td>
</tr>
</table>

<form:form commandName="artigo" name="formArtigo" method="post" action="artigo-form.sp">
<input type="hidden" name="nomeCategoria" value="<c:out value='${artigo.categoriaArtigo.nome}'/>"/>
<input type="hidden" name="idSubCategoria" value="<c:out value='${artigo.subCategoria.id}'/>"/>
<input type="hidden" name="codControleConteudo" id="codControleConteudo" />

<div style="color: red;clear: both;"><form:errors path="*"/></div>

<div style="margin:5px 0 5px 0;">
<input type="submit" class="botao" name="acao" value=" Salvar " style="margin-right: 10px;"/>
<c:if test="${not empty artigo.id}">
<input type="button" name="button" value="Incluir Novo Artigo" onclick="location='artigo-form.sp?idCategoria=<c:out value='${artigo.categoriaArtigo.id}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>';" class="botao" style="margin-right:10px;"/>
</c:if>
<input type="button" class="botao" onclick="location='gc-busca.sp?site=${artigo.categoriaArtigo.site}&nomeCategoria=${artigo.categoriaArtigo.nome}&idSubCategoria=${artigo.subCategoria.id}';" value=" Sair "/>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Conte&uacute;do do artigo</td>
</tr>
 </table>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<%-- 
<c:if test="${artigo.categoriaArtigo.artigoConfig.tema}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Tema:</td>
  <td class="linkCinza"><form:input path="tema" cssClass="forms2" cssStyle="width:30%;"/> <span style="color: #666;">(Para materias-especiais)</span></td>
</tr>
</c:if>
--%>
<tr> 
  <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">T&iacute;tulo:</td>
  <td class="linkCinza"><form:input path="titulo" cssClass="forms2" cssStyle="width:90%;" maxlength="250"/></td>
</tr>

<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiCoordendas}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Latitude:</td>
  <td class="linkCinza"><form:input path="latitude" cssClass="forms2" cssStyle="width:30%;"/></td>
</tr>
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Longitude:</td>
  <td class="linkCinza"><form:input path="longitude" cssClass="forms2" cssStyle="width:30%;"/></td>
</tr>
</c:if>

<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiSubTitulo}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Sub-t&iacute;tulo:</td>
  <td class="linkCinza"><form:input path="subTitulo" cssClass="forms2" cssStyle="width:90%;" maxlength="250"/></td>
</tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiTelefone}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Telefone:</td>
  <td class="linkCinza"><form:input path="telefone" cssClass="forms2" cssStyle="width:30%;"/></td>
</tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiEmail}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">E-mail:</td>
  <td class="linkCinza"><form:input path="email" cssClass="forms2" cssStyle="width:30%;"/></td>
</tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiPreco}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Preço:</td>
  <td class="linkCinza"><form:input path="preco" cssClass="forms2" cssStyle="width:30%;"/></td>
</tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiPrevisao}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Previsão:</td>
  <td class="linkCinza"><form:input path="previsao" cssClass="forms2" cssStyle="width:30%;"/> horas</td>
</tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiCidade}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Cidade:</td>
  <td class="linkCinza"><form:input path="cidade" cssClass="forms2" cssStyle="width:30%;"/></td>
</tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiArtigoMenu}"> 
<tr> 
  <td align="right" valign="middle" class="linkCinzaEscuro">Tipo de Menu:</td>
  <td class="linkCinza"><form:select path="artigoMenuEnum" id="artigoMenuEnum" cssClass="forms2" onchange="exibirLink();">
  		<form:option value="CONTEUDO" label="Conte?do"/>
  		<form:option value="LINK" label="Link"/>
  </form:select></td>
</tr>
  <tr id="linkTR" style="display:none;"> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Link:</td>
    <td class="linkCinza"><form:input path="link" size="73" cssClass="forms2"/> 
      <form:radiobutton path="linkTarget" value="_self"/> Interno <form:radiobutton path="linkTarget" value="_blank"/> 
      Externo</td>
  </tr>
</c:if>
<c:if test="${not empty artigo.id && artigo.categoriaArtigo.artigoConfig.possuiSitemap}"> 
<tr> 
  <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">Url:</td>
  <td class="linkCinza"><ilion:link obj="${artigo}"/></td>
</tr>
</c:if>
</table>

<c:forEach var="layout" items="${artigo.categoriaArtigo.artigoConfig.conteudosLista}">

<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr> 
  <td class="tituloDestaque">Visualiza&ccedil;&atilde;o '<c:out value='${layout}'/>'</td>
</tr>
</table>

<%
ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
%>
<c:set var="novoConteudo" value="true"/>
<c:forEach var="artigoConteudo" items="${artigo.artigoConteudos}">
<a name="<c:out value='${artigoConteudo.codControle}'/>"></a>
<c:if test="${artigoConteudo.layout==layout}">

<c:if test="${empty artigo.artigoConteudo || artigo.artigoConteudo.codControle!=artigoConteudo.codControle}">
<div title="Posi&ccedil;&atilde;o: <c:out value='${artigoConteudo.posicao}'/>" id="texto<c:out value='${artigoConteudo.codControle}'/>" class="conteudoContainerDiv texto" onmouseout="esconderDivAcoes('<c:out value='${artigoConteudo.codControle}'/>');" onmouseover="mostrarDivAcoes('<c:out value='${artigoConteudo.codControle}'/>');">
<%

ArtigoConteudo artigoConteudo = (ArtigoConteudo) pageContext.findAttribute("artigoConteudo");

artigoSiteNegocio.artigoConteudoImprimir(artigoConteudo, out);
%>

<div class="acoesDiv" style="display:none;" id="acoes<c:out value='${artigoConteudo.codControle}'/>">
<input type="submit" class="botao" name="acao" value="Editar" title="Clique para editar"/>
<input type="submit" class="botao" name="acao" value="&uarr;" title="Mover para cima"/>
<input type="submit" class="botao" name="acao" value="&darr;" title="Mover para baixo"/>
<input type="submit" class="botao" name="acao" value="X" title="Clique para excluir" style="color:red;"/>
</div>

</div>
</c:if>
<c:if test="${not empty artigo.artigoConteudo && artigo.artigoConteudo.codControle==artigoConteudo.codControle}">
<c:set var="novoConteudo" value="false"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
  <td><form:textarea path="artigoConteudo.texto" id="artigoConteudo.texto" cssClass="forms2" cssStyle="width:100%;height:200px;"/></td>
</tr>
<tr>
<td class="linkCinzaEscuro">


<a href="javascript:;" onmousedown="editorAvancado('artigoConteudo.texto')" style="margin-right:10px;">Editor de Texto</a><span class="espacoopcoesconteudo"><a href="javascript:;" onmousedown="tinyMCE.get('artigoConteudo.texto').hide();" style="margin-right:10px;">Incorporar HTML</a>|</span>
  <a href="javascript:;"><img title="Arquivos" src="design/imagens/icon_popup.gif" border="0" onclick="window.open('imagem-form.sp?nomeClasse=ArtigoConteudo&idObjeto=<c:out value='${artigo.artigoConteudo.id}'/>&codigo=<c:out value='${artigo.artigoConteudo.codControle}'/>','arquivo','scrollbars=yes,resizable=yes,width=900,height=600,screenX=1,screenY=1,left=150,top=60');"></a> Edi&ccedil;&atilde;o de arquivos 
  </td>
 </tr>
</table>

</c:if>

</c:if>
</c:forEach>

<c:if test="${not empty artigo.artigoConteudo && artigo.artigoConteudo.layout==layout && novoConteudo}">
<a name="<c:out value='${artigoConteudo.codControle}'/>"></a>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
  <td><form:textarea path="artigoConteudo.texto" id="artigoConteudo.texto" cssClass="forms2" cssStyle="width:100%;height:200px;"/></td>
</tr>
<tr>
<td class="linkCinzaEscuro">
<a href="javascript:;" onmousedown="editorAvancado('artigoConteudo.texto')" style="margin-right:10px;">Editor de Texto</a><span class="espacoopcoesconteudo"><a href="javascript:;" onmousedown="tinyMCE.get('artigoConteudo.texto').hide();" style="margin-right:10px;">Incorporar HTML</a>
  <span class="espacoopcoesconteudo">|</span>
  <a href="javascript:;"><img title="Arquivos" src="design/imagens/icon_popup.gif" border="0" onclick="window.open('imagem-form.sp?nomeClasse=ArtigoConteudo&idObjeto=<c:out value='${artigo.artigoConteudo.id}'/>&codigo=<c:out value='${artigo.artigoConteudo.codControle}'/>','arquivo','scrollbars=yes,resizable=yes,width=900,height=600,screenX=1,screenY=1,left=150,top=60');"></a> Edi&ccedil;&atilde;o de arquivos 
  </td>
 </tr>
</table>

</c:if>

<div class="inserirNovoDiv"><input type="submit" class="botao" name="acao" value="Clique aqui para acrescentar um '<c:out value='${layout}'/>'"/></div>


</c:forEach>


  <table width="100%" border="0" cellspacing="1" cellpadding="0">
    <tr> 
      <td class="tituloDestaque">Configura&ccedil;&atilde;o de conte&uacute;do</td>
    </tr>
  </table>
  
<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <c:if test="${artigo.categoriaArtigo.artigoConfig.possuiLink}"> 
  <tr> 
    <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">Link:</td>
    <td colspan="3" class="linkCinza"><form:input path="link" size="73" cssClass="forms2"/> 
      <form:radiobutton path="linkTarget" value="_self"/> Interno <form:radiobutton path="linkTarget" value="_blank"/> 
      Externo</td>
  </tr>
  </c:if> 
  <c:if test="${artigo.categoriaArtigo.artigoConfig.possuiVideo}"> 
  <tr align="left">
    <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">V&iacute;deo (Tag):</td>
    <td colspan="3" valign="middle" class="linkCinza">
<form:input path="youtube" id="youtube" size="70" cssClass="forms2"/></td>
    </tr>
  </c:if>
  <c:if test="${artigo.categoriaArtigo.artigoConfig.possuiArquivos}"> 
  <tr align="left">
    <td width="14%" align="right" valign="middle" class="linkCinzaEscuro"><a href="javascript:;"><img title="Arquivos" src="design/imagens/icon_popup.gif" width="17" height="11" border="0" onclick="window.open('imagem-form.sp?nomeClasse=Artigo&idObjeto=<c:out value='${artigo.id}'/>&codigo=<c:out value='${artigo.codControle}'/>','arquivo','scrollbars=yes,resizable=yes,width=900,height=600,screenX=1,screenY=1,left=150,top=60');"/></a></td>
    <td colspan="3" valign="middle" class="linkCinza">Edi&ccedil;&atilde;o de arquivos</td>
  </tr>
  </c:if> 
</table>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">
    <tr> 
      <td class="tituloDestaque">Configura&ccedil;&atilde;o de publica&ccedil;&atilde;o</td>
    </tr>
  </table>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<%-- 
<c:if test="${artigo.categoriaArtigo.nome=='noticias'}">
<c:if test="${not empty artigo.categoriaArtigo.subCategoriaConfig.possuiSubCategorias && 
				artigo.categoriaArtigo.nome=='noticias'}">
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Sub-categoria:</td>
   <td class="linkCinza">
   	<form:select cssClass="forms2" path="subCategoria.id">
   		<form:option value="" label="- Sub-categoria -"/>
   		<ilion:subCategoriaTodasLista idCategoria="${artigo.categoriaArtigo.id}" varRetorno="subCategorias"/>
   		
   		<c:forEach var="s" items="${subCategorias}">
   			<form:option value="${s.id}" label="${s.nome}"/>
   		</c:forEach>
   		
   	</form:select>
   </td>
 </tr>
</c:if>
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Destaque:</td>
   <td class="linkCinza">
   		<form:radiobutton path="destaque" id="destaqueFalse" value="false" cssStyle="position:relative;top:2px;"/> 
   			<label for="destaqueFalse">N?o</label>
   		<form:radiobutton path="destaque" id="destaqueTrue" value="true" cssStyle="position:relative;top:2px;"/> 
   			<label for="destaqueTrue">Sim</label>
   	</td>
 </tr>
 
</c:if>
--%>
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Facebook:</td>
   <td class="linkCinza">
   	<%-- <button type="button" id="btnFacebook" onclick="shareFace('${artigo.id}')">Compartilhar no Facebook</button> --%>
    <!-- <a href="http://www.facebook.com/sharer.php?u=https://simplesharebuttons.com" target="_blank"> -->
   	<!-- Facebook -->
    <a href="http://www.facebook.com/sharer.php?u=<ilion:url/>artigo-compartilhar/${artigo.id}" target="_blank">
        <img src="images/icone-Facebook.png" alt="Facebook" />
    </a>
   </td>
 </tr>
<c:if test="${not empty artigo.categoriaArtigo.artigoConfig.secoesLista}">
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Se&ccedil;&otilde;es:</td>
   <td class="linkCinza"><c:forEach items="${artigo.categoriaArtigo.artigoConfig.secoesLista}" var="s">
		  <div style="float: left;"><form:checkbox path="secoesList" value="${s}"/> <c:out value="${s}"/></div>
 		  </c:forEach></td>
 </tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiPalavrasChave}">
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Palavras-chave:</td>
   <td class="linkCinza"><form:input path="palavrasChave" cssClass="forms2" cssStyle="width:500px;" maxlength="512"/></td>
 </tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiDescricao}">
 <tr>
   <td align="right" valign="middle" class="linkCinzaEscuro">Descri&ccedil;&atilde;o:</td>
   <td class="linkCinza"><form:input path="descricao" cssClass="forms2" cssStyle="width:500px;" maxlength="512"/></td>
 </tr>
</c:if>
<c:if test="${artigo.categoriaArtigo.artigoConfig.possuiDataEvento}">
 <tr> 
 <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">Data evento:</td>
   <td width="*" class="linkCinza">
   		<form:input path="dataEvento" id="dataEvento" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10"/>
        <a href="javascript:;" onclick="cal1x.select(document.getElementById('dataEvento'),'linkDataEvento','dd/MM/yyyy'); return false;" id="linkDataEvento" name="linkDataEvento">
        	<img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/>
       	</a>
      </td>
 </tr>
</c:if>
 <tr>
 <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">Data publicação: </td>
   <td width="*" class="linkCinza"><form:input path="dataPublicacao" id="dataPublicacao" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10"/>
               <a href="javascript:;" 
onClick="cal1x.select(document.getElementById('dataPublicacao'),'linkDataPublicacao','dd/MM/yyyy'); return false;" id="linkDataPublicacao" name="linkDataPublicacao"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a>
      <form:select cssClass="forms2" path="horaPublicacaoInt">
      	<c:forEach var="h" begin="0" end="23">
      		<form:option value="${h}" label="${h}"/>
      	</c:forEach>
      </form:select>
      </td>
 </tr>
 <tr> 
   
 <td width="14%" align="right" valign="middle" class="linkCinzaEscuro">Data 
   expira&ccedil;&atilde;o: </td>
   <td class="linkCinza"><form:input path="dataExpiracao" id="dataExpiracao" cssClass="forms2" cssStyle="text-align:center;width:78px;" maxlength="10"/>
               <a href="javascript:;" 
onClick="cal1x.select(document.getElementById('dataExpiracao'),'linkDataExpiracao','dd/MM/yyyy'); return false;" id="linkDataExpiracao" name="linkDataExpiracao"><img src="design/imagens/icon_agenda.gif" width="14" height="15" border="0" style="position:relative;top:3px;"/></a></td>
 </tr>
 <tr> 
   
 <td align="right" valign="middle" class="linkCinzaEscuro">Status:</td>
   <td class="linkCinza">
<ilion:permissao tipo="artigo-publicar.sp">
<form:radiobutton path="status" id="statusPublicado" value="Publicado" cssStyle="position:relative;top:2px;"/> <label for="statusPublicado">Publicado</label> 
<form:radiobutton path="status" id="statusNaoPublicado" value="Não Publicado" cssStyle="position:relative;top:2px;"/> <label for="statusNaoPublicado">Não Publicado </label>
</ilion:permissao>
<form:radiobutton path="status" id="statusEmAprovacao" value="Em Aprovação" cssStyle="position:relative;top:2px;"/> <label for="statusEmAprovacao">Em Aprovação</label></td>
 </tr>
 <tr>
   
 <td align="right" valign="middle" class="linkCinzaEscuro">Posi&ccedil;&atilde;o:</td>
   <td class="linkCinza"><form:input path="posicao" id="posicaoArtigo" cssClass="forms2" size="5" cssStyle="text-align:center;"/></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<div style="margin:5px 0 5px 0;">
<input type="submit" class="botao" name="acao" value=" Salvar " style="margin-right: 10px;"/>
<c:if test="${not empty artigo.id}">
<input type="button" name="button" value="Incluir Novo Artigo" onclick="location='artigo-form.sp?idCategoria=<c:out value='${artigo.categoriaArtigo.id}'/>&idSubCategoria=<c:out value='${artigo.subCategoria.id}'/>';" class="botao" style="margin-right:10px;"/>
</c:if>
<input type="button" class="botao" onclick="location='gc-busca.sp?site=${artigo.categoriaArtigo.site}&nomeCategoria=${artigo.categoriaArtigo.nome}&idSubCategoria=${artigo.subCategoria.id}';" value=" Sair "/>
</div>
</form:form>

    
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

function exibirLink() {
	var valor = $('select[name="artigoMenuEnum"]').val();
	console.log(valor);
	if( valor == 'LINK' ) {
		$('#linkTR').show();
	} else {
		$('#linkTR').hide();
	}
}

$(function(){
	
	exibirLink();
	
})


window.onload = function() {

<c:if test="${not empty artigo.artigoConteudo}">
	editorAvancado('artigoConteudo.texto', 0);

	setTimeout(function() {
		location = location+"#<c:out value='${artigo.artigoConteudo.codControle}'/>";
	}, 200);
	
</c:if>
}
</script>

<script type="text/javascript">
var m = "<%=request.getParameter("m")%>";
if(m=='ok') {
	alert("Artigo gravado com sucesso!");
}
</script>

</body>
</html>