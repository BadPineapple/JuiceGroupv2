package ilion.vitazure.negocio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ilion.SpringApplicationContext;
import ilion.vitazure.model.Pessoa;

@Component
@Scope("prototype")
public class EnvioEmailConfirmacao extends Thread{
			
	static Logger logger = Logger.getLogger(EnvioEmailConfirmacao.class);
			
	@Autowired
	private PessoaNegocio pessoaNegocio;
			
	private Pessoa pessoa;
			
	public static void novo(Pessoa pessoa) {
				
	EnvioEmailConfirmacao t = SpringApplicationContext.getBean(EnvioEmailConfirmacao.class);
	t.pessoa = pessoa;
	t.start();
				
	}
			
	@Override
	public void run() {
    	try {
	    	Thread.sleep(20*1000);
			pessoaNegocio.emailAtivacao(pessoa);
    	}	
		 catch (Exception e) {
					logger.error("", e);
		}
				
	}
			
}
		

