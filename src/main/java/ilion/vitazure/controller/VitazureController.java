package ilion.vitazure.controller;


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

import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.FormacaoEnum;
import ilion.vitazure.enumeradores.TemasTrabalhoEnum;
import ilion.vitazure.enumeradores.TempoAntecendenciaEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;

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
	
	private Gson gson = new Gson();

	
	@GetMapping("/vitazure/informacoes-perfil")
	public String carregar(ModelMap modelMap, HttpServletRequest request) {
		Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
		Profissional profissional = new Profissional();
		profissional = profissionalNegocio.consultarPorPessoa(pessoa.getId());
		if (profissional == null || profissional.getId() == 0) {
			profissional.setPessoa(pessoa);
		}
		modelMap.addAttribute("pessoa", pessoa);
		if (pessoa.getCliente() && pessoa.getCpf().equals("")) {
			return "/ilionnet2/vitazure/completar-cadastro";
		}else if(pessoa.getCliente() && !pessoa.getCpf().equals("")) {
			return "/ilionnet2/vitazure/painel-do-cliente";
		}else if (pessoa.getPsicologo() && pessoa.getCpf().equals("")) {
			request.setAttribute("estados", EstadoEnum.values());
			return "/ilionnet2/vitazure/completar-cadastro";
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
			request.setAttribute("formacoes", FormacaoEnum.values());
			request.setAttribute("profissional", profissional);
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
	
	
}
