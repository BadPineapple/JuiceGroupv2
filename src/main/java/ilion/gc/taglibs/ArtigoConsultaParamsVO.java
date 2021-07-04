package ilion.gc.taglibs;

import java.util.Date;

import org.joda.time.LocalDateTime;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.util.Uteis;

public class ArtigoConsultaParamsVO {

	private String site;
	private String categoria;
	private String subCategoriaUrl;
	private String artigoUrl;
	private Boolean comTopicos = false;
	private String order;
	private Date data;
	private String colunas;
	
	public static ArtigoConsultaParamsVO build() {
		return new ArtigoConsultaParamsVO();
	}
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubCategoriaUrl() {
		return subCategoriaUrl;
	}
	public void setSubCategoriaUrl(String subCategoriaUrl) {
		this.subCategoriaUrl = subCategoriaUrl;
	}
	public String getArtigoUrl() {
		return artigoUrl;
	}
	public void setArtigoUrl(String artigoUrl) {
		this.artigoUrl = artigoUrl;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getColunas() {
		return colunas;
	}
	public void setColunas(String colunas) {
		this.colunas = colunas;
	}

	public Boolean getComTopicos() {
		return comTopicos;
	}

	public void setComTopicos(Boolean comTopicos) {
		this.comTopicos = comTopicos;
	}

	public ArtigoConsultaParamsVO comSite(String site) {
		
		if( Uteis.ehNuloOuVazio(site) ) {
			PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
			site = propNegocio.findValueById(PropEnum.SITE);
		}
		
		this.site = site;
		
		return this;
	}
	
	public ArtigoConsultaParamsVO comCategoria(String categoria) {
		
		this.categoria = categoria;
		
		return this;
	}

	public ArtigoConsultaParamsVO comSubCategoriaUrl(String subCategoriaUrl) {
		
		this.subCategoriaUrl = subCategoriaUrl;
		
		return this;
	}
	
	public ArtigoConsultaParamsVO comArtigoUrl(String artigoUrl) {
		
		this.artigoUrl = artigoUrl;
		
		return this;
	}
	
	public ArtigoConsultaParamsVO comOrder(String order) {
		
		this.order = order;
		
		return this;
	}
	
	public ArtigoConsultaParamsVO comColunas(String colunas) {
		
		this.colunas = colunas;
		
		return this;
	}

	public ArtigoConsultaParamsVO comTopicos(Boolean comTopicos) {
		
		this.comTopicos = comTopicos != null && comTopicos ? true : false;
		
		return this;
	}

	public ArtigoConsultaParamsVO comDataAtual() {
		
		this.data = LocalDateTime
				.now()
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0)
				.toDate();
		
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artigoUrl == null) ? 0 : artigoUrl.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((colunas == null) ? 0 : colunas.hashCode());
		result = prime * result + ((comTopicos == null) ? 0 : comTopicos.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((subCategoriaUrl == null) ? 0 : subCategoriaUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtigoConsultaParamsVO other = (ArtigoConsultaParamsVO) obj;
		if (artigoUrl == null) {
			if (other.artigoUrl != null)
				return false;
		} else if (!artigoUrl.equals(other.artigoUrl))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (colunas == null) {
			if (other.colunas != null)
				return false;
		} else if (!colunas.equals(other.colunas))
			return false;
		if (comTopicos == null) {
			if (other.comTopicos != null)
				return false;
		} else if (!comTopicos.equals(other.comTopicos))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (subCategoriaUrl == null) {
			if (other.subCategoriaUrl != null)
				return false;
		} else if (!subCategoriaUrl.equals(other.subCategoriaUrl))
			return false;
		return true;
	}

	
	

	

	
	
	
}