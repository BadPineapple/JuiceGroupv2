package ilion.vitazure.negocio;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.contato.controller.ContatoVH;
import ilion.contato.negocio.Contato;
import ilion.contato.negocio.ContatoNegocio;
import ilion.email.negocio.Email;
import ilion.email.negocio.EmailNegocio;
import ilion.email.negocio.EmailSenderFactory;
import ilion.util.Uteis;
import ilion.vitazure.enumeradores.StatusEnum;
import ilion.vitazure.model.Agenda;
import ilion.vitazure.model.Pessoa;
import ilion.vitazure.model.Profissional;


@Service
public class EnvioEmailConsulta {

	static Logger logger = Logger.getLogger(EnvioEmailConsulta.class);
	
	@Autowired
	private EmailNegocio emailNegocio;
	
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private AgendaNegocio agendaNegocio;
	
	
	public void enviar(Agenda agenda) throws Exception {
		StringBuffer htmlEmail = new StringBuffer();
		htmlEmail.append(emailPadrao(corpoHtmlEmailSolicitacaoAgendamento()));
		enviarEmailAlteracaoSituacaoAgendaCliente(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getPaciente().getNome()).replaceAll("#nomePaciente#", agenda.getPaciente().getNome()).replaceAll("#emailPaciente#", agenda.getPaciente().getEmail()).replaceAll("#celularPaciente#", agenda.getPaciente().getCelular()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()), "Solicitação agendamento");
		enviarEmailAlteracaoSituacaoAgendaProfissional(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#nomePaciente#", agenda.getPaciente().getNome()).replaceAll("#emailPaciente#", agenda.getPaciente().getEmail()).replaceAll("#celularPaciente#", agenda.getPaciente().getCelular()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()), "Solicitação agendamento");
		
	}
	
	public void enviarEmailAlteracaoSituacaoAgenda(Agenda agenda ,Pessoa pessoaSessao) {
		StringBuffer htmlEmail = new StringBuffer();
		if(agenda.getStatus().equals(StatusEnum.CONFIRMADO)) {
			htmlEmail.append(emailPadrao(corpoHtmlEmailConfirmacao()));
			enviarEmailAlteracaoSituacaoAgendaCliente(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getPaciente().getNome()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()), "Confirmação agendamento");
		}else if(agenda.getStatus().equals(StatusEnum.REMARCADO) && pessoaSessao.getCliente()) {
			String urlEntrar = propNegocio.findValueById(PropEnum.URL).concat("/entrar");
			htmlEmail.append(emailPadrao(corpoHtmlEmailReagendamentoCliente()));
			enviarEmailAlteracaoSituacaoAgendaProfissional(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#urlEntrar#", urlEntrar), "Agendamento remarcado pelo cliente.");
		
		}else if(agenda.getStatus().equals(StatusEnum.AGUARDANDO_REMARCACAO) && pessoaSessao.getPsicologo()) {
			String urlEntrar = propNegocio.findValueById(PropEnum.URL).concat("/entrar");
			htmlEmail.append(emailPadrao(corpoHtmlEmailReagendamentoProfissional()));
			enviarEmailAlteracaoSituacaoAgendaCliente(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getPaciente().getNome()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()).replaceAll("#urlEntrar#", urlEntrar), "Solicitação de reagentamento pelo profissional no portal vitazure");
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
		corpoHtmlEmail.append("	<p>Caro cliente, o profissional de saúde do portal Vitazure solicitou um reagendamento da sua consulta.</p>");
		corpoHtmlEmail.append("	<p>Por favor, acesse o site para fazer esta remarcação.</p>");
		corpoHtmlEmail.append(" <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("	<tr> ");
		corpoHtmlEmail.append("	   <td align=\"center\" bgcolor=\"#D6E4E9\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" color=\"#ff1744\"><strong>Consulta aguardando remarcação</strong></font></td>");
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
	
	
	
	private  String corpoHtmlEmailSolicitacaoAgendamento() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append(" <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("	<tr> ");
		corpoHtmlEmail.append("	   <td align=\"center\" bgcolor=\"#D6E4E9\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\"><strong>Agendamento Consulta</strong></font></td>");
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
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Nome:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#nomePaciente#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">E-mail:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#emailPaciente#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Celular:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#celularPaciente#</font></td>");
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
	sb.append("         height: 4rem;");
	sb.append("		    line-height: 4rem;");
	sb.append("		    padding: 8px 2.5rem;");
	sb.append("		    font-size: 18px;");
	sb.append("		    color: #ffffff !important;");
	sb.append("		    background: #82d0f5;");
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

	public void enviarAlertaAgenda(Agenda agenda){
		StringBuffer htmlEmail = new StringBuffer();
		htmlEmail.append(emailPadrao(corpoHtmlEmailAlertaAgenda()));
		enviarEmailAlteracaoSituacaoAgendaCliente(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getPaciente().getNome()).replaceAll("#nomePaciente#", agenda.getPaciente().getNome()).replaceAll("#emailPaciente#", agenda.getPaciente().getEmail()).replaceAll("#celularPaciente#", agenda.getPaciente().getCelular()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()), "Alerta agendamento no portal vitazure");
		enviarEmailAlteracaoSituacaoAgendaProfissional(agenda, htmlEmail.toString().replaceAll("#nome#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#nomePaciente#", agenda.getPaciente().getNome()).replaceAll("#emailPaciente#", agenda.getPaciente().getEmail()).replaceAll("#celularPaciente#", agenda.getPaciente().getCelular()).replaceAll("#dataAgendaEmail#", agenda.getDataAgendaEmail()).replaceAll("#profissionalAtendimento#", agenda.getProfissional().getPessoa().getNome()).replaceAll("#horaAgendaEmail#", agenda.getHoraAgendaEmail()), "Alerta agendamento no portal vitazure");
		agendaNegocio.envioAlertaAgenda(agenda);
	}

	private  String corpoHtmlEmailAlertaAgenda() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append("	<p>Gostaria de lembrá-lo(a) sobre seu horário marcado , segue os dados do agendamento. Obrigado!</p>");
		corpoHtmlEmail.append(" <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("	<tr> ");
		corpoHtmlEmail.append("	   <td align=\"center\" bgcolor=\"#D6E4E9\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\"><strong>Agendamento Consulta</strong></font></td>");
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
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Nome:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#nomePaciente#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">E-mail:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#emailPaciente#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Celular:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#celularPaciente#</font></td>");
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
	
	public void enviarMensagemForm(Contato contato, ContatoVH contatoVH) throws Exception {
		StringBuffer htmlEmail = new StringBuffer();
		htmlEmail.append(emailPadraoForm(corpoHtmlEmailForm()));
		String emailsTo = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);
		String assunto = "";
		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");
		if (emailsLista == null) {
			logger.error("nenhum email na propriedade: " + PropEnum.EMAILS_CONTATO);
			return;
		}
		if (contatoVH.getOuvidoria()) {
			assunto = "[SITE] Ouvidoria - " + propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		}else {
			assunto = "[SITE] Contato - " + propNegocio.findValueById(PropEnum.NOME_EMPRESA);
		}
		for (String emailTo : emailsLista) {
			Email e = new Email();
			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(emailTo);
			e.setReplyToEmail(contato.getEmail());
			e.setToName(contato.getNome());
			e.setSubject(assunto);
			e.setMessage(htmlEmail.toString().replaceAll("#nome#", contato.getNome()).replaceAll("#email#", contato.getEmail()).replaceAll("#celular#", contato.getTelefone()).replaceAll("#mensagem#", contato.getMensagem()).replaceAll("#tipoMensagem#", assunto));			
			EmailSenderFactory emailSenderFactory =	SpringApplicationContext.getBean(EmailSenderFactory.class);
			emailSenderFactory.getInstance().send(e);
		}
	}
	
	private  String corpoHtmlEmailForm() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append(" <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("	<tr> ");
		corpoHtmlEmail.append("	   <td align=\"center\" bgcolor=\"#D6E4E9\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\"><strong>#tipoMensagem#</strong></font></td>");
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
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Nome:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#nome#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">E-mail:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#email#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Celular:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#celular#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Mensagem:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#mensagem#</font></td>");
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
	
	
	public String emailPadraoForm(String corpoEmail) {
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
		sb.append("         height: 4rem;");
		sb.append("		    line-height: 4rem;");
		sb.append("		    padding: 8px 2.5rem;");
		sb.append("		    font-size: 18px;");
		sb.append("		    color: #ffffff !important;");
		sb.append("		    background: #82d0f5;");
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
	
	public void enviarMensagemAutorizarProfissional(Profissional profissional) throws Exception {
		StringBuffer htmlEmail = new StringBuffer();
		htmlEmail.append(emailPadraoForm(corpoHtmlEmailAutorizarProfissional()));
		String emailsTo = propNegocio.findValueById(PropEnum.EMAILS_CONTATO);
		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");
		if (emailsLista == null) {
			logger.error("nenhum email na propriedade: " + PropEnum.EMAILS_CONTATO);
			return;
		}
		String assunto = "Ativação de cadastro profissional";
		for (String emailTo : emailsLista) {
			Email e = new Email();
			e.setToName(propNegocio.findValueById(PropEnum.NOME_EMPRESA));
			e.setToEmail(emailTo);
			e.setSubject(assunto);
			e.setMessage(htmlEmail.toString().replaceAll("#nome#", profissional.getPessoa().getNome()).replaceAll("#email#", profissional.getPessoa().getEmail()).replaceAll("#celular#", profissional.getPessoa().getCelular()).replaceAll("#plano#", profissional.getPlano()).replaceAll("#tipoMensagem#", assunto));			
			EmailSenderFactory emailSenderFactory =	SpringApplicationContext.getBean(EmailSenderFactory.class);
			emailSenderFactory.getInstance().send(e);
		}
	}
	
	private  String corpoHtmlEmailAutorizarProfissional() {
		StringBuffer corpoHtmlEmail = new StringBuffer();
		corpoHtmlEmail.append("	<div class=\"col-12\" style=\"padding-top: 20px;\">");
		corpoHtmlEmail.append(" <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		corpoHtmlEmail.append("	<tr> ");
		corpoHtmlEmail.append("	   <td align=\"center\" bgcolor=\"#D6E4E9\"><font size=\"2\" face=\"Arial, Helvetica, sans-serif\"><strong>#tipoMensagem#</strong></font></td>");
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
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Nome:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#nome#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">E-mail:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#email#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">Celular:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#celular#</font></td>");
		corpoHtmlEmail.append("				              </tr>");
		corpoHtmlEmail.append("				              <tr> ");
		corpoHtmlEmail.append("				                <td width=\"90\"><strong><font size=\"2\" face=\"Arial, Helvetica, sans-serif\" style=\"color: #1895d4;\">plano:</font></strong></td>");
		corpoHtmlEmail.append("				                <td><font size=\"2\" face=\"Arial, Helvetica, sans-serif\">#plano#</font></td>");
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
	
}
