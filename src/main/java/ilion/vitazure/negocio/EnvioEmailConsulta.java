package ilion.vitazure.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailNegocio;
import ilion.util.Uteis;
import ilion.vitazure.model.Agenda;


@Service
public class EnvioEmailConsulta {

	
	@Autowired
	private EmailNegocio emailNegocio;
	
	
	@Autowired
	private PropNegocio propNegocio;
	
	public void enviar(Agenda agenda) throws Exception {
		enviarPaciente(agenda);
		enviarProfissional(agenda);
	}
	
	
	public void enviarPaciente(Agenda agenda) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<b> Agendamento Consulta</b><br/><br/>");
		sb.append("<b>Nome: </b>").append(agenda.getPaciente().getNome()).append("<br/><br/>");
		sb.append("<b>E-mail: </b>").append(agenda.getPaciente().getEmail()).append("<br/><br/>");
		sb.append("<b>Celular: </b>").append(agenda.getPaciente().getCelular()).append("<br/><br/>");
		sb.append("<b>Profissional: </b>").append(agenda.getProfissional().getPessoa().getNome()).append("<br/><br/>");
		sb.append("<b>Horario: </b>").append(agenda.getDataHoraApresentar()).append("<br/><br/>");
		
		String nomeEmpresa = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		
		
		String emailsContato = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);
		
		List<String> emails = Uteis.formarListaComString(emailsContato, ";");
		
		for (String email : emails) {
			
			Email e = new Email();
			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(agenda.getPaciente().getEmail());
			e.setReplyToEmail(agenda.getProfissional().getPessoa().getEmail());
			e.setReplyToName(agenda.getPaciente().getNome());
			e.setSubject("Agendamento Consulta");
			e.setMessage(sb.toString());

			emailNegocio.adicionarEmail(e);
			
		}
		
	}
	
public void enviarProfissional(Agenda agenda) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<b> Agendamento Consulta</b><br/><br/>");
		sb.append("<b>Nome: </b>").append(agenda.getPaciente().getNome()).append("<br/><br/>");
		sb.append("<b>E-mail: </b>").append(agenda.getPaciente().getEmail()).append("<br/><br/>");
		sb.append("<b>Celular: </b>").append(agenda.getPaciente().getCelular()).append("<br/><br/>");
		sb.append("<b>Horario: </b>").append(agenda.getDataHoraApresentar()).append("<br/><br/>");
		
		String nomeEmpresa = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		
		
		String emailsContato = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);
		
		List<String> emails = Uteis.formarListaComString(emailsContato, ";");
		
		for (String email : emails) {
			
			Email e = new Email();
			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(agenda.getProfissional().getPessoa().getEmail());
			e.setReplyToEmail(agenda.getProfissional().getPessoa().getEmail());
			e.setReplyToName(agenda.getPaciente().getNome());
			e.setSubject("Agendamento Consulta");
			e.setMessage(sb.toString());

			emailNegocio.adicionarEmail(e);
			
		}
		
	}
	
	
}
