package ilion.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.gc.util.UteisGC;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.exceptions.ValidacaoException;

@Controller
@AcessoLivre
public class LoginController extends CustomErrorController {

	static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@RequestMapping("/ilionnet")
    public String redirectLogin(HttpServletRequest request) {
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
		Boolean estaLogado = usuarioSessao != null;
		
		if (estaLogado) {
			return "redirect:/ilionnet/home";
		}
		
		return "redirect:/ilionnet/login";
    }
	
	@RequestMapping("/ilionnet/login")
	public String login(HttpServletRequest request) {
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
		Boolean estaLogado = usuarioSessao != null;
		
		if (estaLogado) {
			return "redirect:/ilionnet/home";
		}
		
		return "/ilionnet/modulos/admin/login";
	}
    
    @RequestMapping("/ilionnet/login-logar")
    public String logar(String login, String senha, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	
    	if( Uteis.ehNuloOuVazio(login) || Uteis.ehNuloOuVazio(senha) ) {
    		redirectAttributes.addFlashAttribute("msg", "Login ou senha vazios.");
    		return "redirect:/ilionnet/login";
    	}
    	
    	try {
    		
    		Usuario usuario = usuarioNegocio.efetuarLogin(login, senha);
    		
    		request.getSession().setAttribute("usuarioSessao", usuario);
        	
        	request.getSession().removeAttribute(UteisGC.GRUPOS_ATRIBUTO_SESSAO+"br");
        	request.getSession().removeAttribute(UteisGC.GRUPOS_ATRIBUTO_SESSAO+"en");
    		
		} catch (ValidacaoException e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
    		return "redirect:/ilionnet/login";
		}
    	
        return "redirect:/ilionnet/terrafos/home";
    }
    
    @RequestMapping("/ilionnet/esqueci-minha-senha")
    public String esqueciMinhaSenha(
    		String email, 
    		HttpServletRequest request, 
    		RedirectAttributes redirectAttributes
    		) throws Exception {
    	
    	try {
			
    		usuarioNegocio.enviarSenhaEmail(email);
    		
    		redirectAttributes.addFlashAttribute("msg", "Senha enviada ao email: "+email);
            return "redirect:/ilionnet/login";
    		
    	} catch (ValidacaoException e) {
    		logger.error("", e);
    		redirectAttributes.addFlashAttribute("msg", e.getMessage());
    		return "redirect:/ilionnet/login";
    		
		} catch (Exception e) {
			logger.error("", e);
			redirectAttributes.addFlashAttribute("msg", "Erro ao tentar enviar a senha, por favor tente novamente em instantes.");
    		return "redirect:/ilionnet/login";
		}
    	
    }

    @RequestMapping("/ilionnet/template-esqueci-minha-senha")
    public String esqueciMinhaSenhaEmail(String id, ModelMap modelMap) {
    	Long idLong = Uteis.converterLong(id);
    	
    	if(idLong == null) {
    		return null;
    	}
    	
    	Usuario usuario = (Usuario) usuarioNegocio.consultar(Usuario.class, idLong);
    	
    	if(usuario == null) {
    		logger.error("usuário não encontrado: id: "+id);
    		return null;
    	}
    	
    	modelMap.addAttribute("usuario", usuario);
    	
    	return "/ilionnet/modulos/admin/usuario-senha-email";
    }
    
    @RequestMapping("/ilionnet/logout")
    public String sair(HttpServletRequest request) {
    	
    	request.getSession().removeAttribute("usuarioSessao");
    	request.getSession().removeAttribute("fazendaSessao");
    	
        return "redirect:/ilionnet/login";
    }
    
    @RequestMapping("/ilionnet/dados-iniciais")
    public String dadosIniciais() {
        Boolean inserido = usuarioNegocio.inserirDadosIniciais();
    	
        return "redirect:/ilionnet/login?m=inserido-"+inserido;
    }
    
}