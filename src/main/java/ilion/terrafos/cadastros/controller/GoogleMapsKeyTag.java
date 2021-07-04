package ilion.terrafos.cadastros.controller;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;

public class GoogleMapsKeyTag extends TagSupport {

	private static final long serialVersionUID = 1L;


	static Logger logger = Logger.getLogger(GoogleMapsKeyTag.class);

	private String varRetorno;
	
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {

		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		try {

			String key = propNegocio.findValueById(PropEnum.GOOGLE_MAPS_KEY);
			pageContext.getRequest().setAttribute(varRetorno, key);
			
		} catch (Exception e) {
			String m = "erro ao buscar key do google maps";

			logger.error(m, e);
		}

		return SKIP_BODY;
	}
}