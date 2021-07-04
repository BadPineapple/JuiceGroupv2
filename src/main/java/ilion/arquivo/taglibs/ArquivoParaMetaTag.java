package ilion.arquivo.taglibs;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.negocio.Artigo;

public class ArquivoParaMetaTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    
    static Logger logger = Logger.getLogger(ArquivoParaMetaTag.class);
    
    private Artigo artigo;
	private String varRetorno;
	
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
	
	@Override
	public int doStartTag() throws JspException {

		if(artigo == null){
			return SKIP_BODY;
		}
		
		try {
			
			ArquivoNegocio arquivoNegocio = 
					SpringApplicationContext.getBean(ArquivoNegocio.class);
			List<Arquivo> arquivos = arquivoNegocio.listarArquivos(Artigo.class.getSimpleName(), artigo.getId().toString(), null, false);
			
			if( ! arquivos.isEmpty() ) {
				pageContext.setAttribute(varRetorno, arquivos.get(0));
				return SKIP_BODY;
			}
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
//		List<Arquivo> arquivos = new ArrayList<Arquivo>();
//		
//		GCNegocio gcNegocio = (GCNegocio) SingletonFactory.getInstance().getObject(GCNegocio.class);
//		
//		Layout layoutEnum = Layout.valueOf(layout.toUpperCase());
//		
//		List<ArtigoConteudo>artigoConteudos = gcNegocio.listarArtigosConteudoDoArtigo(artigo);
//		
//		for (ArtigoConteudo artigoConteudo : artigoConteudos) {
//	        
//			@SuppressWarnings("unchecked")
//            List<Arquivo> arqs = ArquivoNegocio.listarArquivos(ArtigoConteudo.class.getSimpleName(), artigoConteudo.getId(), layoutEnum, true);
//        
//			arquivos.addAll(arqs);
//		}
//		
//		
//		if(arquivos != null && arquivos.size() > 0){
//			pageContext.setAttribute(varRetorno, arquivos.get(0));
//		}
		
		return SKIP_BODY;
	}
	

}
