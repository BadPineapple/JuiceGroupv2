package ilion.util.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;

import ilion.util.Uteis;

@SuppressWarnings("rawtypes")
public class TypedSQLDoWork implements Work {
	
	static Logger logger = Logger.getLogger(TypedSQLDoWork.class);
	
	private Class clazz;
	
	private String sql;
	
	private List rows;
	
	public TypedSQLDoWork(Class clazz, String sql) {
		super();
		this.clazz= clazz;
		this.sql = sql;
	}
	
	public void execute(Connection c) throws SQLException {
		
		rows = new ArrayList<>();
		
		try {
			
			Statement stat = c.createStatement();

			ResultSet resultSet = stat.executeQuery(sql);

			ResultSetMetaData md = resultSet.getMetaData();

			Map<String, String> columnLabelsMap = new HashMap<String, String>();
			for (int i = 1; i <= md.getColumnCount(); i++) {
				columnLabelsMap.put(md.getColumnLabel(i), md.getColumnTypeName(i));
			}

			while(resultSet.next()) {
				Object object = clazz.newInstance();
				for (String columnLabel : columnLabelsMap.keySet()) {
					Object valor = resultSet.getObject(columnLabel);
					
					Uteis.set(object, columnLabel, valor);
				}
				rows.add(object);
			}
			
			resultSet.close();
			stat.close();
			
		} catch (Exception e) {
			String m = "erro: "+sql;
			logger.error(m, e);
		}
		
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getRows() {
		return rows;
	}
	
	public Object uniqueResult() {
		if( rows == null || rows.isEmpty() ) {
			return null;
		}
		
		Object row = rows.get(0);
		
		return row;
	}
}