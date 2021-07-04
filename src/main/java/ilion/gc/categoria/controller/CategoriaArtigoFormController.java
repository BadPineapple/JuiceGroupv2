package ilion.gc.categoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/categoria-artigo-form")
@SessionAttributes("categoriaArtigo")
@UsuarioLogado
public class CategoriaArtigoFormController {

	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;

	@Autowired
	private PropNegocio propNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap) {
		CategoriaArtigo categoriaArtigo = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			categoriaArtigo = (CategoriaArtigo) categoriaArtigoNegocio.consultarPorId(idLong);
		}
		
		if(categoriaArtigo == null) {
			categoriaArtigo = new CategoriaArtigo();
		}
		
		modelMap.addAttribute("categoriaArtigo", categoriaArtigo);
		
		return "/ilionnet/modulos/gc/categoria-artigo-form";
	}
	
	@ModelAttribute("site")
	public String getSite() {
		return propNegocio.findValueById(PropEnum.SITE);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiSitemap", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiArtigoMenu", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiSubTitulo", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiTelefone", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiEmail", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiLink", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiArquivos", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiVideo", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiRSS", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiPalavrasChave", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiDescricao", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "artigoConfig.possuiDataEvento", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "subCategoriaConfig.possuiSubCategorias", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "subCategoriaConfig.possuiDescricao", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "subCategoriaConfig.possuiTelefone", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "subCategoriaConfig.possuiEmail", new CustomBooleanEditor(true));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("categoriaArtigo") CategoriaArtigo categoriaArtigo, BindingResult bindingResult, SessionStatus status) {
		
		try {
			
			categoriaArtigoNegocio.incluirAtualizar(categoriaArtigo);
			
			return "redirect:categoria-artigo-form.sp?id="+categoriaArtigo.getId()+"&m=ok";
			
		} catch (Exception e) {
			bindingResult.reject("", e.getMessage());
			return "/ilionnet/modulos/gc/categoria-artigo-form";
		}
		
	}
}