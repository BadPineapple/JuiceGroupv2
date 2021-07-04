package ilion.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ilion.CustomErrorController;
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import net.mlw.vlh.ValueList;

@Controller
public class UsuarioController extends CustomErrorController{
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@RequestMapping("/ilionnet/usuario")
	@UsuarioLogado("usuario.sp")
	public String usuarios(HttpServletRequest request) {
		VLHForm vlhForm = VLHForm.getVLHSession("usuariosLista", request);
		
		ValueList usuarios = usuarioNegocio.busca(vlhForm, new ValueListInfo(vlhForm));
		request.setAttribute("usuarios", usuarios);
		
		return "/ilionnet/modulos/admin/usuario-busca";
	}
	
	@RequestMapping("/ilionnet/usuario-excluir")
	@UsuarioLogado("usuario-excluir.sp")
	public String excluir(String id) {
		Usuario usuario = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			usuario = (Usuario) usuarioNegocio.consultar(Usuario.class, idLong);
			
			usuarioNegocio.excluir(usuario);
		}
		
		return "redirect:/ilionnet/usuario?m=exclusao-ok";
	}
}