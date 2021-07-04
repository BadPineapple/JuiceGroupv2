package ilion.email.negocio;

public interface IEmailSender {
	
	public void setEmailSender(EmailSender emailSender);
	
	public void send(Email email) throws Exception;
	
	public void sendTest(String emailTo) throws Exception;
	
}