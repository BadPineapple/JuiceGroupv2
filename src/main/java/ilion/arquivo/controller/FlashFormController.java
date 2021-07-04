package ilion.arquivo.controller;

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

import ilion.arquivo.negocio.upload.Flash;
import ilion.arquivo.negocio.upload.FlashUpload;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/flash-form")
@SessionAttributes("arquivo")
@UsuarioLogado
public class FlashFormController {
	
	@Autowired
	private FlashUpload flashUpload;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String nomeClasse, String idObjeto, String codigo, ModelMap modelMap) {
		Flash flash = new Flash();
		
		flash.setNomeClasse(nomeClasse);
		flash.setIdObjeto(idObjeto);
		flash.setCodigo(codigo);
		
		modelMap.addAttribute("arquivo", flash);
		
		return "/ilionnet/modulos/arquivo/flash-form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("arquivo") Flash flash, BindingResult bindingResult, HttpServletRequest request) throws Exception {
		
		List<String> erros = flashUpload.validar(flash);
		
		for (String erro : erros) {
			bindingResult.reject("", erro);
		}
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/arquivo/flash-form";
		} else {
			
			String nomeClasse = flash.getNomeClasse();
			String idObjeto = flash.getIdObjeto();
			String codigo = flash.getCodigo();
			
			flashUpload.gravar(flash);
			
			return "redirect:flash-form.sp?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
		}
	}
}
