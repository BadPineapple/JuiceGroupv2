package ilion.gc.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Enquete;
import ilion.gc.negocio.EnqueteNegocio;
import ilion.util.Uteis;

public class EnqueteTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(EnqueteTag.class);

	private String varRetorno;

	public int doStartTag(){

		EnqueteNegocio enqueteNegocio = SpringApplicationContext.getBean(EnqueteNegocio.class);

		Enquete enquete = enqueteNegocio.consultarEnqueteSite();

		if(enquete != null) {
			pageContext.getRequest().setAttribute(varRetorno, enquete);

			Boolean jaVotada = Uteis.enqueteJaVotada(enquete.getId(), (HttpServletRequest)pageContext.getRequest());
			pageContext.getRequest().setAttribute("jaVotada", jaVotada);

			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
}