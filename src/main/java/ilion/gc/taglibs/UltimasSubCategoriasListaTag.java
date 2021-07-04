package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.categoria.negocio.SubCategoriaArtigoSiteNegocio;
import ilion.util.Uteis;

public class UltimasSubCategoriasListaTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(UltimasSubCategoriasListaTag.class);
	
	private String site;
	private String categoria;
	private Integer maxResults;
	private String varRetorno;
	
	public void setSite(String site) {
		this.site = site;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(site, categoria) ) {
			return SKIP_BODY;
		}
		
		try {
			
			SubCategoriaArtigoSiteNegocio subCategoriaArtigoSiteNegocio = 
					SpringApplicationContext.getBean(SubCategoriaArtigoSiteNegocio.class);
			
			ArtigoParamsVO artigoParamsVO = 
					ArtigoParamsVO
					.build()
					.comSite(site)
					.comCategoria(categoria)
					.comMaxResults(maxResults)
					.comDataAtual();
			
			List subCategorias = 
					subCategoriaArtigoSiteNegocio.listarUltimasSubCategoriasSite(artigoParamsVO);
			pageContext.getRequest().setAttribute(varRetorno, subCategorias);
			
		} catch (Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}