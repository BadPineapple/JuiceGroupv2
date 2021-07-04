package ilion.terrafos.cadastros.controller;

import java.util.Date;

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
import ilion.terrafos.cadastros.negocio.PlanoDeAcao;
import ilion.terrafos.cadastros.negocio.PlanoDeAcaoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("planoDeAcao")
@UsuarioLogado
public class PlanoDeAcaoFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(PlanoDeAcaoFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/plano-de-acao-form";
	
	@Autowired
	private PlanoDeAcaoNegocio planoDeAcaoNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/planos-de-acao/novo")
	public String novo(ModelMap map, HttpSession session) {
		
		Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		
		PlanoDeAcao planoDeAcao = new PlanoDeAcao();
		planoDeAcao.setFazenda(fazenda);
		
		map.addAttribute("planoDeAcao", planoDeAcao);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/planos-de-acao/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		PlanoDeAcao planoDeAcao = planoDeAcaoNegocio.findById(id);
		
		map.addAttribute("planoDeAcao", planoDeAcao);
		
		return TEMPLATE;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "dataVisita", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/planos-de-acao/novo", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/planos-de-acao/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("planoDeAcao") PlanoDeAcao planoDeAcao, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			planoDeAcao = planoDeAcaoNegocio.incluirAtualizar(planoDeAcao);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+planoDeAcao.getFazenda().getId()+"/planos-de-acao?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
