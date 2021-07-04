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
import ilion.terrafos.cadastros.negocio.AvaliacaoDoPasto;
import ilion.terrafos.cadastros.negocio.AvaliacaoDosPastos;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("avaliacaoDosPastos")
@UsuarioLogado
public class AvaliacaoDosPastosFormController extends CustomErrorController {
	
	static Logger logger = Logger.getLogger(AvaliacaoDosPastosFormController.class);
	
	private static String TEMPLATE = "/ilionnet2/terrafos/avaliacao-dos-pastos-form";
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/avaliacao-dos-pastos")
	public String novo(@PathVariable Long idFazenda, ModelMap map, HttpSession session) {
		
		//Fazenda fazenda = (Fazenda) session.getAttribute("fazendaSessao");
		
		List<Pasto> pastos = pastoNegocio.listar(new Fazenda(idFazenda));
		
		for (Pasto pasto : pastos) {
			if( pasto.getAvaliacaoDoPasto() == null ) {
				pasto.setAvaliacaoDoPasto(new AvaliacaoDoPasto());
			}
		}
		
		AvaliacaoDosPastos avaliacaoDosPastos = new AvaliacaoDosPastos();
		avaliacaoDosPastos.setPastos(pastos);
		
		map.addAttribute("avaliacaoDosPastos", avaliacaoDosPastos);
		
		return TEMPLATE;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		for (int i = 0; i < 20; i++) {
			binder.registerCustomEditor(Float.class, "pastos["+i+"].avaliacaoDoPasto.aee", Uteis.novoCustomNumberEditor(Float.class, true));
			binder.registerCustomEditor(Float.class, "pastos["+i+"].avaliacaoDoPasto.of", Uteis.novoCustomNumberEditor(Float.class, true));
			binder.registerCustomEditor(Integer.class, "pastos["+i+"].avaliacaoDoPasto.areaCocho", Uteis.novoCustomNumberEditor(Integer.class, true));			
		}
	}
	
	@PostMapping(value={
			"/ilionnet/terrafos/fazendas/{idFazenda}/avaliacao-dos-pastos"
	})
	public String salvar(
			@PathVariable Long idFazenda, 
			@ModelAttribute("avaliacaoDosPastos") AvaliacaoDosPastos avaliacaoDosPastos, 
			BindingResult bindingResult,
			HttpServletRequest request
			) throws Exception {
		
		try {
			
			pastoNegocio.incluirAtualizar(avaliacaoDosPastos);
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/avaliacao-dos-pastos?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			
			request.setAttribute("hasErrors", bindingResult.getErrorCount());
			
			return TEMPLATE;
		}
	}
}
