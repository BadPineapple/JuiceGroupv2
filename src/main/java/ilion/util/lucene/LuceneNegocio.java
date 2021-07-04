package ilion.util.lucene;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

public abstract class LuceneNegocio {
	
	static Logger logger = Logger.getLogger(LuceneNegocio.class);
	
	private LuceneConfig luceneConfig;
	
	public LuceneNegocio() {
		super();
		
		this.luceneConfig = new LuceneConfig(this);
	}
	
	public LuceneConfig getLuceneConfig() {
		return luceneConfig;
	}
	
	public LuceneBusca getLuceneBusca() {
		return luceneConfig.getLuceneBusca();
	}
	
	public synchronized void indexar(final Object object) {
		new Thread() {
			
			@Override
			public void run() {
				try {
					
					luceneConfig.getLuceneIndexar().executar(object);
					
				} catch (Exception e) {
					String m = "erro ao indexar "+luceneConfig.getLuceneNegocio().getEntidade();
					logger.error(m, e);
				}
			}
			
		}.start();
	}
	
	public synchronized void desindexar(final Object object) {
		
		new Thread() {
			
			@Override
			public void run() {
				try {
					
					luceneConfig.getLuceneDesindexar().executar(object);
					
				} catch (Exception e) {
					String m = "erro ao desindexar "+luceneConfig.getLuceneNegocio().getEntidade();
					logger.error(m, e);
				}
			}
			
		}.start();
	}
	
	public ValueList buscar(VLHForm vlhForm, Boolean retornarEntidade, ValueListInfo valueListInfo) throws Exception {
		return luceneConfig.getLuceneBusca().buscar(vlhForm, retornarEntidade, valueListInfo);
	}
	
	public void indexarTotal() {
		luceneConfig.getLuceneIndexTotal().executarThread();
	}
	
	public void finalizar() {
		luceneConfig.finalizar();
	}
	
	@SuppressWarnings("rawtypes")
	public abstract Class getEntidade();
	
	public abstract String getPathIndex();
	
	public abstract String getId(Object object);
	
	public abstract Object converter(Document document);
	
	public abstract Document converter(Object object);
	
	public abstract Query configurarQueryParaBusca(VLHForm vlhForm) throws ParseException;
	
	public abstract Sort getSort();
	
	@SuppressWarnings("rawtypes")
	public abstract List listarTodosParaIndexar(Integer firstResult, Integer maxResult);
}