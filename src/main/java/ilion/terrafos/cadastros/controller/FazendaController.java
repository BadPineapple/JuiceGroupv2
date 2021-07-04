package ilion.terrafos.cadastros.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaNegocio;
import ilion.terrafos.cadastros.negocio.FazendaPastosNoMapaVH;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import ilion.util.json.APIResponse;

@Controller
@UsuarioLogado
public class FazendaController extends CustomErrorController{
	
	static Logger logger = Logger.getLogger(FazendaController.class);
	
	@Autowired
	private FazendaNegocio fazendaNegocio;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas")
	public String busca(HttpServletRequest request) {
		
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		
		List<Fazenda> fazendas = usuarioSessao.getFazendas();
		
		Boolean permitidoGerenciarFazendas = usuarioSessao.getPermissoes().contains("terrafos_fazendas");
		
		if( permitidoGerenciarFazendas ) {
			fazendas = fazendaNegocio.listar();
		}
		
		request.setAttribute("fazendas", fazendas);
		
		return "/ilionnet2/terrafos/fazendas";
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{id}/selecionar")
	public String selecionar(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		Fazenda fazenda = fazendaNegocio.findById(id);
		request.getSession().setAttribute("fazendaSessao", fazenda);
		
		return "redirect:/ilionnet/terrafos/fazendas?m=fazenda-selecionada";
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{id}/regioes-dos-pastos")
	@ResponseBody
	public ResponseEntity<APIResponse> pastosCoordenadas(
			@PathVariable Long id, 
			HttpServletRequest request) {
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = pastoNegocio.listarRegioesDosPastos(new Fazenda(id));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(fazendaPastosNoMapaVH), HttpStatus.OK);
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