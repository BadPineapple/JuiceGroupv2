package ilion.terrafos.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ilion.CustomRestErrorController;
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.terrafos.auth.negocio.TokenDTO;
import ilion.terrafos.auth.negocio.UsuarioLoginVH;
import ilion.terrafos.auth.negocio.UsuarioProfileVH;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.json.APIResponse;

@RestController
@RequestMapping("/v1/auth/")
@AcessoLivre
public class LoginAppResource extends CustomRestErrorController {
	
	static Logger logger = Logger.getLogger(LoginAppResource.class);
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@PostMapping(value="/login/")
	public Object login(@RequestBody UsuarioLoginVH usuarioLogin) throws Exception {
		
		Usuario usuario = usuarioNegocio.efetuarLogin(usuarioLogin.getLogin(), usuarioLogin.getSenha());
		
		UsuarioProfileVH usuarioProfileVH = new UsuarioProfileVH(usuario);
		
		TokenDTO tokenDTO = usuarioNegocio.loginToken(usuarioProfileVH, "Login");
		
		Map<String, Object> retorno = new HashMap<>();
		retorno.put("usuario", usuarioProfileVH);
		retorno.put("token", tokenDTO.getToken());
		
		return new ResponseEntity<APIResponse>(APIResponse.success(retorno), HttpStatus.OK);
	}

}