package ilion.vitazure.negocio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ilion.SpringApplicationContext;
import ilion.vitazure.model.Pessoa;

@Component
@Scope("prototype")
public class EnviarEmailTheread extends Thread{
		
		static Logger logger = Logger.getLogger(EnviarEmailTheread.class);
		
		@Autowired
		private PessoaNegocio pessoaNegocio;
		
		private Pessoa pessoa;
		
		public static void novo(Pessoa pessoa) {
			
			EnviarEmailTheread t = SpringApplicationContext.getBean(EnviarEmailTheread.class);
			t.pessoa = pessoa;
			t.start();
			
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(5*1000);
				pessoaNegocio.esqueciMinhaSenhaEmail(pessoa);
			} catch (Exception e) {
				logger.error("", e);
			}
			
		}
		
	}
	
