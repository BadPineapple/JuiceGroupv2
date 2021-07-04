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
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.CategoriaAnimalNegocio;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/fazendas/")
@UsuarioAppToken
public class CategoriasDeAnimaisResource extends CustomRestErrorController {
	
	@Autowired
	private CategoriaAnimalNegocio categoriaAnimalNegocio;
	
	@GetMapping("{idFazenda}/categorias-de-animais/")
	public ResponseEntity<APIResponse> listarPastos(
			@PathVariable Long idFazenda,
			HttpServletRequest request) {
		
		//Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		List<CategoriaAnimal> categorias = categoriaAnimalNegocio.listar(new Fazenda(idFazenda));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(categorias), HttpStatus.OK);
	}
	
	
}
