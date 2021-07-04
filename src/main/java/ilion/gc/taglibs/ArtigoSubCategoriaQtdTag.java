package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.GCNegocio;
import ilion.gc.negocio.SubCategoriaArtigo;

public class ArtigoSubCategoriaQtdTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ArtigoSubCategoriaQtdTag.class);


	private SubCategoriaArtigo subCategoriaArtigo;

	public void setSubCategoriaArtigo(SubCategoriaArtigo subCategoriaArtigo) {
		this.subCategoriaArtigo = subCategoriaArtigo;
	}

	public int doStartTag(){

		GCNegocio gcNegocio = SpringApplicationContext.getBean(GCNegocio.class);

		if(subCategoriaArtigo == null) {
			return SKIP_BODY;
		}

		Integer qtdArtigos = gcNegocio.qtdArtigos(subCategoriaArtigo);

		if(qtdArtigos == null) {
			qtdArtigos = 0;
		}

		try {
			pageContext.getOut().print(qtdArtigos);
		} catch (IOException e) {
			logger.error("", e);
			try {
				pageContext.getOut().print("?");
			} catch (IOException e1) {}
		}

		return SKIP_BODY;
	}
}