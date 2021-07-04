package ilion.admin.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class PermissaoTag extends TagSupport {

	static Logger logger = Logger.getLogger(PermissaoTag.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	private String verificacao;
	private String varRetorno;
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setVerificacao(String verificacao) {
		this.verificacao = verificacao;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}

	public int doStartTag() throws JspException {
		Usuario usuarioSessao = (Usuario) pageContext.getSession().getAttribute("usuarioSessao");
		
		if( usuarioSessao == null ) {
			return SKIP_BODY;
		}
		
		boolean exibir = false;
		if(tipo != null &&
				!tipo.equals("")) {
			exibir = usuarioSessao.getPermissoes().contains(tipo);
		}
		
		if("false".equals(verificacao))
			exibir = !exibir;
		if(varRetorno != null && !varRetorno.trim().equals("")) {
			((HttpServletRequest) pageContext.getRequest()).setAttribute(varRetorno, new Boolean(exibir));
		}
		return (exibir) ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}