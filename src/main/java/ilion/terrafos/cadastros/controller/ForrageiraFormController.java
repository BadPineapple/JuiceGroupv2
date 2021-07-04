package ilion.terrafos.cadastros.controller;

import java.util.Date;
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
import ilion.terrafos.cadastros.negocio.Forrageira;
import ilion.terrafos.cadastros.negocio.ForrageiraNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("forrageira")
@UsuarioLogado
public class ForrageiraFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(ForrageiraFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/forrageira-form";
	
	@Autowired
	private ForrageiraNegocio forrageiraNegocio;
		
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/forrageiras")
	public String novo(ModelMap map, HttpSession session) {
		
		Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		
		Forrageira forrageira = new Forrageira();
		forrageira.setFazenda(fazenda);
		
		map.addAttribute("forrageira", forrageira);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/forrageiras/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		Forrageira forrageira = forrageiraNegocio.findById(id);
		
		map.addAttribute("forrageira", forrageira);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("forrageiras")
	public List<Forrageira> forrageiras(@PathVariable Long idFazenda) {
		return forrageiraNegocio.listar(new Fazenda(idFazenda));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "data", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/forrageiras", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/forrageiras/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("forrageira") Forrageira forrageira, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			forrageira = forrageiraNegocio.incluirAtualizar(forrageira);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+forrageira.getFazenda().getId()+"/forrageiras?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
