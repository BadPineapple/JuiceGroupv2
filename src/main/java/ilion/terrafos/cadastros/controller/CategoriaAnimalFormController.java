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
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.CategoriaAnimalNegocio;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("categoriaAnimal")
@UsuarioLogado
public class CategoriaAnimalFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(CategoriaAnimalFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/categoria-animal-form";
	
	@Autowired
	private CategoriaAnimalNegocio categoriaAnimalNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/categorias-animal")
	public String novo(ModelMap map, HttpSession session) {
		
		Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		 
		CategoriaAnimal categoriaAnimal = new CategoriaAnimal();
		categoriaAnimal.setFazenda(fazenda);
		
		map.addAttribute("categoriaAnimal", categoriaAnimal);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/categorias-animal/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		CategoriaAnimal categoriaAnimal = categoriaAnimalNegocio.findById(id);
		
		map.addAttribute("categoriaAnimal", categoriaAnimal);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("categoriasAnimal")
	public List<CategoriaAnimal> produtos(@PathVariable Long idFazenda) {
		return categoriaAnimalNegocio.listar(new Fazenda(idFazenda));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/categorias-animal", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/categorias-animal/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("categoriaAnimal") CategoriaAnimal categoriaAnimal, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			categoriaAnimal = categoriaAnimalNegocio.incluirAtualizar(categoriaAnimal);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+categoriaAnimal.getFazenda().getId()+"/categorias-animal?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
