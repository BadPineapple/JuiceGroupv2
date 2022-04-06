package ilion.vitazure.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ilion.CustomErrorController;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.Usuario;
import ilion.admin.negocio.UsuarioNegocio;
import ilion.contato.negocio.ArquivoTextoContatoImportacao;
import ilion.contato.negocio.ContatoGrupo;
import ilion.contato.negocio.ContatoImportacao;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListImpl;
import ilion.util.ValueListInfo;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.ConselhoProfissionalEnum;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.FormacaoEnum;
import ilion.vitazure.enumeradores.SituacaoAprovacaoProfissionalEnum;
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.enumeradores.TemasTrabalhoEnum;
import ilion.vitazure.enumeradores.TempoAntecendenciaEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.Especialidade;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.model.TemaTrabalho;
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.ArquivoTextoImportarFuncionario;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.EspecialidadeNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.HorarioAtendimentoNegocio;
import ilion.vitazure.negocio.PagarMeNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;
import ilion.vitazure.negocio.ProfissionalVH;
import ilion.vitazure.negocio.RelatorioVitazure;
import ilion.vitazure.negocio.TemaAtendimentoNegocio;
import net.mlw.vlh.ValueList;

@Controller
public class menuVitazureController  extends CustomErrorController{

	@Autowired
	private PessoaNegocio pessoaNegocio;
	
	@Autowired
	private ProfissionalNegocio profissionalNegocio;
	
	@Autowired
	private FormacaoAcademicaNegocio formacaoAcademicaNegocio;
	
	@Autowired
	private EnderecoNegocio enderecoNegocio;
	
	@Autowired
	private AgendaNegocio agendaNegocio;
	
	@Autowired
	private PagarMeNegocio pagarMeNegocio;
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private TemaAtendimentoNegocio temaNegocio;
	
	@Autowired
	private EspecialidadeNegocio especialidadeNegocio;
	
	@Autowired
	private HorarioAtendimentoNegocio horarioNegocio;
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@Autowired
	private RelatorioVitazure relatorioVitazure;
	
	
	@RequestMapping("/cliente")
	@UsuarioLogado()
	public String clienteCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("clienteLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList arquivosAreaRestrita = pessoaNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao , "cliente");
		request.setAttribute("clientes", arquivosAreaRestrita);
		
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/clienteCons";
	}

	@RequestMapping("/profissional")
	@UsuarioLogado()
	public String profissionalCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("profissionalLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList profissionais = profissionalNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao);
		request.setAttribute("profissionais", profissionais);
		
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/profissionalCons";
	}
	
	@RequestMapping("/vitazure/cliente/{id}")
	@UsuarioLogado()
	public String editarCliente(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		try {
			Pessoa pessoa = new Pessoa();
			if (id != null && id != 0) {
				pessoa = pessoaNegocio.consultarPorId(id);
			}
			request.setAttribute("pessoa", pessoa);
			return "/ilionnet/modulos/vitazure/clienteForm";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/ilionnet/modulos/vitazure/clienteCons/?m=erro";
		}
	 }
	@RequestMapping("/vitazure/profissional/{id}")
	@UsuarioLogado()
	public String editarProfissional(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		try {
			Profissional profissional = new Profissional();
			List<FormacaoAcademica> formacao = new ArrayList<FormacaoAcademica>();
			List<EnderecoAtendimento> enderecoAtendimento = new ArrayList<EnderecoAtendimento>();
			List<TemaTrabalho> temaTrabalhoEnum = new ArrayList<TemaTrabalho>();
			List<Especialidade> especialidade = new ArrayList<Especialidade>();
			List<HorarioAtendimento> horarioAtendimento = new ArrayList<HorarioAtendimento>();
			if (id != null && id != 0) {
				profissional = profissionalNegocio.consultarPorPessoa(id);
				formacao.addAll(formacaoAcademicaNegocio.consultarFormacoesPorPessoa(profissional.getId()));
				enderecoAtendimento.addAll(enderecoNegocio.consultarEnderecoPorPessoa(profissional.getId()));
				temaTrabalhoEnum.addAll(temaNegocio.consultarTemasPorProfissional(profissional.getId()));
				especialidade.addAll(especialidadeNegocio.consultarEspecialidadesProfissional(profissional.getId()));
				horarioAtendimento.addAll(horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId() , false , false));
			}
			request.setAttribute("profissional", profissional);
			request.setAttribute("estados", EstadoEnum.values());
			request.setAttribute("situacaoAtendimento", SituacaoAprovacaoProfissionalEnum.values());
			request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
			request.setAttribute("especialidades", especialidade);
			request.setAttribute("duracoes", DuracaoAtendimentoEnum.values());
			request.setAttribute("duracaoAtendimentoValor", DuracaoAtendimentoEnum.values());
			request.setAttribute("tiposConta", TipoContaEnum.values());
			request.setAttribute("bancos", BancoEnum.values());
			request.setAttribute("temasTrabalho", temaTrabalhoEnum);
			request.setAttribute("tempoAntecendencia", TempoAntecendenciaEnum.values());
			request.setAttribute("formacoes", FormacaoEnum.values());
			request.setAttribute("formacao", formacao);
			request.setAttribute("enderecoAtendimento", enderecoAtendimento);
			request.setAttribute("conselhoProfissional", ConselhoProfissionalEnum.values());
			request.setAttribute("horarioAtendimento", horarioAtendimento);
			return "/ilionnet/modulos/vitazure/profissionalForm";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/ilionnet/modulos/vitazure/profissionalCons/?m=erro";
		}
	}
	
	@RequestMapping("vitazure/cliente/excluir/{id}")
	@UsuarioLogado()
	public String excluirCliente(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		try {
			pessoaNegocio.excluir(id);
			return "redirect:/cliente?m=ok";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/cliente?m=erro";
		}
	 }
	
	@RequestMapping("vitazure/profissional/excluir/{id}")
	@UsuarioLogado()
	public String excluirProfissional(
			@PathVariable Long id, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		try {
			pessoaNegocio.excluir(id);
			return "redirect:/profissional?m=ok";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgError", e.getMessage());
			return "redirect:/profissional?m=erro";
		}
	 }
	
	@RequestMapping("/agenda")
	@UsuarioLogado()
	public String agendaCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("agendaLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList agendas = agendaNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao , null);
		request.setAttribute("agendas", agendas);
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/agendaCons";
	}
	@RequestMapping("/movimentacoes")
	@UsuarioLogado()
	public String movimentacoesCons(HttpServletRequest request) {
		
		VLHForm vlhForm = VLHForm.getVLHSession("movimentacoesLista", request);
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		ValueList listPagamentos = pagarMeNegocio.buscar(vlhForm, new ValueListInfo(vlhForm) , usuarioSessao);
		request.setAttribute("listPagamentos", listPagamentos);
		request.setAttribute("vlhForm", vlhForm);
		
		return "/ilionnet/modulos/vitazure/movimentacoesCons";
	}
		
	@RequestMapping("/importarFuncionario")
	@UsuarioLogado()
	public String contatoImportar() {
		return "/ilionnet/modulos/vitazure/importarFuncionario";
	}
	
	@RequestMapping("/relResumoAtendimento")
	@UsuarioLogado()
	public String RelResumoAtendimento(HttpServletRequest request) {
		try {
		VLHForm vlhForm = VLHForm.getVLHSession("resumoAtendimento", request);
		if(vlhForm.getPalavraChave() != null) {
			ValueList agendas = agendaNegocio.relResumoAtendimento(vlhForm,new ValueListInfo(vlhForm));
			request.setAttribute("agendas", agendas);
		}else {
			ValueList agendas = new ValueListImpl(new ArrayList<>(), new ValueListInfo());
			request.setAttribute("agendas", agendas);
		}
		Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
		request.setAttribute("empresas", usuarioNegocio.consultarEmpresasCadastradas(usuarioSessao));
		request.setAttribute("status", StatusEnum.values());
		
			return "/ilionnet/modulos/vitazure/relResumoAtendimento";
		} catch (Exception e) {
			return "/ilionnet/modulos/vitazure/relResumoAtendimento";
		}
	}
	
	@RequestMapping("/download-pdf")
	@UsuarioLogado()
	public void enviarArquivo(HttpServletResponse response,HttpServletRequest request) throws Exception {
	        ServletOutputStream stream = null;
	        BufferedInputStream buf = null;

	        try {
	        	
	        	VLHForm vlhForm = VLHForm.getVLHSession("resumoAtendimento", request);
	        	ValueList agendas = agendaNegocio.relResumoAtendimento(vlhForm,new ValueListInfo(vlhForm));

	        	stream = response.getOutputStream();
	        	String urlDownload = relatorioVitazure.UrlDownloadArquivo(agendas, vlhForm ,"RelResumoAtendimento", "Relatório Resumo Atendimento");
	            File pdf = new File(urlDownload);
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition","attachment; filename="+vlhForm.getPalavraChave());
	            response.setContentLength((int) pdf.length());

	            FileInputStream input = new FileInputStream(pdf);
	            buf = new BufferedInputStream(input);

	            int readBytes = 0;

	            while ((readBytes = buf.read()) != -1)
	                stream.write(readBytes);
	        } catch (IOException ioe) {
	            throw new ServletException(ioe.getMessage());
	        }catch (Exception e) {
				e.printStackTrace();
			} finally {
	            if (stream != null) {
	                stream.close();
	            }

	            if (buf != null) {
	                buf.close();
	            }
	        }
	    }
	
	@RequestMapping("/download-excel")
	@UsuarioLogado()
	public void enviarArquivoExcel(HttpServletResponse response,HttpServletRequest request) {
		try {
			VLHForm vlhForm = VLHForm.getVLHSession("resumoAtendimento", request);
			ValueList agendas = agendaNegocio.relResumoAtendimento(vlhForm,new ValueListInfo(vlhForm));
			HSSFWorkbook wb = relatorioVitazure.gerarExcel(response,agendas.getList(), vlhForm.getPalavraChave());
			String nome = vlhForm.getPalavraChave()+Uteis.formatarDataHora(new Date(), "yyyy-MM-dd")+".xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","inline; filename="+nome);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.close();
		} catch (Exception e) {
		}
	}
	
	
	@RequestMapping("/relExtratoFinanceiro")
	@UsuarioLogado()
	public String RelRelExtratoFinanceiro(HttpServletRequest request) {
		ValueList agendas = new ValueListImpl(new ArrayList<>(), new ValueListInfo());
		request.setAttribute("agendas", agendas);
//		request.setAttribute("profissionais", listPagamentos);
		return "/ilionnet/modulos/vitazure/relExtratoFinanceiro";
	}
	
	@PostMapping("/funcionario-importar-executar")
	@UsuarioLogado()
	public String contatoImportarExecutar(HttpServletRequest request){

		try {
			MultipartFile arquivo = null;
			String path = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			if( request instanceof MultipartHttpServletRequest ) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
	
				arquivo = mRequest.getFile("arquivo");
			}
	
			if( arquivo == null || arquivo.isEmpty() ) {
				return "redirect:importarFuncionario.sp?m=nenhum arquivo selecionado";
			}
			Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
	
			ContatoImportacao contatoImportacao =  new ArquivoTextoImportarFuncionario (arquivo.getInputStream(), usuarioSessao);

		    contatoImportacao.importarExcelFuncionario(arquivo, path, "excelImportarFuncionario");
		    

		String mensagem = contatoImportacao.getLog();

		request.setAttribute("mensagem", mensagem);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:importarFuncionario.sp?m="+e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:importarFuncionario.sp?m="+e.getMessage();
		}
		return "/ilionnet/modulos/vitazure/importarFuncionario";
		
	}
	
	
	
}
