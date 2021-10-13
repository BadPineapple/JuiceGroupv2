package ilion.vitazure.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import ilion.email.negocio.EmailSender;
import ilion.me.pagar.model.PagarMe;
import ilion.me.pagar.model.PagarMeException;
import ilion.me.pagar.model.SplitRule;
import ilion.me.pagar.model.Transaction;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.busca.PalavrasChaveCondicoes;
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
	
	
	@Transactional
	public Agenda incluirAgendaPaciente(JSONObject jsonRetornoToken , Pessoa paciente) throws Exception {
		
		try {
			PagarMe.init(propNegocio.findValueById(PropEnum.PAGAR_ME_API_KEY));
			Transaction tx = new Transaction().find(jsonRetornoToken.get("token").toString());
			Profissional profissional = profissionalNegocio.consultarPorId(Long.parseLong(jsonRetornoToken.get("idProfissional").toString()));
			Date dataAgenda = Uteis.converterDataHoraString(jsonRetornoToken.get("dataAtendimento").toString(), jsonRetornoToken.get("horarioPossivelAtendimento").toString());
			Agenda agenda = new Agenda(paciente, profissional, dataAgenda, jsonRetornoToken.get("tipoAtendimento").toString().equals("online") || jsonRetornoToken.get("tipoAtendimento").toString().equals("") ? Boolean.TRUE : Boolean.FALSE, jsonRetornoToken.get("tipoAtendimento").toString().equals("presencial")  ? Boolean.TRUE : Boolean.FALSE, "", StatusEnum.PENDENTE, null , "");
			agenda.setTokenTransacaoPagamentoConsulta(jsonRetornoToken.get("token").toString());
			agenda.setIdTransacao(tx.getId());
			if (agenda.getOnline()) {
				wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
			}
			agenda = (Agenda) hibernateUtil.save(agenda);
			
			Transaction capturarTransacao = new Transaction().find(tx.getId());
			Collection<SplitRule> rules = new ArrayList<>();
				SplitRule splitRules = new SplitRule();
				splitRules.setRecipientId(profissional.getIdRecebedor());
				splitRules.setPercentage(89);
	            rules.add(splitRules);
	        capturarTransacao.setSplitRules(rules);
			capturarTransacao.capture(tx.getAmount());
			PagamentoPagarMe pagamentoPagarMe = new PagamentoPagarMe();
			pagamentoPagarMe = pagamentoPagarMe.pagamento(capturarTransacao, agenda, null);
			pagarMeNegocio.salvarPagamentoPagarMe(pagamentoPagarMe);
			
			envioEmailConsulta.enviar(agenda);
			
			return agenda;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Agenda();
	}
	
	public List<Agenda> consultarAgenda(Pessoa pessoaAgenda){
		
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		if (pessoaAgenda.getCliente()) {
			dc.createAlias("paciente", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));
		}else {
			dc.createAlias("profissional.pessoa", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));	
		}
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}
	
	@Transactional
	public Agenda alterarAgenda(Long idAgenda , String situacao , Pessoa pessoaSessao) throws Exception{
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("id", idAgenda));
		Agenda agenda = (Agenda) hibernateUtil.consultarUniqueResult(dc);
		agenda.setStatus(StatusEnum.valueOf(situacao));
		hibernateUtil.update(agenda);
		envioEmailConsulta.enviarEmailAlteracaoSituacaoAgenda(agenda , pessoaSessao);
		return agenda;
	}
	
	public ValueList buscar(VLHForm vlhForm, ValueListInfo valueListInfo , Usuario usuarioSessao , Pessoa pessoaAgenda) {

		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.createAlias("paciente", "pac");
		dc.createAlias("profissional", "prof");
		dc.createAlias("prof.pessoa", "pessoa");
		if (!Uteis.ehNuloOuVazio(vlhForm.getPalavraChave())) {
			Disjunction disjunction = Restrictions.disjunction();
			List<String> condicoes = PalavrasChaveCondicoes.nova().comPalavrasChave(vlhForm.getPalavraChave()).gerar();
			for (String condicao : condicoes) {
				disjunction.add( Restrictions.ilike("pac.nome", condicao));
				disjunction.add( Restrictions.ilike("pessoa.nome", condicao));
			}
			disjunction.add( Restrictions.eq("status", StatusEnum.fromStringConsulta(vlhForm.getPalavraChave())));
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			Date data;
			try {
				data = formato.parse(vlhForm.getPalavraChave());
				disjunction.add( Restrictions.between("dataHoraAgendamento", data , Uteis.highDateTime(data)));
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
		}else if(pessoaAgenda != null && pessoaAgenda.getPsicologo()) {
			dc.add(Restrictions.eq("prof.pessoa.id", pessoaAgenda.getId()));	
		}
		
		ValueList notificacaos = hibernateUtil.consultarValueList(dc, org.hibernate.criterion.Order.desc("id"), valueListInfo);

		return notificacaos;

	}
	
	public List<Agenda> consultarAgendaDia(Pessoa pessoaAgenda){
				
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		if (pessoaAgenda.getCliente()) {
			dc.createAlias("paciente", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));
		}else {
			dc.createAlias("profissional.pessoa", "p");
			dc.add(Restrictions.eq("p.id", pessoaAgenda.getId()));	
		}
		dc.add(Restrictions.eq("status", StatusEnum.CONFIRMADO));
		dc.add(Restrictions.eq("online", Boolean.TRUE));
		dc.add( Restrictions.between("dataHoraAgendamento", Uteis.inicioDia(new Date()) , Uteis.fimDia(new Date())));
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}
	
	public Agenda consultarAgendaId(Long idAgenda){		
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("id", idAgenda));
		return (Agenda) hibernateUtil.consultarUniqueResult(dc);
	}
	
	@Transactional
	public Agenda avaliarAtendimento(Agenda agenda){
		StringBuilder sql = new StringBuilder();
		sql.append(" update agenda set avaliacaoAtendimentoObservacao = '").append(agenda.getAvaliacaoAtendimentoObservacao()).append("',avaliacaoAtendimentoNota =").append(agenda.getAvaliacaoAtendimentoNota());
		sql.append(" where id = ").append(agenda.getId());
		Integer codigoAgenda = hibernateUtil.updateSQL(sql.toString());
		agenda = consultarAgendaId(Long.parseLong(codigoAgenda.toString()));
		return agenda;
	}
	
	public List<Agenda> consultarAgendaDiaProfissional(Profissional profissional , Date dataConsulta){
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", profissional.getId()));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add( Restrictions.between("dataHoraAgendamento", Uteis.inicioDia(dataConsulta) , Uteis.fimDia(dataConsulta)));
		dc.add(disjunction);
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}
	
	@Transactional
	public Agenda incluirReagendamentoPaciente(JSONObject jsonRetornoToken , Pessoa paciente) throws Exception {
		
		try {
			Profissional profissional = profissionalNegocio.consultarPorId(Long.parseLong(jsonRetornoToken.get("idProfissional").toString()));
			Date dataAgenda = Uteis.converterDataHoraString(jsonRetornoToken.get("dataAtendimento").toString(), jsonRetornoToken.get("horarioPossivelAtendimento").toString());
			Agenda agenda = new Agenda(paciente, profissional, dataAgenda, jsonRetornoToken.get("tipoAtendimento").toString().equals("online") || jsonRetornoToken.get("tipoAtendimento").toString().equals("") ? Boolean.TRUE : Boolean.FALSE, jsonRetornoToken.get("tipoAtendimento").toString().equals("presencial")  ? Boolean.TRUE : Boolean.FALSE, "", StatusEnum.PENDENTE, null , "");
			if (agenda.getOnline()) {
				wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
			}
			agenda = (Agenda) hibernateUtil.save(agenda);
			Agenda agendaReagendada = agendaNegocio.consultarAgendaId(Long.parseLong(jsonRetornoToken.get("idAgendaReagendada").toString()));
			agendaReagendada.setIdAgendaReagendamento(agenda.getId());
			agendaReagendada.setReagendamentoConcluido(Boolean.TRUE);
			hibernateUtil.update(agendaReagendada);
			agendaNegocio.alterarAgenda(agendaReagendada.getId(), StatusEnum.REMARCADO.toString(), paciente);
			envioEmailConsulta.enviar(agenda);
			return agenda;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Agenda();
	}
	
	public List<Agenda> consultarAgendaDia(Date dataConsulta){
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("status", StatusEnum.CONFIRMADO));
		dc.add(Restrictions.isNull("enviadoAlerta"));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add( Restrictions.between("dataHoraAgendamento", dataConsulta , Uteis.fimDia(dataConsulta)));
		dc.add(disjunction);
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}
	
	@Transactional
	public Agenda envioAlertaAgenda(Agenda agenda){
		StringBuilder sql = new StringBuilder();
		sql.append(" update agenda set dataHoraEnviadoAlerta = '").append(new Date()).append("',enviadoAlerta = true");
		sql.append(" where id = ").append(agenda.getId());
		Integer codigoAgenda = hibernateUtil.updateSQL(sql.toString());
		agenda = consultarAgendaId(Long.parseLong(codigoAgenda.toString()));
		return agenda;
	}
	
	public List<Agenda> consultarAgendaDiaNaoAtendida(Date dataConsulta){
		List<Agenda> listAgendas = new ArrayList<Agenda>();
		DetachedCriteria dc = DetachedCriteria.forClass(Agenda.class);
		dc.add(Restrictions.eq("status", StatusEnum.CONFIRMADO));
		dc.add(Restrictions.eq("online", Boolean.TRUE));
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add( Restrictions.between("dataHoraAgendamento", Uteis.inicioDia(dataConsulta) , Uteis.fimDia(dataConsulta)));
		dc.add(disjunction);
		dc.addOrder(Order.asc("dataHoraAgendamento"));
		listAgendas = (List<Agenda>) hibernateUtil.list(dc);
		return listAgendas;
	}
	
}
