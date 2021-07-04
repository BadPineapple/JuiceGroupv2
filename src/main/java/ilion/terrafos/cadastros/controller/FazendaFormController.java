package ilion.terrafos.cadastros.controller;

import javax.servlet.http.HttpServletRequest;

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
import ilion.admin.negocio.UF;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("fazenda")
@UsuarioLogado("terrafos_fazendas")
public class FazendaFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(FazendaFormController.class);
	
	@Autowired
	private FazendaNegocio fazendaNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/novo")
	public String novo(ModelMap map) {
		
		Fazenda fazenda = new Fazenda();
		
		map.addAttribute("fazenda", fazenda);
		
		return "/ilionnet2/terrafos/fazenda-form";
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		Fazenda fazenda = fazendaNegocio.findById(id);
		
		map.addAttribute("fazenda", fazenda);
		
		return "/ilionnet2/terrafos/fazenda-form";
	}
	
	@ModelAttribute("estados")
	public UF[] popularSelectEstados() {
		return UF.values();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={"/ilionnet/terrafos/fazendas/novo", "/ilionnet/terrafos/fazendas/{id}/editar"})
	public String salvar(
			@ModelAttribute("fazenda") Fazenda fazenda, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			fazenda = fazendaNegocio.incluirAtualizar(fazenda);
			
			request.getSession().setAttribute("fazendaSessao", fazenda);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+fazenda.getId()+"/editar?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return "/ilionnet2/terrafos/fazenda-form";
		}
	}
}
