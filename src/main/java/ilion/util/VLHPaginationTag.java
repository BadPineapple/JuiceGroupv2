package ilion.util;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class VLHPaginationTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(VLHPaginationTag.class);
	
	private ValueListInfo valueListInfo;
	private String navCssClass;
	
	public void setValueListInfo(ValueListInfo valueListInfo) {
		this.valueListInfo = valueListInfo;
	}
	public void setNavCssClass(String navCssClass) {
		this.navCssClass = navCssClass;
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			JspWriter out = pageContext.getOut();
			
			out.print("<nav class=\""+navCssClass+"\" aria-label=\"Page navigation\">");
			out.print("  <ul class=\"pagination pagination-sm\">");
			out.print("    <li class=\"disabled\">");
			out.print("      <a href=\"javascript:;\">");
			out.print("        "+valueListInfo.getTotalNumberOfEntries()+" item(ns)");
			out.print("      </a>");
			out.print("    </li>");
			out.print("    <li class=\""+cssClass("first")+"\">");
			out.print("      <a href=\""+linkFirst()+"\" aria-label=\"First\">");
			out.print("        <span aria-hidden=\"true\">&laquo;</span>");
			out.print("        <span aria-hidden=\"true\">&laquo;</span>");
			out.print("      </a>");
			out.print("    </li>");
			out.print("    <li class=\""+cssClass("previews")+"\">");
			out.print("      <a href=\""+linkPreviews()+"\" aria-label=\"Previous\">");
			out.print("        <span aria-hidden=\"true\">&laquo;</span>");
			out.print("      </a>");
			out.print("    </li>");
			
			List<Integer> pages = GeneratePagesVH
					.create()
					.withPagingPage(valueListInfo.getPagingPage())
					.withTotalNumberOfPages(valueListInfo.getTotalNumberOfPages())
					.build();
			
			for (Integer page : pages) {
				
				String cssClass = "";
				
				if( page == valueListInfo.getPagingPage() ) {
					cssClass = "active";
				}
				
				out.print("    <li class=\""+cssClass+"\"><a href=\""+linkToPage(page)+"\">"+page+"</a></li>");
			}
			
			out.print("    <li class=\""+cssClass("next")+"\">");
			out.print("      <a href=\""+linkNext()+"\" aria-label=\"Next\">");
			out.print("        <span aria-hidden=\"true\">&raquo;</span>");
			out.print("      </a>");
			out.print("    </li>");
			out.print("    <li class=\""+cssClass("last")+"\">");
			out.print("      <a href=\""+linkLast()+"\" aria-label=\"Last\">");
			out.print("        <span aria-hidden=\"true\">&raquo;</span>");
			out.print("        <span aria-hidden=\"true\">&raquo;</span>");
			out.print("      </a>");
			out.print("    </li>");
			out.print("  </ul>");
			out.print("</nav>");
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
	
	public String cssClass(String tipo) {
		
		if( tipo.equals("first") ) {
			return valueListInfo.getPagingPage() == 1 ? "disabled" : "";
		}
		
		if( tipo.equals("previews") ) {
			return valueListInfo.getPagingPage() == 1 ? "disabled" : "";
		}
		
		if( tipo.equals("next") ) {
			return valueListInfo.getPagingPage() == valueListInfo.getTotalNumberOfPages() ? "disabled" : "";
		}
		
		if( tipo.equals("last") ) {
			return valueListInfo.getPagingPage() == valueListInfo.getTotalNumberOfPages() ? "disabled" : "";
		}
		
		return "";
	}
	
	public String linkFirst() {
		
		if( valueListInfo.getPagingPage() == 1 ) {
			return "javascript:;";
		}
		
		String link = "?sp=true&pagingPage=1&";
		
		link += concatenarParams();
		
		return link;
	}
	
	public String linkPreviews() {
		
		if( valueListInfo.getPagingPage() == 1 ) {
			return "javascript:;";
		}
		
		Integer page = valueListInfo.getPagingPage();
		
		if( page > 1 ) {
			page = page - 1;
		}
		
		String link = "?sp=true&pagingPage="+page+"&";
		
		link += concatenarParams();
		
		return link;
	}
	
	public String linkNext() {
		
		if( valueListInfo.getPagingPage() == valueListInfo.getTotalNumberOfPages() ) {
			return "javascript:;";
		}
		
		Integer page = valueListInfo.getPagingPage();
		
		if( valueListInfo.getTotalNumberOfPages() != page ) {
			page = page + 1;
		}
		
		String link = "?sp=true&pagingPage="+page+"&";
		
		link += concatenarParams();
		
		return link;
	}
	
	public String linkLast() {
		
		if( valueListInfo.getPagingPage() == valueListInfo.getTotalNumberOfPages() ) {
			return "javascript:;";
		}
		
		String link = "?sp=true&pagingPage="+valueListInfo.getTotalNumberOfPages()+"&";
		
		link += concatenarParams();
		
		return link;
	}
	
	public String linkToPage(Integer page) {
		
		String link = "?sp=true&pagingPage="+page+"&";
		
		link += concatenarParams();
		
		return link;
	}
	
	private String concatenarParams() {
		
		String link = "";
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		for (Iterator iter = request.getParameterMap().keySet().iterator(); iter.hasNext();) {
			String k = (String) iter.next();
			
			if("pagingPage".equals(k)) {
				continue;
			}
			
			link += k+"="+request.getParameter(k)+"&";
		}
		
		return link;
	}
}
