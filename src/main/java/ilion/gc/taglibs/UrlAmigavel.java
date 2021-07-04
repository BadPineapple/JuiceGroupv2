package ilion.gc.taglibs;

import java.io.Serializable;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.util.Uteis;

public class UrlAmigavel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String uri;
	
	private String categoria;
	
	private String urlSubCategoria;
	
	private String urlArtigo;
	
	private String urlFinal;
	
	public UrlAmigavel() {
		super();
	}
	
	
	
	public void setUri(String uri) {
		this.uri = uri;
	}






	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public void setUrlSubCategoria(String urlSubCategoria) {
		this.urlSubCategoria = urlSubCategoria;
	}



	public void setUrlArtigo(String urlArtigo) {
		this.urlArtigo = urlArtigo;
	}



	public String getUrlFinal() {
		if( ! Uteis.ehNuloOuVazio(urlFinal) ) {
			return urlFinal;
		}
		
		urlFinal = gerarUrlFinal();
		
		return urlFinal;
	}
	
	private String gerarUrlFinal() {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		String url = propNegocio.findValueById(PropEnum.URL);
		
		StringBuilder out = new StringBuilder();
		
		out.append(url);
		out.append("/");
		
		if( ! Uteis.ehNuloOuVazio(uri) ) {
			
			out.append(uri);
			
		} else if(categoria != null ||
				urlSubCategoria != null ||
				urlArtigo != null) {
			
			out.append(categoria);
			out.append("/");
			
			if( ! Uteis.ehNuloOuVazio(urlSubCategoria) ) {
				out.append(urlSubCategoria);
				out.append("/");
			}
			
			if( ! Uteis.ehNuloOuVazio(urlArtigo) ) {
				out.append(urlArtigo);
				out.append("/");
			}
		}
		
		return out.toString();
	}
}