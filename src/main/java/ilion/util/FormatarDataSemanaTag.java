package ilion.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class FormatarDataSemanaTag  extends TagSupport{

private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(FormatarDataSemanaTag.class);
	
	private Object value;
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		
		try {
			Date data = (Date) value;
			String retorno = Uteis.formatarDataSemana(data, "dd/MM");
			if(retorno == null || retorno.length() == 0) {
				retorno = "--/--";
			}
			JspWriter out = pageContext.getOut();
			out.print(retorno);
		} catch (IOException e) {
			String m = "Erro ao imprimir valor: "+value;
			logger.error(m, e);
		}
		
		return SKIP_BODY;
	}
	
}
