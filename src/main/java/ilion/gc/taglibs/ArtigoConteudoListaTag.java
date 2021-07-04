package ilion.gc.taglibs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoConteudo;
import ilion.gc.negocio.GCSiteNegocio;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;

public class ArtigoConteudoListaTag extends TagSupport {

	private static final long serialVersionUID = 1L;


	private String obj;
	private String layout;
	private String varRetorno;

	public void setObj(String obj) {
		this.obj = obj;
	}
	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}

	@Override
	public int doStartTag() throws JspException {

		GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);

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

		Long idObjeto = (Long) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, Long.class);

		List<ArtigoConteudo> artigoConteudos = gcSiteNegocio.listarArtigoConteudo(new Artigo(idObjeto), layout);

		pageContext.setAttribute(varRetorno, artigoConteudos);

		return SKIP_BODY;
	}
}