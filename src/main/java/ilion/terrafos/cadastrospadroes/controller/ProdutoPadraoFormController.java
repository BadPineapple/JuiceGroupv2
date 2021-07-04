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
import ilion.terrafos.cadastrospadroes.negocio.ProdutoPadrao;
import ilion.terrafos.cadastrospadroes.negocio.ProdutoPadraoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("produto")
@UsuarioLogado("terrafos_cadastros_padroes")
public class ProdutoPadraoFormController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(ProdutoPadraoFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/cadastros-padroes/produto-padrao-form";
	
	@Autowired
	private ProdutoPadraoNegocio produtoPadraoNegocio;
		
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/produtos")
	public String novo(ModelMap map, HttpSession session) {
		
		ProdutoPadrao produto = new ProdutoPadrao();
		
		map.addAttribute("produto", produto);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/cadastros-padroes/produtos/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		ProdutoPadrao produto = produtoPadraoNegocio.findById(id);
		
		map.addAttribute("produto", produto);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("produtos")
	public List<ProdutoPadrao> produtos() {
		return produtoPadraoNegocio.listar();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "data", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/cadastros-padroes/produtos", 
			"/ilionnet/terrafos/cadastros-padroes/produtos/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("produto") ProdutoPadrao produto, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			produto = produtoPadraoNegocio.incluirAtualizar(produto);
			
			return "redirect:/ilionnet/terrafos/cadastros-padroes/produtos?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
