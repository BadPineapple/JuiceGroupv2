package ilion.util.persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ilion.util.ValueListImpl;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

@Repository
@Transactional(readOnly = true)
public class HibernateUtil {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("rawtypes")
	public Object findById(Class entityClass, Serializable id) {
		return (Object) getCurrentSession().get(entityClass, id);
	}

	public Object save(Object e) {

		Session session = getCurrentSession();

		Serializable id = session.save(e);
		session.flush();

		return findById(e.getClass(), id);
	}

	public Object saveEmpresa(Object e, boolean isLast) {

		Session session = getCurrentSession();

		Serializable id = session.save(e);

		if (isLast) {
			session.flush();
		}

		return findById(e.getClass(), id);
	}

	public Serializable saveReturnId(Object e) {

		Session session = getCurrentSession();

		Serializable id = session.save(e);

		return id;
	}

	public void update(Object e) {
		getCurrentSession().flush();
		getCurrentSession().clear();
		getCurrentSession().update(e);
	}

	public void delete(Object e) {

		getCurrentSession().delete(e);
	}

	public Integer createQueryUpdate(String hql) {

		Query query = getCurrentSession().createQuery(hql);

		return query.executeUpdate();
	}

	public List listAll(Class clazz) {

		String hql = " from " + clazz.getSimpleName();

		return getCurrentSession().createQuery(hql).list();
	}

	public List listHQL(String hql) {

		Query query = getCurrentSession().createQuery(hql);

		return query.list();
	}

	public List listHQL(String hql, ResultTransformer resultTransformer) {

		Query query = getCurrentSession().createQuery(hql);
		if (resultTransformer != null) {
			query.setResultTransformer(resultTransformer);
		}

		return query.list();
	}

	public List listSQL(String sql) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		return sqlQuery.list();
	}

	// public Object uniqueResultSQL(String sql) {
	//
	// SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
	// sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	//
	// return sqlQuery.uniqueResult();
	// }

	public Object createSQLQueryUniqueResult(String hql) throws HibernateException {
		Object o;

		Session s = getCurrentSession();
		o = s.createSQLQuery(hql).uniqueResult();

		return o;
	}

	public Object uniqueResult(DetachedCriteria dc) {
		Session s = getCurrentSession();
		// Session s = SessionFactoryUtils.getSession(sessionFactory, false);

		Criteria criteria = dc.getExecutableCriteria(s);

		return criteria.uniqueResult();
	}

	public List<?> list(DetachedCriteria dc) {
		return list(dc, null);
	}

	public List<?> list(DetachedCriteria dc, Integer qtdResultados) {
		Session s = getCurrentSession();

		Criteria c = dc.getExecutableCriteria(s);

		if (qtdResultados != null) {
			c.setMaxResults(qtdResultados);
		}

		return c.list();
	}

	public List<?> list(DetachedCriteria dc, Integer firstResult, Integer maxResults) {
		Session s = getCurrentSession();

		Criteria c = dc.getExecutableCriteria(s);

		c.setFirstResult(firstResult);
		c.setMaxResults(maxResults);

		return c.list();
	}

	public Object findFirstResult(DetachedCriteria dc) {
		List<?> rows = list(dc, 1);

		return !rows.isEmpty() ? rows.get(0) : null;
	}

	// public VLHList list(DetachedCriteria dc, Order order, Integer pagingPage,
	// Integer pagingNumberPer) {
	// Session s = getCurrentSession();
	//
	// Criteria criteria = dc.getExecutableCriteria(s);
	//
	// Long qtdTotal = (Long)
	// criteria.setProjection(Projections.rowCount()).uniqueResult();
	// criteria.setProjection(null);
	//
	// criteria.setFirstResult((pagingPage - 1) * pagingNumberPer);
	// criteria.setMaxResults(pagingNumberPer);
	//
	// criteria.addOrder(order);
	//
	// criteria.setResultTransformer(Criteria.ROOT_ENTITY);
	//
	// return new VLHList(criteria.list(), qtdTotal);
	// }

	// public VLHList list(DetachedCriteria dc, Order order, VLHList vlhList) {
	// Session s = getCurrentSession();
	//
	// Criteria criteria = dc.getExecutableCriteria(s);
	//
	// Long qtdTotal = (Long)
	// criteria.setProjection(Projections.rowCount()).uniqueResult();
	// vlhList.setTotalNumberOfEntries(qtdTotal);
	// criteria.setProjection(null);
	//
	// Integer pagingPage = vlhList.getPagingPage();
	// Integer pagingNumberPer = vlhList.getPagingNumberPer();
	//
	// criteria.setFirstResult((pagingPage - 1) * pagingNumberPer);
	// criteria.setMaxResults(pagingNumberPer);
	//
	// criteria.addOrder(order);
	//
	// criteria.setResultTransformer(Criteria.ROOT_ENTITY);
	//
	// vlhList.setLista(criteria.list());
	//
	// return vlhList;
	// }

	public List listar(DetachedCriteria detachedCriteria, Integer maxResults, Integer firstResult, CharSequence colunas)
			throws HibernateException {
		if (colunas != null && colunas.length() != 0) {
			ProjectionList p = formarProjetionsList(colunas.toString());

			detachedCriteria.setProjection(p);
			detachedCriteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		}

		Session s = getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(s);
		if (maxResults != null && maxResults.intValue() != 0) {
			criteria.setMaxResults(maxResults.intValue());
		}

		if (firstResult != null) {
			criteria.setFirstResult(firstResult);
		}

		return criteria.list();
	}
	
	public List listarColumn(DetachedCriteria detachedCriteria, CharSequence colunas)
			throws HibernateException {
		if (colunas != null && colunas.length() != 0) {
			ProjectionList p = formarProjetionsList(colunas.toString());
			detachedCriteria.setProjection(p);
		}
		
		Session s = getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(s);
		return criteria.list();
	}

	public ValueList consultarValueList(DetachedCriteria detachedCriteria, Order orderBy, ValueListInfo valueListInfo) {
		return consultarValueList(detachedCriteria, orderBy, null, valueListInfo);
	}

	public ValueList consultarValueList(DetachedCriteria detachedCriteria, Order orderBy, String colunas,
			ValueListInfo valueListInfo) {
		Session s = getCurrentSession();

		Criteria criteria = detachedCriteria.getExecutableCriteria(s);

		Number qtd = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);

		if (qtd != null)
			valueListInfo.setTotalNumberOfEntries(qtd.intValue());

		criteria = configOrder(orderBy, valueListInfo, criteria);

		if (!valueListInfo.isAllPages()) {
			criteria.setFirstResult((valueListInfo.getPagingPage() - 1) * valueListInfo.getPagingNumberPer());
			criteria.setMaxResults(valueListInfo.getPagingNumberPer());
		}

		configurarColunas(criteria, colunas);

		List result = criteria.list();

		return new ValueListImpl(result, valueListInfo);
	}

	public ValueList consultarValueList(DetachedCriteria detachedCriteria, List<Order> ordersBy, String colunas,
			ValueListInfo valueListInfo) {
		Session s = getCurrentSession();

		Criteria criteria = detachedCriteria.getExecutableCriteria(s);

		Number qtd = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);

		if (qtd != null)
			valueListInfo.setTotalNumberOfEntries(qtd.intValue());

		criteria = configOrder(ordersBy, valueListInfo, criteria);

		if (!valueListInfo.isAllPages()) {
			criteria.setFirstResult((valueListInfo.getPagingPage() - 1) * valueListInfo.getPagingNumberPer());
			criteria.setMaxResults(valueListInfo.getPagingNumberPer());
		}

		configurarColunas(criteria, colunas);

		List result = criteria.list();

		return new ValueListImpl(result, valueListInfo);
	}

	public ValueList consultarValueList(DetachedCriteria detachedCriteria, List<Order> ordersBy,
			ValueListInfo valueListInfo) {
		Session s = getCurrentSession();

		Criteria criteria = detachedCriteria.getExecutableCriteria(s);

		Number qtd = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);

		if (qtd != null) {
			valueListInfo.setTotalNumberOfEntries(qtd.intValue());
		}

		for (Order order : ordersBy) {
			criteria.addOrder(order);
		}

		if (!valueListInfo.isAllPages()) {
			criteria.setFirstResult((valueListInfo.getPagingPage() - 1) * valueListInfo.getPagingNumberPer());
			criteria.setMaxResults(valueListInfo.getPagingNumberPer());
		}

		criteria.setResultTransformer(Criteria.ROOT_ENTITY);

		List result = criteria.list();

		return new ValueListImpl(result, valueListInfo);
	}

	private void configurarColunas(Criteria criteria, String colunas) {
		if (colunas != null && colunas.length() != 0) {
			ProjectionList p = formarProjetionsList(colunas);

			criteria.setProjection(p);
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		} else {
			criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		}
	}

	public ProjectionList formarProjetionsList(String colunas) {
		ProjectionList p = Projections.projectionList();

		StringTokenizer st = new StringTokenizer(colunas, ";");
		while (st.hasMoreElements()) {
			String coluna = (String) st.nextElement();
			p.add(Projections.property(coluna), coluna);
		}

		return p;
	}

	public static Criteria configOrder(List<Order> ordersBy, ValueListInfo valueListInfo, Criteria criteria) {
		String ordem = valueListInfo.getSortingColumn();
		if (ordem != null && !ordem.trim().equals("")) {
			String tipoOrdem = valueListInfo.getSortingDirectionName();
			if (tipoOrdem != null && !tipoOrdem.trim().equals("")) {
				if (tipoOrdem.trim().equals("asc"))
					criteria.addOrder(Order.asc(ordem));
				else
					criteria.addOrder(Order.desc(ordem));
			}
		} else if (ordersBy != null && !ordersBy.isEmpty()) {
			for (Order order : ordersBy) {
				criteria.addOrder(order);
			}
		}
		return criteria;
	}

	public Criteria configOrder(Order orderBy, ValueListInfo valueListInfo, Criteria criteria) {
		String ordem = valueListInfo.getSortingColumn();
		if (ordem != null && !ordem.trim().equals("")) {
			String tipoOrdem = valueListInfo.getSortingDirectionName();
			if (tipoOrdem != null && !tipoOrdem.trim().equals("")) {
				if (tipoOrdem.trim().equals("asc"))
					criteria.addOrder(Order.asc(ordem));
				else
					criteria.addOrder(Order.desc(ordem));
			}
		} else if (orderBy != null) {
			criteria.addOrder(orderBy);
		}
		return criteria;
	}

	public Order obterOrderBy(String orderBy) {
		Order order = null;
		if (orderBy != null && !orderBy.equals("")) {
			if (orderBy.contains("desc")) {
				orderBy = orderBy.substring(0, orderBy.indexOf("desc")).trim();
				order = Order.desc(orderBy);
			} else {
				if (orderBy.contains("asc"))
					orderBy = orderBy.substring(0, orderBy.indexOf("asc")).trim();

				order = Order.asc(orderBy);
			}
		}
		return order;
	}

	public Number consultarQtd(DetachedCriteria dc) {
		Session s = getCurrentSession();

		Criteria criteria = dc.getExecutableCriteria(s);

		criteria.setProjection(Projections.rowCount());
		return (Number) criteria.uniqueResult();
	}

	public Boolean possuiRegistros(DetachedCriteria dc) {
		Number qtd = consultarQtd(dc);

		return qtd != null && qtd.longValue() > 0;
	}

	public Boolean possuiRegistros(Class clazz) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);

		Long qtd = consultarQtd(dc).longValue();

		return qtd != null && qtd > 0;
	}

	public Integer updateHQL(String hql) {

		Session session = getCurrentSession();

		Query query = session.createQuery(hql);

		return query.executeUpdate();
	}

	public Integer updateSQL(String sql) {

		Session session = getCurrentSession();

		SQLQuery query = session.createSQLQuery(sql);

		return query.executeUpdate();
	}

	public List<Map<String, Object>> listarSQLMap(String sql) throws HibernateException {

		SQLDoWork work = new SQLDoWork(sql);

		Session s = getCurrentSession();

		s.doWork(work);

		return work.getRows();
	}

	public List listarSQL(Class clazz, String sql) throws HibernateException {

		TypedSQLDoWork work = new TypedSQLDoWork(clazz, sql);

		Session s = getCurrentSession();

		s.doWork(work);

		return work.getRows();
	}

	public List<Object> listarSQLUniqueColumn(String sql) throws HibernateException {

		SQLDoWork work = new SQLDoWork(sql);

		Session s = getCurrentSession();

		s.doWork(work);

		return work.getRowsUniqueColumn();
	}

	public Object uniqueResultSQL(String sql) throws HibernateException {

		SQLDoWork work = new SQLDoWork(sql);

		Session s = getCurrentSession();

		s.doWork(work);

		return work.uniqueResult();

	}

	public Map<String, Object> uniqueResultMapSQL(String sql) throws HibernateException {

		SQLDoWork work = new SQLDoWork(sql);

		Session s = getCurrentSession();

		s.doWork(work);

		return work.uniqueResultMap();

	}

	public Object consultarUniqueResult(DetachedCriteria detachedCriteria) throws HibernateException {
		return consultarUniqueResult(detachedCriteria, null);
	}

	public Object consultarUniqueResult(DetachedCriteria detachedCriteria, String colunas) throws HibernateException {
		Session s = getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(s);
		configurarColunas(criteria, colunas);
		Object obj = criteria.uniqueResult();

		return obj;
	}

	public List buscar(DetachedCriteria detachedCriteria, Integer pagingPage, Integer maxResults) {
		
		Integer firstResult = (pagingPage - 1) * maxResults;
		
		Session s = getCurrentSession();
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(s);
				
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		
		List result = criteria.list();
		
		return result;
	}

}
