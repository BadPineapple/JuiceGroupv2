package ilion.email.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ilion.SpringApplicationContext;
import ilion.util.Uteis;

@Component
public class EnvioDeEmailTimer {
	
	static Logger logger = Logger.getLogger(EnvioDeEmailTimer.class);
	
	@Autowired
	private EmailNegocio emailNegocio;
	
	private Integer INTERVALO_EM_SEGUNDOS_COM_EMAIL = 60;
	private Integer INTERVALO_EM_SEGUNDOS_SEM_EMAIL = 60;
	
	private Timer timer;
	
	public EnvioDeEmailTimer() {
		super();
		
		timer = new Timer();
	}
	
	public void init() {
		
		init(INTERVALO_EM_SEGUNDOS_COM_EMAIL);
		
	}
	
	public void init(Integer intervaloEmSegundos) {
		
		Date dataAgendada = Uteis.acrescentar(new Date(), Calendar.SECOND, intervaloEmSegundos);
		
		timer.schedule(new EnvioDeEmailTimerTask(emailNegocio), dataAgendada);
		
	}
	
	private class EnvioDeEmailTimerTask extends TimerTask {
		
		private EmailNegocio emailNegocio;
		
		public EnvioDeEmailTimerTask(EmailNegocio emailNegocio) {
			super();
			this.emailNegocio = emailNegocio;
		}
		
		public void run() {
			
			Integer intervaloEmSegundos = INTERVALO_EM_SEGUNDOS_COM_EMAIL;
			
			try {
				
				Boolean possuiEmails = emailNegocio.enviarEmail();
				
				if( ! possuiEmails ) {
					intervaloEmSegundos = INTERVALO_EM_SEGUNDOS_SEM_EMAIL;
				}
				
			} catch (Exception e) {
				String m = "ERRO Envio de E-mail";
				Logger.getLogger(EnvioDeEmailTimerTask.class).error(m, e);
			}
			
			EnvioDeEmailTimer envioDeEmailTimer = 
					SpringApplicationContext.getBean(EnvioDeEmailTimer.class);
			
			envioDeEmailTimer.init(intervaloEmSegundos);
		}
	}
}