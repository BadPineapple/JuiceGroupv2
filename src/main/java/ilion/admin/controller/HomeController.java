package ilion.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class HomeController extends CustomErrorController {

	static Logger logger = Logger.getLogger(HomeController.class);
	
	@GetMapping("/ilionnet/terrafos/home")
    public String home(HttpServletRequest request) {
		
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
		return "/ilionnet2/home";
    }
    
    @GetMapping("/ilionnet/home2")
    public String home2(HttpServletRequest request) {
    	
    	return "redirect:/ilionnet/terrafos/home";
    }
    
    @GetMapping("/ilionnet/home")
    public String home1(HttpServletRequest request) {
    	
    	return "redirect:/ilionnet/terrafos/home";
    }
    
}