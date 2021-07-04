package ilion.terrafos.cadastros.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ilion.CustomErrorController;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.PlanoDeAcao;
import ilion.terrafos.cadastros.negocio.PlanoDeAcaoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class PlanoDeAcaoController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(PlanoDeAcaoController.class);
	
	@Autowired
	private PlanoDeAcaoNegocio planoDeAcaoNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/planos-de-acao")
	public String busca(@PathVariable Long idFazenda, HttpServletRequest request) {
		
		List<PlanoDeAcao> planos = planoDeAcaoNegocio.listar(new Fazenda(idFazenda));
		request.setAttribute("planosDeAcao", planos);
		
		return "/ilionnet2/terrafos/planos-de-acao";
	}
	
//	@GetMapping("/ilionnet/terrafos/fazendas/{id}/excluir")
//	public String fazendaExcluir(
//			@PathVariable Long id, 
//			HttpServletRequest request, 
//			RedirectAttributes redirectAttributes) {
//		
////		Usuario usuarioSessao = 
////				(Usuario) request.getSession().getAttribute("usuarioSessao");
//		
//		try {
//			
//			fazendaNegocio.excluir(new Fazenda(id));
//			
//			return "redirect:/ilionnet/terrafos/fazendas?m=fazenda-excluida";
//		} catch (Exception e) {
//			logger.error("", e);
//			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
//			return "redirect:/ilionnet/terrafos/fazendas?m=erro";
//		}
//		
//	}
}