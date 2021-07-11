package ilion.gc.site.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ilion.CustomErrorController;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoSiteNegocio;
import ilion.gc.taglibs.ArtigoParamsVO;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.negocio.PessoaNegocio;

@Controller
@AcessoLivre
public class SiteController extends CustomErrorController {

	static Logger logger = Logger.getLogger(SiteController.class);
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private ArtigoSiteNegocio artigoSiteNegocio;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	@GetMapping(value = { "/",})
	public String aguarde(HttpServletRequest request) {
		return "/aguarde";
	}
	@GetMapping(value = { "/home" })
	public String index(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		return "/ilionnet2/vitazure/index";
	}

	@GetMapping("/404")
	public String page404(HttpServletRequest request) {
		return "/404";
	}
	
//	@RequestMapping("/sobre")
//	public String sobre(HttpServletRequest request) {
//		
//		ArtigoParamsVO artigoParamsVO = 
//				ArtigoParamsVO
//				.build()
//				.comSite(propNegocio.findValueById(PropEnum.SITE))
//				.comCategoria("sobre")
//				.comOrder("posicao")
//				.comMaxResults(1)
//				.comFirstResult(0)
//				.comDataAtual();
//		
//		List artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO);
//		
//		Artigo artigo = null;
//		
//		if( ! artigos.isEmpty() ) {
//			artigo = (Artigo) artigos.get(0);
//		}
//		
//		request.setAttribute("titulo", artigo.getTitulo());
//		request.setAttribute("artigoSobre", artigo);
//		
//		if( artigo != null ) {
//			Arquivo arquivo = arquivoNegocio.findFirstResultImageUrl(Artigo.class.getSimpleName(), artigo.getId().toString(), null);
//			request.setAttribute("arquivoSobre", arquivo);
//		}
//		
//		return "/sobre";
//	}
//	
//	@RequestMapping("/institucional/{slug}")
//	public String institucionalInterna(@PathVariable("slug") String slug, HttpServletRequest request) {
//
//		ArtigoConsultaParamsVO artigoParamsVO = 
//				ArtigoConsultaParamsVO
//				.build()
//				.comSite(propNegocio.findValueById(PropEnum.SITE))
//				.comCategoria("institucional")
//				.comArtigoUrl(slug)
//				.comOrder("dataPublicacao desc")
//				.comDataAtual();
//
//		Artigo artigo = artigoSiteNegocio.consultarArtigoEnderecoUrl(artigoParamsVO);
//		request.setAttribute("artigo", artigo);
//		
//		String titulo = "Institucional";
//		if( artigo != null ) {
//			titulo = artigo.getTitulo();			
//		}
//		request.setAttribute("titulo", titulo);
//
//		return "/institucional";
//	}
//	
//	@GetMapping("/encomendas")
//	public String encomendas(HttpServletRequest request) {
//		
//		request.setAttribute("titulo", "Encomendas");
//		
//		return "/encomendas";
//	}
//	
//	@GetMapping("/fretamento")
//	public String fretamento(HttpServletRequest request) {
//		
//		request.setAttribute("titulo", "Fretamento");
//		
//		return "/fretamento";
//	}
	
	@GetMapping("/termos-de-uso")
	public String temosDeUso(HttpServletRequest request) {
		
		ArtigoParamsVO artigoParamsVO = 
				ArtigoParamsVO
				.build()
				.comSite(propNegocio.findValueById(PropEnum.SITE))
				.comCategoria("termos-de-uso")
				.comOrder("posicao")
				.comMaxResults(1)
				.comFirstResult(0)
				.comDataAtual();
		
		List artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO);
		
		Artigo artigo = null;
		
		if( ! artigos.isEmpty() ) {
			artigo = (Artigo) artigos.get(0);
		}
		
		request.setAttribute("artigo", artigo);
		
		return "/termos-de-uso";
	}
	
	@GetMapping("/politica-de-privacidade")
	public String politicaDePrivacidade(HttpServletRequest request) {
		
		ArtigoParamsVO artigoParamsVO = 
				ArtigoParamsVO
				.build()
				.comSite(propNegocio.findValueById(PropEnum.SITE))
				.comCategoria("politica-de-privacidade")
				.comOrder("posicao")
				.comMaxResults(1)
				.comFirstResult(0)
				.comDataAtual();
		
		List artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO);
		
		Artigo artigo = null;
		
		if( ! artigos.isEmpty() ) {
			artigo = (Artigo) artigos.get(0);
		}
		
		request.setAttribute("artigo", artigo);
		
		return "/politica-de-privacidade";
	}
	
	@GetMapping("/como-funciona")
	public String comoFunciona(HttpServletRequest request) {
//		ArtigoParamsVO artigoParamsVO = 
//				ArtigoParamsVO
//				.build()
//				.comSite(propNegocio.findValueById(PropEnum.SITE))
//				.comCategoria("termos-de-uso")
//				.comOrder("posicao")
//				.comMaxResults(1)
//				.comFirstResult(0)
//				.comDataAtual();
//		
//		List artigos = artigoSiteNegocio.listarArtigosSite(artigoParamsVO);
//		
//		Artigo artigo = null;
//		
//		if( ! artigos.isEmpty() ) {
//			artigo = (Artigo) artigos.get(0);
//		}
//		
//		request.setAttribute("artigo", artigo);
		
		return "/ilionnet2/vitazure/como-funciona";
	}
	
	@GetMapping("/aqui-e-para-voce")
	public String aquiPraVoce(HttpServletRequest request) {
		return "/ilionnet2/vitazure/aqui-e-para-voce";
	}
	@GetMapping("/para-sua-empresa")
	public String paraSuaEmpresa(HttpServletRequest request) {
		return "/ilionnet2/vitazure/aqui-e-para-voce";
	}
	@GetMapping("/sou-profissional")
	public String souProfissional(HttpServletRequest request) {
		return "/ilionnet2/vitazure/sou-profissional";
	}
	@GetMapping("/registre-se-como-psicologo")
	public String registreComoPsicologo(HttpServletRequest request) {
		return "/ilionnet2/vitazure/registre-se-como-psicologo";
	}
	@GetMapping("/registre-se-como-cliente")
	public String registreComoCliente(HttpServletRequest request) {
		return "/ilionnet2/vitazure/registre-se-como-cliente";
	}
	@GetMapping("/entrar")
	public String entrar(HttpServletRequest request) {
		return "/ilionnet2/vitazure/entrar";
	}
	
}