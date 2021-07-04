package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoNegocio;

public class ComentarioQtdTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ComentarioQtdTag.class);

	private Object obj;

	public int doStartTag(){

		ArtigoNegocio artigoNegocio = SpringApplicationContext.getBean(ArtigoNegocio.class);

		Artigo artigo = (Artigo) obj;

		if(artigo == null) {
			return SKIP_BODY;
		}

		Integer qtdComentarios = artigoNegocio.qtdComentarios(artigo);

		if(qtdComentarios == null) {
			qtdComentarios = 0;
		}

		try {
			pageContext.getOut().print(qtdComentarios);
		} catch (IOException e) {
			logger.error("", e);
			try {
				pageContext.getOut().print("?");
			} catch (IOException e1) {}
		}

		return SKIP_BODY;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}