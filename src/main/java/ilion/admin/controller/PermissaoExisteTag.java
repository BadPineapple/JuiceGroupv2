package ilion.admin.controller;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.admin.negocio.Usuario;
import ilion.util.Uteis;

public class PermissaoExisteTag extends TagSupport {

	static Logger logger = Logger.getLogger(PermissaoExisteTag.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String permissoes;
	
	public void setPermissoes(String permissoes) {
		this.permissoes = permissoes;
	}
	
	public int doStartTag() throws JspException {
		
		Usuario usuarioSessao = (Usuario) pageContext.getSession().getAttribute("usuarioSessao");
		
		if( usuarioSessao == null ) {
			return SKIP_BODY;
		}
		
		if( Uteis.ehNuloOuVazio(permissoes) ) {
			return SKIP_BODY;
		}
		
		try {
			
			String[] permissoesVetor = permissoes.split(";");
			
			Boolean possuiPermissao = false;
			
			for (String permissao : permissoesVetor) {
				
				possuiPermissao = usuarioSessao.getPermissoes().contains(permissao);
				
				if( possuiPermissao ) {
					break;
				}
			}
			
			return (possuiPermissao) ? EVAL_BODY_INCLUDE : SKIP_BODY;
			
		} catch (Exception e) {
			logger.error("", e);
			return SKIP_BODY;
		}
		
	}
}