package ilion.vitazure.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.DiasSemanaEnum;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.FormacaoEnum;
import ilion.vitazure.enumeradores.TemasTrabalhoEnum;
import ilion.vitazure.enumeradores.TempoAntecendenciaEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
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
			return "/ilionnet2/vitazure/painel-do-cliente";
		}else if (pessoa.getPsicologo() && (profissional.getAtivo() == null || !profissional.getAtivo())) {
				return "/ilionnet2/vitazure/assinatura";
		}else {
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
}
