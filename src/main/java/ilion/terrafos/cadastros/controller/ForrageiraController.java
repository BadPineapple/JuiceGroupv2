package ilion.terrafos.cadastros.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.CustomErrorController;
import ilion.terrafos.cadastros.negocio.Forrageira;
import ilion.terrafos.cadastros.negocio.ForrageiraNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("forrageira")
@UsuarioLogado
public class ForrageiraController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(ForrageiraController.class);
	
	@Autowired
	private ForrageiraNegocio forrageiraNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/forrageiras/{id}/excluir")
	public String excluir(
			@PathVariable Long idFazenda, 
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes){
		
		try {
			
			forrageiraNegocio.excluir(new Forrageira(id));
			
			redirectAttributes.addFlashAttribute("msgSuccess", "Forrageira excluída com sucesso.");
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/forrageiras?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/forrageiras?m="+e.getMessage();
		}
	}
	
}
