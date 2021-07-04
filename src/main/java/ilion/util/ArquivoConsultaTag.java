package ilion.util;

import javax.servlet.jsp.tagext.TagSupport;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import org.apache.log4j.Logger;


public class ArquivoConsultaTag extends TagSupport {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = Logger.getLogger(ArquivoConsultaTag.class);

  private String nomeClasse;
  private String idObjeto;
  private String layout;
  private String varRetorno;

  public void setNomeClasse(String nomeClasse) {
    this.nomeClasse = nomeClasse;
  }

  public void setIdObjeto(String idObjeto) {
    this.idObjeto = idObjeto;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public void setVarRetorno(String varRetorno) {
    this.varRetorno = varRetorno;
  }


  @Override
  public int doStartTag() {

    if (Uteis.ehNuloOuVazio(nomeClasse) &&
            idObjeto == null &&
            Uteis.ehNuloOuVazio(layout)) {
      logger.info("nomeClasse, idObjeto e layout nulos");
      return SKIP_BODY;
    }

    ArquivoNegocio arquivoNegocio = SpringApplicationContext.getBean(ArquivoNegocio.class);
    Arquivo arquivo = arquivoNegocio.getArquivo(nomeClasse, idObjeto, layout);

    pageContext.setAttribute(varRetorno, arquivo);

    return SKIP_BODY;
  }
}