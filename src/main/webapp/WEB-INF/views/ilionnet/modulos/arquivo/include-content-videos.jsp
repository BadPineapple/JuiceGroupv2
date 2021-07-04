<%@ include file="/ilionnet/taglibs.jsp"%>

<div id="contentArquivos">
	<h1>Adicione seus v&iacute;deos ao site</h1>
	<p>Extens&atilde;o permitida: <em>.mp4</em>, <em>.ogv</em>, <em>.webm</em>.</p>
	<p>Evite subir arquivos muito pesados, pois podem demorar muito tempo ou mesmo sobrecarregar o servidor.</p>

<form:form commandName="video" name="formArquivo" method="post" enctype="multipart/form-data" action="video-form.sp">
<form:errors path="*" cssStyle="color:red;"/>

	<table cellpadding="0" cellspacing="0" class="table-content-management">
		<tbody>
			<tr>
				<td width="330">Selecione: <input type="file" name="arquivos" multiple="multiple"/></td>
				<td width="180"><div class="boxLargura"><label for="largura">Largura</label> <form:input id="largura" path="largura" /> <span>px</span></div></td>
				<td width="180"><div class="boxAltura"><label for="altura">Altura</label><form:input id="altura" path="altura" /> <span>px</span></div></td>
				<td><button type="submit" name="salvar" id="salvar">Salvar</button></td>
			</tr>
		</tbody>
	</table>

</form:form>

<c:set var="retorno" value="video-form.sp" scope="request"/>
<jsp:include page="include-lista-de-arquivos.jsp" flush="true"/>
	
</div>