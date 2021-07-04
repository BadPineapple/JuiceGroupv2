package ilion.email.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ilion.util.Uteis;
import ilion.util.exceptions.NotFoundException;
import ilion.util.exceptions.ValidacaoException;

@Service
public class EmailSenderFactory {
	
	@Autowired
	@Qualifier("smtpEmailSenderImpl")
	private IEmailSender smtpEmailSender;
	
	@Autowired
	@Qualifier("sendgridEmailSenderImpl")
	private IEmailSender sendgridEmailSender;
	
	@Autowired
	@Qualifier("nenhumEmailSenderImpl")
	private IEmailSender nenhumEmailSender;
	
	@Autowired
	private EmailSenderNegocio emailSenderNegocio;
	
	public IEmailSender getInstance() {
		
		EmailSender emailSender = emailSenderNegocio.consultarAtivo();
		
		if( emailSender == null ) {
			return nenhumEmailSender;
		}
		
		emailSender.jsonToBean();
		
		if( EmailSenderEnum.SENDGRID.equals(emailSender.getEmailSenderEnum()) ) {
			
			sendgridEmailSender.setEmailSender(emailSender);
			
			return sendgridEmailSender;
		}
		
		if( EmailSenderEnum.SMTP.equals(emailSender.getEmailSenderEnum()) ) {
			
			smtpEmailSender.setEmailSender(emailSender);
			
			return smtpEmailSender;
		}
		
		return nenhumEmailSender;
	}
	
	public void testar(EmailSender emailSender, String emailTo) throws Exception {
		
		if( emailSender == null || emailSender.getId() == null ) {
			throw new NotFoundException("Email sender não encontrado.");
		}
		
		if( ! Uteis.ehEmailValido(emailTo) ) {
			throw new ValidacaoException("Email deve ser válido.");
		}
		
		emailSender = emailSenderNegocio.findById(emailSender.getId());
		
		if( emailSender == null ) {
			throw new NotFoundException("Email sender não encontrado.");
		}
		
		emailSender.jsonToBean();
		
		if( EmailSenderEnum.SENDGRID.equals(emailSender.getEmailSenderEnum()) ) {
			
			sendgridEmailSender.setEmailSender(emailSender);
			
			sendgridEmailSender.sendTest(emailTo);
		}
		
		if( EmailSenderEnum.SMTP.equals(emailSender.getEmailSenderEnum()) ) {
			
			smtpEmailSender.setEmailSender(emailSender);
			
			smtpEmailSender.sendTest(emailTo);
		}
		
	}
}