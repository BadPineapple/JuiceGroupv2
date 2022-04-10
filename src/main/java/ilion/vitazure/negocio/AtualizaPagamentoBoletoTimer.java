package ilion.vitazure.negocio;

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
public class AtualizaPagamentoBoletoTimer {
	
	static final Logger LOGGER = Logger.getLogger(AtualizaPagamentoBoletoTimer.class);
	
	@Autowired
	private PagarMeNegocio pagarMeNegocio;
	
	private Integer INTERVALO_EM_SEGUNDOS = 3600;
	
	private Timer timer;
	
	public AtualizaPagamentoBoletoTimer() {
		super();
		
		timer = new Timer();
	}
	
	public void init() {
		
		init(INTERVALO_EM_SEGUNDOS);
		
	}
	
	public void init(Integer intervaloEmSegundos) {
		
		Date dataAgendada = Uteis.acrescentar(new Date(), Calendar.SECOND, intervaloEmSegundos);
		
		timer.schedule(new AtualizarPagamentoTask(pagarMeNegocio), dataAgendada);
		
	}
	
	private class AtualizarPagamentoTask extends TimerTask {
		
		private PagarMeNegocio pagarMeNegocio;
		
		public AtualizarPagamentoTask(PagarMeNegocio pagarMeNegocio) {
			super();
			this.pagarMeNegocio = pagarMeNegocio;
		}
		
		public void run() {
			
			Integer intervaloEmSegundos = INTERVALO_EM_SEGUNDOS;
			
			try {
				LOGGER.info("Iniciando validação de pagamentos pendentes");
				Boolean possuiPendentes = pagarMeNegocio.atualizarPagamento();
				LOGGER.info("Finalizado validação de pagamentos pendentes");
				
			} catch (Exception e) {
				String m = "ERRO Atualizar Itens Pendentes de Pagamento";
				Logger.getLogger(AtualizarPagamentoTask.class).error(m, e);
			}
			
			AtualizaPagamentoBoletoTimer atualizaPagamentoBoletoTimer = 
					SpringApplicationContext.getBean(AtualizaPagamentoBoletoTimer.class);
			
			atualizaPagamentoBoletoTimer.init(intervaloEmSegundos);
		}
	}
}