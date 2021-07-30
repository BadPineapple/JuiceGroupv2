package ilion.vitazure.negocio;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.persistencia.HibernateUtil;
import ilion.vitazure.model.Profissional;

@Service
@SuppressWarnings("unchecked")
public class ProfissionalNegocio {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	public Profissional consultarPorPessoa(Long idPessoa) {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.createAlias("pessoa", "p");
		dc.add(Restrictions.eq("p.id", idPessoa));
		Profissional profissional = (Profissional) hibernateUtil.consultarUniqueResult(dc);
		if (profissional == null) {
			return new Profissional();
		}
		return profissional;
	}
	
	@Transactional
	public Profissional incluirAtualizar(Profissional profissional) throws Exception{
		if (profissional.getId() == null || profissional.getId() == 0) {
			profissional = (Profissional) hibernateUtil.save(profissional);
		} else {
			hibernateUtil.update(profissional);
		}
		return profissional;
	}
	
	public List<Profissional> consultarProfissionais() {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		return (List<Profissional>) hibernateUtil.list(dc);
	}
	
	public Profissional consultarPorId(Long idProfissional) {
		DetachedCriteria dc = DetachedCriteria.forClass(Profissional.class);
		dc.add(Restrictions.eq("id", idProfissional));
		Profissional profissional = (Profissional) hibernateUtil.consultarUniqueResult(dc);
		if (profissional == null) {
			return new Profissional();
		}
		return profissional;
	}
	
}
