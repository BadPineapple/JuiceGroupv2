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
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.CategoriaAnimalNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("categoriaAnimal")
@UsuarioLogado
public class CategoriaAnimalController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(CategoriaAnimalController.class);
	
	@Autowired
	private CategoriaAnimalNegocio categoriaAnimalNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/categorias-animal/{id}/excluir")
	public String excluir(
			@PathVariable Long idFazenda, 
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes){
		
		try {
			
			categoriaAnimalNegocio.excluir(new CategoriaAnimal(id));
			
			redirectAttributes.addFlashAttribute("msgSuccess", "Registro excluído com sucesso.");
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/categorias-animal?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/categorias-animal?m="+e.getMessage();
		}
	}
	
}
