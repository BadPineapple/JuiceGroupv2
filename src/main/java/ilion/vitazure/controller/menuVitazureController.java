package ilion.vitazure.controller;

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
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.negocio.PessoaNegocio;
import net.mlw.vlh.ValueList;

@Controller
public class menuVitazureController  extends CustomErrorController{

	@Autowired
	private PessoaNegocio pessoaNegocio;
	
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
		ValueList arquivosAreaRestrita = pessoaNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao , "psicologo");
		request.setAttribute("profissionais", arquivosAreaRestrita);
		
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
			Pessoa pessoa = new Pessoa();
			if (id != null && id != 0) {
				pessoa = pessoaNegocio.consultarPorId(id);
			}
			request.setAttribute("pessoa", pessoa);
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
}
