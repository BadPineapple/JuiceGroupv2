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
public class EnderecoNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public EnderecoAtendimento consultarPorPessoa(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(EnderecoAtendimento.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		EnderecoAtendimento enderecoAtendimento = (EnderecoAtendimento) hibernateUtil.consultarUniqueResult(dc);
		if (enderecoAtendimento == null) {
			return new EnderecoAtendimento();
		}
		return enderecoAtendimento;
	}
	
	@Transactional
	public List<EnderecoAtendimento> validarItens(List<EnderecoAtendimento> listEnderecoAtendimento , Profissional profissional) throws Exception{
		
		List<EnderecoAtendimento> enderecoAtendimento = consultarEnderecoPorPessoa(profissional.getId());
		
		List<EnderecoAtendimento> itensDuplicados = enderecoAtendimento.stream()
	        .filter( o1 -> {
	            return listEnderecoAtendimento.stream()
	                    .map(EnderecoAtendimento::getId)
	                    .anyMatch(i2 -> i2.equals(o1.getId()));
	        }).collect(Collectors.toList());
				
		enderecoAtendimento.removeAll(itensDuplicados);
		
		enderecoAtendimento.forEach(arquivoExcluir-> {
			try {
				excluir(arquivoExcluir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		if (!listEnderecoAtendimento.isEmpty()) {
			listEnderecoAtendimento.forEach(endereco -> {
  			  try {
  				  endereco.setProfissional(profissional);
  				  incluirAtualizar(endereco);
  			  } catch (Exception e) {
  				  e.printStackTrace();
  			  }
  		  });
  	  }
		
		return listEnderecoAtendimento;
	}
	
	@Transactional
	public EnderecoAtendimento incluirAtualizar(EnderecoAtendimento enderecoAtendimento) {
		if (enderecoAtendimento.getId() == null || enderecoAtendimento.getId() == 0) {
			enderecoAtendimento = (EnderecoAtendimento) hibernateUtil.save(enderecoAtendimento);
		} else {
			hibernateUtil.update(enderecoAtendimento);
		}
		return enderecoAtendimento;
	}
	
	public List<EnderecoAtendimento> consultarEnderecoPorPessoa(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(EnderecoAtendimento.class);
		dc.createAlias("profissional", "p");
		dc.add(Restrictions.eq("p.id", idProfissional));
		return (List<EnderecoAtendimento>) hibernateUtil.list(dc);
	}
	
	@Transactional
	public void excluir(EnderecoAtendimento enderecoAtendimento) {
		hibernateUtil.delete(enderecoAtendimento);
	}
}
