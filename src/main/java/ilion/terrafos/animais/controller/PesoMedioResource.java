package ilion.terrafos.animais.controller;

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
import ilion.terrafos.animais.negocio.FormacaoDeLoteDTO;
import ilion.terrafos.animais.negocio.PesagensDTO;
import ilion.terrafos.animais.negocio.PesoMedioNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/pesagens/")
@UsuarioAppToken
public class PesoMedioResource extends CustomRestErrorController {
	
	@Autowired
	private PesoMedioNegocio pesoMedioNegocio;
	
	@PostMapping
	public ResponseEntity<APIResponse> salvar(
			@RequestBody PesagensDTO pesagens,
			HttpServletRequest request) throws Exception {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		pesoMedioNegocio.salvar(pesagens, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(""), HttpStatus.OK);
	}
	
	
}
