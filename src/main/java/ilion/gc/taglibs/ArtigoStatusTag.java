package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.GCNegocio;

public class ArtigoStatusTag extends SimpleTagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoStatusTag.class);


	private Artigo artigo;

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	@Override
	public void doTag() throws JspException, IOException {
		GCNegocio gcNegocio = SpringApplicationContext.getBean(GCNegocio.class);

		JspWriter out = getJspContext().getOut();

		try {

			String status = gcNegocio.identificarStatus(artigo);

			out.print(status);

		} catch (Exception e) {
			logger.error("", e);
		}
	}
}