package ilion.terrafos.animais.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ilion.CustomRestErrorController;
import ilion.admin.negocio.Usuario;
import ilion.terrafos.animais.negocio.BaixasDTO;
import ilion.terrafos.animais.negocio.FormacaoDeLoteDTO;
import ilion.terrafos.animais.negocio.LoteNegocio;
import ilion.terrafos.animais.negocio.MotivoBaixa;
import ilion.terrafos.animais.negocio.MovimentoDeLoteDTO;
import ilion.terrafos.animais.negocio.NascimentosDTO;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/")
@UsuarioAppToken
public class LoteResource extends CustomRestErrorController {
	
	@Autowired
	private LoteNegocio loteNegocio;
	
	@PostMapping("formacao-de-lote/")
	public ResponseEntity<APIResponse> salvar(
			@RequestBody FormacaoDeLoteDTO formacaoDeLote,
			HttpServletRequest request) throws Exception {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		loteNegocio.salvar(formacaoDeLote, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(""), HttpStatus.OK);
	}
	
	@PostMapping("movimentacao-de-lote/")
	public ResponseEntity<APIResponse> movimentacaoDeLote(
			@RequestBody MovimentoDeLoteDTO movimentoDeLote,
			HttpServletRequest request) throws Exception {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		loteNegocio.salvar(movimentoDeLote, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(""), HttpStatus.OK);
	}
	
	@GetMapping("/pastos/{id}/total-de-animais/")
	public ResponseEntity<APIResponse> totalDeAnimais(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception {
		
		List<Map<String, Object>> lista = loteNegocio.listarSaldoDeAnimaisPorPasto(new Pasto(id));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(lista), HttpStatus.OK);
	}
	
	@PostMapping("nascimentos/")
	public ResponseEntity<APIResponse> nascimentos(
			@RequestBody NascimentosDTO nascimentos,
			HttpServletRequest request) throws Exception {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		loteNegocio.nascimentos(nascimentos, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(""), HttpStatus.OK);
	}
	
	@GetMapping("motivo-de-baixas")
	public ResponseEntity<APIResponse> motivoDeBaixas() throws Exception {
		
		List<Map<String, String>> motivos = MotivoBaixa.toList();
		
		return new ResponseEntity<APIResponse>(APIResponse.success(motivos), HttpStatus.OK);
	}
	
	@PostMapping("baixas/")
	public ResponseEntity<APIResponse> baixas(
			@RequestBody BaixasDTO baixas,
			HttpServletRequest request) throws Exception {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		loteNegocio.baixas(baixas, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(""), HttpStatus.OK);
	}
}
