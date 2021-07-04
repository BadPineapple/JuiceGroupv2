package ilion.email.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.email.negocio.EmailSender;
import ilion.email.negocio.EmailSenderEnum;
import ilion.email.negocio.EmailSenderFactory;
import ilion.email.negocio.EmailSenderNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class EmailSenderController {
	
	static Logger logger = Logger.getLogger(EmailSenderController.class);
	
	@Autowired
	private EmailSenderNegocio emailSenderNegocio;
	
	@Autowired
	private EmailSenderFactory emailSenderFactory;
	
	@GetMapping("/ilionnet/email-senders")
	public String senders(HttpServletRequest request) {
		
		List<EmailSender> emailSenders = emailSenderNegocio.listar();
		request.setAttribute("emailSenders", emailSenders);
		
		return "/ilionnet/modulos/admin/email/email-senders";
	}
	
	@GetMapping("/ilionnet/email-senders/{id}/editar")
	public String editar(@PathVariable Long id, HttpServletRequest request) {
		
		EmailSender emailSender = (EmailSender) emailSenderNegocio.findById(id);
		
		if( EmailSenderEnum.SENDGRID.equals(emailSender.getEmailSenderEnum()) ) {
			return "redirect:/ilionnet/email-senders/sendgrid/"+emailSender.getId()+"/editar";
		}
		
		if( EmailSenderEnum.SMTP.equals(emailSender.getEmailSenderEnum()) ) {
			return "redirect:/ilionnet/email-senders/smtp/"+emailSender.getId()+"/editar";
		}
		
		return "redirect:/ilionnet/email-senders?m=tipo-nao-encontrado";
	}
	
	@GetMapping("/ilionnet/email-senders/{id}/testar")
	public String enviarTeste(
			@PathVariable Long id,
			@RequestParam String emailTo, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		try {
			
			emailSenderFactory.testar(new EmailSender(id), emailTo);
			
			return "redirect:/ilionnet/email-senders?m=envio-ok";
		} catch (Exception e) {
			logger.error("", e);
			
			redirectAttributes.addFlashAttribute("exception", Uteis.extractStackTrace(e));
			
			return "redirect:/ilionnet/email-senders";
		}
		
	}
}