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
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaNegocio;
import ilion.terrafos.cadastros.negocio.FazendaVH;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/fazendas/")
@UsuarioAppToken
public class FazendasResource extends CustomRestErrorController {
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@Autowired
	private FazendaNegocio fazendaNegocio;
	
	@GetMapping
	public ResponseEntity<APIResponse> listar(HttpServletRequest request) {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		Usuario usuario = usuarioNegocio.consultarPorId(idUsuario);
		
		List<Fazenda> fazendas = usuario.getFazendas();
		
		List<FazendaVH> fazendasVH = fazendaNegocio.carregarVinculos(fazendas);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(fazendasVH), HttpStatus.OK);
	}
	
	@GetMapping("{id}/")
	public ResponseEntity<APIResponse> consultar(@PathVariable Long id, HttpServletRequest request) {
		
		Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		Usuario usuario = usuarioNegocio.consultarPorId(idUsuario);
		
		Fazenda fazenda = null;
		
		for (Fazenda f : usuario.getFazendas()) {
			
			if( f.getId().equals(id) ) {
				fazenda = f;
			}
		}
		
		return new ResponseEntity<APIResponse>(APIResponse.success(fazenda), HttpStatus.OK);
	}
	
	
}
