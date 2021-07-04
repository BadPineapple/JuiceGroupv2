package ilion.terrafos.entrevero.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ilion.CustomRestErrorController;
import ilion.admin.negocio.Usuario;
import ilion.terrafos.entrevero.negocio.EntreveroDTO;
import ilion.terrafos.entrevero.negocio.EntreveroNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/")
@UsuarioAppToken
public class EntreveroResource extends CustomRestErrorController {
	
	@Autowired
	private EntreveroNegocio entreveroNegocio;
	
	@PostMapping("/entrevero/")
	public ResponseEntity<APIResponse> salvarEntrevero(
			@RequestBody EntreveroDTO entreveroDTO, 
			HttpServletRequest request) {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		entreveroNegocio.incluir(entreveroDTO, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success("ok"), HttpStatus.OK);
	}
	
	
}
