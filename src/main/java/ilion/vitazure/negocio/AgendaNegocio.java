package ilion.vitazure.negocio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.Uteis;
import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;

@Service
public class AgendaNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private ProfissionalNegocio profissionalNegocio;
	
	@Autowired
	private WherebyApi wherebyApi;
	
	
	@Transactional
	public Agenda incluirAgendaPaciente(String idProfissional , String horarioPossivelAtendimento ,String dataAtendimento,  String tipoAtendimento , Pessoa paciente) {
		
		try {
		Profissional profissional = profissionalNegocio.consultarPorId(Long.parseLong(idProfissional));
		Date dataAgenda = Uteis.converterDataHoraString(dataAtendimento, horarioPossivelAtendimento);
		Agenda agenda = new Agenda(paciente, profissional, dataAgenda, tipoAtendimento.equals("online") ? Boolean.TRUE : Boolean.FALSE, tipoAtendimento.equals("presencial") ? Boolean.TRUE : Boolean.FALSE, "", StatusEnum.ANDAMENTO, null , "");
		if (agenda.getOnline()) {
			wherebyApi.gerarLinkAtendimentoOnline(profissional, agenda);
		}
		agenda = (Agenda) hibernateUtil.save(agenda);
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
	
}
