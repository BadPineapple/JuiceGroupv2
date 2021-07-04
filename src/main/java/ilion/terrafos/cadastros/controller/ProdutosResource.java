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
import ilion.terrafos.cadastros.negocio.Produto;
import ilion.terrafos.cadastros.negocio.ProdutoNegocio;
import ilion.util.contexto.autorizacao.UsuarioAppToken;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/terrafos/fazendas/")
@UsuarioAppToken
public class ProdutosResource extends CustomRestErrorController {
	
	@Autowired
	private ProdutoNegocio produtoNegocio;
	
	@GetMapping("{idFazenda}/produtos/")
	public ResponseEntity<APIResponse> listar(
			@PathVariable Long idFazenda,
			HttpServletRequest request) {
		
		//Long idUsuario = (Long) request.getAttribute("idUsuario");
		
		List<Produto> produtos = produtoNegocio.listar(new Fazenda(idFazenda));
		
		return new ResponseEntity<APIResponse>(APIResponse.success(produtos), HttpStatus.OK);
	}
	
	
}
