package ilion.terrafos.cadastrospadroes.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.CustomErrorController;
import ilion.terrafos.cadastrospadroes.negocio.ForrageiraPadrao;
import ilion.terrafos.cadastrospadroes.negocio.ForrageiraPadraoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("forrageira")
@UsuarioLogado
public class ForrageiraPadraoController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(ForrageiraPadraoController.class);
	
	@Autowired
	private ForrageiraPadraoNegocio forrageiraPadraoNegocio;
	
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/forrageiras/{id}/excluir")
	public String excluir(
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes){
		
		try {
			
			forrageiraPadraoNegocio.excluir(new ForrageiraPadrao(id));
			
			redirectAttributes.addFlashAttribute("msgSuccess", "Forrageira excluída com sucesso.");
			
			return "redirect:/ilionnet/terrafos/cadastros-padroes/forrageiras?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			
			return "redirect:/ilionnet/terrafos/cadastros-padroes/forrageiras?m="+e.getMessage();
		}
	}
	
}
