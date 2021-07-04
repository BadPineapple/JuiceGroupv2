package ilion.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ilion.CustomErrorController;
import ilion.admin.negocio.Perfil;
import ilion.admin.negocio.PerfilNegocio;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
public class PerfilController extends CustomErrorController{
	
	@Autowired
	private PerfilNegocio perfilNegocio;
	
	@RequestMapping("/ilionnet/perfil")
	@UsuarioLogado("perfil.sp")
	public String usuarios(HttpServletRequest request) {
		List<Perfil> perfis = perfilNegocio.listarPerfis();
		request.setAttribute("perfis", perfis);
		
		return "/ilionnet/modulos/admin/perfil-lista";
	}
	
	@RequestMapping("/ilionnet/perfil-excluir")
	@UsuarioLogado("perfil-excluir.sp")
	public String excluir(String id) {
		Perfil perfil = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			perfil = (Perfil) perfilNegocio.consultar(Perfil.class, idLong);
			
			perfilNegocio.excluir(perfil);
		}
		
		return "redirect:perfil.sp?m=exclusao-ok";
	}
}