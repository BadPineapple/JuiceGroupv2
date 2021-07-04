package ilion.gc.taglibs;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoNegocio;

public class ArtigoConsultaPorIdTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoConsultaPorIdTag.class);
	
	private Long id;
	private String varRetorno;
	
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int doStartTag() {
		
		if( id == null ){
			return SKIP_BODY;
		}
		
		try {
			
			ArtigoNegocio artigoNegocio = SpringApplicationContext.getBean(ArtigoNegocio.class);
			Artigo artigo = (Artigo) artigoNegocio.consultar(Artigo.class, id);
			
			pageContext.setAttribute(varRetorno, artigo);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		
		return SKIP_BODY;
	}
}