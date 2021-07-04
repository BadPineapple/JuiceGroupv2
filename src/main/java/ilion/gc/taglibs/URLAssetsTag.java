package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropNegocio;

public class URLAssetsTag extends TagSupport {

	static Logger logger = Logger.getLogger(URLAssetsTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pasta;
	
	public void setPasta(String pasta) {
		this.pasta = pasta;
	}
	
	@Override
	public int doStartTag() throws JspException {

		try {
			
			PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
			
			JspWriter out = pageContext.getOut();
			
			out.print(propNegocio.getUrlAssets(pasta));
			
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}