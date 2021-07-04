package ilion.contato.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.admin.negocio.Usuario;
import ilion.contato.negocio.Contato;
import ilion.contato.negocio.ContatoGrupoNegocio;
import ilion.contato.negocio.ContatoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/contato-form")
@SessionAttributes("contato")
@UsuarioLogado("contato-form.sp")
public class ContatoFormController {

	@Autowired
	private ContatoNegocio contatoNegocio;
	
	@Autowired
	private ContatoGrupoNegocio contatoGrupoNegocio;
	
	public ContatoFormController() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap) {
		
		Contato contato = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			contato = (Contato) contatoNegocio.consultar(Contato.class, idLong);
			
			List<Long> idsGrupos = contatoNegocio.listarIdsGrupos(contato);
			contato.setIdsGrupos(idsGrupos);
		}
		
		if(contato == null) {
			contato = new Contato();
		}
		
		modelMap.addAttribute("contato", contato);
		
		return "/ilionnet/modulos/contato/contato-form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("contato") Contato contato, BindingResult bindingResult, HttpServletRequest request) throws Exception {
		
		contatoNegocio.validarContato(contato, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/contato/contato-form";
		} else {
			
			if (contato.getId() == null) {
				Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
				contato.setCadastradoPor(usuarioSessao.getNome());
				contato.setDataCriacao(new Date());
			}
			
			contato = contatoNegocio.incluirAtualizar(contato);
			
			return "redirect:contato-form.sp?id="+contato.getId()+"&m=ok";
		}
	}
}