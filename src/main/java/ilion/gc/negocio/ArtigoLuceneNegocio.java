package ilion.gc.negocio;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.lucene.LuceneConfig;
import ilion.util.lucene.LuceneNegocio;
import ilion.util.lucene.MyCustomAnalyzer;

public class ArtigoLuceneNegocio extends LuceneNegocio {
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntidade() {
		return Artigo.class;
	}

	@Override
	public String getPathIndex() {
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		String path = propNegocio.findValueById(PropEnum.PATH_LUCENE);
		
		if( Uteis.ehNuloOuVazio(path) ) {
			path = Uteis.caminhoFisico+"/arquivos/";
		}
		
		path += "/lucene-index/artigo/";
		
		ArquivoUteis.verificarPastaExistente(path);
		
		return path;
	}

	@Override
	public String getId(Object object) {
		Artigo artigo = (Artigo) object;
		return artigo.getId().toString();
	}

	@Override
	public Object converter(Document document) {
		ArtigoLuceneVH artigoLuceneVH = new ArtigoLuceneVH(document);
		return artigoLuceneVH;
	}

	@Override
	public Document converter(Object object) {
		Artigo artigo = (Artigo) object;
		ArtigoLuceneVH artigoLuceneVH = new ArtigoLuceneVH(artigo);
		return artigoLuceneVH.toDocument();
	}

	@Override
	public Query configurarQueryParaBusca(VLHForm vlhForm) throws ParseException {
		QueryParser parser = new QueryParser(LuceneConfig.VERSAO_LUCENE, "conteudo", new MyCustomAnalyzer());
		parser.setAllowLeadingWildcard(true);
		
		String qRetorno = getLuceneBusca().formatarQueryString(vlhForm);
		
		Query query = parser.parse(qRetorno);
		
		return query;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listarTodosParaIndexar(Integer firstResult, Integer maxResult) {
		
		GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);
		List<Artigo> artigos = gcSiteNegocio.listarArtigoTodosParaIndexarLuceneSite(maxResult, firstResult);
		
		return artigos;
	}

	@Override
	public Sort getSort() {
		return new Sort(new SortField("titulo", SortField.STRING, false));
	}

}
