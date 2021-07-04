package ilion.gc.categoria.negocio;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.admin.negocio.Perfil;
import ilion.admin.negocio.PerfilNegocio;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.contexto.CacheFilter;
import ilion.util.persistencia.HibernateUtil;

@Service
public class SubCategoriaArtigoNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private PerfilNegocio perfilNegocio;
	
	@Autowired
	ArquivoNegocio arquivoNegocio;
	
	public SubCategoriaArtigo consultarPorId(Long id) {
		return (SubCategoriaArtigo) hibernateUtil.findById(SubCategoriaArtigo.class, id);
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
	
	@Cacheable("subcategorias.por.categoria.ilionnet")
	public List<Map<String, Object>> listarSubCategoriasArtigo(CategoriaArtigo categoriaArtigo, String ordem) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" categoriaartigo_id, id, nome, posicao, status, qtd_artigos.qtd ");
		sb.append(" from gcsubcategoriaartigo ");
		sb.append(" left join ( ");
		sb.append(" 	select count(subcategoria_id) as qtd, subcategoria_id "); 
		sb.append(" 	from gcartigo ");
		sb.append(" 	group by subcategoria_id ");
		sb.append(" 	) as qtd_artigos on qtd_artigos.subcategoria_id=gcsubcategoriaartigo.id ");
		sb.append(" where categoriaartigo_id=").append(categoriaArtigo.getId());
		sb.append(" order by ").append(ordem);
		
		return hibernateUtil.listSQL(sb.toString());
	}
	
	public List<GrupoVH> listarGruposComCategoriasDoPerfil(Perfil perfil, String site) {
		
		Perfil perfilAux = perfilNegocio.consultarPorIdComCategorias(perfil.getId());
		
		List<CategoriaArtigo> categoriaArtigos = perfilAux.getCategorias();
		
		List<GrupoVH> grupos = GrupoVH.from(categoriaArtigos, site);
		
		return grupos;
	}
	
	public Integer consultarUltimaPosicaoSubCategoria(CategoriaArtigo categoriaArtigo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SubCategoriaArtigo.class)
		.add(Restrictions.eq("categoriaArtigo.id", categoriaArtigo.getId()))
		.setProjection(Projections.max("posicao"));

		return (Integer) hibernateUtil.uniqueResult(detachedCriteria);
	}

	public void validarSubCategoria(
			SubCategoriaArtigo subCategoria,
			BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome não pode ser vazio!");

		String enderecoUrl = StringUtil.ajustarTextoParaUrl(subCategoria.getNome());
		if( existeEnderecoUrl(subCategoria, enderecoUrl) ) {
			bindingResult.rejectValue("nome", "", "Já existe uma subcategoria nesta categoria com este nome.");
		}

	}
	
	private Boolean existeEnderecoUrl(SubCategoriaArtigo subCategoriaArtigo, String enderecoUrl) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(SubCategoriaArtigo.class);
		dc.add(Restrictions.eq("enderecoUrl", enderecoUrl));
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.id", subCategoriaArtigo.getCategoriaArtigo().getId()));

		if(subCategoriaArtigo.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", subCategoriaArtigo.getId())));
		}

		return hibernateUtil.possuiRegistros(dc);
	}
	
	@Transactional
	@CacheEvict(value="subcategorias.por.categoria.ilionnet",allEntries=true)
	public void incluirAtualizar(SubCategoriaArtigo subCategoria) {
		
		subCategoria.setEnderecoUrl(StringUtil.ajustarTextoParaUrl(subCategoria.getNome()));
		
		if(subCategoria.getId() == null) {
//			subCategoriaArtigo.setCriadoPor(usuario.getNome());
//			subCategoriaArtigo.setDataCriacao(new Date());
			
			String codControle = subCategoria.getCodControle();
			
			subCategoria = (SubCategoriaArtigo) inserir(subCategoria);
			
			arquivoNegocio.vincularArquivos(SubCategoriaArtigo.class, subCategoria.getId(), codControle);
		}
		
		String conteudoInfo = UteisGC.conteudoInfo(subCategoria, SubCategoriaArtigo.class);
		subCategoria.setConteudoInfo(conteudoInfo);

		atualizar(subCategoria);

		CacheFilter.limparCache();
		
	}
}