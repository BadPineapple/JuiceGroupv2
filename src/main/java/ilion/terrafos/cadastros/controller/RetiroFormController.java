package ilion.terrafos.cadastros.controller;

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
import ilion.terrafos.cadastros.negocio.Retiro;
import ilion.terrafos.cadastros.negocio.RetiroNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("retiro")
@UsuarioLogado
public class RetiroFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(RetiroFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/retiro-form";
	
	@Autowired
	private RetiroNegocio retiroNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/retiros/novo")
	public String novo(ModelMap map, HttpSession session) {
		
		Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		
		Retiro retiro = new Retiro();
		retiro.setFazenda(fazenda);
		
		map.addAttribute("retiro", retiro);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/retiros/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		Retiro retiro = retiroNegocio.findById(id);
		
		map.addAttribute("retiro", retiro);
		
		return TEMPLATE;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/retiros/novo", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/retiros/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("retiro") Retiro retiro, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			retiro = retiroNegocio.incluirAtualizar(retiro);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+retiro.getFazenda().getId()+"/retiros?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
