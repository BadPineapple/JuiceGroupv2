package ilion.arquivo.taglibs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.util.Uteis;

public class ArquivoLateralTag extends TagSupport {

	static Logger logger = Logger.getLogger(ArquivoLateralTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String obj;
	private String cssClass = "";
	private String table;
	
	
	public void setObj(String obj) {
		this.obj = obj;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
	
	@SuppressWarnings("unchecked")
	public int doStartTag() {
		Object objeto = pageContext.findAttribute(obj);
		
		if(objeto == null)
			return SKIP_BODY;

		String conteudoInfo;
		
		if(objeto instanceof Map) {
			HashMap map = (HashMap) objeto;
			
			conteudoInfo = (String) map.get("conteudoInfo");			
		} else {
			conteudoInfo = (String) Uteis.getRetornoMetodoReflection(objeto, "getConteudoInfo");
		}
		
		JspWriter out = pageContext.getOut();
		
		try {
			
			if("true".equals(table)) {
				UteisTag.arquivoLateralTable(conteudoInfo, conteudoInfo, out);
			} else {
				UteisTag.arquivoLateral(conteudoInfo, cssClass, out);
			}
			
		} catch (IOException e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
}
