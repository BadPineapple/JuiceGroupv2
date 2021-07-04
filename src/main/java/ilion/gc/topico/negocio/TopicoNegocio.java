package ilion.gc.topico.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ilion.util.persistencia.HibernateUtil;

@Service
public class TopicoNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	
	public Topico consultarPorId(Long id) {
		return (Topico) hibernateUtil.findById(Topico.class, id);
	}

	@Transactional
	public void excluir(Topico topico) {
		
		Assert.notNull(topico.getId(), "id deve ser preenchido.");
		
		topico = consultarPorId(topico.getId());
		
		hibernateUtil.delete(topico);
	}
	
	@Transactional
	public void incluirAtualizar(Topico topico) {
		
		if( topico.getId() == null ) {
			topico = (Topico) hibernateUtil.save(topico);
		} else {
			hibernateUtil.update(topico);
		}
		
	}
	
	public Boolean existeTopicoComNome(Topico topico) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Topico.class);
		dc.add(Restrictions.ilike("nome", topico.getNome()));
		
		if( topico.getId() != null ) {
			dc.add(Restrictions.not(Restrictions.eq("id", topico.getId())));
		}
		
		return hibernateUtil.possuiRegistros(dc);
	}

	public List<Topico> listar() {
		DetachedCriteria dc = DetachedCriteria.forClass(Topico.class);
		dc.addOrder(Order.asc("nome"));
		return (List<Topico>) hibernateUtil.list(dc);
	}
}
