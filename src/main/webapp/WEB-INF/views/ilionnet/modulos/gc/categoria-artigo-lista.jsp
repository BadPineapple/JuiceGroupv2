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
	            <li><i class="fa fa-users"></i> Categorias</li>
	         </ol>
	         <h2><i class="fa fa-users"></i> Categorias</h2>
	      </header>
	   	</div>
	   </div>
	   
	   <a href="categoria-artigo-form.sp" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> Nova</a>
	   
	   <div class="row">
		   <div class="col-sm-9">
		   
			   	<form class="form-inline" method="post" action="categoria-artigo-lista.sp">
		         
		         <div class="form-group">
		            <label class="sr-only" for="site">Site</label>
		            <select class="form-control" id="site" name="site">
		            	<option value="${site}">${site}</option>
		            </select>
		         </div>
	
		         <div class="form-group">
		            <label class="sr-only" for="grupo">Grupo</label>
		            <input type="text" class="form-control" id="grupo" name="palavraChave" placeholder="Grupo/Nome"/>
		         </div>
		         
		         <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Buscar</button>
		      </form>
		   
		   </div>
		   <div class="col-sm-3">
		   
			   	
		   
		   </div>
	</div>
	   
	   
	  <div class="box-content-table">
      	<table class="table table-bordered table-condensed table-hover">
      	   <thead>
      	      <tr>
      	         <th width="100px"> &nbsp; </th>
      	         <th class="text-center">ID</th>
      	         <th class="text-center">Grupo</th>
      	         <th class="text-center">Nome</th>
      	         <th class="text-center">Ordem (Artigos)</th>
      	         <th class="text-center">Possui sub-categorias</th>
      	         <th class="text-center">Status</th>
      	      </tr>
      	   </thead>
      	   <tbody>
      	      
      	      <c:forEach var="c" items="${categorias}">
      	      <tr>
      	         <td class="text-center"><a href="categoria-artigo-form.sp?id=${c.id}"><i class="glyphicon glyphicon-edit"></i> editar</a></td>
      	         <td class="text-center">${c.id}</td>
      	         <td class="text-center">${c.grupo}</td>
      	         <td class="text-center">${c.nome}</td>
      	         <td class="text-center">${c.artigoConfig.ordem}</td>
      	         <td class="text-center">${c.subCategoriaConfig.possuiSubCategorias}</td>
      	         <td class="text-center">${c.statusEnum}</td>
      	      </tr>
      	      </c:forEach>
      	      
      	   </tbody>
      	</table>
      </div>
         

	</div><!-- fim container -->
</div><!-- fim wrap -->


</body>
</html>