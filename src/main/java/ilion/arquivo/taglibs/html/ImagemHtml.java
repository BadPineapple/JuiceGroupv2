package ilion.arquivo.taglibs.html;

import org.apache.log4j.Logger;

import ilion.arquivo.negocio.Arquivo;

public class ImagemHtml extends AbstractArquivoHtml {
	
	static Logger logger = Logger.getLogger(ImagemHtml.class);
	
	public ImagemHtml(AbstractArquivoHtml proximo) {
		super();
		this.proximo = proximo;
	}
	
	@Override
	protected Boolean ehValido(Arquivo arquivo) {
		
		Boolean ehImagemSemAmpliacao = arquivo.getTipo() == (byte) 1;
		
		return ehImagemSemAmpliacao;
	}
	
	@Override
	protected void escrever(Arquivo arquivo, Appendable out) {
		
		try {
			
			out.append("<div class=\"img-group\">");
			out.append("  <img src=\"").append(arquivo.getUrl()).append("\" />");
			out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
			out.append("</div>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		

	}

}
