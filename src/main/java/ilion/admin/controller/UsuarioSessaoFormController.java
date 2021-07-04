package ilion.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.util.StringUtil;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/usuario-sessao-form")
@SessionAttributes("usuario")
@UsuarioLogado
public class UsuarioSessaoFormController extends CustomErrorController{

	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(ModelMap modelMap, HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
		String senha = StringUtil.decodePassword(usuario.getSenha());
		usuario.setSenha(senha);
		usuario.setConfirmar(senha);
		
		modelMap.addAttribute("usuario", usuario);
		
		return "/ilionnet/modulos/admin/usuario-sessao-form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, SessionStatus status) {
	
		usuarioNegocio.validarUsuarioSessao(usuario, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/admin/usuario-sessao-form";
		} else {
			String senha = StringUtil.encodePassword(usuario.getSenha());
			usuario.setSenha(senha);
			
			usuarioNegocio.atualizar(usuario);
			
			return "redirect:usuario-sessao-form.sp?m=ok";
		}
	}
}