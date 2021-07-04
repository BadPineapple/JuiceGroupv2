package ilion.arquivo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.arquivo.negocio.upload.Video;
import ilion.arquivo.negocio.upload.VideoUpload;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/video-form")
@SessionAttributes("video")
@UsuarioLogado
public class VideoFormController {
	
	@Autowired
	private VideoUpload videoUpload;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String nomeClasse, String idObjeto, String codigo, ModelMap modelMap) {
		Video video = new Video();
		
		video.setNomeClasse(nomeClasse);
		video.setIdObjeto(idObjeto);
		video.setCodigo(codigo);
		
		modelMap.addAttribute("video", video);
		
		return "/ilionnet/modulos/arquivo/video-form";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, "largura", new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Integer.class, "altura", new CustomNumberEditor(Integer.class, true));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("video") Video video, BindingResult bindingResult, HttpServletRequest request) throws Exception {
		
		List<String> erros = videoUpload.validar(video);
		
		for (String erro : erros) {
			bindingResult.reject("", erro);
		}
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/arquivo/video-form";
		} else {
			
			String nomeClasse = video.getNomeClasse();
			String idObjeto = video.getIdObjeto();
			String codigo = video.getCodigo();
			
			videoUpload.gravar(video);
			
			return "redirect:video-form.sp?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
		}
	}
}
