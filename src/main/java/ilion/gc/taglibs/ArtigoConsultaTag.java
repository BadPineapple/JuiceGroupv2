package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.ArtigoSiteNegocio;
import ilion.util.Uteis;

public class ArtigoConsultaTag extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoConsultaTag.class);
	
	private String site;
	private String categoria;
	private String subCategoria;
	private String artigo;
	private Boolean topicos;

	private String order;
	private String varRetorno;
	private String colunas;


	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}
	public void setTopicos(Boolean topicos) {
		this.topicos = topicos;
	}
	
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(categoria) ) {
			return SKIP_BODY;
		}
		
		try {
			
			if( Uteis.ehNuloOuVazio(artigo) ) {
				consultarPrimeiroArtigo();
			} else {
				consultarArtigo();
			}

		} catch (Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
	
	private void consultarPrimeiroArtigo() {
		
		ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
		
		ArtigoParamsVO artigoParamsVO = ArtigoParamsVO
				.build()
				.comSite(site)
				.comCategoria(categoria)
				.comSubCategoriaUrl(subCategoria)
				.comTopicos(topicos)
				.comOrder(order)
				.comMaxResults(1)
				.comColunas(colunas)
				.comDataAtual();
		
		List artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO);
		
		Object retorno = artigos;
		
		retorno = ! artigos.isEmpty() ? artigos.get(0) : null;
		
		pageContext.getRequest().setAttribute(varRetorno, retorno);
		
	}
	
	private void consultarArtigo() {
		
		ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
		
		ArtigoConsultaParamsVO artigoParamsVO = ArtigoConsultaParamsVO
				.build()
				.comSite(site)
				.comCategoria(categoria)
				.comSubCategoriaUrl(subCategoria)
				.comArtigoUrl(artigo)
				.comTopicos(topicos)
				.comOrder(order)
				.comColunas(colunas)
				.comDataAtual();
		
		Object objeto = artigoSiteNegocio.consultarArtigoEnderecoUrl(artigoParamsVO);

		pageContext.getRequest().setAttribute(varRetorno, objeto);
		
	}

}