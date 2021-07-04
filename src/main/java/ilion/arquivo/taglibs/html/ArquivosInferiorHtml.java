package ilion.arquivo.taglibs.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.util.Uteis;

public class ArquivosInferiorHtml {
	
	static Logger logger = Logger.getLogger(ArquivosInferiorHtml.class);
	
	public static void imprimir(List<Arquivo> arquivos, Appendable out) {
		
		List<Arquivo> arquivosValidos = new ArrayList<Arquivo>();
		
		for (Arquivo arquivo : arquivos) {
			
			if( ! ehValido(arquivo) ) {
				continue;
			}
			
			arquivosValidos.add(arquivo);
			
		}
		
		_imprimir(arquivosValidos, out);
	}
	
	private static boolean ehValido(Arquivo arquivo) {
		
		return "inferior".equals(arquivo.getLayout());
	}
	
	/*
<ul class="img-list">
  
  <li>
    <div class="img-group">
      <div class="img-list-item" 
           style="background-image: url(http://placehold.it/320x240);">
      </div>
      <figcaption>First image</figcaption>
    </div>
  </li>
  
  <li>
    <a href="#">
      <div class="img-group">
        <div class="img-list-item" 
             style="background-image: url(http://placehold.it/320x240);">
        </div>
        <figcaption>Second image</figcaption>
      </div>
    </a>
  </li>
  
</ul>
	 */
	
	private static void _imprimir(List<Arquivo> arquivos, Appendable out) {
		
		if( arquivos == null || arquivos.isEmpty() ) {
			return;
		}
		
		try {
			
			out.append("<ul class=\"img-list\">");
			
			for (Arquivo arquivo : arquivos) {
				
				_imprimir(arquivo, out);
				
			}
			
			out.append("</ul>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
	}
	
	private static void _imprimir(Arquivo arquivo, Appendable out) throws Exception {
		
		if( ! Uteis.ehNuloOuVazio(arquivo.getLink()) ) {
			_imprimirComLink(arquivo, out);
			return;
		}
		
		Boolean ehImagemSemAmpliacao = arquivo.getTipo() == (byte) 1;
		
		if( ehImagemSemAmpliacao ) {
			_imprimirSemAmpliacao(arquivo, out);
			return;
		}
		
		Boolean ehImagemComAmpliacao = arquivo.getTipo() == (byte) 2;
		
		if( ehImagemComAmpliacao ) {
			_imprimirComAmpliacao(arquivo, out);
			return;
		}
		
		Boolean ehDownload = arquivo.getTipo() == (byte) 3;
		
		if( ehDownload ) {
			_imprimirDownload(arquivo, out);
			return;
		}
		
		
	}
	
	private static void _imprimirSemAmpliacao(Arquivo arquivo, Appendable out) throws Exception {
		
		String url = arquivo.getUrl();
		
		out.append("<li>");
		out.append("<div class=\"img-group\">");
		out.append("  <div class=\"img-list-item\" style=\"background-image:url(").append(url).append(");\">");
		out.append("  </div>");
		out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
		out.append("</div>");
		out.append("</li>");
		
	}
	
	private static void _imprimirComAmpliacao(Arquivo arquivo, Appendable out) throws IOException {
		
		String pathArquivo1 = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo1();
		String pathArquivo2 = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo2();
		
		out.append("<li>");
		out.append("<a href=\"").append(pathArquivo2).append("\" class=\"fancybox\" rel=\"group\">");
		out.append("<div class=\"img-group\">");
		out.append("  <div class=\"img-list-item\" style=\"background-image:url(").append(pathArquivo1).append(");\">");
		out.append("  </div>");
		out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
		out.append("</div>");
		out.append("</a>");
		out.append("</li>");
		
	}
	
	private static void _imprimirComLink(Arquivo arquivo, Appendable out) throws Exception {
		
		String pathArquivo1 = ArquivoTipoEnum.IMAGEM.getPathRelativo()+arquivo.getArquivo1();
		
		out.append("<li>");
		out.append("<a href=\"").append(arquivo.getLink()).append("\">");
		out.append("<div class=\"img-group\">");
		out.append("  <div class=\"img-list-item\" style=\"background-image:url(").append(pathArquivo1).append(");\">");
		out.append("  </div>");
		out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
		out.append("</div>");
		out.append("</a>");
		out.append("</li>");
		
	}
	
	private static void _imprimirDownload(Arquivo arquivo, Appendable out) throws Exception {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		String imagemDownload = "icon-thumb-download.gif";
		
		String pathIcone = propNegocio.findValueById(PropEnum.URL)+"/ilionnet/images/"+imagemDownload;
		String pathDownload = ArquivoTipoEnum.DOWNLOAD.getPathRelativo()+arquivo.getArquivo1();
		
		out.append("<li>");
		out.append("<a href=\"").append(pathDownload).append("\" target=\"_blank\">");
		out.append("<div class=\"img-group\">");
		out.append("  <div class=\"img-list-item\" style=\"background-image:url(").append(pathIcone).append(");\">");
		out.append("  </div>");
		out.append("  <figcaption>").append(arquivo.getTexto()!=null?arquivo.getTexto():"").append("</figcaption>");
		out.append("</div>");
		out.append("</a>");
		out.append("</li>");
		
	}
}