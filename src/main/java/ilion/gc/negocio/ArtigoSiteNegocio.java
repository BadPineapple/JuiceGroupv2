package ilion.gc.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.taglibs.html.ArquivosCentroHtml;
import ilion.arquivo.taglibs.html.ArquivosInferiorHtml;
import ilion.arquivo.taglibs.html.ArquivosLateralDireitaHtml;
import ilion.arquivo.taglibs.html.ArquivosLateralEsquerdaHtml;
import ilion.gc.taglibs.ArtigoConsultaParamsVO;
import ilion.gc.taglibs.ArtigoParamsVO;
import ilion.gc.taglibs.ArtigosPorTopicosParamsVO;
import ilion.util.Uteis;
import ilion.util.ValueListInfo;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
public class ArtigoSiteNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	public Artigo consultarArtigoEnderecoUrl(ArtigoConsultaParamsVO artigoConsultaParamsVO) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.site", artigoConsultaParamsVO.getSite()));
		dc.add(Restrictions.eq("c.nome", artigoConsultaParamsVO.getCategoria()));
		
		if( ! Uteis.ehNuloOuVazio(artigoConsultaParamsVO.getSubCategoriaUrl()) ) {
			dc.createAlias("subCategoria", "s");
			dc.add(Restrictions.eq("s.enderecoUrl", artigoConsultaParamsVO.getSubCategoriaUrl()));
		}
		
		dc.add(Restrictions.eq("enderecoUrl", artigoConsultaParamsVO.getArtigoUrl()));
		
		dc.add(Restrictions.eq("status", "Publicado"));
		dc.add(Restrictions.le("dataPublicacao", artigoConsultaParamsVO.getData()));
		dc.add(Restrictions.ge("dataExpiracao", artigoConsultaParamsVO.getData()));
		
		if( artigoConsultaParamsVO.getComTopicos() ) {
			dc.setFetchMode("topicos", FetchMode.JOIN);
		}
		
		return (Artigo) hibernateUtil.uniqueResult(dc);
	}
	
	@Cacheable("artigos.com.arquivos.site")
	public List<Arquivo> listarArquivosDosArtigos(ArtigoParamsVO artigoParamsVO) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select arquivo.*, ");
		sql.append(" gcartigo.titulo, ");
		sql.append(" gcartigo.link as artigolink, ");
		sql.append(" gcartigo.linktarget as artigolinktarget ");
		sql.append(" from arquivo  ");
		sql.append(" join gcartigo on cast(gcartigo.id as text)=arquivo.idobjeto ");
		sql.append(" where nomeclasse='Artigo'  ");
		sql.append(" and idobjeto in (select cast(gcartigo.id as text) as id "); 
		sql.append(" 		from gcartigo  ");
		sql.append(" 		join gccategoriartigo on gccategoriartigo.id=gcartigo.categoriaartigo_id ");
		sql.append(" 		where gccategoriartigo.site='").append(artigoParamsVO.getSite()).append("' and ");
		sql.append("			gccategoriartigo.nome='").append(artigoParamsVO.getCategoria()).append("' and ");
		sql.append("			gcartigo.status='Publicado' and ");
		sql.append("			gcartigo.datapublicacao <= '").append(Uteis.formatarDataHora(artigoParamsVO.getData(), "yyyy-MM-dd HH:mm")).append("' and ");
		sql.append("			gcartigo.dataexpiracao >= '").append(Uteis.formatarDataHora(artigoParamsVO.getData(), "yyyy-MM-dd HH:mm")).append("' ");
		sql.append(" 		order by posicao) ");
		sql.append(" and arquivo.naopublicado=false ");
		
		if( ! Uteis.ehNuloOuVazio(artigoParamsVO.getLayout()) ) {
			sql.append(" and arquivo.layout='").append(artigoParamsVO.getLayout()).append("' ");
		}
		
		sql.append(" order by gcartigo.").append(artigoParamsVO.getOrder());
		
		if( artigoParamsVO.getMaxResults() != null ) {
			sql.append(" limit ").append(artigoParamsVO.getMaxResults()).append(" offset 0 ");
		}
		
		List<Map<String, Object>> arquivosMap = hibernateUtil.listSQL(sql.toString());
		
		List<Arquivo> arquivos = new ArrayList<Arquivo>();
		
		for (Map<String, Object> map : arquivosMap) {
			
			Arquivo arquivo = Arquivo.fromMap(map);
			
			arquivos.add(arquivo);
			
		}
		
		return arquivos;
	}
	
	@Cacheable("artigos.site")
	public List listarArtigosSite(ArtigoParamsVO artigoParamsVO) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("_.categoriaArtigo", "c");
		
		dc.add(Restrictions.eq("c.site", artigoParamsVO.getSite()));
		
		if( artigoParamsVO.getCategoria().contains(";") ) {
			List<String> categorias = Uteis.formarListaComString(artigoParamsVO.getCategoria(), ";");
			dc.add(Restrictions.in("c.nome", categorias));
		} else {
			dc.add(Restrictions.eq("c.nome", artigoParamsVO.getCategoria()));
		}
		
		if( ! Uteis.ehNuloOuVazio(artigoParamsVO.getSubCategoriaUrl()) ) {
			dc.createAlias("_.subCategoria", "s");
			dc.add(Restrictions.eq("s.enderecoUrl", artigoParamsVO.getSubCategoriaUrl()));
		}
		
		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", artigoParamsVO.getData()));
		dc.add(Restrictions.ge("_.dataExpiracao", artigoParamsVO.getData()));
		
		if( ! Uteis.ehNuloOuVazio(artigoParamsVO.getOrder()) ) {
			Order order = hibernateUtil.obterOrderBy(artigoParamsVO.getOrder());
			dc.addOrder(order);
		}
		
		if(artigoParamsVO.getSecoes() != null) {
			if(artigoParamsVO.getSecoes().length() != 0) {
				if( ! "todas".equalsIgnoreCase(artigoParamsVO.getSecoes()) ) {
					dc.add(Restrictions.like("_.secoes", "%>"+artigoParamsVO.getSecoes()+"<%"));
				}
			} else if(artigoParamsVO.getSecoes().length() == 0) {
				dc.add(Restrictions.or(Restrictions.eq("_.secoes", ""), Restrictions.isNull("_.secoes")));
			}
		}
		
		List artigos = hibernateUtil.listar(dc, artigoParamsVO.getMaxResults(), artigoParamsVO.getFirstResult(), artigoParamsVO.getColunas());
		
		return artigos;
	}
	
	@Cacheable("artigos.por.topicos.site")
	public List listarArtigosPorTopicosSite(ArtigosPorTopicosParamsVO params) {
		
		if( params.getTopicos() == null || params.getTopicos().isEmpty() ) {
			return Collections.emptyList();
		}
		
		List<Long> idsTopicos = params.getTopicosIds();
		String queryInIdsTopicos = Uteis.formatarStringQueryIn(idsTopicos);
		
		String sql = " select artigo_id "
				+ " from gcartigo_x_topico "
				+ " where "
				+ " artigo_id <> "+params.getIdArtigoAtual()+" and "
				+ " topico_id in ("+queryInIdsTopicos+") ";
		
		List idsArtigos = hibernateUtil.listarSQLUniqueColumn(sql);
		
		if( idsArtigos == null || idsArtigos.isEmpty() ) {
			return Collections.emptyList();
		}
		
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("_.categoriaArtigo", "c");
		
		dc.add(Restrictions.eq("c.site", params.getSite()));
		
		dc.add(Restrictions.eq("c.nome", params.getCategoria()));
		
		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", params.getData()));
		dc.add(Restrictions.ge("_.dataExpiracao", params.getData()));
		
		if( ! Uteis.ehNuloOuVazio(params.getOrder()) ) {
			Order order = hibernateUtil.obterOrderBy(params.getOrder());
			dc.addOrder(order);
		}
		
		dc.add(Restrictions.in("_.id", idsArtigos));
		
		List artigos = hibernateUtil.listar(dc, params.getMaxResults(), params.getFirstResult(), params.getColunas());
		
		return artigos;
	}
	
	
	public ValueList listarArtigosSite(ArtigoParamsVO artigoParamsVO, ValueListInfo valueListInfo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class, "_");
		dc.createAlias("_.categoriaArtigo", "c");
		
		dc.add(Restrictions.eq("c.site", artigoParamsVO.getSite()));
		
		if( artigoParamsVO.getCategoria().contains(";") ) {
			List<String> categorias = Uteis.formarListaComString(artigoParamsVO.getCategoria(), ";");
			dc.add(Restrictions.in("c.nome", categorias));
		} else {
			dc.add(Restrictions.eq("c.nome", artigoParamsVO.getCategoria()));
		}
		
		if( ! Uteis.ehNuloOuVazio(artigoParamsVO.getSubCategoriaUrl()) ) {
			dc.createAlias("_.subCategoria", "s");
			dc.add(Restrictions.eq("s.enderecoUrl", artigoParamsVO.getSubCategoriaUrl()));
		}
		
		dc.add(Restrictions.eq("_.status", "Publicado"));
		dc.add(Restrictions.le("_.dataPublicacao", artigoParamsVO.getData()));
		dc.add(Restrictions.ge("_.dataExpiracao", artigoParamsVO.getData()));
		
		if(artigoParamsVO.getSecoes() != null) {
			if(artigoParamsVO.getSecoes().length() != 0) {
				if( ! "todas".equalsIgnoreCase(artigoParamsVO.getSecoes()) ) {
					dc.add(Restrictions.like("_.secoes", "%>"+artigoParamsVO.getSecoes()+"<%"));
				}
			} else if(artigoParamsVO.getSecoes().length() == 0) {
				dc.add(Restrictions.or(Restrictions.eq("_.secoes", ""), Restrictions.isNull("_.secoes")));
			}
		}
		
		Order order = hibernateUtil.obterOrderBy(artigoParamsVO.getOrder());
		
		return hibernateUtil.consultarValueList(dc, order, artigoParamsVO.getColunas(), valueListInfo);
	}
	
//	@Cacheable("artigo.conteudos.lista")
	public List<ArtigoConteudo> listarArtigoConteudoIdTextoSite(Artigo artigo, String layout) {
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
		
		ProjectionList projectionList = Projections.projectionList();
		
		projectionList.add(Projections.property("id").as("id"));
		projectionList.add(Projections.property("texto").as("texto"));
		
		dc.setProjection(projectionList);
		
		dc.setResultTransformer(Transformers.aliasToBean(ArtigoConteudo.class));

		return (List<ArtigoConteudo>) hibernateUtil.list(dc);
	}
	
	public void artigoConteudoImprimir(ArtigoConteudo artigoConteudo, Appendable out) throws IOException {
		
		List<Arquivo> arquivos = 
				arquivoNegocio.listarArquivosPublicados(
						ArtigoConteudo.class.getSimpleName(), 
						artigoConteudo.getId(), 
						artigoConteudo.getCodControle());
		
		ArquivosCentroHtml.imprimir(arquivos, out);
		
		ArquivosLateralDireitaHtml.imprimir(arquivos, out);
		
		ArquivosLateralEsquerdaHtml.imprimir(arquivos, out);
		
		String texto = artigoConteudo.getTexto();
		
		out.append(texto);
		
		ArquivosInferiorHtml.imprimir(arquivos, out);
	}
	
	@CacheEvict(value={
		"gc.grupos.por.perfil",
		"subcategorias.por.categoria.ilionnet",
		"artigos.site",
		"artigos.por.topicos.site",
		"artigos.com.arquivos.site",
		"imagens.site",
		"arquivos.publicados", 
		"artigo.conteudos.lista", 
		"subcategoria.lista.site",
		"ultimas.subcategoria.lista.site"
		},allEntries=true)
	public void limparCache() {
	}
}