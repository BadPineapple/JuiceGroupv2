package ilion.email.negocio;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.util.Uteis;

public class IlionHtmlEmail extends HtmlEmail {
	
	static Logger logger = Logger.getLogger(IlionHtmlEmail.class);
	
//	public IlionHtmlEmail() {
//		super();
//		
//		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
//		
//		this.setCharset(propNegocio.findValueById(PropEnum.EMAIL_CHARSET));
//		
//		this.setSentDate(new Date());
//		this.setHostName(propNegocio.findValueById(PropEnum.EMAIL_SMTP_SERVER));
//		
//		Integer smtpPorta = propNegocio.findValueIntegerById(PropEnum.EMAIL_SMTP_PORT, 587);
//		this.setSmtpPort(smtpPorta);
//		
//		configuracoesAdicionaisEmail();
//		
//		this.setAuthentication(propNegocio.findValueById(PropEnum.EMAIL_FROM), propNegocio.findValueById(PropEnum.EMAIL_PASS));
//	}

	private IlionHtmlEmail() {
	}
	
	public static IlionHtmlEmail from(EmailSender emailSender) throws EmailException {
		
		IlionHtmlEmail ilionHtmlEmail = new IlionHtmlEmail();
		
		ilionHtmlEmail.setSentDate(new Date());
		ilionHtmlEmail.setCharset(emailSender.getSmtpVH().getCharset());
		ilionHtmlEmail.setHostName(emailSender.getSmtpVH().getServer());
		ilionHtmlEmail.setSmtpPort(emailSender.getSmtpVH().getPorta());
		ilionHtmlEmail.setAuthentication(emailSender.getSmtpVH().getUsuario(), emailSender.getSmtpVH().getSenha());
		
		PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
		
		ilionHtmlEmail.setFrom(emailSender.getSmtpVH().getEmail(), propNegocio.findValueById(PropEnum.NOME_EMPRESA));
		
		if(emailSender.getSmtpVH().getUsarSSL() != null && emailSender.getSmtpVH().getUsarSSL()) {
			ilionHtmlEmail.setSSL(true);
		}
		if(emailSender.getSmtpVH().getUsarTLS() != null && emailSender.getSmtpVH().getUsarTLS()) {
			ilionHtmlEmail.setTLS(true);
		}
		if(emailSender.getSmtpVH().getSslSmtpPort() != null) {
			ilionHtmlEmail.setSslSmtpPort(emailSender.getSmtpVH().getSslSmtpPort().toString());
		}
		
		return ilionHtmlEmail;
	}
	
	public void addToEmailsConcatenados(String emailsTo, String nomeTo) throws EmailException {
		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");
		
		for (String email : emailsLista) {
			this.addTo(email, nomeTo);
		}
	}
	
	public void sendComThread() {
		
		final IlionHtmlEmail ilionHtmlEmail = this;
		
		new Thread() {
			
			@Override
			public void run() {
				try {
					ilionHtmlEmail.send();
				} catch (EmailException e) {
					logger.error("", e);
				}
			}
			
		}.start();
	}
}