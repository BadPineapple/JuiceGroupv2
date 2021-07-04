package ilion.arquivo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.Redimencionamento;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/ilionnet-login-form")
@SessionAttributes("arquivo")
@UsuarioLogado("ilionnet-login-form.sp")
public class IlionnetLoginFormController {

	@Autowired
	private ArquivoNegocio arquivoNegocio;
	
	@Autowired
	private Redimencionamento redimencionamento;
	
	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, String nomeClasse, String idObjeto, String codigo, ModelMap modelMap) {
		Arquivo arquivo = null;
		
		if( ! Uteis.ehNuloOuVazio(id) ) {
			arquivo = new Arquivo(id);
			arquivo = (Arquivo) arquivoNegocio.consultar(Arquivo.class, id);
		} else {
			if(nomeClasse == null || nomeClasse.length() == 0) {
				nomeClasse = "login-topo";
			}
			
			arquivo = new Arquivo();
			//arquivo.setDestaque(Boolean.FALSE);
			arquivo.setNaoPublicado(Boolean.FALSE);
			arquivo.setTipo((byte)1);
			
			arquivo.setNomeClasse(nomeClasse);
			arquivo.setIdObjeto("1");
			
			arquivo.setImagemGrande(false);
			arquivo.setArquivosEmMassa(false);
			arquivo.setLayout("login");
		}
		
		arquivo.setLarguraPequena(150);
		arquivo.setOpcao("imagem");
		
		modelMap.addAttribute("arquivo", arquivo);
		
		return "/ilionnet/modulos/arquivo/ilionnetlogin-form";
	}
	
	@ModelAttribute("arquivos")
	public List listarArquivos() {
		
		List arquivos = arquivoNegocio.listarArquivos("login-topo", "1", null);
		List arquivos2 = arquivoNegocio.listarArquivos("login-logo", "1", null);
		
		arquivos.addAll(arquivos2);
		
		return arquivos;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(
			@ModelAttribute("arquivo") Arquivo arquivo,
			BindingResult bindingResult, 
			HttpServletRequest request) throws Exception {
		
		String acao = (String) request.getParameter("acao");
		
		if("refresh".equals(acao)) {
			bindingResult.reject("", "");
			return "/ilionnet/modulos/arquivo/ilionnetlogin-form";
		}
		
		validar(arquivo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/ilionnet/modulos/arquivo/ilionnetlogin-form";
		} else {
			
			arquivo = arquivoNegocio.gravarImagemIlionnetCliente(arquivo);
			
			return "redirect:ilionnet-login-form.sp?nomeClasse="+arquivo.getNomeClasse();
		}
	}
	
	private void validar(Arquivo arquivo, BindingResult bindingResult) throws Exception {
		if(arquivo.getArquivo() == null || arquivo.getArquivo().isEmpty()) {
			bindingResult.reject("", "Nenhum arquivo selecionado.");
			return;
		}
		
		ArquivoTipoEnum imagemTipoEnum = ArquivoTipoEnum.IMAGEM;
		
		if( ! imagemTipoEnum.ehExtensaoPermitida(arquivo.getArquivo().getOriginalFilename()) ) {
			
			bindingResult.reject("", "O arquivo selecionado para upload n�o � uma imagem!");
			
		} else if( ! redimencionamento.ehImagemValida(arquivo.getArquivo().getInputStream()) ) {
			
			bindingResult.reject("", "Formato de imagem inv�lido!");
			
		} else if( ! imagemTipoEnum.ehTamanhoPermitido(arquivo.getArquivo().getSize()) ) {
			
			bindingResult.reject("", "M�ximo tamanho permitido � "+imagemTipoEnum.getInfoMaxSize()+".");
			
		}
	}
}