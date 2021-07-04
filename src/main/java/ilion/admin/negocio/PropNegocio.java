package ilion.admin.negocio;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.Uteis;
import ilion.util.persistencia.HibernateUtil;

@Service
public class PropNegocio {
	
	static Logger logger = Logger.getLogger(PropNegocio.class);
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Cacheable("prop.find.by.id")
	public Prop findById(String id) {
		DetachedCriteria dc = DetachedCriteria.forClass(Prop.class);
		dc.add(Restrictions.eq("id", id));
		
		return (Prop) hibernateUtil.uniqueResult(dc);
	}
	
	@Cacheable("prop.find.by.enum")
	public Prop findByEnum(PropEnum propEnum) {
		DetachedCriteria dc = DetachedCriteria.forClass(Prop.class);
		dc.add(Restrictions.eq("id", propEnum.toString()));
		
		return (Prop) hibernateUtil.uniqueResult(dc);
	}
	
	@Cacheable("prop.find.value.by.enum")
	public String findValueById(PropEnum propEnum) {
		DetachedCriteria dc = DetachedCriteria.forClass(Prop.class);
		dc.add(Restrictions.eq("id", propEnum.toString()));
		
		Prop prop = (Prop) hibernateUtil.uniqueResult(dc);
		
		if( prop == null ) {
			logger.warn("prop não encontrada: "+propEnum);
		}
		
		return prop != null ? prop.getValor() : "";
	}
	
	public Integer findValueIntegerById(PropEnum propEnum, Integer valueDefault) {
		
		String v = findValueById(propEnum);
		
		if( Uteis.ehNuloOuVazio(v) ) {
			return valueDefault;
		}
		
		return Uteis.converterInteger(v);
	}
	
	public Boolean findValueBooleanById(PropEnum propEnum, Boolean valueDefault) {
		
		String v = findValueById(propEnum);
		
		if( Uteis.ehNuloOuVazio(v) ) {
			return valueDefault;
		}
		
		return "true".equals(v);
	}
	
	@Transactional
	@CacheEvict(allEntries=true, value= {
		"prop.find.by.id",
		"prop.find.by.enum",
		"prop.find.value.by.enum",
	})
	public void incluirAtualizar(String id, String valor) {
		
		Prop p = findById(id);
		if(p == null) {
			p = new Prop();
			p.setId(id);
		}

		p.setValor(valor);
		
		if( p.getId() == null ) {
			hibernateUtil.save(p);
		} else {
			hibernateUtil.update(p);
		}
		
	}
	
	public String getUrlSemImagem() {
		
		String urlImages = getUrlAssets("images");
		
		String retorno = urlImages+"sem-imagem.png";
		
		return retorno;
	}
	
	public String getUrlAssets(String pasta) {
		
		String url = findValueById(PropEnum.URL);
		
		return url+"/assets/"+pasta+"/";
	}
}