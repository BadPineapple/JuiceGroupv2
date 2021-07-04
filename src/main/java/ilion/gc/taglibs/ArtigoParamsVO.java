package ilion.gc.taglibs;

import java.util.Date;

import org.joda.time.LocalDateTime;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.util.Uteis;

public class ArtigoParamsVO {

	private String site;
	private String categoria;
	private String subCategoriaUrl;
	private String secoes;
	private Boolean comTopicos = false;
	private String order;
	private String layout;// usado para ArquivoCategoriaListaTag
	private Integer firstResult;
	private Integer maxResults;
	private Date data;
	private String colunas;
	
	public static ArtigoParamsVO build() {
		return new ArtigoParamsVO();
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
	public String getSecoes() {
		return secoes;
	}
	public void setSecoes(String secoes) {
		this.secoes = secoes;
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

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Boolean getComTopicos() {
		return comTopicos;
	}

	public void setComTopicos(Boolean comTopicos) {
		this.comTopicos = comTopicos;
	}

	public ArtigoParamsVO comSite(String site) {
		
		if( Uteis.ehNuloOuVazio(site) ) {
			
			PropNegocio propNegocio = SpringApplicationContext.getBean(PropNegocio.class);
			
			site = propNegocio.findValueById(PropEnum.SITE);
		}
		
		this.site = site;
		
		return this;
	}
	
	public ArtigoParamsVO comCategoria(String categoria) {
		
		this.categoria = categoria;
		
		return this;
	}

	public ArtigoParamsVO comSubCategoriaUrl(String subCategoriaUrl) {
		
		this.subCategoriaUrl = subCategoriaUrl;
		
		return this;
	}
	
	public ArtigoParamsVO comSecoes(String secoes) {
		
		this.secoes = secoes;
		
		return this;
	}
	
	public ArtigoParamsVO comOrder(String order) {
		
		this.order = order;
		
		return this;
	}
	
	public ArtigoParamsVO comLayout(String layout) {
		
		this.layout = layout;
		
		return this;
	}
	
	public ArtigoParamsVO comFirstResult(Integer firstResult) {
		
		this.firstResult = firstResult;
		
		return this;
	}
	
	public ArtigoParamsVO comMaxResults(Integer maxResults) {
		
		this.maxResults = maxResults;
		
		return this;
	}
	
	public ArtigoParamsVO comColunas(String colunas) {
		
		this.colunas = colunas;
		
		return this;
	}

	public ArtigoParamsVO comTopicos(Boolean comTopicos) {
		
		this.comTopicos = comTopicos != null && comTopicos ? true : false;
		
		return this;
	}

	public ArtigoParamsVO comDataAtual() {
		
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
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((colunas == null) ? 0 : colunas.hashCode());
		result = prime * result + ((comTopicos == null) ? 0 : comTopicos.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((firstResult == null) ? 0 : firstResult.hashCode());
		result = prime * result + ((layout == null) ? 0 : layout.hashCode());
		result = prime * result + ((maxResults == null) ? 0 : maxResults.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((secoes == null) ? 0 : secoes.hashCode());
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
		ArtigoParamsVO other = (ArtigoParamsVO) obj;
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
		if (firstResult == null) {
			if (other.firstResult != null)
				return false;
		} else if (!firstResult.equals(other.firstResult))
			return false;
		if (layout == null) {
			if (other.layout != null)
				return false;
		} else if (!layout.equals(other.layout))
			return false;
		if (maxResults == null) {
			if (other.maxResults != null)
				return false;
		} else if (!maxResults.equals(other.maxResults))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (secoes == null) {
			if (other.secoes != null)
				return false;
		} else if (!secoes.equals(other.secoes))
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