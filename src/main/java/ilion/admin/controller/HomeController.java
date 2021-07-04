package ilion.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class HomeController extends CustomErrorController {

	static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private FazendaNegocio fazendaNegocio;
	
	@GetMapping("/ilionnet/terrafos/home")
    public String home(HttpServletRequest request) {
		
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
		Fazenda fazenda = (Fazenda) request.getSession().getAttribute("fazendaSessao");
		
		if( fazenda == null ) {
			//List<Fazenda> fazendas = fazendaNegocio.listar();
			List<Fazenda> fazendas = usuarioSessao.getFazendas();
			
			if( fazendas.isEmpty() ) {
				return "/ilionnet2/home";
			}
			
			fazenda = fazendas.get(0);
			request.getSession().setAttribute("fazendaSessao", fazenda);			
		}
		
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