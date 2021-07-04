package ilion.gc.taglibs;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;

public class ArtigoSubCategoriaValueListTag extends TagSupport {

	private static final long serialVersionUID = 1L;


	static Logger logger = Logger.getLogger(ArtigoSubCategoriaValueListTag.class);

	private String categoria;
	private String order;
	private String varRetorno;
	private String qtd;
	private String colunas;
	private String primeiraSubCategoria;


	public void setPrimeiraSubCategoria(String primeiraSubCategoria) {
		this.primeiraSubCategoria = primeiraSubCategoria;
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
	public void setQtd(String qtd) {
		this.qtd = qtd;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}

	public int doStartTag() {

		ArtigoUteisTag artigoUteisTag = SpringApplicationContext.getBean(ArtigoUteisTag.class);

		//Long idSubCategoria = UteisSpring.converterLong(pageContext.getRequest().getParameter("idSubCategoria"));
		String subCategoriaUrl = (String) pageContext.getRequest().getAttribute("subCategoriaUrl");

		/*
		 * Caso seja chamado uma pagina e nao seja passado o id da subcategoria
		 */
		if(subCategoriaUrl != null && subCategoriaUrl.length() != 0){

			artigoUteisTag.consultarSubCategoriaValueList(pageContext,
					subCategoriaUrl,
					categoria,
					order,
					qtd,
					varRetorno,
					colunas);

		} else if("true".equals(primeiraSubCategoria)){

			artigoUteisTag.consultarValueList(pageContext,
					categoria,
					order,
					qtd,
					varRetorno,
					true);

		}

		return SKIP_BODY;
	}
}