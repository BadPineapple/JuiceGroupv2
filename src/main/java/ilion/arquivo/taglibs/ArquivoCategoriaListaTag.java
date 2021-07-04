package ilion.arquivo.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.Arquivo;
import ilion.gc.negocio.ArtigoSiteNegocio;
import ilion.gc.taglibs.ArtigoParamsVO;
import ilion.util.Uteis;

public class ArquivoCategoriaListaTag extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArquivoCategoriaListaTag.class);

	private String site;
	private String categoria;
	private String order;
	private String layout;
	private Integer maxResults;
	private String varRetorno;

	
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
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(categoria) ) {
			return SKIP_BODY;
		}
		
		try {
			ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
			
			ArtigoParamsVO artigoParamsVO = ArtigoParamsVO
					.build()
					.comSite(site)
					.comCategoria(categoria)
					.comOrder(order)
					.comLayout(layout)
					.comMaxResults(maxResults)
					.comDataAtual();
			
			List<Arquivo> arquivos = artigoSiteNegocio.listarArquivosDosArtigos(artigoParamsVO);
			
			if (maxResults != null && maxResults == 1 && arquivos != null && arquivos.size() > 0) {
				
				pageContext.getRequest().setAttribute(varRetorno, arquivos.get(0));
				return SKIP_BODY;
			}
			
			pageContext.getRequest().setAttribute(varRetorno, arquivos);

		} catch (Exception e) {
			String m = "";

			logger.error(m, e);
		}

		return SKIP_BODY;
	}
}