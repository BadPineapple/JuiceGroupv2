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
import ilion.terrafos.cadastros.negocio.Retiro;
import ilion.terrafos.cadastros.negocio.RetiroNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/fazendas/")
@UsuarioAppToken
public class RetirosResource extends CustomRestErrorController {
	
	@Autowired
	private RetiroNegocio retiroNegocio;
	
	@GetMapping("{idFazenda}/retiros/")
	public ResponseEntity<APIResponse> listarRetiros(@PathVariable Long idFazenda, HttpServletRequest request) {
		
		//Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		List<Retiro> retiros = retiroNegocio.listar(new Fazenda(idFazenda));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(retiros), HttpStatus.OK);
	}
	
	
}
