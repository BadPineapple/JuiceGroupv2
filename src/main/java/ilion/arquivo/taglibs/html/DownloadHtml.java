package ilion.arquivo.taglibs.html;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;

public class DownloadHtml extends AbstractArquivoHtml {
	
	static Logger logger = Logger.getLogger(DownloadHtml.class);
	
	public DownloadHtml(AbstractArquivoHtml proximo) {
		super();
		this.proximo = proximo;
	}
	
	@Override
	protected Boolean ehValido(Arquivo arquivo) {
		
		Boolean ehDownload = arquivo.getTipo() == (byte) 3;
		
		return ehDownload;
	}
	
	@Override
	protected void escrever(Arquivo arquivo, Appendable out) {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		try {
			
			String imagemDownload = "icon-thumb-download.gif";
			
			String pathIcone = propNegocio.findValueById(PropEnum.URL)+"/ilionnet/images/"+imagemDownload;
			String pathDownload = ArquivoTipoEnum.DOWNLOAD.getPathRelativo()+arquivo.getArquivo1();
			
			out.append("<a target=\"_blank\" href=\"").append(pathDownload).append("\">");
			out.append("<div class=\"img-group\">");
			out.append("  <img src=\"").append(pathIcone).append("\" />");
			out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
			out.append("</div>");
			out.append("</a>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		

	}

}
