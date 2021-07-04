package ilion.gc.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.util.UteisGC;
import ilion.util.Uteis;
import ilion.util.ValueListInfo;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
public class GCSiteNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	private Map<String, String> caracteres;

	public GCSiteNegocio() {
		super();
		caracteres = new HashMap<String, String>();
		caracteres.put("�", "&ccedil;");
		caracteres.put("�", "&aacute;");
		caracteres.put("�", "&eacute;");
		caracteres.put("�", "&iacute;");
		caracteres.put("�", "&oacute;");
		caracteres.put("�", "&uacute;");
		caracteres.put("�", "&atilde;");
		caracteres.put("�", "&otilde;");
		caracteres.put("�", "&acirc;");
		caracteres.put("�", "&ecirc;");
		caracteres.put("�", "&icirc;");
		caracteres.put("�", "&ocirc;");
		caracteres.put("�", "&ucirc;");
		caracteres.put("�", "&agrave;");

	}

	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}

	public Object inserir(Object object) {
		return hibernateUtil.save(object);
	}

	public void atualizar(Object object) {
		hibernateUtil.update(object);
	}

	public void excluir(Object object) {
		hibernateUtil.delete(object);
	}

	@SuppressWarnings("unchecked")
	public List<ArtigoConteudo> listarArtigoConteudo(Artigo artigo, String layout) {
		if(artigo.getId() == null && (artigo.getCodControle() == null || artigo.getCodControle().length() == 0)) {
			throw new IllegalArgumentException("artigo id e codControle sao nulos");
		}

		DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);
		if(artigo.getId() != null) {
			dc.createAlias("artigo", "a");
			dc.add(Restrictions.eq("a.id", artigo.getId()));
		} else {
			dc.add(Restrictions.eq("codControle", artigo.getCodControle()));
		}
		dc.add(Restrictions.eq("layout", layout));
		dc.addOrder(Order.asc("posicao"));

		return (List<ArtigoConteudo>) hibernateUtil.list(dc);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listarArtigoConteudoSite(Artigo artigo, String layout, CharSequence colunas) {
		if(artigo.getId() == null && (artigo.getCodControle() == null || artigo.getCodControle().length() == 0)) {
			throw new IllegalArgumentException("artigo id e codControle sao nulos");
		}

		DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);

		if(artigo.getId() != null) {
			dc.createAlias("artigo", "a");
			dc.add(Restrictions.eq("a.id", artigo.getId()));
		} else {
			dc.add(Restrictions.eq("codControle", artigo.getCodControle()));
		}

		dc.add(Restrictions.eq("layout", layout));
		dc.addOrder(Order.asc("posicao"));

		return hibernateUtil.listar(dc, null, null, colunas);
	}

	public Object consultarArtigoEnderecoUrl(String categoria, String subCategoriaUrl, String tituloUrl, String colunas) {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);

		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.nome", categoria));

		if(subCategoriaUrl != null && subCategoriaUrl.length() != 0) {
			dc.createAlias("subCategoria", "s");
			dc.add(Restrictions.eq("s.enderecoUrl", subCategoriaUrl));
		}

		dc.add(Restrictions.eq("enderecoUrl", tituloUrl));

		List artigos = hibernateUtil.listar(dc, 1, null, colunas);
		if(artigos != null && ! artigos.isEmpty()) {
			return artigos.get(0);
		} else {
			return null;
		}
	}

	public String listarPrimeiraSubCategoriaSite(String categoria, Order order){
		java.sql.Date hoje = new java.sql.Date(System.currentTimeMillis());

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SubCategoriaArtigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", categoria))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", hoje))
				.add(Restrictions.ge("dataExpiracao", hoje))
				.addOrder(order)
				.setProjection(Projections.property("enderecoUrl"));

		List enderecosUrl = hibernateUtil.listar(detachedCriteria, 1, null, null);

		return (String) ((enderecosUrl != null && !enderecosUrl.isEmpty()) ? enderecosUrl.get(0) : null);
	}

	public String consultarPrimeiraSubCategoriaSite(String categoria, Order order){
		java.sql.Date hoje = new java.sql.Date(System.currentTimeMillis());

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SubCategoriaArtigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", categoria))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", hoje))
				.add(Restrictions.ge("dataExpiracao", hoje))
				.addOrder(order)
				.setProjection(Projections.property("enderecoUrl"));

		List enderecosUrl = hibernateUtil.listar(detachedCriteria, 1, null, null);

		return (String) ((enderecosUrl != null && !enderecosUrl.isEmpty()) ? enderecosUrl.get(0) : null);
	}

	public Object consultarSubCategoriaEnderecoUrl(String categoria, String enderecoUrl, String colunas) {
		DetachedCriteria dc = DetachedCriteria.forClass(SubCategoriaArtigo.class);
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.nome", categoria));
		dc.add(Restrictions.eq("enderecoUrl", enderecoUrl));

		List subCategorias = hibernateUtil.listar(dc, 1, null, colunas);
		if(subCategorias != null && ! subCategorias.isEmpty()) {
			return subCategorias.get(0);
		} else {
			return null;
		}
	}

	public Long listarIdPrimeiraSubCategoriaSite(String categoria) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SubCategoriaArtigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", categoria))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", new Date()))
				.add(Restrictions.ge("dataExpiracao", new Date()))
				.addOrder(Order.asc("posicao"))
				.setProjection(Projections.property("id"));

		List ids = hibernateUtil.listar(detachedCriteria, 1, null, null);

		return (Long) ((ids != null && !ids.isEmpty()) ? ids.get(0) : null);
	}

	@SuppressWarnings("unchecked")
	public List listarArtigoSite(String nomeCategoria, 
			boolean somenteDestaque, 
			boolean retirarDestaque, 
			Order orderBy, 
			Integer qtdResultados,
			String colunas) {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class, "_")
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", nomeCategoria))
				.add(Restrictions.eq("_.status", "Publicado"))
				.add(Restrictions.le("_.dataPublicacao", new Date()))
				.add(Restrictions.ge("_.dataExpiracao", new Date()));
		if(orderBy != null)
			detachedCriteria.addOrder(orderBy);

		if(somenteDestaque) {
			detachedCriteria.add(Restrictions.eq("_.destaque", Boolean.TRUE));
		} else if(retirarDestaque)
			detachedCriteria.add(Restrictions.or(Restrictions.eq("_.destaque", Boolean.FALSE), Restrictions.eq("_.destaque", null)));

		return hibernateUtil.listar(detachedCriteria, qtdResultados, null, colunas);
	}


//	public List listarArtigoSite(String nomeCategoria, 
//			String subCategoriaUrl, 
//			String secoes, 
//			String orderBy, 
//			Integer qtdResultados, 
//			String colunas) {
//
//		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
//		dc.createAlias("categoriaArtigo", "c");
//
//		if(subCategoriaUrl != null && subCategoriaUrl.length() != 0) {
//			dc.createAlias("subCategoria", "s");
//			dc.add(Restrictions.eq("s.enderecoUrl", subCategoriaUrl));
//		}
//
//		if(nomeCategoria.contains(";")) {
//			List<String> categorias = Uteis.formarListaComString(nomeCategoria, ";");
//			dc.add(Restrictions.in("c.nome", categorias));
//		} else {
//			dc.add(Restrictions.eq("c.nome", nomeCategoria));
//		}
//
//		dc.add(Restrictions.eq("_.status", "Publicado"));
//		dc.add(Restrictions.le("_.dataPublicacao", new Date()));
//		dc.add(Restrictions.ge("_.dataExpiracao", new Date()));
//
//		if( ! Uteis.ehNuloOuVazio(orderBy) ) {
//			Order order = hibernateUtil.obterOrderBy(orderBy);
//			dc.addOrder(order);
//		}
//
//		if(secoes != null) {
//			if(secoes.length() != 0) {
//				if( ! "todas".equalsIgnoreCase(secoes) ) {
//					dc.add(Restrictions.like("_.secoes", "%>"+secoes+"<%"));
//				}
//			} else if(secoes.length() == 0) {
//				dc.add(Restrictions.or(Restrictions.eq("_.secoes", ""), Restrictions.isNull("_.secoes")));
//			}
//		}
//
//		return hibernateUtil.findAll(dc, qtdResultados, null, colunas);
//	}


	@SuppressWarnings("unchecked")
	public List listarArtigoSite(String nomeCategoria, 
			String secoes, 
			Order orderBy, 
			Integer qtdResultados,
			String colunas) {		

		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("categoriaArtigo", "c");

		if(nomeCategoria.contains(";")) {
			List<String> categorias = Uteis.formarListaComString(nomeCategoria, ";");
			dc.add(Restrictions.in("c.nome", categorias));
		} else {
			dc.add(Restrictions.eq("c.nome", nomeCategoria));
		}

		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", new Date()));
		dc.add(Restrictions.ge("_.dataExpiracao", new Date()));

		if(orderBy != null)
			dc.addOrder(orderBy);

		if(secoes != null) {
			if(secoes.length() != 0) {
				if( ! "todas".equalsIgnoreCase(secoes) ) {
					dc.add(Restrictions.like("_.secoes", "%>"+secoes+"<%"));
				}
			} else if(secoes.length() == 0) {
				dc.add(Restrictions.or(Restrictions.eq("_.secoes", ""), Restrictions.isNull("_.secoes")));
			}
		}

		return hibernateUtil.listar(dc, qtdResultados, null, colunas);
	}

	@SuppressWarnings("unchecked")
	public List listarSubCategoriaArtigoSite(String nomeCategoria, 
			String subCategoriaUrl, 
			String secoes, 
			String orderBy, 
			Integer qtdResultados,
			String colunas) {		

		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("categoriaArtigo", "c");

		dc.createAlias("subCategoria", "s");
		dc.add(Restrictions.eq("s.enderecoUrl", subCategoriaUrl));

		if(nomeCategoria.contains(";")) {
			List<String> categorias = Uteis.formarListaComString(nomeCategoria, ";");
			dc.add(Restrictions.in("c.nome", categorias));
		} else {
			dc.add(Restrictions.eq("c.nome", nomeCategoria));
		}

		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", new Date()));
		dc.add(Restrictions.ge("_.dataExpiracao", new Date()));

		if( ! Uteis.ehNuloOuVazio(orderBy) ) {
			Order order = hibernateUtil.obterOrderBy(orderBy);
			dc.addOrder(order);
		}
		
		if(secoes != null) {
			if(secoes.length() != 0) {
				if( ! "todas".equalsIgnoreCase(secoes) ) {
					dc.add(Restrictions.like("_.secoes", "%>"+secoes+"<%"));
				}
			} else if(secoes.length() == 0) {
				dc.add(Restrictions.or(Restrictions.eq("_.secoes", ""), Restrictions.isNull("_.secoes")));
			}
		}

		return hibernateUtil.listar(dc, qtdResultados, null, colunas);
	}

	@SuppressWarnings("unchecked")
	public Artigo listarArtigoAleatorio(String categoria) throws Exception{

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", categoria))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", new Date()))
				.add(Restrictions.ge("dataExpiracao", new Date()))
				.setProjection(Projections.property("id"));

		List ids = hibernateUtil.list(detachedCriteria);		
		Artigo artigo = new Artigo();		
		if(ids != null && ids.size() > 0){
			Integer index = (int)Math.ceil(Math.random() * ids.size())-1;
			Long id = (Long) ids.get(index);				
			artigo.setId(id);	
			artigo = (ilion.gc.negocio.Artigo) this.consultar(Artigo.class, artigo.getId());						
			return artigo;
		}

		return null;		
	}

	@SuppressWarnings("unchecked")
	public List listarSubCategoriaSite(Long idSubCategoria, 
			boolean somenteDestaque, 
			boolean retirarDestaque, 
			Order orderBy,
			String colunas) throws Exception {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class, "_")
				.createAlias("subCategoria", "s")
				.add(Restrictions.eq("s.id", idSubCategoria))
				.add(Restrictions.eq("_.status", "Publicado"))
				.add(Restrictions.le("_.dataPublicacao", new Date()))
				.add(Restrictions.ge("_.dataExpiracao", new Date()))
				.addOrder(orderBy);

		if(somenteDestaque){
			detachedCriteria.add(Restrictions.eq("_.destaque", Boolean.TRUE));
		} else if(retirarDestaque)
			detachedCriteria.add(Restrictions.or(Restrictions.eq("_.destaque", Boolean.FALSE), Restrictions.isNull("_.destaque")));

		return hibernateUtil.listar(detachedCriteria, null, null, colunas);
	}

	@SuppressWarnings("unchecked")
	public List listarSubCategoriaSite(String subCategoriaUrl, 
			String categoria,
			boolean somenteDestaque, 
			boolean retirarDestaque, 
			Order orderBy,
			String colunas) {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class, "_")
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", categoria))
				.createAlias("subCategoria", "s")
				.add(Restrictions.eq("s.enderecoUrl", subCategoriaUrl))
				.add(Restrictions.eq("_.status", "Publicado"))
				.add(Restrictions.le("_.dataPublicacao", new Date()))
				.add(Restrictions.ge("_.dataExpiracao", new Date()))
				.addOrder(orderBy);

		if(somenteDestaque){
			detachedCriteria.add(Restrictions.eq("_.destaque", Boolean.TRUE));
		} else if(retirarDestaque)
			detachedCriteria.add(Restrictions.or(Restrictions.eq("_.destaque", Boolean.FALSE), Restrictions.isNull("_.destaque")));

		return hibernateUtil.listar(detachedCriteria, null, null, colunas);
	}

	@SuppressWarnings("unchecked")
	public ValueList listarArtigoVLHSite(String nomeCategoria, 
			String secoes, 
			String orderBy, 
			String colunas, 
			ValueListInfo valueListInfo) {		

		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("categoriaArtigo", "c");

		if(nomeCategoria.contains(";")) {
			List<String> categorias = Uteis.formarListaComString(nomeCategoria, ";");
			dc.add(Restrictions.in("c.nome", categorias));
		} else {
			dc.add(Restrictions.eq("c.nome", nomeCategoria));
		}

		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", new Date()));
		dc.add(Restrictions.ge("_.dataExpiracao", new Date()));
		
		if(secoes != null && secoes.length() != 0) {
			if( ! "todas".equalsIgnoreCase(secoes) ) {
				dc.add(Restrictions.like("_.secoes", "%>"+secoes+"<%"));
			}
		} else {
			dc.add(Restrictions.or(Restrictions.eq("_.secoes", ""), Restrictions.isNull("_.secoes")));
		}
		
		Order order = hibernateUtil.obterOrderBy(orderBy);
		
		return hibernateUtil.consultarValueList(dc, order, colunas, valueListInfo);
	}

	public ValueList listarSubCategoriaValueList(Long idSubCategoria, boolean somenteDestaque, Order orderBy, String colunas, ValueListInfo valueListInfo) {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class)
				.createAlias("subCategoria", "s")
				.add(Restrictions.eq("s.id", idSubCategoria))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", new Date()))
				.add(Restrictions.ge("dataExpiracao", new Date()))
				.addOrder(orderBy);

		if(somenteDestaque)
			detachedCriteria.add(Restrictions.eq("destaque", Boolean.TRUE));

		return hibernateUtil.consultarValueList(detachedCriteria, orderBy, colunas, valueListInfo);
	}

	public ValueList listarSubCategoriaValueListSite(String nomeCategoria,
			String subCategoriaUrl,
			String orderBy,
			String colunas,
			ValueListInfo valueListInfo) {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class, "_")
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", nomeCategoria))
				.createAlias("subCategoria", "s")
				.add(Restrictions.eq("s.enderecoUrl", subCategoriaUrl))
				.add(Restrictions.eq("_.status", "Publicado"))
				.add(Restrictions.le("_.dataPublicacao", new Date()))
				.add(Restrictions.ge("_.dataExpiracao", new Date()));

		Order order = hibernateUtil.obterOrderBy(orderBy);
		
		return hibernateUtil.consultarValueList(detachedCriteria, order, colunas, valueListInfo);
	}

	public ValueList listarSubCategoriaValueListSite(String nomeCategoria, 
			String subCategoriaUrl, 
			boolean somenteDestaque, 
			String orderBy,
			String colunas,
			ValueListInfo valueListInfo) {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class, "_")
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", nomeCategoria))
				.createAlias("subCategoria", "s")
				.add(Restrictions.eq("s.enderecoUrl", subCategoriaUrl))
				.add(Restrictions.eq("_.status", "Publicado"))
				.add(Restrictions.le("_.dataPublicacao", new Date()))
				.add(Restrictions.ge("_.dataExpiracao", new Date()));

		if(somenteDestaque)
			detachedCriteria.add(Restrictions.eq("_.destaque", Boolean.TRUE));
		
		Order order = hibernateUtil.obterOrderBy(orderBy);
		
		return hibernateUtil.consultarValueList(detachedCriteria, order, colunas, valueListInfo);
	}

	public Collection<String> converterCaracteres(String palavraInicial) {
		Collection<String> palavras = new ArrayList<String>();
		palavras.add(palavraInicial);

		String[] vetor1 = palavraInicial.split(" ");
		if(vetor1 != null) {
			for (String v : vetor1) {
				palavras.add(v);
			}
		}

		String palavraFinal = "";
		for (Iterator<String> iter = caracteres.keySet().iterator(); iter.hasNext();) {
			String k = (String) iter.next();
			if(palavraInicial.contains(k)) {
				palavraFinal = palavraInicial.replace(k,(String) caracteres.get(k));
			}
		}

		if(palavraFinal != null && palavraFinal.length() != 0) {
			palavras.add(palavraFinal);

			String[] vetor2 = palavraFinal.split(" ");
			if(vetor1 != null) {
				for (String v : vetor2) {
					palavras.add(v);
				}
			}
		}

		return palavras;
	}

	private List<Criterion> formarCriterions(String palavraChave, String coluna) {
		List<Criterion> criterions = new ArrayList<Criterion>();

		Collection<String> palavras = converterCaracteres(palavraChave);
		for (Iterator<String> iter = palavras.iterator(); iter.hasNext();) {
			String elemento = (String) iter.next();

			String like = "%"+elemento+"%";

			criterions.add(Restrictions.like(coluna, like));
		}

		return criterions;
	}

	private void addCriterions(DetachedCriteria dc, String palavraChave, String coluna) {
		if(palavraChave != null && palavraChave.length() != 0) {
			Collection<String> palavras = converterCaracteres(palavraChave);

			if(palavras.size() > 0) {

				List<Criterion> criterions = formarCriterions(palavraChave, coluna);

				Criterion cFinal = (Criterion) criterions.iterator().next();

				for (Iterator<Criterion> iter = criterions.iterator(); iter.hasNext();) {
					Criterion c = (Criterion) iter.next();
					cFinal = Restrictions.or(cFinal, c);
				}

				dc.add(cFinal);
			}
		}
	}

	public ValueList listarArtigoVLHSite(Collection<String> categorias, 
			boolean somenteDestaque, 
			String orderBy, 
			String colunas,
			ValueListInfo valueListInfo) {		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class, "_")
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.in("c.nome", categorias))
				.add(Restrictions.eq("_.status", "Publicado"))
				.add(Restrictions.le("_.dataPublicacao", new Date()))
				.add(Restrictions.ge("_.dataExpiracao", new Date()));

		if(somenteDestaque)
			detachedCriteria.add(Restrictions.eq("_.destaque", Boolean.TRUE));
		
		Order order = hibernateUtil.obterOrderBy(orderBy);
		
		return hibernateUtil.consultarValueList(detachedCriteria, order, colunas, valueListInfo);
	}

	public ValueList buscarArtigoSite(String palavraChave,
			String categoriasBusca, 
			ValueListInfo valueListInfo) {		

		DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);
		addCriterions(dc, palavraChave, "texto");
		dc.setProjection(Projections.property("artigo.id"));
		//List idsArtigo = HibernateUtil.findAll(dc);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.in("c.nome", Uteis.converterStringParaCollection(categoriasBusca, ";")))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", new Date()))
				.add(Restrictions.ge("dataExpiracao", new Date()));

		Collection<Criterion> criterions = formarCriterions(palavraChave, "titulo");

		Criterion cFinal = (Criterion) criterions.iterator().next();

		for (Iterator<Criterion> iter = criterions.iterator(); iter.hasNext();) {
			Criterion c = (Criterion) iter.next();
			cFinal = Restrictions.or(cFinal, c);
		}

		detachedCriteria.add(Restrictions.or(cFinal, Subqueries.propertyIn("id", dc)));

		return hibernateUtil.consultarValueList(detachedCriteria, Order.desc("dataPublicacao"), valueListInfo);
	}

//	public Boolean categoriaPossuiSubCategoria(String categoria) {
//		Boolean possuiSubCategoria = null;
//		if(categoriaPossuiSubCategoria.containsKey(categoria)) {
//			possuiSubCategoria = categoriaPossuiSubCategoria.get(categoria);
//		} else {
//			CategoriaProp categoriaProp = new CategoriaProp(categoria);
//
//			if(categoriaProp.getSubCategorias()) {
//				possuiSubCategoria = true;
//			} else {
//				possuiSubCategoria = false;
//			}
//
//			categoriaPossuiSubCategoria.put(categoria, possuiSubCategoria);
//		}
//		return possuiSubCategoria;
//	}
	
	public List listarArtigoSiteMap(CategoriaArtigo categoria) {
		java.sql.Date hoje = new java.sql.Date(System.currentTimeMillis());
		
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("_.categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.id", categoria.getId()));
		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", hoje));
		dc.add(Restrictions.ge("_.dataExpiracao", hoje));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("_.enderecoUrl"), "enderecoUrl");
		projectionList.add(Projections.property("_.subCategoria"), "subCategoria");

		dc.setProjection(projectionList);

		dc.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		return hibernateUtil.list(dc);
	}

	public static List<Map<String, Object>> agruparArtigosPorData(List artigos) {
		List<Map<String, Object>> datasArtigos = new ArrayList<Map<String, Object>>();

		for (Iterator iterator = artigos.iterator(); iterator.hasNext();) {
			Map<String, Object> a = (Map<String, Object>) iterator.next();

			Date dataPublicacao = (Date) a.get("dataPublicacao");
			String dataPublicacaoString = Uteis.formatarDataHora(dataPublicacao, "dd/MM/yyyy");

			Map<String, Object> dataArtigo = null;

			for (Map<String, Object> dataArtigoMap : datasArtigos) {
				if(((String)dataArtigoMap.get("dataPublicacaoString")).equals(dataPublicacaoString)) {
					dataArtigo = dataArtigoMap;
				}
			}

			if(dataArtigo == null) {
				dataArtigo = new HashMap<String, Object>();
				dataArtigo.put("dataPublicacaoString", dataPublicacaoString);
				dataArtigo.put("artigos", new ArrayList());

				datasArtigos.add(dataArtigo);
			}

			List artigosData = (List) dataArtigo.get("artigos");
			artigosData.add(a);
		}

		return datasArtigos;
	}

	public Integer qtdComentariosSite(Artigo artigo) {

		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));
		dc.add(Restrictions.eq("status", "Publicado"));

		return hibernateUtil.consultarQtd(dc).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Comentario> listarComentariosSite(Artigo artigo, Integer qtd, String colunas) {

		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));
		dc.add(Restrictions.eq("status", "Publicado"));
		dc.addOrder(Order.asc("data"));

		return hibernateUtil.listar(dc, qtd, 0, colunas);
	}

//	public Long listarPrimeiraSubCategoria(String categoria){
//		String hql = "select min(s.id) from ilion.gc.negocio.SubCategoriaArtigo as s where s.categoriaArtigo.nome = '"+categoria+"'";
//		return (Long) hibernateUtil.createQueryUniqueResult(hql);			
//	}

	public ValueList listarVLHSite(String nomeCategoria, boolean somenteDestaque, String orderBy, ValueListInfo valueListInfo) throws Exception {		
		Date hoje = new Date();

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Artigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.nome", nomeCategoria))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", hoje))
				.add(Restrictions.ge("dataExpiracao", hoje));

		if(somenteDestaque)
			detachedCriteria.add(Restrictions.eq("destaque", Boolean.TRUE));
		
		Order order = hibernateUtil.obterOrderBy(orderBy);
		
		return hibernateUtil.consultarValueList(detachedCriteria, order, valueListInfo);
	}
	
	@Autowired
	UteisGC uteisGC;
	
	@SuppressWarnings("unchecked")
	public List<Artigo> listarArtigoTodosParaIndexarLuceneSite(Integer maxResult, Integer firstResult) {
//		List<String> categorias = uteisGC.listarCategoriasSiteMap();
//		
//		if(categorias == null || categorias.isEmpty()) {
//			return new ArrayList<Artigo>();
//		}
//		
//		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
//		dc.createAlias("categoriaArtigo", "c");
//		dc.add(Restrictions.in("c.nome", categorias));
//		dc.add(Restrictions.eq("status", "Publicado"));
//		dc.add(Restrictions.le("dataPublicacao", new Date()));
//		dc.add(Restrictions.ge("dataExpiracao", new Date()));
//		
//		return hibernateUtil.findAll(dc, maxResult, firstResult, null);
		return Collections.emptyList();
	}
}