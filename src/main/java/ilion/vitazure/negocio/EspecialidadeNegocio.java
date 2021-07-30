package ilion.vitazure.negocio;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.Especialidade;
import ilion.vitazure.model.Profissional;

@Service
public class EspecialidadeNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public Especialidade consultarPorProfissional(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(Especialidade.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		Especialidade especialidade = (Especialidade) hibernateUtil.consultarUniqueResult(dc);
		if (especialidade == null) {
			return new Especialidade();
		}
		return especialidade;
	}
	
	@Transactional
	public List<Especialidade> validarItens(List<Especialidade> listEspecialidade , Profissional profissional) throws Exception{
		
		List<Especialidade> especialidade = consultarEspecialidadesProfissional(profissional.getId());
		
		List<Especialidade> itensDuplicados = especialidade.stream()
	        .filter( o1 -> {
	            return listEspecialidade.stream()
	                    .map(Especialidade::getId)
	                    .anyMatch(i2 -> i2.equals(o1.getId()));
	        }).collect(Collectors.toList());
				
		especialidade.removeAll(itensDuplicados);
		
		especialidade.forEach(especialidadeExcluir-> {
			try {
				excluir(especialidadeExcluir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		if (!listEspecialidade.isEmpty()) {
			listEspecialidade.forEach(especialidadeAtendimento -> {
  			  try {
  				especialidadeAtendimento.setProfissional(profissional);
  				  incluirAtualizar(especialidadeAtendimento);
  			  } catch (Exception e) {
  				  e.printStackTrace();
  			  }
  		  });
  	  }
		
		return listEspecialidade;
	}
	
	@Transactional
	public Especialidade incluirAtualizar(Especialidade especialidade) {
		if (especialidade.getId() == null || especialidade.getId() == 0) {
			especialidade = (Especialidade) hibernateUtil.save(especialidade);
		} else {
			hibernateUtil.update(especialidade);
		}
		return especialidade;
	}
	
	public List<Especialidade> consultarEspecialidadesProfissional(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(Especialidade.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		return (List<Especialidade>) hibernateUtil.list(dc);
	}
	
	@Transactional
	public void excluir(Especialidade especialidade) {
		hibernateUtil.delete(especialidade);
	}

}
