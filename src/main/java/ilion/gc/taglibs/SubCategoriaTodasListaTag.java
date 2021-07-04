package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.gc.categoria.negocio.SubCategoriaArtigoNegocio;

public class SubCategoriaTodasListaTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(SubCategoriaTodasListaTag.class);
	
	private Long idCategoria;
	private String varRetorno;
	
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {
		
		if( idCategoria == null ) {
			return SKIP_BODY;
		}
		
		try {
			CategoriaArtigoNegocio categoriaArtigoNegocio = 
					SpringApplicationContext.getBean(CategoriaArtigoNegocio.class);
			
			CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorId(idCategoria);
			
			SubCategoriaArtigoNegocio subCategoriaArtigoNegocio = 
					SpringApplicationContext.getBean(SubCategoriaArtigoNegocio.class);
			
			List subCategorias = 
					subCategoriaArtigoNegocio.listarSubCategoriasArtigo(
							categoriaArtigo, 
							categoriaArtigo.getSubCategoriaConfig().getOrdem()
							);
			pageContext.getRequest().setAttribute(varRetorno, subCategorias);
			
		} catch (Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}