package ilion.arquivo.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio.Layout;
import ilion.arquivo.negocio.ArquivoSiteNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.Uteis;

public class ImagemConsultaTag extends TagSupport {

	static Logger logger = Logger.getLogger(ImagemConsultaTag.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeClasse;
	private String idObjeto;
	private String layout;
	private String imagemDefault;
	private String varRetorno;
	
	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}
	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public void setImagemDefault(String imagemDefault) {
		this.imagemDefault = imagemDefault;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(nomeClasse, idObjeto) ) {
			return SKIP_BODY;
		}
		
		try {
			
			String imagem = null;
			
			ArquivoSiteNegocio arquivoSiteNegocio = SpringApplicationContext.getBean(ArquivoSiteNegocio.class);
			List<Arquivo> imagens = arquivoSiteNegocio.listarImagensSite(nomeClasse, idObjeto, Layout.fromString(layout));
			
			if( ! imagens.isEmpty() ) {
				
				imagem = imagens.get(0).getUrl();
				
			} else {
				
				if( ! Uteis.ehNuloOuVazio(imagemDefault) ) {
					
					PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
					
					imagem = propNegocio.findValueById(PropEnum.STATIC_URL)+"/"+imagemDefault;
					
				}
			}
			
			pageContext.setAttribute(varRetorno, imagem);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
}
