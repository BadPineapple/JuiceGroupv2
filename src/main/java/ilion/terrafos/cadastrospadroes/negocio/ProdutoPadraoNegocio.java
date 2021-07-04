package ilion.terrafos.cadastrospadroes.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Produto;
import ilion.terrafos.cadastros.negocio.ProdutoNegocio;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class ProdutoPadraoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private ProdutoNegocio produtoNegocio;
	
	public List<ProdutoPadrao> listar() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(ProdutoPadrao.class);
		
		dc.addOrder(Order.asc("nome"));
		
		return (List<ProdutoPadrao>) hibernateUtil.list(dc);
	}
	
	public ProdutoPadrao findById(Long id) {
		return (ProdutoPadrao) hibernateUtil.findById(ProdutoPadrao.class, id);
	}
	
	@Transactional
	public ProdutoPadrao incluirAtualizar(ProdutoPadrao produto) throws Exception {
		
		if( Uteis.ehNuloOuVazio(produto.getNome()) ) {
			throw new ValidacaoException("Nome deve ser definido.");
		}
		
		if( produto.getId() == null ) {
			produto = (ProdutoPadrao) hibernateUtil.save(produto);
		} else {
			hibernateUtil.update(produto);
		}
		
		return produto;
	}

	@Transactional
	public void cadastroInicial(Fazenda fazenda) throws Exception {
		
		List<ProdutoPadrao> produtos = listar();
		
		for (ProdutoPadrao pp : produtos) {
			
			Produto f = pp.toProduto(fazenda);
			produtoNegocio.incluirAtualizar(f);
			
		}
		
	}
}