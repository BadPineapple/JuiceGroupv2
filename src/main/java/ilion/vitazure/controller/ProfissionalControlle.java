package ilion.vitazure.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.util.json.JsonString;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.HorarioPossivelAtendimento;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.model.TemaTrabalho;
import ilion.vitazure.negocio.AgendaNegocio;
import ilion.vitazure.negocio.EnderecoNegocio;
import ilion.vitazure.negocio.EspecialidadeNegocio;
import ilion.vitazure.negocio.FormacaoAcademicaNegocio;
import ilion.vitazure.negocio.HorarioAtendimentoNegocio;
import ilion.vitazure.negocio.PessoaNegocio;
import ilion.vitazure.negocio.ProfissionalNegocio;
import ilion.vitazure.negocio.ProfissionalVH;
import ilion.vitazure.negocio.TemaAtendimentoNegocio;

@Controller
@SessionAttributes("pessoaSessao")
@PessoaLogada
public class ProfissionalControlle {

	static Logger logger = Logger.getLogger(PessoaController.class);

	@Autowired
	private ProfissionalNegocio profissionalNegocio;

	@Autowired
	private PessoaNegocio pessoaNegocio;

	@Autowired
	private EnderecoNegocio enderecoNegocio;

	@Autowired
	private FormacaoAcademicaNegocio formacaoAcademicaNegocio;

	@Autowired
	private TemaAtendimentoNegocio temaNegocio;

	@Autowired
	private EspecialidadeNegocio especialidadeNegocio;

	@Autowired
	private HorarioAtendimentoNegocio horarioNegocio;

	@Autowired
	private AgendaNegocio agendaNegocio;

	@Autowired
	private ArquivoUteis arquivosUteis;

	@Autowired
	private PropNegocio propNegocio;

	@Autowired
	private ArquivoNegocio arquivoNegocio;

	private Gson gson = new Gson();

	@PostMapping(value = "/vitazure/perfilProfissional", produces = "application/json")
	@ResponseBody
	public ResponseEntity<JsonString> atualizarPerfilProfissional(HttpServletRequest request,
			@RequestBody ProfissionalVH profissionalVH) {
		try {
			if (profissionalVH.getProfissional().getPessoa().getFoto() != null
					&& !profissionalVH.getProfissional().getPessoa().getFoto().getArquivo1().equals("")) {
				profissionalVH.getProfissional().getPessoa()
						.setFoto(arquivoNegocio.inserir(profissionalVH.getProfissional().getPessoa().getFoto()));
			} else if (profissionalVH.getProfissional().getPessoa().getFoto() == null
					|| profissionalVH.getProfissional().getPessoa().getFoto().getId().equals("")) {
				profissionalVH.getProfissional().getPessoa().setFoto(null);
			}
			profissionalNegocio.validarDados(profissionalVH);
			profissionalVH.getProfissional()
					.setPessoa(pessoaNegocio.incluirAtualizar(profissionalVH.getProfissional().getPessoa()));
			profissionalVH.setProfissional(profissionalNegocio.incluirAtualizar(profissionalVH.getProfissional()));
			especialidadeNegocio.validarItens(profissionalVH.getEspecialidade(), profissionalVH.getProfissional());
			temaNegocio.validarItens(profissionalVH.getTemasTrabalho(), profissionalVH.getProfissional());
			formacaoAcademicaNegocio.validarItens(profissionalVH.getFormacaoAcademica(),
					profissionalVH.getProfissional());
			enderecoNegocio.validarItens(profissionalVH.getEnderecoAtendimento(), profissionalVH.getProfissional());
			horarioNegocio.validarItens(profissionalVH.getHorarioAtendimento(), profissionalVH.getProfissional());
			profissionalNegocio.dadosProfissionaisCompleto(profissionalVH);
			request.getSession().setAttribute(PessoaNegocio.PROFISSIONAL_COMPLETO,
					profissionalVH.getProfissional().getDadosCompleto());
			StringBuilder listItensAtualizar = new StringBuilder();
			if (profissionalVH.getItensIncompletos() != null && !profissionalVH.getItensIncompletos().isEmpty()) {
				listItensAtualizar.append(
						"<div class=\"col-12 col-md-12 col-xl-12\"><i class=\"fas fa-exclamation-triangle\" style=\"color: #dc3545;padding: 13px;\"></i>Itens aguardando preenchimentos :</div>");
				profissionalVH.getItensIncompletos().stream().forEach(valor -> listItensAtualizar.append(
						"<div class=\"col-12 col-md-12 col-xl-12\" style=\"line-height: 3.4rem;color: #dc3545;\"><i class=\"fas fa-check\" style=\"color: #dc3545;padding: 6px;\"></i>"
								+ valor + "</div>"));
			}
			return new ResponseEntity<>(new JsonString("Perfil Profissional Atualizado!" + listItensAtualizar),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(Uteis.tratamentoMensagemErro(e)), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/vitazure/imagem", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> UploadImagemCapaCategoria(MultipartHttpServletRequest request) {

		List<MultipartFile> files = request.getFiles("files");
		String path = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
		try {
			List<Arquivo> arquivos = arquivosUteis.uploadArquivos(files, path, "fotos");
			Arquivo arquivo = new Arquivo();
			if (arquivos != null && arquivos.size() > 0) {
				arquivo = arquivos.get(0);
			}
			return new ResponseEntity<>(gson.toJson(arquivo), HttpStatus.OK);
		} catch (ValidationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/vitazure/listaProfissionais")
	public String souProfissional(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		List<Profissional> lisProfissional = profissionalNegocio.consultarProfissionaisAtivos();
		lisProfissional.stream().forEach(profissional-> {
			profissional.getPessoa().setCidade(enderecoNegocio.consultarCidadeEnderecoPorProfissional(profissional.getId()));
		});
		List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(PessoaSessao);
		request.setAttribute("agendaDia", listAgendaDia);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", true);
		request.setAttribute("especialidades", EspecialidadesEnum.values());
		request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
		consultarDataDisponivelProfissionais(lisProfissional, false, false);
		request.getSession().setAttribute("listProfissionais", lisProfissional);
		request.setAttribute("estados", EstadoEnum.values());
		return "/ilionnet2/vitazure/resultado-de-busca";
	}
	@GetMapping("/resultado-de-busca/{palavraChave}/{especialista}/{estado}/{cidade}")
	public String buscaProfissional(HttpServletRequest request, @PathVariable String palavraChave,@PathVariable String especialista , @PathVariable String estado,@PathVariable String cidade) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		List<Profissional> lisProfissional = profissionalNegocio.consultarProfissionaisFiltro(palavraChave,especialista,estado,cidade);
		lisProfissional.stream().forEach(profissional-> {
			profissional.getPessoa().setCidade(enderecoNegocio.consultarCidadeEnderecoPorProfissional(profissional.getId()));
		});
		List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(PessoaSessao);
		request.setAttribute("agendaDia", listAgendaDia);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", true);
		consultarDataDisponivelProfissionais(lisProfissional, false, false);
		request.getSession().setAttribute("listProfissionais", lisProfissional);
		request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
		request.setAttribute("especialidades", EspecialidadesEnum.values());
		request.setAttribute("estados", EstadoEnum.values());
		return "/ilionnet2/vitazure/resultado-de-busca";
	}

	private List<Profissional> consultarDataDisponivelProfissionais(List<Profissional> lisProfissional,
			Boolean atendimentoOnline, Boolean atendimentoPresencial) {
		List<Date> lista = new ArrayList<Date>();

		int diasIncrementado = 0;
		while (diasIncrementado < 60) {
			lista.add(Uteis.acrescentar(new Date(), Calendar.DATE, diasIncrementado));
			diasIncrementado++;
		}
		lisProfissional.stream().forEach(profissional -> {
			List<HorarioAtendimento> listaHorarioatendimento = horarioNegocio
					.consultarHorariosAtendimentoPorProfissional(profissional.getId(), atendimentoOnline,
							atendimentoPresencial);
			List<Date> datasPossivelAgendamento = lista.stream().filter(o1 -> {
				return listaHorarioatendimento.stream().map(HorarioAtendimento::getDiaSemana)
						.anyMatch(i2 -> i2.getValue() == o1.getDay());
			}).collect(Collectors.toList());
			profissional.getDatasPossivelAgendamento().addAll(datasPossivelAgendamento);
		});
		return lisProfissional;

	}

	public List<HorarioPossivelAtendimento> maisTeste(Profissional profissional, Boolean atendimentoOnline,
			Boolean atendimentoPresencial, Date dataConsulta) {
		List<HorarioAtendimento> listaHorarioatendimento = new ArrayList<HorarioAtendimento>();
		listaHorarioatendimento.addAll(horarioNegocio.consultarHorariosAtendimentoPorProfissional(profissional.getId(),
				atendimentoOnline, atendimentoPresencial));
		List<HorarioPossivelAtendimento> listHorarioPossivelAtendimento = new ArrayList<HorarioPossivelAtendimento>();
		listaHorarioatendimento.stream().filter(a -> a.getDiaSemana().getValue() == dataConsulta.getDay())
				.forEach(horarioAtendimento -> {
					Integer quantidadeMinutosAtendimento = Uteis.diferencaEmMinutos(
							Uteis.converterHoraEmDate(horarioAtendimento.getHoraFim(), "HH:mm"),
							Uteis.converterHoraEmDate(horarioAtendimento.getHoraInicio(), "HH:mm"));
					Integer quantidadePossiveisAtendimento = quantidadeMinutosAtendimento
							/ (Integer.parseInt(profissional.getDuracaoAtendimento().getNome()) + 10);
					int x = 1;
					String horaInicio = horarioAtendimento.getHoraInicio();
					String horaFinal = "";
					if (!horarioAtendimento.getHoraInicio().equals("")) {
						HorarioPossivelAtendimento horarioPossivelAtendimentoInicial = new HorarioPossivelAtendimento();
						horarioPossivelAtendimentoInicial.setHoraPossivelAtendiemnto(horaInicio);
						horarioPossivelAtendimentoInicial.setDiaSemanaEnum(horarioAtendimento.getDiaSemana());
						horarioPossivelAtendimentoInicial.setCodigoProfissional(profissional.getId());
						if (horarioAtendimento.getEnderecoAtendimento() != null
								&& horarioAtendimento.getEnderecoAtendimento().getId() != null) {
							horarioPossivelAtendimentoInicial.setEnderecoatendimento(horarioAtendimento
									.getEnderecoAtendimento().getLogradouro().concat(" - ")
									.concat(horarioAtendimento.getEnderecoAtendimento().getComplemento()).concat(" - ")
									.concat(horarioAtendimento.getEnderecoAtendimento().getBairro().concat(" - ")
											.concat(horarioAtendimento.getEnderecoAtendimento().getCidade())
											.concat(" - ").concat(horarioAtendimento.getEnderecoAtendimento()
													.getEstado().getNome())));
							horarioPossivelAtendimentoInicial
									.setLinkGoogleMaps(horarioAtendimento.getEnderecoAtendimento().getLinkGoogleMaps());
						}
						listHorarioPossivelAtendimento.add(horarioPossivelAtendimentoInicial);
						while (x <= quantidadePossiveisAtendimento) {
							HorarioPossivelAtendimento horarioPossivelAtendimento = new HorarioPossivelAtendimento();
							horarioPossivelAtendimento
									.setHoraPossivelAtendiemnto(Uteis.calculodeHoraSemIntervalo(horaInicio, 1,
											(Integer.parseInt(profissional.getDuracaoAtendimento().getNome()) + 10)));
							horarioPossivelAtendimento.setDiaSemanaEnum(horarioAtendimento.getDiaSemana());
							horarioPossivelAtendimento.setCodigoProfissional(profissional.getId());
							if (horarioAtendimento.getEnderecoAtendimento() != null
									&& horarioAtendimento.getEnderecoAtendimento().getId() != null) {
								horarioPossivelAtendimento.setEnderecoatendimento(horarioAtendimento
										.getEnderecoAtendimento().getLogradouro().concat(" - ")
										.concat(horarioAtendimento.getEnderecoAtendimento().getComplemento())
										.concat(" - ").concat(horarioAtendimento.getEnderecoAtendimento().getBairro()));
								horarioPossivelAtendimento.setLinkGoogleMaps(
										horarioAtendimento.getEnderecoAtendimento().getLinkGoogleMaps());
							}
							listHorarioPossivelAtendimento.add(horarioPossivelAtendimento);
							horaInicio = horarioPossivelAtendimento.getHoraPossivelAtendiemnto();
							x++;
						}
					}
				});

		return listHorarioPossivelAtendimento;
	}

	@PostMapping(value = "/vitazure/agendar", produces = "application/json")
	@ResponseBody
	public ResponseEntity<JsonString> agendar(HttpServletRequest request, @RequestBody String retornoToken) {
		try {
			Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
			List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(PessoaSessao);
			request.setAttribute("agendaDia", listAgendaDia);
			JSONObject jsonRetornoToken = new JSONObject(retornoToken);
			agendaNegocio.incluirAgendaPaciente(jsonRetornoToken, PessoaSessao);
			return new ResponseEntity<>(new JsonString("Agendamento realizado com sucesso."), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/vitazure/alterarSituacaoAgenda", produces = "application/json")
	@ResponseBody
	public ResponseEntity<JsonString> definirAgendamento(HttpServletRequest request, @RequestBody String jsonAlterar) {
		try {
			JSONObject jsonRetornoToken = new JSONObject(jsonAlterar);
			Long idAgenda = Long.parseLong(jsonRetornoToken.get("idAgenda").toString());
			String situacaoAlterar = jsonRetornoToken.get("situacaoAlterar").toString();
			agendaNegocio.alterarAgenda(idAgenda, situacaoAlterar);
			return new ResponseEntity<>(new JsonString("Agenda " + situacaoAlterar.toLowerCase() + " com Sucesso!"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JsonString(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/vitazure/profissionais")
	public String profissionais(HttpServletRequest request) {
		Pessoa PessoaSessao = (Pessoa) request.getSession().getAttribute(PessoaNegocio.ATRIBUTO_SESSAO);
		List<Profissional> lisProfissional = (List<Profissional>) request.getSession().getAttribute("listProfissionais");
		lisProfissional.stream().forEach(profissional-> {
			profissional.getPessoa().setCidade(enderecoNegocio.consultarCidadeEnderecoPorProfissional(profissional.getId()));
		});
		List<Agenda> listAgendaDia = agendaNegocio.consultarAgendaDia(PessoaSessao);
		request.setAttribute("agendaDia", listAgendaDia);
		request.setAttribute("pessoa", PessoaSessao);
		request.setAttribute("areaRestrita", true);
		request.setAttribute("especialidades", EspecialidadesEnum.values());
		request.setAttribute("tiposProfissional", TipoProfissionalEnum.values());
		consultarDataDisponivelProfissionais(lisProfissional, false, false);
		request.getSession().setAttribute("listProfissionais", lisProfissional);
		request.setAttribute("estados", EstadoEnum.values());
		return "/ilionnet2/vitazure/resultado-de-busca";
	}
	
}
