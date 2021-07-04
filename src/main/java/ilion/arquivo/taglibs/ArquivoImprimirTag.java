package ilion.arquivo.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.arquivo.negocio.Arquivo;

public class ArquivoImprimirTag extends TagSupport {

	static Logger logger = Logger.getLogger(ArquivoImprimirTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object obj;
	private String cssClass = "";
	private String comTabela = "false";


	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setComTabela(String comTabela) {
		this.comTabela = comTabela;
	}


	public int doStartTag() {
		
		if(obj == null)
			return SKIP_BODY;
		
		Arquivo arquivo = (Arquivo) obj;
		
		JspWriter out = pageContext.getOut();

		try {
			
			if("true".equals(comTabela)) {
				tabelasArquivos(arquivo, out);
			} else { 
				somenteArquivos(arquivo, out);
			}
			
		} catch (IOException e) {
			logger.error("", e);
		}

		return SKIP_BODY;
	}
	
	private void somenteArquivos(Arquivo arquivo, JspWriter out) throws IOException {
		UteisTag.imprimir(arquivo, cssClass, out);
	}
	
	private void tabelasArquivos(Arquivo arquivo, JspWriter out) throws IOException {

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
