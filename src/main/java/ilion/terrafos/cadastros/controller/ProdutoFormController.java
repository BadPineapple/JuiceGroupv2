package ilion.terrafos.cadastros.controller;

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
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Produto;
import ilion.terrafos.cadastros.negocio.ProdutoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("produto")
@UsuarioLogado
public class ProdutoFormController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(ProdutoFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/produto-form";
	
	@Autowired
	private ProdutoNegocio produtoNegocio;
		
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/produtos")
	public String novo(ModelMap map, HttpSession session) {
		
		Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		
		Produto produto = new Produto();
		produto.setFazenda(fazenda);
		
		map.addAttribute("produto", produto);
		
		return TEMPLATE;
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/produtos/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		Produto produto = produtoNegocio.findById(id);
		
		map.addAttribute("produto", produto);
		
		return TEMPLATE;
	}
	
	@ModelAttribute("produtos")
	public List<Produto> produtos(@PathVariable Long idFazenda) {
		return produtoNegocio.listar(new Fazenda(idFazenda));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "data", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/produtos", 
			"/ilionnet/terrafos/fazendas/{idFazenda}/produtos/{id}/editar"
	})
	public String salvar(
			@ModelAttribute("produto") Produto produto, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			produto = produtoNegocio.incluirAtualizar(produto);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+produto.getFazenda().getId()+"/produtos?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
