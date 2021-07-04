package ilion.gc.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.gc.negocio.ArtigoLuceneNegocio;
import ilion.util.VLHForm;
import ilion.util.ValueListInfo;
import net.mlw.vlh.ValueList;

public class ArtigoBuscaTag extends TagSupport {

	private static final long serialVersionUID = 1L;


	static Logger logger = Logger.getLogger(ArtigoBuscaTag.class);

	private String varRetorno;
	private Integer qtd;
	private String stringPadraoCampo;

	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	public void setStringPadraoCampo(String stringPadraoCampo) {
		this.stringPadraoCampo = stringPadraoCampo;
	}

	public int doStartTag() {

		ArtigoLuceneNegocio artigoLuceneNegocio = SpringApplicationContext.getBean(ArtigoLuceneNegocio.class);
		VLHForm vlhForm = VLHForm.getVLHSession("ArtigoLuceneBuscaTag", (HttpServletRequest) pageContext.getRequest());

		if(stringPadraoCampo != null && stringPadraoCampo.length() != 0) {
			if(stringPadraoCampo.equals(vlhForm.getPalavraChave()))
				vlhForm.setPalavraChave("");
		}

		if(vlhForm.getPalavraChave() == null) {
			vlhForm.setPalavraChave("");
		}

		try {

			ValueList valueList = artigoLuceneNegocio.buscar(vlhForm, true, new ValueListInfo(vlhForm, qtd));
			pageContext.getRequest().setAttribute(varRetorno, valueList);

			if(vlhForm.getPalavraChave() != null) {
				pageContext.getRequest().setAttribute("palavraChave", vlhForm.getPalavraChave());
			}

		} catch (Exception e) {
			String m = "busca de artigos lucene";

			logger.error(m, e);
		}

		return SKIP_BODY;
	}
}