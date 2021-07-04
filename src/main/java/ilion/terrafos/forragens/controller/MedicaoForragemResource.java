package ilion.terrafos.forragens.controller;

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
import ilion.terrafos.forragens.negocio.MedicaoDeForragemDTO;
import ilion.terrafos.forragens.negocio.MedicaoForragemNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/")
@UsuarioAppToken
public class MedicaoForragemResource extends CustomRestErrorController {
	
	@Autowired
	private MedicaoForragemNegocio medicaoForragemNegocio;
	
	@PostMapping("/medicao-de-forragem/")
	public ResponseEntity<APIResponse> salvarMedicao(
			@RequestBody MedicaoDeForragemDTO medicaoDeForragemDTO, 
			HttpServletRequest request) {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		medicaoForragemNegocio.incluir(medicaoDeForragemDTO, new Usuario(idUsuario));
		
		return new ResponseEntity<APIResponse>(APIResponse.success("ok"), HttpStatus.OK);
	}
	
	
}
