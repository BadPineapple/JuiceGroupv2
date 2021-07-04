package ilion.arquivo.taglibs.html;

public class ArquivoHtmlFactory {

	private static AbstractArquivoHtml instance;
	
	public static AbstractArquivoHtml getInstance() {
		if( instance != null ) {
			return instance;
		}
		
		ImagemComLinkHtml instance = 
				new ImagemComLinkHtml(
				new ImagemComAmpliacaoHtml(
						new ImagemHtml(
								new DownloadHtml(null)
						)
				)
		);
		
		return instance;
	}
	
}