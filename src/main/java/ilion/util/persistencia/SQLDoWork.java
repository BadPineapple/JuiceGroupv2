package ilion.util.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;

public class SQLDoWork implements Work {
	
	static Logger logger = Logger.getLogger(SQLDoWork.class);
	
	private String sql;
	
	private List<Map<String, Object>> rows;
	
	public SQLDoWork(String sql) {
		super();
		this.sql = sql;
	}
	
	public void execute(Connection c) throws SQLException {
		
		rows = new ArrayList<Map<String, Object>>();
		
		try {
			
			Statement stat = c.createStatement();

			ResultSet resultSet = stat.executeQuery(sql);

			ResultSetMetaData md = resultSet.getMetaData();

			Map<String, String> columnLabelsMap = new HashMap<String, String>();
			for (int i = 1; i <= md.getColumnCount(); i++) {
				columnLabelsMap.put(md.getColumnLabel(i), md.getColumnTypeName(i));
			}

			while(resultSet.next()) {
				Map<String, Object> map = new TreeMap<String, Object>();
				for (String columnLabel : columnLabelsMap.keySet()) {
					Object valor = resultSet.getObject(columnLabel);
					
					map.put(columnLabel, valor);
				}
				rows.add(map);
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

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public Map<String, Object> uniqueResultMap() {
		if( rows == null || rows.isEmpty() ) {
			return null;
		}
		
		Map<String, Object> row = rows.get(0);
		
		return row;
	}

	public Object uniqueResult() {
		if( rows == null || rows.isEmpty() ) {
			return null;
		}
		
		Map<String, Object> row = rows.get(0);
		
		if( row.isEmpty() ) {
			return null;
		}
		
		return row.values().iterator().next();
	}
	
	public List<Object> getRowsUniqueColumn() {
		if( rows == null || rows.isEmpty() ) {
			return Collections.emptyList();
		}
		
		List valores = new ArrayList();
		
		for (Map<String, Object> r : rows) {
			String k = r.keySet().iterator().next();
			
			valores.add(r.get(k));
		}
		
		return valores;
	}
}