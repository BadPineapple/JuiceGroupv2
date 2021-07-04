package ilion.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;

public class ExibirMenuTag extends TagSupport {

	static Logger logger = Logger.getLogger(ExibirMenuTag.class);
	
	private static final long serialVersionUID = 1L;
	
	private String prop;
	
	public void setProp(String prop) {
		this.prop = prop;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		if( Uteis.ehNuloOuVazio(prop) ) {
			return SKIP_BODY;
		}
		
		PropEnum propEnum = PropEnum.fromString(prop);
		
		if( propEnum == null ) {
			return SKIP_BODY;
		}
		
		try {
			
			PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
			
			Boolean exibirMenu = propNegocio.findValueBooleanById(propEnum, false);
			
			if( exibirMenu ) {
				return EVAL_BODY_INCLUDE;
			}
			
		} catch (Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}