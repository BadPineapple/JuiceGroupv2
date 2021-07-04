package ilion.arquivo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/arquivo-detalhes-form")
//@SessionAttributes("arquivo")
@UsuarioLogado
public class ArquivoDetalhesFormController {
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap, HttpServletResponse response) throws Exception {
		
		if(Uteis.ehNuloOuVazio(id)) {
			Uteis.escreverResposta("id nulo", false, response);
			return null;
		}
		
		Arquivo arquivo = (Arquivo) arquivoNegocio.consultar(Arquivo.class, id);
		modelMap.addAttribute("arquivo", arquivo);
		
		return "/ilionnet/modulos/arquivo/arquivo-detalhes-iframe-form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("arquivo") Arquivo arquivo, BindingResult bindingResult, HttpServletRequest request) throws Exception {
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/arquivo/arquivo-detalhes-iframe-form";
		} else {
			
			arquivoNegocio.atualizarDetalhes(arquivo);
			
			return "redirect:arquivo-detalhes-form.sp?id="+arquivo.getId()+"&m=ok";
		}
	}
}
