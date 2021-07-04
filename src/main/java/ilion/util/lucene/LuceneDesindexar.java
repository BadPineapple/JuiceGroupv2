package ilion.util.lucene;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;

public class LuceneDesindexar {
	
	static Logger logger = Logger.getLogger(LuceneDesindexar.class);
	
	private LuceneConfig luceneConfig;
	
	public LuceneDesindexar(LuceneConfig luceneConfig) {
		super();
		this.luceneConfig = luceneConfig;
	}
	
	public synchronized void executar(Object object) throws Exception {
		
		IndexWriter indexWriter = luceneConfig.novoIndexWriter();
		
		try {
			
			Query query = configurarQueryConsulta(object);
			
			indexWriter.deleteDocuments(query);
			
			indexWriter.commit();
			
		} catch (Throwable t) {
			
			luceneConfig.rollbackIndexWriter(indexWriter, t);
			
		} finally {
			
			luceneConfig.closeIndexWriter(indexWriter);
			
		}
	}
	
	private Query configurarQueryConsulta(Object object) throws ParseException {
		String id = luceneConfig.getLuceneNegocio().getId(object);
		
		Analyzer analyzer = new StandardAnalyzer(LuceneConfig.VERSAO_LUCENE);
		
		QueryParser queryParser = new QueryParser(LuceneConfig.VERSAO_LUCENE, "id", analyzer);
		
		return queryParser.parse(id);
	}
	
	public synchronized void limpar() throws Exception {
		IndexWriter indexWriter = luceneConfig.novoIndexWriter();
		
		try {
			
			indexWriter.deleteAll();
			
			indexWriter.commit();
			
			logger.info("todos os documento foram excluídos de "+luceneConfig.getLuceneNegocio().getEntidade());
			
		} catch (Throwable t) {
			
			luceneConfig.rollbackIndexWriter(indexWriter, t);
			
		} finally {
			
			luceneConfig.closeIndexWriter(indexWriter);
			
		}
	}
}