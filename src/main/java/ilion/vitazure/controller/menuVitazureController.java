package ilion.vitazure.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.UsuarioLogado;
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
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.PagarMeNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;
import ilion.vitazure.negocio.ProfissionalVH;
import net.mlw.vlh.ValueList;

@Controller
public class menuVitazureController  extends CustomErrorController{

	@Autowired
	private PessoaNegocio pessoaNegocio;
	
	@Autowired
	private ProfissionalNegocio profissionalNegocio;
	
	@Autowired
	private FormacaoAcademicaNegocio formacaoAcademicaNegocio;
	
	@Autowired
	private EnderecoNegocio enderecoNegocio;
	
	@Autowired
	private AgendaNegocio agendaNegocio;
	
	@Autowired
	private PagarMeNegocio pagarMeNegocio;
	
	@RequestMapping("/cliente")
	@UsuarioLogado()
	public String clienteCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("clienteLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList arquivosAreaRestrita = pessoaNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao , "cliente");
		request.setAttribute("clientes", arquivosAreaRestrita);
		
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/clienteCons";
	}

	@RequestMapping("/profissional")
	@UsuarioLogado()
	public String profissionalCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("profissionalLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList profissionais = profissionalNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao);
		request.setAttribute("profissionais", profissionais);
		
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/profissionalCons";
	}
	
	@RequestMapping("/vitazure/cliente/{id}")
	@UsuarioLogado()
	public String editarCliente(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		try {
			Pessoa pessoa = new Pessoa();
			if (id != null && id != 0) {
				pessoa = pessoaNegocio.consultarPorId(id);
			}
			request.setAttribute("pessoa", pessoa);
			return "/ilionnet/modulos/vitazure/clienteForm";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/ilionnet/modulos/vitazure/clienteCons/?m=erro";
		}
	 }
	@RequestMapping("/vitazure/profissional/{id}")
	@UsuarioLogado()
	public String editarProfissional(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		try {
			Profissional profissional = new Profissional();
			List<FormacaoAcademica> formacao = new ArrayList<FormacaoAcademica>();
			List<EnderecoAtendimento> enderecoAtendimento = new ArrayList<EnderecoAtendimento>();
			if (id != null && id != 0) {
				profissional = profissionalNegocio.consultarPorPessoa(id);
				formacao.addAll(formacaoAcademicaNegocio.consultarFormacoesPorPessoa(profissional.getId()));
				enderecoAtendimento.addAll(enderecoNegocio.consultarEnderecoPorPessoa(profissional.getId()));
			}
			request.setAttribute("profissional", profissional);
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
			request.setAttribute("formacao", formacao);
			request.setAttribute("enderecoAtendimento", enderecoAtendimento);
			return "/ilionnet/modulos/vitazure/profissionalForm";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/ilionnet/modulos/vitazure/profissionalCons/?m=erro";
		}
	}
	
	@RequestMapping("vitazure/cliente/excluir/{id}")
	@UsuarioLogado()
	public String excluirCliente(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		try {
			pessoaNegocio.excluir(id);
			return "redirect:/cliente/?m=ok";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/cliente/?m=erro";
		}
	 }
	
	@RequestMapping("vitazure/profissional/excluir/{id}")
	@UsuarioLogado()
	public String excluirProfissional(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		try {
			pessoaNegocio.excluir(id);
			return "redirect:/profissional/?m=ok";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/profissional/?m=erro";
		}
	 }
	
	@RequestMapping("/agenda")
	@UsuarioLogado()
	public String agendaCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("agendaLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList agendas = agendaNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao , null);
		request.setAttribute("agendas", agendas);
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/agendaCons";
	}
	@RequestMapping("/movimentacoes")
	@UsuarioLogado()
	public String movimentacoesCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("movimentacoesLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList listPagamentos = pagarMeNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao);
		request.setAttribute("listPagamentos", listPagamentos);
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/movimentacoesCons";
	}
}
