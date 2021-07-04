package ilion.arquivo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ClienteImagemService;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class ArquivoController {

	@Autowired
	private ArquivoNegocio arquivoNegocio;

	@Autowired
	private ClienteImagemService clienteImagemService;
	
	@RequestMapping("/ilionnet/arquivo-acao")
	public String acao(String nomeClasse, 
			String idObjeto, 
			String codigo, 
			String acao, 
			String retorno, 
			HttpServletRequest request) throws Exception {
		
		if(acao == null) {
			return "redirect:"+retorno+"?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
		}
		
		String[] idsArquivo = request.getParameterValues("idsArquivo");
		
		if(idsArquivo != null) {
			for (String idArquivo : idsArquivo) {
				
				if(idArquivo != null) {
					Arquivo arquivo = new Arquivo(idArquivo);
					
					if(acao.toLowerCase().equals("excluir")) {
						
						arquivoNegocio.excluir(arquivo);
						
					} else if(acao.toLowerCase().equals("naoPublicado")) {
						
						arquivoNegocio.alterarParaNaoPublicado(arquivo);
						
					} else if(acao.toLowerCase().startsWith(("layout"))) {
						String layout = acao.substring(acao.indexOf("-")+1);
						
						arquivoNegocio.alterarLayout(arquivo, layout);
					}
				}
			}
		}
		
		String[] ids = request.getParameterValues("ids");
		String[] posicoesAnteriores = request.getParameterValues("posicoesAnteriores");
		String[] posicoesNovas = request.getParameterValues("posicoesNovas");
		
		arquivoNegocio.alterarPosicoes(ids, posicoesAnteriores, posicoesNovas);
		
		return "redirect:"+retorno+"?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
	}

	@RequestMapping("/ilionnet/arquivo-excluir")
	public String excluir(HttpServletRequest request) throws Exception {

		String idExcluir = request.getParameter("idExcluir");

		if(idExcluir != null) {
			arquivoNegocio.excluir(new Arquivo(idExcluir));
		}

		String nomeClasse = request.getParameter("nomeClasse");
		String idObjeto = request.getParameter("idObjeto");
		String codigo = request.getParameter("codigo");

		return "redirect:arquivo-form.sp?nomeClasse="+nomeClasse+"&idObjeto="+idObjeto+"&codigo="+codigo;
	}
	
	@RequestMapping("/ilionnet/ilionnet-login-excluir")
	public String excluirIlionnetLogin(String idExcluir) throws Exception {
		
		if(idExcluir != null) {
			arquivoNegocio.excluir(new Arquivo(idExcluir));
		}
		
		clienteImagemService.limparCache();
		
		return "redirect:ilionnet-login-form.sp";
	}
}