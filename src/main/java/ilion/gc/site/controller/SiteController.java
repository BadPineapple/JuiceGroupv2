package ilion.gc.site.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
import sun.java2d.pipe.SpanShapeRenderer;

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
	
	@Autowired
	private PessoaNegocio  pessoaNegocio;
	
	@GetMapping(value = { "/",})
	public String aguarde(HttpServletRequest request) {
		return "/aguarde";
	}
	@GetMapping(value = { "/home" })
	public String index(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false);
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
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false);
		return "/ilionnet2/vitazure/como-funciona";
	}
	
	@GetMapping("/aqui-e-para-voce")
	public String aquiPraVoce(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false);
		return "/ilionnet2/vitazure/aqui-e-para-voce";
	}
	@GetMapping("/para-sua-empresa")
	public String paraSuaEmpresa(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false);
		return "/ilionnet2/vitazure/para-sua-empresa";
	}
	@GetMapping("/sou-profissional")
	public String souProfissional(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false);
		return "/ilionnet2/vitazure/sou-profissional";
	}
	
	@RequestMapping("/resultado-de-busca/{tipoProfissional}/{especialista}")
	public String buscaProfissional(HttpServletRequest request,@PathVariable String tipoProfissional,@PathVariable String especialista) {
		List<Pessoa> listPessoa = pessoaNegocio.consultarProfissionais("");
		request.getSession().setAttribute("listPessoa", listPessoa);
		return "/ilionnet2/vitazure/resultado-de-busca";
	}
	@GetMapping("/registre-se-como-psicologo")
	public String registreComoPsicologo(HttpServletRequest request) {
		return "/ilionnet2/vitazure/registre-se-como-psicologo";
	}
	@GetMapping("/registre-se-como-cliente")
	public String registreComoCliente(HttpServletRequest request) {
		return "/ilionnet2/vitazure/registre-se-como-cliente";
	}
	@GetMapping("/cadastre-se")
	public String cadastrase(HttpServletRequest request) {
		return "/ilionnet2/vitazure/nova-conta";
	}
	@GetMapping("/entrar")
	public String entrar(HttpServletRequest request) {
		return "/ilionnet2/vitazure/entrar";
	}
	
	@GetMapping("/listaProfissionais")
	public String consultarProfissionais(HttpServletRequest request) {
		request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		List<Pessoa> listPessoa = (List<Pessoa>) request.getSession().getAttribute("listPessoa");
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("listPessoa", listPessoa);
		request.setAttribute("areaRestrita", false);
		return "/ilionnet2/vitazure/resultado-de-busca";
	}

	@GetMapping("/consulta")
	public String gerarConsulta(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false); //true

		HttpClient httpClient = HttpClientBuilder.create().build();
		String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmFwcGVhci5pbiIsImF1ZCI6Imh0dHBzOi8vYXBpLmFwcGVhci5pbi92MSIsImV4cCI6OTAwNzE5OTI1NDc0MDk5MSwiaWF0IjoxNjI0NzE0ODIyLCJvcmdhbml6YXRpb25JZCI6MTE2NjYyLCJqdGkiOiJmZGM0ZDcwZi1iODFiLTRhNWItOWM0Mi1kZjc0OGI0YmI3YmYifQ.Ic8HKQscH7Io5bxH-tiGPaTtsyshGQA5h4VM-YhnjCc";

		SimpleDateFormat sdfData = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm:ss");
		Date newDate = new Date();

		//Datas apenas temporárias
		//Formatação padrão da data yyyy-MM-ddThh-mm-ss.000Z
		String startDate = sdfData.format(new Date(newDate.getTime()));
		String startHour = sdfHora.format(new Date(newDate.getTime() + 2 * 1000)); // adiciona 1 min a data de início

		String endDate = sdfData.format(new Date(newDate.getTime()));
		String endHour = sdfHora.format(new Date(newDate.getTime()  + (5 * 60 * 1000))); // 1 min após o inicio

		try {
			HttpPost requestJson = new HttpPost("https://api.whereby.dev/v1/meetings"); //url da api

			//Body do Json
			StringEntity params = new StringEntity("{ \"startDate\": \"" + startDate + "T" + startHour + ".000Z" + "\", \"endDate\": " +
							" \"" + endDate + "T" + endHour + ".000Z" + "\"," +
							" \"fields\": [\"https://vitazure.whereby.com/\"]}");

			//Header do Json
			requestJson.addHeader("authorization", "Bearer " + API_KEY);
			requestJson.addHeader("content-type", "application/json");
			requestJson.setEntity(params);

			HttpResponse responseJson = httpClient.execute(requestJson); //Possíveis retornos "200" OK "401" Autenticação incorreta

			String responseString = EntityUtils.toString(responseJson.getEntity());

			String iframeSource = responseString.substring(responseString.indexOf("https:"), responseString.lastIndexOf("\",")); //Pega a url da resposta

			request.setAttribute("iframeSource", iframeSource);
			request.setAttribute("iframeSourceFull", responseString);


		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/ilionnet2/vitazure/whereby";
	}
	
}