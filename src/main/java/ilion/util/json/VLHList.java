package ilion.util.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ilion.util.Uteis;

public class VLHList {
	
	private List lista;
	
	private Map<String, Object> params;
	
	private Integer pagingPage;
	
	private Integer pagingNumberPer;
	
	private Integer totalNumberOfPages;
	
	private Integer totalNumberOfEntries;

	public VLHList() {
		super();
		params = new HashMap<String, Object>();
	}

	public List getLista() {
		return lista;
	}

	public void setLista(List lista) {
		this.lista = lista;
	}

	public Integer getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(Integer pagingPage) {
		this.pagingPage = pagingPage;
	}
	
	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public Integer getTotalNumberOfEntries() {
		return totalNumberOfEntries;
	}

	public void setTotalNumberOfEntries(Integer totalNumberOfEntries) {
		this.totalNumberOfEntries = totalNumberOfEntries;
		
		this.totalNumberOfPages = (totalNumberOfEntries/pagingNumberPer)+1;
	}

	public Integer getPagingNumberPer() {
		return pagingNumberPer;
	}

	public void setPagingNumberPer(Integer pagingNumberPer) {
		this.pagingNumberPer = pagingNumberPer;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public static VLHList fromRequest(HttpServletRequest request) {
		
		VLHList vlhList = new VLHList();
		
		vlhList.setPagingPage( Uteis.converterInteger(request.getParameter("pagingPage")) );
		vlhList.setPagingNumberPer( Uteis.converterInteger(request.getParameter("pagingNumberPer")) );
		
		for (Iterator iterator = request.getParameterMap().keySet().iterator(); iterator.hasNext();) {
			String param = (String) iterator.next();
			String valor = request.getParameter(param);
			
			vlhList.getParams().put(param, Uteis.decode(valor));
		}
		
		return vlhList;
	}
	
	public Object getParam(String key) {
		if( params == null ) {
			return null;
		}
		
		return params.get(key);
	}
}