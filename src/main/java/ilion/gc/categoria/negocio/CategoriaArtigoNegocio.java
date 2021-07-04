package ilion.gc.categoria.negocio;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ilion.admin.negocio.Perfil;
import ilion.admin.negocio.PerfilNegocio;
import ilion.util.StatusEnum;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class CategoriaArtigoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private PerfilNegocio perfilNegocio;
	
	@Cacheable("gc.categoria.por.id")
	public CategoriaArtigo consultarPorId(Long id) {
		return (CategoriaArtigo) hibernateUtil.findById(CategoriaArtigo.class, id);
	}
	
	@Cacheable("gc.categoria.por.site.nome")
	public CategoriaArtigo consultarPorSiteENome(String site, String nomeCategoria) {
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);
		
		dc.add(Restrictions.eq("site", site));
		dc.add(Restrictions.eq("nome", nomeCategoria));
		
		return (CategoriaArtigo) hibernateUtil.uniqueResult(dc);
	}
	
	@Cacheable("gc.categorias.publicadas")
	public List<CategoriaArtigo> listarCategoriasPublicadas() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);
		
		dc.add(Restrictions.eq("statusEnum", StatusEnum.PUBLICADO));
		
		dc.addOrder(Order.asc("site"));
		dc.addOrder(Order.asc("grupo"));
		dc.addOrder(Order.asc("nome"));
		
		return (List<CategoriaArtigo>) hibernateUtil.list(dc);
	}
	
	public List<CategoriaArtigo> listarCategoriasArtigo(String site, String palavraChave) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);
		
		if( ! Uteis.ehNuloOuVazio(site) ) {
			dc.add(Restrictions.eq("site", site));
		}
		
		if( ! Uteis.ehNuloOuVazio(palavraChave) ) {
			String like = "%"+palavraChave+"%";
			
			Criterion c = Restrictions.ilike("grupo", like);
			c = Restrictions.or(c, Restrictions.ilike("nome", like));
			
			dc.add(c);
		}
		
		dc.addOrder(Order.asc("site"));
		dc.addOrder(Order.asc("grupo"));
		dc.addOrder(Order.asc("nome"));
		
		return (List<CategoriaArtigo>) hibernateUtil.list(dc);
	}
	
	@Cacheable("gc.grupos.por.perfil")
	public List<GrupoVH> listarGruposComCategoriasDoPerfil(Perfil perfil, String site) {
		
		List<CategoriaArtigo> categoriaArtigos = listarCategoriasPorPerfil(perfil);
		
		List<GrupoVH> grupos = GrupoVH.from(categoriaArtigos, site);
		
		return grupos;
	}
	
	private List<CategoriaArtigo> listarCategoriasPorPerfil(Perfil perfil) {
		
		String sql = " select categoria_id "
				+ " from adminperfil_x_categorias "
				+ " where perfil_id='"+perfil.getId()+"'";
		
		List ids = hibernateUtil.listarSQLUniqueColumn(sql);
		
		if( ids == null || ids.isEmpty() ) {
			return Collections.emptyList();
		}
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);
		
		dc.add(Restrictions.in("id", ids));
		
		dc.add(Restrictions.eq("statusEnum", StatusEnum.PUBLICADO));
		
		dc.addOrder(Order.asc("grupo"));
		dc.addOrder(Order.asc("nome"));
		
		return (List<CategoriaArtigo>) hibernateUtil.list(dc);
	}
	
	@Cacheable("gc.categorias.sitemap")
	public List<CategoriaArtigo> listarCategoriasSiteMap() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);
		dc.add(Restrictions.eq("artigoConfig.possuiSitemap", true));
		dc.addOrder(Order.asc("site"));
		dc.addOrder(Order.asc("nome"));
		
		return (List<CategoriaArtigo>) hibernateUtil.list(dc);
	}
	
	@Transactional
	@CacheEvict(value={
			"gc.categoria.por.id",
			"gc.categoria.por.site.nome",
			"gc.categorias.publicadas",
			"gc.grupos.por.perfil",
			"gc.categorias.sitemap"
			},allEntries=true)
	public void incluirAtualizar(CategoriaArtigo categoriaArtigo) {
		
		Assert.hasText(categoriaArtigo.getSite(), "Site deve ser preenchido.");
		Assert.hasText(categoriaArtigo.getNome(), "Nome deve ser preenchido.");
		Assert.hasText(categoriaArtigo.getGrupo(), "Grupo deve ser preenchido.");
		Assert.notNull(categoriaArtigo.getStatusEnum(), "Status deve ser preenchido.");
		
		if( categoriaJaExistente(categoriaArtigo) ) {
			throw new ValidacaoException("Categoria já existente neste site.");
		}
		
		if( categoriaArtigo.getId() == null ) {
			categoriaArtigo = (CategoriaArtigo) hibernateUtil.save(categoriaArtigo);
		} else {
			hibernateUtil.update(categoriaArtigo);
		}
		
	}
	
	private Boolean categoriaJaExistente(CategoriaArtigo categoriaArtigo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaArtigo.class);
		dc.add(Restrictions.eq("site", categoriaArtigo.getSite()));
		dc.add(Restrictions.eq("nome", categoriaArtigo.getNome()));
		
		if( categoriaArtigo.getId() != null ) {
			dc.add( Restrictions.not( Restrictions.eq("id", categoriaArtigo.getId()) ) );
		}
		
		return hibernateUtil.possuiRegistros(dc);
	}

}