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
public class RacaNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public List<Raca> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Raca.class);
		
		dc.add(Restrictions.eq("fazenda", fazenda));
		
		dc.addOrder(Order.asc("nome"));
		
		return (List<Raca>) hibernateUtil.list(dc);
	}
	
	public Raca findById(Long id) {
		return (Raca) hibernateUtil.findById(Raca.class, id);
	}
	
	@Transactional
	public Raca incluirAtualizar(Raca raca) throws Exception {
		
		if( raca.getFazenda() == null || raca.getFazenda().getId() == null ) {
			throw new ValidacaoException("Fazenda deve ser definida.");
		}
		
		if( Uteis.ehNuloOuVazio(raca.getNome()) ) {
			throw new ValidacaoException("Categoria Animal deve ser definido.");
		}
		
		if( raca.getId() == null ) {
			raca = (Raca) hibernateUtil.save(raca);
		} else {
			hibernateUtil.update(raca);
		}
		
		return raca;
	}
}