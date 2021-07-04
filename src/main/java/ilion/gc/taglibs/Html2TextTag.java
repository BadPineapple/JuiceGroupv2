package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.util.Html2Text;
import ilion.util.Uteis;

public class Html2TextTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(Html2TextTag.class);
	
	private String html;
	
	public void setHtml(String html) {
		this.html = html;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		if( Uteis.ehNuloOuVazio(html) ) {
			return SKIP_BODY;
		}
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.print( Html2Text.parseReturn(html) );
		} catch (IOException e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
}