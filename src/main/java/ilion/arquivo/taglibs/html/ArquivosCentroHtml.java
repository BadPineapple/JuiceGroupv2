package ilion.arquivo.taglibs.html;

import java.util.List;

import org.apache.log4j.Logger;

import ilion.arquivo.negocio.Arquivo;

public class ArquivosCentroHtml {
	
	static Logger logger = Logger.getLogger(ArquivosCentroHtml.class);
	
	public static void imprimir(List<Arquivo> arquivos, Appendable out) {
		
		for (Arquivo arquivo : arquivos) {
			
			if( ! ehValido(arquivo) ) {
				continue;
			}
			
			imprimir(arquivo, out);
			
		}
		
	}
	
	private static boolean ehValido(Arquivo arquivo) {
		
		return "topo".equals(arquivo.getLayout()) || "centro".equals(arquivo.getLayout());
	}
	
	private static void imprimir(Arquivo arquivo, Appendable out) {
		try {
			
			AbstractArquivoHtml arquivoHtml = ArquivoHtmlFactory.getInstance();
			
			out.append("<div class=\"img-wrap img-center\">");
			
			arquivoHtml.executar(arquivo, out);
			
			out.append("</div>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
	}
}