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
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Raca;
import ilion.terrafos.cadastros.negocio.RacaNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/fazendas/")
@UsuarioAppToken
public class RacasResource extends CustomRestErrorController {
	
	@Autowired
	private RacaNegocio racaNegocio;
	
	@GetMapping("{idFazenda}/racas/")
	public ResponseEntity<APIResponse> listar(
			@PathVariable Long idFazenda,
			HttpServletRequest request) {
		
		//Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		List<Raca> racas = racaNegocio.listar(new Fazenda(idFazenda));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(racas), HttpStatus.OK);
	}
	
	
}
