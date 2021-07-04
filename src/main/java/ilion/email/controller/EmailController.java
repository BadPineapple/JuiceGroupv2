package ilion.email.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ilion.email.negocio.EmailNegocio;
import ilion.email.negocio.EmailSenderNegocio;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import net.mlw.vlh.ValueList;

@Controller
@UsuarioLogado
public class EmailController {
	
	static Logger logger = Logger.getLogger(EmailController.class);
	
	@Autowired
	private EmailNegocio emailNegocio;
	
	@Autowired
	private EmailSenderNegocio emailSenderNegocio;
	
	@GetMapping("/ilionnet/emails")
	public String pedidos(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("emailsBusca", request);
		
		ValueList emails = emailNegocio.busca(vlhForm, new ValueListInfo(vlhForm));
		request.setAttribute("emails", emails);
		
		request.setAttribute("vlhForm", vlhForm);
		
		request.setAttribute("emailSender", emailSenderNegocio.consultarAtivo());
		
		return "/ilionnet/modulos/admin/email/emails";
	}
}