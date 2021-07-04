package ilion.gc.taglibs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoConteudo;
import ilion.gc.negocio.ArtigoSiteNegocio;
import ilion.util.Uteis;

public class ArtigoConteudoImprimeTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ArtigoConteudoListaTag.class);
	
	private Object obj;
	private String layout;
	
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		
		if(obj == null) {
			return SKIP_BODY;
		}
		
		Long idArtigo = null;

		if(obj instanceof Map) {
			
			HashMap map = (HashMap) obj;
			
			idArtigo = (Long) map.get("id");
			
		} else {
			
			idArtigo = (Long) Uteis.getRetornoMetodoReflection(obj, "getId");
			
		}
		
		ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
		List<ArtigoConteudo> artigoConteudos = artigoSiteNegocio.listarArtigoConteudoIdTextoSite(new Artigo(idArtigo), layout);
		
		try {
			
			JspWriter out = pageContext.getOut();
			
			for (ArtigoConteudo artigoConteudo : artigoConteudos) {
				
				artigoSiteNegocio.artigoConteudoImprimir(artigoConteudo, out);
				
			}
			
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}