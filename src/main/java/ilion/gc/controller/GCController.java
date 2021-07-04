package ilion.gc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.contato.negocio.ContatoNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.gc.categoria.negocio.SubCategoriaArtigoNegocio;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoNegocio;
import ilion.gc.negocio.Comentario;
import ilion.gc.negocio.Enquete;
import ilion.gc.negocio.GCNegocio;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.CacheFilter;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import net.mlw.vlh.ValueList;

@Controller
@UsuarioLogado
public class GCController {

	static Logger logger = Logger.getLogger(GCController.class);

	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;
	
	@Autowired
	private SubCategoriaArtigoNegocio subCategoriaArtigoNegocio;
	
	@Autowired
	private ContatoNegocio contatoNegocio;
	
	@Autowired
	private GCNegocio gcNegocio;
	
	@Autowired
	private ArtigoNegocio artigoNegocio;

	@Autowired
	private PropNegocio propNegocio;

	private String setarIdioma(HttpServletRequest request) {
		String idioma = (String) request.getSession().getAttribute("idiomaSelecionado");
		if(idioma == null) {
			idioma = "br";
		}

		String idiomaParam = request.getParameter("idioma");
		if(idiomaParam != null && idiomaParam.length() != 0) {
			idioma = idiomaParam;
		}

		request.getSession().setAttribute("idiomaSelecionado", idioma);

		return idioma;
	}
	
	private String setarSite(HttpServletRequest request) {
		String site = (String) request.getSession().getAttribute("siteSelecionado");
		if(site == null) {
			site = propNegocio.findValueById(PropEnum.SITE);
		}
		
		String siteParam = request.getParameter("site");
		if(siteParam != null && siteParam.length() != 0) {
			site = siteParam;
		}
		
		request.getSession().setAttribute("siteSelecionado", site);
		
		return site;
	}

	@RequestMapping("/ilionnet/gc")
	@UsuarioLogado("gc.sp")
	public String gc(HttpServletRequest request) {
		setarIdioma(request);
		
		setarSite(request);
		
		List artigosEmAprovacao = gcNegocio.artigosEmAprovacao();
		request.setAttribute("artigosEmAprovacao", artigosEmAprovacao);
		
		List comentariosEmAprovacao = gcNegocio.comentariosEmAprovacao();
		request.setAttribute("comentariosEmAprovacao", comentariosEmAprovacao);
		
		return "/ilionnet/modulos/gc/gc";
	}

	@RequestMapping("/ilionnet/gc-busca-geral")
	@UsuarioLogado("gc.sp")
	public String gcBuscaGeral(HttpServletRequest request) {
		VLHForm vlhForm = VLHForm.getVLHSession("artigosGeralIlionnet", request);
		
		ValueList valueList = gcNegocio.buscaGeral(vlhForm, Order.desc("dataPublicacao"), new ValueListInfo(vlhForm, 15));

		request.setAttribute("artigos", valueList);

		request.setAttribute("vlhForm", vlhForm);

		return "/ilionnet/modulos/gc/artigo-busca-geral";
	}

	@RequestMapping("/ilionnet/gc-busca")
	@UsuarioLogado("gc.sp")
	public String gcBusca(String site, String nomeCategoria, String idSubCategoria, HttpServletRequest request) {
		
		CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorSiteENome(site, nomeCategoria);
		request.setAttribute("categoriaArtigo", categoriaArtigo);
		
		if( categoriaArtigo == null ) {
			return "redirect:gc.sp?m=categoria-nao-encontrada";
		}
		
//		if( "noticias".equals(nomeCategoria) ) {
//			return "redirect:noticias-busca.sp?site="+propNegocio.findValueById(PropEnum.SITE)+"&nomeCategoria=noticias";
//		}
		
		Boolean possuiSubCategoria = categoriaArtigo.getSubCategoriaConfig().getPossuiSubCategorias() != null && 
				categoriaArtigo.getSubCategoriaConfig().getPossuiSubCategorias();
		
		if(possuiSubCategoria) {
			Long idSubCategoriaLong = Uteis.converterLong(idSubCategoria);
			if(idSubCategoriaLong == null) {
				return subCategoriaLista(site, nomeCategoria, request);
			}
		} else {
			idSubCategoria = "";
		}

		return artigoBusca(categoriaArtigo.getId(), idSubCategoria, request);
	}
	
	@RequestMapping("/ilionnet/subcategorias-busca")
	@UsuarioLogado("gc.sp")
	private String subCategoriaLista(String site, String nomeCategoria, HttpServletRequest request) {
		
		CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorSiteENome(site, nomeCategoria);
		request.setAttribute("categoriaArtigo", categoriaArtigo);
		
		if( categoriaArtigo == null ) {
			return "redirect:gc.sp?m=categoria-nao-encontrada";
		}
		
		List<Map<String, Object>> subCategorias = 
				subCategoriaArtigoNegocio.listarSubCategoriasArtigo(
						categoriaArtigo,
						categoriaArtigo.getSubCategoriaConfig().getOrdem());
		request.setAttribute("subCategorias", subCategorias);
		
		return "/ilionnet/modulos/gc/subcategoria-lista";
	}
	
	@RequestMapping("/ilionnet/artigo-busca")
	@UsuarioLogado("gc.sp")
	private String artigoBusca(Long idCategoria, String idSubCategoria, HttpServletRequest request) {
		
		CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorId(idCategoria);
		request.setAttribute("categoriaArtigo", categoriaArtigo);
		
		if( categoriaArtigo == null ) {
			return "redirect:gc.sp?m=categoria-nao-encontrada";
		}
		
		VLHForm vlhForm = VLHForm.getVLHSession("artigosIlionnet-"+categoriaArtigo.getId()+"-"+idSubCategoria, request);
		
		vlhForm.setIdCategoria(categoriaArtigo.getId());
		
		Long idSubCategoriaLong = Uteis.converterLong(idSubCategoria);
		vlhForm.setIdSubCategoria(idSubCategoriaLong);
		
		if( idSubCategoriaLong != null ) {
			SubCategoriaArtigo subCategoriaArtigo = subCategoriaArtigoNegocio.consultarPorId(idSubCategoriaLong);
			request.setAttribute("subCategoria", subCategoriaArtigo);
		}
		
		ValueList valueList = gcNegocio.busca(
				vlhForm, 
				categoriaArtigo.getArtigoConfig().getOrdem(), 
				new ValueListInfo(vlhForm, 15));

		request.setAttribute("artigos", valueList);
		
		request.setAttribute("vlhForm", vlhForm);

		return "/ilionnet/modulos/gc/artigo-busca";
	}
	
	@RequestMapping("/ilionnet/noticias-busca")
	@UsuarioLogado("gc.sp")
	private String noticiasBusca(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("noticiasIlionnet", request);
		
		CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorSiteENome(propNegocio.findValueById(PropEnum.SITE), "noticias");
		request.setAttribute("categoriaArtigo", categoriaArtigo);
		
		List<Map<String, Object>> subCategorias = 
				subCategoriaArtigoNegocio.listarSubCategoriasArtigo(
						categoriaArtigo,
						categoriaArtigo.getSubCategoriaConfig().getOrdem());
		request.setAttribute("subCategorias", subCategorias);
		
		vlhForm.setIdCategoria(categoriaArtigo.getId());		
		
		ValueList valueList = gcNegocio.busca(
				vlhForm, 
				categoriaArtigo.getArtigoConfig().getOrdem(), 
				new ValueListInfo(vlhForm, 15));
		
		request.setAttribute("artigos", valueList);
		
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/gc/noticias-busca";
	}

//	public String enqueteBusca(HttpServletRequest request) {
//		List enquetes = enqueteNegocio.findAll();
//
//		request.setAttribute("enquetes", enquetes);
//
//		return "/ilionnet/modulos/gc/enquete-lista";
//	}

	@RequestMapping("/ilionnet/artigo-posicao-acima")
	@UsuarioLogado("artigo-form.sp")
	public String artigoPosicaoAcima(String id) {
		Long idArtigo = Uteis.converterLong(id);

		if(idArtigo == null) {
			return "redirect:gc.sp";
		}

		Artigo artigo = (Artigo) gcNegocio.consultar(Artigo.class, idArtigo);
		if(artigo != null) {
			String nomeCategoria = artigo.getCategoriaArtigo().getNome();
			String idSubCategoria = artigo.getSubCategoria() != null ? artigo.getSubCategoria().getId().toString() : "";

			Integer posicao = artigo.getPosicao();
			posicao++;

			artigo.setPosicao(posicao);
			gcNegocio.atualizar(artigo);

			return "redirect:gc-busca.sp?nomeCategoria="+nomeCategoria+"&idSubCategoria="+idSubCategoria;
		}

		return "redirect:gc.sp";
	}

	@RequestMapping("/ilionnet/artigo-posicao-abaixo")
	@UsuarioLogado("artigo-form.sp")
	public String artigoPosicaoAbaixo(String id) {
		Long idArtigo = Uteis.converterLong(id);

		if(idArtigo == null) {
			return "redirect:gc.sp";
		}

		Artigo artigo = (Artigo) gcNegocio.consultar(Artigo.class, idArtigo);
		if(artigo != null) {
			String nomeCategoria = artigo.getCategoriaArtigo().getNome();
			String idSubCategoria = artigo.getSubCategoria() != null ? artigo.getSubCategoria().getId().toString() : "";

			Integer posicao = artigo.getPosicao();
			if(posicao > 1) {
				posicao--;
			}
			artigo.setPosicao(posicao);
			gcNegocio.atualizar(artigo);

			return "redirect:gc-busca.sp?nomeCategoria="+nomeCategoria+"&idSubCategoria="+idSubCategoria;
		}

		return "redirect:gc.sp";
	}

	@RequestMapping("/ilionnet/artigo-excluir")
	@UsuarioLogado("artigo-excluir.sp")
	public String artigoExcluir(String id) {
		Long idArtigo = Uteis.converterLong(id);

		if(idArtigo == null) {
			return "redirect:gc.sp";
		}
		
		try {
			Artigo artigo = (Artigo) gcNegocio.consultar(Artigo.class, idArtigo);
			if(artigo != null) {
				String site = artigo.getCategoriaArtigo().getSite();
				String nomeCategoria = artigo.getCategoriaArtigo().getNome();
				String idSubCategoria = artigo.getSubCategoria() != null ? artigo.getSubCategoria().getId().toString() : "";
				
				if(artigoNegocio.possuiComentarios(artigo)) {
					return "redirect:gc-busca.sp?site="+site+"&nomeCategoria="+nomeCategoria+"&idSubCategoria="+idSubCategoria+"&m=possui-comentarios";
				}
				
				artigoNegocio.excluirArtigo(artigo);
				
				return "redirect:gc-busca.sp?site="+site+"&nomeCategoria="+nomeCategoria+"&idSubCategoria="+idSubCategoria;
			}
		} catch (Exception e) {
			logger.error("erro ao excluir artigo", e);
			return "redirect:gc-busca.sp?m="+e.getMessage();
		}

		return "redirect:gc.sp";
	}

//	@RequestMapping("/ilionnet/artigo-todos-excluir")
//	@UsuarioLogado("artigo-excluir.sp")
//	public String artigoTodosExcluir(String nomeCategoria) {
//		CategoriaProp categoriaProp = null;
//		try {
//			categoriaProp = new CategoriaProp(nomeCategoria);
//		} catch (MissingResourceException e) {
//			return "redirect:"+propNegocio.findValueById(PropEnum.URL)+"/ilionnet/"+"gc.sp?m=categoria-nao-encontrada";
//		}
//
//		if(nomeCategoria == null) {
//			return "redirect:"+propNegocio.findValueById(PropEnum.URL)+"/ilionnet/gc.sp";
//		}
//
//		if( ! categoriaProp.getRss() ){
//			return "redirect:"+propNegocio.findValueById(PropEnum.URL)+"/ilionnet/gc-busca.sp?nomeCategoria="+nomeCategoria+"&m=noticias-nao-rss";
//		}
//
//		List artigos = gcNegocio.listarArtigos(nomeCategoria);
//		if(artigos != null && artigos.size() != 0) {
//			for (Iterator iterator = artigos.iterator(); iterator.hasNext();) {
//				Artigo artigo = (Artigo) iterator.next();
//
//				artigoNegocio.excluirArtigo(artigo);
//			}
//		}
//
//		return "redirect:"+propNegocio.findValueById(PropEnum.URL)+"/ilionnet/gc-busca.sp?nomeCategoria="+nomeCategoria;
//	}

	@RequestMapping("/ilionnet/subcategoria-excluir")
	@UsuarioLogado("subcategoria-excluir.sp")
	public String subCategoriaExcluir(String id) {
		Long idSubCategoria = Uteis.converterLong(id);

		if(idSubCategoria == null) {
			return "redirect:gc.sp";
		}

		SubCategoriaArtigo subCategoriaArtigo = (SubCategoriaArtigo) gcNegocio.consultar(SubCategoriaArtigo.class, idSubCategoria);
		if(subCategoriaArtigo != null) {
			String nomeCategoria = subCategoriaArtigo.getCategoriaArtigo().getNome();

			if( gcNegocio.possuiArtigos(subCategoriaArtigo) ) {
				return "redirect:gc-busca.sp?nomeCategoria="+nomeCategoria+"&m=subcategoria-possui-artigos";
			}

			gcNegocio.excluirSubCategoriaArtigo(subCategoriaArtigo);

			return "redirect:gc-busca.sp?nomeCategoria="+nomeCategoria;
		}

		return "redirect:gc.sp";
	}

	@RequestMapping("/ilionnet/enquete-excluir")
	@UsuarioLogado("artigo-excluir.sp")
	public String enqueteExcluir(String id) {
		Long idEnquete = Uteis.converterLong(id);

		if(idEnquete == null) {
			return "redirect:gc-busca.sp?nomeCategoria=enquete";
		}

		Enquete enquete = (Enquete) gcNegocio.consultar(Enquete.class, idEnquete);
		if(enquete != null) {
			gcNegocio.excluir(enquete);
		}

		return "redirect:gc-busca.sp?nomeCategoria=enquete";
	}

	@RequestMapping("/comentario-incluir")
	@AcessoLivre
	public String comentarioSiteIncluir(HttpServletRequest request) {
		String url = request.getParameter("url");

		Long idArtigo = Uteis.converterLong(request.getParameter("idArtigo"));

		if(idArtigo == null) {
			return "redirect:"+url+"?m=id-artigo-vazio";
		}

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String comentario = request.getParameter("comentario");
		Boolean permissao = "true".equals(request.getParameter("permissao"));

		if(nome == null || 
				nome.length() == 0 || 
				email == null || 
				email.length() == 0 || 
				comentario == null || 
				comentario.length() == 0) {
			return "redirect:"+url+"?m=nome-email-comentario-vazios";
		}

		Comentario c = new Comentario();
		c.setArtigo(new Artigo(idArtigo));
		c.setComentario(comentario);
		c.setData(new Date());
		c.setEmail(email);
		c.setNome(nome);
		c.setStatus("EmAprovacao");

		gcNegocio.inserir(c);

		if(permissao) {
			contatoNegocio.cadastraContatoSite(nome, email, null, "Visitante do Site");
		}

		return "redirect:"+url+"?m=comentario-ok";
	}

	@RequestMapping("/ilionnet/comentarios")
	@UsuarioLogado("comentarios.sp")
	public String comentarios(HttpServletRequest request) {
		Long idArtigo = Uteis.converterLong(request.getParameter("idArtigo"));

		Artigo artigo = (Artigo) gcNegocio.consultar(Artigo.class, idArtigo);
		if(artigo != null) {
			request.setAttribute("artigo", artigo);

			VLHForm vlhForm = VLHForm.getVLHSession("comentariosIlionnet-"+idArtigo, request);
			
			ValueList comentarios = artigoNegocio.buscaComentarios(vlhForm, new ValueListInfo(vlhForm, 15));
			request.setAttribute("comentarios", comentarios);
		}

		return "/ilionnet/modulos/gc/comentarios";
	}

	@RequestMapping("/ilionnet/comentario-status")
	@UsuarioLogado("comentario-status.sp")
	public String comentarioStatus(HttpServletRequest request) {
		Long id = Uteis.converterLong(request.getParameter("id"));

		Comentario comentario = (Comentario) gcNegocio.consultar(Comentario.class, id);
		if(comentario != null) {
			String status = request.getParameter("status");

			if( ! "Publicado".equals(status) && 
					! "NaoPublicado".equals(status) && 
					! "EmAprovacao".equals(status)) {
				return "redirect:comentarios.sp?idArtigo="+comentario.getArtigo().getId();
			}

			comentario.setStatus(status);
			gcNegocio.atualizar(comentario);

			if("Publicado".equals(status)) {
				CacheFilter.limparCache();
			}

			return "redirect:comentarios.sp?idArtigo="+comentario.getArtigo().getId();
		}

		return "redirect:gc.sp";
	}
	
	@RequestMapping("/ilionnet/comentario-excluir")
	@UsuarioLogado("comentario-excluir.sp")
	public String comentarioExcluir(String id,String idArtigo) {
		Long idComentario = Uteis.converterLong(id);

		if(idComentario == null) {
			return "redirect:comentarios.sp?idArtigo="+idArtigo;
		}

		Comentario comentario = (Comentario) gcNegocio.consultar(Comentario.class, idComentario);
		
		if(comentario != null) {
			gcNegocio.excluir(comentario);
		}

		return "redirect:comentarios.sp?idArtigo="+idArtigo;
	}

}