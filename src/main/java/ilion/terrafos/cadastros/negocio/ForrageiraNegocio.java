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
public class ForrageiraNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	public List<Forrageira> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Forrageira.class);
		
		dc.add(Restrictions.eq("fazenda", fazenda));
		
		dc.addOrder(Order.asc("especie"));
		
		return (List<Forrageira>) hibernateUtil.list(dc);
	}
	
	public Forrageira findById(Long id) {
		return (Forrageira) hibernateUtil.findById(Forrageira.class, id);
	}
	
	@Transactional
	public Forrageira incluirAtualizar(Forrageira forrageira) throws Exception {
		
		if( forrageira.getFazenda() == null || forrageira.getFazenda().getId() == null ) {
			throw new ValidacaoException("Fazenda deve ser definida.");
		}
		
		if( Uteis.ehNuloOuVazio(forrageira.getEspecie()) ) {
			throw new ValidacaoException("Espécie deve ser definido.");
		}
		
		if( forrageira.getId() == null ) {
			forrageira = (Forrageira) hibernateUtil.save(forrageira);
		} else {
			hibernateUtil.update(forrageira);
		}
		
		return forrageira;
	}
	
	@Transactional
	public void excluir(Forrageira forrageira) {
		
		if( pastoNegocio.haPastoVinculado(forrageira) ) {
			throw new ValidacaoException("Forrageira não pode ser excluída, há pasto vinculado.");
		}
		
		forrageira = findById(forrageira.getId());
		
		hibernateUtil.delete(forrageira);
		
	}
}