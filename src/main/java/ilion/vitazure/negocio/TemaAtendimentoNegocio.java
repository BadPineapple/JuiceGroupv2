package ilion.vitazure.negocio;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.model.TemaTrabalho;

@Service
public class TemaAtendimentoNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public TemaTrabalho consultarPorProfissional(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(TemaTrabalho.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		TemaTrabalho temaTrabalho = (TemaTrabalho) hibernateUtil.consultarUniqueResult(dc);
		if (temaTrabalho == null) {
			return new TemaTrabalho();
		}
		return temaTrabalho;
	}
	
	@Transactional
	public List<TemaTrabalho> validarItens(List<TemaTrabalho> listTemaTrabalho , Profissional profissional) throws Exception{
		
		List<TemaTrabalho> temaTrabalho = consultarTemasPorProfissional(profissional.getId());
		
		List<TemaTrabalho> itensDuplicados = temaTrabalho.stream()
	        .filter( o1 -> {
	            return listTemaTrabalho.stream()
	                    .map(TemaTrabalho::getId)
	                    .anyMatch(i2 -> i2.equals(o1.getId()));
	        }).collect(Collectors.toList());
				
		temaTrabalho.removeAll(itensDuplicados);
		
		temaTrabalho.forEach(arquivoExcluir-> {
			try {
				excluir(arquivoExcluir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		if (!listTemaTrabalho.isEmpty()) {
			listTemaTrabalho.forEach(tema -> {
  			  try {
  				tema.setProfissional(profissional);
  				  incluirAtualizar(tema);
  			  } catch (Exception e) {
  				  e.printStackTrace();
  			  }
  		  });
  	  }
		
		return listTemaTrabalho;
	}
	
	@Transactional
	public TemaTrabalho incluirAtualizar(TemaTrabalho temaTrabalho) {
		if (temaTrabalho.getId() == null || temaTrabalho.getId() == 0) {
			temaTrabalho = (TemaTrabalho) hibernateUtil.save(temaTrabalho);
		} else {
			hibernateUtil.update(temaTrabalho);
		}
		return temaTrabalho;
	}
	
	public List<TemaTrabalho> consultarTemasPorProfissional(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(TemaTrabalho.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		return (List<TemaTrabalho>) hibernateUtil.list(dc);
	}
	
	@Transactional
	public void excluir(TemaTrabalho temaTrabalho) {
		hibernateUtil.delete(temaTrabalho);
	}
	
}
