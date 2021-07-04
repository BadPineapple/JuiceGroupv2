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
public class ProdutoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
//	@Autowired
//	private IEntidadeImagemNegocio entidadeImagemNegocio;
	
	public List<Produto> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Produto.class);
		
		dc.add(Restrictions.eq("fazenda", fazenda));
		
		dc.addOrder(Order.asc("nome"));
		
		return (List<Produto>) hibernateUtil.list(dc);
	}
	
	public Produto findById(Long id) {
		return (Produto) hibernateUtil.findById(Produto.class, id);
	}
	
	@Transactional
	public Produto incluirAtualizar(Produto produto) throws Exception {
		
		if( produto.getFazenda() == null || produto.getFazenda().getId() == null ) {
			throw new ValidacaoException("Fazenda deve ser definida.");
		}
		
		if( Uteis.ehNuloOuVazio(produto.getNome()) ) {
			throw new ValidacaoException("Nome deve ser definido.");
		}
		
		if( produto.getId() == null ) {
			produto = (Produto) hibernateUtil.save(produto);
		} else {
			hibernateUtil.update(produto);
		}
		
		return produto;
	}
}