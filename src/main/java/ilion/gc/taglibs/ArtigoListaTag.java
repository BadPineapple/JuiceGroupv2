package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.ArtigoSiteNegocio;

public class ArtigoListaTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoListaTag.class);
	
	private String site;
	private String categoria;
	private String subCategoria;
	private String secoes;
	private String order;
	private String varRetorno;
	private Integer maxResults;
	private Integer firstResult;
	private String colunas;

	
	public void setSite(String site) {
		this.site = site;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public void setSecoes(String secoes) {
		this.secoes = secoes;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}
	
	public int doStartTag() {
		
		ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
		
		try {
			
			ArtigoParamsVO artigoParamsVO = ArtigoParamsVO
					.build()
					.comSite(site)
					.comCategoria(categoria)
					.comSubCategoriaUrl(subCategoria)
					.comSecoes(secoes)
					.comOrder(order)
					.comMaxResults(maxResults)
					.comFirstResult(firstResult)
					.comColunas(colunas)
					.comDataAtual();
			
			List artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO);
			
			Object retorno = artigos;
			
			if( artigos != null && ! artigos.isEmpty()) {
				if(maxResults != null && maxResults == 1) {
					retorno = artigos.get(0);
				}
			} else {
				retorno = null;
			}
			
			pageContext.getRequest().setAttribute(varRetorno, retorno);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		

		return SKIP_BODY;
	}


}