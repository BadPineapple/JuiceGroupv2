package ilion.vitazure.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ilion.SpringApplicationContext;
import ilion.util.Uteis;
import ilion.vitazure.model.Agenda;

@Component
public class JobAlertaAgenda {

	static Logger logger = Logger.getLogger(JobAlertaAgenda.class);
	
	@Autowired
	private AgendaNegocio agendaNegocio;
	
	@Autowired
	private EnvioEmailConsulta envioEmailConsulta;
	
	private Integer INTERVALO_EM_SEGUNDOS_TIMER = 60;
	
	private Timer timer;
	
	public JobAlertaAgenda() {
		super();
		timer = new Timer();
	}
	
	public void init() {
		init(INTERVALO_EM_SEGUNDOS_TIMER);
	}
	
	public void init(Integer intervaloEmSegundos) {

		Date dataAgendada = Uteis.acrescentar(new Date(), Calendar.SECOND, intervaloEmSegundos);

		timer.schedule(new JobAlertaAgendaTask(agendaNegocio), dataAgendada);

	}
	
	private class JobAlertaAgendaTask extends TimerTask {

		private AgendaNegocio agendaNegocio;

		public JobAlertaAgendaTask(AgendaNegocio agendaNegocio) {
			super();
			this.agendaNegocio = agendaNegocio;
		}

		public void run() {
			
			try {
				List<Agenda> agendaAlerta = agendaNegocio.consultarAgendaDia(Uteis.subtrair(new Date(), Calendar.MINUTE, 32));
				agendaAlerta.stream().forEach(agenda -> envioEmailConsulta.enviarAlertaAgenda(agenda));
			} catch (Exception e) {
				logger.error("erro envio Alerta Mensagem", e);
			}
			
			JobAlertaAgenda jobAlertaAgenda = SpringApplicationContext.getBean(JobAlertaAgenda.class);
			
			jobAlertaAgenda.init( INTERVALO_EM_SEGUNDOS_TIMER );
		}
	}
	
	
	
}
