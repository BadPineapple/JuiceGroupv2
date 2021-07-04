package ilion.arquivo.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio.Layout;
import ilion.arquivo.negocio.ArquivoSiteNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.Uteis;

public class DownloadConsultaTag extends TagSupport {

	static Logger logger = Logger.getLogger(DownloadConsultaTag.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeClasse;
	private String idObjeto;
	private String layout;
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
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {
		
		if( Uteis.ehNuloOuVazio(nomeClasse, idObjeto) ) {
			return SKIP_BODY;
		}
		
		try {
			
			String url = null;
			
			ArquivoSiteNegocio arquivoSiteNegocio = SpringApplicationContext.getBean(ArquivoSiteNegocio.class);
			List<Arquivo> downloads = arquivoSiteNegocio.listarDownloadsSite(nomeClasse, idObjeto, Layout.fromString(layout));
			
			if( ! downloads.isEmpty() ) {
				ArquivoUteis arquivoUteis = SpringApplicationContext.getBean(ArquivoUteis.class);
				
				url = arquivoUteis.getEnderecoArquivos()+"downloads/"+downloads.get(0).getArquivo1();
			}
			
			pageContext.setAttribute(varRetorno, url);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
}
