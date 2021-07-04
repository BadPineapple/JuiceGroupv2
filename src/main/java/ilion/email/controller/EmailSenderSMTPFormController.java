package ilion.email.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.email.negocio.EmailSender;
import ilion.email.negocio.EmailSenderEnum;
import ilion.email.negocio.EmailSenderNegocio;
import ilion.email.negocio.smtp.SmtpVH;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@SessionAttributes("emailSender")
@UsuarioLogado
public class EmailSenderSMTPFormController {
	
	static Logger logger = Logger.getLogger(EmailSenderSMTPFormController.class);
	
	@Autowired
	private EmailSenderNegocio emailSenderNegocio;
	
	@GetMapping("/ilionnet/email-senders/smtp/novo")
	public String novo(ModelMap map){
		
		EmailSender emailSender = new EmailSender();
		emailSender.setEmailSenderEnum(EmailSenderEnum.SMTP);
		emailSender.setSmtpVH(new SmtpVH());
		
		map.addAttribute("emailSender", emailSender);
		
		return "/ilionnet/modulos/admin/email/smtp-form";
	}
	
	@GetMapping("/ilionnet/email-senders/smtp/{id}/editar")
	public String carregar(@PathVariable Long id, ModelMap map){
		
		EmailSender emailSender = (EmailSender) emailSenderNegocio.findByTypeId(EmailSenderEnum.SMTP, id);
		
		emailSender.jsonToBean();
		
		map.addAttribute("emailSender", emailSender);
		
		return "/ilionnet/modulos/admin/email/smtp-form";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@PostMapping(value={"/ilionnet/email-senders/smtp/novo", "/ilionnet/email-senders/smtp/{id}/editar"})
	public String salvar(
			@ModelAttribute("emailSender") EmailSender emailSender, 
			BindingResult bindingResult
			) throws Exception {
		
		try {
			
			emailSender = emailSenderNegocio.incluirAtualizar(emailSender);
			
			return "redirect:/ilionnet/email-senders/smtp/"+emailSender.getId()+"/editar?m=ok";
		} catch (Exception e) {
			logger.error("", e);
			bindingResult.reject("", e.getMessage());
			return "/ilionnet/modulos/admin/email/smtp-form";
		}
	}
}
