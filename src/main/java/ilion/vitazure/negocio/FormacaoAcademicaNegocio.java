package ilion.vitazure.negocio;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.Profissional;

@Service
@SuppressWarnings("unchecked")
public class FormacaoAcademicaNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public FormacaoAcademica consultarPorPessoa(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(FormacaoAcademica.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		FormacaoAcademica formacaoAcademica = (FormacaoAcademica) hibernateUtil.consultarUniqueResult(dc);
		if (formacaoAcademica == null) {
			return new FormacaoAcademica();
		}
		return formacaoAcademica;
	}
	
	public List<FormacaoAcademica> consultarFormacoesPorPessoa(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(FormacaoAcademica.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		return (List<FormacaoAcademica>) hibernateUtil.list(dc);
	}
	
	@Transactional
	public FormacaoAcademica incluirAtualizar(FormacaoAcademica formacaoAcademica) throws Exception{
		if (formacaoAcademica.getId() == null || formacaoAcademica.getId() == 0) {
			formacaoAcademica = (FormacaoAcademica) hibernateUtil.save(formacaoAcademica);
		} else {
			hibernateUtil.update(formacaoAcademica);
		}
		return formacaoAcademica;
	}
	
	@Transactional
	public List<FormacaoAcademica> validarItens(List<FormacaoAcademica> listFormacaoAcademica , Profissional profissional) throws Exception{
		
		List<FormacaoAcademica> formacaoAcademica = consultarFormacoesPorPessoa(profissional.getId());
		
		List<FormacaoAcademica> itensDuplicados = formacaoAcademica.stream()
	        .filter( o1 -> {
	            return listFormacaoAcademica.stream()
	                    .map(FormacaoAcademica::getId)
	                    .anyMatch(i2 -> i2.equals(o1.getId()));
	        }).collect(Collectors.toList());
				
		formacaoAcademica.removeAll(itensDuplicados);
		
		formacaoAcademica.forEach(arquivoExcluir-> {
			try {
				excluir(arquivoExcluir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		if (!listFormacaoAcademica.isEmpty()) {
			listFormacaoAcademica.forEach(endereco -> {
  			  try {
  				  endereco.setProfissional(profissional);
  				  incluirAtualizar(endereco);
  			  } catch (Exception e) {
  				  e.printStackTrace();
  			  }
  		  });
  	  }
		
		return listFormacaoAcademica;
	}
	
	@Transactional
	public void excluir(FormacaoAcademica formacaoAcademica) {
		hibernateUtil.delete(formacaoAcademica);
	}
	
}
