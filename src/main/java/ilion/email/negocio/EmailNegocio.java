package ilion.email.negocio;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import ilion.util.exceptions.NenhumEmailSenderException;
import ilion.util.persistencia.HibernateUtil;
import net.mlw.vlh.ValueList;

@Service
public class EmailNegocio {

  static Logger logger = Logger.getLogger(EmailNegocio.class);

  private static final Integer QTD_TENTATIVAS = 20;

  @Autowired
  private EmailSenderFactory emailSenderFactory;

  @Autowired
  private HibernateUtil hibernateUtil;

  public ValueList busca(VLHForm vlhForm, ValueListInfo valueListInfo) {

    DetachedCriteria dc = DetachedCriteria.forClass(Email.class);

    return hibernateUtil.consultarValueList(dc, Order.desc("createdAt"), valueListInfo);
  }

  private Email consultarProximoEmail() {

    DetachedCriteria dc = DetachedCriteria.forClass(Email.class);

    Disjunction disjunction = Restrictions.disjunction();
    disjunction.add(Restrictions.isNull("errorCount"));
    disjunction.add(Restrictions.lt("errorCount", QTD_TENTATIVAS));
    dc.add(disjunction);

    Order order = System.currentTimeMillis() % 2 == 1 ? Order.asc("id") : Order.desc("id");

    dc.addOrder(Order.asc("priority"));
    dc.addOrder(order);

    List<Email> emails = hibernateUtil.listar(dc, 1, 0, null);

    Email retorno = null;

    if (emails != null && !emails.isEmpty()) {
      retorno = emails.get(0);
    }

    return retorno;
  }

  @Transactional
  @CacheEvict(value = "emails.qtd.para.envio", allEntries = true)
  public Boolean enviarEmail() throws Exception {
    Email email = consultarProximoEmail();

    if (email == null) {
      return false;
    }

    if (!Uteis.ehEmailValido(email.getToEmail())) {
      logger.info("Email inválido, envio IGNORADO: " + email);
      retirarEmail(email);
      return true;
    }

    try {

      IEmailSender emailSender = emailSenderFactory.getInstance();

      emailSender.send(email);

      retirarEmail(email);

    } catch (NenhumEmailSenderException e) {

      logger.error("nenhum e-mail sender ativo!");

    } catch (Exception e) {
      logger.error("Erro ao enviar email: " + email, e);

      incrementarTentativa(email);
    }

    return true;
  }

  @Transactional
  @CacheEvict(value = "emails.qtd.para.envio", allEntries = true)
  public void incrementarTentativa(Email email) {
    Integer errorCount = email.getErrorCount();
    if (errorCount == null) {
      errorCount = 0;
    }
    errorCount++;

    email.setErrorCount(errorCount);

    hibernateUtil.update(email);
  }

  @Transactional
  @CacheEvict(value = "emails.qtd.para.envio", allEntries = true)
  public void retirarEmail(Email email) {

    hibernateUtil.delete(email);

  }

  @Transactional
  @CacheEvict(value = "emails.qtd.para.envio", allEntries = true)
  public void adicionarEmail(Email email) {
      hibernateUtil.save(email);
  }

  @Transactional
  @CacheEvict(value = "emails.qtd.para.envio", allEntries = true)
  public void adicionarEmailPedido(Email email) {

    hibernateUtil.save(email);

  }

  private Boolean jaAdicionadoParaEnvio(Email email) {
    DetachedCriteria dc = DetachedCriteria.forClass(Email.class);

    dc.add(Restrictions.eq("toEmail", email.getToEmail()));
    dc.add(Restrictions.eq("subject", email.getSubject()));

    return hibernateUtil.possuiRegistros(dc);
  }

  @Cacheable("emails.qtd.para.envio")
  public Long qtdEmailsParaEnvio() {
    DetachedCriteria dc = DetachedCriteria.forClass(Email.class);

    Disjunction disjunction = Restrictions.disjunction();
    disjunction.add(Restrictions.isNull("errorCount"));
    disjunction.add(Restrictions.lt("errorCount", QTD_TENTATIVAS));
    dc.add(disjunction);

    return (Long) hibernateUtil.consultarQtd(dc);
  }

}