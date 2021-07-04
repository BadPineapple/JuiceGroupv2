package ilion.terrafos.cadastrospadroes.controller;

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
import ilion.terrafos.cadastrospadroes.negocio.ForrageiraPadrao;
import ilion.terrafos.cadastrospadroes.negocio.ForrageiraPadraoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("forrageira")
@UsuarioLogado("terrafos_cadastros_padroes")
public class ForrageiraPadraoFormController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(ForrageiraPadraoFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/cadastros-padroes/forrageira-padrao-form";
	
	@Autowired
	private ForrageiraPadraoNegocio forrageiraPadraoNegocio;
		
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/forrageiras")
	public String novo(ModelMap map, HttpSession session) {
		
		ForrageiraPadrao forrageiraPadrao = new ForrageiraPadrao();
		
		map.addAttribute("forrageira", forrageiraPadrao);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/forrageiras/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		ForrageiraPadrao forrageira = forrageiraPadraoNegocio.findById(id);
		
		map.addAttribute("forrageira", forrageira);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("forrageiras")
	public List<ForrageiraPadrao> forrageiras() {
		return forrageiraPadraoNegocio.listar();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "data", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/cadastros-padroes/forrageiras", 
			"/ilionnet/terrafos/cadastros-padroes/forrageiras/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("forrageira") ForrageiraPadrao forrageiraPadrao, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			forrageiraPadrao = forrageiraPadraoNegocio.incluirAtualizar(forrageiraPadrao);
			
			return "redirect:/ilionnet/terrafos/cadastros-padroes/forrageiras?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
