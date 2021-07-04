package ilion.util.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;

import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListImpl;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

public class LuceneBusca {
	
	static Logger logger = Logger.getLogger(LuceneBusca.class);
	
	private LuceneConfig luceneConfig;
	private Integer numHits = 10000;
	
	public LuceneBusca(LuceneConfig luceneConfig) {
		super();
		this.luceneConfig = luceneConfig;
	}
	
	public ValueList buscar(VLHForm vlhForm, Boolean retornarEntidade, ValueListInfo valueListInfo) throws Exception {

		ValueList valueList = null;

		Sort sort = luceneConfig.getLuceneNegocio().getSort();

		if( sort != null ) {
			valueList = buscarOrdenado(vlhForm, retornarEntidade, valueListInfo);
		} else {
			valueList = buscarTopScore(vlhForm, retornarEntidade, valueListInfo);
		}

		return valueList;
	}

	public ValueList buscarOrdenado(VLHForm vlhForm, Boolean retornarEntidade, ValueListInfo valueListInfo) throws Exception {
		Query query = luceneConfig.getLuceneNegocio().configurarQueryParaBusca(vlhForm);
		Sort sort = luceneConfig.getLuceneNegocio().getSort();

		TopDocs topFieldDocs = luceneConfig.getIndexSearcher().search(query, numHits, sort);

		int numTotalHits = topFieldDocs.totalHits;
		//System.out.println(numTotalHits + " total matching documents");
		valueListInfo.setTotalNumberOfEntries(numTotalHits);

		int firstResult = (valueListInfo.getPagingPage() - 1) * valueListInfo.getPagingNumberPer();

		if(firstResult > numTotalHits) {
			logger.info("Fim paginação: "+query.toString());
			return new ValueListImpl(new ArrayList(), valueListInfo);
		}

		List<ScoreDoc> lista = Arrays.asList(topFieldDocs.scoreDocs);

		ValueList artigos = montarValueList(lista, firstResult, retornarEntidade, valueListInfo);

		return artigos;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ValueList montarValueList(List hits, Integer firstResult, Boolean retornarEntidade, ValueListInfo valueListInfo) throws CorruptIndexException, IOException {
		List objs = new ArrayList();

		int hitsPerPage = valueListInfo.getPagingNumberPer();

		for (int i = firstResult; i < (hitsPerPage+firstResult); i++) {

			//System.out.println("i: "+i+", hits.length: "+hits.length+", numTotalHits: "+numTotalHits);

			try {
				ScoreDoc scoreDoc = (ScoreDoc) hits.get(i);
				
				Document hitDoc = luceneConfig.getIndexSearcher().doc(scoreDoc.doc);
				
				if( retornarEntidade ) {
					//System.out.println(hitDoc.get("id")+" "+hitDoc.get("conteudo"));

					Object obj = luceneConfig.getLuceneNegocio().converter(hitDoc);

					objs.add(obj);
					
				} else {
					
					objs.add(hitDoc);
					
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				//logger.error("java.lang.ArrayIndexOutOfBoundsException: index: "+e.getMessage());
				break;
			}
		}

		return new ValueListImpl(objs, valueListInfo);
	}

	private ValueList buscarTopScore(VLHForm vlhForm, Boolean retornarEntidade, ValueListInfo valueListInfo) throws Exception {
		Query query = luceneConfig.getLuceneNegocio().configurarQueryParaBusca(vlhForm);

		TopScoreDocCollector collector = TopScoreDocCollector.create(numHits, true);
		luceneConfig.getIndexSearcher().search(query, collector);

		int numTotalHits = collector.getTotalHits();
		//System.out.println(numTotalHits + " total matching documents");
		valueListInfo.setTotalNumberOfEntries(numTotalHits);

		int firstResult = (valueListInfo.getPagingPage() - 1) * valueListInfo.getPagingNumberPer();

		if(firstResult > numTotalHits) {
			logger.info("Fim paginação: "+query.toString());
			return new ValueListImpl(new ArrayList(), valueListInfo);
		}

		ScoreDoc[] hits = collector.topDocs(firstResult).scoreDocs;

		ValueList retorno = montarValueList(hits, retornarEntidade, valueListInfo);

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ValueList montarValueList(ScoreDoc[] hits, Boolean retornarEntidade, ValueListInfo valueListInfo) throws CorruptIndexException, IOException {
		List objs = new ArrayList();

		int hitsPerPage = valueListInfo.getPagingNumberPer();

		for (int i = 0; i < hitsPerPage; i++) {

			//System.out.println("i: "+i+", hits.length: "+hits.length+", numTotalHits: "+numTotalHits);

			try {
				Document hitDoc = luceneConfig.getIndexSearcher().doc(hits[i].doc);
				
				if( retornarEntidade ) {
					//System.out.println("score="+hits[i].score+" : "+hitDoc.get("id")+" "+hitDoc.get("conteudo"));

					Object obj = luceneConfig.getLuceneNegocio().converter(hitDoc);

					objs.add(obj);
					
				} else {
					
					objs.add(hitDoc);
					
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				//logger.error("java.lang.ArrayIndexOutOfBoundsException: index: "+e.getMessage());
				break;
			}
		}

		return new ValueListImpl(objs, valueListInfo);
	}
	
	public String formatarQueryString(VLHForm vlhForm) {
		String q = vlhForm.getPalavraChave();
		
		String query = null;
		
		if( ! Uteis.ehNuloOuVazio(q) ) {
			
			if(q.indexOf("\"") != -1) {
				query += " "+q;
			} else {
				query += " \""+q+"\" OR "+q;
			}
			
		} else {
			query = "a* b* c* d* e* f* g* h* i* j* k* l* m* n* o* p* q* r* s* t* u* v* w* x* y* z* 1* 2* 3* 4* 5* 6* 7* 8* 9* 0*";
		}
		
		//logger.info(query);
		
		return query;
	}
}