package ilion.arquivo.taglibs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.arquivo.negocio.ArquivoNegocio.Layout;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;

public class ArquivoSimplesTag extends TagSupport {

	static Logger logger = Logger.getLogger(ArquivoSimplesTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object obj;
	private String cssClass = "";
	private String comTabela = "false";
	private String layout;

	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setComTabela(String comTabela) {
		this.comTabela = comTabela;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	@SuppressWarnings("unchecked")
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
		
		Integer possuiArquivos = 
			(Integer) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ARQ_QTD, Integer.class);
		if(possuiArquivos == null || possuiArquivos == 0)
			return SKIP_BODY;

		String nomeClasse = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.NOME_CLASS, String.class);
		String idObjeto = (String) StringUtil.buscarValor(conteudoInfo, UteisGC.CONTEUDOINFO_NOS.ID_OBJETO, String.class);
		
		Layout layoutEnum = Layout.fromString(layout);
		
		ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
		List arquivos = arquivoNegocio.listarArquivos(nomeClasse, idObjeto, layoutEnum, false);

		if(arquivos == null || arquivos.isEmpty())
			return SKIP_BODY;

		JspWriter out = pageContext.getOut();

		try {

			UteisTag.setarImagemVideoPreview(arquivos);
			
			if("true".equals(comTabela))
				tabelasArquivos(arquivos, out);
			else 
				somenteArquivos(arquivos, out);
			
		} catch (IOException e) {
			logger.error("", e);
		}
		
		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	private void somenteArquivos(List arquivos, JspWriter out) throws IOException {
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();
			UteisTag.imprimir(arquivo, cssClass, out);
		}
	}

	@SuppressWarnings("unchecked")
	private void tabelasArquivos(List arquivos, JspWriter out) throws IOException {
		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			out.append("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"4\">");

			if(arquivo.getCreditos() != null && arquivo.getCreditos().length() != 0) {
				out.append("        <tr>");
				out.append("          <td align=\"left\"><span style=\"font-family: Verdana, Arial, Helvetica, sans-serif; color: #666666; font-size: 8px;\">").append(arquivo.getCreditos()).append("</span></td>"); 
				out.append("        </tr>");
			}

			out.append("        <tr>");
			out.append("  <td align=\"center\">");

			UteisTag.imprimir(arquivo, cssClass, out);

			out.append("  </td>");
			out.append("        </tr>");

			if(arquivo.getTexto() != null && arquivo.getTexto().length() != 0) {
				out.append("<tr>");
				out.append("  <td align=\"center\"><table width=\"").append((arquivo.getLargura()-20)+"").append("\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.append("						<tr>");
				out.append("							<td align=\"center\"><font color=\"#666666\" size=\"1\" face=\"Verdana, Arial, Helvetica, sans-serif\">").append(arquivo.getTexto()).append("</font></td>"); 
				out.append("						</tr>");
				out.append("					</table>");
				out.append("</td>");
				out.append("</tr>");
			}

			out.append("</table>");
		}
	}
}
