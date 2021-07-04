package ilion.email.negocio.smtp;

import java.util.Date;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailSender;
import ilion.email.negocio.EmailSenderEnum;
import ilion.email.negocio.IEmailSender;
import ilion.email.negocio.IlionHtmlEmail;
import ilion.util.Uteis;

@Service
public class SmtpEmailSenderImpl implements IEmailSender {
	
	static Logger logger = Logger.getLogger(SmtpEmailSenderImpl.class);
	
	private EmailSender emailSender;
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Override
	public void send(Email email) throws Exception {
		
		//email.setPath("/home/jan/dev/utils/document-teste-1.pdf");
		
		if( Uteis.ehNuloOuVazio(email.getPath()) ) {
			
			enviarHtmlEmail(email);
			
		} else {
			
			enviarMultiPartEmail(email);
			
		}
		
		logger.info("OK Enviado: "+email);

	}

	private void enviarHtmlEmail(Email email) throws Exception {
		
		IlionHtmlEmail e = IlionHtmlEmail.from(emailSender);
		
		if( Uteis.ehNuloOuVazio(email.getToName()) ) {
			email.setToName(email.getToEmail());
		}
		
		e.addTo(email.getToEmail(), email.getToName());
		
		if( Uteis.ehNuloOuVazio(email.getReplyToName()) ) {
			email.setReplyToName(email.getReplyToEmail());
		}
		
		if( Uteis.ehEmailValido(email.getReplyToEmail()) ) {
			e.addReplyTo(email.getReplyToEmail(), email.getReplyToName());
		}
		
		e.setSubject(email.getSubject());
		
		String mensagem = email.getMessage();
		if( Uteis.ehNuloOuVazio(mensagem) ) {
			mensagem = "&nbsp;";
		}
		e.setHtmlMsg(mensagem);
		
		e.send();
		
	}
	
	private void enviarMultiPartEmail(Email email) throws Exception {
		
		MultiPartEmail multipartEmail = new MultiPartEmail();
		
		multipartEmail.setSentDate(new Date());
		multipartEmail.setCharset(emailSender.getSmtpVH().getCharset());
		multipartEmail.setHostName(emailSender.getSmtpVH().getServer());
		multipartEmail.setSmtpPort(emailSender.getSmtpVH().getPorta());
		multipartEmail.setAuthentication(emailSender.getSmtpVH().getUsuario(), emailSender.getSmtpVH().getSenha());
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		multipartEmail.setFrom(emailSender.getSmtpVH().getEmail(), propNegocio.findValueById(PropEnum.NOME_EMPRESA));
		
		if(emailSender.getSmtpVH().getUsarSSL() != null && emailSender.getSmtpVH().getUsarSSL()) {
			multipartEmail.setSSL(true);
		}
		if(emailSender.getSmtpVH().getUsarTLS() != null && emailSender.getSmtpVH().getUsarTLS()) {
			multipartEmail.setTLS(true);
		}
		if(emailSender.getSmtpVH().getSslSmtpPort() != null) {
			multipartEmail.setSslSmtpPort(emailSender.getSmtpVH().getSslSmtpPort().toString());
		}
		
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(email.getPath());
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Bilhete");
		attachment.setName(email.getNomeAnexo());
		
		multipartEmail.attach(attachment);
		
		
		if( Uteis.ehNuloOuVazio(email.getToName()) ) {
			email.setToName(email.getToEmail());
		}
		
		multipartEmail.addTo(email.getToEmail(), email.getToName());
		
		if( Uteis.ehNuloOuVazio(email.getReplyToName()) ) {
			email.setReplyToName(email.getReplyToEmail());
		}
		
		if( Uteis.ehEmailValido(email.getReplyToEmail()) ) {
			multipartEmail.addReplyTo(email.getReplyToEmail(), email.getReplyToName());
		}
		
		multipartEmail.setSubject(email.getSubject());
		
		String mensagem = email.getMessage();
		if( Uteis.ehNuloOuVazio(mensagem) ) {
			mensagem = "&nbsp;";
		}
		multipartEmail.setMsg(email.getMessage());
		
		multipartEmail.send();
		
	}

	@Override
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Override
	public void sendTest(String emailTo) throws Exception {
		
		String nomeEmpresa = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		
		String assunto = "Testando envio de e-mail - "+nomeEmpresa;
		
		String html = "Teste efetuado via: "+emailSender.getNome()+" ("+EmailSenderEnum.SMTP+")";
		
		Email e = new Email();
		
		e.setToEmail(emailTo);
		e.setToName(emailTo);
		e.setSubject(assunto);
		e.setMessage(html);
		
		send(e);
	}

}
