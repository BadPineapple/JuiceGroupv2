package ilion.arquivo.taglibs;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.util.Uteis;

public class ArquivoListaTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArquivoListaTag.class);

	private String nomeClasse;
	private String idObjeto;
	private String codigo;
	private String varRetorno;
	private Integer maxResults;

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}
	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	
	public void setMaxResults( Integer maxResults ) {

		this.maxResults = maxResults;
	}


	@Override
	public int doStartTag() throws JspException {

		if( Uteis.ehNuloOuVazio(nomeClasse) &&
				idObjeto == null &&
				Uteis.ehNuloOuVazio(codigo) ) {
			logger.info("nomeClasse, idObjeto e codigo nulos");
			return SKIP_BODY;
		}

		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);

		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, codigo);
		
		if (maxResults != null && maxResults.intValue() == 1 && arquivos != null && !arquivos.isEmpty()) {
			pageContext.setAttribute(varRetorno, arquivos.get( 0 ));
		} else {
			
			pageContext.setAttribute(varRetorno, arquivos);
		}


		return SKIP_BODY;
	}
}