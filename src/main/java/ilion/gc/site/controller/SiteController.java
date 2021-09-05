package ilion.gc.site.controller;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import ilion.CustomErrorController;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoSiteNegocio;
import ilion.gc.taglibs.ArtigoParamsVO;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.vitazure.controller.ProfissionalControlle;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.Especialidade;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.HorarioPossivelAtendimento;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.PostBlog;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.model.TemaTrabalho;
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.EspecialidadeNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.HorarioAtendimentoNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;
import ilion.vitazure.negocio.TemaAtendimentoNegocio;

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
	
	@Autowired
	private ProfissionalNegocio profissionalNegocio;

	@Autowired
	private ProfissionalControlle profissionalControlle;

	@Autowired
	private HorarioAtendimentoNegocio horarioNegocio;

	@Autowired
	private EspecialidadeNegocio especialidadeNegocio;
	
	@Autowired
	private AgendaNegocio agendaNegocio;
	
	@Autowired
	private FormacaoAcademicaNegocio formacaoAcademicaNegocio;
	
	@Autowired
	private TemaAtendimentoNegocio temaNegocio;
	
	@Autowired
	private EnderecoNegocio enderecoNegocio;

	private Gson gson = new Gson();
	
	
	@GetMapping(value = { "/",})
	public String aguarde(HttpServletRequest request) {
		return "/aguarde";
	}
	@GetMapping(value = { "/home" })
	public String index(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", false);

		String xml = Uteis.getHtml("https://blog.vitazure.com.br/feed/");

		Collection<PostBlog> posts = new ArrayList<PostBlog>();

		int index = 0;

		for (int i = 0; i < 4; i++) {
			if (xml.indexOf("</title>", index) != -1) {
				String item = xml.substring(xml.indexOf("<item>", index) + 7, xml.indexOf("</item>", index));

				String titulo = item.substring(item.indexOf("<title>")+7, item.indexOf("</title>"));

				String link = item.substring(item.indexOf("<link>")+6, item.indexOf("</link>"));

				String src;

				int indexI1 = item.indexOf("src=\"")+5;
				int indexI2 = item.indexOf("g\"")+1;
				if (indexI2 != 0) {
					src = item.substring(indexI1, indexI2);
				}else {
					src = "";
				}

				byte[] bytes = titulo.getBytes(StandardCharsets.ISO_8859_1);

				titulo = new String(bytes, StandardCharsets.UTF_8);

				PostBlog post = new PostBlog(titulo, link, src);

				posts.add(post);

				index = xml.indexOf("</item>", index) + 1;
			} else {
				break;
			}

		}

		request.setAttribute("especialidades", EspecialidadesEnum.values());
		request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
		request.setAttribute("posts", posts);

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

		request.setAttribute("especialidades", EspecialidadesEnum.values());
		request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());

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
	
	@RequestMapping("/resultado-de-busca2/{tipoProfissional}/{especialista}")
	public String buscaProfissional(HttpServletRequest request,@PathVariable String tipoProfissional,@PathVariable String especialista) {
		List<Profissional> listProfissionais = profissionalNegocio.consultarProfissionaisAtivos();
		consultarDataDisponivelProfissionais(listProfissionais , false , false);
		request.getSession().setAttribute("listProfissionais", listProfissionais);
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
	@GetMapping("/entreContato")
	public String entreContato(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		request.setAttribute("pessoa", PessoaSessao);
		return "/ilionnet2/vitazure/entreContato";
	}
	
	
	@GetMapping("/listaProfissionais")
	public String consultarProfissionais(HttpServletRequest request) {
		request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		List<Profissional> listProfissionais = (List<Profissional>) request.getSession().getAttribute("listProfissionais");
		request.setAttribute("listProfissionais", listProfissionais);
		request.setAttribute("areaRestrita", false);
		return "/ilionnet2/vitazure/resultado-busca_aberta";
	}

	
	private List<Profissional> consultarDataDisponivelProfissionais(List<Profissional> lisProfissional , Boolean atendimentoOnline , Boolean atendimentoPresencial) {
  	  List<Date> lista = new ArrayList<Date>();
  	  
  	  int diasIncrementado = 0;
  	  while (diasIncrementado  < 60){
  		  lista.add(Uteis.acrescentar(new Date(), Calendar.DATE, diasIncrementado));
  		  diasIncrementado++;
  	  }
  	  lisProfissional.stream().forEach(profissional -> {
  		  List<HorarioAtendimento> listaHorarioatendimento = horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId() , atendimentoOnline , atendimentoPresencial);
  		  List<Date> datasPossivelAgendamento = lista.stream()
  				  .filter( o1 -> {
  					  return listaHorarioatendimento.stream()
  							  .map(HorarioAtendimento::getDiaSemana)
  							  .anyMatch(i2 -> i2.getValue() == o1.getDay());
  				  }).collect(Collectors.toList());
  		  profissional.getDatasPossivelAgendamento().addAll(datasPossivelAgendamento);
  	  });
  	  return lisProfissional;
  	  
    }
	
	 @GetMapping("/vitazure/perfil-do-profissional/{id}")
	  	public String perfilProfissional(HttpServletRequest request ,@PathVariable Long id) {
	  		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
	  		Profissional profissional = profissionalNegocio.consultarPorId(id);
//	  		List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(PessoaSessao);
//	  		request.setAttribute("agendaDia", listAgendaDia);
	  		request.setAttribute("pessoa", PessoaSessao);
	  		request.setAttribute("areaRestrita", true);
	  		consultarDataDisponivelProfissional(profissional , false , false);
	  		List<TemaTrabalho> temasTrabalho = temaNegocio.consultarTemasPorProfissional(id);
	  		List<FormacaoAcademica> formacoes = formacaoAcademicaNegocio.consultarFormacoesPorPessoa(id);
	  		List<Especialidade> especialidades = especialidadeNegocio.consultarEspecialidadesProfissional(id);
	  		request.getSession().setAttribute("profissional", profissional);
	  		request.getSession().setAttribute("temasTrabalho", temasTrabalho);
	  		request.getSession().setAttribute("formacoes", formacoes);
	  		request.getSession().setAttribute("especialidades", especialidades);
	  		List<EnderecoAtendimento> enderecoAtendimento = new ArrayList<EnderecoAtendimento>();
	  		enderecoAtendimento.addAll(enderecoNegocio.consultarEnderecoPorPessoa(profissional.getId()));
	  		request.setAttribute("enderecoAtendimento", enderecoAtendimento);
	  		request.setAttribute("cidadeProfissional", enderecoAtendimento.get(0).getCidade());
			return "/ilionnet2/vitazure/perfil-do-profissional";
	  	}
	 
	 private Profissional consultarDataDisponivelProfissional(Profissional profissional , Boolean atendimentoOnline , Boolean atendimentoPresencial) {
   	  
   	  List<Date> lista = new ArrayList<Date>();
   	  
   	  int diasIncrementado = 0;
   	  while (diasIncrementado  < 60){
   		  lista.add(Uteis.acrescentar(new Date(), Calendar.DATE, diasIncrementado));
   		  diasIncrementado++;
   	  }
   		  List<HorarioAtendimento> listaHorarioatendimento = horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId() , atendimentoOnline , atendimentoPresencial);
   		  List<Date> datasPossivelAgendamento = lista.stream()
   				  .filter( o1 -> {
   					  return listaHorarioatendimento.stream()
   							  .map(HorarioAtendimento::getDiaSemana)
   							  .anyMatch(i2 -> i2.getValue() == o1.getDay());
   				  }).collect(Collectors.toList());
   		  profissional.getDatasPossivelAgendamento().addAll(datasPossivelAgendamento);
   	  
   		  return profissional;
   	  
     }

	 @GetMapping("/resultado-de-busca-externa/{especialista}")
		public String buscaProfissional(HttpServletRequest request, @PathVariable String especialista) {
			Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
			List<Profissional> lisProfissional = profissionalNegocio.consultarProfissionaisFiltro(null,especialista,null,null);
			lisProfissional.stream().forEach(profissional-> {
				profissional.getPessoa().setCidade(enderecoNegocio.consultarCidadeEnderecoPorProfissional(profissional.getId()));
			});
			request.setAttribute("areaRestrita", true);
			consultarDataDisponivelProfissionais(lisProfissional, false, false);
			request.getSession().setAttribute("listProfissionais", lisProfissional);
			request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
			request.setAttribute("especialidades", EspecialidadesEnum.values());
			request.setAttribute("estados", EstadoEnum.values());
			return "/ilionnet2/vitazure/resultado-de-busca";
		}
	 
	 @GetMapping("/vitazure/profissionais-externa")
		public String profissionais(HttpServletRequest request) {
			List<Profissional> lisProfissional = (List<Profissional>) request.getSession().getAttribute("listProfissionais");
			lisProfissional.stream().forEach(profissional-> {
				profissional.getPessoa().setCidade(enderecoNegocio.consultarCidadeEnderecoPorProfissional(profissional.getId()));
			});
			request.setAttribute("areaRestrita", true);
			request.setAttribute("especialidades", EspecialidadesEnum.values());
			request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
			consultarDataDisponivelProfissionais(lisProfissional, false, false);
			request.getSession().setAttribute("listProfissionais", lisProfissional);
			request.setAttribute("estados", EstadoEnum.values());
			return "/ilionnet2/vitazure/resultado-busca_aberta";
		}
	 
		@RequestMapping("/vitazure/consultarDatasProfissional/{data}/{profissional}")
		public ResponseEntity<String> consultaHorarioAtendimento(@PathVariable String data,@PathVariable Long profissional) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				Date date = sdf.parse(data);
				Profissional prof = profissionalNegocio.consultarPorId(profissional);
				List<HorarioPossivelAtendimento> listHorarioPossivelAtendimento = profissionalControlle.maisTeste(prof, false, false, date);
				return new ResponseEntity<>(gson.toJson(listHorarioPossivelAtendimento), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}
	 
	 @GetMapping("/teste")
	 public String testeHtml(HttpServletRequest request) {
		 Agenda agenda = agendaNegocio.consultarAgendaId(39L);
		 request.setAttribute("agenda", agenda);
		 return "/ilionnet2/vitazure/htmlTeste"; 
	 }
}