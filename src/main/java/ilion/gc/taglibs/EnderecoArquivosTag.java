package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.ArquivoUteis;

public class EnderecoArquivosTag extends TagSupport {

	static Logger logger = Logger.getLogger(EnderecoArquivosTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			
			JspWriter out = pageContext.getOut();
			
			ArquivoUteis arquivoUteis = SpringApplicationContext.getBean(ArquivoUteis.class);
			
			out.print(arquivoUteis.getEnderecoArquivos());
			
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}