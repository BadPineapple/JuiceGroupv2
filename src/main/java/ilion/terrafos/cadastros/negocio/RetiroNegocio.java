package ilion.terrafos.cadastros.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class RetiroNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
//	@Autowired
//	private IEntidadeImagemNegocio entidadeImagemNegocio;
	
	public List<Retiro> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Retiro.class);
		
		dc.add(Restrictions.eq("fazenda", fazenda));
		
		dc.addOrder(Order.asc("nome"));
		
		List<Retiro> retiros = (List<Retiro>) hibernateUtil.list(dc);
		
		return retiros;
	}
	
	public Retiro findById(Long id) {
		return (Retiro) hibernateUtil.findById(Retiro.class, id);
	}
	
	@Transactional
	public Retiro incluirAtualizar(Retiro retiro) throws Exception {
		
		if( retiro.getFazenda() == null || retiro.getFazenda().getId() == null ) {
			throw new ValidacaoException("Fazenda deve ser definida.");
		}
		
		if( Uteis.ehNuloOuVazio(retiro.getNome()) ) {
			throw new ValidacaoException("Nome deve ser definido.");
		}
		
		if( retiro.getId() == null ) {
			retiro = (Retiro) hibernateUtil.save(retiro);
		} else {
			hibernateUtil.update(retiro);
		}
		
		return retiro;
	}
}