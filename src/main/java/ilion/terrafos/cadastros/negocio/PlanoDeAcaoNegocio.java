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
public class PlanoDeAcaoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
//	@Autowired
//	private IEntidadeImagemNegocio entidadeImagemNegocio;
	
	public List<PlanoDeAcao> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(PlanoDeAcao.class);
		
		dc.add(Restrictions.eq("fazenda", fazenda));
		
		dc.addOrder(Order.desc("dataVisita"));
		
		List<PlanoDeAcao> retiros = (List<PlanoDeAcao>) hibernateUtil.list(dc);
		
		return retiros;
	}
	
	public PlanoDeAcao findById(Long id) {
		return (PlanoDeAcao) hibernateUtil.findById(PlanoDeAcao.class, id);
	}
	
	@Transactional
	public PlanoDeAcao incluirAtualizar(PlanoDeAcao planoDeAcao) throws Exception {
		
		if( planoDeAcao.getFazenda() == null || planoDeAcao.getFazenda().getId() == null ) {
			throw new ValidacaoException("Fazenda deve ser definida.");
		}
		
		if( planoDeAcao.getDataVisita() == null ) {
			throw new ValidacaoException("Data da visita deve ser definida.");
		}
		
		if( Uteis.ehNuloOuVazio(planoDeAcao.getoQue()) ) {
			throw new ValidacaoException("'O que?' deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(planoDeAcao.getComo()) ) {
			throw new ValidacaoException("'Como?' deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(planoDeAcao.getPorQue()) ) {
			throw new ValidacaoException("'Porque?' deve ser definido.");
		}
		
		if( planoDeAcao.getId() == null ) {
			planoDeAcao = (PlanoDeAcao) hibernateUtil.save(planoDeAcao);
		} else {
			hibernateUtil.update(planoDeAcao);
		}
		
		return planoDeAcao;
	}
}