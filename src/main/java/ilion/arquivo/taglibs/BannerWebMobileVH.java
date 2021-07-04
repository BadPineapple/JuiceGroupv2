package ilion.arquivo.taglibs;

import java.util.ArrayList;
import java.util.List;

import ilion.arquivo.negocio.Arquivo;

public class BannerWebMobileVH {

	String nomeClasse;
	
	String idObjeto;
	
	String urlWeb;
	
	String urlMobile;
	
	String link;
	
	String tipoLink;
	
	

	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public String getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getUrlWeb() {
		return urlWeb;
	}

	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}

	public String getUrlMobile() {
		return urlMobile;
	}

	public void setUrlMobile(String urlMobile) {
		this.urlMobile = urlMobile;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTipoLink() {
		return tipoLink;
	}

	public void setTipoLink(String tipoLink) {
		this.tipoLink = tipoLink;
	}

	public static List<BannerWebMobileVH> from(List<Arquivo> bannersWeb, List<Arquivo> bannersMobile) {
		
		List<BannerWebMobileVH> banners = new ArrayList<BannerWebMobileVH>();
		
		for (Arquivo arquivo : bannersWeb) {
			
			banners.add( BannerWebMobileVH.from(arquivo) );
			
		}
		
		if( bannersMobile != null ) {
			
			for (BannerWebMobileVH bannerWebMobileVH : banners) {
				
				bannerWebMobileVH.setarUrlMobile(bannersMobile);
				
			}
			
		}
		
		return banners;
	}

	private static BannerWebMobileVH from(Arquivo arquivo) {
		
		BannerWebMobileVH b = new BannerWebMobileVH();
		
		b.nomeClasse = arquivo.getNomeClasse();
		b.idObjeto = arquivo.getIdObjeto();
		b.urlWeb = arquivo.getUrl();
		b.urlMobile = arquivo.getUrl();
		b.link = arquivo.getLink();
		b.tipoLink = arquivo.getTipoLink();
		
		return b;
	}
	
	private void setarUrlMobile(List<Arquivo> bannersMobile) {
		
		for (Arquivo arquivo : bannersMobile) {
			
			if( ! this.nomeClasse.equals(arquivo.getNomeClasse()) ) {
				continue;
			}
			
			if( ! this.idObjeto.equals(arquivo.getIdObjeto()) ) {
				continue;
			}
			
			this.urlMobile = arquivo.getUrl();
			
		}
		
	}

	@Override
	public String toString() {
		return "BannerWebMobileVH [nomeClasse=" + nomeClasse + ", idObjeto=" + idObjeto + ", urlWeb=" + urlWeb
				+ ", urlMobile=" + urlMobile + "]";
	}
	
	
}