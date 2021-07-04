package ilion.admin.negocio;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.persistencia.HibernateUtil;

@Service
public class CidadeNegocio {

	static Logger logger = Logger.getLogger(CidadeNegocio.class);

	@Autowired
	private HibernateUtil hibernateUtil;
	
    @Transactional
	@CacheEvict(
			value={"cidades.lista.por.uf", "cidades.nomes.lista.por.uf"},
			allEntries=true
			)
	public Cidade inserir(Cidade cidade) {

		if( jaIncluido(cidade) ) {
			return cidade;
		}

		logger.info("Inserindo cidade: "+cidade.toString());

		return (Cidade) hibernateUtil.save(cidade);
	}

	private Boolean jaIncluido(Cidade cidade) {

		DetachedCriteria dc = DetachedCriteria.forClass(Cidade.class);
		dc.add(Restrictions.eq("id", cidade.getId()));

		return hibernateUtil.possuiRegistros(dc);
	}

	@Cacheable("cidades.lista.por.uf")
	public List<Cidade> listarCidades(UF uf) {
		DetachedCriteria dc = DetachedCriteria.forClass(Cidade.class);
		dc.add(Restrictions.eq("uf", uf));
		dc.addOrder(Order.asc("nome"));

		return (List<Cidade>) hibernateUtil.list(dc);
	}

	@Cacheable("cidades.nomes.lista.por.uf")
	public List<String> listarNomesCidades(UF uf) {
		DetachedCriteria dc = DetachedCriteria.forClass(Cidade.class);
		dc.add(Restrictions.eq("uf", uf));
		dc.addOrder(Order.asc("nome"));

		dc.setProjection(Projections.property("nome"));

		return (List<String>) hibernateUtil.list(dc);
	}


	public Cidade consultar(Long idCidade){
		return (Cidade) hibernateUtil.findById(Cidade.class, idCidade);
	}
}