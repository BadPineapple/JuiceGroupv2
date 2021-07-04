package ilion.arquivo.taglibs;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ilion.arquivo.negocio.Arquivo;

public class BannersWebMobileGroupTag extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(BannersWebMobileGroupTag.class);
	
	private List<Arquivo> bannersWeb;
	private List<Arquivo> bannersMobile;
	private String varRetorno;

	public void setBannersWeb(List<Arquivo> bannersWeb) {
		this.bannersWeb = bannersWeb;
	}

	public void setBannersMobile(List<Arquivo> bannersMobile) {
		this.bannersMobile = bannersMobile;
	}

	public void setVarRetorno(String varRetorno) {
		this.varRetorno = varRetorno;
	}
	
	public int doStartTag() {
		
		if( bannersWeb == null ) {
			return SKIP_BODY;
		}
		
		try {
			
			List<BannerWebMobileVH> bannersWebMobile = BannerWebMobileVH.from(bannersWeb, bannersMobile);
			
			pageContext.getRequest().setAttribute(varRetorno, bannersWebMobile);

		} catch (Exception e) {
			String m = "";

			logger.error(m, e);
		}

		return SKIP_BODY;
	}
}