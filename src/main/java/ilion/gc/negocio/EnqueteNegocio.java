package ilion.gc.negocio;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.util.persistencia.HibernateUtil;

@Service
public class EnqueteNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}

	public Object inserir(Object object) {
		return hibernateUtil.save(object);
	}

	public void atualizar(Object object) {
		hibernateUtil.update(object);
	}

	public void excluir(Object object) {
		hibernateUtil.delete(object);
	}

	public List listar() {
		DetachedCriteria dc = DetachedCriteria.forClass(Enquete.class);
		dc.addOrder(Order.desc("dataPublicacao"));
		
		return hibernateUtil.list(dc);
	}

	public void validarEnquete(Enquete enquete, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "assunto", "", "Assunto n�o pode ser vazio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "opcao1", "", "Op��o 1 n�o pode ser vazia.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "opcao2", "", "Op��o 2 n�o pode ser vazia.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "status", "", "Status n�o selecionado.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "dataPublicacao", "", "Data de publica��o n�o selecionada.");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "dataExpiracao", "", "Data de expiracao n�o selecionada.");
	}
	
	public Enquete consultarEnqueteSite() {
		DetachedCriteria dc = DetachedCriteria.forClass(Enquete.class);
		dc.add(Restrictions.eq("status", "Publicado"));
		dc.add(Restrictions.le("dataPublicacao", new Date()));
		dc.add(Restrictions.ge("dataExpiracao", new Date()));
		dc.addOrder(Order.desc("dataPublicacao"));
		
		List enquetes = hibernateUtil.listar(dc, 1, null, null);
		
		if(enquetes != null && ! enquetes.isEmpty()) {
			return (ilion.gc.negocio.Enquete) enquetes.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Enquete> listarEnqueteSite() {
		DetachedCriteria dc = DetachedCriteria.forClass(Enquete.class);
		dc.add(Restrictions.eq("status", "Publicado"));
		dc.add(Restrictions.le("dataPublicacao", new Date()));
		dc.add(Restrictions.ge("dataExpiracao", new Date()));
		dc.addOrder(Order.desc("dataPublicacao"));
		
		return (List<Enquete>) hibernateUtil.list(dc);
	}
	
	public Cookie configuraCookie(Long idEnquete){
   		Cookie cookie = new Cookie(idEnquete.toString(), new Date().toString());
   		cookie.setMaxAge(60 * 24 * 60 * 60);
   		cookie.setVersion(0);
   		cookie.setSecure(false);
   		cookie.setComment("Enquete ilionnet!");
   		return cookie;
	}
	
	public Enquete acrescentarVoto(Enquete enquete, String opcao) {
		if(opcao.equals("opcao1"))
			enquete.setVotosOpcao1( enquete.getVotosOpcao1().intValue() + 1 );
		else if(opcao.equals("opcao2"))
			enquete.setVotosOpcao2( enquete.getVotosOpcao2().intValue() + 1 );
		else if(opcao.equals("opcao3"))
			enquete.setVotosOpcao3( enquete.getVotosOpcao3().intValue() + 1 );
		else if(opcao.equals("opcao4"))
			enquete.setVotosOpcao4( enquete.getVotosOpcao4().intValue() + 1 );
		else if(opcao.equals("opcao5"))
			enquete.setVotosOpcao5( enquete.getVotosOpcao5().intValue() + 1 );
		else if(opcao.equals("opcao6"))
			enquete.setVotosOpcao6( enquete.getVotosOpcao6().intValue() + 1 );
		
		return enquete;
	}
}