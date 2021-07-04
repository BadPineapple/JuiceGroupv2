package ilion.email.negocio;

import org.springframework.stereotype.Service;

import ilion.util.exceptions.NenhumEmailSenderException;

@Service
public class NenhumEmailSenderImpl implements IEmailSender {
	
	@Override
	public void setEmailSender(EmailSender emailSender){
	}

	@Override
	public void send(Email email) throws Exception {
		
		throw new NenhumEmailSenderException();
		
	}

	@Override
	public void sendTest(String emailTo) throws Exception {
	}

}
