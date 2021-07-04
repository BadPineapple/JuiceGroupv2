package ilion.gc.taglibs;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.Usuario;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.gc.categoria.negocio.GrupoVH;

public class CategoriaGrupoListaTag extends TagSupport {


	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(CategoriaGrupoListaTag.class);

	private String varRetorno;

	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}

	@Override
	public int doStartTag() throws JspException {

		//String idioma = (String) pageContext.getSession().getAttribute("idiomaSelecionado");
		
		String site = (String) pageContext.getSession().getAttribute("siteSelecionado");
		
		if( site == null ) {
			
			PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
			
			site = propNegocio.findValueById(PropEnum.SITE);
			
			pageContext.getSession().setAttribute("siteSelecionado", site);
		}

		Usuario usuario = (Usuario) pageContext.getSession().getAttribute("usuarioSessao");

		try {
			
			CategoriaArtigoNegocio categoriaArtigoNegocio = 
					SpringApplicationContext.getBean(CategoriaArtigoNegocio.class);
			
			List<GrupoVH> grupos = categoriaArtigoNegocio.listarGruposComCategoriasDoPerfil(usuario.getPerfil(), site);
			
			pageContext.getRequest().setAttribute(varRetorno, grupos);
			
		} catch(Exception e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}

}