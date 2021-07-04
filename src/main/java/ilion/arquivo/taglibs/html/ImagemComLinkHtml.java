package ilion.arquivo.taglibs.html;

import org.apache.log4j.Logger;

import ilion.arquivo.negocio.Arquivo;
import ilion.util.Uteis;

public class ImagemComLinkHtml extends AbstractArquivoHtml {
	
	static Logger logger = Logger.getLogger(ImagemComLinkHtml.class);
	
	public ImagemComLinkHtml(AbstractArquivoHtml proximo) {
		super();
		this.proximo = proximo;
	}

	@Override
	protected Boolean ehValido(Arquivo arquivo) {
		
		Boolean ehImagemSemAmpliacao = arquivo.getTipo() == (byte) 1;
		Boolean ehImagemComAmpliacao = arquivo.getTipo() == (byte) 2;
		Boolean ehImagemComLink = arquivo.getTipo() == (byte) 5;
		
		return (ehImagemSemAmpliacao || ehImagemComAmpliacao || ehImagemComLink) && ! Uteis.ehNuloOuVazio(arquivo.getLink());
	}
	
	@Override
	protected void escrever(Arquivo arquivo, Appendable out) {
		
		try {
			
			out.append("<a href=\"").append(arquivo.getLink()).append("\">");
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
