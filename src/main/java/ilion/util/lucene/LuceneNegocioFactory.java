package ilion.util.lucene;

import org.springframework.stereotype.Service;

import ilion.gc.negocio.Artigo;

@Service
public class LuceneNegocioFactory {
	
	public static LuceneNegocio getImpl(Object object) {
		
		LuceneNegocio luceneNegocio = null;
		
		if(object instanceof Artigo) {
			//luceneNegocio = SpringApplicationContext.getBean(ArtigoLuceneNegocio.class);
		}
		
		return luceneNegocio;
	}
}