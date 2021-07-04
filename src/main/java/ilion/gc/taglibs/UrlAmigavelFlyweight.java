package ilion.gc.taglibs;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UrlAmigavelFlyweight {
	
	@Cacheable("urls.amigaveis")
	public UrlAmigavel get(
			String uri,  
			String categoria, 
			String urlSubCategoria,
			String urlArtigo) {
		
		UrlAmigavel urlAmigavel = new UrlAmigavel();
		urlAmigavel.setUri(uri);
		urlAmigavel.setCategoria(categoria);
		urlAmigavel.setUrlSubCategoria(urlSubCategoria);
		urlAmigavel.setUrlArtigo(urlArtigo);
		
		return urlAmigavel;
	}
	
}