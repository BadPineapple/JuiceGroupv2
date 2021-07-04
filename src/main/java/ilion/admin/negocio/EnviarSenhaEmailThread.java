package ilion.admin.negocio;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ilion.SpringApplicationContext;

@Component
@Scope("prototype")
public class EnviarSenhaEmailThread extends Thread {
	
	static Logger logger = Logger.getLogger(EnviarSenhaEmailThread.class);
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	private Usuario usuario;
	
	public static void novo(Usuario usuario) {
		
		EnviarSenhaEmailThread t = SpringApplicationContext.getBean(EnviarSenhaEmailThread.class);
		t.usuario = usuario;
		t.start();
		
	}
	
	@Override
	public void run() {
		
		try {
			
			Thread.sleep(5*1000);
			
			usuarioNegocio.enviarSenhaEmail(usuario);
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
	}
	
}