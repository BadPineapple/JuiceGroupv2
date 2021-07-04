package ilion.gc.categoria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class CategoriaArtigoController {
	
	static Logger logger = Logger.getLogger(CategoriaArtigoController.class);

	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;

	@Autowired
	private PropNegocio propNegocio;
	
	@RequestMapping("/ilionnet/categoria-artigo-lista")
	public String lista(String site, String palavraChave, HttpServletRequest request) {
		
		request.setAttribute("site", propNegocio.findValueById(PropEnum.SITE));
		
		List categorias = categoriaArtigoNegocio.listarCategoriasArtigo(site, palavraChave);
		request.setAttribute("categorias", categorias);
		
		return "/ilionnet/modulos/gc/categoria-artigo-lista";
	}
}
