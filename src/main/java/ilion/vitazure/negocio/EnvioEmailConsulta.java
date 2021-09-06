package ilion.vitazure.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailNegocio;
import ilion.util.Uteis;
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.Pessoa;


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
	
	public void enviarEmailAlteracaoSituacaoAgenda(Agenda agenda ,Pessoa pessoaSessao) {
		StringBuffer htmlEmail = new StringBuffer();
		if(agenda.getStatus().equals(StatusEnum.CONFIRMADO)) {
			htmlEmail.append(emailPadrao(corpoHtmlEmailConfirmacao()));
			enviarEmailAlteracaoSituacaoAgendaCliente(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getPaciente().getNome()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()), "Confirmação atendimento");
		}else if(agenda.getStatus().equals(StatusEnum.REMARCADO) && pessoaSessao.getCliente()) {
			String urlEntrar = propNegocio.findValueById(PropEnum.URL).concat("/entrar");
			htmlEmail.append(emailPadrao(corpoHtmlEmailReagendamentoCliente()));
			enviarEmailAlteracaoSituacaoAgendaProfissional(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getProfissional().getPessoa().getNome()), "Reagendamento atendimento solicitação cliente");
		
		}else if(agenda.getStatus().equals(StatusEnum.REMARCADO) && pessoaSessao.getPsicologo()) {
			String urlEntrar = propNegocio.findValueById(PropEnum.URL).concat("/entrar");
			htmlEmail.append(emailPadrao(corpoHtmlEmailReagendamentoProfissional()));
			enviarEmailAlteracaoSituacaoAgendaCliente(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getPaciente().getNome()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()).replaceAll("#urlEntrar#", urlEntrar), "Reagendamento atendimento solicitação profissional");
		}else if(agenda.getStatus().equals(StatusEnum.CANCELADO)) {
			
		}
		
	}
	
	
	private void enviarEmailAlteracaoSituacaoAgendaCliente(Agenda agenda , String htmlEmail , String assuntoEmail) {
		
		 String nomeEmpresa = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
			Email e = new Email();
			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(agenda.getPaciente().getEmail());
			e.setReplyToName(agenda.getPaciente().getNome());
			e.setSubject(assuntoEmail);
			e.setMessage(htmlEmail.toString());
			emailNegocio.adicionarEmail(e);
		
	}
	
	private void enviarEmailAlteracaoSituacaoAgendaProfissional(Agenda agenda , String htmlEmail , String assuntoEmail) {
		
		String nomeEmpresa = propNegocio.findValueById(PropEnum.NOME_EMPRESA);
			Email e = new Email();
			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(agenda.getProfissional().getPessoa().getEmail());
			e.setReplyToName(agenda.getProfissional().getPessoa().getNome());
			e.setSubject(assuntoEmail);
			e.setMessage(htmlEmail.toString());
			emailNegocio.adicionarEmail(e);
	}
	
	
	private  String corpoHtmlEmailConfirmacao() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append("	<p>Seu agendamento foi confirmado com sucesso!</p>");
		corpoHtmlEmail.append("<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("			  <tr>");
		corpoHtmlEmail.append("			    <td valign=\"top\" bgcolor=\"#F3F3F3\">");
		corpoHtmlEmail.append("				  <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"3\" cellspacing=\"5\" bgcolor=\"#F3F3F3\">");
		corpoHtmlEmail.append("				        <tr> ");
		corpoHtmlEmail.append("				          <td><table width=\"100%\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td colspan=\"2\" align=\"right\">&nbsp;</td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Data:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#dataAgendaEmail#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Horário:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#horaAgendaEmail#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong style=\"margin-right: 3px;\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Profissional:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#profissionalAtendimento#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td colspan=\"2\" align=\"right\">&nbsp;</td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("						</table>");
		corpoHtmlEmail.append("						</td>");
		corpoHtmlEmail.append("						</tr>");
		corpoHtmlEmail.append("					</table>");
		corpoHtmlEmail.append("			    </td>");
		corpoHtmlEmail.append("		   </tr>");
		corpoHtmlEmail.append("		</table>");
		corpoHtmlEmail.append("</div>");
		return corpoHtmlEmail.toString();
	}
	
	private  String corpoHtmlEmailReagendamentoProfissional() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append("	<p>Olá!</p>");
		corpoHtmlEmail.append("	<p>Infelizmente não poderemos realizar sua consulta na data agendada.</p>");
		corpoHtmlEmail.append("	<p>Por gentileza, solicitamos que, acesse nossa agenda para remarcar sua consulta.</p>");
		corpoHtmlEmail.append(" <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("	<tr> ");
		corpoHtmlEmail.append("	   <td align=\"center\" bgcolor=\"#D6E4E9\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" color=\"#ff1744\"><strong>Consulta Cancelada</strong></font></td>");
		corpoHtmlEmail.append("	</tr>");
		corpoHtmlEmail.append("			  <tr>");
		corpoHtmlEmail.append("			    <td valign=\"top\" bgcolor=\"#F3F3F3\">");
		corpoHtmlEmail.append("				  <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"3\" cellspacing=\"5\" bgcolor=\"#F3F3F3\">");
		corpoHtmlEmail.append("				        <tr> ");
		corpoHtmlEmail.append("				          <td><table width=\"100%\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\" bgcolor=\"#FFFFFF\">");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td colspan=\"2\" align=\"right\">&nbsp;</td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #ff1744;\">Data:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" color=\"#ff1744\">#dataAgendaEmail#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #ff1744;\">Horário:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" color=\"#ff1744\">#horaAgendaEmail#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong style=\"margin-right: 3px;\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #ff1744;\">Profissional:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" color=\"#ff1744\">#profissionalAtendimento#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td colspan=\"2\" align=\"right\">&nbsp;</td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("						</table>");
		corpoHtmlEmail.append("						</td>");
		corpoHtmlEmail.append("						</tr>");
		corpoHtmlEmail.append("					</table>");
		corpoHtmlEmail.append("			    </td>");
		corpoHtmlEmail.append("		   </tr>");
		corpoHtmlEmail.append("		</table>");
		corpoHtmlEmail.append(" <div class=\"col-12\" style=\"padding-top: 20px;text-align: center;\">");
		corpoHtmlEmail.append("	   <a href=\"#urlEntrar#\" class=\"botaoReagendar\">Reagendar consulta</a></font></td>");
		corpoHtmlEmail.append("	</div>");
		corpoHtmlEmail.append("</div>");
		return corpoHtmlEmail.toString();
	}
	
	private  String corpoHtmlEmailReagendamentoCliente() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append("	<p>Olá!</p>");
		corpoHtmlEmail.append("	<p>Sua agenda de atendimento foi alterada acesse o link e confira as novas datas.</p>");		
		corpoHtmlEmail.append(" <div class=\"col-12\" style=\"padding-top: 20px;text-align: center;\">");
		corpoHtmlEmail.append("	   <a href=\"#urlEntrar#\" class=\"botaoReagendar\">Visualizar agenda</a></font></td>");
		corpoHtmlEmail.append("	</div>");
		corpoHtmlEmail.append("</div>");
		return corpoHtmlEmail.toString();
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
	
public String emailPadrao(String corpoEmail) {
	StringBuffer sb = new StringBuffer();
	sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	sb.append("");
	sb.append("<head>");
	sb.append("<title>ILIONnet</title>");
	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
	sb.append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" />");
	sb.append("	<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/jQuery.mmenu/8.5.6/mmenu.min.css\" />");
	sb.append("	<style type=\"text/css\">");
	sb.append("	  #triangulo-para-cima {");
	sb.append("		  width: 0; ");
	sb.append("		  height: 0; ");
	sb.append("		  border-left: 25px solid transparent;");
	sb.append("		  border-right: 25px solid transparent;");
	sb.append("		  border-bottom: 25px solid #82D0F5;");
	sb.append("		  margin-left: 46%;");
	sb.append("		  margin-top: 6px;");
	sb.append("		  padding-top: 45px;");
	sb.append("}");
	sb.append(".botaoReagendar {");
	sb.append("            height: 4rem;");
	sb.append("		    line-height: 4rem;");
	sb.append("		    padding: 8px 2.5rem;");
	sb.append("		    font-size: 18px;");
	sb.append("		    color: #ffffff;");
	sb.append("		    background: #218838;");
	sb.append("		    border-radius: 7px;");
	sb.append("		    text-decoration: none;");
	sb.append("		    margin: 0 1rem;");
	sb.append("		    transition: all .3s ease-in-out;");
	sb.append("		    border: 1px solid transparent;");
	sb.append(" }");
	sb.append("	</style>");
	sb.append("</head>");
	sb.append("<body>");
	sb.append("<div class=\"container\" style=\"padding-top: 5%;\">");
	sb.append("	<div class=\"row\">");
	sb.append("		<div class=\"col-12\">");
	sb.append("		 <img style=\"width:140px\" src=\"https://www.vitazure.com.br/assets/images/VITAZURE_LOGO_COR.png\" alt=\"\" /></p>");
	sb.append("		</div>");
	sb.append("		<div class=\"col-12\" style=\"border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;\">");
	sb.append("		 <h3>Prezado(a) #nome#, </h3>");
	sb.append("		</div>");
	sb.append(corpoEmail);
	sb.append("		<div id=\"triangulo-para-cima\"></div>");
	sb.append("		<div class=\"col-12\" style=\"border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;\">");
	sb.append("		</div>	");
	sb.append("	</div>");
	sb.append("</div>	");
	sb.append("</body>");
	sb.append("</html>");
	return sb.toString();
}	

}
