package ilion.admin.negocio;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.util.json.GSONUteis;
import ilion.util.persistencia.HibernateUtil;

@Service
public class PerfilNegocio {
	
	//private Logger logger = Logger.getLogger(PerfilNegocio.class);
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
//	@Autowired
//	GCNegocio gcNegocio;
	
	public Object consultar(Class clazz, Long id) {
		return hibernateUtil.findById(clazz, id);
	}
	
	public Perfil consultarPorIdComCategorias(Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(Perfil.class);
		dc.setFetchMode("categorias", FetchMode.JOIN);
		dc.add(Restrictions.eq("id", id));
		return (Perfil) hibernateUtil.uniqueResult(dc);
	}

	public Object inserir(Object object) {
		return hibernateUtil.save(object);
	}

	public void atualizar(Object object) {
		hibernateUtil.update(object);
	}

	public void excluir(Object object) {
		hibernateUtil.delete(object);
	}
	
	@SuppressWarnings("unchecked")
	public List<Perfil> listarPerfis() {
		DetachedCriteria dc = DetachedCriteria.forClass(Perfil.class);
		dc.addOrder(Order.asc("nome"));
		
		return hibernateUtil.listAll(Perfil.class);
	}

	public void validarPerfil(Perfil perfil, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome n�o pode ser vazio.");

	}
	
	@Transactional
	@CacheEvict(value={"gc.grupos.por.perfil"},allEntries=true)
	public void incluirAtualizar(Perfil perfil) {
		
		List<String> permissoes = perfil.getPermissoes();
		String permissoesJson = GSONUteis.getInstance().toJson(permissoes);
		perfil.setPermissoesJson(permissoesJson);
		
		if(perfil.getId() == null) {
			perfil = (Perfil) hibernateUtil.save(perfil);
		} else {
			hibernateUtil.update(perfil);
		}
		
	}
}
