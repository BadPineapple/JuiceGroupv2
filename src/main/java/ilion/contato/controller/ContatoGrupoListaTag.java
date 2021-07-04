package ilion.contato.controller;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.contato.negocio.ContatoGrupoNegocio;

public class ContatoGrupoListaTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ContatoGrupoListaTag.class);
	
	private String somentePermissaoNewsletter;
	private String varRetorno;
	
	public void setSomentePermissaoNewsletter(String somentePermissaoNewsletter) {
		this.somentePermissaoNewsletter = somentePermissaoNewsletter;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	@Override
	public int doStartTag() throws JspException {

		try {
			
			ContatoGrupoNegocio contatoGrupoNegocio = 
					SpringApplicationContext.getBean(ContatoGrupoNegocio.class);
			
			List grupos = contatoGrupoNegocio.listarGruposComQtd( "true".equals(somentePermissaoNewsletter) );
			pageContext.setAttribute(varRetorno, grupos);
			
		} catch (Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
	
	

}
