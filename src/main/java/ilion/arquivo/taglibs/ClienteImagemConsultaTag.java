package ilion.arquivo.taglibs;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.ClienteImagemService;
import ilion.util.Uteis;

public class ClienteImagemConsultaTag extends TagSupport {

	static Logger logger = Logger.getLogger(ClienteImagemConsultaTag.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeClasse;
	private String imagemDefault;
	private String varRetorno;
	
	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}
	public void setImagemDefault(String imagemDefault) {
		this.imagemDefault = imagemDefault;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(nomeClasse) || Uteis.ehNuloOuVazio(imagemDefault) ) {
			return SKIP_BODY;
		}
		
		try {
			
			ClienteImagemService clienteImagemService = SpringApplicationContext.getBean(ClienteImagemService.class);
			String pathImagemRetorno = clienteImagemService.consultarPorNomeClasse(nomeClasse, imagemDefault);
			pageContext.getRequest().setAttribute(varRetorno, pathImagemRetorno);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
}
