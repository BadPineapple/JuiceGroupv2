<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html dir="ltr" xml:lang="pt-br" lang="pt-br" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Editar detalhes</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="geo.region" content="BR" />
<meta name="author" content="www.ilion.com.br" />
<meta name="language" content="pt-br" />

<link href="<ilion:url/>ilionnet/design/css/css_arqs.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- jquery -->
<script type="text/javascript" src="<ilion:url/>ilionnet/design/script/jquery/jquery.js"></script>
<script type="text/javascript" src="<ilion:url/>ilionnet/design/script/utils.js"></script>
<!-- jquery -->
</head>
<body onload="ocultarMensagemRetorno();">

<form:form commandName="arquivo" method="post" action="arquivo-detalhes-form.sp">
<form:errors path="*" cssStyle="color:red;"/>

<form:hidden path="id"/>

<c:if test="${param.m=='ok'}">
<div class="mensagemRetorno" id="mensagemRetorno">Alterações aplicadas com sucesso!</div>
</c:if>

<table class="tabelaDetalhes" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="secaoLabel">Legenda</td>
		<td class="secaoInput"><form:input cssClass="input-text" path="texto" maxlength="255" /></td>
		<td class="secaoLabel">T&iacute;tulo</td>
		<td class="secaoInput"><form:input cssClass="input-text" path="title" maxlength="30" /></td>
	</tr>
	<tr>
		<td>Cr&eacute;ditos</td>
		<td><form:input cssClass="input-text" path="creditos" maxlength="30" /></td>
		<td>Data</td>
		<td><form:input cssClass="input-text" path="data" maxlength="30" /></td>
	</tr>
	<tr>
		<td>Link</td>
		<td><form:input cssClass="input-text" path="link" maxlength="255" /></td>
		<td>Abrir link</td>
		<td><form:select path="tipoLink">
			<form:option value="_self" label="Mesma janela"/>
			<form:option value="_blank" label="Nova janela"/>
		</form:select></td>
	</tr>
	<tr>
		<td>URL</td>
		<td colspan="3"><input class="input-text" type="text" onclick="select();" value="<c:out value='${arquivo.url}'/>" style="width:100%;" /></td>
	</tr>
	<tr>
		<td colspan="2"><form:checkbox id="naoPublicado" path="naoPublicado" value="true" /> <label for="naoPublicado">N&atilde;o publicado</label></td>
		<td>Alinhamento</td>
		<td><form:select path="layout">
			<form:option value="centro" label="Centro"/>
			<form:option value="lateral" label="Lateral (direita)"/>
			<form:option value="lateral_esquerda" label="Lateral (esquerda)"/>
			<form:option value="inferior" label="Inferior"/>
			<form:option value="destaque" label="Destaque"/>
			<form:option value="galeria" label="Galeria"/>
			<form:option value="mobile" label="Mobile"/>
		</form:select></td>
	</tr>
	<tr>
		<td colspan="4"><button type="submit">Aplicar</button></td>
	</tr>
</table>

</form:form>

</body>
</html>