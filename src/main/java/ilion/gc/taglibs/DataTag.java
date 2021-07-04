package ilion.gc.taglibs;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.util.Uteis;

public class DataTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private Date data;
	
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.print(Uteis.formatarDataPorExtenso(data));
		} catch (IOException e) {
			Logger.getLogger("").error("", e);
		}
		
		return SKIP_BODY;
	}
}