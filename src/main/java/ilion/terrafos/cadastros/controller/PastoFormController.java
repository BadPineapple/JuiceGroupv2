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
import ilion.admin.negocio.UF;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Forrageira;
import ilion.terrafos.cadastros.negocio.ForrageiraNegocio;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.terrafos.cadastros.negocio.Retiro;
import ilion.terrafos.cadastros.negocio.RetiroNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("pasto")
@UsuarioLogado
public class PastoFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(PastoFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/pasto-form";
	
	@Autowired
	private RetiroNegocio retiroNegocio;
	
	@Autowired
	private ForrageiraNegocio forrageiraNegocio;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/pastos/novo")
	public String novo(ModelMap map, HttpSession session) {
		
		Pasto pasto = new Pasto();
		pasto.setRetiro(new Retiro());
		pasto.setForrageira(new Forrageira());
		
		map.addAttribute("pasto", pasto);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/pastos/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		Pasto pasto = pastoNegocio.findById(id);
		
		map.addAttribute("pasto", pasto);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("estados")
	public UF[] popularSelectEstados(@PathVariable Long idFazenda) {
		return UF.values();
	}
	
	@ModelAttribute("retiros")
	public List<Retiro> popularSelectRetiros(@PathVariable Long idFazenda) {
		return retiroNegocio.listar(new Fazenda(idFazenda));
	}
	
	@ModelAttribute("forrageiras")
	public List<Forrageira> popularSelectForrageiras(@PathVariable Long idFazenda) {
		return forrageiraNegocio.listar(new Fazenda(idFazenda));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/pastos/novo", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/pastos/{id}/editar"
	})
	public String salvar(
			@PathVariable Long idFazenda, 
			@ModelAttribute("pasto") Pasto pasto, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			pasto = pastoNegocio.incluirAtualizar(pasto);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/pastos/"+pasto.getId()+"/editar?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
