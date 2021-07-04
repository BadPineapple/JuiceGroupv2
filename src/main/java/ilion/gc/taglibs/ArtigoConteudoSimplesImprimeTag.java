package ilion.gc.taglibs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.GCSiteNegocio;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;

public class ArtigoConteudoSimplesImprimeTag extends TagSupport {

	private static final long serialVersionUID = 1L;


	static Logger logger = Logger.getLogger(ArtigoConteudoListaTag.class);

	private Object obj;
	private String layout;

	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {

		GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);

		if(obj == null) {
			return SKIP_BODY;
		}

		String conteudoInfo = null;

		if(obj instanceof Map) {
			HashMap map = (HashMap) obj;

			conteudoInfo = (String) map.get("conteudoInfo");
		} else {
			conteudoInfo = (String) Uteis.getRetornoMetodoReflection(obj, "getConteudoInfo");
		}

		Long idObjeto = (Long) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, Long.class);

		if(idObjeto == null) {
			return SKIP_BODY;
		}

		StringBuilder colunas = new StringBuilder("texto;");

		List<Map<String, Object>> artigoConteudos = gcSiteNegocio.listarArtigoConteudoSite(new Artigo(idObjeto), layout, colunas);

		JspWriter out = pageContext.getOut();
		try {

			if(artigoConteudos != null && ! artigoConteudos.isEmpty()) {

				for (Map<String, Object> artigoConteudoMap : artigoConteudos) {
					String texto = (String) artigoConteudoMap.get("texto");
					out.append(texto);
				}
			}
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
}