package ilion.gc.taglibs;


import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DataAtualTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String varRetorno;
	
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}

	@Override
	public int doStartTag() throws JspException {
		
		pageContext.getRequest().setAttribute(varRetorno, new Date());
		
		return SKIP_BODY;
	}
}