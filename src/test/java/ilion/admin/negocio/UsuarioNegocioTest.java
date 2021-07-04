package ilion.admin.negocio;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioNegocioTest {

	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@Test
	public void deveTestarIncluirUsuario() {
		
		usuarioNegocio.inserirDadosIniciais();
		
		Usuario usuario = (Usuario) usuarioNegocio.consultar(Usuario.class, 1l);
		
		Assert.assertEquals("Ilion Solucoes", usuario.getNome());
	}
	
}
