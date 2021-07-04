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

import ilion.arquivo.negocio.upload.Imagem;
import ilion.arquivo.negocio.upload.ImagemUpload;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/imagem-form")
@SessionAttributes("arquivo")
@UsuarioLogado
public class ImagemFormController {

	@Autowired
	private ImagemUpload imagemUpload;

	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String nomeClasse, String idObjeto, String codigo, ModelMap modelMap) {
		Imagem imagem = new Imagem();

		imagem.setNomeClasse(nomeClasse);
		imagem.setIdObjeto(idObjeto);
		imagem.setCodigo(codigo);

		imagem.setLarguraPequena(150);
		//imagem.setTamanhoOriginal(true);
		//imagem.setLarguraGrande(500);

		modelMap.addAttribute("arquivo", imagem);

		return "/ilionnet/modulos/arquivo/imagem-form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("arquivo") Imagem imagem, BindingResult bindingResult, HttpServletRequest request) throws Exception {

		List<String> erros = imagemUpload.validar(imagem);

		for (String erro : erros) {
			bindingResult.reject("", erro);
		}

		if (bindingResult.hasErrors()) {

			return "/ilionnet/modulos/arquivo/imagem-form";

		} else {

			String nomeClasse = imagem.getNomeClasse();
			String idObjeto = imagem.getIdObjeto();
			String codigo = imagem.getCodigo();

			try {
				imagemUpload.gravar(imagem);
			} catch (Exception e) {

				bindingResult.reject("Ocorreu erro ao salvar: "+e.getMessage());

				return "/ilionnet/modulos/arquivo/imagem-form";
			}

			return "redirect:imagem-form.sp?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
		}
	}
}