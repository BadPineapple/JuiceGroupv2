package ilion.util.json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ilion.util.Uteis;

public class VLHCriteria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pagingPage;
	
	private Integer pagingNumberPer;
	
	private String sortColumn;
	
	private String sortDirection;
	
	private Map<String, String> params;
	
	public VLHCriteria() {
		super();
		params = new HashMap<String, String>();
	}

	public Integer getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(Integer pagingPage) {
		this.pagingPage = pagingPage;
	}

	public Integer getPagingNumberPer() {
		return pagingNumberPer;
	}

	public void setPagingNumberPer(Integer pagingNumberPer) {
		this.pagingNumberPer = pagingNumberPer;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortDirection() {
		return sortDirection;
	}
	
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	
	public Integer calcularFirstResult() {
		return (pagingPage - 1) * pagingNumberPer;
	}
	
	public static VLHCriteria fromRequest(HttpServletRequest request) {
		VLHCriteria retorno = new VLHCriteria();
		
		retorno.setPagingPage( Uteis.converterInteger(request.getParameter("pagingPage")) );
		retorno.setPagingNumberPer( Uteis.converterInteger(request.getParameter("pagingNumberPer")) );
		retorno.setSortColumn( request.getParameter("sortColumn") );
		retorno.setSortDirection( request.getParameter("sortDirection") );
		
		for (Iterator iter = request.getParameterMap().keySet().iterator(); iter.hasNext();) {
			String k = (String) iter.next();
			String v = request.getParameter(k);
			
			retorno.getParams().put(k, v);
		}
		
		return retorno;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	
}