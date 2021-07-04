package ilion.terrafos.relatorios.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.util.persistencia.HibernateUtil;

@Service
public class LogDeAcaoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Transactional
	public void incluir(LogDeAcao logDeAcao) {
		
		hibernateUtil.save(logDeAcao);
		
	}
	
	public List<LogDeAcao> buscar(Fazenda fazenda, Date dtInicio, Date dtFim) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(LogDeAcao.class);
		dc.createAlias("pasto", "p");
		dc.createAlias("p.retiro", "r");
		dc.add(Restrictions.eq("r.fazenda", fazenda));
		
		dc.addOrder(Order.desc("dataCriacao"));
		
		return (List<LogDeAcao>) hibernateUtil.list(dc);
	}
}
