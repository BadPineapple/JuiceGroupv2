package ilion.arquivo.taglibs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.util.Uteis;

public class ArquivoInferiorTag extends TagSupport {

	static Logger logger = Logger.getLogger(ArquivoInferiorTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String obj;
	private String cssClass = "";
	private String downloadsComoLista;
	private String table;
	private String largura;

	public void setObj(String obj) {
		this.obj = obj;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setDownloadsComoLista(String downloadsComoLista) {
		this.downloadsComoLista = downloadsComoLista;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}


	@SuppressWarnings("unchecked")
	public int doStartTag() {
		Object object = pageContext.findAttribute(obj);

		if(object == null)
			return SKIP_BODY;

		String conteudoInfo;

		if(object instanceof Map) {
			HashMap map = (HashMap) object;

			conteudoInfo = (String) map.get("conteudoInfo");			
		} else {
			conteudoInfo = (String) Uteis.getRetornoMetodoReflection(object, "getConteudoInfo");
		}

		Integer larguraInt = Uteis.converterInteger(largura);
		
		JspWriter out = pageContext.getOut();
		
		try {
			if("true".equals(table)) {
				UteisTag.arquivoInferiorTable(conteudoInfo, larguraInt, conteudoInfo, out);
			} else {
				UteisTag.arquivoInferior(conteudoInfo, cssClass, "true".equals(downloadsComoLista), larguraInt, out);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}
}