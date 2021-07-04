package ilion.admin.controller;

import java.util.List;
import java.util.UUID;

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

import ilion.CustomErrorController;
import ilion.admin.negocio.Perfil;
import ilion.admin.negocio.PerfilNegocio;
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaNegocio;
import ilion.util.StringUtil;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/usuario-form")
@SessionAttributes("usuario")
@UsuarioLogado("usuario-form.sp")
public class UsuarioFormController extends CustomErrorController{
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@Autowired
	private PerfilNegocio perfilNegocio;
	
	@Autowired
	private FazendaNegocio fazendaNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap) {

		Usuario usuario = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			usuario = (Usuario) usuarioNegocio.consultarPorId(idLong);
			usuario.setSenhaAux( StringUtil.decodePassword(usuario.getSenha()) );
		}
		
		if(usuario == null) {
			usuario = new Usuario();
			usuario.setSenhaAux(UUID.randomUUID().toString().substring(0, 6));
		}
		
		if(usuario.getPerfil() == null) {
			usuario.setPerfil(new Perfil());
		}
		
		modelMap.addAttribute("usuario", usuario);
		
		return "/ilionnet/modulos/admin/usuario-form";
	}
	
	@ModelAttribute("perfis")
	public List<Perfil> popularSelectPerfil() {
		return perfilNegocio.listarPerfis();
	}
	
	@ModelAttribute("fazendasTodas")
	public List<Fazenda> listarFazendasTodas() {
		return fazendaNegocio.listar();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, "fazendas", new CustomCollectionEditor(List.class) {
			
			@Override
			protected Object convertElement(Object element) {
				Long id = null;
				//System.out.println("element: "+element);
				if(element instanceof String && !((String)element).equals("")){
					//From the JSP 'element' will be a String
					try {
						id = Long.parseLong((String) element);
					} catch (NumberFormatException e) {
					}
				} else if(element instanceof Long) {
					id = (Long) element;
				}
				
				return id != null ? new Fazenda(id) : null;
			}
		});
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(
			@ModelAttribute("usuario") Usuario usuario, 
			BindingResult bindingResult) throws Exception {
	
		usuarioNegocio.validarUsuario(usuario, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/admin/usuario-form";
		} else {
			
			usuarioNegocio.incluirAtualizar(usuario);
			
			return "redirect:usuario-form.sp?id="+usuario.getId()+"&m=ok";
		}
	}
}