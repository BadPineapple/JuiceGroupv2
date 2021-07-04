<%@ include file="/ilionnet/taglibs.jsp"%>
<ul class="abasArquivos">
	<li class="abaImagens"><a href="imagem-form.sp?nomeClasse=<c:out value='${arquivo.nomeClasse}'/>&idObjeto=<c:out value='${arquivo.idObjeto}'/>&codigo=<c:out value='${arquivo.codigo}'/>">Imagens (jpg, gif e png)</a></li>
	<li class="abaDownloads"><a href="download-form.sp?nomeClasse=<c:out value='${arquivo.nomeClasse}'/>&idObjeto=<c:out value='${arquivo.idObjeto}'/>&codigo=<c:out value='${arquivo.codigo}'/>">Arquivos para download</a></li>
	<li class="abaVideos"><a href="video-form.sp?nomeClasse=<c:out value='${arquivo.nomeClasse}'/>&idObjeto=<c:out value='${arquivo.idObjeto}'/>&codigo=<c:out value='${arquivo.codigo}'/>">V&iacute;deos (mp4, ogv, webm)</a></li>
</ul>