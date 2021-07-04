package ilion.arquivo.taglibs.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ilion.arquivo.negocio.Arquivo;

public class ArquivosLateralEsquerdaHtml {
	
	static Logger logger = Logger.getLogger(ArquivosLateralEsquerdaHtml.class);
	
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
		
		return "lateral_esquerda".equals(arquivo.getLayout());
	}
	
	private static void _imprimir(List<Arquivo> arquivos, Appendable out) {
		
		if( arquivos == null || arquivos.isEmpty() ) {
			return;
		}
		
		try {
			
			AbstractArquivoHtml arquivoHtml = ArquivoHtmlFactory.getInstance();
			
			out.append("<div class=\"img-wrap img-left\">");
			
			for (Arquivo arquivo : arquivos) {
				
				arquivoHtml.executar(arquivo, out);
				
			}
			
			out.append("</div>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
	}
}