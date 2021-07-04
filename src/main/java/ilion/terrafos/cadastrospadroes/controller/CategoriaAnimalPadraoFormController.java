package ilion.terrafos.cadastrospadroes.controller;

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
import ilion.terrafos.cadastrospadroes.negocio.CategoriaAnimalPadrao;
import ilion.terrafos.cadastrospadroes.negocio.CategoriaAnimalPadraoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("categoriaAnimal")
@UsuarioLogado("terrafos_cadastros_padroes")
public class CategoriaAnimalPadraoFormController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(CategoriaAnimalPadraoFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/cadastros-padroes/categoria-animal-padrao-form";
	
	@Autowired
	private CategoriaAnimalPadraoNegocio categoriaAnimalPadraoNegocio;
	
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/categorias-animal")
	public String novo(ModelMap map, HttpSession session) {
		 
		CategoriaAnimalPadrao categoriaAnimalPadrao = new CategoriaAnimalPadrao();
		
		map.addAttribute("categoriaAnimal", categoriaAnimalPadrao);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/categorias-animal/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		CategoriaAnimalPadrao categoriaAnimalPadrao = categoriaAnimalPadraoNegocio.findById(id);
		
		map.addAttribute("categoriaAnimal", categoriaAnimalPadrao);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("categoriasAnimal")
	public List<CategoriaAnimalPadrao> listar() {
		return categoriaAnimalPadraoNegocio.listar();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/cadastros-padroes/categorias-animal", 
			"/ilionnet/terrafos/cadastros-padroes/categorias-animal/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("categoriaAnimal") CategoriaAnimalPadrao categoriaAnimal, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			categoriaAnimal = categoriaAnimalPadraoNegocio.incluirAtualizar(categoriaAnimal);
			
			return "redirect:/ilionnet/terrafos/cadastros-padroes/categorias-animal?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
