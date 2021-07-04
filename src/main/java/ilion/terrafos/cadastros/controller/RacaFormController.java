package ilion.terrafos.cadastros.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.CustomErrorController;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Raca;
import ilion.terrafos.cadastros.negocio.RacaNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("raca")
@UsuarioLogado
public class RacaFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(RacaFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/raca-form";
	
	@Autowired
	private RacaNegocio racaNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/racas")
	public String novo(ModelMap map, HttpSession session) {
		
		Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		 
		Raca raca = new Raca();
		raca.setFazenda(fazenda);
		
		map.addAttribute("raca", raca);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/racas/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		Raca raca = racaNegocio.findById(id);
		
		map.addAttribute("raca", raca);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("racas")
	public List<Raca> produtos(@PathVariable Long idFazenda) {
		return racaNegocio.listar(new Fazenda(idFazenda));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/racas", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/racas/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("raca") Raca raca, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			raca = racaNegocio.incluirAtualizar(raca);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+raca.getFazenda().getId()+"/racas?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
