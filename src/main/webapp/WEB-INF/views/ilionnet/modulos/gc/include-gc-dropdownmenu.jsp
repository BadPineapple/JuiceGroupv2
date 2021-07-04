<%@ include file="/ilionnet/taglibs.jsp"%>
<%@page import="ilion.admin.negocio.Usuario"%>
<%@page import="ilion.admin.negocio.UsuarioNegocio"%>
<%@page import="java.util.List"%>
<%@page import="ilion.gc.util.UteisGC"%>
<%@page import="ilion.gc.negocio.GCNegocio"%>
<%@page import="ilion.util.Uteis"%>
<%@page import="ilion.gc.negocio.SubCategoriaArtigo"%>

<% 
String nomeCategoria = request.getParameter("nomeCategoria");
request.setAttribute("nomeCategoria", nomeCategoria);
%>

<ilion:categoriaGrupoLista varRetorno="grupos"/>


    	<ul class="dropdown dropdown-horizontal">
          <li class="dir menuWebSite"><a href="<ilion:url/>ilionnet/gc">Web Site</a>
          		<ul>
<c:forEach var="g" items="${grupos}">
                	<li class="dir"><a href="javascript:;"><c:out value='${g.nome}'/></a>
                    	<ul>



<c:forEach var="c" items="${g.categorias}">
	
	<c:if test="${c.nome != param.nomeCategoria}">
                       	  <li><a href="<ilion:url/>ilionnet/gc-busca.sp?site=${c.site}&nomeCategoria=${c.nome}"><c:out value='${c.nome}'/></a></li>
    </c:if>
    <c:if test="${c.nome == param.nomeCategoria}">
        <c:if test="${ ! categoriaProp.subCategorias}">
        				  <li><a href="<ilion:url/>ilionnet/gc-busca.sp?site=${c.site}&nomeCategoria=${c.nome}"><c:out value='${c.nome}'/></a></li>
        </c:if>
		<c:if test="${categoriaProp.subCategorias}">
                      	  <li class="dir"><a href="<ilion:url/>ilionnet/gc-busca.sp?site=${c.site}&nomeCategoria=${c.nome}"><c:out value='${c.nome}'/></a>
		                       	<ul>

									<c:forEach var="s" items="${subCategorias}">
					                	<li><a href="<ilion:url/>ilionnet/gc-busca.sp?site=${c.site}&nomeCategoria=${c.nome}idSubCategoria=${s.id}"><c:out value='${s.nome}'/></a></li>
									</c:forEach>

		                       	</ul>
                       	  </li>
		</c:if>
    </c:if>
    
</c:forEach>


                        </ul>
                    </li>
</c:forEach>
              </ul>
          </li>


      </ul>
   