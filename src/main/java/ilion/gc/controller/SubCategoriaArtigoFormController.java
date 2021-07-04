package ilion.gc.controller;

import java.util.Calendar;
import java.util.Date;

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
import org.springframework.web.bind.support.SessionStatus;

import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.gc.categoria.negocio.SubCategoriaArtigoNegocio;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/subcategoria-form")
@SessionAttributes("subCategoria")
@UsuarioLogado
public class SubCategoriaArtigoFormController {
	
	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;
	
	@Autowired
	private SubCategoriaArtigoNegocio subCategoriaArtigoNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, Long idCategoria, ModelMap modelMap) {
		SubCategoriaArtigo subCategoria = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			subCategoria = (SubCategoriaArtigo) subCategoriaArtigoNegocio.consultarPorId(idLong);
		}
		
		if(subCategoria == null) {
			subCategoria = new SubCategoriaArtigo();
			
			CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorId(idCategoria);
			if( categoriaArtigo == null ) {
				return "redirect:gc-busca.sp?m=categoria-nao-encontrada";
			}
			subCategoria.setCategoriaArtigo(categoriaArtigo);
			
			subCategoria.setStatus("Publicado");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			subCategoria.setDataPublicacao(c.getTime());
			c.add(Calendar.YEAR, 5);
			subCategoria.setDataExpiracao(c.getTime());
			
			Integer posicao = subCategoriaArtigoNegocio.consultarUltimaPosicaoSubCategoria(categoriaArtigo);
			if(posicao == null) {
				posicao = 0;
			}
			posicao++;
			subCategoria.setPosicao(posicao);
			
			subCategoria.setCodControle("SUB:"+System.currentTimeMillis()+"");
		}
		
		modelMap.addAttribute("subCategoria", subCategoria);
		
		return "/ilionnet/modulos/gc/subcategoria-form";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, "posicao", new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Date.class, "dataPublicacao", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
		binder.registerCustomEditor(Date.class, "dataExpiracao", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("subCategoria") SubCategoriaArtigo subCategoria, BindingResult bindingResult, SessionStatus status) {
		
		subCategoriaArtigoNegocio.validarSubCategoria(subCategoria, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/gc/subcategoria-form";
		} else {
			
			subCategoriaArtigoNegocio.incluirAtualizar(subCategoria);
			
			return "redirect:subcategoria-form.sp?id="+subCategoria.getId()+"&m=ok";
		}
	}
}
