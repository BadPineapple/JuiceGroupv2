package ilion.gc.taglibs;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.GCSiteNegocio;

public class SubCategoriaArtigoConsultaTag extends TagSupport {


	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(SubCategoriaArtigoConsultaTag.class);

	private String varRetorno;
	private String colunas;
	private String categoria;

	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public int doStartTag() {

		GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);

		String subCategoriaUrl = null;
		try {
			subCategoriaUrl = (String) pageContext.getRequest().getAttribute("subCategoriaUrl");

			if(subCategoriaUrl == null || subCategoriaUrl.length() == 0) {
				subCategoriaUrl = gcSiteNegocio.listarPrimeiraSubCategoriaSite(categoria, Order.asc("posicao"));
			}

			if(subCategoriaUrl != null && subCategoriaUrl.length() != 0) {
				Object objeto = gcSiteNegocio.consultarSubCategoriaEnderecoUrl(categoria, subCategoriaUrl, colunas);
				pageContext.setAttribute(varRetorno, objeto);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return SKIP_BODY;
	}
}