package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;

public class URLTag extends TagSupport {

	static Logger logger = Logger.getLogger(URLTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {

		try {
			
			PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
			
			JspWriter out = pageContext.getOut();
			out.print(propNegocio.findValueById(PropEnum.URL));
			out.print("/");
			
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}