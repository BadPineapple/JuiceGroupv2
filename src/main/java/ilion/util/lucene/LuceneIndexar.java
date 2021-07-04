package ilion.util.lucene;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

public class LuceneIndexar {
	
	static Logger logger = Logger.getLogger(LuceneIndexar.class);
	
	private LuceneConfig luceneConfig;
	
	public LuceneIndexar(LuceneConfig luceneConfig) {
		super();
		this.luceneConfig = luceneConfig;
	}
	
	public synchronized void executar(Object object) throws Exception {
		luceneConfig.getLuceneDesindexar().executar(object);
		
		IndexWriter indexWriter = luceneConfig.novoIndexWriter();
		
		try {
			
			Document document = luceneConfig.getLuceneNegocio().converter(object);
			
			indexWriter.addDocument(document);
			
			indexWriter.commit();
			
			logger.info("Doc inserido: "+document.get("id")+"  "+document.get("titulo"));
			
		} catch (Throwable t) {
			
			luceneConfig.rollbackIndexWriter(indexWriter, t);
			
		} finally {
			
			luceneConfig.closeIndexWriter(indexWriter);
			
		}
	}
	
	@SuppressWarnings("rawtypes")
	public synchronized void executar(List objetos) throws IOException {
		IndexWriter indexWriter = luceneConfig.novoIndexWriter();
		
		try {
			
			for (Iterator iterator = objetos.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				
				Document document = luceneConfig.getLuceneNegocio().converter(object);
				
				indexWriter.addDocument(document);
			}
			
			indexWriter.commit();
			
		} catch (Throwable t) {
			
			luceneConfig.rollbackIndexWriter(indexWriter, t);
			
		} finally {
			
			luceneConfig.closeIndexWriter(indexWriter);
			
		}
	}
}