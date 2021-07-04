package ilion.terrafos.cadastrospadroes.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Forrageira;
import ilion.terrafos.cadastros.negocio.ForrageiraNegocio;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class ForrageiraPadraoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private ForrageiraNegocio forrageiraNegocio;
	
	public List<ForrageiraPadrao> listar() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(ForrageiraPadrao.class);
		
		dc.addOrder(Order.asc("especie"));
		
		return (List<ForrageiraPadrao>) hibernateUtil.list(dc);
	}
	
	public ForrageiraPadrao findById(Long id) {
		return (ForrageiraPadrao) hibernateUtil.findById(ForrageiraPadrao.class, id);
	}
	
	@Transactional
	public ForrageiraPadrao incluirAtualizar(ForrageiraPadrao forrageira) throws Exception {
		
		if( Uteis.ehNuloOuVazio(forrageira.getEspecie()) ) {
			throw new ValidacaoException("Espécie deve ser definido.");
		}
		
		if( forrageira.getId() == null ) {
			forrageira = (ForrageiraPadrao) hibernateUtil.save(forrageira);
		} else {
			hibernateUtil.update(forrageira);
		}
		
		return forrageira;
	}

	@Transactional
	public void cadastroInicial(Fazenda fazenda) throws Exception {
		
		List<ForrageiraPadrao> forrageiras = listar();
		
		for (ForrageiraPadrao forrageiraPadrao : forrageiras) {
			
			Forrageira f = forrageiraPadrao.toForrageira(fazenda);
			forrageiraNegocio.incluirAtualizar(f);
			
		}
		
	}

	@Transactional
	public void excluir(ForrageiraPadrao forrageiraPadrao) {
		
		forrageiraPadrao = findById(forrageiraPadrao.getId());
		
		hibernateUtil.delete(forrageiraPadrao);
		
	}
}