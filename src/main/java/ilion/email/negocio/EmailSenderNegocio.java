package ilion.email.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class EmailSenderNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Cacheable("email.senders")
	public List<EmailSender> listar() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(EmailSender.class);
		
		dc.addOrder(Order.asc("prioridade"));
		
		return (List<EmailSender>) hibernateUtil.list(dc);
	}
	
	public EmailSender findById(Long id) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(EmailSender.class);
		
		dc.add(Restrictions.eq("id", id));
		
		return (EmailSender) hibernateUtil.uniqueResult(dc);
	}
	
	public EmailSender findByTypeId(EmailSenderEnum emailSenderEnum, Long id) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(EmailSender.class);
		
		dc.add(Restrictions.eq("emailSenderEnum", emailSenderEnum));
		dc.add(Restrictions.eq("id", id));
		
		return (EmailSender) hibernateUtil.uniqueResult(dc);
	}
	
	@Transactional
	@CacheEvict(value={
		"email.senders",
		"email.sender.ativo"
	},allEntries=true)
	public EmailSender incluirAtualizar(EmailSender emailSender) {
		
		if( emailSender.getEmailSenderEnum() == null ) {
			throw new ValidacaoException("Tipo de email sender deve ser preenchido.");
		}
		
		if( Uteis.ehNuloOuVazio(emailSender.getNome()) ) {
			throw new ValidacaoException("Nome do email sender deve ser preenchido.");
		}
		
		if( emailSender.getPrioridade() == null ) {
			throw new ValidacaoException("Prioridade do email sender deve ser preenchido.");
		}
		
		if( EmailSenderEnum.SENDGRID.equals(emailSender.getEmailSenderEnum()) ) {
			
			if( Uteis.ehNuloOuVazio(emailSender.getSendGridVH().getEmail()) ) {
				throw new ValidacaoException("E-mail deve ser preenchido.");
			}
			
			if( Uteis.ehNuloOuVazio(emailSender.getSendGridVH().getApiKey()) ) {
				throw new ValidacaoException("API key deve ser preenchida.");
			}
			
		}
		
		if( EmailSenderEnum.SMTP.equals(emailSender.getEmailSenderEnum()) ) {
			
			if( Uteis.ehNuloOuVazio(emailSender.getSmtpVH().getServer()) ) {
				throw new ValidacaoException("Server deve ser preenchido.");
			}
			
			if( emailSender.getSmtpVH().getPorta() == null ) {
				throw new ValidacaoException("Porta deve ser preenchido.");
			}
			
			if( Uteis.ehNuloOuVazio(emailSender.getSmtpVH().getEmail()) ) {
				throw new ValidacaoException("E-mail deve ser preenchido.");
			}
			
		}
		
		emailSender.beanToJson();
		
		if( Uteis.ehNuloOuVazio(emailSender.getDadosJson()) ) {
			throw new ValidacaoException("Dados do email sender deve ser preenchido.");
		}
		
		if( emailSender.getId() == null ) {
			emailSender = (EmailSender) hibernateUtil.save(emailSender);
		} else {
			hibernateUtil.update(emailSender);
		}
		
		return emailSender;
	}
	
	@Cacheable("email.sender.ativo")
	public EmailSender consultarAtivo() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(EmailSender.class);
		
		dc.add(Restrictions.gt("prioridade", 0));
		
		dc.addOrder(Order.asc("prioridade"));
		
		List<EmailSender> senders = (List<EmailSender>) hibernateUtil.list(dc);
		
		if( senders.isEmpty() ) {
			return null;
		}
		
		return senders.get(0);
	}
	
	
}