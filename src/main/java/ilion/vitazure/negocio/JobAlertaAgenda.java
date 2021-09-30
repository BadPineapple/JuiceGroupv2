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
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.Pessoa;

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
				List<Agenda> agendaNaoCompareceu = agendaNegocio.consultarAgendaDiaNaoAtendida(new Date());
				agendaAlerta.stream().forEach(agenda -> envioEmailConsulta.enviarAlertaAgenda(agenda));
				agendaNaoCompareceu.stream().forEach(naoCompareceu -> agendaNaoCompareceu2(naoCompareceu));
			} catch (Exception e) {
				logger.error("erro envio Alerta Mensagem", e);
			}
			
			JobAlertaAgenda jobAlertaAgenda = SpringApplicationContext.getBean(JobAlertaAgenda.class);
			
			jobAlertaAgenda.init( INTERVALO_EM_SEGUNDOS_TIMER );
		}
	}
	
	public void agendaNaoCompareceu2(Agenda agenda) {
		if(Uteis.validarDataInicialMaiorFinalComHora(Uteis.formatarDataHora(new Date(), "HH:mm"), new Date(), Uteis.acrescentar(agenda.getDataHoraAgendamento(), Calendar.MINUTE, 62))) {
			try {
				agendaNegocio.alterarAgenda(agenda.getId(), StatusEnum.NAO_COMPARECEU.toString() , new Pessoa());
				logger.error("Alterado a situação para não compareceu agenda id "+agenda.getId());
			} catch (Exception e) {
				logger.error("Erro para alterar a situação para não compareceu agenda id "+agenda.getId(), e);
				e.printStackTrace();
			}
		}
	}
	
}
