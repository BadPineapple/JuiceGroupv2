package ilion.terrafos.cadastrospadroes.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.CategoriaAnimalNegocio;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class CategoriaAnimalPadraoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private CategoriaAnimalNegocio categoriaAnimalNegocio;
	
	public List<CategoriaAnimalPadrao> listar() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaAnimalPadrao.class);
		
		dc.addOrder(Order.asc("lote"));
		
		return (List<CategoriaAnimalPadrao>) hibernateUtil.list(dc);
	}
	
	public CategoriaAnimalPadrao findById(Long id) {
		return (CategoriaAnimalPadrao) hibernateUtil.findById(CategoriaAnimalPadrao.class, id);
	}
	
	@Transactional
	public CategoriaAnimalPadrao incluirAtualizar(CategoriaAnimalPadrao categoriaAnimalPadrao) throws Exception {
		
		if( Uteis.ehNuloOuVazio(categoriaAnimalPadrao.getLote()) ) {
			throw new ValidacaoException("Lote deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(categoriaAnimalPadrao.getSigla()) ) {
			throw new ValidacaoException("Sigla deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(categoriaAnimalPadrao.getNome()) ) {
			throw new ValidacaoException("Categoria Animal deve ser definido.");
		}
		
		if( categoriaAnimalPadrao.getNascimentosSexo() != null ) {
			if( categoriaParaNascimentoJaCadastrada(categoriaAnimalPadrao) ) {
				throw new ValidacaoException("Já existe uma categoria animal definida para o sexo: "+categoriaAnimalPadrao.getNascimentosSexo().getNome());				
			}
		}
		
		if( categoriaAnimalPadrao.getId() == null ) {
			categoriaAnimalPadrao = (CategoriaAnimalPadrao) hibernateUtil.save(categoriaAnimalPadrao);
		} else {
			hibernateUtil.update(categoriaAnimalPadrao);
		}
		
		return categoriaAnimalPadrao;
	}
	
	public Boolean categoriaParaNascimentoJaCadastrada(CategoriaAnimalPadrao ca) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaAnimalPadrao.class);
		
		dc.add(Restrictions.eq("nascimentosSexo", ca.getNascimentosSexo()));
		
		if(ca.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", ca.getId())));
		}
		
		return hibernateUtil.possuiRegistros(dc);
	}

	@Transactional
	public void cadastroInicial(Fazenda fazenda) throws Exception {
		
		List<CategoriaAnimalPadrao> categorias = listar();
		
		for (CategoriaAnimalPadrao cap : categorias) {
			
			CategoriaAnimal f = cap.toCategoriaAnimal(fazenda);
			categoriaAnimalNegocio.incluirAtualizar(f);
			
		}
		
	}
}