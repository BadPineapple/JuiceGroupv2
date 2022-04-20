package ilion.vitazure.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.admin.negocio.Usuario;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.ResponsePaymentTransaction;
import ilion.me.pagar.model.SplitRule;
import ilion.me.pagar.model.Transaction;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.PagamentoPagarMe;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;
import net.mlw.vlh.ValueList;

@Service
public class AgendaNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private ProfissionalNegocio profissionalNegocio;

	@Autowired
	private WherebyApi wherebyApi;

	@Autowired
	private PropNegocio propNegocio;

	@Autowired
	private PagarMeNegocio pagarMeNegocio;

	@Autowired
	private EnvioEmailConsulta envioEmailConsulta;

	@Autowired
	private AgendaNegocio agendaNegocio;

	private List<Agenda> listAgendasEnviarEmail;

	public List<Agenda> getListAgendasEnviarEmail() {
		if (listAgendasEnviarEmail == null) {
			listAgendasEnviarEmail = new ArrayList<Agenda>();
		}
		return listAgendasEnviarEmail;
	}

	public void setListAgendasEnviarEmail(List<Agenda> listAgendasEnviarEmail) {
		this.listAgendasEnviarEmail = listAgendasEnviarEmail;
	}

	public Collection<SplitRule> getSplitedRules(Profissional profissional, String situacaoPagarme,
			String tipoCobranca) {
		Collection<SplitRule> rules = new ArrayList<>();
		SplitRule splitRulesProfissional = new SplitRule();
		splitRulesProfissional.setRecipientId(profissional.getIdRecebedor());
		splitRulesProfissional.setPercentage(
				tipoCobranca == "BOLETO" ? propNegocio.findValueIntegerById(PropEnum.PERCENTUAL_BOLETO_PROFISSIONAL, 0)
						: propNegocio.findValueIntegerById(PropEnum.PERCENTUAL_PIX_PROFISSIONAL, 0));
		splitRulesProfissional.setChargeProcessingFee(Boolean.FALSE);
		rules.add(splitRulesProfissional);
		SplitRule splitRulesEmpresa = new SplitRule();
		if (situacaoPagarme.equals("PRODUCAO")) {
			splitRulesEmpresa.setRecipientId("re_ckp8jpaz5069y0h9ttty8srcu");
		} else {
			splitRulesEmpresa.setRecipientId("re_ckraudxgi00zm0p9tsbnszhmo");
		}
		splitRulesEmpresa.setPercentage(
				tipoCobranca == "BOLETO" ? propNegocio.findValueIntegerById(PropEnum.PERCENTUAL_BOLETO_EMPRESA, 0)
						: propNegocio.findValueIntegerById(PropEnum.PERCENTUAL_PIX_EMPRESA, 0));
		rules.add(splitRulesEmpresa);
		return rules;
	}

	private Agenda incluirAgenda(Pessoa paciente, Profissional profissional, JSONObject jsonRetornoToken,
			Transaction tx) {
		Date dataAgenda;
		Agenda agenda = null;
		try {
			dataAgenda = Uteis.converterDataHoraString(jsonRetornoToken.get("dataAtendimento").toString(),
					jsonRetornoToken.get("horarioPossivelAtendimento").toString());

			agenda = new Agenda(paciente, profissional, dataAgenda,
					jsonRetornoToken.get("tipoAtendimento").toString().equals("online")
							|| jsonRetornoToken.get("tipoAtendimento").toString().equals("") ? Boolean.TRUE
									: Boolean.FALSE,
					jsonRetornoToken.get("tipoAtendimento").toString().equals("presencial") ? Boolean.TRUE
							: Boolean.FALSE,
					"", StatusEnum.AG_PAGAMENTO, null, "");
			agenda.setTokenTransacaoPagamentoConsulta(jsonRetornoToken.get("token").toString());
			agenda.setIdTransacao(tx.getId());
			if (jsonRetornoToken.get("payment_method").toString().toUpperCase().equals("BOLETO") ||
					jsonRetornoToken.get("payment_method").toString().toUpperCase().equals("PIX") ) { 
				agenda.setTipoPagamento(jsonRetornoToken.get("payment_method").toString().toUpperCase());
				agenda.setUrlPagamento(jsonRetornoToken.get("payment_method").equals("boleto") ? tx.getBoletoUrl() : "https://api.qrserver.com/v1/create-qr-code/?data="+tx.getPixQRCode());
			}
			if (agenda.getOnline()) {
				wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
			}
			agenda = (Agenda) hibernateUtil.save(agenda);
			getListAgendasEnviarEmail().add(agenda);
			if (!jsonRetornoToken.get("pacote").toString().equals("")) {
				gerarAgendaPacote(dataAgenda, jsonRetornoToken, paciente, profissional, tx);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agenda;

	}

	public ResponsePaymentTransaction controleTransacao(JSONObject jsonRetornoToken, Pessoa paciente) throws Exception {
		// Buscsa Dados de pagamento e Profissional

		PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
		String situacaoPagarme = propNegocio.findValueById(PropEnum.SITUACAO_PAGARME);
		Transaction tx = new Transaction().find(jsonRetornoToken.get("token").toString());
		Profissional profissional = profissionalNegocio
				.consultarPorId(Long.parseLong(jsonRetornoToken.get("idProfissional").toString()));
		// Capturar Transação
		Thread.sleep(5000);
		System.out.println("Pasou no Sleep");
		Transaction capturarTransacao = new Transaction().find(tx.getId());
		capturarTransacao.setSplitRules(getSplitedRules(profissional, situacaoPagarme,
				jsonRetornoToken.get("payment_method").toString().toUpperCase()));
		tx = capturarTransacao.capture(tx.getAmount());
		String boletoUrl = tx.getBoletoUrl();
		String pixQrCode = tx.getPixQRCode();
		String statusBoleto = tx.getStatus().toString();
		ResponsePaymentTransaction paymentRes = new ResponsePaymentTransaction(
				jsonRetornoToken.get("payment_method").toString(), boletoUrl, pixQrCode);
		// Registrar Transação
		Agenda agenda = incluirAgenda(paciente, profissional, jsonRetornoToken, tx);
		PagamentoPagarMe pagamentoPagarMe = new PagamentoPagarMe();
		pagamentoPagarMe = pagamentoPagarMe.pagamento(capturarTransacao, agenda, null,jsonRetornoToken.get("payment_method").toString().toUpperCase(),jsonRetornoToken.get("payment_method").equals("boleto") ? boletoUrl : "https://api.qrserver.com/v1/create-qr-code/?data="+pixQrCode);
		pagarMeNegocio.salvarPagamentoPagarMe(pagamentoPagarMe);
		envioEmailConsulta.enviarEmailBoletoAgenda(agenda,paciente,jsonRetornoToken.get("payment_method").equals("boleto") ? boletoUrl : pixQrCode,jsonRetornoToken.get("payment_method").toString());
		return paymentRes;
	}

	@Transactional
	public ResponsePaymentTransaction incluirAgendaPaciente(JSONObject jsonRetornoToken, Pessoa paciente)
			throws Exception {

		try {
			if (jsonRetornoToken.get("payment_method").equals("boleto")
					|| jsonRetornoToken.get("payment_method").equals("pix")) {
				ResponsePaymentTransaction responsePayment = controleTransacao(jsonRetornoToken, paciente);
				return responsePayment;
			} else {

				getListAgendasEnviarEmail().clear();
				PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
				String situacaoPagarme = propNegocio.findValueById(PropEnum.SITUACAO_PAGARME);
				Transaction tx = new Transaction().find(jsonRetornoToken.get("token").toString());

				Profissional profissional = profissionalNegocio
						.consultarPorId(Long.parseLong(jsonRetornoToken.get("idProfissional").toString()));
				Date dataAgenda = Uteis.converterDataHoraString(jsonRetornoToken.get("dataAtendimento").toString(),
						jsonRetornoToken.get("horarioPossivelAtendimento").toString());
				Agenda agenda = new Agenda(paciente, profissional, dataAgenda,
						jsonRetornoToken.get("tipoAtendimento").toString().equals("online")
								|| jsonRetornoToken.get("tipoAtendimento").toString().equals("") ? Boolean.TRUE
										: Boolean.FALSE,
						jsonRetornoToken.get("tipoAtendimento").toString().equals("presencial") ? Boolean.TRUE
								: Boolean.FALSE,
						"", StatusEnum.PENDENTE, null, "");
				agenda.setTokenTransacaoPagamentoConsulta(jsonRetornoToken.get("token").toString());
				agenda.setIdTransacao(tx.getId());
				if (agenda.getOnline()) {
					wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
				}
				agenda = (Agenda) hibernateUtil.save(agenda);
				getListAgendasEnviarEmail().add(agenda);
				if (!jsonRetornoToken.get("pacote").toString().equals("")) {
					gerarAgendaPacote(dataAgenda, jsonRetornoToken, paciente, profissional, tx);
				}

				Transaction capturarTransacao = new Transaction().find(tx.getId());
				Collection<SplitRule> rules = new ArrayList<>();
				SplitRule splitRulesProfissional = new SplitRule();
				splitRulesProfissional.setRecipientId(profissional.getIdRecebedor());
				splitRulesProfissional.setPercentage(90);
				splitRulesProfissional.setChargeProcessingFee(Boolean.FALSE);
				rules.add(splitRulesProfissional);
				SplitRule splitRulesEmpresa = new SplitRule();
				if (situacaoPagarme.equals("PRODUCAO")) {
					splitRulesEmpresa.setRecipientId("re_ckp8jpaz5069y0h9ttty8srcu");
				} else {
					splitRulesEmpresa.setRecipientId("re_ckraudxgi00zm0p9tsbnszhmo");
				}
				splitRulesEmpresa.setPercentage(10);
				rules.add(splitRulesEmpresa);
				capturarTransacao.setSplitRules(rules);
				// capturarTransacao.capture(tx.getAmount());
				String boletoUrl = tx.getBoletoUrl();
				String pixQrCode = tx.getPixQRCode();
				
				PagamentoPagarMe pagamentoPagarMe = new PagamentoPagarMe();
				pagamentoPagarMe = pagamentoPagarMe.pagamento(capturarTransacao, agenda, null,jsonRetornoToken.get("payment_method").toString(),jsonRetornoToken.get("payment_method").equals("boleto") ? boletoUrl : pixQrCode);
				pagarMeNegocio.salvarPagamentoPagarMe(pagamentoPagarMe);
				getListAgendasEnviarEmail().stream().forEach(agendaEnviar -> {
					try {
						envioEmailConsulta.enviar(agendaEnviar);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				ResponsePaymentTransaction paymentRes = new ResponsePaymentTransaction(
						jsonRetornoToken.get("payment_method").toString(), null, null);
				return paymentRes;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResponsePaymentTransaction();
	}

	@Transactional
	public void gerarAgendaPacote(Date dataAgendaInicial, JSONObject jsonRetornoToken, Pessoa paciente,
			Profissional profissional, Transaction tx) {
		try {
			List<Date> listDatasPacote = new ArrayList<Date>();
			if (jsonRetornoToken.get("pacote").toString().contains("pacote2")) {
				listDatasPacote.addAll(listaDataPacote(dataAgendaInicial, 1));
			} else if (jsonRetornoToken.get("pacote").toString().contains("pacote3")) {
				listDatasPacote.addAll(listaDataPacote(dataAgendaInicial, 2));
			} else if (jsonRetornoToken.get("pacote").toString().contains("pacote4")) {
				listDatasPacote.addAll(listaDataPacote(dataAgendaInicial, 3));
			}
			listDatasPacote.stream().forEach(data -> gerarAgenda(data, jsonRetornoToken, paciente, profissional, tx));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public List<Date> listaDataPacote(Date dataAgendaInicial, Integer quantidadeDataPacote) {
		List<Date> listDataPacote = new ArrayList<Date>();
		Integer quantidadeDataGerada = 0;
		Date dataTemp = dataAgendaInicial;
		while (quantidadeDataGerada < quantidadeDataPacote) {
			dataTemp = Uteis.acrescentar(dataTemp, Calendar.DATE, 7);
			listDataPacote.add(dataTemp);
			quantidadeDataGerada++;
		}
		return listDataPacote;
	}

	public void gerarAgenda(Date dataAgenda, JSONObject jsonRetornoToken, Pessoa paciente, Profissional profissional,
			Transaction tx) {
		try {
			String payment_method = jsonRetornoToken.get("payment_method").toString().toUpperCase();
			Agenda agenda = new Agenda(paciente, profissional, dataAgenda,
					jsonRetornoToken.get("tipoAtendimento").toString().equals("online")
							|| jsonRetornoToken.get("tipoAtendimento").toString().equals("") ? Boolean.TRUE
									: Boolean.FALSE,
					jsonRetornoToken.get("tipoAtendimento").toString().equals("presencial") ? Boolean.TRUE
							: Boolean.FALSE,
					"", payment_method.equals("BOLETO") || payment_method.equals("PIX") ? StatusEnum.AG_PAGAMENTO :  StatusEnum.PENDENTE, null, "");
			agenda.setTokenTransacaoPagamentoConsulta(jsonRetornoToken.get("token").toString());
			agenda.setIdTransacao(tx.getId());
			if (agenda.getOnline()) {
				wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
			}
			agenda = (Agenda) hibernateUtil.save(agenda);
			getListAgendasEnviarEmail().add(agenda);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public List<Agenda> consultarAgenda(Pessoa pessoaAgenda) {

		List<Agenda> listAgendas = new ArrayList<Agenda>();

		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		if (pessoaAgenda.getCliente()) {
			dc.createAlias("paciente", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));
		} else {
			dc.createAlias("profissional.pessoa", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));
		}
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}

	@Transactional
	public Agenda alterarAgenda(Long idAgenda, String situacao, Pessoa pessoaSessao) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("id", idAgenda));
		Agenda agenda = (Agenda) hibernateUtil.consultarUniqueResult(dc);
		agenda.setStatus(StatusEnum.valueOf(situacao));
		hibernateUtil.update(agenda);
		envioEmailConsulta.enviarEmailAlteracaoSituacaoAgenda(agenda, pessoaSessao);
		return agenda;
	}


	public ValueList buscar(VLHForm vlhForm, ValueListInfo valueListInfo, Usuario usuarioSessao, Pessoa pessoaAgenda) {
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.createAlias("paciente", "pac");
		dc.createAlias("profissional", "prof");
		dc.createAlias("prof.pessoa", "pessoa");
		if (!Uteis.ehNuloOuVazio(vlhForm.getPalavraChave())) {
			Disjunction disjunction = Restrictions.disjunction();
			List<String> condicoes = PalavrasChaveCondicoes.nova().comPalavrasChave(vlhForm.getPalavraChave()).gerar();
			for (String condicao : condicoes) {
				disjunction.add(Restrictions.ilike("pac.nome", condicao));
				disjunction.add(Restrictions.ilike("pessoa.nome", condicao));
			}
			disjunction.add(Restrictions.eq("status", StatusEnum.fromStringConsulta(vlhForm.getPalavraChave())));

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data;
			try {
				data = formato.parse(vlhForm.getPalavraChave());
				disjunction.add(Restrictions.between("dataHoraAgendamento", data, Uteis.highDateTime(data)));
			} catch (ParseException e) {
			}
			Long id = Uteis.converterLong(vlhForm.getPalavraChave());
			if (id != null) {
				disjunction.add(Restrictions.eq("id", id));
			}
			dc.add(disjunction);
		}

		StatusEnum statusEnum = StatusEnum.fromString(vlhForm.getStatus());

		if (statusEnum != null) {
			dc.add(Restrictions.eq("status", statusEnum));
		}

		if (pessoaAgenda != null && pessoaAgenda.getCliente()) {
			dc.add(Restrictions.eq("pac.id", pessoaAgenda.getId()));
		} else if (pessoaAgenda != null && pessoaAgenda.getPsicologo()) {
			dc.add(Restrictions.eq("prof.pessoa.id", pessoaAgenda.getId()));
		}

		ValueList notificacaos = hibernateUtil.consultarValueList(dc, org.hibernate.criterion.Order.desc("dataHoraAgendamento"),
				valueListInfo);

		return notificacaos;

	}

	public List<Agenda> consultarAgendaDia(Pessoa pessoaAgenda) {

		List<Agenda> listAgendas = new ArrayList<Agenda>();

		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		if (pessoaAgenda.getCliente()) {
			dc.createAlias("paciente", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));
		} else {
			dc.createAlias("profissional.pessoa", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));
		}
		dc.add(Restrictions.eq("status", StatusEnum.CONFIRMADO));
		dc.add(Restrictions.eq("online", Boolean.TRUE));
		dc.add(Restrictions.between("dataHoraAgendamento", Uteis.inicioDia(new Date()), Uteis.fimDia(new Date())));
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}

	public Agenda consultarAgendaId(Long idAgenda) {
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("id", idAgenda));
		return (Agenda) hibernateUtil.consultarUniqueResult(dc);
	}
	public List<Agenda> consultarAgendaIdTransacao(Integer idTransacao) {
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("idTransacao", idTransacao));
		return (List<Agenda>) hibernateUtil.buscar(dc,1,10);
	}
	
	public List<Agenda> atualizarAgendaPorIdTransacao(Integer idTransacao,String situacao,Pessoa pessoaSessao) {
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("idTransacao", idTransacao));
		List<Agenda> agendas = hibernateUtil.buscar(dc,1,10);
		for (Agenda item : agendas ) {
			item.setStatus(StatusEnum.valueOf(situacao.toUpperCase()));
			hibernateUtil.update(item);
			envioEmailConsulta.enviarEmailAlteracaoSituacaoAgenda(item, pessoaSessao);
		}
		return agendas;
	}
	@Transactional
	public Agenda avaliarAtendimento(Agenda agenda) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update agenda set avaliacaoAtendimentoObservacao = '")
				.append(agenda.getAvaliacaoAtendimentoObservacao()).append("',avaliacaoAtendimentoNota =")
				.append(agenda.getAvaliacaoAtendimentoNota());
		sql.append(" where id = ").append(agenda.getId());
		Integer codigoAgenda = hibernateUtil.updateSQL(sql.toString());
		agenda = consultarAgendaId(Long.parseLong(codigoAgenda.toString()));
		return agenda;
	}

	public List<Agenda> consultarAgendaDiaProfissional(Profissional profissional, Date dataConsulta) {
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", profissional.getId()));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(
				Restrictions.between("dataHoraAgendamento", Uteis.inicioDia(dataConsulta), Uteis.fimDia(dataConsulta)));
		dc.add(disjunction);
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}

	@Transactional
	public Agenda incluirReagendamentoPaciente(JSONObject jsonRetornoToken, Pessoa paciente) throws Exception {

		try {
			Profissional profissional = profissionalNegocio.consultarPorId(Long.parseLong(jsonRetornoToken.get("idProfissional").toString()));
			Date dataAgenda = Uteis.converterDataHoraString(jsonRetornoToken.get("dataAtendimento").toString(),jsonRetornoToken.get("horarioPossivelAtendimento").toString());
			Agenda agendaReagendada = agendaNegocio.consultarAgendaId(Long.parseLong(jsonRetornoToken.get("idAgendaReagendada").toString()));
			Agenda agenda = new Agenda(paciente, profissional, dataAgenda,jsonRetornoToken.get("tipoAtendimento").toString().equals("online") || jsonRetornoToken.get("tipoAtendimento").toString().equals("") ? Boolean.TRUE : Boolean.FALSE, jsonRetornoToken.get("tipoAtendimento").toString().equals("presencial") ? Boolean.TRUE	: Boolean.FALSE,
					"", StatusEnum.PENDENTE, null, "");
			if (agenda.getOnline()) {
				wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
			}
			agenda = (Agenda) hibernateUtil.save(agenda);
			agendaReagendada.setIdAgendaReagendamento(agenda.getId());
			agendaReagendada.setDataHoraAgendamento(dataAgenda);
			agendaReagendada.setReagendamentoConcluido(Boolean.TRUE);
			hibernateUtil.update(agendaReagendada);
			agendaNegocio.alterarAgenda(agendaReagendada.getId(), StatusEnum.REMARCADO.toString(), paciente);
			envioEmailConsulta.enviar(agendaReagendada);
			return agendaReagendada;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Agenda();
	}

	public List<Agenda> consultarAgendaDia(Date dataConsulta) {
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("status", StatusEnum.CONFIRMADO));
		dc.add(Restrictions.isNull("enviadoAlerta"));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.between("dataHoraAgendamento", dataConsulta, Uteis.fimDia(dataConsulta)));
		dc.add(disjunction);
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}

	@Transactional
	public Agenda envioAlertaAgenda(Agenda agenda) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update agenda set dataHoraEnviadoAlerta = '").append(new Date()).append("',enviadoAlerta = true");
		sql.append(" where id = ").append(agenda.getId());
		Integer codigoAgenda = hibernateUtil.updateSQL(sql.toString());
		agenda = consultarAgendaId(Long.parseLong(codigoAgenda.toString()));
		return agenda;
	}

	public List<Agenda> consultarAgendaDiaNaoAtendida(Date dataConsulta) {
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("status", StatusEnum.CONFIRMADO));
		dc.add(Restrictions.eq("online", Boolean.TRUE));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(
				Restrictions.between("dataHoraAgendamento", Uteis.inicioDia(dataConsulta), Uteis.fimDia(dataConsulta)));
		dc.add(disjunction);
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}

	public ValueList relResumoAtendimento(VLHForm vlhForm, ValueListInfo valueListInfo) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.createAlias("paciente", "pac");
		dc.add(Restrictions.eq("pac.empresaImportacao", vlhForm.getPalavraChave()));
		if (vlhForm.getStatusAgenda() != null) {
			dc.add(Restrictions.eq("status", vlhForm.getStatusAgenda()));
		}
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.between("dataHoraAgendamento",
				Uteis.converterHoraEmDate(vlhForm.getDataInicio(), "yyyy-MM-dd"),
				Uteis.converterHoraEmDate(vlhForm.getDataFim(), "yyyy-MM-dd")));
		dc.add(disjunction);
		ValueList notificacaos = hibernateUtil.consultarValueList(dc, org.hibernate.criterion.Order.asc("id"),
				valueListInfo);

		return notificacaos;

	}

	private void validarDadosConsulta(VLHForm vlhForm) throws Exception {
		if (vlhForm.getPalavraChave() == null) {
			throw new ValidacaoException("Necessário informar a empresa.");
		} else if (vlhForm.getDataInicio() == null) {
			throw new ValidacaoException("Necessário informar data inicio.");
		} else if (vlhForm.getDataFim() == null) {
			throw new ValidacaoException("Necessário informar data fim.");
		}

	}

}
