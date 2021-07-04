package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.ArtigoSiteNegocio;

public class ArtigoListaPorTopicosTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ArtigoListaPorTopicosTag.class);
	
	private String site;
	private String categoria;
	private Long idArtigoAtual;
	private List topicos;
	private String order;
	private Integer maxResults;
	private Integer firstResult;
	private String varRetorno;
	
	public void setSite(String site) {
		this.site = site;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setIdArtigoAtual(Long idArtigoAtual) {
		this.idArtigoAtual = idArtigoAtual;
	}
	public void setTopicos(List topicos) {
		this.topicos = topicos;
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
	
	public int doStartTag() {
		
		ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
		
		try {
			
			ArtigosPorTopicosParamsVO artigoParamsVO = ArtigosPorTopicosParamsVO
					.build()
					.comSite(site)
					.comCategoria(categoria)
					.retirarIdArtigoAtual(idArtigoAtual)
					.comTopicos(topicos)
					.comOrder(order)
					.comMaxResults(maxResults)
					.comFirstResult(firstResult)
					.comDataAtual();
			
			List artigos = artigoSiteNegocio.listarArtigosPorTopicosSite(artigoParamsVO);
			
			pageContext.getRequest().setAttribute(varRetorno, artigos);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		

		return SKIP_BODY;
	}


}