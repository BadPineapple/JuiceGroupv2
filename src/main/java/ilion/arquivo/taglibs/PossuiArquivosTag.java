package ilion.arquivo.taglibs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ArquivoNegocio.Layout;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;

public class PossuiArquivosTag extends TagSupport {

	static Logger logger = Logger.getLogger(PossuiArquivosTag.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object obj;
	private String layout;
	
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	public int doStartTag() {
		
		if(obj == null)
			return SKIP_BODY;

		String conteudoInfo;
		
		if(obj instanceof Map) {
			HashMap map = (HashMap) obj;
			
			conteudoInfo = (String) map.get("conteudoInfo");			
		} else {
			conteudoInfo = (String) Uteis.getRetornoMetodoReflection(obj, "getConteudoInfo");
		}
		
		Integer qtdArquivos = 
			(Integer) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_QTD, Integer.class);
		if(qtdArquivos == null || qtdArquivos == 0)
			return SKIP_BODY;
		
		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		
		Layout layoutEnum = Layout.fromString(layout);
		
		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		Boolean possuiArquivos = arquivoNegocio.possuiArquivos(nomeClasse, idObjeto, layoutEnum, false);
		
		return possuiArquivos ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}
