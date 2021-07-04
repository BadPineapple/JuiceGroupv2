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
public class CategoriaAnimalNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
//	@Autowired
//	private IEntidadeImagemNegocio entidadeImagemNegocio;
	
	public List<CategoriaAnimal> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaAnimal.class);
		
		dc.add(Restrictions.eq("fazenda", fazenda));
		
		dc.addOrder(Order.asc("lote"));
		
		return (List<CategoriaAnimal>) hibernateUtil.list(dc);
	}
	
	public CategoriaAnimal findById(Long id) {
		return (CategoriaAnimal) hibernateUtil.findById(CategoriaAnimal.class, id);
	}
	
	@Transactional
	public CategoriaAnimal incluirAtualizar(CategoriaAnimal categoriaAnimal) throws Exception {
		
		if( categoriaAnimal.getFazenda() == null || categoriaAnimal.getFazenda().getId() == null ) {
			throw new ValidacaoException("Fazenda deve ser definida.");
		}
		
		if( Uteis.ehNuloOuVazio(categoriaAnimal.getLote()) ) {
			throw new ValidacaoException("Lote deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(categoriaAnimal.getSigla()) ) {
			throw new ValidacaoException("Sigla deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(categoriaAnimal.getNome()) ) {
			throw new ValidacaoException("Categoria Animal deve ser definido.");
		}
		
		if( categoriaAnimal.getNascimentosSexo() != null ) {
			if( categoriaParaNascimentoJaCadastrada(categoriaAnimal) ) {
				throw new ValidacaoException("Já existe uma categoria animal definida para o sexo: "+categoriaAnimal.getNascimentosSexo().getNome());				
			}
		}
		
		if( categoriaAnimal.getId() == null ) {
			categoriaAnimal = (CategoriaAnimal) hibernateUtil.save(categoriaAnimal);
		} else {
			hibernateUtil.update(categoriaAnimal);
		}
		
		return categoriaAnimal;
	}
	
	public Boolean categoriaParaNascimentoJaCadastrada(CategoriaAnimal ca) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaAnimal.class);
		
		dc.add(Restrictions.eq("fazenda", ca.getFazenda()));
		
		dc.add(Restrictions.eq("nascimentosSexo", ca.getNascimentosSexo()));
		
		if(ca.getId() != null) {
			dc.add(Restrictions.not(Restrictions.eq("id", ca.getId())));
		}
		
		return hibernateUtil.possuiRegistros(dc);
	}

	public CategoriaAnimal consultarCategoriaParaNascimento(Sexo sexo) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CategoriaAnimal.class);
		
		dc.add(Restrictions.eq("nascimentosSexo", sexo));
		
		return (CategoriaAnimal) hibernateUtil.uniqueResult(dc);
	}
	
	@Transactional
	public void excluir(CategoriaAnimal categoriaAnimal) {
		
		categoriaAnimal = findById(categoriaAnimal.getId());
		
		hibernateUtil.delete(categoriaAnimal);
		
	}
}