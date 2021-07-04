package ilion.gc.topico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ilion.gc.topico.negocio.Topico;
import ilion.gc.topico.negocio.TopicoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/topico-form")
@SessionAttributes("topico")
@UsuarioLogado("topico")
public class TopicoFormController {
	
	@Autowired
	private TopicoNegocio topicoNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, Long idCategoria, ModelMap modelMap) {
		Topico topico = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			topico = topicoNegocio.consultarPorId(idLong);
		}
		
		if(topico == null) {
			topico = new Topico();
		}
		
		modelMap.addAttribute("topico", topico);
		
		return "/ilionnet/modulos/gc/topico-form";
	}
	
	@ModelAttribute("topicos")
	public List<Topico> listarTopico() {
		List<Topico> topicos = topicoNegocio.listar();
		return topicos;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("topico") Topico topico, BindingResult bindingResult, SessionStatus status) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome n�o pode ser vazio!");
		
		if( topicoNegocio.existeTopicoComNome(topico) ) {
			bindingResult.rejectValue("nome", "", "J� existe um topico com este nome.");
		}
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/gc/topico-form";
		} else {
			
			topicoNegocio.incluirAtualizar(topico);
			
			return "redirect:topico-form.sp?m=ok";
		}
	}
}
