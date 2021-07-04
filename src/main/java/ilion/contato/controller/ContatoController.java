package ilion.contato.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ilion.admin.negocio.Usuario;
import ilion.contato.negocio.ArquivoExcelContatoExportacao;
import ilion.contato.negocio.ArquivoTextoContatoExportacao;
import ilion.contato.negocio.ArquivoTextoContatoImportacao;
import ilion.contato.negocio.Contato;
import ilion.contato.negocio.ContatoExportacao;
import ilion.contato.negocio.ContatoGrupo;
import ilion.contato.negocio.ContatoGrupoNegocio;
import ilion.contato.negocio.ContatoImportacao;
import ilion.contato.negocio.ContatoNegocio;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import net.mlw.vlh.ValueList;

@Controller
public class ContatoController {

	static Logger logger = Logger.getLogger(ContatoController.class);

	@Autowired
	private ContatoNegocio contatoNegocio;

	@Autowired
	private ContatoGrupoNegocio contatoGrupoNegocio;
	
	@Autowired
	private ArquivoTextoContatoExportacao textoExportacao;
	
	@Autowired
	private ArquivoExcelContatoExportacao excelExportacao;

	@RequestMapping("/ilionnet/contato")
	@UsuarioLogado("contato.sp")
	public String busca(HttpServletRequest request) {

		VLHForm vlhForm = VLHForm.getVLHSession("contatosLista", request);

		ValueList contatos = contatoNegocio.busca(vlhForm, null, null, new ValueListInfo(vlhForm));
		request.setAttribute("contatos", contatos);

		request.setAttribute("vlhForm", vlhForm);

		return "/ilionnet/modulos/contato/contato-busca";
	}

	@RequestMapping("/ilionnet/contato-comentarios")
	@UsuarioLogado("contato.sp")
	public String comentarios(HttpServletRequest request) {

		Long id = Uteis.converterLong(request.getParameter("id"));

		if( id == null ) {
			return "redirect:contato.sp?m=id-contato-nulo";
		}

		Contato contato = (Contato) contatoNegocio.consultar(Contato.class, id);
		request.setAttribute("contato", contato);

		List contatoComentarios = contatoNegocio.listarContatoComentarios(contato);
		request.setAttribute("contatoComentarios", contatoComentarios);

		return "/ilionnet/modulos/contato/contato-comentarios";
	}

	@RequestMapping("/ilionnet/contato-excluir")
	@UsuarioLogado("contato-excluir.sp")
	public String contatoExcluir(String id) throws Exception {

		Long idLong = Uteis.converterLong(id);

		if(idLong != null) {

			contatoNegocio.excluirContato(new Contato(idLong));

		}

		return "redirect:contato.sp?m=contato-excluido"; 
	}

	@RequestMapping("/ilionnet/contato-excluir-selecionados")
	@UsuarioLogado("contato-excluir.sp")
	public String contatoExcluirSelecionados(String[] idsContatos, HttpServletRequest request) throws Exception {

		Integer qtdExcluidos = null;

		if(idsContatos != null && idsContatos.length != 0) {
			for (int i = 0; i < idsContatos.length; i++) {
				String idString = idsContatos[i];

				contatoNegocio.excluirContato(new Contato(new Long(idString)));

			}

			qtdExcluidos = idsContatos.length;
		}

		return "redirect:contato.sp?qtdExcluidos="+qtdExcluidos;
	}

	@RequestMapping("/ilionnet/contato-importar")
	@UsuarioLogado("contato-importar.sp")
	public String contatoImportar() throws Exception {
		return "/ilionnet/modulos/contato/contato-importar";
	}

	@PostMapping("/ilionnet/contato-importar-executar")
	@UsuarioLogado("contato-importar.sp")
	public String contatoImportarExecutar(HttpServletRequest request) throws Exception {

		Long idLong = Uteis.converterLong( (String) request.getParameter("id"));
		if(idLong == null) {
			return "redirect:contato-importar.sp?m=grupo-nao-selecionado";
		}

		ContatoGrupo contatoGrupo = (ContatoGrupo) contatoNegocio.consultar(ContatoGrupo.class, idLong);
		if(contatoGrupo == null) {
			return "redirect:contato-importar.sp?m=grupo-nao-encontrado";
		}

		String charset = (String) request.getParameter("charset");

		MultipartFile arquivo = null;

		if( request instanceof MultipartHttpServletRequest ) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

			arquivo = mRequest.getFile("arquivo");
		}

		if( arquivo == null || arquivo.isEmpty() ) {
			return "redirect:contato-importar.sp?m=nenhum-arquivo";
		}

		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");

		ContatoImportacao contatoImportacao = new ArquivoTextoContatoImportacao(arquivo.getInputStream(), charset, contatoGrupo, usuarioSessao);

		contatoImportacao.importar();

		String mensagem = contatoImportacao.getLog();

		request.setAttribute("mensagem", mensagem);

		return "/ilionnet/modulos/contato/contato-importar";
	}

	@RequestMapping("/ilionnet/contato-grupo-excluir-contatos")
	@UsuarioLogado("contato-grupo-excluir-contatos.sp")
	public String excluirContatosPorGrupo(String id) throws Exception {

		Long idLong = Uteis.converterLong(id);

		if(idLong != null) {
			ContatoGrupo contatoGrupo = (ContatoGrupo) contatoNegocio.consultar(ContatoGrupo.class, idLong);

			if(contatoGrupo != null) {
				contatoNegocio.excluirContatosPorGrupo(contatoGrupo);

				return "redirect:contato-grupo-form.sp?m=contatos-excluidos";
			}
		}

		return "/ilionnet/modulos/contato/contato-grupo-form.sp";
	}

	@RequestMapping("/ilionnet/contato-grupo-excluir")
	@UsuarioLogado("contato-grupo-excluir.sp")
	public String contatoGrupoExcluir(String id) {

		Long idLong = Uteis.converterLong(id);

		if(idLong == null) {
			return "redirect:contato-grupo-form.sp?m=id-grupo-nao-selecionado";
		}

		try {

			contatoGrupoNegocio.excluir(new ContatoGrupo(idLong));
			
		} catch (Exception e) {
			logger.error("", e);
			return "redirect:contato-grupo-form.sp?m="+e.getMessage();
		}

		return "redirect:contato-grupo-form.sp";
	}

	@RequestMapping("/ilionnet/contato-grupo-transferir")
	@UsuarioLogado("contato-grupo-transferir.sp")
	public String contatoGrupoTransferir(String idGrupoContatoOrigem, String idGrupoContatoDestino, String qtd) {

		Long idGrupoContatoOrigemLong = Uteis.converterLong(idGrupoContatoOrigem);
		Long idGrupoContatoDestinoLong = Uteis.converterLong(idGrupoContatoDestino);

		if(idGrupoContatoOrigemLong != null && idGrupoContatoDestinoLong != null) {
			ContatoGrupo grupoContatoOrigem = new ContatoGrupo(idGrupoContatoOrigemLong);
			ContatoGrupo grupoContatoDestino = new ContatoGrupo(idGrupoContatoDestinoLong);

			contatoGrupoNegocio.transferirContatos(grupoContatoOrigem, grupoContatoDestino, qtd);

			return "redirect:contato-grupo-form.sp?m=contatos-transferidos";
		}

		return "redirect:contato-grupo-form.sp";
	}

	@RequestMapping("/ilionnet/contato-exportar")
	@UsuarioLogado("contato-exportar.sp")
	public String contatoExportar() throws Exception {
		return "/ilionnet/modulos/contato/contato-exportar";
	}

	@RequestMapping("/ilionnet/contato-exportar-executar")
	@UsuarioLogado("contato-exportar.sp")
	public String contatoExportar(String idContatoGrupo, String permissaoEmail, String exportarPara, HttpServletResponse response) throws Exception {
		Long idContatoGrupoLong = Uteis.converterLong(idContatoGrupo);

		ContatoGrupo contatoGrupo = new ContatoGrupo(idContatoGrupoLong);

		Boolean permissaoEmailBoolean = Uteis.converterBoolean(permissaoEmail);

		if("Todos".equals(permissaoEmail)) {
			permissaoEmailBoolean = null;
		}

		ContatoExportacao contatoExportacao = null;

		if("xls".equals(exportarPara)) {
			excelExportacao.setContatoGrupo(contatoGrupo);
			excelExportacao.setPermissaoEmail(permissaoEmailBoolean);
			excelExportacao.setResponse(response);
			contatoExportacao = excelExportacao;
			
			//contatoExportacao = new ArquivoExcelContatoExportacao(contatoGrupo, permissaoEmailBoolean, response);
		} else {
			textoExportacao.setContatoGrupo(contatoGrupo);
			textoExportacao.setPermissaoEmail(permissaoEmailBoolean);
			textoExportacao.setResponse(response);
			textoExportacao.setExportados(new StringBuilder());
			contatoExportacao = textoExportacao;
			
			//contatoExportacao = new ArquivoTextoContatoExportacao(contatoGrupo, permissaoEmailBoolean, response);
		}

		try {

			contatoExportacao.exportar();

			return null;
		} catch (RuntimeException e) {
			return "redirect:contato-exportar.sp?m="+e.getMessage();

		} catch (Exception e) {
			String m = "problema na exportação de contatos";
			logger.error(m, e);

			return "redirect:contato-exportar.sp?m=erro-"+e.getMessage();
		}
	}

}