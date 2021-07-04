package ilion.arquivo.taglibs.html;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;

public class ImagemComAmpliacaoHtml extends AbstractArquivoHtml {
	
	static Logger logger = Logger.getLogger(ImagemComAmpliacaoHtml.class);
	
	public ImagemComAmpliacaoHtml(AbstractArquivoHtml proximo) {
		super();
		this.proximo = proximo;
	}
	
	@Override
	protected Boolean ehValido(Arquivo arquivo) {
		
		Boolean ehImagemComAmpliacao = arquivo.getTipo() == (byte) 2;
		
		return ehImagemComAmpliacao;
	}
	
	@Override
	protected void escrever(Arquivo arquivo, Appendable out) {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		try {
			
			String url2 = propNegocio.findValueById(PropEnum.URL)+"/arquivos/"+arquivo.getArquivo2();
			
			out.append("<a href=\"").append(url2).append("\" class=\"fancybox\" rel=\"group\">");
			out.append("<div class=\"img-group\">");
			out.append("  <img src=\"").append(arquivo.getUrl()).append("\" />");
			out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
			out.append("</div>");
			out.append("</a>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		

	}

}
