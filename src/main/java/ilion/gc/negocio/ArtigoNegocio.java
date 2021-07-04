package ilion.gc.negocio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.admin.negocio.Usuario;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.contato.controller.ContatoVH;
import ilion.contato.negocio.ContatoNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.CacheFilter;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
public class ArtigoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;
	
	@Autowired
	private ContatoNegocio contatoNegocio;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}
	
	@Transactional(readOnly=true)
	public Artigo consultarPorIdParaEditar(Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		dc.add(Restrictions.eq("id", id));
		Artigo artigo = (Artigo) hibernateUtil.uniqueResult(dc);
		
		Hibernate.initialize(artigo.getTopicos());
		
		return artigo;
	}

	public Object inserir(Object object) {
		return hibernateUtil.save(object);
	}
	
	public Serializable inserirRetornarId(Object object) {
		return hibernateUtil.saveReturnId(object);
	}

	public void atualizar(Object object) {
		hibernateUtil.update(object);
	}

	@Transactional
	public void excluir(Object object) {
		hibernateUtil.delete(object);
	}
	
	public Integer consultarUltimaPosicaoArtigo(Long idCategoria, Long idSubCategoria) {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);
		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.id", idCategoria));

		if(idSubCategoria != null) {
			dc.createAlias("subCategoria", "s");
			dc.add(Restrictions.eq("s.id", idSubCategoria));
		}

		dc.setProjection(Projections.max("posicao"));

		return (Integer) hibernateUtil.uniqueResult(dc);
	}
	
	public void validarArtigo(String acao, Artigo artigo, BindingResult bindingResult) {
		if( acao != null) {
			acao = acao.trim();
		}

		if( ! "Salvar".equalsIgnoreCase(acao)) {
			bindingResult.reject("", "");
			return;
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "titulo", "", "Título não pode ser vazio!");

		String enderecoUrl = StringUtil.ajustarTextoParaUrl(artigo.getTitulo());
		if( existeEnderecoUrl(artigo, enderecoUrl) ) {
			bindingResult.rejectValue("titulo", "", "Já existe um artigo nesta categoria com este título.");
		}
		
		if( artigo.getCategoriaArtigo().getSubCategoriaConfig().getPossuiSubCategorias() ) {
			ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "subCategoria.id", "", "Selecione uma subcategoria!");
		}
	}
	
	public void verificarAdicionarArtigoConteudo(Artigo artigo) {
		if(artigo.getArtigoConteudo() != null) {
			Boolean possuiArquivos = 
				arquivoNegocio.possuiArquivos(ArtigoConteudo.class.getSimpleName(), artigo.getArtigoConteudo().getId(), artigo.getArtigoConteudo().getCodControle());

			if(possuiArquivos || 
					(artigo.getArtigoConteudo().getTexto() != null && 
							artigo.getArtigoConteudo().getTexto().length() != 0)) {

				Boolean alteracao = false;
				for (ArtigoConteudo artigoConteudo : artigo.getArtigoConteudos()) {
					String codControleLaco = artigoConteudo.getCodControle();
					String codControleEditando = artigo.getArtigoConteudo().getCodControle();
					if(codControleLaco.equals(codControleEditando)) {
						artigoConteudo = artigo.getArtigoConteudo();
						alteracao = true;
					}
				}

				if( ! alteracao) {
					artigo.getArtigoConteudos().add(artigo.getArtigoConteudo());
				}
			}
		}
	}
	
	@Transactional
	@CacheEvict(value={
		"artigos.site",
		"artigos.com.arquivos.site",
		"artigo.conteudos.lista"
	},allEntries=true)
	public Artigo incluirArtigo(Artigo artigo, Usuario usuario) {
		Boolean possuiPermissaoPublicar = usuario.getPermissoes().contains("artigo-publicar.sp");
		if(possuiPermissaoPublicar == null || !possuiPermissaoPublicar) {
			artigo.setStatus("Em Aprovação");
		}
		
		verificarAdicionarArtigoConteudo(artigo);
		
		artigo.setEnderecoUrl(StringUtil.ajustarTextoParaUrl(artigo.getTitulo()));
		
		artigo.setSecoes(Uteis.formarXmlComLista(artigo.getSecoesList(), "s"));
		
		if(artigo.getHoraPublicacaoInt() != null) {
			Calendar dataPublicacao = Calendar.getInstance();
			dataPublicacao.setTime(artigo.getDataPublicacao());
			
			dataPublicacao.set(Calendar.HOUR_OF_DAY, artigo.getHoraPublicacaoInt());
			dataPublicacao.set(Calendar.MINUTE, 0);
			
			artigo.setDataPublicacao(dataPublicacao.getTime());
		}
		
		List<ArtigoConteudo> artigoConteudos = artigo.getArtigoConteudos();
		
		if(artigo.getId() == null) {
			String codControle = artigo.getCodControle();
			
			artigo = (Artigo) inserir(artigo);
			
			arquivoNegocio.vincularArquivos(Artigo.class, artigo.getId(), codControle);
		}
		
		for (ArtigoConteudo artigoConteudo : artigoConteudos) {
			if(artigoConteudo.getId() == null) {
				String codControle = artigoConteudo.getCodControle();
				
				artigoConteudo.setArtigo(artigo);
				
				Serializable idArtigoConteudo = inserirRetornarId(artigoConteudo);
				
				arquivoNegocio.vincularArquivos(ArtigoConteudo.class, idArtigoConteudo, codControle);
			}
			
			String conteudoInfo = UteisGC.conteudoInfo(artigoConteudo, ArtigoConteudo.class);
			artigoConteudo.setConteudoInfo(conteudoInfo);
			
			atualizar(artigoConteudo);
		}
		
		String conteudoInfo = UteisGC.conteudoInfo(artigo, Artigo.class);
		conteudoInfo += UteisGC.artigoConteudoInfo(artigoConteudos);
		artigo.setConteudoInfo(conteudoInfo);
		
		atualizar(artigo);
		
//		LuceneNegocio luceneNegocio = LuceneNegocioFactory.getImpl(artigo);
//		if( "Publicado".equals(artigo.getStatus()) ) {
//			luceneNegocio.indexar(artigo);
//		} else {
//			luceneNegocio.desindexar(artigo);
//		}
		
		CacheFilter.limparCache();
		
		return artigo;
	}
	
	@Transactional
	public void excluirArtigo(Artigo artigo) {
		List<String> idsArtigoConteudos = listarIdsArtigoConteudo(artigo);
		for (String idArtigoConteudo : idsArtigoConteudos) {
			
			arquivoNegocio.excluirArquivos(ArtigoConteudo.class.getSimpleName(), idArtigoConteudo);
			
		}
		
		excluirArtigosConteudo(artigo);
		
		arquivoNegocio.excluirArquivos(Artigo.class.getSimpleName(), artigo.getId());
		
		hibernateUtil.delete(artigo);
		
		//artigoLuceneNegocio.desindexar(artigo);
		
		String status = artigo.getStatus();
		if("Publicado".equals(status)) {
			CacheFilter.limparCache();
		}
	}
	
	private void excluirArtigosConteudo(Artigo artigo) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM ").append(ArtigoConteudo.class.getName());
		sb.append(" WHERE artigo.id = '").append(artigo.getId()).append("' ");

		hibernateUtil.updateHQL(sb.toString());
		
	}
	
	public Boolean existeEnderecoUrl(Artigo artigo, String enderecoUrl) {
		DetachedCriteria dc = DetachedCriteria.forClass(Artigo.class);

		dc.add(Restrictions.eq("enderecoUrl", enderecoUrl));

		dc.createAlias("categoriaArtigo", "c");
		dc.add(Restrictions.eq("c.nome", artigo.getCategoriaArtigo().getNome()));

		if(artigo.getSubCategoria() != null) {
			dc.createAlias("subCategoria", "s");
			dc.add(Restrictions.eq("s.id", artigo.getSubCategoria().getId()));
		}

		if(artigo.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", artigo.getId())));
		}

		return hibernateUtil.possuiRegistros(dc);
	}

	@SuppressWarnings("unchecked")
	public List<ArtigoConteudo> listarArtigoConteudo(Artigo artigo, String layout) {
		if(artigo.getId() == null) {
			throw new IllegalArgumentException("artigo.id é nulo");
		}
		
		DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);

		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));

		if(layout != null) {
			dc.add(Restrictions.eq("layout", layout));
		}
		
		dc.addOrder(Order.asc("posicao"));

		return (List<ArtigoConteudo>) hibernateUtil.list(dc);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarIdsArtigoConteudo(Artigo artigo) {
		if(artigo.getId() == null) {
			throw new IllegalArgumentException("artigo.id é nulo");
		}
		
		DetachedCriteria dc = DetachedCriteria.forClass(ArtigoConteudo.class);

		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));
		
		dc.addOrder(Order.asc("posicao"));
		
		dc.setProjection(Projections.property("id"));

		return (List<String>) hibernateUtil.list(dc);
	}
	
	public Boolean possuiComentarios(Artigo artigo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));
		
		return hibernateUtil.possuiRegistros(dc);
	}
	
	public Integer qtdComentarios(Artigo artigo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));
		
		return hibernateUtil.consultarQtd(dc).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> listarComentarios(Artigo artigo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", artigo.getId()));
		dc.addOrder(Order.asc("data"));
		
		return (List<Comentario>) hibernateUtil.list(dc);
	}
	
	public ValueList buscaComentarios(VLHForm vlhForm, ValueListInfo valueListInfo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Comentario.class);
		
		dc.createAlias("artigo", "a");
		dc.add(Restrictions.eq("a.id", vlhForm.getIdArtigo()));
		
		return hibernateUtil.consultarValueList(dc, Order.asc("data"), valueListInfo);
	}
	
	@Transactional
	public void criarDepoimento(HttpServletRequest request) throws Exception {
		
		String nome = (String) request.getParameter("nome");
		String email = (String) request.getParameter("email");
		String curso = (String) request.getParameter("curso");
		String mensagem = (String) request.getParameter("mensagem");
		//String autorizado = (String) request.getParameter("autorizado");
		
		Artigo artigo = new Artigo();

		CategoriaArtigo categoriaArtigo = (CategoriaArtigo) categoriaArtigoNegocio.consultarPorSiteENome("emagis", "depoimentos");
		artigo.setCategoriaArtigo(categoriaArtigo);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		artigo.setDataPublicacao(c.getTime());
		c.add(Calendar.YEAR, 5);
		artigo.setDataExpiracao(c.getTime());

		artigo.setTitulo("Depoimento de "+nome.toString()+" - "+System.currentTimeMillis());
		artigo.setEmail(email);
		artigo.setLink(nome);
		artigo.setYoutube(curso);
		//artigo.setAutorizado(autorizado != null && "true".equals(autorizado) ? true : false);
		
		Integer posicao = consultarUltimaPosicaoArtigo(categoriaArtigo.getId(), null);
		if(posicao == null)
			posicao = 0;
		posicao++;
		artigo.setPosicao(posicao);

		artigo.setStatus("Em Aprovação");

		artigo.setDataCriacao(artigo.getDataPublicacao());
		artigo.setCriadoPor(nome);
		artigo.setAlteradoPor(nome);

		artigo.setEnderecoUrl(StringUtil.ajustarTextoParaUrl(artigo.getTitulo()));
		
		artigo = (Artigo) hibernateUtil.save(artigo);

		ArtigoConteudo texto = new ArtigoConteudo();
		texto.setArtigo(artigo);
		texto.setLayout("texto");
		texto.setPosicao((byte)1);
		texto.setTexto("<p>"+mensagem+"</p>");
		hibernateUtil.save(texto);
		
		ContatoVH contatoVH = new ContatoVH();
		contatoVH.setNome(nome);
		contatoVH.setEmail(email);
		contatoVH.setMensagem(mensagem);
		contatoVH.setGrupo("Depoimentos");
		contatoVH.setCadastrarComentario(true);
		contatoVH.setPermissaoEmail(true);
		contatoVH.setEnviarEmail(true);
		
		contatoNegocio.cadastraContatoSite(contatoVH);
		
	}
}