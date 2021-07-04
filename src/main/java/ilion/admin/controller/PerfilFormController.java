package ilion.admin.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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

import ilion.CustomErrorController;
import ilion.admin.negocio.Perfil;
import ilion.admin.negocio.PerfilNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import ilion.util.json.GSONUteis;

@Controller
@RequestMapping("/ilionnet/perfil-form")
@SessionAttributes("perfil")
@UsuarioLogado("perfil-form.sp")
public class PerfilFormController extends CustomErrorController{

	@Autowired
	private PerfilNegocio perfilNegocio;

	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;

	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap) {
		Perfil perfil = null; 

		Long idLong = Uteis.converterLong(id);

		if(idLong != null) {
			perfil = (Perfil) perfilNegocio.consultarPorIdComCategorias(idLong);

			List<String> permissoes = Collections.emptyList();
			if( ! Uteis.ehNuloOuVazio(perfil.getPermissoesJson()) ) {
				permissoes = GSONUteis.getInstance().fromJson(perfil.getPermissoesJson(), List.class);
			}
			perfil.setPermissoes(permissoes);

		}

		if(perfil == null) {
			perfil = new Perfil();
		}

		modelMap.addAttribute("perfil", perfil);

		return "/ilionnet/modulos/admin/perfil-form";
	}

	@ModelAttribute("categoriasArtigoTodas")
	public List<CategoriaArtigo> listarCategoriasArtigoTodas() {
		return categoriaArtigoNegocio.listarCategoriasPublicadas();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, "categorias", new CustomCollectionEditor(List.class) {
			
			@Override
			protected Object convertElement(Object element) {
				Long id = null;
				//System.out.println("element: "+element);
				if(element instanceof String && !((String)element).equals("")){
					//From the JSP 'element' will be a String
					try {
						id = Long.parseLong((String) element);
					} catch (NumberFormatException e) {
						//                      System.out.println("Element was " + ((String) element));
						//                      e.printStackTrace();
					}
				} else if(element instanceof Long) {
					//From the database 'element' will be a Long
					id = (Long) element;
				}
				
				return id != null ? new CategoriaArtigo(id) : null;
				//return id != null ? categoriaArtigoNegocio.consultarPorId(id) : null;
			}
		});
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("perfil") Perfil perfil, BindingResult bindingResult, SessionStatus status) {

		perfilNegocio.validarPerfil(perfil, bindingResult);

		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/admin/perfil-form";
		} else {

			perfilNegocio.incluirAtualizar(perfil);

			return "redirect:perfil-form.sp?id="+perfil.getId()+"&m=ok";
		}
	}
}