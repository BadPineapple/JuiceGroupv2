package ilion.gc.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.negocio.Enquete;
import ilion.gc.negocio.EnqueteNegocio;
import ilion.gc.util.UteisGC;
import ilion.util.Uteis;
import ilion.util.contexto.CacheFilter;

@Controller
@RequestMapping("/ilionnet/enquete-form")
@SessionAttributes("enquete")
public class EnqueteFormController {

	@Autowired
	ArquivoNegocio arquivoNegocio;
	
	@Autowired
	private EnqueteNegocio enqueteNegocio;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, ModelMap modelMap) {
		Enquete enquete = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			enquete = (Enquete) enqueteNegocio.consultar(Enquete.class, idLong);
		}
		
		if(enquete == null) {
			enquete = new Enquete();
			
			enquete.setStatus("Publicado");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			enquete.setDataPublicacao(c.getTime());
			c.add(Calendar.YEAR, 5);
			enquete.setDataExpiracao(c.getTime());
			
			enquete.setCodControle("SUB:"+System.currentTimeMillis()+"");
		}
		
		modelMap.addAttribute("enquete", enquete);
		
		return "/ilionnet/modulos/gc/enquete-form";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "dataPublicacao", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
		binder.registerCustomEditor(Date.class, "dataExpiracao", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("enquete") Enquete enquete, BindingResult bindingResult, SessionStatus status) {
		
		enqueteNegocio.validarEnquete(enquete, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/gc/enquete-form";
		} else {
			
			if(enquete.getId() == null) {
//				enqueteArtigo.setCriadoPor(usuario.getNome());
//				enqueteArtigo.setDataCriacao(new Date());
				
				enquete.setVotosOpcao1(0);
				enquete.setVotosOpcao2(0);
				enquete.setVotosOpcao3(0);
				enquete.setVotosOpcao4(0);
				enquete.setVotosOpcao5(0);
				
				String codControle = enquete.getCodControle();
				
				enquete = (Enquete) enqueteNegocio.inserir(enquete);
				
				arquivoNegocio.vincularArquivos(Enquete.class, enquete.getId(), codControle);
			}
			
			String conteudoInfo = UteisGC.conteudoInfo(enquete, Enquete.class);
			enquete.setConteudoInfo(conteudoInfo);

			enqueteNegocio.atualizar(enquete);

			CacheFilter.limparCache();
			
			return "redirect:enquete-form.sp?id="+enquete.getId();
		}
	}
}
