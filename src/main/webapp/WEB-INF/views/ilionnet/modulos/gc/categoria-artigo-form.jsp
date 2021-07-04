<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html lang="pt" dir="ltr">
<head>
<title>ILION</title>

<jsp:include page="../include-head-bootstrap.jsp" flush="true" />

</head>
<body>

<jsp:include page="../include-header-bootstrap.jsp" flush="true" />

<div class="wrap">
	<div class="container">

        
        <div class="row">
         <div class="col-sm-12">
	      <header class="titulo-pagina">
	         <ol class="breadcrumb">
	            <li><i class="fa fa-home"></i> ILIONnet</li>
	            <li><i class="fa fa-users"></i> Categorias Edição</li>
	         </ol>
	         <h2><i class="fa fa-users"></i> Categorias Edição</h2>
	      </header>
	   	</div>
	   </div>
		
		<form:form commandName="categoriaArtigo" method="post" action="categoria-artigo-form.sp">

         <a class="btn btn-default" href="categoria-artigo-lista.sp"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
         <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-save"></span> Salvar</button>
	   
	   <div class="row">
		   <div class="col-sm-12">
		   
		         
		         
		         <div class="form-group col-sm-4">
		            <label for="site">Site</label>
		            <form:select cssClass="form-control" id="site" path="site">
		            	<form:option value="${site}" label="${site}"/>
		            </form:select>
		         </div>
	
		         <div class="form-group col-sm-4">
		            <label for="grupo">Grupo</label>
		            <form:input cssClass="form-control" id="grupo" path="grupo" placeholder="Grupo"/>
		         </div>

		         <div class="form-group col-sm-4">
		            <label for="nome">Nome</label>
		            <form:input cssClass="form-control" id="nome" path="nome" placeholder="Nome"/>
		         </div>
				
			</div>
		</div>
		
		<div class="row">
		   <div class="col-sm-12">
				
				<h2>Artigos</h2>
				
		         <div class="form-group col-sm-4">
		            <label for="ordemArtigos">Ordem</label>
		            <form:input cssClass="form-control" id="ordemArtigos" path="artigoConfig.ordem"/>
		         </div>

		         <div class="form-group col-sm-4">
		            <label for="conteudos">Conteúdos</label>
		            <form:input cssClass="form-control" id="conteudos" path="artigoConfig.conteudos"/>
		         </div>

		         <div class="form-group col-sm-4">
		            <label for="secoes">Seções</label>
		            <form:input cssClass="form-control" id="secoes" path="artigoConfig.secoes"/>
		         </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiSitemap" /> Possui Sitemap
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiArtigoMenu" /> Possui ArtigoMenu 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiSubTitulo" /> Possui SubTitulo 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiTelefone" /> Possui Telefone 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiEmail" /> Possui Email
				    </label>
				  </div>
				  
				  <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiPreco" /> Possui Preço
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiLink" /> Possui Link 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiArquivos" /> Possui Arquivos 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiVideo" /> Possui Video 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiRSS" /> Possui RSS 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiPalavrasChave" /> Possui PalavrasChave 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiDescricao" /> Possui Descricao 
				    </label>
				  </div>

				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiDataEvento" /> Possui DataEvento 
				    </label>
				  </div>
				  
				  <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiCoordendas" /> Possui Coordenadas 
				    </label>
				  </div>
				  
				  <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiPrevisao" /> Possui Previsão 
				    </label>
				  </div>
				  
				  <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="artigoConfig.possuiCidade" /> Possui Cidade 
				    </label>
				  </div>
		         
			</div>
		</div>
		
		<div class="row">
		   <div class="col-sm-12">
				
				<h2>Sub-categorias</h2>
				
				<div class="form-group col-sm-4">
		            <label for="subCategoriaConfigOrdem">Ordem</label>
		            <form:input cssClass="form-control" id="subCategoriaConfigOrdem" path="subCategoriaConfig.ordem"/>
		         </div>
				
				<div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="subCategoriaConfig.possuiSubCategorias" /> Possui SubCategorias
				    </label>
				  </div>
				  
		         <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="subCategoriaConfig.possuiDescricao" /> Possui Descricao
				    </label>
				  </div>
		         <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="subCategoriaConfig.possuiTelefone" /> Possui Telefone
				    </label>
				  </div>
		         <div class="checkbox col-sm-4">
				    <label>
				      <form:checkbox path="subCategoriaConfig.possuiEmail" /> Possui Email
				    </label>
				  </div>
		         
		         
		   
		   </div>
		</div>
      
      <div class="row">
		   <div class="col-sm-12">
				
				<div class="form-group col-sm-4">
		            <label for="status">Status</label>
		            <form:select cssClass="form-control" id="status" path="statusEnum">
		            	<form:option value="PUBLICADO" label="PUBLICADO"/>
		            	<form:option value="EXCLUIDO" label="EXCLUIDO"/>
		            </form:select>
		         </div>
				
		   </div>
		</div>
      
         <a class="btn btn-default" href="categoria-artigo-lista.sp"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
         <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-save"></span> Salvar</button>
      
      </form:form>
	   
	</div><!-- fim container -->
</div><!-- fim wrap -->


</body>
</html>