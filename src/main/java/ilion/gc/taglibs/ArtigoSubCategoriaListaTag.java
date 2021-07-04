package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.GCSiteNegocio;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

public class ArtigoSubCategoriaListaTag extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoSubCategoriaListaTag.class);


	private String categoria;
	private String secoes;
	private String order;
	private String varRetorno;
	private Integer qtd;
	private String colunas;
	private Boolean paginacao;

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}
	public void setPaginacao(Boolean paginacao) {
		this.paginacao = paginacao;
	}

	public int doStartTag() {

		GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);

		String subCategoriaUrl = (String) pageContext.getRequest().getParameter("subCategoriaUrl");

		if(subCategoriaUrl == null || subCategoriaUrl.length() == 0){
			subCategoriaUrl = gcSiteNegocio.consultarPrimeiraSubCategoriaSite(categoria, Order.asc("posicao"));
		}

		if(paginacao == null || ! paginacao) {
			artigoSubCategoriaLista(categoria,
					subCategoriaUrl,
					secoes,
					order,
					qtd,
					varRetorno,
					colunas);
		} else {
			listarSubCategoriaValueList(categoria,
					subCategoriaUrl,
					secoes,
					order,
					qtd,
					varRetorno,
					colunas);
		}

		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	private void artigoSubCategoriaLista(String categoria,
			String subCategoriaUrl,
			String secoes,
			String order,
			Integer qtd,
			String varRetorno,
			String colunas) {

		ArtigoUteisTag artigoUteisTag = SpringApplicationContext.getBean(ArtigoUteisTag.class);

		List artigos = artigoUteisTag.listarSubCategoriaArtigos(categoria,
				subCategoriaUrl,
				secoes,
				order,
				qtd,
				varRetorno,
				colunas);

		if( artigos != null && ! artigos.isEmpty()) {
			if(qtd != null && qtd == 1) {
				pageContext.getRequest().setAttribute(varRetorno, artigos.get(0));
			} else {
				pageContext.getRequest().setAttribute(varRetorno, artigos);
			}
		}
	}

	private void listarSubCategoriaValueList(String categoria,
			String subCategoriaUrl,
			String secoes,
			String order,
			Integer qtd,
			String varRetorno,
			String colunas) {

		ArtigoUteisTag artigoUteisTag = SpringApplicationContext.getBean(ArtigoUteisTag.class);

		VLHForm vlhForm = VLHForm.getVLHSession("artigoSubCategoriaValueList"+categoria+subCategoriaUrl, (HttpServletRequest)pageContext.getRequest());

		try {

			ValueList valueList = artigoUteisTag.listarSubCategoriaArtigosValueList(
					categoria,
					subCategoriaUrl,
					secoes,
					order,
					colunas,
					new ValueListInfo(vlhForm, new Integer(qtd)));

			pageContext.getRequest().setAttribute(varRetorno, valueList);

		} catch (Exception e) {
			logger.error("", e);
		}
	}
}