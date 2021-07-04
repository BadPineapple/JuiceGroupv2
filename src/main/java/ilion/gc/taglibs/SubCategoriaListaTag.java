package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.categoria.negocio.SubCategoriaArtigoSiteNegocio;
import ilion.util.Uteis;

public class SubCategoriaListaTag extends TagSupport {


	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(SubCategoriaListaTag.class);
	
	private String site;
	private String categoria;
	private String order;
	private String varRetorno;
	private String colunas;

	
	public void setSite(String site) {
		this.site = site;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}
	
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(categoria, varRetorno) ) {
			return SKIP_BODY;
		}
		
		SubCategoriaArtigoSiteNegocio subCategoriaArtigoSiteNegocio = 
				SpringApplicationContext.getBean(SubCategoriaArtigoSiteNegocio.class);
		
		try {
			
			ArtigoParamsVO artigoParamsVO = 
					ArtigoParamsVO
					.build()
					.comSite(site)
					.comCategoria(categoria)
					.comColunas(colunas)
					.comOrder(order)
					.comDataAtual();
			
			List subCategorias = subCategoriaArtigoSiteNegocio.listarSubCategoriaSite(artigoParamsVO);
			pageContext.getRequest().setAttribute(varRetorno, subCategorias);
			
		} catch (Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}