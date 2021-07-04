package ilion.contato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ilion.contato.negocio.ContatoGrupo;
import ilion.contato.negocio.ContatoGrupoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/contato-grupo-form")
@SessionAttributes("grupoContato")
@UsuarioLogado("contato-grupo-form.sp")
public class ContatoGrupoFormController {

	@Autowired
	private ContatoGrupoNegocio contatoGrupoNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap) {
		ContatoGrupo grupoContato = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			grupoContato = (ContatoGrupo) contatoGrupoNegocio.consultar(ContatoGrupo.class, idLong);
		}
		
		if(grupoContato == null) {
			grupoContato = new ContatoGrupo();
		}
		
		modelMap.addAttribute("grupoContato", grupoContato);
		
		return "/ilionnet/modulos/contato/contato-grupo-form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("grupoContato") ContatoGrupo grupoContato, BindingResult bindingResult, SessionStatus status) {
		
		contatoGrupoNegocio.validarGrupo(grupoContato, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/contato/contato-grupo-form";
		} else {
			
			contatoGrupoNegocio.incluirAtualizar(grupoContato);
			
			return "redirect:contato-grupo-form.sp";
		}
	}
}