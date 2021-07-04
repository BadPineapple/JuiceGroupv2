package ilion.util.lucene;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class LuceneConfig {

	static Logger logger = Logger.getLogger(LuceneConfig.class);

	private LuceneNegocio luceneNegocio;

	private Directory directoryIndex;
	private IndexReader indexReader;
	private IndexSearcher indexSearcher;

	private Set<String> stopWords;

	private LuceneBusca luceneBusca;
	private LuceneIndexar luceneIndexar;
	private LuceneDesindexar luceneDesindexar;
	private LuceneIndexTotal luceneIndexTotal;
	
	public static final Version VERSAO_LUCENE = Version.LUCENE_36;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LuceneConfig(LuceneNegocio luceneNegocio) {
		super();

		this.luceneNegocio = luceneNegocio;

		this.luceneBusca = new LuceneBusca(this);

		this.luceneIndexar = new LuceneIndexar(this);

		this.luceneDesindexar = new LuceneDesindexar(this);

		this.luceneIndexTotal = new LuceneIndexTotal(this);

		init();
	}

	private void init() {
		//logger.info("Iniciando lucene...");

		File indexPathFile = new File(luceneNegocio.getPathIndex());

		try {

			if( ! indexPathFile.exists()) {
				indexPathFile.mkdir();
			}

			if( indexPathFile.exists() ) {
				directoryIndex = FSDirectory.open(indexPathFile);

				IndexWriter indexWriter = novoIndexWriter();
				indexWriter.close();
				
				indexReader = IndexReader.open(directoryIndex);
				
				atribuirStopWords();
				
				logger.info("Lucene \""+luceneNegocio.getEntidade()+"\" corretamente iniciado...");
			}

		} catch (Throwable t) {
			String m = "Erro ao iniciar Lucene";
			logger.error(m, t);
		} finally {
			if( ! indexPathFile.exists() ) {
				logger.info("ERRO ao iniciar Lucene. PastaIndex nao existe em "+luceneNegocio.getPathIndex());
			}
		}
	}

	private void atribuirStopWords() {
		String keys = "a;as;o;os;e;";
		keys += "um;uma;uns;umas;";
		keys += "ao;aos;";
		keys += "da;das;de;do;dos;";
		keys += "este;está;estão;estavam;estive;";
		keys += "pela;pelas;pelo;";
		keys += "por;para;pode;";
		keys += "na;nas;nem;em;no;nos;mas;mais;sobre;";
		keys += "ano;anos;mês;meses;semana;semanas;dia;dias;";
		keys += "hora;horas;minuto;minutos;segundo;segundos;";
		keys += "sem;são;";
		keys += "antes;após;";
		keys += "dizem;";
		keys += "quer;querem;";
		keys += "faz;fazem;";
		keys += "já;";

		stopWords = new HashSet<String>();

		StringTokenizer st = new StringTokenizer(keys, ";");
		while(st.hasMoreElements()) {
			String e = (String) st.nextElement();

			stopWords.add(e);
		}
	}

	public IndexWriter novoIndexWriter() throws CorruptIndexException, LockObtainFailedException, IOException {
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(VERSAO_LUCENE,  new MyCustomAnalyzer());

		IndexWriter indexWriter = new IndexWriter(directoryIndex, indexWriterConfig);
		
		return indexWriter;
	}

	public void rollbackIndexWriter(IndexWriter indexWriter, Throwable t) throws IOException {
		indexWriter.rollback();

		String m = "Erro ao indexar o conteudo no Lucene";
		logger.error(m, t);
	}

	public void closeIndexWriter(IndexWriter indexWriter) {
		if(indexWriter != null) {
			try {
				indexWriter.close();
			} catch (Throwable t) {
				String m = "Erro ao fechar indexWriter do Lucene";
				logger.error(m, t);
			}
		}
		
		refreshIndexSearcher();
	}

	public synchronized IndexReader getIndexReader() throws CorruptIndexException, LockObtainFailedException, IOException {
		
		IndexReader newReader = IndexReader.openIfChanged(indexReader);
		if (newReader != null) {
			indexReader = newReader;
		}
		
		return indexReader;
	}
	
	public synchronized IndexSearcher getIndexSearcher() throws CorruptIndexException, LockObtainFailedException, IOException {
		if(indexSearcher != null) {
			return indexSearcher;
		}
		
		indexReader = getIndexReader();
		
		indexSearcher = new IndexSearcher(indexReader);
		
		return indexSearcher;
	}
	
	private synchronized void refreshIndexSearcher() {
		if( indexSearcher != null ) {
			try {
				indexSearcher.close();
			} catch (IOException e) {
				String m = "Erro ao fechar indexSearcher do Lucene";
				logger.error(m, e);
			} finally {
				indexSearcher = null;
			}
		}
	}

	public Set<String> getStopWords() {
		return stopWords;
	}

	public LuceneNegocio getLuceneNegocio() {
		return luceneNegocio;
	}

	public void setLuceneNegocio(LuceneNegocio luceneNegocio) {
		this.luceneNegocio = luceneNegocio;
	}

	public LuceneBusca getLuceneBusca() {
		return luceneBusca;
	}

	public LuceneIndexar getLuceneIndexar() {
		return luceneIndexar;
	}

	public LuceneDesindexar getLuceneDesindexar() {
		return luceneDesindexar;
	}

	public LuceneIndexTotal getLuceneIndexTotal() {
		return luceneIndexTotal;
	}
	
	public void finalizar() {
		try {
			if(indexSearcher != null) {
				indexSearcher.close();
				indexSearcher = null;
			}
		} catch (IOException e) {
			String m = "Erro ao fechar indexSearcher";
			logger.error(m, e);
		}
		
		try {
			if(indexReader != null) {
				indexReader.close();
				indexReader = null;
			}
		} catch (IOException e) {
			String m = "Erro ao fechar indexReader";
			logger.error(m, e);
		}
		
		try {
			if(directoryIndex != null) {
				directoryIndex.close();
				directoryIndex = null;
			}
		} catch (IOException e) {
			String m = "Erro ao fechar directoryIndex";
			logger.error(m, e);
		}
	}
}