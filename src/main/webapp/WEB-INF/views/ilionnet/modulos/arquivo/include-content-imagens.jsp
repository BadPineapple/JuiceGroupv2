<%@ include file="/ilionnet/taglibs.jsp"%>

<div id="contentArquivos">
	<h1>Adicione imagens de seu computador</h1>
	<p>Extens&otilde;es de arquivo permitidas: <em>jpg</em>, <em>jpeg</em>, <em>png</em>, <em>gif</em>. Tamanho m&aacute;ximo para cada arquivo: 1MB. Evite subir arquivos muito pesados, pois podem demorar muito tempo ou mesmo sobrecarregar o servidor.</p>
	
<form:form commandName="arquivo" name="formArquivo" method="post" enctype="multipart/form-data" action="imagem-form.sp">
<form:errors path="*" cssStyle="color:red;"/>
	
	<table cellpadding="0" cellspacing="0" class="table-content-management">
		<tbody>
			<tr>
				<td width="330">Selecione: <input type="file" name="arquivos" multiple="multiple"/></td>
				<td width="180"><div class="boxRedimensionar"><form:checkbox id="redimensionar" path="redimensionar" onclick="mostraEsconde('boxResizeTrue');SetFocus('larguraResize');" /></div> <label for="redimensionar">Redimensionar</label></td>
				<td width="215"><div class="boxAmpliar"><form:checkbox id="imagemAmpliada" path="imagemAmpliada" onclick="mostraEsconde('boxAmpliarTrue');SetFocus('larguraResizeAmpliar');" /></div> <label for="imagemAmpliada">Foto com amplia&ccedil;&atilde;o</label></td>
				<td><button type="submit" name="salvar" id="salvar">Salvar</button></td>
			</tr>
		</tbody>
	</table>
	
	<div class="line2">
		<div id="boxResizeTrue" class="hidden"><form:input id="larguraResize" path="larguraPequena" /> <span>px de largura</span></div>
		<div id="boxAmpliarTrue" class="hidden"><form:input id="larguraResizeAmpliar" path="larguraGrande" /> <span>px de largura</span></div>
	</div><!-- fim line -->

</form:form>

<c:set var="retorno" value="imagem-form.sp" scope="request"/>
<jsp:include page="include-lista-de-arquivos.jsp" flush="true"/>

</div>