package ilion.contato.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ilion.contato.negocio.Contato;
import ilion.contato.negocio.ContatoNegocio;
import ilion.util.RecaptchaUtil;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.AcessoLivre;
import ilion.util.json.GSONUteis;
import ilion.util.json.RespostaStatus;

@Controller
@AcessoLivre
public class FormulariosController {
	
	static Logger logger = Logger.getLogger(FormulariosController.class);
	
	@Autowired
	private ContatoNegocio contatoNegocio;
	
	@Autowired
	private RecaptchaUtil recaptchaUtil;
	
	@PostMapping("/rest/newsletter")
	@ResponseBody
	public Object emailParaNewsletter(
			@RequestBody Contato contato, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		RespostaStatus respostaStatus = RespostaStatus.sucesso;
		String grupo = request.getParameter( "grupo" );
		
		try {
			
			contatoNegocio.cadastraContatoSite(
					contato.getNome(), 
					contato.getEmail(), 
					contato.getTelefone(), 
					grupo);
			
		} catch (Exception e) {
			logger.error("", e);
			respostaStatus = RespostaStatus.erroComException(e);
		}
		
		return respostaStatus;
	}
	
	@RequestMapping("/rest/contato-registrar")
	public void contatoFormJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String json = Uteis.bodyToString(request);
		
		ContatoVH contatoVH = (ContatoVH) GSONUteis.getInstance().fromJson(json, ContatoVH.class);
		
		try {
			
			contatoVH.setCadastrarComentario(true);
			contatoVH.setEnviarEmail(true);
			
			contatoNegocio.cadastraContatoSite(contatoVH);
			
			GSONUteis.escreverJSONSucesso("ok", response);
		} catch (Exception e) {
			GSONUteis.escreverJSONErro(e, response);
		}
		
		return;
	}
	
	@RequestMapping("/rest/trabalhe-conosco")
	public void trabalheConosco(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * String gRecaptchaResponse = request.getParameter("gRecaptchaResponse");
		 * 
		 * if( ! RecaptchaUtil.isValid(gRecaptchaResponse) ) { return
		 * RespostaStatus.erroComMensagem( "Responda Não Sou Robô!" ); }
		 */
		
		String json = Uteis.bodyToString(request);
		
		ContatoVH contatoVH = (ContatoVH) GSONUteis.getInstance().fromJson(json, ContatoVH.class);
		
		try {
			
			contatoVH.setCadastrarComentario(true);
			contatoVH.setEnviarEmail(true);
			
			contatoNegocio.cadastraContatoSite(contatoVH);
			
			GSONUteis.escreverJSONSucesso("ok", response);
		} catch (Exception e) {
			GSONUteis.escreverJSONErro(e, response);
		}
		
		return;
	}
	
	@RequestMapping("/rest/fretamento-registrar")
	public void fretamentoFormJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * String gRecaptchaResponse = request.getParameter("gRecaptchaResponse");
		 * 
		 * if( ! RecaptchaUtil.isValid(gRecaptchaResponse) ) { return
		 * RespostaStatus.erroComMensagem( "Responda Não Sou Robô!" ); }
		 */
		
		String json = Uteis.bodyToString(request);
		
		ContatoVH contatoVH = (ContatoVH) GSONUteis.getInstance().fromJson(json, ContatoVH.class);
		
		try {
			
			contatoVH.setCadastrarComentario(true);
			
			contatoNegocio.cadastrarFretamento(contatoVH);
			
			GSONUteis.escreverJSONSucesso("ok", response);
		} catch (Exception e) {
			GSONUteis.escreverJSONErro(e, response);
		}
		
		return;
	}
	
//	@RequestMapping("buscar-cidades-por-estado-json")
//	public String buscarCidadesPeloEstadoJson(HttpServletRequest request, HttpServletResponse response) throws Exception{
//
//		String sigla = request.getParameter("estado");
//
//		if(sigla !=  null && !"null".equals(sigla)){
//
//			List<Cidade> cidades = contatoNegocio.listarCidadesPelaSiglaEstado(sigla); 
//
//			GSONUteis.escreverJSON(cidades, response);
//		}
//
//		return null;
//	}
	

	
}