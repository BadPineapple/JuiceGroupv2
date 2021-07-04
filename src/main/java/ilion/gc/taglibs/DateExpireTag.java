package ilion.gc.taglibs;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class DateExpireTag extends TagSupport {

	static Logger logger = Logger.getLogger(DateExpireTag.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public int doStartTag() {
		
//		Date data = new Date();
//
//		JspWriter out = pageContext.getOut();
//
//		try {
//			GCSiteNegocio gcSiteNegocio = SpringApplicationContext.getBean(GCSiteNegocio.class);
//			
//			Artigo artigo = gcSiteNegocio.consultarUltimoArtigoDateExpire();
//			
//			if (artigo != null) {
//				
//				if (artigo.getDataExpiracao().before(Uteis.acrescentar(data, Calendar.DAY_OF_MONTH, -5))) {
//					artigo.setDataExpiracao(Uteis.acrescentar(data, Calendar.MONTH, 6));
//					
//					gcSiteNegocio.atualizar(artigo);
//				}
//				
//				out.append(Uteis.formatarDataHoraUSA(artigo.getDataExpiracao(), "EEE, dd MMM yyyy"));
//			}else{
//				out.append(Uteis.formatarDataHoraUSA(new Date(), "EEE, dd MMM yyyy"));
//			}
//			
//		} catch (IOException e) {
//			logger.error("", e);
//		}

		return SKIP_BODY;
	}

}
