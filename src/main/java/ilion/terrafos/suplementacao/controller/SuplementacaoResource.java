package ilion.terrafos.suplementacao.controller;

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
import ilion.terrafos.suplementacao.negocio.SuplementacaoDTO;
import ilion.terrafos.suplementacao.negocio.SuplementacaoNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/")
@UsuarioAppToken
public class SuplementacaoResource extends CustomRestErrorController {
	
	@Autowired
	private SuplementacaoNegocio suplementacaoNegocio;
	
	@PostMapping("/suplementacao/")
	public ResponseEntity<APIResponse> salvar(
			@RequestBody SuplementacaoDTO sDTO, 
			HttpServletRequest request) {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		suplementacaoNegocio.incluir(sDTO, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success("ok"), HttpStatus.OK);
	}
	
	
}
