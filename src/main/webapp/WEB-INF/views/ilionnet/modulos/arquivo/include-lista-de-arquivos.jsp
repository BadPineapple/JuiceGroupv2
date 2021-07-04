<%@ include file="/ilionnet/taglibs.jsp"%>

<ilion:arquivoLista nomeClasse="${arquivo.nomeClasse}" idObjeto="${arquivo.idObjeto}" codigo="${arquivo.codigo}" varRetorno="arquivos"/>

<c:if test="${empty arquivos}">
<p>Nenhum arquivo incluído.</p>
</c:if>

<c:if test="${not empty arquivos}">

<form method="post" action="arquivo-acao.sp">
<input type="hidden" name="nomeClasse" value="<c:out value='${arquivo.nomeClasse}'/>"/>
<input type="hidden" name="idObjeto" value="<c:out value='${arquivo.idObjeto}'/>"/>
<input type="hidden" name="codigo" value="<c:out value='${arquivo.codigo}'/>"/>
<input type="hidden" name="retorno" value="<c:out value='${retorno}'/>"/>

	<table cellpadding="0" cellspacing="0" border="0" class="listaArquivos">
 	  <thead>
		<tr>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th>posi&ccedil;&atilde;o</th>
			<th class="colDetalhes"><label for="todos">selecionar todos</label></th>
			<th class="colCheckBox"><input id="todos" type="checkbox" onclick="selecionarTodos();" /></th>
		</tr>
	  </thead>
 	  <tbody>
<c:forEach var="arquivo" items="${arquivos}">
<input type="hidden" name="ids" value="<c:out value='${arquivo.id}'/>"/>
<input type="hidden" name="posicoesAnteriores" value="<c:out value='${arquivo.posicao}'/>"/>
		<tr>
			<td class="colThumb"><ilion:arquivoThumb arquivo="${arquivo}"/></td>
			<td class="colDesc"><c:out value='${arquivo.arquivo1}'/></td>
			<td><input type="text" name="posicoesNovas" class="campoPosicao" value="<c:out value='${arquivo.posicao}'/>"/></td>
			<td class="colDetalhes"><a href="javascript:abrirDetalhes('arquivo<c:out value='${arquivo.id}'/>');">Detalhes</a></td>
			<td class="colCheckBox"><input type="checkbox" name="idsArquivo" value="<c:out value='${arquivo.id}'/>" /></td>
		</tr>
		<tr class="linhaDetalhes" id="arquivo<c:out value='${arquivo.id}'/>" style="display:none;">
			<td colspan="6">
				<iframe src="arquivo-detalhes-form.sp?id=<c:out value='${arquivo.id}'/>" class="frameDetalhes"></iframe>
			</td>
		</tr>
</c:forEach>
	  </tbody>
	  <tfoot>
	  	<tr>
	  		<td colspan="5">
	  			<div class="floatright">
	  				Mais a&ccedil;&otilde;es:
	  				<select name="acao">
	  					<option>-</option>
	  					<optgroup label="A&ccedil;&otilde;es:">
	  					<option value="excluir">Excluir</option>
	  					<option value="naoPublicado">N&atilde;o publicado</option>
	  					</optgroup>
	  					
	  					<optgroup label="Alinhamento:">
	  					<option value="layout-centro">Centro</option>
	  					<option value="layout-lateral">Lateral (direita)</option>
	  					<option value="layout-lateral_esquerda">Lateral (esquerda)</option>
	  					<option value="layout-inferior">Inferior</option>
	  					<option value="layout-destaque">Destaque</option>
	  					<option value="layout-galeria">Galeria</option>
	  					<option value="layout-mobile">Mobile</option>
	  					
	  					</optgroup>
	  					
	  				</select>
	  				<button type="submit">Aplicar altera&ccedil;&otilde;es</button>
	  			</div>
	  		</td>
	  	</tr>
	  </tfoot>
	</table>

</form>

</c:if>