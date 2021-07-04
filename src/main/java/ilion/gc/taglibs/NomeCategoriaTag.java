package ilion.gc.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.util.Uteis;

public class NomeCategoriaTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String grupo;
	private String categoria;
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		String retorno = null;
		
		try {
			retorno = Uteis.escreverNomeCategoria(grupo, categoria);
		} catch (Exception e) {
			Logger.getLogger("").error("erro ao retirar grupo de categoria. grupo: "+grupo+", categoria: "+categoria, e);
			retorno = categoria;
		}
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.print(retorno);
		} catch (IOException e) {
			Logger.getLogger("").error("", e);
		}
		
		return SKIP_BODY;
	}
}