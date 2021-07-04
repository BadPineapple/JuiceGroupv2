package ilion.email.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ilion.email.negocio.sendgrid.SendGridVH;
import ilion.email.negocio.smtp.SmtpVH;
import ilion.util.json.GSONUteis;

@Entity
@Table(name="email_sender")
public class EmailSender implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="email_sender_id_seq",sequenceName="email_sender_id_seq")
	@GeneratedValue(generator = "email_sender_id_seq",strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EmailSenderEnum emailSenderEnum;
	
	private String nome;
	
	@Column(columnDefinition="text")
	private String dadosJson;
	
	private Integer prioridade;
	
	@Transient
	private SmtpVH smtpVH;
	
	@Transient
	private SendGridVH sendGridVH;
	
	public EmailSender() {
		super();
	}

	public EmailSender(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public EmailSenderEnum getEmailSenderEnum() {
		return emailSenderEnum;
	}

	public void setEmailSenderEnum(EmailSenderEnum emailSenderEnum) {
		this.emailSenderEnum = emailSenderEnum;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDadosJson() {
		return dadosJson;
	}

	public void setDadosJson(String dadosJson) {
		this.dadosJson = dadosJson;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public SendGridVH getSendGridVH() {
		return sendGridVH;
	}

	public void setSendGridVH(SendGridVH sendGridVH) {
		this.sendGridVH = sendGridVH;
	}

	public SmtpVH getSmtpVH() {
		return smtpVH;
	}

	public void setSmtpVH(SmtpVH smtpVH) {
		this.smtpVH = smtpVH;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailSender other = (EmailSender) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailSender [id=" + id + ", emailSenderEnum=" + emailSenderEnum + ", nome=" + nome + ", dadosJson="
				+ dadosJson + ", prioridade=" + prioridade + ", sendGridVH=" + sendGridVH + "]";
	}
	
	public void jsonToBean() {
		
		if( EmailSenderEnum.SENDGRID.equals(emailSenderEnum) ) {
			this.sendGridVH = GSONUteis.getInstance().fromJson(dadosJson, SendGridVH.class);
		}
		
		if( EmailSenderEnum.SMTP.equals(emailSenderEnum) ) {
			this.smtpVH = GSONUteis.getInstance().fromJson(dadosJson, SmtpVH.class);
		}
		
	}
	
	public void beanToJson() {
		
		if( EmailSenderEnum.SENDGRID.equals(emailSenderEnum) ) {
			this.dadosJson = GSONUteis.getInstance().toJson(this.sendGridVH);
		}
		
		if( EmailSenderEnum.SMTP.equals(emailSenderEnum) ) {
			this.dadosJson = GSONUteis.getInstance().toJson(this.smtpVH);
		}
		
	}


	
	
}