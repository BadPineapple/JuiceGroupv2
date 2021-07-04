<%@ include file="/ilionnet/taglibs.jsp"%>

<div id="contentArquivos">
	<h1>Adicione arquivos para download (documentos, planilhas, pdf, m&uacute;sicas, etc)</h1>
	<p>Exemplo de extens&otilde;es permitidas: <em>.doc</em>, <em>.docx</em>, <em>.xls</em>, <em>.pdf</em>, <em>.mp3</em>, etc. Tamanho m&aacute;ximo para cada arquivo: 5MB. Evite subir arquivos muito pesados, pois podem demorar muito tempo ou mesmo sobrecarregar o servidor.</p>

<form:form commandName="arquivo" name="formArquivo" method="post" enctype="multipart/form-data" action="download-form.sp">
<form:errors path="*" cssStyle="color:red;"/>

	<table cellpadding="0" cellspacing="0" class="table-content-management">
		<tbody>
			<tr>
				<td width="330">Selecione: <input type="file" name="arquivos" multiple="multiple"/></td>
				<td><button type="submit" name="salvar" id="salvar">Salvar</button></td>
			</tr>
		</tbody>
	</table>

</form:form>

<c:set var="retorno" value="download-form.sp" scope="request"/>
<jsp:include page="include-lista-de-arquivos.jsp" flush="true"/>

</div>