package ilion.vitazure.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ilion.util.Uteis;
import ilion.vitazure.enumeradores.StatusEnum;

@Entity
@Table(name = "agenda")
public class Agenda {


	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "agenda_id_seq")
	  @SequenceGenerator(name = "agenda_id_seq", sequenceName = "agenda_id_seq", allocationSize = 1)
	  private Long id;
	  
	  @ManyToOne
	  private Pessoa paciente;
	  
	  @ManyToOne
	  private Profissional profissional;
	  
	  private Date dataHoraAgendamento;
	  
	  private Boolean online;
	  
	  private Boolean presencial;
	  
	  private String urlAtendimentoOnline;
	 
	  private String hostUrlAtendimentoOnline;
	  
	  @Enumerated(EnumType.STRING)
	  private StatusEnum status;
	  
	  private BigDecimal valorConsulta;

	  private String meetingId;
	  
	  private String horaFinalAtendimento;

	  private String tokenTransacaoPagamentoConsulta;
	  
	  private Integer idTransacao;
	  
	  private Integer avaliacaoAtendimentoNota;
	  
	  private String avaliacaoAtendimentoObservacao;
	  
	  private Long idAgendaReagendamento;
	  
	  private Boolean reagendamentoConcluido;
	  
	  private Boolean enviadoAlerta;
	  
	  private Date dataHoraEnviadoAlerta;
	  
	  private String tipoPagamento;
	  
	  private String urlPagamento;
	  
	  public Long getId() {
			if (id == null) {
				id = 0l;
			}
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public Pessoa getPaciente() {
			if (paciente == null) {
				paciente = new Pessoa();
			}
			return paciente;
		}
	
		public void setPaciente(Pessoa paciente) {
			this.paciente = paciente;
		}
	
		public Profissional getProfissional() {
			if (profissional == null) {
				profissional = new Profissional();
			}
			return profissional;
		}
	
		public void setProfissional(Profissional profissional) {
			this.profissional = profissional;
		}
	
		public Date getDataHoraAgendamento() {
			return dataHoraAgendamento;
		}
	
		public void setDataHoraAgendamento(Date dataHoraAgendamento) {
			this.dataHoraAgendamento = dataHoraAgendamento;
		}
	
		public Boolean getOnline() {
			if (online == null) {
				online = Boolean.FALSE;
			}
			return online;
		}
	
		public void setOnline(Boolean online) {
			this.online = online;
		}
	
		public Boolean getPresencial() {
			if (presencial == null) {
				presencial = Boolean.FALSE;
			}
			return presencial;
		}
	
		public void setPresencial(Boolean presencial) {
			this.presencial = presencial;
		}
	
		public String getUrlAtendimentoOnline() {
			if (urlAtendimentoOnline == null) {
				urlAtendimentoOnline = "";
			}
			return urlAtendimentoOnline;
		}
	
		public void setUrlAtendimentoOnline(String urlAtendimentoOnline) {
			this.urlAtendimentoOnline = urlAtendimentoOnline;
		}
	
		public StatusEnum getStatus() {
			if (status == null) {
				status = StatusEnum.PENDENTE;
			}
			return status;
		}
	
		public void setStatus(StatusEnum status) {
			this.status = status;
		}
	
		public BigDecimal getValorConsulta() {
			if (valorConsulta == null) {
				valorConsulta = BigDecimal.ZERO;
			}
			return valorConsulta;
		}
	
		public void setValorConsulta(BigDecimal valorConsulta) {
			this.valorConsulta = valorConsulta;
		}
	  
		public String getMeetingId() {
			return meetingId;
		}

		public void setMeetingId(String meetingId) {
			this.meetingId = meetingId;
		}
		
		public String getHoraFinalAtendimento() {
			return horaFinalAtendimento;
		}

		public void setHoraFinalAtendimento(String horaFinalAtendimento) {
			this.horaFinalAtendimento = horaFinalAtendimento;
		}

		public Agenda(Pessoa paciente,Profissional profissional , Date dataHoraAgendamento, Boolean online , Boolean presencial ,String urlAtendimentoOnline , StatusEnum status , BigDecimal valorConsulta , String meetingId) {
		   this.paciente = paciente;
		   this.profissional= profissional;
		   this.dataHoraAgendamento = dataHoraAgendamento;
		   this.online = online;
		   this.presencial = presencial;
		   this.urlAtendimentoOnline = urlAtendimentoOnline;
		   this.status = status;
		   this.valorConsulta = valorConsulta;
		   this.meetingId = meetingId;
	   }
		
		public Agenda() {
		}	
	  
		public String getDataHoraApresentar() {
			return Uteis.formatDataLocateBrasil(getDataHoraAgendamento(), "dd/MM/YYYY HH:mm");
		}

		public String getTokenTransacaoPagamentoConsulta() {
			return tokenTransacaoPagamentoConsulta;
		}

		public void setTokenTransacaoPagamentoConsulta(String tokenTransacaoPagamentoConsulta) {
			this.tokenTransacaoPagamentoConsulta = tokenTransacaoPagamentoConsulta;
		}

		public Integer getIdTransacao() {
			return idTransacao;
		}

		public void setIdTransacao(Integer idTransacao) {
			this.idTransacao = idTransacao;
		}

		public String getHostUrlAtendimentoOnline() {
			return hostUrlAtendimentoOnline;
		}

		public void setHostUrlAtendimentoOnline(String hostUrlAtendimentoOnline) {
			this.hostUrlAtendimentoOnline = hostUrlAtendimentoOnline;
		}
		
		public String getDataHoraLiberarLink() {
			return Uteis.formatarDataHora(Uteis.subtrair(getDataHoraAgendamento(), Calendar.MINUTE, 5), "HH:mm");
		}
		public String getDataHoraBloquearLink() {
			return Uteis.formatarDataHora(Uteis.acrescentar(getDataHoraAgendamento(), Calendar.MINUTE, (Integer.parseInt(getProfissional().getDuracaoAtendimento().getNome()) + 5)), "HH:mm");
		}
		public String getDataHoraAlterAtendimento() {
			return Uteis.formatarDataHora(Uteis.subtrair(getDataHoraAgendamento(), Calendar.MINUTE, 5), "HH:mm");
		}
		public String getDataHoraAvisoAtendimento() {
			return Uteis.formatarDataHora(Uteis.subtrair(getDataHoraAgendamento(), Calendar.MINUTE, 35), "HH:mm");
		}
		
		@Override
		public String toString() {
			return "Agenda[{" + id + "," + getDataHoraLiberarLink() + ","+getDataHoraBloquearLink() + ","+getDataHoraAlterAtendimento() + ","+ urlAtendimentoOnline + "}]";
		}
		
		public String getHoraInicioAgenda() {
			return Uteis.formatDataLocateBrasil(getDataHoraAgendamento(), "HH:mm");
		}
		
		public String getHoraAgendaEmail() {
			String campoFormatado = Uteis.formatDataLocateBrasil(getDataHoraAgendamento(), "HH- mm");
			campoFormatado = campoFormatado.replaceAll("-", "h");
			return campoFormatado;
		}
		public String getDataAgendaEmail() {
			return Uteis.formatDataLocateBrasil(getDataHoraAgendamento(), "dd/MM/YYYY");
		}
		
		public Integer getAvaliacaoAtendimentoNota() {
			return avaliacaoAtendimentoNota;
		}

		public void setAvaliacaoAtendimentoNota(Integer avaliacaoAtendimentoNota) {
			this.avaliacaoAtendimentoNota = avaliacaoAtendimentoNota;
		}

		public String getAvaliacaoAtendimentoObservacao() {
			return avaliacaoAtendimentoObservacao;
		}

		public void setAvaliacaoAtendimentoObservacao(String avaliacaoAtendimentoObservacao) {
			this.avaliacaoAtendimentoObservacao = avaliacaoAtendimentoObservacao;
		}

		public Long getIdAgendaReagendamento() {
			return idAgendaReagendamento;
		}

		public void setIdAgendaReagendamento(Long idAgendaReagendamento) {
			this.idAgendaReagendamento = idAgendaReagendamento;
		}

		public Boolean getReagendamentoConcluido() {
			if(reagendamentoConcluido == null) {
				reagendamentoConcluido = Boolean.FALSE;
			}
			return reagendamentoConcluido;
		}

		public void setReagendamentoConcluido(Boolean reagendamentoConcluido) {
			this.reagendamentoConcluido = reagendamentoConcluido;
		}

		public Boolean getEnviadoAlerta() {
			if(enviadoAlerta == null) {
			   enviadoAlerta = Boolean.FALSE;
			}
			return enviadoAlerta;
		}

		public void setEnviadoAlerta(Boolean enviadoAlerta) {
			this.enviadoAlerta = enviadoAlerta;
		}

		public Date getDataHoraEnviadoAlerta() {
			return dataHoraEnviadoAlerta;
		}

		public void setDataHoraEnviadoAlerta(Date dataHoraEnviadoAlerta) {
			this.dataHoraEnviadoAlerta = dataHoraEnviadoAlerta;
		}

		public String getTipoPagamento() {
			return tipoPagamento;
		}

		public void setTipoPagamento(String tipoPagamento) {
			this.tipoPagamento = tipoPagamento;
		}

		public String getUrlPagamento() {
			return urlPagamento;
		}

		public void setUrlPagamento(String urlPagamento) {
			this.urlPagamento = urlPagamento;
		}
		
		
		
}
