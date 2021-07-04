package ilion.terrafos.cadastros.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ilion.CustomRestErrorController;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.terrafos.cadastros.negocio.Retiro;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/fazendas/")
@UsuarioAppToken
public class PastosResource extends CustomRestErrorController {
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@GetMapping("{idFazenda}/retiros/{idRetiro}/pastos/")
	public ResponseEntity<APIResponse> listarPastos(
			@PathVariable Long idFazenda, 
			@PathVariable Long idRetiro, 
			HttpServletRequest request) {
		
		//Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		List<Pasto> pastos = pastoNegocio.listar(new Retiro(idRetiro));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(pastos), HttpStatus.OK);
	}
	
	@GetMapping("{idFazenda}/retiros/{idRetiro}/pastos/{id}/")
	public ResponseEntity<APIResponse> consultarPasto(
			@PathVariable Long idFazenda, 
			@PathVariable Long idRetiro, 
			@PathVariable Long id, 
			HttpServletRequest request) {
		
		//Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		Pasto pasto = pastoNegocio.consultarPorIdComSaldos(new Pasto(id));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(pasto), HttpStatus.OK);
	}
	
}
