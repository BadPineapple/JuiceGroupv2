package ilion.gc.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.ArtigoSiteNegocio;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

public class ArtigoVLHTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoVLHTag.class);
	
	private String site;
	private String categoria;
	private String subCategoria;
	private String secoes;
	private String order;
	private String varRetorno;
	private Integer maxResults;
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
					.comColunas(colunas)
					.comDataAtual();
			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			VLHForm vlhForm = VLHForm.getVLHSession(ArtigoVLHTag.class.getSimpleName()+"session", request);
			
			ValueList artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO, new ValueListInfo(vlhForm, artigoParamsVO.getMaxResults()));
			
			pageContext.getRequest().setAttribute(varRetorno, artigos);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		

		return SKIP_BODY;
	}


}