package ilion.vitazure.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ilion.me.pagar.model.Transaction;
import ilion.me.pagar.model.Transaction.Status;
import ilion.util.Uteis;

@Entity
@Table(name = "pagamento_pagarMe")
public class PagamentoPagarMe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pagamento_pagarMe_id_seq")
	@SequenceGenerator(name = "pagamento_pagarMe_id_seq", sequenceName = "pagamento_pagarMe_id_seq", allocationSize = 1)
	private Long id;
	private String profissional;
	private Long idProfissional;
	private Long idPaciente;
	private Long agenda;
	private Integer idTransacao;
	private String dataTransacao;
	private String status;
	private BigDecimal valorPago;
	private Boolean planoVitazure;
	private String  planoVitazureSelecionado;
	private String dataAgenda;
	private String nomePaciente;
	private String tipoPagamento;
	private String linkPagamento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProfissional() {
		return profissional;
	}
	public void setProfissional(String profissional) {
		this.profissional = profissional;
	}
	public Long getAgenda() {
		return agenda;
	}
	public void setAgenda(Long agenda) {
		this.agenda = agenda;
	}
	public String getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getIdProfissional() {
		return idProfissional;
	}
	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public Integer getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	
	public Boolean getPlanoVitazure() {
		return planoVitazure;
	}
	public void setPlanoVitazure(Boolean planoVitazure) {
		this.planoVitazure = planoVitazure;
	}
	public String getPlanoVitazureSelecionado() {
		return planoVitazureSelecionado;
	}
	public void setPlanoVitazureSelecionado(String planoVitazureSelecionado) {
		this.planoVitazureSelecionado = planoVitazureSelecionado;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getDataAgenda() {
		return dataAgenda;
	}
	public void setDataAgenda(String dataAgenda) {
		this.dataAgenda = dataAgenda;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getLinkPagamento() {
		return linkPagamento;
	}
	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}
	
	public PagamentoPagarMe pagamento(Transaction transacao, Agenda agenda , Profissional profissional, String tipoPagamento, String linkpagamento){
		
		PagamentoPagarMe pagamento =  new PagamentoPagarMe();
		
		if (agenda != null) {
			pagamento.setAgenda(agenda.getId());
			pagamento.setProfissional(agenda.getProfissional().getPessoa().getNome());
			pagamento.setIdProfissional(agenda.getProfissional().getId());
			pagamento.setIdPaciente(agenda.getPaciente().getId());
			pagamento.setNomePaciente(agenda.getPaciente().getNome());
			pagamento.setDataAgenda(agenda.getDataHoraApresentar());
		}
		if (profissional != null) {
			pagamento.setProfissional(profissional.getPessoa().getNome());
			pagamento.setIdProfissional(profissional.getId());
			pagamento.setPlanoVitazure(Boolean.TRUE);
			pagamento.setPlanoVitazureSelecionado(profissional.getPlano());
		}
		pagamento.setIdTransacao(transacao.getId());
		pagamento.setDataTransacao(transacao.getUpdatedAt().toString());
		pagamento.setStatus(situacaoTransacao(transacao.getStatus()));
		pagamento.setValorPago(new BigDecimal(transacao.getAmount()).divide(new BigDecimal(100)));
		pagamento.setTipoPagamento(tipoPagamento);
		pagamento.setLinkPagamento(linkpagamento);
		return pagamento;
	}
	
	private String situacaoTransacao(Status status) {
        if (Status.PROCESSING.equals(status)) {
			return "EM PROCESSAMENTO";
		}else if (Status.AUTHORIZED.equals(status)) {
        	return "AUTORIZADO";
        }else  if (Status.PAID.equals(status)) {
        	return "PAGO";
        }else if (Status.REFUNDED.equals(status)) {
        	return "DEVOLVEU";
        }else if (Status.WAITING_PAYMENT.equals(status)) {
        	return "ESPERANDO PAGAMENTO";
        }else if (Status.PENDING_REFUND.equals(status)) {
        	return "REEMBOLSO PENDENTE";
        }else if (Status.REFUSED.equals(status)) {
        	return "RECUSOU";
        }else if (Status.CHARGEDBACK.equals(status)) {
        	return "CHARGEDBACK";
        } 
        return "";
	}
	
	public String getDataFormatada() {
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		Date date = sdf.parse(getDataTransacao());
		return Uteis.formatarDataHora(date, "dd/MM/YYYY");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getPlanoApresentar() {
		  return getPlanoVitazure().equals("plano_mensal") ? "Mensal" : getPlanoVitazure().equals("plano_semestral") ? "Semestral" :  "Anual";
		}

	
}
