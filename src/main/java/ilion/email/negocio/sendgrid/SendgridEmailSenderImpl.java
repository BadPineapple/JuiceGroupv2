package ilion.email.negocio.sendgrid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailSender;
import ilion.email.negocio.EmailSenderEnum;
import ilion.email.negocio.IEmailSender;

@Service
public class SendgridEmailSenderImpl implements IEmailSender {
	
	static Logger logger = Logger.getLogger(SendgridEmailSenderImpl.class);
	
	private EmailSender emailSender;
	
	@Autowired
	private PropNegocio propNegocio;

	@Override
	public void send(Email email) throws Exception {

		emailSender.jsonToBean();

		com.sendgrid.Email from = new com.sendgrid.Email(emailSender.getSendGridVH().getEmail(), propNegocio.findValueById(PropEnum.NOME_EMPRESA));

		String subject = email.getSubject();

		com.sendgrid.Email to = new com.sendgrid.Email(email.getToEmail(), email.getToName());

		Content content = new Content("text/html", email.getMessage());

		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid( emailSender.getSendGridVH().getApiKey() );

		Request request = new Request();

		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());

		Response response = sg.api(request);
		
		logger.info("OK Enviado: "+email+", response: statusCode: " + response.getStatusCode() + ", body: " + response.getBody()
		+ ", headers: " + response.getHeaders());
		
	}

	@Override
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Override
	public void sendTest(String emailTo) throws Exception {
		
		String nomeEmpresa = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		
		String assunto = "Testando envio de e-mail - "+nomeEmpresa;
		
		String html = "Teste efetuado via: "+emailSender.getNome()+" ("+EmailSenderEnum.SENDGRID+")";
		
		Email e = new Email();
		
		e.setToEmail(emailTo);
		e.setToName(emailTo);
		e.setSubject(assunto);
		e.setMessage(html);
		
		send(e);
	}

}
