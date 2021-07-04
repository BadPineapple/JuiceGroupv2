package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.Comentario;
import ilion.gc.negocio.GCSiteNegocio;

public class ComentarioTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(ComentarioTag.class);


	private Object obj;
	private Integer qtd;
	private String varRetorno;

	public int doStartTag(){

		GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);

		Artigo artigo = (Artigo) obj;

		if(artigo == null) {
			return SKIP_BODY;
		}

		Integer qtdComentarios = gcSiteNegocio.qtdComentariosSite(artigo);
		pageContext.setAttribute("qtdComentarios", qtdComentarios);

		List<Comentario> comentarios = null;

		Boolean todos = "true".equals(pageContext.getRequest().getParameter("comentarios"));
		if(todos) {
			comentarios = gcSiteNegocio.listarComentariosSite(artigo, null, "nome;email;data;comentario;");
		} else {
			comentarios = gcSiteNegocio.listarComentariosSite(artigo, qtd, "nome;email;data;comentario;");
		}

		pageContext.setAttribute(varRetorno, comentarios);
		pageContext.setAttribute("todosComentarios", todos);

		return SKIP_BODY;
	}

	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

}