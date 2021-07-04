package ilion.gc.categoria.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.gc.taglibs.ArtigoParamsVO;
import ilion.util.persistencia.HibernateUtil;

@Service
public class SubCategoriaArtigoSiteNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Cacheable("subcategoria.lista.site")
	public List listarSubCategoriaSite(ArtigoParamsVO artigoParamsVO) {
		
		DetachedCriteria dcSubquery = DetachedCriteria.forClass(Artigo.class);
		dcSubquery.createAlias("categoriaArtigo", "c");
		dcSubquery.add(Restrictions.eq("c.site", artigoParamsVO.getSite()));
		dcSubquery.add(Restrictions.eq("c.nome", artigoParamsVO.getCategoria()));
		dcSubquery.add(Restrictions.eq("status", "Publicado"));
		dcSubquery.add(Restrictions.le("dataPublicacao", artigoParamsVO.getData()));
		dcSubquery.add(Restrictions.ge("dataExpiracao", artigoParamsVO.getData()));
		dcSubquery.createAlias("subCategoria", "s");
		dcSubquery.setProjection(Projections.property("s.id"));
		
		DetachedCriteria dc = DetachedCriteria.forClass(SubCategoriaArtigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.site", artigoParamsVO.getSite()))
				.add(Restrictions.eq("c.nome", artigoParamsVO.getCategoria()))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", artigoParamsVO.getData()))
				.add(Restrictions.ge("dataExpiracao", artigoParamsVO.getData()))
				.add(Subqueries.propertyIn("id", dcSubquery));
		
		Order order = hibernateUtil.obterOrderBy(artigoParamsVO.getOrder());
		
		dc.addOrder(order);
		
		return hibernateUtil.listar(dc, null, null, artigoParamsVO.getColunas());
	}
	
	@Cacheable("ultimas.subcategoria.lista.site")
	public List listarUltimasSubCategoriasSite(ArtigoParamsVO artigoParamsVO) {
		
		DetachedCriteria dcSubquery = DetachedCriteria.forClass(Artigo.class);
		dcSubquery.createAlias("categoriaArtigo", "c");
		dcSubquery.add(Restrictions.eq("c.site", artigoParamsVO.getSite()));
		dcSubquery.add(Restrictions.eq("c.nome", artigoParamsVO.getCategoria()));
		dcSubquery.add(Restrictions.eq("status", "Publicado"));
		dcSubquery.add(Restrictions.le("dataPublicacao", artigoParamsVO.getData()));
		dcSubquery.add(Restrictions.ge("dataExpiracao", artigoParamsVO.getData()));
		dcSubquery.addOrder(Order.desc("dataPublicacao"));
		dcSubquery.createAlias("subCategoria", "s");
		dcSubquery.setProjection(Projections.property("s.id"));
		
		DetachedCriteria dc = DetachedCriteria.forClass(SubCategoriaArtigo.class)
				.createAlias("categoriaArtigo", "c")
				.add(Restrictions.eq("c.site", artigoParamsVO.getSite()))
				.add(Restrictions.eq("c.nome", artigoParamsVO.getCategoria()))
				.add(Restrictions.eq("status", "Publicado"))
				.add(Restrictions.le("dataPublicacao", artigoParamsVO.getData()))
				.add(Restrictions.ge("dataExpiracao", artigoParamsVO.getData()))
				.add(Subqueries.propertyIn("id", dcSubquery));
		
		return hibernateUtil.listar(dc, artigoParamsVO.getMaxResults(), artigoParamsVO.getFirstResult(), artigoParamsVO.getColunas());
	}
	
}