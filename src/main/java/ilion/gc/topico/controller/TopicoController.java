package ilion.gc.topico.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ilion.gc.topico.negocio.Topico;
import ilion.gc.topico.negocio.TopicoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado("topico")
public class TopicoController {
	
	static Logger logger = Logger.getLogger(TopicoController.class);
	
	@Autowired
	private TopicoNegocio topicoNegocio;
	
	@RequestMapping("/ilionnet/topico-excluir")
	public String excluir(Long id) {
		
		try {
			
			topicoNegocio.excluir(new Topico(id));
			
			return "redirect:topico-form.sp?m=excluido";
		} catch (Exception e) {
			logger.error("", e);
			return "redirect:topico-form.sp?m=erro&e="+e.getMessage();
		}
		
	}
	
}
