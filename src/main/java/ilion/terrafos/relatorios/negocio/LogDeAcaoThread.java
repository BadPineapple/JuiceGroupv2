package ilion.terrafos.relatorios.negocio;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ilion.SpringApplicationContext;

@Component
@Scope("prototype")
public class LogDeAcaoThread extends Thread {
	
	static Logger logger = Logger.getLogger(LogDeAcaoThread.class);
	
	@Autowired
	private LogDeAcaoNegocio logDeAcaoNegocio;
	
	private LogDeAcao logDeAcao;
	
	private LogDeAcaoThread() {
		super();
	}
	
	public static void incluiLog(LogDeAcao logDeAcao) {
		
		LogDeAcaoThread t = SpringApplicationContext.getBean(LogDeAcaoThread.class);
		
		t.logDeAcao = logDeAcao;
		
		t.start();
	}

	@Override
	public void run() {
		
		try {
			
			logDeAcaoNegocio.incluir(logDeAcao);
			
		} catch (Exception e) {
			logger.error("erro ao gravar log", e);
		}
		
	}
	
}
