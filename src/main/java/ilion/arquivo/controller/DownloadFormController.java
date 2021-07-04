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

import ilion.arquivo.negocio.upload.Download;
import ilion.arquivo.negocio.upload.DownloadUpload;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/download-form")
@SessionAttributes("arquivo")
@UsuarioLogado
public class DownloadFormController {

	@Autowired
	private DownloadUpload downloadUpload;

	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String nomeClasse, String idObjeto, String codigo, ModelMap modelMap) {
		Download download = new Download();

		download.setNomeClasse(nomeClasse);
		download.setIdObjeto(idObjeto);
		download.setCodigo(codigo);

		modelMap.addAttribute("arquivo", download);

		return "/ilionnet/modulos/arquivo/download-form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("arquivo") Download download, BindingResult bindingResult, HttpServletRequest request) throws Exception {

		List<String> erros = downloadUpload.validar(download);

		for (String erro : erros) {
			bindingResult.reject("", erro);
		}

		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/arquivo/download-form";
		} else {

			String nomeClasse = download.getNomeClasse();
			String idObjeto = download.getIdObjeto();
			String codigo = download.getCodigo();

			downloadUpload.gravar(download);

			return "redirect:download-form.sp?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
		}
	}
}
