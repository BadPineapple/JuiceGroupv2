package ilion.contato.negocio;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class ContatoGrupoNegocio {
	
	static Logger logger = Logger.getLogger(ContatoGrupoNegocio.class);
	
	@Autowired
	protected HibernateUtil hibernateUtil;
	
	//@CacheEvict("contato.grupos.com.qtd")
	public Object consultar(Class clazz, Long id) {
        return hibernateUtil.findById(clazz, id);
    }
	
	public void validarGrupo(ContatoGrupo grupoContato, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "nome", "", "Nome não pode ser vazio.");

		if( possuiGrupo(grupoContato) ) {
			bindingResult.rejectValue("nome", "", "Grupo já existente.");
		}
	}
	
	public List<Map<String, Object>> listarGruposComQtd() {
		return listarGruposComQtd(false);
	}
	
	//@Cacheable("contato.grupos.com.qtd")
	public List<Map<String, Object>> listarGruposComQtd(Boolean somentePermissaoNewsletter) {
		
		String sql = " select ";
		sql+= " contatogrupo.*,  ";
		sql+= " (case when qtd_contatos.qtd is not null then qtd_contatos.qtd else 0 end) as qtd ";
		sql+= " from contatogrupo ";
		sql+= " left join (select grupo_id, count(*) as qtd ";
		sql+= " 		from contatoxgrupo  ";
		sql+= " 		join contato on contato.id=contatoxgrupo.contato_id ";
		sql+= " 		where 1=1  ";
		
		if( somentePermissaoNewsletter ) {
			sql+= " 		and contato.permissaoemail=true ";
		}

		sql+= " 		group by grupo_id) as qtd_contatos on qtd_contatos.grupo_id=contatogrupo.id ";
		sql+= " order by nome ";
		
		return (List<Map<String, Object>>) hibernateUtil.listarSQLMap(sql);
	}
	
	public Boolean possuiGrupo(ContatoGrupo grupoContato) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoGrupo.class);
		dc.add(Restrictions.eq("nome", grupoContato.getNome()));

		if( grupoContato.getId() != null ) {
			dc.add(Restrictions.not(Restrictions.eq("id", grupoContato.getId())));
		}

		return hibernateUtil.possuiRegistros(dc);
	}

	public Integer qtdContatosXGrupo(ContatoGrupo grupoContato, Boolean somentePermissaoEmail) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("grupo", "t");
		dc.add(Restrictions.eq("t.id", grupoContato.getId()));

		if(somentePermissaoEmail) {
			dc.createAlias("contato", "c");
			dc.add(Restrictions.eq("c.permissaoEmail", true));
		}

		return hibernateUtil.consultarQtd(dc).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<String> listarNomesGrupos(Contato contato) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("contato", "c");
		dc.add(Restrictions.eq("c.id", contato.getId()));

		dc.createAlias("grupo", "t");
		dc.setProjection(Projections.property("t.nome"));

		return (List<String>) hibernateUtil.list(dc);
	}
	
	@SuppressWarnings("unchecked")
	public List<ContatoXGrupo> listarContatoXGrupo(ContatoGrupo grupoContato, Integer qtd) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("contato", "c");
		dc.createAlias("grupo", "t");

		dc.add(Restrictions.eq("t.id", grupoContato.getId()));
		dc.addOrder(Order.asc("c.nome"));

		return (List<ContatoXGrupo>) hibernateUtil.list(dc, qtd);
	}

	public String formatarGrupos(Contato contato) {
		if(contato == null) {
			return "";
		}

		List<String> grupos = listarNomesGrupos(contato);

		StringBuilder sb = new StringBuilder();
		
		// Só adiciona vírgula caso possuir mais que 1 grupo.
		if(grupos != null && grupos.size() > 0) {
			if (grupos.size() > 1) {
				for (String t : grupos) {
					sb.append(t).append(", ");
				}
			} else {
				sb.append(grupos.get(0));
			}
		}

		return sb.toString();
	}
	
	@Transactional
	@CacheEvict(value={
			"contato.grupos.com.qtd"
	},allEntries=true)
	public void transferirContatos(ContatoGrupo grupoContatoOrigem, ContatoGrupo grupoContatoDestino, String qtd) {
		if("todos".equals(qtd)) {
			transferirContatosTodos(grupoContatoOrigem, grupoContatoDestino);
		} else {
			Integer qtdInt = Uteis.converterInteger(qtd);

			if(qtdInt != null) {
				transferirContatos(grupoContatoOrigem, grupoContatoDestino, qtdInt);
			}
		}
	}
	
	@CacheEvict(value={
			"contato.grupos.com.qtd"
	},allEntries=true)
	public void transferirContatosTodos(ContatoGrupo grupoContatoOrigem, ContatoGrupo grupoContatoDestino) {
		grupoContatoOrigem = (ContatoGrupo) hibernateUtil.findById(ContatoGrupo.class, grupoContatoOrigem.getId());
		grupoContatoDestino = (ContatoGrupo) hibernateUtil.findById(ContatoGrupo.class, grupoContatoDestino.getId());

		String hqlUpdate = " update "+ContatoXGrupo.class.getName()+" set grupo.id='"+grupoContatoDestino.getId()+"' where grupo.id='"+grupoContatoOrigem.getId()+"' ";

		hibernateUtil.createQueryUpdate(hqlUpdate);
	}
	
	@CacheEvict(value={
			"contato.grupos.com.qtd"
	},allEntries=true)
	public void transferirContatos(ContatoGrupo grupoContatoOrigem, ContatoGrupo grupoContatoDestino, Integer qtd) {
		grupoContatoOrigem = (ContatoGrupo) hibernateUtil.findById(ContatoGrupo.class, grupoContatoOrigem.getId());
		grupoContatoDestino = (ContatoGrupo) hibernateUtil.findById(ContatoGrupo.class, grupoContatoDestino.getId());

		List<ContatoXGrupo> contatoXGrupos = listarContatoXGrupo(grupoContatoOrigem, qtd);

		for (ContatoXGrupo contatoXGrupo : contatoXGrupos) {
			contatoXGrupo.setGrupo(grupoContatoDestino);

			hibernateUtil.update(contatoXGrupo);
		}
	}

	@Transactional
	@CacheEvict(value={
			"contato.grupos.com.qtd"
	},allEntries=true)
	public void incluirAtualizar(ContatoGrupo grupoContato) {
		
		if(grupoContato.getId() == null) {
			grupoContato = (ContatoGrupo) hibernateUtil.save(grupoContato);
		} else {
			hibernateUtil.update(grupoContato);
		}
		
	}
	
	@Transactional
	@CacheEvict(value={
			"contato.grupos.com.qtd"
	},allEntries=true)
	public void excluir(ContatoGrupo contatoGrupo) {
		
		if( existeContatosXGrupo(contatoGrupo) ) {
			throw new ValidacaoException("grupo-possui-contatos");
		}
		
		contatoGrupo = (ContatoGrupo) consultar(ContatoGrupo.class, contatoGrupo.getId());

		hibernateUtil.delete(contatoGrupo);
	}
	
	public Boolean existeContatosXGrupo(ContatoGrupo grupoContato) {
		DetachedCriteria dc = DetachedCriteria.forClass(ContatoXGrupo.class);
		dc.createAlias("grupo", "t");
		dc.add(Restrictions.eq("t.id", grupoContato.getId()));

		return hibernateUtil.possuiRegistros(dc);
	}
}