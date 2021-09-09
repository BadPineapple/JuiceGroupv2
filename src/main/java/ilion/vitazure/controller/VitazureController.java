package ilion.vitazure.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import ilion.vitazure.enumeradores.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.Usuario;
import ilion.me.pagar.exception.PagarMeException;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.Transaction;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.util.json.JsonString;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.Especialidade;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.PagamentoPagarMe;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.model.TemaTrabalho;
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.EspecialidadeNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.HorarioAtendimentoNegocio;
import ilion.vitazure.negocio.PagarMeNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;
import ilion.vitazure.negocio.TemaAtendimentoNegocio;
import net.mlw.vlh.ValueList;

@Controller
@SessionAttributes("usuario")
@PessoaLogada
public class VitazureController {

	@Autowired
	private ProfissionalNegocio profissionalNegocio;
	
	@Autowired
	private FormacaoAcademicaNegocio formacaoAcademicaNegocio;
	
	@Autowired
	private EnderecoNegocio enderecoNegocio;
	
	@Autowired
	private TemaAtendimentoNegocio temaNegocio;
	
	@Autowired
	private EspecialidadeNegocio especialidadeNegocio;
	
	@Autowired
	private HorarioAtendimentoNegocio horarioNegocio;
	
	@Autowired
	private PessoaNegocio pessoaNegocio;
	
	@Autowired
	private AgendaNegocio agendaNegocio;
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private PagarMeNegocio pagarMeNegocio;
	
	private Gson gson = new Gson();

	
	@GetMapping("/vitazure/informacoes-perfil")
	public String carregar(ModelMap modelMap, HttpServletRequest request) {
		Pessoa pessoa = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
		pessoa = pessoaNegocio.consultarPorId(pessoa.getId());
		Profissional profissional = new Profissional();
		profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
		if (profissional == null || profissional.getId() == 0) {
			profissional.setPessoa(pessoa);
		}
		modelMap.addAttribute("pessoa", pessoa);
		modelMap.addAttribute("agendaDia", listAgendaDia);
		if (pessoa.getCpf().equals("")) {
			request.setAttribute("estados", EstadoEnum.values());
			return "/ilionnet2/vitazure/completar-cadastro";
		}else if(pessoa.getCliente()) {
			request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
			request.setAttribute("especialidades", EspecialidadesEnum.values());
			request.setAttribute("estados", EstadoEnum.values());
			return "/ilionnet2/vitazure/painel-do-cliente";
		}else if (pessoa.getPsicologo() && (profissional.getAtivo() == null || !profissional.getAtivo())) {
				return "/ilionnet2/vitazure/assinatura";
		}else {
			request.setAttribute("conselhoProfissional", ConselhoProfissionalEnum.values());
			request.setAttribute("estados", EstadoEnum.values());
			request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
			request.setAttribute("especialidades", EspecialidadesEnum.values());
			request.setAttribute("temasTrabalho", TemasTrabalhoEnum.values());
			request.setAttribute("duracoes", DuracaoAtendimentoEnum.values());
			request.setAttribute("duracaoAtendimentoValor", DuracaoAtendimentoEnum.values());
			request.setAttribute("tiposConta", TipoContaEnum.values());
			request.setAttribute("bancos", BancoEnum.values());
			request.setAttribute("temasTrabalho", TemasTrabalhoEnum.values());
			request.setAttribute("tempoAntecendencia", TempoAntecendenciaEnum.values());
			request.setAttribute("diasSemana", DiasSemanaEnum.values());
			request.setAttribute("profissional", profissional);
			request.setAttribute("informacacaoPerfil", true);
			request.setAttribute("enderecoAtendimento", enderecoNegocio.consultarEnderecoPorPessoa(profissional.getId()));
			return "/ilionnet2/vitazure/informacoes-perfil";
		}
	}
	@RequestMapping("/deslogar")
	public String deslogar(HttpServletRequest request) {
		request.getSession().removeAttribute("pessoaSessao");
		request.getSession().removeAttribute("idProfissional");
		request.getSession().removeAttribute("agendaPessoa");
		return "redirect:/home";
	}
	
	 @RequestMapping("/vitazure/formacao/{id}")
	 public  ResponseEntity<String> consultaFormacoes(@PathVariable String id) {
			try {
				List<FormacaoAcademica> formacao = formacaoAcademicaNegocio.consultarFormacoesPorPessoa(Long.valueOf(id));
				return new ResponseEntity<>(gson.toJson(formacao), HttpStatus.OK);
			} catch (Exception e) {
			    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
	 
	 @RequestMapping("/vitazure/excluirEndereco/{id}")
	 public  ResponseEntity<String> excluirEndereco(@PathVariable String id) {
		 try {
			 EnderecoAtendimento enderecoAtendimento = enderecoNegocio.consultarPorId(Long.parseLong(id));
			 enderecoNegocio.excluir(enderecoAtendimento);
			 return new ResponseEntity<>(gson.toJson("Endereço Excluído Com Sucesso."), HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 @RequestMapping("/vitazure/enderecoAtendimento/{id}")
	 public  ResponseEntity<String> consultaEnderecosAtendimento(@PathVariable String id) {
		 try {
			 List<EnderecoAtendimento> enderecoAtendimento = enderecoNegocio.consultarEnderecoPorPessoa(Long.valueOf(id));
			 return new ResponseEntity<>(gson.toJson(enderecoAtendimento), HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 @RequestMapping("/vitazure/especialidadeAtendimento/{id}")
	 public  ResponseEntity<String> consultaEspecialidadeAtendimento(@PathVariable String id) {
		 try {
			 List<Especialidade> especialidade = especialidadeNegocio.consultarEspecialidadesProfissional(Long.valueOf(id));
			 return new ResponseEntity<>(gson.toJson(especialidade), HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 @RequestMapping("/vitazure/temasAtendimento/{id}")
	 public  ResponseEntity<String> consultaTemasAtendimento(@PathVariable String id) {
		 try {
			 List<TemaTrabalho> temaTrabalhoEnum = temaNegocio.consultarTemasPorProfissional(Long.valueOf(id));
			 return new ResponseEntity<>(gson.toJson(temaTrabalhoEnum), HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 @RequestMapping("/vitazure/horarioAtendimento/{id}")
	 public  ResponseEntity<String> consultaHorarioAtendimento(@PathVariable String id) {
		 try {
			 List<HorarioAtendimento> horarioAtendimento = horarioNegocio.consultarHorariosAtendimentoPorProfissional(Long.valueOf(id) , false , false);
			 return new ResponseEntity<>(gson.toJson(horarioAtendimento), HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		 }
	 }
	
	 
	 @GetMapping("/vitazure/meuCadastro")
		public String meuCadastro(ModelMap modelMap, HttpServletRequest request) {
			Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
			List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
			modelMap.addAttribute("agendaDia", listAgendaDia);
			modelMap.addAttribute("pessoa", pessoa);
			return "/ilionnet2/vitazure/completar-cadastro";
			
	 }
	
	 @RequestMapping("/vitazure/lista-de-consultas")
	 public String listaConsulta(ModelMap modelMap, HttpServletRequest request) {
		 Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
		 modelMap.addAttribute("pessoa", pessoa);
		 List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
		 modelMap.addAttribute("agendaDia", listAgendaDia);
		 VLHForm vlhForm = VLHForm.getVLHSession("agendaLista", request);
		 vlhForm.setPagingNumberPer(10);
		 ValueList listAgendas = agendaNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , null,pessoa);
		 request.setAttribute("vlhForm", vlhForm);
		 request.setAttribute("listAgendas", listAgendas);
		 if (pessoa.getCliente()) {
			 return "/ilionnet2/vitazure/lista-de-consultas-paciente";
		}
		 return "/ilionnet2/vitazure/lista-de-consultas";
		 
	 }
	 @GetMapping("/vitazure/telaAgradecimento")
	 public String telaAgradecimento(ModelMap modelMap, HttpServletRequest request) {
		 Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
		 modelMap.addAttribute("pessoa", pessoa);
		 return "/ilionnet2/vitazure/checkout";
		 
	 }
	 
	 @GetMapping("/pagamento")
	 public String pagamentoPagarMe(ModelMap modelMap, HttpServletRequest request) {
		 Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
		 modelMap.addAttribute("pessoa", pessoa);
		 List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
		modelMap.addAttribute("agendaDia", listAgendaDia);
			return "/ilionnet2/vitazure/assinatura";
		}
	
	  @GetMapping("/vitazure/consultaTrasacoes")
	  public String consultaTransacoes(ModelMap modelMap,HttpServletRequest request) {
	    Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
	    List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
		modelMap.addAttribute("agendaDia", listAgendaDia);
	    List<PagamentoPagarMe> listPagamentos = new ArrayList<PagamentoPagarMe>();
	    listPagamentos.addAll(pagarMeNegocio.consultarPagamentoPagarMe(pessoa.getId(), pessoa.getPsicologo()));
	    modelMap.addAttribute("pessoa", pessoa);
	    request.setAttribute("listPagamentos", listPagamentos);
	    if (pessoa.getPsicologo()) {
	    	return "/ilionnet2/vitazure/historicoPagamento_profissional";    
		}
	    return "/ilionnet2/vitazure/historicoPagamento_cliente";    
	  }
	  
	  @GetMapping("/vitazure/minhaAssinatura")
		public String meuPlano(ModelMap modelMap, HttpServletRequest request) {
			Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
			List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
			modelMap.addAttribute("agendaDia", listAgendaDia);
			Profissional profissional = new Profissional();
			profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
			modelMap.addAttribute("pessoa", pessoa);
			modelMap.addAttribute("profissional", profissional);
			return "/ilionnet2/vitazure/minhaAssinatura";
			
		}
	  @GetMapping("/vitazure/alteraMinhaAssinatura")
	  public String alteraMinhaAssinatura(ModelMap modelMap, HttpServletRequest request) {
		  Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
		  List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
		  modelMap.addAttribute("agendaDia", listAgendaDia);
		  Profissional profissional = new Profissional();
		  profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
		  modelMap.addAttribute("pessoa", pessoa);
		  modelMap.addAttribute("profissional", profissional);
		  return "/ilionnet2/vitazure/assinatura";
		  
	  }
	  
	  @GetMapping("/vitazure/areaRestrita")
		public String arearestrita(ModelMap modelMap, HttpServletRequest request) {
			Pessoa pessoa = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
			String idProfissional = (String) request.getSession().getAttribute("idProfissional");
			List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
			pessoa = pessoaNegocio.consultarPorId(pessoa.getId());
			Profissional profissional = new Profissional();
			profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
			if (profissional == null || profissional.getId() == 0) {
				profissional.setPessoa(pessoa);
			}
			modelMap.addAttribute("pessoa", pessoa);
			modelMap.addAttribute("agendaDia", listAgendaDia);
			if(idProfissional != null) {
			  return "redirect:/vitazure/perfil-do-profissional/"+idProfissional;	
			}
			if (pessoa.getCpf().equals("")) {
				request.setAttribute("estados", EstadoEnum.values());
				return "/ilionnet2/vitazure/completar-cadastro";
			}else if(pessoa.getCliente()) {
				request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
				request.setAttribute("especialidades", EspecialidadesEnum.values());
				return listaConsulta(modelMap, request);
			}else if (pessoa.getPsicologo() && (profissional.getAtivo() == null || !profissional.getAtivo())) {
					return "/ilionnet2/vitazure/assinatura";
			}else {
				 request.getSession().setAttribute(PessoaNegocio.PROFISSIONAL_COMPLETO, profissional.getDadosCompleto());
				return listaConsulta(modelMap, request);
			}
		}
	  
	  @PostMapping(value = "/vitazure/finalizarAtendimento", produces = "application/json")
	  @ResponseBody
	  public ResponseEntity<String> finalizarAtendimento(ModelMap modelMap,HttpServletRequest request,	@RequestBody Long  id){
			 Pessoa pessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
			 try {
				   Agenda agendaConcluida = agendaNegocio.alterarAgenda(id, StatusEnum.REALIZADO.toString() , pessoaSessao);
				 
					if (pessoaSessao.getCliente()) {
				        request.getSession().setAttribute("agendaConcluida", agendaConcluida);
				        return new ResponseEntity<>(gson.toJson("/vitazure/avaliacaoAtendimento"), HttpStatus.OK);
					}
					return new ResponseEntity<>(gson.toJson("/vitazure/lista-de-consultas"), HttpStatus.OK);
				} catch (ValidationException e) {
					e.printStackTrace();
					return new ResponseEntity<>(gson.toJson("/vitazure/lista-de-consultas"), HttpStatus.BAD_REQUEST);
				} catch (Exception e) {
					return new ResponseEntity<>(gson.toJson("/vitazure/lista-de-consultas"), HttpStatus.BAD_REQUEST);
				}
		}
	  
	    @RequestMapping("/vitazure/avaliacaoAtendimento")
		public String avaliacaoAtendimento(ModelMap modelMap, HttpServletRequest request) {
			Pessoa pessoa = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
			List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(pessoa);
			pessoa = pessoaNegocio.consultarPorId(pessoa.getId());
			Agenda agenda = (Agenda) request.getSession().getAttribute("agendaConcluida");
			Profissional profissional = new Profissional();
			profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
			if (profissional == null || profissional.getId() == 0) {
				profissional.setPessoa(pessoa);
			}
			modelMap.addAttribute("pessoa", pessoa);
			modelMap.addAttribute("agendaDia", listAgendaDia);
			modelMap.addAttribute("agendaConcluida", agenda);
			return "/ilionnet2/vitazure/avaliacaoAtendimento";
			
		}
	    
	    @GetMapping("/consulta/{id}")
		public String consultaAgendaId(ModelMap modelMap,HttpServletRequest request , @PathVariable Long id) {
	     Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
	     Agenda agenda = new Agenda();
		 agenda = agendaNegocio.consultarAgendaId(id);
		 request.getSession().setAttribute("agenda", agenda);
		 modelMap.addAttribute("pessoa", PessoaSessao);
		 return "redirect:/vitazure/consulta";
		}
	
		 @RequestMapping("/vitazure/consulta")
		 public String apresentarDadosConsulta(ModelMap modelMap,HttpServletRequest request) {
			 Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
			 Agenda agenda = (Agenda) request.getSession().getAttribute("agenda");
			 Profissional profissional = profissionalNegocio.consultarPorId(agenda.getProfissional().getId());
			 String horaFimAtendimento = Uteis.calculodeHoraSemIntervalo(agenda.getHoraInicioAgenda(), 1, (Integer.parseInt(profissional.getDuracaoAtendimento().getNome())));
			 request.setAttribute("horaFimAtendimento", horaFimAtendimento);
			 modelMap.addAttribute("pessoa", PessoaSessao);
			 request.setAttribute("agenda", agenda);
			 return "/ilionnet2/vitazure/whereby";
		 }
	    
		 @PostMapping(value = "/vitazure/finalizarAvaliacaoAtendimento", produces = "application/json")
		 @ResponseBody
		 public ResponseEntity<String> avaliacaoAtendimento(ModelMap modelMap,HttpServletRequest request, @RequestBody Agenda  agenda) throws NumberFormatException, JSONException {
				 Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
				 try {
					   Agenda agendaAvaliada = agendaNegocio.avaliarAtendimento(agenda);
					   return new ResponseEntity<>(gson.toJson("Agradecemos a sua Avaliação."), HttpStatus.OK);
					} catch (ValidationException e) {
						e.printStackTrace();
						return new ResponseEntity<>(gson.toJson("Aconteceu um erro ao avaliar seu atendimento."), HttpStatus.BAD_REQUEST);
					}
			}
		 
		 @GetMapping("/vitazure/reagendar/{id}")
			public String reagendarConsultaAgendareagendamento(ModelMap modelMap,HttpServletRequest request , @PathVariable Long id) {
		     Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		     Agenda agenda = new Agenda();
			 agenda = agendaNegocio.consultarAgendaId(id);
			 request.getSession().setAttribute("agenda", agenda);
			 modelMap.addAttribute("pessoa", PessoaSessao);
			 return "redirect:/vitazure/reagendar";
			}
		
			 @RequestMapping("/vitazure/reagendar")
			 public String apresentarDadosProfissionalReagendamento(ModelMap modelMap,HttpServletRequest request) {
				 Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
				 Agenda agenda = (Agenda) request.getSession().getAttribute("agenda");
				 Profissional profissional = profissionalNegocio.consultarPorId(agenda.getProfissional().getId());
				 profissionalNegocio.consultarDataDisponivelProfissional(profissional , false , false);
				 List<TemaTrabalho> temasTrabalho = temaNegocio.consultarTemasPorProfissional(profissional.getId());
			  	 List<FormacaoAcademica> formacoes = formacaoAcademicaNegocio.consultarFormacoesPorPessoa(profissional.getId());
			  	 List<Especialidade> especialidades = especialidadeNegocio.consultarEspecialidadesProfissional(profissional.getId());
			  	 request.getSession().setAttribute("profissional", profissional);
			  	 request.getSession().setAttribute("temasTrabalho", temasTrabalho);
			  	 request.getSession().setAttribute("formacoes", formacoes);
			  	 request.getSession().setAttribute("especialidades", especialidades);
			  	 request.getSession().setAttribute("agenda", agenda);
			  	 modelMap.addAttribute("pessoa", PessoaSessao);
			  	 List<EnderecoAtendimento> enderecoAtendimento = new ArrayList<EnderecoAtendimento>();
			  	 enderecoAtendimento.addAll(enderecoNegocio.consultarEnderecoPorPessoa(profissional.getId()));
			  	 request.setAttribute("enderecoAtendimento", enderecoAtendimento);
			  	 request.setAttribute("cidadeProfissional", enderecoAtendimento.get(0).getCidade());
				 return "/ilionnet2/vitazure/perfil-do-profissional-reagendamento";
			 }
			 
			    @PostMapping(value = "/vitazure/concluirReagendamento", produces = "application/json")
				@ResponseBody
				public ResponseEntity<JsonString> agendar(HttpServletRequest request, @RequestBody String jsonReagendamento) {
					try {
						Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
						List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(PessoaSessao);
						request.setAttribute("agendaDia", listAgendaDia);
						JSONObject jsonRetornoToken = new JSONObject(jsonReagendamento);
						agendaNegocio.incluirReagendamentoPaciente(jsonRetornoToken, PessoaSessao);
						return new ResponseEntity<>(new JsonString("Reagendamento concluído com sucesso."), HttpStatus.OK);
					} catch (Exception e) {
						e.printStackTrace();
						return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
					}
				}
		 
}
