package ilion;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.email.negocio.EnvioDeEmailTimer;
import ilion.util.Uteis;
import ilion.vitazure.negocio.JobAlertaAgenda;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	static Logger logger = Logger.getLogger(ApplicationStartup.class);
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private EnvioDeEmailTimer envioDeEmailTimer;
	
	@Autowired
	private JobAlertaAgenda jobAlertaAgenda;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		
		logger.info("### CONTEXTO "+propNegocio.findValueById(PropEnum.NOME_EMPRESA)+" INICIADO! ###");
		
		if( SpringApplicationContext.ehAmbienteTeste() ) {
			logger.info("executando em modo teste");
			return;
		}
		
		Uteis.caminhoFisico = servletContext.getRealPath("/");
		logger.info("RealPath: "+Uteis.caminhoFisico);
		
		envioDeEmailTimer.init();
		jobAlertaAgenda.init();
		
	}
	
	

}
